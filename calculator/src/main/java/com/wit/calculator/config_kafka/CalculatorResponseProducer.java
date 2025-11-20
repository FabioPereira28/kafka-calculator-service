package com.wit.calculator.config_kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CalculatorResponseProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CalculatorResponseProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarResposta(String correlationId, double resultado) {
        String payload = correlationId + ";" + resultado;
        kafkaTemplate.send("calculos_response", payload);
    }
}
