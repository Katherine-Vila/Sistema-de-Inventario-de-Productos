function validarProducto() {
    const codigo = document.getElementById("codigo").value.trim();
    const nombre = document.getElementById("nombre").value.trim();
    const precio = Number(document.getElementById("precio").value);
    const stock = Number(document.getElementById("stock").value);
    const regexCodigo = /^[A-Za-z0-9-]{3,20}$/;

    if (codigo.length === 0 || !regexCodigo.test(codigo)) {
        alert("El codigo debe tener de 3 a 20 caracteres alfanumericos o guiones.");
        return false;
    }

    if (nombre.length === 0) {
        alert("El nombre es obligatorio.");
        return false;
    }

    if (Number.isNaN(precio) || precio <= 0) {
        alert("El precio debe ser un numero mayor a 0.");
        return false;
    }

    if (!Number.isInteger(stock) || stock < 0) {
        alert("El stock debe ser un entero mayor o igual a 0.");
        return false;
    }

    return true;
}

function validarCategoria() {
    const campoNombre = document.querySelector("input[name='nombre']");
    const nombre = campoNombre ? campoNombre.value.trim() : "";

    if (nombre.length === 0) {
        alert("El nombre de la categoria es obligatorio.");
        return false;
    }

    if (nombre.length > 80) {
        alert("El nombre de la categoria no debe superar 80 caracteres.");
        return false;
    }

    return true;
}
