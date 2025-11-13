package Modelo;

import Persistencia.ClienteData;
import Persistencia.Conexion;
import Persistencia.ConsultorioData;
import Persistencia.DiadespaData;
import Persistencia.InstalacionData;
import Persistencia.MasajistaData;
import Persistencia.SesionData;
import Persistencia.TratamientoData;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.*;
import java.util.List;

public class MainPrueba {

    public static void main(String[] args) {
        try {
        Conexion conexion = new Conexion();
        SesionData sesData = new SesionData(conexion);
        DiadespaData diaData = new DiadespaData(conexion);
        ClienteData cliData = new ClienteData(conexion);
        ConsultorioData consuData = new ConsultorioData(conexion);
        InstalacionData instData = new InstalacionData(conexion);
        MasajistaData masData = new MasajistaData(conexion);
        TratamientoData tratData = new TratamientoData();
        
        
        Cliente c = new Cliente(4580291, "pruebita", 295812411, 23, "Prueba");
        cliData.insertarCliente(c);
        Consultorio cons = new Consultorio("Maquinas", "Masajes");
        consuData.insertarConsultorio(cons);
        Instalacion inst1 = new Instalacion("Ave fenix", "Masajes", 20);
        Instalacion inst2 = new Instalacion("Dannita code", "Masajes", 20);
        ArrayList<Instalacion> inst = new ArrayList<>();
        inst.add(inst2);
        inst.add(inst1);
        instData.insertarInstalacion(inst1);
        instData.insertarInstalacion(inst2);
        Masajista mas = new Masajista(24512, "AgusDIaz", 26471284, "Facial");
        masData.insertarMasajista(mas);
        String prod1="Crema";
        String prod2 ="Jabon";
        List<String> lista = new ArrayList<>();
        lista.add(prod2);
        lista.add(prod1);
        Tratamiento trat = new Tratamiento("TratFAcial", "Masajes dos dedos",lista,2,200);
        Tratamiento trat2 = new Tratamiento("Tratcorp", "Masajes dos dedos",lista,2,200);
        tratData.agregarTratamiento(trat);
        tratData.agregarTratamiento(trat2);
        Sesion s = new Sesion(cons,trat,inst,mas,LocalDateTime.now(), LocalDateTime.now(), false);
        Sesion s2 = new Sesion(cons,trat2,inst,mas,LocalDateTime.now(), LocalDateTime.now(), true);
        sesData.insertarSesion(s);
        sesData.insertarSesion(s2);
        ArrayList<Sesion> listaS = new ArrayList<>();
        listaS.add(s);
        listaS.add(s2);
        DiaDeSpa dia = new DiaDeSpa(c, listaS, LocalDateTime.now(),"Holi",200);
        
        
       
     

        diaData.generarDiaDeSpa(dia);
        
//        } catch (SQLIntegrityConstraintViolationException e) {
//            System.err.println("error de integridad sql: " + e.getMessage());
//            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
}
