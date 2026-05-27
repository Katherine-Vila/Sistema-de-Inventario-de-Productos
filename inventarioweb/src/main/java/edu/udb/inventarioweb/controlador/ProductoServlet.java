package edu.udb.inventarioweb.controlador;

import edu.udb.inventarioweb.dao.CategoriaDAO;
import edu.udb.inventarioweb.dao.ProductoDAO;
import edu.udb.inventarioweb.modelo.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/producto")
public class ProductoServlet extends HttpServlet {

    private final ProductoDAO productoDAO = new ProductoDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) accion = "listar";

        switch (accion) {

            case "nuevo":

                request.setAttribute("categorias", categoriaDAO.listarActivas());

                request.getRequestDispatcher("vistas/producto/formulario.jsp")
                        .forward(request, response);
                break;

            case "editar":

                int id = Integer.parseInt(request.getParameter("id"));

                request.setAttribute("producto", productoDAO.obtenerPorId(id));
                request.setAttribute("categorias", categoriaDAO.listarActivas());

                request.getRequestDispatcher("vistas/producto/formulario.jsp")
                        .forward(request, response);
                break;

            case "eliminar":

                int idEliminar = Integer.parseInt(request.getParameter("id"));
                if (productoDAO.eliminar(idEliminar)) {
                    request.getSession().setAttribute("mensaje", "Producto eliminado correctamente.");
                } else {
                    request.getSession().setAttribute("error", "No se pudo eliminar el producto.");
                }

                response.sendRedirect("producto?accion=listar");
                break;

            default:

                int idCategoria = parseEntero(request.getParameter("idCategoria"), 0);
                request.setAttribute("idCategoriaSeleccionada", idCategoria);
                request.setAttribute("categorias", categoriaDAO.listarActivas());
                request.setAttribute("lista", idCategoria > 0
                        ? productoDAO.listarPorCategoria(idCategoria)
                        : productoDAO.listarTodos());
                moverMensajesSesion(request);

                request.getRequestDispatcher("vistas/producto/listado.jsp")
                        .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        Producto p = new Producto();

        p.setIdProducto(parseEntero(request.getParameter("id"), 0));
        p.setCodigo(limpiar(request.getParameter("codigo")).toUpperCase());
        p.setNombre(limpiar(request.getParameter("nombre")));
        p.setDescripcion(limpiar(request.getParameter("descripcion")));
        p.setIdCategoria(parseEntero(request.getParameter("idCategoria"), 0));
        p.setPrecio(parseDouble(request.getParameter("precio"), -1));
        p.setStock(parseEntero(request.getParameter("stock"), -1));
        p.setEstado(parseEntero(request.getParameter("estado"), 1));

        Map<String, String> errores = validar(p);

        if (!errores.isEmpty()) {
            request.setAttribute("errores", errores);
            request.setAttribute("producto", p);
            request.setAttribute("categorias", categoriaDAO.listarActivas());
            request.getRequestDispatcher("vistas/producto/formulario.jsp")
                    .forward(request, response);
            return;
        }

        if ("guardar".equals(accion)) {

            if (productoDAO.insertar(p)) {
                request.getSession().setAttribute("mensaje", "Producto guardado correctamente.");
            } else {
                request.getSession().setAttribute("error", "No se pudo guardar el producto.");
            }

        } else {

            if (productoDAO.actualizar(p)) {
                request.getSession().setAttribute("mensaje", "Producto actualizado correctamente.");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar el producto.");
            }
        }

        response.sendRedirect("producto?accion=listar");
    }

    private Map<String, String> validar(Producto p) {
        Map<String, String> errores = new HashMap<>();

        if (p.getCodigo() == null || p.getCodigo().isBlank()) {
            errores.put("codigo", "El codigo es obligatorio.");
        } else if (!p.getCodigo().matches("[A-Z0-9-]{3,20}")) {
            errores.put("codigo", "El codigo debe tener de 3 a 20 caracteres alfanumericos o guiones.");
        } else if (productoDAO.existeCodigo(p.getCodigo(), p.getIdProducto())) {
            errores.put("codigo", "Ya existe un producto con ese codigo.");
        }

        if (p.getNombre() == null || p.getNombre().isBlank()) {
            errores.put("nombre", "El nombre es obligatorio.");
        }

        if (p.getIdCategoria() <= 0) {
            errores.put("idCategoria", "Debe seleccionar una categoria.");
        }

        if (p.getPrecio() <= 0) {
            errores.put("precio", "El precio debe ser mayor a 0.");
        }

        if (p.getStock() < 0) {
            errores.put("stock", "El stock debe ser mayor o igual a 0.");
        }

        return errores;
    }

    private String limpiar(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private int parseEntero(String valor, int defecto) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception e) {
            return defecto;
        }
    }

    private double parseDouble(String valor, double defecto) {
        try {
            return Double.parseDouble(valor);
        } catch (Exception e) {
            return defecto;
        }
    }

    private void moverMensajesSesion(HttpServletRequest request) {
        Object mensaje = request.getSession().getAttribute("mensaje");
        Object error = request.getSession().getAttribute("error");

        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
            request.getSession().removeAttribute("mensaje");
        }

        if (error != null) {
            request.setAttribute("error", error);
            request.getSession().removeAttribute("error");
        }
    }
}
