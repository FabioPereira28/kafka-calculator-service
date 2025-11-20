package com.wit.rest.controller;

import com.wit.rest.config_kafka.RestProducer;
import com.wit.rest.config_kafka.ResponseTracker;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    private final RestProducer producerKafkaConfig;
    private final ResponseTracker tracker;

    public CalculatorController(RestProducer requestProducer, ResponseTracker tracker) {
        this.producerKafkaConfig = requestProducer;
        this.tracker = tracker;
    }

    @GetMapping("/sum")
    public double sum(@RequestParam("a") double a, @RequestParam("b") double b)throws Exception{
        String correlationId = producerKafkaConfig.enviarPedido("sum",a, b);
        CompletableFuture<Double> futuro = tracker.criarFuture(correlationId);
        return futuro.get(5, TimeUnit.SECONDS);
    }

    @GetMapping("/subtract")
    public double subtract(@RequestParam("a") double a, @RequestParam("b") double b)throws Exception{
        String correlationId = producerKafkaConfig.enviarPedido("sub",a, b);
        CompletableFuture<Double> futuro = tracker.criarFuture(correlationId);
        return futuro.get(5, TimeUnit.SECONDS);
    }

    @GetMapping("/multiply")
    public double multiply(@RequestParam("a") double a, @RequestParam("b") double b)throws Exception{
        String correlationId = producerKafkaConfig.enviarPedido("mul",a, b);
        CompletableFuture<Double> futuro = tracker.criarFuture(correlationId);
        return futuro.get(5, TimeUnit.SECONDS);
    }

    @GetMapping("/divide")
    public double divide(@RequestParam("a") double a, @RequestParam("b") double b)throws Exception{
        String correlationId = producerKafkaConfig.enviarPedido("div",a, b);
        CompletableFuture<Double> futuro = tracker.criarFuture(correlationId);
        return futuro.get(5, TimeUnit.SECONDS);
    }
}
