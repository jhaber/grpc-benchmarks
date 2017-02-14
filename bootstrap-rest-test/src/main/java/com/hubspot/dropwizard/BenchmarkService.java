package com.hubspot.dropwizard;

import com.hubspot.bootstrap.rest.RestApplication;

public class BenchmarkService {

  public static void main(String... args) throws Exception {
    RestApplication.newBuilder()
        .modules(binder -> binder.bind(BenchmarkResource.class))
        .build()
        .run(args);
  }
}
