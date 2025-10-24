package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Tratamiento {

    private int idTratamiento;
    private String nombre;
    private String detalle;
    private List<String> productos;
    private int duracion;
    private double costo;
    private boolean activo;

    public Tratamiento(String nombre, String detalle, List<String> productos, int duracion, double costo) {
        this.idTratamiento = -1;
        this.nombre = nombre;
        this.detalle = detalle;
        this.productos = new ArrayList<>(productos);
        this.duracion = duracion;
        this.costo = costo;
        this.activo = false;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Nro de tratamiento: " + this.idTratamiento + " | " + "Nombre: " + this.nombre;
    }
}
