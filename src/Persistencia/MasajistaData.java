
package Persistencia;

import Modelo.Cliente;
import Modelo.Masajista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MasajistaData {
   
 private Connection con = null;

    public MasajistaData(Conexion con) {
      this.con = con.establecerConexion();
    }
 public void insertarMasajista(Masajista e) {
        String query = "INSERT INTO masajista(matricula,nombre_completo,telefono,especialidad,estado) VALUES(?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, e.getMatricula());
            ps.setString(2, e.getNombreCompleto());
            ps.setInt(3, e.getTelefono());
            ps.setString(4, e.getEspecialidad());
            ps.setBoolean(5, e.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setMatricula(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener la matricula ");
            }

            ps.close();
            System.out.println("Masajista guardado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   

    public Masajista buscarMasajista(int matricula) {
        Masajista a = null;
        String sql = "SELECT * FROM masajista WHERE matricula=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matricula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                a = new Masajista(
                        rs.getInt("matricula"),
                        rs.getString("nombre_completo"),
                        rs.getInt("telefono"),
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
        }
        return a;
    }

    public void actualizarMasajista(int matricula,int parseInt, String nombrecompletonuevo, int telefononuevo, String especialidadnuevo, boolean estadonuevo) {
        String sql = "UPDATE masajista SET  nombre_completo=?,telefono=?,especialidad=?,estado=? WHERE matricula=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombrecompletonuevo);
            ps.setInt(2, telefononuevo);
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
        }
    }

    public void bajaMasajista(int matricula) {
        String sql = "UPDATE masajista SET estado=false WHERE matricula=?";
        try {
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
        }
    }

    public void altaMasajista(int matricula) {
        String sql = "UPDATE masajista SET estado=true WHERE matricula=?";
        try {
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
        }
    }

    public void borrarMasajista(int matricula) {
        String sql = "DELETE FROM masajista WHERE matricula=?";
        try {
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
        }
    }

    public  ArrayList<Masajista> obtenerMasajista() {
        ArrayList<Masajista> listaDeMasajistas = new ArrayList<>();
        String sql = "Select * FROM masajista";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int matricula = rs.getInt("matricula");
                String nombrecompleto = rs.getString("nombre_completo");
                int telefono = rs.getInt("telefono");
                String especialidad=rs.getString("especialidad");
                boolean estado = rs.getBoolean("estado");

                Masajista masajista = new Masajista( matricula,  nombrecompleto,  telefono,  especialidad);
                masajista.setMatricula(matricula);
                masajista.setEstado(estado);
                listaDeMasajistas.add(masajista);
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaDeMasajistas;

    }   
 
}
