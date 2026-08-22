# Plano de implementação e análise do projeto Almeida Presença

## 1. Visão geral do projeto
Projeto Spring Boot de backend para gestão de presença escolar/academica, com:
- API REST em Java 17
- Spring Boot 3.4.4
- JPA + PostgreSQL
- Spring Security + JWT
- CORS configurado por propriedade
- Email e autenticação de usuários

## 2. Arquitetura atual observada
Estrutura principal:
- src/main/java/br/com/almeidaPresenca/almeidaPresenca
    - controllers/: endpoints REST (AlunoController, AuthController)
    - services/: regras de negócio (AlunoService, EmailService)
    - dao/: acesso a dados customizado
    - models/: entidades JPA (AlunoVO, AulaVO, PlanoVO, PresencaVO, GraduacaoVO)
    - infra/security/: configurações de autenticação e JWT
    - infra/cors/: CORS
    - dto/: classes de transferência

Observações:
- Há uso de DAO manual e serviços, com padrão funcional básico para API.
- O projeto usa Spring Security com autenticação stateless.
- Possui CORS ajustado para leitura por variavel de ambiente.
- Há forte dependência de variáveis de ambiente para conexão e segredos.

## 3. Objetivos do plano
- Garantir uma configuração segura e reproduzível para dev/prod.
- Padronizar ambiente e variáveis.
- Reduzir risco de falha por CORS, JWT ou secrets em produção.
- Criar base de testes e validações de build.
- Preparar deploy com documentação e checklist.

## 4. Fases de implementação

### Fase 1 — Diagnóstico e arquitetura
- Mapear controllers, services, entities e fluxo principal.
- Validar se as entidades possuem relacionamento e regras consistentes.
- Verificar se as DAOs possuem lógica duplicada ou inconsistências.
- Confirmar o fluxo de login/autenticação, reset de senha, envio de e-mails e presença.

### Fase 2 — Configuração e ambiente
- Manter application.properties como fonte principal.
- Usar variáveis de ambiente para dados sensíveis.
- Padronizar:
    - DB_URL, DB_USERNAME, DB_PASSWORD
    - TOKEN_SECRET
    - MAIL_HOST, MAIL_PORT, MAIL_USER, MAIL_PASSWORD
    - URL_FRONTEND / APP_CORS_ALLOWED_ORIGINS
- Definir uso de .env para desenvolvimento local e variáveis do ambiente para produção.
- Evitar defaults sensíveis em produção.

### Fase 3 — Segurança
- Revisar SecurityConfig:
    - endpoints públicos vs protegidos
    - expiração de tokens
    - permitir somente endpoints necessários
- Revisar CORS:
    - permitir somente origins autorizadas
    - remover fallback inseguro
    - validar configuração no startup
- Revisar JWT:
    - secret forte e externa ao código
    - decidir expiração adequada
    - conferir issuer/audiente conforme regra de negócio

### Fase 4 — Persistência e regras de negócio
- Validar entidades JPA (campos obrigatórios, relações e naming conventions).
- Revisar AlunoService e DAO para separar regras de negócio de consulta SQL manual.
- Identificar se o código precisa de repositories do Spring Data JPA para reduzir acoplamento.
- Verificar consistência do schema do PostgreSQL, naming strategy e migrations.

### Fase 5 — Qualidade e testes
- Criar testes para:
    - AuthController/login
    - AlunoController CRUD
    - AlunoService regras de negócio
    - geração/validação do JWT
    - CORS e security filters
- Definir build mínimo de CI: mvn test e mvn package.
- Garantir cobertura crítica para casos de erro.

### Fase 6 — Deploy e operação
- Validar Dockerfile/docker-compose.
- Confirmar que as variáveis de ambiente ficam fora do código.
- Preparar checklist de produção:
    - aplicar secret manager ou envs do servidor
    - configurar CORS correto
    - confirmar banco e e-mail em produção
    - validar health do app e logs

## 5. Riscos e pontos de atenção
- Uso de secrets no código ou em .env local sem controle.
- CORS com origem fixa ou fallback inseguro.
- Autenticação sem rigor em endpoints públicos.
- Dependência excessiva de DAO próprio em vez de repositórios Spring.
- Falta de testes automatizados para regras críticas.
- Ambiente de desenvolvimento e produção sem padronização.

## 6. Prioridades recomendadas
1. Segurança e configuração (JWT/CORS/ENV)
2. Validação de build e execução local
3. Padronização de arquitetura e persistência
4. Testes automatizados
5. Deploy e observabilidade

## 7. Próximo passo prático
- Revisar todos os controllers e services em ordem de importância.
- Validar segurança da autenticação e endpoints públicos.
- Executar build do projeto e corrigir falhas reais.
- Depois, consolidar testes e documentação.

## 8. Resultado esperado
Obter um backend Spring Boot mais seguro, documentado, configurável por ambiente e pronto para evoluir sem depender de hardcoded values ou setup manual frágil.
