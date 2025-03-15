# Proyecto Reactivo con Spring Boot

## Descripción
Este proyecto es una aplicación reactiva desarrollada con **Spring Boot** y **Reactor**, que permite gestionar trazas de mensajes. Además, incluye integración con **Micrometer** para la recopilación de métricas.

## Tecnologías utilizadas
- **Spring Boot** (WebFlux)
- **Project Reactor**
- **MongoDB** (Reactive Streams Driver)
- **Micrometer** (para métricas)
- **Prometheus** (para exportación de métricas)

## Configuración

### Requisitos previos
- **Java 8+**
- **MongoDB** en ejecución (puede configurarse en `application.yml`)
- **Prometheus** si se desea monitoreo externo

### Configuración en `application.yml`
```yaml
server:
  port: 9898

spring:
  data:
    mongodb:
      uri: "mongodb://127.0.0.1:27017"
      database: exampleDb

management:
  endpoints:
    web:
      exposure:
        include: health,info,prometheus,metrics
  metrics:
    export:
      prometheus:
        enabled: true
```

## Endpoints

### 1. Insertar una traza
- **Método:** `POST`
- **URL:** `/trace`
- **Cuerpo:**
```json
{
  "sessionId": "abc123",
  "payload": "Mensaje de prueba",
  "ts": "2025-03-14T12:00:00Z"
}
```

### 2. Obtener trazas por rango de fechas
- **Método:** `GET`
- **URL:** `/trace`
- **Cuerpo:**
```json
{
  "from": "2023-03-13T00:00:00+00:00",
  "to": "2025-03-30T23:59:59+00:00"
}
```

## Monitoreo de métricas
El servicio expone métricas en el endpoint:
```
http://localhost:9898/actuator/metrics
```
Algunas métricas disponibles:
- `hacom.test.developer.insert.rx`: Contador de inserciones
- `jvm.memory.used`: Uso de memoria de la JVM
- `system.cpu.usage`: Uso de CPU del sistema

## Ejecución
Para ejecutar el proyecto:
```sh
mvn spring-boot:run
```
Luego, acceder a:
```
http://localhost:9898/actuator/metrics
```
para verificar las métricas.

## Autor
Desarrollado por **Bismark Lozada**.

