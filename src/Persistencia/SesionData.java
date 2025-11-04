
package Persistencia;

//solo mia

import org.mariadb.jdbc.Connection;
import java.sql.*;
import java.time.LocalDate;


public class SesionData {
  private Connection con = null;
  private int codSesion=-1;
  private LocalDate fechainicio;
  private LocalDate fechafinal;
  private TratamientoData tratamiento;
  private ConsultorioData consultorio;
  private MasajistaData masajista;
  private DiadespaData diadespa;

    public SesionData(LocalDate fechainicio, LocalDate fechafinal ,Conexion con) {
        this.fechainicio = fechainicio;
        this.fechafinal = fechafinal;
        this.con = (Connection) con.establecerConexion();
        this.tratamiento=new TratamientoData();
        this.consultorio=new ConsultorioData(con);
        this.masajista=new MasajistaData(con);
        //this.diadespa=new DiadespaData(con);
        
    }
    
    public void insertarSesion( e) {
        String query = "INSERT INTO masajista(matricula,nombre_completo,telefono,especialidad,estado) VALUES(?,?,?,?,?) ";
        try {
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
        }
    } 
  
    
}
