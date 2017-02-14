package com.hubspot.dropwizard;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pojo {
  private final long id;
  private final String name;
  private final Map<String, String> properties;

  @JsonCreator
  public Pojo(@JsonProperty("id") long id,
              @JsonProperty("name") String name,
              @JsonProperty("properties") Map<String, String> properties) {
    this.id = id;
    this.name = name;
    this.properties = properties;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Map<String, String> getProperties() {
    return properties;
  }
}
