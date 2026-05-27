Sistema de Gestion de Inventario Web
====================================

Proyecto web Java basado en el patron MVC para administrar categorias y
productos de inventario. La aplicacion implementa operaciones CRUD usando
Servlets como controladores, JSP con JSTL para la capa de presentacion, DAO
para el acceso a datos y JDBC para la conexion con MySQL/MariaDB.


Descripcion
-----------

El sistema permite gestionar un catalogo de productos organizados por
categorias. Cada producto pertenece a una categoria y puede registrarse con
codigo, nombre, descripcion, precio, stock, estado y fecha de creacion.

El proyecto fue desarrollado como aplicacion web Java EE/Jakarta EE, siguiendo
una estructura Maven estandar y separando responsabilidades en capas:

- Modelo: clases POJO de Categoria y Producto.
- Vista: paginas JSP con JSTL Core y Format.
- Controlador: Servlets para Categoria y Producto.
- Acceso a datos: clases DAO con JDBC y PreparedStatement.


Funcionalidades Principales
---------------------------

- CRUD completo de categorias.
- CRUD completo de productos.
- Relacion uno a muchos entre Categoria y Producto.
- Listado de categorias con estado activo/inactivo.
- Listado de productos con nombre de categoria mediante JOIN.
- Filtro de productos por categoria.
- Eliminacion logica mediante cambio de estado.
- Validaciones del lado cliente con JavaScript.
- Validaciones del lado servidor en los Servlets.
- Validacion de unicidad para nombre de categoria y codigo de producto.
- Mensajes de exito y error.
- Interfaz responsiva con CSS personalizado y Bootstrap en formularios.
- Uso de JSTL en las vistas en lugar de scriptlets Java.


Tecnologias Utilizadas
----------------------

- Java 11
- Jakarta Servlet API 6.0
- JSTL Jakarta
- JSP
- JDBC
- MySQL 8.x o MariaDB 10.x
- Apache Tomcat 10.x
- Maven 3.x
- HTML5, CSS3 y JavaScript
- Bootstrap 5 por CDN


Requisitos Previos
------------------

Antes de ejecutar el proyecto, instalar o tener disponible:

- JDK 11 o superior
- Apache Tomcat 10.x
- MySQL 8.x o MariaDB 10.x
- Maven 3.x, o usar el Maven Wrapper incluido
- Un IDE compatible con proyectos web Java, como IntelliJ IDEA, Eclipse EE o NetBeans


Estructura del Proyecto
-----------------------

inventarioweb/
|
+-- pom.xml
+-- src/
|   +-- main/
|       +-- java/
|       |   +-- edu/udb/inventarioweb/
|       |       +-- modelo/
|       |       |   +-- Categoria.java
|       |       |   +-- Producto.java
|       |       +-- dao/
|       |       |   +-- ConexionDB.java
|       |       |   +-- CategoriaDAO.java
|       |       |   +-- ProductoDAO.java
|       |       +-- controlador/
|       |           +-- CategoriaServlet.java
|       |           +-- ProductoServlet.java
|       +-- resources/
|       |   +-- db_inventario.sql
|       +-- webapp/
|           +-- index.jsp
|           +-- WEB-INF/
|           |   +-- web.xml
|           +-- vistas/
|           |   +-- categoria/
|           |   |   +-- listado.jsp
|           |   |   +-- formulario.jsp
|           |   +-- producto/
|           |       +-- listado.jsp
|           |       +-- formulario.jsp
|           +-- css/
|           |   +-- estilos.css
|           +-- js/
|               +-- validaciones.js


Configuracion de la Base de Datos
---------------------------------

1. Iniciar MySQL o MariaDB.

2. Ejecutar el script SQL incluido en:

   src/main/resources/db_inventario.sql

   Este script crea la base de datos db_inventario, las tablas CATEGORIA y
   PRODUCTO, la llave foranea entre ambas tablas y datos iniciales de prueba.

3. Revisar las credenciales de conexion en:

   src/main/java/edu/udb/inventarioweb/dao/ConexionDB.java

   Configuracion actual:

   URL:      jdbc:mysql://localhost:3306/db_inventario?useSSL=false&serverTimezone=UTC
   Usuario:  root
   Password: vacio

   Si tu MySQL usa otra clave, actualizar la constante PASSWORD.


Ejecucion del Proyecto
----------------------

Opcion 1: usando Maven Wrapper en Windows

   mvnw.cmd clean package

Opcion 2: usando Maven instalado

   mvn clean package

Luego desplegar el archivo WAR generado o configurar el proyecto en Tomcat 10.x
desde el IDE.

En IntelliJ IDEA, una configuracion comun es:

   Artifact: inventarioweb:war exploded
   Server:   Apache Tomcat 10.x
   URL:      http://localhost:8080/inventarioweb/

Si tu Tomcat usa otro puerto, ajustar la URL. Por ejemplo:

   http://localhost:8081/inventarioweb/


Rutas Principales
-----------------

- Inicio:
  /index.jsp

- Listado de categorias:
  /categoria?accion=listar

- Nueva categoria:
  /categoria?accion=nuevo

- Editar categoria:
  /categoria?accion=editar&id=1

- Listado de productos:
  /producto?accion=listar

- Nuevo producto:
  /producto?accion=nuevo

- Editar producto:
  /producto?accion=editar&id=1


Uso de JSTL
-----------

Las vistas JSP utilizan JSTL para controlar la presentacion sin usar scriptlets
Java. Entre las etiquetas utilizadas estan:

- c:forEach para recorrer listas.
- c:if para mostrar mensajes y campos condicionales.
- c:choose, c:when y c:otherwise para estados y condiciones.
- c:set para variables auxiliares.
- c:out para imprimir datos escapando caracteres HTML.
- c:url y c:param para construir enlaces.
- c:redirect para redireccionar desde index.jsp.
- fmt:formatDate para fechas.
- fmt:formatNumber para precios.


Validaciones
------------

Validaciones en cliente:

- Campos obligatorios.
- Precio mayor a 0.
- Stock entero mayor o igual a 0.
- Codigo de producto entre 3 y 20 caracteres.
- Nombre de categoria obligatorio.

Validaciones en servidor:

- Revalidacion de campos en CategoriaServlet y ProductoServlet.
- Validacion de unicidad de nombre de categoria.
- Validacion de unicidad de codigo de producto.
- Retorno al formulario con mensajes de error cuando corresponde.


Notas de Implementacion
-----------------------

- El proyecto usa Jakarta, por lo que esta orientado a Tomcat 10.x.
- Las eliminaciones se realizan de forma logica, cambiando el estado a 0.
- Una categoria no puede eliminarse si tiene productos activos asociados.
- La clase ConexionDB lanza SQLException con mensajes claros si falla el driver
  o la conexion a la base de datos.
- Los DAO usan try-with-resources para cerrar Connection, PreparedStatement y
  ResultSet cuando aplica.


Compilacion Verificada
----------------------

El proyecto fue verificado localmente con:

   mvn test

Resultado:

   BUILD SUCCESS


Autores
-------

- Katherine Alexandra Pinto Vila
- Grecia Alejandra Soto Parada
- Omar Gabriel Martinez Murcia


