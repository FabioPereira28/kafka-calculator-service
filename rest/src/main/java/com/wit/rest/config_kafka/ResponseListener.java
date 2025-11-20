package com.wit.rest.config_kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ResponseListener {
    private final ResponseTracker tracker;

    public ResponseListener(ResponseTracker tracker) {
        this.tracker = tracker;
    }

    @KafkaListener(topics = "calculos_response", groupId = "rest-group")
    public void onMessage(String mensagem) {
        String[] partes = mensagem.split(";");
        String correlationId = partes[0];
        double resultado = Double.parseDouble(partes[1]);
        tracker.completar(correlationId, resultado);
    }
}
