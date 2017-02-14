package com.hubspot.dropwizard;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BenchmarkResponse {
  private final List<Pojo> pojos;

  @JsonCreator
  public BenchmarkResponse(@JsonProperty("pojos") List<Pojo> pojos) {
    this.pojos = pojos;
  }

  public List<Pojo> getPojos() {
    return pojos;
  }
}
