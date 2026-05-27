package edu.udb.inventarioweb.dao;

import edu.udb.inventarioweb.modelo.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> listarTodas() {

        List<Categoria> lista = new ArrayList<>();

        String sql = "SELECT * FROM CATEGORIA ORDER BY nombre";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();

                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getInt("estado"));
                c.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());

                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Categoria> listarActivas() {

        List<Categoria> lista = new ArrayList<>();

        String sql = "SELECT * FROM CATEGORIA WHERE estado = 1 ORDER BY nombre";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();

                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getInt("estado"));
                c.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());

                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Categoria obtenerPorId(int id) {

        Categoria c = null;

        String sql = "SELECT * FROM CATEGORIA WHERE id_categoria=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Categoria();

                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getInt("estado"));
                c.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    public boolean insertar(Categoria c) {

        String sql = "INSERT INTO CATEGORIA(nombre, descripcion) VALUES (?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Categoria c) {

        String sql = "UPDATE CATEGORIA SET nombre=?, descripcion=?, estado=? WHERE id_categoria=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getEstado());
            ps.setInt(4, c.getIdCategoria());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int id) {

        String sql = "UPDATE CATEGORIA SET estado = 0 WHERE id_categoria=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean existeNombre(String nombre, int idEx) {

        String sql = "SELECT COUNT(*) FROM CATEGORIA WHERE nombre=? AND id_categoria != ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
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

    public boolean tieneProductosActivos(int idCategoria) {

        String sql = "SELECT COUNT(*) FROM PRODUCTO WHERE id_categoria=? AND estado=1";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

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
