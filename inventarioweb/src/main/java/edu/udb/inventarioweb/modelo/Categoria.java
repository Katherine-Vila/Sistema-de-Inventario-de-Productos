package edu.udb.inventarioweb.modelo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Categoria {

    private int idCategoria;
    private String nombre;
    private String descripcion;
    private int estado;
    private LocalDateTime fechaCreacion;

    public Categoria() {
    }

    public Categoria(int idCategoria, String nombre, String descripcion, int estado, LocalDateTime fechaCreacion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCreacionDate() {
        if (fechaCreacion == null) {
            return null;
        }
        return Date.from(fechaCreacion.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
