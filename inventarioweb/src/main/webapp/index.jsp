<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:if test="${param.modulo == 'categorias'}">
    <c:redirect url="/categoria">
        <c:param name="accion" value="listar" />
    </c:redirect>
</c:if>

<c:if test="${param.modulo == 'productos'}">
    <c:redirect url="/producto">
        <c:param name="accion" value="listar" />
    </c:redirect>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema Inventario</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<div class="home-container">
    <div class="home-box">
        <h1 class="home-title">Sistema de Inventario</h1>

        <div class="botones-home">
            <a href="categoria?accion=listar" class="btn-custom btn-primary-custom">Categorias</a>
            <a href="producto?accion=listar" class="btn-custom btn-home">Productos</a>
        </div>
    </div>
</div>

</body>
</html>
