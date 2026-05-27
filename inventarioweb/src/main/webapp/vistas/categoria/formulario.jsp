<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario Categoria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<c:set var="editando" value="${categoria.idCategoria > 0}" />

<div class="container mt-5">
    <div class="card-form">
        <div class="text-center mb-4">
            <h2 class="titulo-principal">
                <c:choose>
                    <c:when test="${editando}">Editar Categoria</c:when>
                    <c:otherwise>Nueva Categoria</c:otherwise>
                </c:choose>
            </h2>
        </div>

        <form action="${pageContext.request.contextPath}/categoria" method="post" onsubmit="return validarCategoria()">
            <input type="hidden" name="accion" value="${editando ? 'actualizar' : 'guardar'}">
            <input type="hidden" name="id" value="${categoria.idCategoria}">

            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control"
                       value="<c:out value='${categoria.nombre}' />" required maxlength="80">
                <c:if test="${not empty errores.nombre}">
                    <div class="texto-error"><c:out value="${errores.nombre}" /></div>
                </c:if>
            </div>

            <div class="mb-3">
                <label class="form-label">Descripcion</label>
                <textarea name="descripcion" class="form-control" rows="4" maxlength="255"><c:out value="${categoria.descripcion}" /></textarea>
            </div>

            <c:if test="${editando}">
                <div class="mb-4">
                    <label class="form-label">Estado</label>
                    <select name="estado" class="form-select" required>
                        <option value="1" ${categoria.estado == 1 ? 'selected' : ''}>Activo</option>
                        <option value="0" ${categoria.estado == 0 ? 'selected' : ''}>Inactivo</option>
                    </select>
                </div>
            </c:if>

            <div class="text-center">
                <button type="submit" class="btn btn-guardar">Guardar</button>
                <a href="${pageContext.request.contextPath}/categoria?accion=listar" class="btn btn-inicio">Cancelar</a>
            </div>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/validaciones.js"></script>
</body>
</html>
