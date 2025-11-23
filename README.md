# RBBackendComite - Sistema de Gestion de Eventos Comunitarios

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[English Version](README.en.md)

## Descripcion General

**RBBackendComite** es una API REST robusta desarrollada con Spring Boot que permite a grupos altruistas y comites comunitarios organizar, gestionar y coordinar eventos de impacto social como reforestaciones, campañas de limpieza, y otras actividades comunitarias.

El sistema facilita la organizacion de eventos, gestion de participantes, administracion de grupos comunitarios y seguimiento de actividades, todo a traves de una arquitectura moderna y segura con autenticacion JWT.

## Caracteristicas Principales

### Gestion de Eventos
- ✅ Creacion y administracion completa de eventos (CRUD)
- ✅ Clasificacion por tipos (Reforestacion, Limpieza, Campañas, etc.)
- ✅ Control de estados: Proximamente, En Ejecucion, Finalizado
- ✅ Asignacion automatica de eventos a grupos especificos
- ✅ Validacion de fechas (eventos solo pueden ser hoy o futuro)
- ✅ Filtrado avanzado por estado, grupo o tipo de evento

### Gestion de Participantes
- ✅ Registro y confirmacion de asistencia
- ✅ Asignacion multiple de usuarios a eventos
- ✅ Seguimiento de confirmaciones de participacion
- ✅ Cancelacion de asistencias
- ✅ Consulta de todos los eventos por usuario
- ✅ Listado completo de participantes por evento

### Gestion de Usuarios
- ✅ Autenticacion segura con JWT (JSON Web Tokens)
- ✅ Roles diferenciados: ADMIN, GROUP_ADMIN, MEMBER
- ✅ Gestion de perfiles (nombre completo, email, telefono)
- ✅ Usuarios vinculados a grupos comunitarios
- ✅ Encriptacion de contraseñas con BCrypt
- ✅ Validacion de unicidad (username y email)

### Gestion de Grupos Comunitarios
- ✅ Creacion y administracion de grupos/comites
- ✅ Informacion geografica (municipio, colonia)
- ✅ Seguimiento de miembros por grupo
- ✅ Asociacion de eventos con grupos especificos
- ✅ Multiples grupos independientes en un mismo sistema

### Seguridad y Documentacion
- ✅ Autenticacion JWT con expiracion configurable (10 horas)
- ✅ Sesiones stateless (sin estado en el servidor)
- ✅ Soporte SSL/TLS con keystore PKCS12
- ✅ Configuracion CORS para integracion con frontend
- ✅ Documentacion interactiva con Swagger/OpenAPI
- ✅ Endpoints publicos documentados

## Tecnologias Utilizadas

### Backend Framework
- **Spring Boot 3.5.3** - Framework principal
- **Java 17** - Lenguaje de programacion
- **Maven** - Gestion de dependencias y construccion

### Base de Datos
- **MySQL 8.0** - Sistema de gestion de base de datos
- **Spring Data JPA** - ORM y operaciones de base de datos
- **Hibernate** - Implementacion de JPA

### Seguridad
- **Spring Security** - Framework de seguridad
- **JWT (JJWT 0.9.1)** - Generacion y validacion de tokens
- **BCrypt** - Encriptacion de contraseñas
- **SSL/TLS** - Comunicacion segura HTTPS

### Validacion y Utilidades
- **Jakarta Validation API 3.0.2** - Validacion de datos
- **Hibernate Validator** - Implementacion de Bean Validation
- **Lombok 1.18.34** - Reduccion de codigo boilerplate

### Documentacion
- **SpringDoc OpenAPI 2.8.9** - Generacion de documentacion
- **Swagger UI** - Interfaz interactiva de API

## Arquitectura del Proyecto

