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
public class DiaDeSpa {
    private int codPack = -1;
    private Cliente cliente;
    private int idCliente;
    private ArrayList<Sesion> sesiones;
    private LocalDate fechayHora;
    private String preferencias;
    private double monto;
    private boolean estado;

    public DiaDeSpa(Cliente cliente, ArrayList<Sesion> sesiones, LocalDate fechayHora, String preferencias, double monto, boolean estado) {
        this.cliente = cliente;
        this.idCliente = cliente.getIdCliente();
        this.sesiones = sesiones;
        this.fechayHora = fechayHora;
        this.preferencias = preferencias;
        this.monto = monto;
        this.estado = estado;
    }

    public int getCodPack() {
        return codPack;
    }

    public void setCodPack(int codPack) {
        this.codPack = codPack;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(ArrayList<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    public LocalDate getFechayHora() {
        return fechayHora;
    }

    public void setFechayHora(LocalDate fechayHora) {
        this.fechayHora = fechayHora;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
