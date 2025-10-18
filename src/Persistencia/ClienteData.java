/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Cliente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import java.sql.*;

/**
 *
 * @author matute
 */
public class ClienteData {
    private Connection con = null;

    public ClienteData(Conexion con) {
        this.con = (Connection) con.establecerConexion();
    }
    public void insertarCliente(Cliente e) {
        String query = "INSERT INTO cliente(dni,nombre_completo,telefono,edad,afecciones,estado) VALUES(?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, e.getDni());
            ps.setString(2, e.getNombreCompleto());
            ps.setInt(3, e.getTelefono());
            ps.setInt(4, e.getEdad());
            ps.setString(5, e.getAfecciones());
            ps.setBoolean(6, e.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setCodCli(rs.getInt(1));
            } else {
                System.out.println("No se pudo tener el ID ");
            }

            ps.close();
            System.out.println("Cliente guardado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void mostrarTodos(){
        String sql = "SELECT * FROM cliente";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente a = new Cliente(
                        rs.getInt("dni"),
                        rs.getString("nombre_completo"),
                        rs.getInt("telefono"),
                        rs.getInt("edad"),
                        rs.getString("afecciones")
                );
                a.setCodCli(rs.getInt("id_cliente"));
                System.out.println("ID: " + a.getCodCli()+
                        " | DNI: " + a.getDni() +
                        " | " + a.getNombreCompleto()+ ", " + a.getTelefono()+
                        " | Afecciones: " + a.getAfecciones()+
                        " | Estado: " + (a.isEstado() ? "Activo" : "Inactivo"));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Cliente buscarCliente(int id) {
        Cliente a = null;
        String sql = "SELECT * FROM cliente WHERE id_cliente=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Cliente(
                        rs.getInt("dni"),
                        rs.getString("nombre_completo"),
                        rs.getInt("telefono"),
                        rs.getInt("edad"),
                        rs.getString("afecciones")
                );
                a.setCodCli(rs.getInt("id_cliente"));
                System.out.println("Encontrado: " + a.getNombreCompleto());
            } else {
                System.out.println("No se encontró el alumno con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }
    
   public void actualizarCliente(int id,int dniNuevo, String nuevoNombre,int telefonoNuevo,int edadNueva, String afeccionesNuevo) {
        String sql = "UPDATE Cliente SET dni=?, nombre_completo=?,telefono=?,edad=?,afecciones=? WHERE id_cliente=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dniNuevo);
            ps.setString(2, nuevoNombre);
            ps.setInt(3, telefonoNuevo);
            ps.setInt(4, edadNueva);
            ps.setString(5, afeccionesNuevo);
            ps.setInt(6, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Cliente actualizado correctamente");
            } else {
                System.out.println("No se encuentra el Cliente con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
   public void bajaCliente(int id) {
        String sql = "UPDATE Cliente SET estado=false WHERE id_cliente=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Cliente dado de baja");
            } else {
                System.out.println("No se encuentra el Cliente con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void altaCliente(int id) {
        String sql = "UPDATE cliente SET estado=true WHERE id_cliente=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Cliente dado de alta");
            } else {
                System.out.println("No se encuentra el Cliente con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void borrarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Cliente borrado de la DB");
            } else {
                System.out.println("No se encuentra el Cliente con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Cliente> obtenerClientes(){
      ArrayList<Cliente> listaDeClientes=new ArrayList<>();
      String sql="Select * FROM cliente";
      try{
       PreparedStatement ps = con.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
           int codCli=rs.getInt("id_cliente");
           int dni=rs.getInt("dni");
           String nombreCompleto =rs.getString("nombre_completo");
           int telefono=rs.getInt("telefono");
           int edad=rs.getInt("edad");
           String afecciones=rs.getString("afecciones");
           boolean estado =rs.getBoolean("estado");
           
           Cliente cliente = new Cliente( dni,  nombreCompleto,  telefono,  edad,  afecciones);
           cliente.setCodCli(codCli);
           cliente.setEstado(estado);
           listaDeClientes.add(cliente);
       }
        
         
         
         ps.close();
      }catch(SQLException ex){
        ex.printStackTrace();
      }
        return listaDeClientes;
      
  }
}
