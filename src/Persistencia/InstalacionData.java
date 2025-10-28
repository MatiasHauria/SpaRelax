/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import Modelo.Instalacion;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author matute
 */
public class InstalacionData {
    private Connection con = null;

    public InstalacionData(Conexion con2) {
        this.con = (Connection) con2.establecerConexion();
    }
    public void insertarInstalacion(Instalacion e) {
        String query = "INSERT INTO instalacion(nombre,detalle_de_uso,precio_hr,estado) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDetalleUso());
            ps.setDouble(3, e.getPrecioPorHora());
            ps.setBoolean(4, e.isEstado());

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
        }
    }

    public Instalacion buscarInstalacion(int id) {
        Instalacion a = null;
        String sql = "SELECT * FROM instalacion WHERE id_instalacion=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Instalacion(
                        rs.getString("nombre"),
                        rs.getString("detalle_de_uso"),
                        rs.getInt("precio_hr"),
                        rs.getBoolean("estado")
                );
                a.setIdInstalacion(rs.getInt("id_instalacion"));
                System.out.println("Encontrado: " + a.getNombre());
            } else {
                System.out.println("No se encontró la instalacion con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    public void actualizarInstalacion(int id, String nuevoNombre, String nuevosDetalles, double nuevoPrecio) {
        String sql = "UPDATE instalacion SET nombre=?,detalle_de_uso=?,precio_hr=? WHERE id_instalacion=?";
        try {
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
        }
    }

    public void bajaCliente(int id) {
        String sql = "UPDATE instalacion SET estado=false WHERE id_instalacion=?";
        try {
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
        }
    }

    public void altaCliente(int id) {
        String sql = "UPDATE instalacion SET estado=true WHERE id_instalacion=?";
        try {
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
        }
    }

    public void borrarCliente(int id) {
        String sql = "DELETE FROM instalacion WHERE id_instalacion=?";
        try {
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
        }
    }

    public ArrayList<Instalacion> obtenerClientes() {
        ArrayList<Instalacion> listaDeClientes = new ArrayList<>();
        String sql = "Select * FROM instalacion";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idInstalacion = rs.getInt("id_instalacion");
                String nombrea = rs.getString("nombre");
                String detallesuso = rs.getString("detalles_de_uso");
                double precioa = rs.getDouble("precio_hr");
                int edad = rs.getInt("edad");
                boolean estado = rs.getBoolean("estado");

                Instalacion instalacion = new Instalacion(nombrea, detallesuso, precioa, estado);
                instalacion.setIdInstalacion(idInstalacion);
                listaDeClientes.add(instalacion);
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaDeClientes;

    }
    
    
    
    
    
    
    
}