```
src/main/java/mx/edu/utez/rbbackendcomite/
├── config/                  # Configuraciones generales
│   ├── ApiResponseDto       # Respuestas estandarizadas
│   ├── InitialDataConfig    # Datos iniciales (seeding)
│   └── MainSecurity         # Configuracion de seguridad
├── controller/              # Controladores REST
│   ├── auth/               # Autenticacion
│   ├── event/              # Eventos
│   ├── eventType/          # Tipos de evento
│   ├── group/              # Grupos
│   ├── role/               # Roles
│   └── user/               # Usuarios
├── models/                  # Entidades y DTOs
│   ├── event/              # Modelo de eventos
│   ├── eventType/          # Modelo de tipos de evento
│   ├── group/              # Modelo de grupos
│   ├── role/               # Modelo de roles
│   ├── user/               # Modelo de usuarios
│   └── userHasEvents/      # Relacion usuario-evento
├── security/                # Seguridad y JWT
│   ├── filters/            # Filtros de autenticacion
│   ├── jwt/                # Utilidades JWT
│   └── SwaggerSecurity     # Configuracion Swagger
├── services/                # Logica de negocio
│   ├── auth/               # Servicio de autenticacion
│   ├── event/              # Servicio de eventos
│   ├── eventType/          # Servicio de tipos
│   ├── group/              # Servicio de grupos
│   ├── role/               # Servicio de roles
│   └── user/               # Servicio de usuarios
└── utils/                   # Utilidades generales
    ├── DBConnection
    └── PasswordEncoderService
```

## Modelo de Datos

### Entidades Principales

#### UserEntity (usuarios)
- Informacion personal: username, password, fullName, phone, email
- Relaciones: rol, grupo, eventos participados

#### EventEntity (eventos)
- Datos del evento: title, date, status
- Relaciones: tipo de evento, grupo organizador, participantes

#### GroupEntity (grupos)
- Informacion del grupo: name, municipality, neighborhood
- Relaciones: miembros, eventos organizados

#### EventParticipantEntity (participantes)
- Tabla intermedia para relacion usuario-evento
- Campo de confirmacion de asistencia

#### RoleEntity (roles)
- Tres roles predefinidos: ADMIN, GROUP_ADMIN, MEMBER

#### EventTypeEntity (tipos de evento)
- Ejemplos: Limpieza, Reforestacion, Campaña Social

### Estados de Eventos
- **PROXIMAMENTE** - Evento programado
- **EN_EJECUCION** - Evento en curso
- **FINALIZADO** - Evento completado

## API Endpoints

### Autenticacion
```
POST /api/auth - Login (devuelve token JWT)
```

### Usuarios
```
GET    /api/users                        - Listar todos los usuarios
GET    /api/users/{id}                   - Obtener usuario por ID
POST   /api/users                        - Crear nuevo usuario
PUT    /api/users/{id}                   - Actualizar usuario
DELETE /api/users/{id}                   - Eliminar usuario
GET    /api/users/usersByRole/{roleId}   - Usuarios por rol
GET    /api/users/usersByEvent/{eventId} - Usuarios por evento
GET    /api/users/usersByGroup/{groupId} - Usuarios por grupo
```

### Eventos
```
GET    /api/event                                              - Listar todos los eventos
GET    /api/event/{id}                                         - Obtener evento por ID
POST   /api/event                                              - Crear nuevo evento
PUT    /api/event/{id}                                         - Actualizar evento
DELETE /api/event/{id}                                         - Eliminar evento
GET    /api/event/status/{status}                              - Eventos por estado
GET    /api/event/group/{groupId}                              - Eventos por grupo
GET    /api/event/type/{typeId}                                - Eventos por tipo
GET    /api/event/user/{userId}                                - Eventos de un usuario
POST   /api/event/setUsersToEvent/{eventId}                    - Asignar usuarios a evento
PUT    /api/event/attendance/{eventId}/participants/{userId}   - Confirmar/cancelar asistencia
PUT    /api/event/{id}/status                                  - Actualizar estado del evento
```

### Grupos
```
GET    /api/groups     - Listar todos los grupos
GET    /api/groups/{id} - Obtener grupo por ID
POST   /api/groups     - Crear nuevo grupo
PUT    /api/groups/{id} - Actualizar grupo
DELETE /api/groups/{id} - Eliminar grupo
```

### Roles
```
GET    /api/roles     - Listar todos los roles
GET    /api/roles/{id} - Obtener rol por ID
POST   /api/roles     - Crear nuevo rol
PUT    /api/roles/{id} - Actualizar rol
DELETE /api/roles/{id} - Eliminar rol
```

