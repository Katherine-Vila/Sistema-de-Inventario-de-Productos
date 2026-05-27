package edu.udb.inventarioweb.controlador;

import edu.udb.inventarioweb.dao.CategoriaDAO;
import edu.udb.inventarioweb.modelo.Categoria;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {

    private final CategoriaDAO dao = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) accion = "listar";

        switch (accion) {

            case "nuevo":
                request.getRequestDispatcher("vistas/categoria/formulario.jsp")
                        .forward(request, response);
                break;

            case "editar":

                int id = Integer.parseInt(request.getParameter("id"));
                Categoria categoria = dao.obtenerPorId(id);

                request.setAttribute("categoria", categoria);

                request.getRequestDispatcher("vistas/categoria/formulario.jsp")
                        .forward(request, response);
                break;

            case "eliminar":

                int idEliminar = Integer.parseInt(request.getParameter("id"));
                if (dao.tieneProductosActivos(idEliminar)) {
                    request.getSession().setAttribute("error", "No se puede eliminar la categoria porque tiene productos activos.");
                } else if (dao.eliminar(idEliminar)) {
                    request.getSession().setAttribute("mensaje", "Categoria eliminada correctamente.");
                } else {
                    request.getSession().setAttribute("error", "No se pudo eliminar la categoria.");
                }

                response.sendRedirect("categoria?accion=listar");
                break;

            default:

                request.setAttribute("lista", dao.listarTodas());
                moverMensajesSesion(request);

                request.getRequestDispatcher("vistas/categoria/listado.jsp")
                        .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        String nombre = limpiar(request.getParameter("nombre"));
        String descripcion = limpiar(request.getParameter("descripcion"));
        int id = parseEntero(request.getParameter("id"), 0);
        int estado = parseEntero(request.getParameter("estado"), 1);

        Map<String, String> errores = validar(nombre, id);

        Categoria c = new Categoria();
        c.setIdCategoria(id);
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setEstado(estado);

        if (!errores.isEmpty()) {
            request.setAttribute("errores", errores);
            request.setAttribute("categoria", c);
            request.getRequestDispatcher("vistas/categoria/formulario.jsp")
                    .forward(request, response);
            return;
        }

        if ("guardar".equals(accion)) {

            if (dao.insertar(c)) {
                request.getSession().setAttribute("mensaje", "Categoria guardada correctamente.");
            } else {
                request.getSession().setAttribute("error", "No se pudo guardar la categoria.");
            }

        } else {

            if (dao.actualizar(c)) {
                request.getSession().setAttribute("mensaje", "Categoria actualizada correctamente.");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar la categoria.");
            }
        }

        response.sendRedirect("categoria?accion=listar");
    }

    private Map<String, String> validar(String nombre, int id) {
        Map<String, String> errores = new HashMap<>();

        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es obligatorio.");
        } else if (nombre.length() > 80) {
            errores.put("nombre", "El nombre no debe superar 80 caracteres.");
        } else if (dao.existeNombre(nombre, id)) {
            errores.put("nombre", "Ya existe una categoria con ese nombre.");
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
