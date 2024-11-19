# IRRIOT RESTful API

## Overview

The IRRIOT RESTful API is designed to support operations for managing `Things` and `ThingStates` in the IRRIOT ecosystem. This API will integrate with the EasyIRRIOT Web Application, EasyIRRIOT Mobile App, and Embedded IoT System. The API provides endpoints to handle the creation and retrieval of `Things` and `ThingStates` with specific business rules.

### Bounded Contexts:
- **Inventory**: Handles `Things`
- **Observability**: Handles `ThingStates`
- **Shared**: Contains common elements and functionality

### Database:
- The data will be stored in a relational database (MySQL) in the `irriot` schema.

## Technical Constraints
1. **Things** and **ThingStates** are managed in separate bounded contexts.
2. Each `Thing` and `ThingState` must adhere to certain validation rules (e.g., unique serial number, valid thresholds, etc.).
3. **Audit** properties (CreatedAt and UpdatedAt) will be automatically set during creation and updates.
4. The API will be packaged as a `.zip` file for deployment.

## Endpoints

### `/api/v1/things`

#### POST: Create a Thing
This endpoint is used to create a new `Thing` in the system. The `serialNumber` and `id` will be auto-generated. The default `operationMode` will be set to `ScheduleDriven` upon creation.

- **Request Body (JSON)**:
  ```json
  {
    "model": "DeviceModelX",
    "maximumTemperatureThreshold": 30.00,
    "minimumHumidityThreshold": 40.00
  }
  ```

- **Response (201 Created)**:
  ```json
  {
    "id": 1,
    "serialNumber": "UUID-12345",
    "model": "DeviceModelX",
    "operationMode": "ScheduleDriven",
    "maximumTemperatureThreshold": 30.00,
    "minimumHumidityThreshold": 40.00
  }
  ```

#### GET: Get a Thing by ID
This endpoint retrieves a `Thing` by its `id`.

- **Request (URL)**:
  `/api/v1/things/{id}`

- **Response (200 OK)**:
  ```json
  {
    "id": 1,
    "serialNumber": "UUID-12345",
    "model": "DeviceModelX",
    "operationMode": "ScheduleDriven",
    "maximumTemperatureThreshold": 30.00,
    "minimumHumidityThreshold": 40.00
  }
  ```

### `/api/v1/thing-states`

#### POST: Create a Thing State
This endpoint is used to create a new `ThingState` for an existing `Thing`. The `thingSerialNumber` is required to associate the `ThingState` with a `Thing`. The `currentOperationMode`, `currentTemperature`, `currentHumidity`, and `collectedAt` attributes must also be provided.

- **Request Body (JSON)**:
  ```json
  {
    "thingSerialNumber": "UUID-12345",
    "currentOperationMode": 1,
    "currentTemperature": 25.00,
    "currentHumidity": 60.00,
    "collectedAt": "2024-11-19T10:00:00"
  }
  ```

- **Response (201 Created)**:
  ```json
  {
    "id": 1,
    "thingSerialNumber": "UUID-12345",
    "currentOperationMode": 1,
    "currentTemperature": 25.00,
    "currentHumidity": 60.00,
    "collectedAt": "2024-11-19T10:00:00"
  }
  ```

## Business Rules
1. **Thing Registration**:
   - The `serialNumber` is unique and automatically generated (UUID).
   - The `operationMode` defaults to `ScheduleDriven`.
   - `maximumTemperatureThreshold` must be between -40.00 and 85.00.
   - `minimumHumidityThreshold` must be between 0.00 and 100.00.
   
2. **Thing State Registration**:
   - A `ThingState` must be associated with an existing `Thing`.
   - The `collectedAt` timestamp cannot be in the future.
   - A combination of `thingSerialNumber` and `collectedAt` must be unique.
   - `currentOperationMode` must be an integer between 0 and 2.

3. **Audit Properties**:
   - `CreatedAt` and `UpdatedAt` are automatically set when records are created or updated.

## Architecture and Design Patterns
- **Domain-Driven Design (DDD)**: The system is organized into bounded contexts, with clear separation between aggregates (`Thing` and `ThingState`).
- **CQRS**: Command and Query Responsibility Segregation is used to separate write and read operations for better scalability.
- **Anti-Corruption Layer (ACL)**: ACL is used to maintain boundaries between the `Inventory` and `Observability` bounded contexts.

## Documentation

### OpenAPI Specification (Swagger)

The OpenAPI specification for the RESTful API can be generated and used for documentation purposes.

---

**Author**: `Smartschoolfish9`

**University**: UPC - Software Engineering
