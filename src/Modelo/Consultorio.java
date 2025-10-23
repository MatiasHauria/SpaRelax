package Modelo;

public class Consultorio {

    private int idConsultorio;
    private String usos;
    private String equipamiento;
    private boolean apto;

    public Consultorio(String usos, String equipamiento, boolean apto) {
        this.idConsultorio = -1;
        this.usos = usos;
        this.equipamiento = equipamiento;
        this.apto = apto; // SUjeto a cambios. Debería inicializarse en false (?
    }

    public int getNroConsultorio() {
        return idConsultorio;
    }

    public String getUsos() {
        return usos;
    }

    public String getEquipamiento() {
        return equipamiento;
    }

    public boolean isApto() {
        return apto;
    }

    public void setNroConsultorio(int idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public void setUsos(String usos) {
        this.usos = usos;
    }

    public void setEquipamiento(String equipamiento) {
        this.equipamiento = equipamiento;
    }

    public void setApto(boolean apto) {
        this.apto = apto;
    }

    @Override
    public String toString() {
        return "Nro de consultorio: " + this.idConsultorio + " | " + "Usos: " + this.usos + " | " + "Equipamiento: " + this.equipamiento + " | " + "Apto: " + this.apto;
    }

}
