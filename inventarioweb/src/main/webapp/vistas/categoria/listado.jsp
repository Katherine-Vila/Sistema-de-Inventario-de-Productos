<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categorias</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<div class="container-custom">
    <h1 class="titulo">Listado de Categorias</h1>

    <c:if test="${not empty mensaje}">
        <div class="alerta exito"><c:out value="${mensaje}" /></div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alerta error"><c:out value="${error}" /></div>
    </c:if>

    <div class="top-bar">
        <c:url var="urlInicio" value="/index.jsp" />
        <c:url var="urlNueva" value="/categoria">
            <c:param name="accion" value="nuevo" />
        </c:url>

        <a href="${urlInicio}" class="btn-custom btn-home">Inicio</a>
        <a href="${urlNueva}" class="btn-custom btn-primary-custom">Nueva Categoria</a>
    </div>

    <table class="table-custom">
        <thead>
        <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Descripcion</th>
            <th>Estado</th>
            <th>Fecha Creacion</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lista}" var="c" varStatus="s">
            <c:set var="estadoActivo" value="${c.estado == 1}" />
            <tr>
                <td><c:out value="${s.count}" /></td>
                <td><c:out value="${c.nombre}" /></td>
                <td><c:out value="${c.descripcion}" /></td>
                <td>
                    <c:choose>
                        <c:when test="${estadoActivo}">
                            <span class="badge-custom activo">Activo</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge-custom inactivo">Inactivo</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="td-fecha">
                    <fmt:formatDate value="${c.fechaCreacionDate}" pattern="dd/MM/yyyy HH:mm" />
                </td>
                <td>
                    <c:url var="urlEditar" value="/categoria">
                        <c:param name="accion" value="editar" />
                        <c:param name="id" value="${c.idCategoria}" />
                    </c:url>
                    <c:url var="urlEliminar" value="/categoria">
                        <c:param name="accion" value="eliminar" />
                        <c:param name="id" value="${c.idCategoria}" />
                    </c:url>

                    <a href="${urlEditar}" class="btn-custom btn-warning-custom">Editar</a>
                    <a href="${urlEliminar}" class="btn-custom btn-danger-custom"
                       onclick="return confirm('Eliminar categoria?')">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
