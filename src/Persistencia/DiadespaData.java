package Persistencia;

import Modelo.DiaDeSpa;
import java.sql.*;
import javax.swing.JOptionPane;

public class DiadespaData {

    private Connection conexion = null;
    private ClienteData cliente;

    public DiadespaData(Conexion conexion) {
        this.conexion = (Connection) conexion.establecerConexion();
        this.cliente = new ClienteData(conexion);
    }

    public void generarDiaDeSpa(DiaDeSpa dia) {
        String query = "INSERT INTO dia_de_spa (id_cliente, fecha_hora, preferencias, monto, sesiones) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, dia.getIdCliente());
            ps.setObject(2, dia.getFechayHora());
            ps.setString(3, dia.getPreferencias());
            ps.setDouble(4, dia.getMonto());
            String listaSesiones = String.join(",", dia.getSesiones());
            ps.setString(5, listaSesiones);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Dia de spa generado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Se produjo un error con generar el dia de spa.");
            }
        } catch (SQLException e) {
            System.out.println("Error: No se pudo realizar la consulta SQL.");
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }
    
    public void actualizarDiaDeSpa(int cod, DiaDeSpa dia) {
        String query = "UPDATE dia_de_spa SET id_cliente=?, fecha_hora=?, preferencias=?, monto=?, sesiones=? WHERE id_pack=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, dia.getIdCliente());
            ps.setObject(2, dia.getFechayHora());
            ps.setString(3, dia.getPreferencias());
            ps.setDouble(4, dia.getMonto());
            String listaSesiones = String.join(",", dia.getSesiones());
            ps.setString(4, listaSesiones);
            ps.setInt(5, cod);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Dia de spa actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Se produjo un error con actualizar el dia de spa.");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo realizar la consulta SQL.");
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }
}
