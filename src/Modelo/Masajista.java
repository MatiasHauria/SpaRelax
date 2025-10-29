package Modelo;

public class Masajista {

    private int matricula;
    private String nombrecompleto;
    private long telefono;
    private String especialidad;
    private boolean estado;

    public Masajista(int matricula, String nombrecompleto, long telefono, String especialidad) {
        this.matricula = matricula;
        this.nombrecompleto = nombrecompleto;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.estado = false; // Sujeto a cambios. Debería inicializarse en false (?
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombreCompleto() {
        return nombrecompleto;
    }

    public void setNombreCompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }
    
    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Masajista{" + "matricula=" + matricula + ", nombrecompleto=" + nombrecompleto + ", especialidad=" + especialidad + '}';
    }

   

}
