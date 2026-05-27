<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<c:set var="editando" value="${producto.idProducto > 0}" />

<div class="container mt-5">
    <div class="card-form">
        <div class="text-center mb-4">
            <h2 class="titulo-principal">
                <c:choose>
                    <c:when test="${editando}">Editar Producto</c:when>
                    <c:otherwise>Nuevo Producto</c:otherwise>
                </c:choose>
            </h2>
        </div>

        <form action="${pageContext.request.contextPath}/producto" method="post" onsubmit="return validarProducto()">
            <input type="hidden" name="accion" value="${editando ? 'actualizar' : 'guardar'}">
            <input type="hidden" name="id" value="${producto.idProducto}">

            <div class="mb-3">
                <label class="form-label">Codigo</label>
                <input id="codigo" type="text" name="codigo" class="form-control"
                       value="<c:out value='${producto.codigo}' />" required minlength="3" maxlength="20">
                <c:if test="${not empty errores.codigo}">
                    <div class="texto-error"><c:out value="${errores.codigo}" /></div>
                </c:if>
            </div>

            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input id="nombre" type="text" name="nombre" class="form-control"
                       value="<c:out value='${producto.nombre}' />" required maxlength="150">
                <c:if test="${not empty errores.nombre}">
                    <div class="texto-error"><c:out value="${errores.nombre}" /></div>
                </c:if>
            </div>

            <div class="mb-3">
                <label class="form-label">Descripcion</label>
                <textarea name="descripcion" class="form-control" rows="3" maxlength="500"><c:out value="${producto.descripcion}" /></textarea>
            </div>

            <div class="mb-3">
                <label class="form-label">Categoria</label>
                <select name="idCategoria" class="form-select" required>
                    <option value="">Seleccione...</option>
                    <c:forEach items="${categorias}" var="c">
                        <option value="${c.idCategoria}" ${c.idCategoria == producto.idCategoria ? 'selected' : ''}>
                            <c:out value="${c.nombre}" />
                        </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty errores.idCategoria}">
                    <div class="texto-error"><c:out value="${errores.idCategoria}" /></div>
                </c:if>
            </div>

            <div class="mb-3">
                <label class="form-label">Precio</label>
                <input id="precio" type="number" step="0.01" min="0.01" name="precio" class="form-control"
                       value="${producto.precio > 0 ? producto.precio : ''}" required>
                <c:if test="${not empty errores.precio}">
                    <div class="texto-error"><c:out value="${errores.precio}" /></div>
                </c:if>
            </div>

            <div class="mb-3">
                <label class="form-label">Stock</label>
                <input id="stock" type="number" min="0" name="stock" class="form-control"
                       value="${producto.stock >= 0 ? producto.stock : ''}" required>
                <c:if test="${not empty errores.stock}">
                    <div class="texto-error"><c:out value="${errores.stock}" /></div>
                </c:if>
            </div>

            <c:if test="${editando}">
                <div class="mb-4">
                    <label class="form-label">Estado</label>
                    <select name="estado" class="form-select" required>
                        <option value="1" ${producto.estado == 1 ? 'selected' : ''}>Activo</option>
                        <option value="0" ${producto.estado == 0 ? 'selected' : ''}>Inactivo</option>
                    </select>
                </div>
            </c:if>

            <div class="text-center">
                <button type="submit" class="btn btn-guardar">Guardar</button>
                <a href="${pageContext.request.contextPath}/producto?accion=listar" class="btn btn-inicio">Cancelar</a>
            </div>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/validaciones.js"></script>
</body>
</html>