### Tipos de Evento
```
GET    /api/eventType     - Listar todos los tipos
GET    /api/eventType/{id} - Obtener tipo por ID
POST   /api/eventType     - Crear nuevo tipo
PUT    /api/eventType/{id} - Actualizar tipo
DELETE /api/eventType/{id} - Eliminar tipo
```

## Instalacion y Configuracion

### Requisitos Previos
- Java 17 o superior
- MySQL 8.0 o superior
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Pasos de Instalacion

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/RBBackendComite.git
cd RBBackendComite
```

2. **Configurar la base de datos**
```sql
CREATE DATABASE comite;
```

3. **Configurar application.properties**

Editar `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/comite
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

4. **Compilar el proyecto**
```bash
mvn clean install
```

5. **Ejecutar la aplicacion**
```bash
mvn spring-boot:run
```

La aplicacion estara disponible en `https://localhost:8443`

### Usuarios por Defecto

El sistema crea automaticamente usuarios de prueba:

| Username | Password | Rol |
|----------|----------|-----|
| admin | admin123 | ADMIN |
| usuario1 | UserPass123! | GROUP_ADMIN |
| gerente1 | ManagerPass456! | MEMBER |
| invitado | GuestPass789! | GROUP_ADMIN |

## Documentacion de la API

Una vez iniciada la aplicacion, acceder a:

- **Swagger UI**: `https://localhost:8443/swagger-ui.html`
- **OpenAPI JSON**: `https://localhost:8443/v3/api-docs`

La documentacion interactiva permite probar todos los endpoints directamente desde el navegador.

## Configuracion de Seguridad

### Autenticacion JWT

1. **Login**: Enviar credenciales a `/api/auth`
```json
{
  "username": "admin",
  "password": "admin123"
}
```

2. **Respuesta**: Recibir token JWT
```json
{
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIs...",
    "user": {
      "id": 1,
      "username": "admin",
      "role": "ADMIN"
    }
  }
}
```

3. **Usar el token**: Incluir en header de peticiones protegidas
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
```

### Endpoints Publicos
- `/api/auth` - Autenticacion
- `/swagger-ui/**` - Documentacion
- `/v3/api-docs/**` - Especificacion OpenAPI

## Validaciones Implementadas

- **Username**: 4-20 caracteres
- **Password**: Minimo 8 caracteres
- **Email**: Formato valido de email
- **Telefono**: Exactamente 10 digitos
- **Fecha de evento**: Solo fechas actuales o futuras
- **Unicidad**: Username y email unicos en el sistema

## Estructura de Respuestas API

Todas las respuestas siguen un formato estandarizado:

```json
{
  "data": { },
  "error": false,
  "message": "Operacion exitosa",
  "status": 200
}
```

## Casos de Uso Principales

### 1. Organizacion de Evento de Reforestacion
1. Admin crea un tipo de evento "Reforestacion"
2. Group Admin crea el evento con fecha, titulo y grupo
3. Usuarios se registran como participantes
4. Participantes confirman su asistencia
5. Admin actualiza el estado del evento (Proximamente → En Ejecucion → Finalizado)

### 2. Gestion de Grupo Comunitario
1. Admin crea un nuevo grupo con ubicacion
2. Usuarios se registran y se asignan al grupo
3. Group Admin organiza eventos para su grupo
4. Miembros del grupo participan en eventos

### 3. Seguimiento de Participacion
1. Usuario consulta eventos disponibles
2. Usuario se registra en eventos de interes
3. Usuario confirma asistencia antes del evento
4. Sistema registra participacion efectiva

## Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## Licencia

Este proyecto esta bajo la Licencia MIT. Ver el archivo `LICENSE` para mas detalles.

## Contacto y Soporte

Para preguntas, sugerencias o reportar problemas:

- Abrir un issue en GitHub
- Enviar pull request con mejoras
- Contactar al equipo de desarrollo

## Roadmap Futuro

- [ ] Notificaciones por email
- [ ] Sistema de recordatorios de eventos
- [ ] Reportes y estadisticas
- [ ] Integracion con calendario
- [ ] App movil (iOS/Android)
- [ ] Sistema de puntos/gamificacion
- [ ] Geolocalizacion de eventos
- [ ] Subida de fotos de eventos

---

Desarrollado con ❤️ para comunidades que hacen la diferencia
