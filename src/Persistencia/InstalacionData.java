package Persistencia;

import java.sql.Connection;
import Modelo.Instalacion;
import java.util.ArrayList;
import java.sql.*;

public class InstalacionData {

    public void insertarInstalacion(Instalacion e) {
        String query = "INSERT INTO instalacion(nombre,detalle_de_uso,precio_hr,estado) VALUES(?,?,?,?) ";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDetalleUso());
            ps.setDouble(3, e.getPrecioPorHora());
            ps.setBoolean(4, e.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setIdInstalacion(rs.getInt(1));
            } else {
                System.out.println("No se pudo tener el ID ");
            }
            ps.close();
            System.out.println("Instalacion guardada correctamente");
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

    public Instalacion buscarInstalacion(int id) {
        Instalacion a = null;
        Connection con = null;
        String sql = "SELECT * FROM instalacion WHERE id_instalacion=?";
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Instalacion(
                        rs.getString("nombre"),
                        rs.getString("detalle_de_uso"),
                        rs.getInt("precio_hr")
                );
                a.setIdInstalacion(rs.getInt("id_instalacion"));
                a.setEstado(rs.getBoolean("estado"));
                System.out.println("Encontrado: " + a.getNombre());
            } else {
                System.out.println("No se encontró la instalacion con ID " + id);
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

    public void actualizarInstalacion(int id, String nuevoNombre, String nuevosDetalles, double nuevoPrecio) {
        String sql = "UPDATE instalacion SET nombre=?,detalle_de_uso=?,precio_hr=? WHERE id_instalacion=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevosDetalles);
            ps.setDouble(3, nuevoPrecio);
            ps.setInt(4, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Instalacion actualizada correctamente");
            } else {
                System.out.println("No se encuentra la instalacion con ID " + id);
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

    public void bajaInstalacion(int id) {
        String sql = "UPDATE instalacion SET estado=false WHERE id_instalacion=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Instalacion dada de baja");
            } else {
                System.out.println("No se encuentra la Instalacion con ID " + id);
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

    public void altaInstalacion(int id) {
        String sql = "UPDATE instalacion SET estado=true WHERE id_instalacion=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Instalacion dada de alta");
            } else {
                System.out.println("No se encuentra la instalacion con ID " + id);
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

    public void borrarInstalacion(int id) {
        String sql = "DELETE FROM instalacion WHERE id_instalacion=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("instalacion borrada de la DB");
            } else {
                System.out.println("No se encuentra la instalacion con ID " + id);
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

    public ArrayList<Instalacion> obtenerInstalaciones() {
        ArrayList<Instalacion> listaDeInstalacion = new ArrayList<>();
        String sql = "Select * FROM instalacion";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idInstalacion = rs.getInt("id_instalacion");
                String nombrea = rs.getString("nombre");
                String detallesuso = rs.getString("detalle_de_uso");
                double precioa = rs.getDouble("precio_hr");
                boolean estado = rs.getBoolean("estado");
                Instalacion instalacion = new Instalacion(nombrea, detallesuso, precioa);
                instalacion.setEstado(estado);
                instalacion.setIdInstalacion(idInstalacion);
                listaDeInstalacion.add(instalacion);
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
        return listaDeInstalacion;
    }
}
