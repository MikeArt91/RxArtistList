package com.mikeart.rxartistlist.data.cache;

/**
 * Created by Mike on 11.11.2016.
 */

import android.content.Context;
import com.mikeart.rxartistlist.data.cache.serializer.JsonSerializer;
import com.mikeart.rxartistlist.data.entity.ArtistEntity;
import com.mikeart.rxartistlist.data.exception.ArtistNotFoundException;
import com.mikeart.rxartistlist.domain.executor.ThreadExecutor;
import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * {@link ListCache} implementation.
 */

@Singleton
public class ListCacheImpl implements ListCache {
    private static final String SETTINGS_FILE_NAME = "com.fernandocejas.rxartistlist.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "list";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link ListCacheImpl}.
     *
     * @param context A
     * @param artistCacheSerializer {@link JsonSerializer} for object serialization.
     * @param fileManager {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    public ListCacheImpl(Context context, JsonSerializer artistCacheSerializer,
                         FileManager fileManager, ThreadExecutor executor) {
        if (context == null || artistCacheSerializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = artistCacheSerializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override public Observable<List<ArtistEntity>> get() {
        return Observable.create(subscriber -> {
            File artistEntityFile = ListCacheImpl.this.buildFile();
            String fileContent = ListCacheImpl.this.fileManager.readFileContent(artistEntityFile);
            List<ArtistEntity> artistEntityList = ListCacheImpl.this.serializer.deserializeArtist(fileContent);

            if (artistEntityList != null) {
                subscriber.onNext(artistEntityList);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new ArtistNotFoundException());
            }
        });
    }

    @Override public void put(List<ArtistEntity> artistEntityList) {
        if (artistEntityList != null) {
            File artistEntityFile = this.buildFile();
            if (!isCached()) {
                String jsonString = this.serializer.serializeArtist(artistEntityList);
                this.executeAsynchronously(new CacheWriter(this.fileManager, artistEntityFile,
                        jsonString));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override public boolean isCached() {
        File artistEntitiyFile = this.buildFile();
        return this.fileManager.exists(artistEntitiyFile);
    }

    @Override public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * build the file.
     * @return A valid file.
     */
    private File buildFile() {
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);

        return new File(fileNameBuilder.toString());
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
