package Persistencia;

import Modelo.Tratamiento;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class TratamientoData {
 
    
    
    
    public void agregarTratamiento(Tratamiento tratamiento) {
        Connection conexion = null;
        try {
            conexion = Conexion.establecerConexion();
            String query = "INSERT INTO tratamiento (nombre, detalle, productos, duracion, costo, activo) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tratamiento.getNombre());
            ps.setString(2, tratamiento.getDetalle());
            String listaProductos = String.join(",", tratamiento.getProductos());
            ps.setString(3, listaProductos);
            ps.setInt(4, tratamiento.getDuracion());
            ps.setDouble(5, tratamiento.getCosto());
            ps.setBoolean(6, tratamiento.isActivo());
            int filas = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                tratamiento.setIdTratamiento(rs.getInt(1));
            } else {
                System.out.println("No se logro obtener el ID del tratamiento.");
            }
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Tratamiento agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Se produjo un error con agregar el tratamiento.");
            }
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo procesar la consulta.");
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
    
    public List<Tratamiento> mostrarTratamientos() {
        Connection conexion = null;
        List<Tratamiento> listaTratamientos = new ArrayList<>();
        try {
            conexion = Conexion.establecerConexion();
            String query = "SELECT * FROM tratamiento";
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String productos = rs.getString("productos");
                String[] arrayProductos = productos.split(",");
                List<String> listaProductos = Arrays.asList(arrayProductos);
                Tratamiento tratamiento = new Tratamiento(
                        rs.getString("nombre"),
                        rs.getString("detalle"),
                        listaProductos,
                        rs.getInt("duracion"),
                        rs.getDouble("costo")
                );
                tratamiento.setIdTratamiento(rs.getInt("id_tratamiento"));
                tratamiento.setActivo(rs.getBoolean("activo"));
                listaTratamientos.add(tratamiento);
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Error: No se pudo procesar la consulta. Verificar si hay una conexion a la base de datos establecida.");
            e.getMessage();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
        return listaTratamientos;
    }
    
    public void actualizarTratamiento(int idTratamiento, String nombre, String detalle, String productos, int duracion, double costo) {
        Connection conexion = null;
        try {
            conexion = Conexion.establecerConexion();
            String query = "UPDATE tratamiento SET nombre=?, detalle=?, productos=?, duracion=?, costo=? WHERE id_tratamiento=?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, detalle);
            ps.setString(3, productos);
            ps.setInt(4, duracion);
            ps.setDouble(5, costo);
            ps.setInt(6, idTratamiento);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Tratamiento actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El ID ingresado no apunta a ningun tratamiento.");
            }
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo realizar la consulta.");
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
    
    public void bajaLogica(int id) {
        Connection conexion = null;
        try {
            conexion = Conexion.establecerConexion();
            String query = "UPDATE tratamiento SET activo=0 WHERE id_tratamiento=?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Tratamiento dado de baja exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No existe un tratamiento con ese ID.");
            }
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "No se pudo procesar la consulta.");
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
    
    public void altaLogica(int id) {
        Connection conexion = null;
        try {
            conexion = Conexion.establecerConexion();
            String query = "UPDATE tratamiento SET activo=1 WHERE id_tratamiento=?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Tratamiento dado de alta exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No existe un tratamiento con ese ID.");
            }
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "No se pudo procesar la consulta.");
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
    
    public void borrarTratamiento(int id) {
        Connection conexion = null;
        try {
            conexion = Conexion.establecerConexion();
            String query = "DELETE FROM tratamiento WHERE id_tratamiento=?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Tratamiento borrado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un tratamiento con ese ID.");
            }
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "No se pudo procesar la consulta.");
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
    
    public Tratamiento buscarTratamiento(int id) {
        Tratamiento tratamiento = null;
        Connection conexion = null;
        String query = "SELECT * FROM tratamiento WHERE id_tratamiento=?";
        try {
            conexion = Conexion.establecerConexion();
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String productos = rs.getString("productos");
                String[] arrayProductos = productos.split(",");
                List<String> listaProductos = Arrays.asList(arrayProductos);
                tratamiento = new Tratamiento(
                        rs.getString("nombre"),
                        rs.getString("detalle"),
                        listaProductos,
                        rs.getInt("duracion"),
                        rs.getDouble("costo")
                );
                tratamiento.setIdTratamiento(rs.getInt("id_tratamiento"));
                tratamiento.setActivo(rs.getBoolean("activo"));
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
        return tratamiento;
    }
}
