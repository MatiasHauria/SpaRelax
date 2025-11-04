/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class Sesion {
    private int codSesion = -1;
    private Consultorio consultorio;
    private int codConsultorio;
    private Tratamiento tratamiento;
    private int codTratamiento;
    private DiaDeSpa diadespa;
    private int codPack;
    private ArrayList<Instalacion> instalacion;
    private Masajista masajista;
    private int matricula;
    private LocalDate fechaHoraInicio;
    private LocalDate fechaHoraFin;
    private boolean estado;

    public Sesion(Consultorio consultorio, Tratamiento tratamiento, DiaDeSpa diadespa, ArrayList<Instalacion> instalacion, Masajista masajista, LocalDate fechaHoraInicio, LocalDate fechaHoraFin, boolean estado) {
        this.consultorio = consultorio;
        this.codConsultorio = consultorio.getNroConsultorio();
        this.tratamiento = tratamiento;
        this.codTratamiento = tratamiento.getIdTratamiento();
        this.diadespa = diadespa;
        this.codPack = diadespa.getCodPack();
        this.instalacion = instalacion;
        this.masajista = masajista;
        this.matricula = masajista.getMatricula();
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estado = estado;
    }

    public int getCodSesion() {
        return codSesion;
    }

    public void setCodSesion(int codSesion) {
        this.codSesion = codSesion;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public int getCodConsultorio() {
        return codConsultorio;
    }

    public void setCodConsultorio(int codConsultorio) {
        this.codConsultorio = codConsultorio;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public int getCodTratamiento() {
        return codTratamiento;
    }

    public void setCodTratamiento(int codTratamiento) {
        this.codTratamiento = codTratamiento;
    }

    public DiaDeSpa getDiadespa() {
        return diadespa;
    }

    public void setDiadespa(DiaDeSpa diadespa) {
        this.diadespa = diadespa;
    }

    public int getCodPack() {
        return codPack;
    }

    public void setCodPack(int codPack) {
        this.codPack = codPack;
    }

    public ArrayList<Instalacion> getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(ArrayList<Instalacion> instalacion) {
        this.instalacion = instalacion;
    }

    public Masajista getMasajista() {
        return masajista;
    }

    public void setMasajista(Masajista masajista) {
        this.masajista = masajista;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDate fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDate getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDate fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
