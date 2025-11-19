package Persistencia;

import Modelo.Masajista;
import java.util.ArrayList;
import java.sql.*;

public class MasajistaData {

    public void insertarMasajista(Masajista e) {
        String query = "INSERT INTO masajista(matricula,nombre_completo,telefono,especialidad,estado) VALUES(?,?,?,?,?) ";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, e.getMatricula());
            ps.setString(2, e.getNombreCompleto());
            ps.setLong(3, e.getTelefono());
            ps.setString(4, e.getEspecialidad());
            ps.setBoolean(5, e.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            System.out.println("Masajista guardado correctamente");
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

    public Masajista buscarMasajista(int matricula) {
        Masajista a = null;
        Connection con = null;
        String sql = "SELECT * FROM masajista WHERE matricula=?";
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matricula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Masajista(
                        rs.getInt("matricula"),
                        rs.getString("nombre_completo"),
                        rs.getLong("telefono"),
                        rs.getString("especialidad")
                );
                a.setMatricula(rs.getInt("matricula"));
                System.out.println("Encontrado: " + a.getNombreCompleto());
            } else {
                System.out.println("No se encontró el Masajista con Matricula " + matricula);
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

    public void actualizarMasajista(int matricula, String nombrecompletonuevo, long telefononuevo, String especialidadnuevo, boolean estadonuevo) {
        String sql = "UPDATE masajista SET   nombre_completo=?,telefono=?,especialidad=?,estado=? WHERE matricula=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombrecompletonuevo);
            ps.setLong(2, telefononuevo);
            ps.setString(3, especialidadnuevo);
            ps.setBoolean(4, estadonuevo);
            ps.setInt(5, matricula);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Masajista actualizado correctamente");
            } else {
                System.out.println("No se encuentra el Masajista con matricula " + matricula);
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

    public void bajaMasajista(int matricula) {
        String sql = "UPDATE masajista SET estado=false WHERE matricula=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matricula);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Masajista dado de baja");
            } else {
                System.out.println("No se encuentra el Masajista con matricula " + matricula);
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

    public void altaMasajista(int matricula) {
        String sql = "UPDATE masajista SET estado=true WHERE matricula=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matricula);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Masajista dado de alta");
            } else {
                System.out.println("No se encuentra el Masajista con matricula " + matricula);
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

    public void borrarMasajista(int matricula) {
        String sql = "DELETE FROM masajista WHERE matricula=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matricula);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("masajista borrado de la DB");
            } else {
                System.out.println("No se encuentra el masajista con matricula " + matricula);
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

    public ArrayList<Masajista> obtenerMasajista() {
        ArrayList<Masajista> listaDeMasajistas = new ArrayList<>();
        String sql = "Select * FROM masajista";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int matricula = rs.getInt("matricula");
                String nombrecompleto = rs.getString("nombre_completo");
                long telefono = rs.getLong("telefono");
                String especialidad = rs.getString("especialidad");
                boolean estado = rs.getBoolean("estado");

                Masajista masajista = new Masajista(matricula, nombrecompleto, telefono, especialidad);
                masajista.setMatricula(matricula);
                masajista.setEstado(estado);
                listaDeMasajistas.add(masajista);
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
        return listaDeMasajistas;
    }

    public ArrayList<Masajista> obtenerMasajistasPorEspecialidad(String especialidad) {
        ArrayList<Masajista> lista = new ArrayList<>();
        String sql = "SELECT * FROM masajista WHERE especialidad = ?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, especialidad);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Masajista m = new Masajista(
                        rs.getInt("matricula"),
                        rs.getString("nombre_completo"),
                        rs.getLong("telefono"),
                        rs.getString("especialidad")
                );
                m.setEstado(rs.getBoolean("estado"));
                lista.add(m);
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
        return lista;
    }
}
