package Persistencia;

import Modelo.Sesion;
import Modelo.Instalacion;
import Modelo.Consultorio;
import Modelo.Masajista;
import Modelo.Tratamiento;
import Modelo.DiaDeSpa;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SesionData {

    private TratamientoData tratamiento;
    private ConsultorioData consultorio;
    private MasajistaData masajista;
    private DiadespaData diadespa;
    private InstalacionData instalacionData;

    public SesionData() {
        this.diadespa = null;
        this.tratamiento = new TratamientoData();
        this.consultorio = new ConsultorioData();
        this.masajista = new MasajistaData();
        this.instalacionData = new InstalacionData();
    }

    public void insertarSesion(Sesion s) {
        String sql = "INSERT INTO sesion (id_consultorio, id_tratamiento,id_pack,instalaciones,matricula, fecha_hora_inicio, fecha_hora_fin, estado) VALUES (?,?,?,?,?,?,?,?)";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, s.getCodConsultorio());
            ps.setInt(2, s.getCodTratamiento());
            ps.setNull(3, java.sql.Types.INTEGER);
            String listaInstalacion = String.join(",", s.getNombresInstalacion());
            ps.setString(4, listaInstalacion);
            ps.setInt(5, s.getMatricula());
            ps.setTimestamp(6, Timestamp.valueOf(s.getFechaHoraInicio()));
            ps.setTimestamp(7, Timestamp.valueOf(s.getFechaHoraFin()));
            ps.setBoolean(8, s.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                s.setCodSesion(rs.getInt(1));
            }
            System.out.println("Sesión guardada correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void borrarSesion(int idSesion) {
        String sql = "DELETE FROM sesion WHERE id_sesion = ?";
        Connection con = null;

        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSesion);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Sesión eliminada correctamente");
            } else {
                System.out.println("No se encontró sesión con ID: " + idSesion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void ActualizarSesiones(int id, Sesion s) {
        String sql = "UPDATE sesion SET id_consultorio=?, id_tratamiento=?,id_pack=?,instalaciones=?,matricula=?, fecha_hora_inicio=?, fecha_hora_fin=?, estado=? WHERE id_sesion=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, s.getCodConsultorio());
            ps.setInt(2, s.getCodTratamiento());
            ps.setInt(3, s.getCodPack());
            String listaInstalacion = String.join(",", s.getNombresInstalacion());
            ps.setString(4, listaInstalacion);
            ps.setInt(5, s.getMatricula());
            ps.setTimestamp(6, Timestamp.valueOf(s.getFechaHoraInicio()));
            ps.setTimestamp(7, Timestamp.valueOf(s.getFechaHoraFin()));
            ps.setBoolean(8, s.isEstado());
            ps.setInt(9, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Sesion actualizada correctamente");
            } else {
                System.out.println("No se encuentra la Sesion con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Sesion> obtenerSesiones() {
        ArrayList<Sesion> listaDeSesiones = new ArrayList<>();
        String sql = "Select * FROM sesion";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (this.diadespa == null) {
                this.diadespa = new DiadespaData();
            }
            while (rs.next()) {
                int codSesion = rs.getInt("id_sesion");
                int codConsultorio = rs.getInt("id_consultorio");
                Consultorio c = consultorio.buscarConsultorio(codConsultorio);
                int idTratamiento = rs.getInt("id_tratamiento");
                Tratamiento t = tratamiento.buscarTratamiento(idTratamiento);
                int idSpa = rs.getInt("id_pack");
                DiaDeSpa i = new DiaDeSpa();
                i.setCodPack(idSpa);
                ArrayList<Instalacion> listaInstalaciones = new ArrayList<>();
                String instalaciones = rs.getString("instalaciones");
                String[] partes = instalaciones.split(",");
                for (String nombre : partes) {
                    Instalacion a = instalacionData.buscarInstalacionNombre(nombre.trim());
                    if (a != null) {
                        listaInstalaciones.add(a);
                    } else {
                        System.out.println("AVISO: La instalación '" + nombre + "' figura en la sesión pero no existe en la tabla de instalaciones.");
                    }
                }
                int matricula = rs.getInt("matricula");
                Masajista m = masajista.buscarMasajista(matricula);
                LocalDateTime fechaInicio = rs.getTimestamp("fecha_hora_inicio").toLocalDateTime();
                LocalDateTime fechaFinal = rs.getTimestamp("fecha_hora_fin").toLocalDateTime();
                boolean estado = rs.getBoolean("estado");

                Sesion sesion = new Sesion(c, t, listaInstalaciones, m, fechaInicio, fechaFinal, estado);
                sesion.setCodSesion(codSesion);
                sesion.setCodConsultorio(codConsultorio);
                sesion.setDiadespa(i);
                listaDeSesiones.add(sesion);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return listaDeSesiones;
    }

    public void bajaSesion(int id) {
        String sql = "UPDATE sesion SET estado=false WHERE id_sesion=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Sesion dada de baja");
            } else {
                System.out.println("No se encuentra la Sesion con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void altaSesion(int id) {
        String sql = "UPDATE sesion SET estado=true WHERE id_sesion=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Sesion dada de alta");
            } else {
                System.out.println("No se encuentra la Sesion con ID " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Sesion buscarSesion(int idSesion) {
        Sesion a = null;
        String sql = "SELECT * FROM sesion WHERE id_sesion=?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSesion);
            ResultSet rs = ps.executeQuery();
            if (this.diadespa == null) {
                this.diadespa = new DiadespaData();
            }
            if (rs.next()) {
                ArrayList<Instalacion> listaInstalaciones = new ArrayList<>();
                String instalaciones = rs.getString("instalaciones");
                
                if (instalaciones != null && !instalaciones.isEmpty()) {
                    String[] partes = instalaciones.split(",");
                    for (String nombre : partes) {
                        
                        Instalacion b = instalacionData.buscarInstalacionNombre(nombre.trim());
                        
                        if (b != null) {
                            listaInstalaciones.add(b);
                        }
                    }
                }
                Consultorio consul = consultorio.buscarConsultorio(rs.getInt("id_consultorio"));
                int idSpa = rs.getInt("id_pack");
                DiaDeSpa diaspa = new DiaDeSpa();
                diaspa.setCodPack(idSpa);
                Tratamiento trata = tratamiento.buscarTratamiento(rs.getInt("id_tratamiento"));
                Masajista masa = masajista.buscarMasajista(rs.getInt("matricula"));
                LocalDateTime fechaInicio = rs.getTimestamp("fecha_hora_inicio").toLocalDateTime();
                LocalDateTime fechaFinal = rs.getTimestamp("fecha_hora_fin").toLocalDateTime();
                a = new Sesion(
                        consul,
                        trata,
                        listaInstalaciones,
                        masa,
                        fechaInicio,
                        fechaFinal,
                        rs.getBoolean("estado")
                );
                a.setCodSesion(rs.getInt("id_sesion"));
                a.setDiadespa(diaspa);
                System.out.println("Encontrado: " + a.getCodSesion());
            } else {
                System.out.println("No se encontró el Id de la Sesion " + idSesion);
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

    public ArrayList<Integer> obtenerMasajistasOcupados(LocalDateTime inicio, LocalDateTime fin) {
        ArrayList<Integer> ocupados = new ArrayList<>();
        String sql = "SELECT matricula FROM sesion WHERE fecha_hora_inicio < ? AND fecha_hora_fin > ?";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(fin));
            ps.setTimestamp(2, Timestamp.valueOf(inicio));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ocupados.add(rs.getInt("matricula"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
        return ocupados;
    }

    public ArrayList<Masajista> obtenerMasajistasLibres(LocalDateTime inicio, LocalDateTime fin) {
        ArrayList<Integer> ocupados = this.obtenerMasajistasOcupados(inicio, fin);
        ArrayList<Masajista> libres = new ArrayList<>();
        String sql = "SELECT * FROM masajista WHERE estado = 1";
        Connection con = null;
        try {
            con = Conexion.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int mat = rs.getInt("matricula");

                if (!ocupados.contains(mat)) {
                    Masajista m = new Masajista(
                            mat,
                            rs.getString("nombre_completo"),
                            rs.getLong("telefono"),
                            rs.getString("especialidad")
                            
                    );
                    m.setEstado(rs.getBoolean("estado"));
                    libres.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        }
        return libres;
    }
    
}
