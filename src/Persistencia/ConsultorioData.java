package Persistencia;

import Modelo.Consultorio;
import java.util.ArrayList;
import java.sql.*;

public class ConsultorioData {

    public void insertarConsultorio(Consultorio e) {
        String query = "INSERT INTO consultorio(id_consultorio,usos,equipamento,apto) VALUES(?,?,?,?)";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, e.getNroConsultorio());
            ps.setString(2, e.getUsos());
            ps.setString(3, e.getEquipamiento());
            ps.setBoolean(4, e.isApto());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setNroConsultorio(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el ID");
            }
            ps.close();
            System.out.println("Consultorio guardado con exito");
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public void mostrarTodos() {
        String sql = "SELECT * FROM consultorio";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Consultorio a = new Consultorio(
                        rs.getString("usos"),
                        rs.getString("equipamento"));
                a.setNroConsultorio(rs.getInt("id_consultorio"));
                System.out.println("ID: " + a.getNroConsultorio()
                        + " | Equipamento: " + a.getEquipamiento()
                        + " | Usos: " + a.getUsos() + ", "
                        + " | Apto: " + (a.isApto() ? "Activo" : "Inactivo"));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public Consultorio buscarConsultorio(int id_consultorio) {
        Consultorio a = null;
        Connection con = null;
        String sql = "SELECT * FROM consultorio WHERE id_consultorio=?";
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_consultorio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Consultorio(
                        rs.getString("usos"),
                        rs.getString("equipamento"));
                a.setNroConsultorio(rs.getInt("id_consultorio"));
                System.out.println("Encontrado: " + a.getNroConsultorio());
            } else {
                System.out.println("No se encuentra el consultorio" + id_consultorio);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
        return a;
    }

    public void actualizarConsultorio(int idNuevo, String usoNuevo, String equipoNuevo, boolean aptoNuevo) {
        String sql = "UPDATE Consultorio SET id_consultorio=?,usos=?,equipamento=?,apto=? WHERE id_consultorio=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idNuevo);
            ps.setString(2, usoNuevo);
            ps.setString(3, equipoNuevo);
            ps.setBoolean(4, aptoNuevo);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Consultorio actualizado correctamente");
            } else {
                System.out.println("No se encuentra el consultorio con ID " + idNuevo);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public void bajaConsultorio(int id_consultorio) {
        String sql = "UPDATE Consultorio SET apto=false WHERE id_consultorio=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_consultorio);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Consultorio dado de baja");
            } else {
                System.out.println("No se encuentra el consultorio con ID " + id_consultorio);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public void altaConsultorio(int id_consultorio) {
        String sql = "UPDATE consultorio SET apto=true WHERE id_consultorio=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_consultorio);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Consultorio dado de alta");
            } else {
                System.out.println("No se encuentra el consultorio con ID " + id_consultorio);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public void borrarConsultorio(int id_consultorio) {
        String sql = "DELETE FROM consultorio WHERE id_consultorio=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_consultorio);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Consultorio borrado de la DB");
            } else {
                System.out.println("No se encuentra el consultorio con ID " + id_consultorio);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public ArrayList<Consultorio> obtenerConsultorio() {
        ArrayList<Consultorio> listaDeConsultorios = new ArrayList<>();
        String sql = "Select * FROM consultorio";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idCons = rs.getInt("id_consultorio");
                String usos = rs.getString("usos");
                String equipo = rs.getString("equipamento");
                boolean apto = rs.getBoolean("apto");
                Consultorio consultorio = new Consultorio(usos, equipo);
                consultorio.setNroConsultorio(idCons);
                consultorio.setUsos(usos);
                consultorio.setEquipamiento(equipo);
                consultorio.setApto(apto);
                listaDeConsultorios.add(consultorio);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
        return listaDeConsultorios;
    }
}
