# Sistema de Gestion de Inventario Web

Proyecto web Java basado en el patron MVC para administrar categorias y productos de inventario. La aplicacion implementa operaciones CRUD usando Servlets, JSP con JSTL, DAO, JDBC y MySQL/MariaDB.

## Funcionalidades

- CRUD completo de categorias.
- CRUD completo de productos.
- Relacion uno a muchos entre `CATEGORIA` y `PRODUCTO`.
- Listado de productos con `JOIN` hacia categoria.
- Filtro de productos por categoria.
- Eliminacion logica mediante cambio de estado.
- Validaciones en cliente con JavaScript.
- Validaciones en servidor con Servlets.
- Mensajes de exito y error.
- Interfaz responsiva con CSS personalizado y Bootstrap 5.
- Vistas JSP con JSTL, sin scriptlets Java de logica.

## Tecnologias

- Java 11
- Jakarta Servlet API 6.0
- JSP
- JSTL Jakarta
- JDBC
- MySQL 8.x o MariaDB 10.x
- Apache Tomcat 10.x
- Maven
- HTML5, CSS3 y JavaScript

## Estructura

```text
inventarioweb/
|-- pom.xml
|-- src/main/java/edu/udb/inventarioweb/
|   |-- modelo/
|   |   |-- Categoria.java
|   |   `-- Producto.java
|   |-- dao/
|   |   |-- ConexionDB.java
|   |   |-- CategoriaDAO.java
|   |   `-- ProductoDAO.java
|   `-- controlador/
|       |-- CategoriaServlet.java
|       `-- ProductoServlet.java
|-- src/main/resources/
|   `-- db_inventario.sql
`-- src/main/webapp/
    |-- index.jsp
    |-- WEB-INF/web.xml
    |-- vistas/
    |   |-- categoria/
    |   `-- producto/
    |-- css/estilos.css
    `-- js/validaciones.js
```

## Base de datos

Ejecutar el script:

```text
src/main/resources/db_inventario.sql
```

La conexion se configura en:

```text
src/main/java/edu/udb/inventarioweb/dao/ConexionDB.java
```

Configuracion actual:

```text
Base de datos: db_inventario
Usuario: root
Password: vacio
```

Si tu MySQL tiene clave, actualizar la constante `PASSWORD`.

## Ejecucion

Compilar con Maven:

```bash
mvn clean package
```

O con Maven Wrapper en Windows:

```bash
mvnw.cmd clean package
```

Despues desplegar en Apache Tomcat 10.x. En IntelliJ IDEA se puede usar el artifact:

```text
inventarioweb:war exploded
```

URL comun:

```text
http://localhost:8080/inventarioweb/
```

## Rutas principales

- `/index.jsp`
- `/categoria?accion=listar`
- `/categoria?accion=nuevo`
- `/categoria?accion=editar&id=1`
- `/producto?accion=listar`
- `/producto?accion=nuevo`
- `/producto?accion=editar&id=1`

## Validaciones

Cliente:

- Codigo requerido, de 3 a 20 caracteres.
- Nombre requerido.
- Precio mayor a 0.
- Stock entero mayor o igual a 0.
- Categoria obligatoria.

Servidor:

- Revalidacion de campos.
- Unicidad de nombre de categoria.
- Unicidad de codigo de producto.
- Mensajes de error junto a los campos.

## Notas

- El proyecto usa Jakarta, por eso esta orientado a Tomcat 10.x.
- Los DAO usan `try-with-resources`.
- `ConexionDB` lanza `SQLException` con mensajes claros si falla la conexion.
- La carpeta `target/` es generada por Maven y no debe subirse a GitHub.

## Autores

- Katherine Alexandra Pinto Vila
- Grecia Alejandra Soto Parada
- Omar Gabriel Martinez Murcia

