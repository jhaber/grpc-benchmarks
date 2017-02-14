package com.hubspot.dropwizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class BenchmarkResource {
  private static final BenchmarkResponse RESPONSE = createResponse();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public BenchmarkResponse get() {
    return RESPONSE;
  }

  private static BenchmarkResponse createResponse() {
    List<Pojo> values = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      Map<String, String> properties = new HashMap<>();
      properties.put("property1", String.valueOf(ThreadLocalRandom.current().nextLong()));
      properties.put("property2", String.valueOf(ThreadLocalRandom.current().nextLong()));
      properties.put("property3", String.valueOf(ThreadLocalRandom.current().nextLong()));
      properties.put("property4", String.valueOf(ThreadLocalRandom.current().nextLong()));
      properties.put("property5", String.valueOf(ThreadLocalRandom.current().nextLong()));

      values.add(new Pojo(i, "name_" + i, properties));
    }

    return new BenchmarkResponse(values);
  }
}
