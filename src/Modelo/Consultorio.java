package Modelo;

public class Consultorio {
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    private int idConsultorio;
    private String usos;
    private String equipamiento;
    private boolean apto;

<<<<<<< Updated upstream
    public Consultorio(String usos, String equipamiento, boolean apto) {
        this.idConsultorio = -1;
=======
    public Consultorio(int idConsultorio, String equipamiento, String usos, boolean apto) {
        this.idConsultorio = idConsultorio;
>>>>>>> Stashed changes
        this.usos = usos;
        this.equipamiento = equipamiento;
        this.apto = apto; // SUjeto a cambios. Debería inicializarse en false (?
    }

    public int getIdConsultorio() {
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

<<<<<<< Updated upstream
    public void setIdConsultorio(int idConsultorio) {
        this.idConsultorio = idConsultorio;
=======
    public void setNroConsultorio(int nroConsultorio) {
        this.idConsultorio = nroConsultorio;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        return "Nro de consultorio: " + this.idConsultorio + " | " + "Usos: " + this.usos + " | " + "Equipamiento: " + this.equipamiento + " | " + "Apto: " + this.apto;
=======
        return "Consultorio{" + "idConsultorio=" + idConsultorio + ", usos=" + usos + ", equipamiento=" + equipamiento + ", apto=" + apto + '}';
>>>>>>> Stashed changes
    }

}
