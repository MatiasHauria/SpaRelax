
package Persistencia;

//solo mia

import Modelo.Sesion;
import Modelo.Instalacion;
import Modelo.Consultorio;
import Modelo.Masajista;
import Modelo.Tratamiento;
import Modelo.DiaDeSpa;
import org.mariadb.jdbc.Connection;
import java.sql.*;
import java.time.LocalDate;


public class SesionData {
  private Connection con = null;
  private TratamientoData tratamiento;
  private ConsultorioData consultorio;
  private MasajistaData masajista;
  private DiadespaData diadespa;

    public SesionData(Conexion con) {
        
        this.con = (Connection) con.establecerConexion();
        this.tratamiento=new TratamientoData();
        this.consultorio=new ConsultorioData(con);
        this.masajista=new MasajistaData(con);
        //this.diadespa=new DiadespaData(con);
        
    }
    
    public void insertarSesion(Sesion s) {
    String sql = "INSERT INTO sesion (id_consultorio, id_tratamiento,id_pack,instalaciones,matricula, fecha_hora_inicio, fecha_hora_fin, estado) "
               + "VALUES (?,?,?,?,?,?,?,?)";

    try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      
        ps.setInt(1,s.getCodConsultorio());
        ps.setInt(2, s.getCodTratamiento());
        ps.setInt(3,s.getCodPack());
        String listaInstalacion=String.join(",",s.getNombresInstalacion());
        ps.setString(4, listaInstalacion);
        ps.setInt(5, s.getMatricula());
        ps.setTimestamp(6,Timestamp.valueOf(s.getFechaHoraInicio()));
        ps.setTimestamp(7,Timestamp.valueOf(s.getFechaHoraFin()));
        ps.setBoolean(8, s.isEstado());

        ps.executeUpdate();


        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            s.setCodSesion(rs.getInt(1));
        }

        System.out.println("Sesión guardada correctamente");

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
    
   public void borrarSesion(int idSesion){
    String sql = "DELETE FROM sesion WHERE id_sesion = ?";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idSesion);

        int filas = ps.executeUpdate();
        if (filas > 0) {
            System.out.println("Sesión eliminada correctamente");
        } else {
            System.out.println("No se encontró sesión con ID: " + idSesion);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
   
  public void ActualizarSesiones(int id){
      String sql = "UPDATE sesion SETid_consultorio=?, id_tratamiento=?,id_pack=?,instalaciones=?,matricula=?, fecha_hora_inicio=?, fecha_hora_fin=?, estado=? WHERE id_sesion=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, notanueva);
            ps.setInt(2, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Nota actualizada correctamente");
            } else {
                System.out.println("No se encuentra la Nota con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
  }
  
    
}
