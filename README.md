# 📚 Book Manager API

API RESTful construida con **Spring Boot 3** para la gestión de libros. Permite crear, consultar, actualizar, eliminar libros y aplicar filtros por título, autor o estado. Incluye paginación y documentación con Swagger.

## 🚀 Tecnologías

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- MySQL
- Swagger/OpenAPI (SpringDoc)
- Maven

## 🧱 Estructura del proyecto

```bash
src/main/java/com/manuel/curso/springboot/backend/bookmanagerspring/
├── configuration     # Configuraciones
├── controller        # Controladores REST
├── dto               # Clases para respuestas personalizadas
├── exception         # Clase para manejar excepciones
├── model             # Entidades JPA
│   └── enums         # Enumeraciones (Status)
├── repository        # Repositorios JPA
├── service           # Servicios e interfaces
└── BookManagerSpringApplication.java
```

## 📦 Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/manutatum/BookManagerSpring.git
cd BookManagerSpring
```
2. Configura tu base de datos en application.properties:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookdb
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```
3. Compila y ejecuta

## 📖 Documentación Swagger
Una vez en ejecución, accede a la documentación de la API:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## ✅ Endpoints principales

### 📄 GET /api/books

Obtiene una lista paginada de libros. Soporta:
1. Filtros por title, author, status
2. Paginación: page, size
3. Ordenamiento: sort

### 📄 GET /api/books/{id}

Obtiene un libro por ID.

### ➕ POST /api/books

Crea un nuevo libro. Requiere:
```json
{
  "title": "Nombre del libro",
  "author": "Autor",
  "status": "AVAILABLE",
  "publishDate": "2024-06-22"
}
```

### 📝 PUT /api/books/{id}

Actualiza un libro existente.

### ❌ DELETE /api/books/{id}

Elimina un libro por ID.

### 🧹 DELETE /api/books

Elimina todos los libros.

## 🗂 Status (Enum)

```java
public enum Status {
    AVAILABLE,
    BORROWED,
    LOST
}
```

## 📘 DTO personalizado

Se utiliza una clase PageResponseDto<T> para personalizar la respuesta de paginación y mostrar solo los datos necesarios al cliente.

## 🔒 Validación

Se utiliza @Valid en los controladores para asegurar integridad de datos.

## 👤 Autor
Manuel Jiménez
[Visita mi portfolio](https://manueljimenez.es)
