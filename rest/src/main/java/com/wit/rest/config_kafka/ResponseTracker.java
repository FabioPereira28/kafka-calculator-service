package com.wit.rest.config_kafka;

import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ResponseTracker {
    private final ConcurrentHashMap<String, CompletableFuture<Double>> pendentes = new ConcurrentHashMap<>();

    public CompletableFuture<Double> criarFuture(String correlationId) {
        CompletableFuture<Double> f = new CompletableFuture<>();
        pendentes.put(correlationId, f);
        return f;
    }

    public void completar(String correlationId, double resultado) {
        CompletableFuture<Double> f = pendentes.remove(correlationId);
        if (f != null) {
            f.complete(resultado);
        };
    }
}
