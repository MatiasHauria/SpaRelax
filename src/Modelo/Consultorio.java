/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class Consultorio {
    private int idConsultorio;
    private String usos;
    private String equipamiento;
    private boolean apto;

    public Consultorio(int idConsultorio, String equipamiento, String usos) {
        this.idConsultorio = idConsultorio;
        this.usos = usos;
        this.equipamiento = equipamiento;
        this.apto = apto;
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

    public void setNroConsultorio(int nroConsultorio) {
        this.idConsultorio = nroConsultorio;
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
        return "Consultorio{" + "idConsultorio=" + idConsultorio + ", usos=" + usos + ", equipamiento=" + equipamiento + ", apto=" + apto + '}';
    }
    
    
    
}
