package com.wit.calculator.config_kafka;

import com.wit.calculator.service.CalculatorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorRequestListener {

    private final CalculatorService calculatorService;
    private final CalculatorResponseProducer responseProducer;

    public CalculatorRequestListener(CalculatorService calculatorService,
                                     CalculatorResponseProducer responseProducer) {
        this.calculatorService = calculatorService;
        this.responseProducer = responseProducer;
    }

    @KafkaListener(topics = "calculos_request", groupId = "calculator-group")
    public void onMessage(String mensagem) {

        String[] partes = mensagem.split(";");
        String correlationId = partes[0];
        String operacao = partes[1];
        double a = Double.parseDouble(partes[2]);
        double b = Double.parseDouble(partes[3]);
        double resultado=0;

        switch(operacao){
            case "sum":
                resultado = calculatorService.sum(a, b);
                break;
            case "sub":
                resultado = calculatorService.subtract(a, b);
                break;
            case "mul":
                resultado = calculatorService.multiply(a, b);
                break;
            case "div":
                resultado = calculatorService.divide(a, b);
                break;
        }
        responseProducer.enviarResposta(correlationId, resultado);
    }
}
