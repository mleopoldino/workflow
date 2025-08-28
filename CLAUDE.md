# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot + Camunda BPM workflow application for Esfera demo, implementing a points redemption system with manual approval processes. The application uses H2 database for persistence and includes workflow automation through Camunda's BPMN engine.

## Build Commands

- **Compile**: `mvn compile`
- **Run application**: `mvn spring-boot:run`
- **Build JAR**: `mvn clean package`
- **Clean**: `mvn clean`

## Development Setup

- **Java Version**: 17
- **Framework**: Spring Boot 3.4.4
- **Workflow Engine**: Camunda BPM 7.23.0
- **Database**: H2 (file-based: `./camunda-h2-database`)
- **Admin Credentials**: demo/demo for Camunda Cockpit/Tasklist

## Architecture

### Core Components

- **Application.java**: Main Spring Boot application entry point
- **Delegate Classes** (`src/main/java/com/esfera/camunda/delegates/`):
  - `CalcularMediaPontosBeneficios`: Calculates points-to-benefits ratio
  - `ConsultarValidadePontos`: Validates point expiration
  - `NotificarClientePontosInvalidos`: Handles invalid points notifications

### Process Resources

- **BPMN Workflows** (`src/main/resources/bpmn/`):
  - `demo-esfera.bpmn`: Main points redemption workflow
  - `pontos-invalidos.bpmn`: Invalid points handling process
- **DMN Decision Tables** (`src/main/resources/dmn/`):
  - `aprovacao-resgate.dmn`: Automated redemption approval rules
- **Forms** (`src/main/resources/static/forms/`):
  - `form_solicitacao.form`: Redemption request form
  - `form_aprovacao.form`: Manual approval form

## Workflow Process

The main workflow (`workflow-process`) implements a points redemption system:
1. User submits redemption request via start form
2. System validates point expiration
3. Calculates points-to-benefits ratio
4. Routes to manual approval task if needed
5. Processes approval/rejection with timer events

## Database

H2 database files are stored locally:
- `camunda-h2-database.mv.db` (main database file)
- `camunda-h2-database.trace.db` (trace file)

Access H2 console at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./camunda-h2-database`
- User: `sa`
- Password: (empty)

## Camunda Web Apps

When running the application, access:
- **Cockpit**: `http://localhost:8080/camunda/app/cockpit/`
- **Tasklist**: `http://localhost:8080/camunda/app/tasklist/`
- **Admin**: `http://localhost:8080/camunda/app/admin/`

Use credentials: `demo/demo`