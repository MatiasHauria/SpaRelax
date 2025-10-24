/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;


import Modelo.Consultorio;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import java.sql.*;

public class ConsultorioData {

    private Connection con = null;

    public ConsultorioData(Conexion con) {
        this.con = (Connection) con.establecerConexion();
    }
    
    public void insertarConsultorio(Consultorio e){
        String query =  "INSERT INTO consultorio(id_consultorio,usos,equipamento,apto) VALUES(?,?,?,?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, e.getNroConsultorio());
            ps.setString(2, e.getUsos());
            ps.setString(3, e.getEquipamiento());
            ps.setBoolean(4, e.isApto());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                e.setNroConsultorio(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el ID");
            }
            
            ps.close();
            System.out.println("Consultorio guardado con exito");
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void mostrarTodos(){
        String sql = "SELECT * FROM cliente";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Consultorio a = new Consultorio(
                        rs.getInt("id_consultorio"),
                        rs.getString("usos"),
                        rs.getString("equipamento"));
                a.setNroConsultorio(rs.getInt("id_consultorio"));
                System.out.println("ID: " + a.getNroConsultorio()+
                        " | Equipamento: " + a.getEquipamiento()+
                        " | Usos: " + a.getUsos()+ ", " +
                        " | Apto: " + (a.isApto()? "Activo" : "Inactivo"));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Consultorio buscarConsultorio(int id_consultorio) {
        Consultorio a = null;
        String sql = "SELECT * FROM consultorio WHERE id_consultorio=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_consultorio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Consultorio(
                        rs.getInt("id_consultorio"),
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
        }
        return a;
    }
    
     public void actualizarConsultorio(int idNuevo, int parseInt, String usoNuevo, String equipoNuevo, boolean aptoNuevo) {
        String sql = "UPDATE Consultorio SET id_consultorio=?,usos=?,equipamento=?,apto=? WHERE id_consultorio=?";
        try {
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
        }
    }
   
   public void bajaConsultorio(int id_consultorio) {
        String sql = "UPDATE Consultorio SET apto=false WHERE id_consultorio=?";
        try {
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
        }
    }

    public void altaConsultorio(int id_consultorio) {
        String sql = "UPDATE consultorio SET apto=true WHERE id_consultorio=?";
        try {
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
        }
    }
    public void borrarConsultorio(int id_consultorio) {
        String sql = "DELETE FROM consultorio WHERE id_consultorio=?";
        try {
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
        }
    }
    
    public ArrayList<Consultorio> obtenerConsultorio(){
      ArrayList<Consultorio> listaDeConsultorios=new ArrayList<>();
      String sql="Select * FROM consultorio";
      try{
       PreparedStatement ps = con.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
           int idCons=rs.getInt("id_consultorio");
           String usos =rs.getString("usos");
           String equipo=rs.getString("equipamento");
           boolean apto =rs.getBoolean("apto");
           
           Consultorio consultorio = new Consultorio( idCons, usos, equipo);
           consultorio.setNroConsultorio(idCons);
           consultorio.setUsos(usos);
           consultorio.setEquipamiento(equipo);
           consultorio.setApto(apto);
           listaDeConsultorios.add(consultorio);
       }
        
         
         
         ps.close();
      }catch(SQLException ex){
        ex.printStackTrace();
      }
        return listaDeConsultorios;
      
  }
    
}
