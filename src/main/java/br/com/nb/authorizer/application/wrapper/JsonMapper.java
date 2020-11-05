package br.com.nb.authorizer.application.wrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonMapper<T> {
  private static final String DATA_PATTERN_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

  private Gson gson;

  public JsonMapper() {
    this.gson = new GsonBuilder().setDateFormat(DATA_PATTERN_FORMAT).create();
  }

  public T mapperJsonToObject(String json, Class<T> clazz) {
    return gson.fromJson(json, clazz);
  }

  public String mapperObjectToJson(T content) {
    return gson.toJson(content);
  }
}
