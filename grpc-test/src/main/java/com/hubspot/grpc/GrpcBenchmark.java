package com.hubspot.grpc;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubspot.grpc.BenchmarkProtos.BenchmarkRequest;
import com.hubspot.grpc.BenchmarkProtos.BenchmarkResponse;
import com.hubspot.java.thread.ThreadFactories;

import ch.qos.logback.classic.Level;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcBenchmark {

  static {
    Logger logger = LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
    if (logger instanceof ch.qos.logback.classic.Logger) {
      ((ch.qos.logback.classic.Logger) logger).setLevel(Level.WARN);
    }
  }

  private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor(ThreadFactories.newDaemonThreadFactory("grpc"));
  private static final com.hubspot.grpc.BenchmarkGrpc.BenchmarkBlockingStub CLIENT = createClient();

  public static void main(String... args) throws Exception {
    EXECUTOR.submit(() -> {
      Server server = ServerBuilder.forPort(8080)
          .addService(new BenchmarkImpl())
          .build()
          .start();
      server.awaitTermination();
      return null;
    });

    boolean started = false;
    long start = System.nanoTime();
    while (TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - start) < 30) {
      try {
        CLIENT.get(BenchmarkRequest.getDefaultInstance());
        started = true;
        break;
      } catch (Exception ignored) {}

      Thread.sleep(2_000);
    }

    if (!started) {
      throw new RuntimeException("Server didn't start up");
    }

    Options options = new OptionsBuilder()
        .warmupIterations(5)
        .measurementIterations(10)
        .threads(5)
        .forks(1)
        .resultFormat(ResultFormatType.TEXT)
        .timeUnit(TimeUnit.MILLISECONDS)
        .addProfiler(GCProfiler.class)
        .build();
    new Runner(options).run();
  }

  @Benchmark
  @BenchmarkMode({ Mode.Throughput, Mode.AverageTime, Mode.SampleTime })
  public BenchmarkResponse hitGrpc() throws IOException {
    return CLIENT.get(BenchmarkRequest.getDefaultInstance());
  }

  private static com.hubspot.grpc.BenchmarkGrpc.BenchmarkBlockingStub createClient() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext(true)
        .compressorRegistry(CompressorRegistry.getDefaultInstance())
        .decompressorRegistry(DecompressorRegistry.getDefaultInstance())
        .build();

    return com.hubspot.grpc.BenchmarkGrpc.newBlockingStub(channel);
  }
}
