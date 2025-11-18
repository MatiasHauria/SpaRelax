package Persistencia;

import Modelo.DiaDeSpa;
import java.sql.*;
import Modelo.Cliente;
import Modelo.Sesion;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DiadespaData {

    private ClienteData cliente;
    private SesionData sesion;

    public DiadespaData() {
        this.cliente = new ClienteData();
        this.sesion = new SesionData();
    }

    public void setSesion(SesionData sesion) {
        this.sesion = sesion;
    }

    public void generarDiaDeSpa(DiaDeSpa dia) {
        String query = "INSERT INTO dia_de_spa (id_cliente, fecha_hora, preferencias, monto, estado, sesiones) VALUES (?,?,?,?,?,?)";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, dia.getIdCliente());
            ps.setObject(2, dia.getFechayHora());
            ps.setString(3, dia.getPreferencias());
            ps.setDouble(4, dia.getMonto());
            ps.setBoolean(5, dia.isEstado());
            String listaSesiones = String.join(",", dia.getSesionesCodigos());
            ps.setString(6, listaSesiones);
            int filas = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                dia.setCodPack(rs.getInt(1));
            } else {
                System.out.println("No se logro obtener el ID del día de spa.");
            }
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Dia de spa generado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Se produjo un error con generar el dia de spa.");
            }
        } catch (SQLException e) {
            System.out.println("Error: No se pudo realizar la consulta SQL.");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public void actualizarDiaDeSpa(int cod, DiaDeSpa dia) {
        Connection con = null;
        String query = "UPDATE dia_de_spa SET id_cliente=?, fecha_hora=?, preferencias=?, monto=?, sesiones=? WHERE id_pack=?";
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, dia.getIdCliente());
            ps.setObject(2, dia.getFechayHora());
            ps.setString(3, dia.getPreferencias());
            ps.setDouble(4, dia.getMonto());
            String listaSesiones = String.join(",", dia.getSesionesCodigos());
            ps.setString(5, listaSesiones);
            ps.setInt(6, cod);
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
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public DiaDeSpa buscarDiaDeSpa(int cod) {
        DiaDeSpa dia = null;
        Connection con = null;
        String query = "SELECT * FROM dia_de_spa WHERE id_pack=?";
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String sesiones = rs.getString("sesiones");
                List<Sesion> listaSesiones = new ArrayList<>();
                List<String> sesionesCodigos = new ArrayList<>();
                
                if (sesiones != null && !sesiones.trim().isEmpty()) {
                    String[] arraySesiones = sesiones.split("\\s*,\\s*");
                    
                    for (String sesionStr : arraySesiones) {
                        if (!sesionStr.trim().isEmpty()) {
                            try {
                                int codigoSesion = Integer.parseInt(sesionStr.trim());
                                Sesion sesionObj = this.sesion.buscarSesion(codigoSesion);
                                if (sesionObj != null) {
                                    listaSesiones.add(sesionObj);
                                    sesionesCodigos.add(sesionStr.trim());
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Error: código de sesión inválido: " + sesionStr);
                            }
                        }
                    }
                }
                
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
                dia.setSesionesCodigos(sesionesCodigos);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
        return dia;
    }

    public void borrarDiaDeSpa(int cod) {
        String query = "DELETE FROM dia_de_spa WHERE id_pack=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query);
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
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public void bajaLogicaDiaDeSpa(int cod) {
        Connection con = null;
        String query = "UPDATE dia_de_spa SET estado=0 WHERE id_pack=?";
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query);
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
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public void altaLogicaDiaDeSpa(int cod) {
        Connection con = null;
        String query = "UPDATE dia_de_spa SET estado=1 WHERE id_pack=?";
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query);
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
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public List<DiaDeSpa> obtenerTodosLosDiasDeSpa() {
        List<DiaDeSpa> dias = new ArrayList<>();
        Connection con = null;
        String query = "SELECT * FROM dia_de_spa";
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idPack = rs.getInt("id_pack");
                int idCliente = rs.getInt("id_cliente");
                LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();
                String preferencias = rs.getString("preferencias");
                double monto = rs.getDouble("monto");
                boolean estado = rs.getBoolean("estado");
                String sesionesStr = rs.getString("sesiones");

                Cliente cliente = this.cliente.buscarCliente(idCliente);

                List<Sesion> sesiones = new ArrayList<>();
                List<String> sesionesCodigos = new ArrayList<>();

                if (sesionesStr != null && !sesionesStr.trim().isEmpty()) {
                    String[] codigos = sesionesStr.split("\\s*,\\s*");
                    for (String codigoStr : codigos) {
                        if (!codigoStr.trim().isEmpty()) {
                            try {
                                int codigo = Integer.parseInt(codigoStr.trim());
                                Sesion sesion = this.sesion.buscarSesion(codigo);
                                if (sesion != null) {
                                    sesiones.add(sesion);
                                    sesionesCodigos.add(codigoStr.trim());
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Código de sesión inválido: " + codigoStr);
                            }
                        }
                    }
                }

                DiaDeSpa dia = new DiaDeSpa(cliente, sesiones, fechaHora, preferencias, monto);
                dia.setCodPack(idPack);
                dia.setEstado(estado);
                dia.setSesionesCodigos(sesionesCodigos);

                dias.add(dia);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener días de spa: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
        return dias;
    }
}