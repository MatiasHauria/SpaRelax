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

    private Connection conexion = null;
    private ClienteData cliente;
    private SesionData sesion;
    private Conexion gestorConexion;

    public DiadespaData(Conexion conexion) {
        this.gestorConexion = conexion;
        //this.conexion = (Connection) conexion.establecerConexion();
        //this.cliente = new ClienteData(conexion);
        //this.sesion = new SesionData(conexion);
    }

    public void generarDiaDeSpa(DiaDeSpa dia) {
        String query = "INSERT INTO dia_de_spa (id_cliente, fecha_hora, preferencias, monto, estado, sesiones) VALUES (?,?,?,?,?,?)";
        Connection conexionLocal = null;
        try {
            conexionLocal = (Connection) gestorConexion.establecerConexion();
            PreparedStatement ps = conexionLocal.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            if (conexionLocal != null) {
                try {
                    conexionLocal.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public void actualizarDiaDeSpa(int cod, DiaDeSpa dia) {
        Connection conexionLocal = null;
        String query = "UPDATE dia_de_spa SET id_cliente=?, fecha_hora=?, preferencias=?, monto=?, sesiones=? WHERE id_pack=?";
        try {
            conexionLocal = (Connection) gestorConexion.establecerConexion();
            PreparedStatement ps = conexionLocal.prepareStatement(query);
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
            if (conexionLocal != null) {
                try {
                    conexionLocal.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public DiaDeSpa buscarDiaDeSpa(int cod) {
        DiaDeSpa dia = null;
        Connection conexionLocal = null;
        String query = "SELECT * FROM dia_de_spa WHERE id_pack=?";
        try {
            conexionLocal = (Connection) gestorConexion.establecerConexion();
            PreparedStatement ps = conexionLocal.prepareStatement(query);
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String sesiones = rs.getString("sesiones");
                String[] arraySesiones = sesiones.split(",");
                int[] codigosSesiones = new int[arraySesiones.length];
                List<Sesion> listaSesiones = new ArrayList<>();
                for (int i = 0; i < arraySesiones.length; i++) {
                    codigosSesiones[i] = Integer.parseInt(arraySesiones[i]);
                    Sesion sesion = this.sesion.buscarSesion(codigosSesiones[i]);
                    listaSesiones.add(sesion);
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
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            if (conexionLocal != null) {
                try {
                    conexionLocal.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
        return dia;
    }

    public void borrarDiaDeSpa(int cod) {
        String query = "DELETE FROM dia_de_spa WHERE id_pack=?";
        Connection conexionLocal = null;
        try {
            conexionLocal = (Connection) gestorConexion.establecerConexion();
            PreparedStatement ps = conexionLocal.prepareStatement(query);
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
            if (conexionLocal != null) {
                try {
                    conexionLocal.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public void bajaLogicaDiaDeSpa(int cod) {
        Connection conexionLocal = null;
        String query = "UPDATE dia_de_spa SET estado=0 WHERE id_pack=?";
        try {
             conexionLocal = (Connection) gestorConexion.establecerConexion();
            PreparedStatement ps = conexionLocal.prepareStatement(query);
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
            if (conexionLocal != null) {
                try {
                    conexionLocal.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public void altaLogicaDiaDeSpa(int cod) {
        Connection conexionLocal = null;
        String query = "UPDATE dia_de_spa SET estado=1 WHERE id_pack=?";
        try {
            conexionLocal = (Connection) gestorConexion.establecerConexion();
            PreparedStatement ps = conexionLocal.prepareStatement(query);
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
            if (conexionLocal != null) {
                try {
                    conexionLocal.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
    }

    public List<DiaDeSpa> obtenerTodosLosDiasDeSpa() {
        List<DiaDeSpa> dias = new ArrayList<>();
        Connection conexionLocal = null;
        String query = "SELECT * FROM dia_de_spa";

        try {
            conexionLocal = (Connection) gestorConexion.establecerConexion();
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Obtener datos básicos
                int idPack = rs.getInt("id_pack");
                int idCliente = rs.getInt("id_cliente");
                LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();
                String preferencias = rs.getString("preferencias");
                double monto = rs.getDouble("monto");
                boolean estado = rs.getBoolean("estado");
                String sesionesStr = rs.getString("sesiones");

                // Obtener el cliente
                Cliente cliente = this.cliente.buscarCliente(idCliente);

                // Procesar las sesiones
                List<Sesion> sesiones = new ArrayList<>();
                List<String> sesionesCodigos = new ArrayList<>();

                if (sesionesStr != null && !sesionesStr.trim().isEmpty()) {
                    String[] codigos = sesionesStr.split("\\s*,\\s*");
                    for (String codigoStr : codigos) {
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

                // Crear el objeto DiaDeSpa
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
        }

        return dias;
    }


}
