package edu.udb.inventarioweb.modelo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Producto {

    private int idProducto;
    private int idCategoria;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int estado;
    private LocalDateTime fechaCreacion;

    private String nombreCategoria;

    public Producto() {
    }

    public Producto(int idProducto, int idCategoria, String codigo, String nombre,
                    String descripcion, double precio, int stock,
                    int estado, LocalDateTime fechaCreacion) {

        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", idCategoria=" + idCategoria +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", estado=" + estado +
                ", fechaCreacion=" + fechaCreacion +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                '}';
    }
}
