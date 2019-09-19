package com.gmail.dario.spring;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gmail.dario.spring.evolution.ReactorEvolution;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        final ExecutorService backGroundExecutor = Executors.newSingleThreadExecutor();

        final Future<List<Integer>> bestReactor = backGroundExecutor.submit(new ReactorEvolution());

        try {
            System.out.println(bestReactor.get().stream().map(it -> it.toString()).collect(Collectors.joining(",")));
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        SpringApplication.run(Application.class, args);
    }


}
