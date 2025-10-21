/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Instalacion {
    private int id_instalacion;
    private String nombre;
    private String detalle_de_uso;
    private double precio;
    private boolean estado;

    public Instalacion(int id_instalacion, String nombre, String detalle_de_uso, double precio, boolean estado) {
        this.id_instalacion = id_instalacion;
        this.nombre = nombre;
        this.detalle_de_uso = detalle_de_uso;
        this.precio = precio;
        this.estado = estado;
    }

    public int getId_instalacion() {
        return id_instalacion;
    }

    public void setId_instalacion(int id_instalacion) {
        this.id_instalacion = id_instalacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle_de_uso() {
        return detalle_de_uso;
    }

    public void setDetalle_de_uso(String detalle_de_uso) {
        this.detalle_de_uso = detalle_de_uso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
