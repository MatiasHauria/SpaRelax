package Modelo;

import Persistencia.Conexion;
import Persistencia.DiadespaData;
import Persistencia.SesionData;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiaDeSpa {
    private int codPack = -1;
    private Cliente cliente;
    private int idCliente;
    private List<Sesion> sesiones;
    private List<String> sesionesCodigos;
    private LocalDateTime fechayHora;
    private String preferencias;
    private double monto;
    private boolean estado;
    public DiaDeSpa(Cliente cliente, List<Sesion> sesiones, LocalDateTime fechayHora, String preferencias, double monto) {
        this.cliente = cliente;
        this.sesiones = new ArrayList<>(sesiones);
        this.sesionesCodigos = new ArrayList<>();
        for (Sesion sesion : sesiones) {
            String sesionesCod = String.valueOf(sesion.getCodSesion());
            sesionesCodigos.add(sesionesCod);
        }
        this.fechayHora = fechayHora;
        this.preferencias = preferencias;
        this.monto = monto;
        this.idCliente = cliente.getIdCliente();
        this.estado = false;
    }

    public List<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    public List<String> getSesionesCodigos() {
        return sesionesCodigos;
    }

    public void setSesionesCodigos(List<String> sesionesCodigos) {
        this.sesionesCodigos = sesionesCodigos;
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

    public LocalDateTime getFechayHora() {
        return fechayHora;
    }

    public void setFechayHora(LocalDateTime fechayHora) {
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
