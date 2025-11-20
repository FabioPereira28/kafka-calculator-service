package com.wit.rest.controller;

import com.wit.rest.config_kafka.RestProducer;
import com.wit.rest.config_kafka.ResponseTracker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.CompletableFuture;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CalculatorController.class)
@ContextConfiguration(classes = { CalculatorController.class })
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestProducer producerKafkaConfig;

    @MockBean
    private ResponseTracker tracker;

    @Test
    void testSumEndpoint() throws Exception {

        Mockito.when(producerKafkaConfig.enviarPedido("sum", 2.0, 3.0))
                .thenReturn("id123");

        CompletableFuture<Double> futuro = new CompletableFuture<>();
        futuro.complete(5.0);

        Mockito.when(tracker.criarFuture("id123"))
                .thenReturn(futuro);

        mockMvc.perform(get("/api/v1/calculator/sum?a=2&b=3"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }
}
