package Modelo;

public class Instalacion {

    private int nroInstalacion;
    private String nombre;
    private String detalleUso;
    private double precio;
    private boolean estado;

    public Instalacion(String nombre, String detalleUso, double precio, boolean estado) {
        this.nroInstalacion = -1;
        this.nombre = nombre;
        this.detalleUso = detalleUso;
        this.precio = precio;
        this.estado = estado; // Sujeto a cambios. Debería inicializar en false (?
    }

    public int getId_instalacion() {
        return nroInstalacion;
    }

    public void setId_instalacion(int nroInstalacion) {
        this.nroInstalacion = nroInstalacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle_de_uso() {
        return detalleUso;
    }

    public void setDetalle_de_uso(String detalleUso) {
        this.detalleUso = detalleUso;
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

    public String toString() {
        return "Nro de instalación: " + " | " + "Nombre: " + this.nombre;
    }

}
