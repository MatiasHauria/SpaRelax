package Modelo;

public class Instalacion {

    private int idInstalacion;
    private String nombre;
    private String detalleUso;
    private double precioPorHora;
    private boolean estado;

    public Instalacion(String nombre, String detalleUso, double precioPorHora, boolean estado) {
        this.idInstalacion = -1;
        this.nombre = nombre;
        this.detalleUso = detalleUso;
        this.precioPorHora = precioPorHora;
        this.estado = estado; // Sujeto a cambios. Debería inicializar en false (?
    }

    public int getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(int idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalleUso() {
        return detalleUso;
    }

    public void setDetalleUso(String detalleUso) {
        this.detalleUso = detalleUso;
    }

    public double getPrecioPorHora() {
        return precioPorHora;
    }

    public void setPrecioPorHora(double precioPorHora) {
        this.precioPorHora = precioPorHora;
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
