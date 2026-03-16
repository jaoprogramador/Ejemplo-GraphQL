# 📚 Recetas GraphQL API

Este proyecto es una aplicación **Spring Boot** que implementa una API GraphQL para la gestión de un recetario profesional. Utiliza una arquitectura de capas desacoplada para manejar consultas (Queries) y modificaciones (Mutations) de recetas.

## 🚀 Tecnologías Utilizadas

- **Java 21/23**
- **Spring Boot 4.0.x** (Spring GraphQL)
- **Lombok** (Generación de código)
- **Maven** (Gestión de dependencias)
- **GraphiQL** (Interfaz de pruebas interactiva)

---

## 🏗️ Arquitectura del Sistema

La aplicación está organizada siguiendo el patrón de diseño de capas de Spring:



1.  **Capa de Presentación (Controller)**: Define los EntryPoints de GraphQL y mapea los esquemas `.graphqls`.
2.  **Capa de Servicio (Service)**: Gestiona la lógica de negocio, validaciones y orquestación de datos.
3.  **Capa de Datos (Repository)**: Actualmente implementa persistencia en memoria para pruebas rápidas.
4.  **Mappers**: Encargados de la conversión entre DTOs (Data Transfer Objects) y Entidades de dominio.

---

## 📁 Estructura del Proyecto

```text
src/main/java/com/jao/spring/graphql/
├── controller/    # RecetaController
├── service/       # RecetaService
├── repository/    # RecetaRepository (In-Memory)
├── dto/           # RecetaDto (Input Objects)
├── entity/        # Receta (Domain Model)
├── mapper/        # RecetaMapper
├── exception/     # Custom Exceptions & Global Handler
└── RecetasGraphqlApplication.java
```

## 📋 Componentes Principales

### 1. **RecetasController**

Controlador GraphQL que maneja las operaciones CRUD de recetas:

#### Queries disponibles:

- listarTodasLasRecetas: Devuelve la lista completa de recetas activas.

- obtenerRecetasRecientes(conteo, compensacion): Consulta paginada de recetas.

- obtenerRecetaPorId(id): Recupera una receta por su identificador único.

#### Mutations disponibles:

- `guardarReceta(recetaDto)`: Crea un nueva receta
- `EliminarRecetaById(id)`: Elimina una receta por ID

### 2. **Entidad receta**

La entidad principal que representa una publicación con los siguientes campos:

- `id`: Identificador único

- `titulo`: Nombre de la receta

- `descripcion`: Resumen o introducción

- `elaboracion`: Pasos detallados para cocinar

- `tiempoPreparacion`: Minutos de preparación previa

- `tiempoCoccion`: Minutos de fuego/horno

- `comensales`: Número de personas

- `dificultad`: Nivel (Fácil, Media, Difícil)

- `categoria`: Tipo de plato (Postre, Principal, etc.)

- `ingredientes`: Lista de Strings con los componentes

- `urlImagen`: Enlace a la fotografía del plato

- `autor`: Nombre del creador de la receta

- `fechaCreacion`: Marca de tiempo de registro (fechaCreacion)

- `fechaActualizacion`: Última modificación (fechaActualizacion)

- `fechaEliminacion`: Fecha para borrado lógico (fechaEliminacion)

### 3. **RecetaDto**

DTO utilizado para la creación y actualización de recetas:

- titulo, descripcion, elaboracion: Información textual básica

- tiempoPreparacion, tiempoCoccion, comensales: Datos numéricos

- dificultad, categoria, autor, urlImagen: Metadatos

- ingredientes: Array de hilos (Strings)
### 4. **Schema GraphQL**

Definido en , incluye: `schema.graphqls`

- Tipo con todos sus campos `Receta`
- Input para operaciones de escritura `RecetaDto`
- Queries para lectura de datos
- Mutations para modificación de datos

## 🚀 Configuración y Ejecución

### Prerrequisitos

- Java 21
- Maven 3.x

### Comandos de ejecución

``` bash
# Compilar el proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Ejecutar la aplicación
mvn spring-boot:run
```

### Acceso a GraphQL

Una vez iniciada la aplicación, puedes acceder a:

- **GraphQL Playground**: `http://localhost:8080/graphiql`
- **Endpoint GraphQL**: `http://localhost:8080/graphql`

## 📊 Ejemplos de Uso

### Query - Obtener todas las recetas

``` graphql
query {
	listarTodasLasRecetas {
    id
    titulo
    autor
    dificultad
    ingredientes
  }
}
```

### Query - Obtener recetas recientes

``` graphql
query {
  obtenerRecetasRecientes(count: 10, offset: 0) {
    id
    titulo
    fechaCreacion
  }
}
```

### Mutation - Crear una receta receta

``` graphql
mutation {
  guardarReceta(recetaDto: {
    titulo: "Tortilla de Patatas",
    descripcion: "Plato tradicional español",
    elaboracion: "Freír patatas y cebolla, mezclar con huevo...",
    dificultad: "Fácil",
    autor: "Chef Jao",
    ingredientes: ["Patatas", "Huevos", "Cebolla", "Aceite"]
  }) {
    id
    titulo
    fechaCreacion
  }
}
```

### Mutation - Eliminar una receta

``` graphql
mutation {
  eliminarRecetaPorId(id: "1") {
    id
    titulo
    fechaEliminacion
  }
}
```

## 🔧 Configuración Adicional

### Maven

El proyecto utiliza las siguientes dependencias principales:

- `spring-boot-starter-graphql`
- `spring-boot-starter-web`
- `lombok`
- `spring-boot-starter-test`
- `spring-graphql-test`

### Lombok

Se configura el procesador de anotaciones de Lombok para generar automáticamente getters, setters y constructores.

## 📁 Estructura de Archivos

``` 
spring-graphql/
├── src/
│   ├── main/
│   │   ├── java/com/jao/springgraphql/
│   │   └── resources/
│   │       ├── graphql/schema.graphqls
│   │       └── application.yml
│   └── test/
├── pom.xml
├── README.md
└── otros archivos de configuración
```

## 🎯 Funcionalidades Implementadas

- ✅ **API GraphQL** completa para gestión de recetas
- ✅ **Operaciones CRUD** (Create, Read, Update, Delete)
- ✅ **Paginación** en consultas
- ✅ **Mapeo de datos** entre DTOs y entidades
- ✅ **Manejo de excepciones** básico
- ✅ **Tests unitarios** configurados
- ✅ **Configuración Maven** optimizada

Esta documentación proporciona una visión completa del proyecto y debe servir como guía tanto para el desarrollo como
para el mantenimiento de la aplicación.
