package com.hubspot.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.1.2)",
    comments = "Source: benchmark.proto")
public class BenchmarkGrpc {

  private BenchmarkGrpc() {}

  public static final String SERVICE_NAME = "grpc.Benchmark";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest,
      com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse> METHOD_GET =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "grpc.Benchmark", "Get"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BenchmarkStub newStub(io.grpc.Channel channel) {
    return new BenchmarkStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BenchmarkBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BenchmarkBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static BenchmarkFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BenchmarkFutureStub(channel);
  }

  /**
   */
  public static abstract class BenchmarkImplBase implements io.grpc.BindableService {

    /**
     */
    public void get(com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest request,
        io.grpc.stub.StreamObserver<com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET,
            asyncUnaryCall(
              new MethodHandlers<
                com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest,
                com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse>(
                  this, METHODID_GET)))
          .build();
    }
  }

  /**
   */
  public static final class BenchmarkStub extends io.grpc.stub.AbstractStub<BenchmarkStub> {
    private BenchmarkStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BenchmarkStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BenchmarkStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BenchmarkStub(channel, callOptions);
    }

    /**
     */
    public void get(com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest request,
        io.grpc.stub.StreamObserver<com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BenchmarkBlockingStub extends io.grpc.stub.AbstractStub<BenchmarkBlockingStub> {
    private BenchmarkBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BenchmarkBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BenchmarkBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BenchmarkBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse get(com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BenchmarkFutureStub extends io.grpc.stub.AbstractStub<BenchmarkFutureStub> {
    private BenchmarkFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BenchmarkFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BenchmarkFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BenchmarkFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse> get(
        com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BenchmarkImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(BenchmarkImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get((com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest) request,
              (io.grpc.stub.StreamObserver<com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class BenchmarkDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.hubspot.grpc.BenchmarkProtos.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BenchmarkGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BenchmarkDescriptorSupplier())
              .addMethod(METHOD_GET)
              .build();
        }
      }
    }
    return result;
  }
}
