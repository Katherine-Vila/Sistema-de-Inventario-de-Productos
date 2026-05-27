package edu.udb.inventarioweb.dao;

import edu.udb.inventarioweb.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listarTodos() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT p.*, c.nombre AS categoria FROM PRODUCTO p " +
                "INNER JOIN CATEGORIA c ON p.id_categoria = c.id_categoria " +
                "ORDER BY p.nombre";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto p = new Producto();

                p.setIdProducto(rs.getInt("id_producto"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setEstado(rs.getInt("estado"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNombreCategoria(rs.getString("categoria"));
                p.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Producto> listarPorCategoria(int idCat) {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT p.*, c.nombre AS categoria FROM PRODUCTO p " +
                "INNER JOIN CATEGORIA c ON p.id_categoria = c.id_categoria " +
                "WHERE p.id_categoria=? ORDER BY p.nombre";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCat);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();

                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setCodigo(rs.getString("codigo"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setStock(rs.getInt("stock"));
                    p.setEstado(rs.getInt("estado"));
                    p.setIdCategoria(rs.getInt("id_categoria"));
                    p.setNombreCategoria(rs.getString("categoria"));
                    p.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());

                    lista.add(p);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Producto obtenerPorId(int id) {

        Producto p = null;

        String sql = "SELECT * FROM PRODUCTO WHERE id_producto=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                p = new Producto();

                p.setIdProducto(rs.getInt("id_producto"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setEstado(rs.getInt("estado"));
                p.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    public boolean insertar(Producto p) {

        String sql = "INSERT INTO PRODUCTO(id_categoria, codigo, nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getIdCategoria());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getDescripcion());
            ps.setDouble(5, p.getPrecio());
            ps.setInt(6, p.getStock());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Producto p) {

        String sql = "UPDATE PRODUCTO SET id_categoria=?, codigo=?, nombre=?, descripcion=?, precio=?, stock=?, estado=? WHERE id_producto=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getIdCategoria());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getDescripcion());
            ps.setDouble(5, p.getPrecio());
            ps.setInt(6, p.getStock());
            ps.setInt(7, p.getEstado());
            ps.setInt(8, p.getIdProducto());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int id) {

        String sql = "UPDATE PRODUCTO SET estado = 0 WHERE id_producto=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean existeCodigo(String codigo, int idEx) {

        String sql = "SELECT COUNT(*) FROM PRODUCTO WHERE codigo=? AND id_producto != ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ps.setInt(2, idEx);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
