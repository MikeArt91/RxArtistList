package com.mikeart.rxartistlist.presentation.internal.di.modules;

import android.content.Context;

import com.mikeart.rxartistlist.data.cache.ListCache;
import com.mikeart.rxartistlist.data.cache.ListCacheImpl;
import com.mikeart.rxartistlist.data.executor.JobExecutor;
import com.mikeart.rxartistlist.data.repository.ArtistDataRepository;
import com.mikeart.rxartistlist.domain.executor.PostExecutionThread;
import com.mikeart.rxartistlist.domain.executor.ThreadExecutor;
import com.mikeart.rxartistlist.domain.repository.ArtistRepository;
import com.mikeart.rxartistlist.presentation.AndroidApplication;
import com.mikeart.rxartistlist.presentation.UIThread;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton ListCache provideListCache(ListCacheImpl listCache) {
    return listCache;
  }

  @Provides @Singleton
  ArtistRepository provideArtistRepository(ArtistDataRepository artistDataRepository) {
    return artistDataRepository;
  }
}
