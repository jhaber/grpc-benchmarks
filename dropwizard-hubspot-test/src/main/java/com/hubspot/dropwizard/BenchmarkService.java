package com.hubspot.dropwizard;

import com.hubspot.jersey.dropwizard.HubSpotService;

public class BenchmarkService {

  public static void main(String... args) throws Exception {
    HubSpotService.newBuilder().buildService().run(args);
  }
}
