# migração_micronaut_microservices

Sistema de bar que demonstra uma arquitetura de **microsserviços** usando **Micronaut**, com serviços de orquestração, pagamentos e gerenciamento de tabelas, orquestrados com `docker-compose`.

## Índice
- [Descrição](#descrição)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e uso](#instalação-e-uso)

## Tecnologias
- Micronaut
- Java 21 +
- Docker & Docker Compose

## Arquitetura
- `orchestrator-services` – gerencia o fluxo entre os microsserviços.
- `payment-services` – trata lógica de pagamento.
- `validation-product-services` – trata a lógica de validação de produtos.
- `table-services` – gerencia dados/tabulares.
- `invetory-services` – organiza todo o inventario gerenciando tudo que entra e sai.
- `docker-compose.yaml` – inicia todos os serviços em contêineres.

## Pré-requisitos
- Java 21+
- Docker
- Docker Compose

## Instalação e uso
```bash
git clone https://github.com/RavikFerreira/migracao_micronaut_microservices.git
cd migracao_micronaut_microservices
docker-compose up --build
