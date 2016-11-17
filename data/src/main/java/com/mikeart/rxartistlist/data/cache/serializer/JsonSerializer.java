package com.mikeart.rxartistlist.data.cache.serializer;

import com.mikeart.rxartistlist.data.entity.ArtistEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class artist as Serializer/Deserializer for artist entities.
 */
@Singleton
public class JsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public JsonSerializer() {}

  /** ARTIST
   * Serialize an object to Json.
   *
   * @param artistEntityList {@link List<ArtistEntity>} to serialize.
   */

  public String serializeArtist(List<ArtistEntity> artistEntityList) {
    String jsonString = gson.toJson(artistEntityList);
    return jsonString;
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link List<ArtistEntity>}
   */
  public List<ArtistEntity> deserializeArtist(String jsonString) {
    Type listType = new TypeToken<List<ArtistEntity>>(){}.getType();
    List<ArtistEntity> artistEntityList = gson.fromJson(jsonString,listType);
    return artistEntityList;
  }



}
