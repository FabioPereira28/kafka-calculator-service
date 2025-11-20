# Kafka Calculator Service

Este projeto é uma API RESTful de calculadora que suporta soma, subtração, multiplicação e divisão, usando Spring Boot e comunicação via Apache Kafka entre os módulos `rest` e `calculator`.

## Como executar

1. Certifica-te de que tens o Docker instalado.
2. Na raiz do projeto, executa:
   ```bash
   docker compose up -d --build
3. A API ficará disponível em http://localhost:8080/api/v1/calculator.

# Endpoints:
- Soma
  ```bash
  GET /api/v1/calculator/sum?a=VALOR1&b=VALOR2
Retorna a soma de a + b.

- Subtração
  ```bash
  GET /api/v1/calculator/subtract?a=VALOR1&b=VALOR2
Retorna a subtração de a - b.

- Multiplicação
  ```bash
  GET /api/v1/calculator/multiply?a=VALOR1&b=VALOR2
Retorna a multiplicação de a * b.

- Divisão
  ```bash
  GET /api/v1/calculator/divide?a=VALOR1&b=VALOR2
Retorna a divisão de a / b.
