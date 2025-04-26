# BeyondCity

# ForkMap

## 1. Introducción
Desarrollo de una aplicación estilo “The Fork” donde el usuario:
- Localiza restaurantes en un mapa.
- Filtra resultados según criterios (cocina, precio, valoración, distancia).
- Añade reseñas y guarda sus favoritos.

Partimos del esqueleto de código proporcionado en `P5.zip`.

---

## 2. Objetivos
1. Geolocalizar al usuario en el mapa.  
2. Mostrar restaurantes cercanos según filtros.  
3. Permitir crear, editar y eliminar reseñas.  
4. Gestionar lista de favoritos de cada usuario.  
5. Diseñar una UI moderna con el mapa como elemento central.

---

## 3. Alcance y Requisitos

### Funcionales
- Registro, login y perfil de usuarios.  
- Geolocalización y actualización de posición.  
- Búsqueda y filtrado en tiempo real.  
- CRUD de reseñas.  
- Gestión de favoritos.

### No funcionales
- Tiempo de respuesta < 200 ms.  
- Arquitectura escalable y modular.  
- Despliegue continuo en Render.com.

---

## 4. Tecnologías y Arquitectura

### Frontend
- **Framework**: React (+ Vite o Next.js)  
- **Mapa**: Leaflet o Mapbox GL JS  
- **Estilos**: Tailwind CSS

### Backend
- **Lenguaje**: Java 17+  
- **Framework**: Spring Boot  
- **BD**: H2 (desarrollo) / PostgreSQL (producción)  
- **ORM**: JPA / Hibernate  
- **API**: REST documentada con OpenAPI

### Infraestructura
- **Contenedores**: Docker  
- **CI/CD**: GitHub Actions + Render.com  
- **Repositorios**: GitHub (ramas `develop` y `main`)

---

## 5. Estructura del Repositorio
- /(raiz)
  |- backend/  #Spring Boot
  |- frontend/  #React
  |- Readme.md
  |- docker-compose.yml  #Orquestación local
  |- .github/workflows/  #CI/CD
  


---

## 6. Diseño UI/UX
- Mapa a pantalla completa con panel lateral colapsable.  
- Componentes reutilizables (tarjetas, modales).  
- Flujo:  
  1. Seleccionar posición.  
  2. Aplicar filtros.  
  3. Ver detalles, reseñas y favoritos.

---

## 7. Planificación de Sprints

| Sprint | Fechas                  | Objetivos principales                            |
|:------:|:-----------------------:|--------------------------------------------------|
| 1      | 27/04/2025 – 04/05/2025 | Setup, geolocalización y mapa interactivo básico |
| 2      | 05/05/2025 – 11/05/2025 | Filtros de búsqueda y listado de restaurantes    |
| 3      | 12/05/2025 – 18/05/2025 | Reseñas, favoritos, tests y despliegue           |

---

## 8. CI / CD

- **CI**: GitHub Actions ejecuta `mvn test` y `npm test` en cada PR.  
- **CD**: Merge a `main` dispara build y despliegue en Render.com.

---

## 9. Estrategia de Pruebas

- **Backend**: JUnit + Mockito, cobertura ≥ 80%.  
- **Frontend**: Jest + React Testing Library.  
- **E2E**: Cypress para flujos críticos.

---

## 10. Documentación

- **Wiki**: Casos de uso, modelo de datos, endpoints API, manual de usuario.  
- **Demos**: Capturas o vídeos de cada sprint.  
- **Acceso**: GitHub Pages o sección “Docs” del repositorio.

---
