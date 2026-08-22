# almeidaPresenca

Projeto Spring Boot para gerenciamento de presença (backend).

## Visão geral
API REST construída com Spring Boot que expõe endpoints para autenticação e controle de presença. Inclui configuração CORS parametrizável para permitir frontends em diferentes ambientes (dev/prod).

## Tecnologias
- Java + Spring Boot
- Maven (pom.xml presente no repositório)

## Requisitos
- JDK 17+
- Maven

## Executando localmente (desenvolvimento)
1. Ajustar variáveis de ambiente ou application.properties se necessário.
2. Executar:
   mvn clean install

## Configuração CORS
As origens permitidas para CORS são parametrizáveis. Importante: não há fallback — a configuração é obrigatória em produção. Prioridade:
1. Variável de ambiente: URL_FRONTEND
2. Propriedade em application.properties: app.cors.allowed-origins

Exemplos:
- Variável de ambiente (Linux/macOS):
  export URL_FRONTEND=https://meuprod.com

- application.properties:
  app.cors.allowed-origins=http://localhost:4200,https://meuprod.com
Observação: múltiplos origins são separados por vírgula. Barras finais nas URLs são removidas automaticamente.

## Ambiente de produção
Definir URL_FRONTEND no ambiente de execução do servidor para evitar editar arquivos antes do deploy, por exemplo:
  export URL_FRONTEND=https://meuprod.com