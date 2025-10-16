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
import org.mariadb.jdbc.Connection;

/**
 *
 * @author matute
 */
public class ClienteData {
    private Connection con = null;

    public ClienteData(Conexion a) {
        this con = a.BuscarConexion();
    }
    public void insertarAlumno(Cliente e) {
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
            System.out.println("Alumno guardado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
}
