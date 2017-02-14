package com.hubspot.grpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest;
import com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse;
import com.hubspot.grpc.BenchmarkProtos.Value;

import io.grpc.stub.StreamObserver;

public class BenchmarkImpl extends com.hubspot.grpc.BenchmarkGrpc.BenchmarkImplBase {
  private static final BenchmarkResponse RESPONSE = createResponse();

  @Override
  public void get(BenchmarkRequest request, StreamObserver<BenchmarkResponse> responseObserver) {
    responseObserver.onNext(RESPONSE);
    responseObserver.onCompleted();
  }

  private static BenchmarkResponse createResponse() {
    List<Value> values = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      Map<String, String> properties = new HashMap<>();
      properties.put("property1", String.valueOf(ThreadLocalRandom.current().nextLong()));
      properties.put("property2", String.valueOf(ThreadLocalRandom.current().nextLong()));
      properties.put("property3", String.valueOf(ThreadLocalRandom.current().nextLong()));
      properties.put("property4", String.valueOf(ThreadLocalRandom.current().nextLong()));
      properties.put("property5", String.valueOf(ThreadLocalRandom.current().nextLong()));

      values.add(Value.newBuilder().setId(i).setName("name_" + i).putAllProperties(properties).build());
    }

    return BenchmarkResponse.newBuilder().addAllValues(values).build();
  }
}
