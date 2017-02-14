package com.hubspot.dropwizard;

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

import com.hubspot.connect.http.HttpClient;
import com.hubspot.connect.http.HttpRequest;
import com.hubspot.connect.http.HttpResponse;
import com.hubspot.java.thread.ThreadFactories;

import ch.qos.logback.classic.Level;

public class DropwizardHubSpotBenchmark {
  private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor(ThreadFactories.newDaemonThreadFactory("dropwizard-hubspot"));
  private static final HttpClient HTTP = HttpClient.newBuilder("benchmark").build();
  private static final HttpRequest REQUEST = HttpRequest.newBuilder().setUri("http://localhost:8080/dropwizard-hubspot").build();

  public static void main(String... args) throws Exception {
    EXECUTOR.submit(() -> {
      BenchmarkService.main("server", "dropwizard-hubspot-test/src/main/resources/benchmark.yaml");
      return null;
    });

    boolean started = false;
    long start = System.nanoTime();
    while (TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - start) < 30) {
      try {
        HttpResponse response = HTTP.execute(REQUEST);
        if (response.isSuccess()) {
          started = true;
          break;
        }
      } catch (Exception ignored) {}

      Thread.sleep(2_000);
    }

    if (!started) {
      throw new RuntimeException("Server didn't start up");
    }

    Logger logger = LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
    if (logger instanceof ch.qos.logback.classic.Logger) {
      ((ch.qos.logback.classic.Logger) logger).setLevel(Level.WARN);
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
  public BenchmarkResponse hitDropwizardHubSpot() throws IOException {
    HttpResponse response = HTTP.execute(REQUEST);
    if (response.isSuccess()) {
      return response.getAs(BenchmarkResponse.class);
    } else {
      throw new RuntimeException("Non-success: " + response.getStatusCode());
    }
  }
}
