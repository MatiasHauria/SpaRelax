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

    public int generarDiaDeSpa(DiaDeSpa dia) {
    String queryDiaSpa = "INSERT INTO dia_de_spa (id_cliente, fecha_hora, preferencias, monto, estado) VALUES (?,?,?,?,?)";
    Connection con = null;
    
    try {
        con = Conexion.establecerConexion();
        
        System.out.println("debug dia spa pq me harto el error");
        System.out.println("Sesiones a asignar:");
        for (Sesion sesion : dia.getSesiones()) {
            System.out.println(" - Sesion ID: " + sesion.getCodSesion());
        }
        
        PreparedStatement psDia = con.prepareStatement(queryDiaSpa, Statement.RETURN_GENERATED_KEYS);
        psDia.setInt(1, dia.getIdCliente());
        psDia.setTimestamp(2, Timestamp.valueOf(dia.getFechayHora()));
        psDia.setString(3, dia.getPreferencias());
        psDia.setDouble(4, dia.getMonto());
        psDia.setBoolean(5, dia.isEstado());
        
        int filas = psDia.executeUpdate();
        System.out.println("Filas insertadas en dia_de_spa: " + filas);
        
        ResultSet rs = psDia.getGeneratedKeys();
        int idPackGenerado = -1;
        if (rs.next()) {
            idPackGenerado = rs.getInt(1);
            dia.setCodPack(idPackGenerado);
            System.out.println("ID PACK generado: " + idPackGenerado);
        }
        
        if (idPackGenerado != -1 && !dia.getSesiones().isEmpty()) {
            actualizarSesionesConIdPack(con, idPackGenerado, dia.getSesiones());
        } else {
            System.out.println("No se pudieron actualizar sesiones. idPackGenerado: " + idPackGenerado + ", sesiones vacias: " + dia.getSesiones().isEmpty());
        }
        
        if (filas > 0) {
            JOptionPane.showMessageDialog(null, "Dia de spa generado exitosamente. ID: " + idPackGenerado);
        }
        
        return idPackGenerado;
        
    } catch (SQLException e) {
        System.out.println("Error al generar dia de spa: " + e.getMessage());
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al generar dia de spa: " + e.getMessage());
        return -1;
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

private void actualizarSesionesConIdPack(Connection con, int idPack, List<Sesion> sesiones) throws SQLException {
    String query = "UPDATE sesion SET id_pack = ? WHERE id_sesion = ?";
    PreparedStatement ps = con.prepareStatement(query);
    
    System.out.println("actualizacion sesiones");
    for (Sesion sesion : sesiones) {
        int idSesion = sesion.getCodSesion();
        ps.setInt(1, idPack);
        ps.setInt(2, idSesion);
        ps.addBatch();
        System.out.println("Asignando id_pack " + idPack + " a sesión " + idSesion);
    }
    
    int[] resultados = ps.executeBatch();
    System.out.println("Batch ejecutado. Resultados: " + resultados.length);
    
    for (int i = 0; i < resultados.length; i++) {
        System.out.println("Sesion " + sesiones.get(i).getCodSesion() + "filas actualizadas: " + resultados[i]);
    }
    
    ps.close();
}


    public void actualizarDiaDeSpa(int idPack, DiaDeSpa dia) {
    Connection con = null;
    
    try {
        con = Conexion.establecerConexion();
        
        limpiarIdPackDeSesiones(con, idPack);
        
        String queryDia = "UPDATE dia_de_spa SET id_cliente=?, fecha_hora=?, preferencias=?, monto=? WHERE id_pack=?";
        PreparedStatement psDia = con.prepareStatement(queryDia);
        psDia.setInt(1, dia.getIdCliente());
        psDia.setTimestamp(2, Timestamp.valueOf(dia.getFechayHora()));
        psDia.setString(3, dia.getPreferencias());
        psDia.setDouble(4, dia.getMonto());
        psDia.setInt(5, idPack);
        
        int filas = psDia.executeUpdate();
        
        if (filas > 0 && !dia.getSesiones().isEmpty()) {
            actualizarSesionesConIdPack(con, idPack, dia.getSesiones());
        }
        
        if (filas > 0) {
            JOptionPane.showMessageDialog(null, "Día de spa actualizado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el día de spa para actualizar.");
        }
        
    } catch (SQLException e) {
        System.out.println("Error al actualizar día de spa: " + e.getMessage());
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar día de spa: " + e.getMessage());
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
    
    private void limpiarIdPackDeSesiones(Connection con, int idPack) throws SQLException {
    String query = "UPDATE sesion SET id_pack = NULL WHERE id_pack = ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, idPack);
    int filas = ps.executeUpdate();
    System.out.println("Sesiones limpiadas: " + filas);
    ps.close();
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

   public void borrarDiaDeSpa(int idPack) {
    Connection con = null;
    
    try {
        con = Conexion.establecerConexion();
        
        limpiarIdPackDeSesiones(con, idPack);
        
        String query = "DELETE FROM dia_de_spa WHERE id_pack=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idPack);
        int filas = ps.executeUpdate();
        
        if (filas > 0) {
            JOptionPane.showMessageDialog(null, "Día de spa eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el día de spa para eliminar.");
        }
        
    } catch (SQLException s) {
        System.out.println("Error al eliminar día de spa: " + s.getMessage());
        s.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al eliminar día de spa: " + s.getMessage());
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

            Cliente cliente = this.cliente.buscarCliente(idCliente);

            List<Sesion> sesiones = obtenerSesionesPorIdPack(con, idPack);
            List<String> sesionesCodigos = new ArrayList<>();
            
            for (Sesion sesion : sesiones) {
                sesionesCodigos.add(String.valueOf(sesion.getCodSesion()));
            }
            
            System.out.println("Día " + idPack + " tiene " + sesiones.size() + " sesiones: " + sesionesCodigos);

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
    private List<Sesion> obtenerSesionesPorIdPack(Connection con, int idPack) throws SQLException {
    List<Sesion> sesiones = new ArrayList<>();
    String query = "SELECT * FROM sesion WHERE id_pack = ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, idPack);
    ResultSet rs = ps.executeQuery();
    
    while (rs.next()) {
        Sesion sesion = this.sesion.buscarSesion(rs.getInt("id_sesion"));
        if (sesion != null) {
            sesiones.add(sesion);
        }
    }
    
    ps.close();
    return sesiones;
}
}