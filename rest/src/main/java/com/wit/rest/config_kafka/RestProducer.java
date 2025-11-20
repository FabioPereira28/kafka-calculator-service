package com.wit.rest.config_kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.UUID;


@Component
public class RestProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public RestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String enviarPedido(String operacao,double a, double b) {
        String correlationId = UUID.randomUUID().toString();
        String payload = correlationId + ";" + operacao + ";" + a + ";" + b;
        kafkaTemplate.send("calculos_request", payload);
        return correlationId;
    }
}
