<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<div class="container-custom">
    <h1 class="titulo">Listado de Productos</h1>

    <c:if test="${not empty mensaje}">
        <div class="alerta exito"><c:out value="${mensaje}" /></div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alerta error"><c:out value="${error}" /></div>
    </c:if>

    <div class="top-bar">
        <c:url var="urlInicio" value="/index.jsp" />
        <c:url var="urlNuevo" value="/producto">
            <c:param name="accion" value="nuevo" />
        </c:url>

        <a href="${urlInicio}" class="btn-custom btn-home">Inicio</a>
        <a href="${urlNuevo}" class="btn-custom btn-primary-custom">Nuevo Producto</a>
    </div>

    <form action="${pageContext.request.contextPath}/producto" method="get" class="filtro-form">
        <input type="hidden" name="accion" value="listar">
        <label for="idCategoria">Filtrar por categoria</label>
        <select id="idCategoria" name="idCategoria" onchange="this.form.submit()">
            <option value="0">Todas</option>
            <c:forEach items="${categorias}" var="cat">
                <option value="${cat.idCategoria}" ${cat.idCategoria == idCategoriaSeleccionada ? 'selected' : ''}>
                    <c:out value="${cat.nombre}" />
                </option>
            </c:forEach>
        </select>
    </form>

    <table class="table-custom">
        <thead>
        <tr>
            <th>#</th>
            <th>Codigo</th>
            <th>Nombre</th>
            <th>Categoria</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Estado</th>
            <th>Fecha Creacion</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lista}" var="p" varStatus="s">
            <c:set var="stockBajo" value="${p.stock < 5}" />
            <tr>
                <td><c:out value="${s.count}" /></td>
                <td><c:out value="${p.codigo}" /></td>
                <td><c:out value="${p.nombre}" /></td>
                <td><c:out value="${p.nombreCategoria}" /></td>
                <td class="td-numero">
                    $<fmt:formatNumber value="${p.precio}" type="number" minFractionDigits="2" maxFractionDigits="2" />
                </td>
                <td class="td-numero">
                    <c:choose>
                        <c:when test="${stockBajo}">
                            <span class="stock-bajo"><c:out value="${p.stock}" /></span>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${p.stock}" />
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${p.estado == 1}">
                            <span class="badge-custom activo">Activo</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge-custom inactivo">Inactivo</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="td-fecha">
                    <fmt:formatDate value="${p.fechaCreacionDate}" pattern="dd/MM/yyyy HH:mm" />
                </td>
                <td>
                    <c:url var="urlEditar" value="/producto">
                        <c:param name="accion" value="editar" />
                        <c:param name="id" value="${p.idProducto}" />
                    </c:url>
                    <c:url var="urlEliminar" value="/producto">
                        <c:param name="accion" value="eliminar" />
                        <c:param name="id" value="${p.idProducto}" />
                    </c:url>

                    <a href="${urlEditar}" class="btn-custom btn-warning-custom">Editar</a>
                    <a href="${urlEliminar}" class="btn-custom btn-danger-custom"
                       onclick="return confirm('Eliminar producto?')">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
