package Persistencia;

import Modelo.DiaDeSpa;
import java.sql.*;
import Modelo.Cliente;
import java.util.Arrays;
import java.util.List;
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
    
    public DiaDeSpa buscarDiaDeSpa(int cod) {
        DiaDeSpa dia = null;       
        String query = "SELECT * FROM dia_de_spa WHERE id_pack=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String sesiones = rs.getString("sesiones");
                String[] arraySesiones = sesiones.split(",");
                List<String> listaSesiones = Arrays.asList(arraySesiones);
                Cliente cliente = this.cliente.buscarCliente(rs.getInt("id_cliente"));
                dia = new DiaDeSpa(
                        cliente,
                        listaSesiones,
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("preferencias"),
                        rs.getDouble("monto")
                );
                dia.setCodPack(rs.getInt("id_pack"));
                dia.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
        return dia;
    }
    
    public void borrarDiaDeSpa (int cod) {
        String query = "DELETE FROM dia_de_spa WHERE id_pack=?";
        try {
         PreparedStatement ps = conexion.prepareStatement(query);
         ps.setInt(1, cod);
         int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Se ha eliminado el dia de spa exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado un dia de spa con ese ID.");
            }
        } catch (SQLException s) {
            s.printStackTrace();
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
    
    public void bajaLogicaDiaDeSpa (int cod) {
        String query = "UPDATE dia_de_spa SET estado=0 WHERE id_pack=?";
        try {
         PreparedStatement ps = conexion.prepareStatement(query);
         ps.setInt(1, cod);
         int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Se ha dado de baja el dia de spa exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado un dia de spa con ese ID.");
            }
        } catch (SQLException s) {
            s.printStackTrace();
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
    public void altaLogicaDiaDeSpa (int cod) {
        String query = "UPDATE dia_de_spa SET estado=1 WHERE id_pack=?";
        try {
         PreparedStatement ps = conexion.prepareStatement(query);
         ps.setInt(1, cod);
         int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Se ha dado de alta el dia de spa exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado un dia de spa con ese ID.");
            }
        } catch (SQLException s) {
            s.printStackTrace();
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
