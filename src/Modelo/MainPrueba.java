package Modelo;

import java.util.Arrays;

public class MainPrueba {

    public static void main(String[] args) {
        /*
        if (rs.next()) {
                String sesiones = rs.getString("sesiones");
                String[] arraySesiones = sesiones.split(", ");
                List<Sesion> listaSesiones = new ArrayList<>();
                for (String aux: arraySesiones) {
                    int value = Integer.parseInt(aux);
                    //Aca tendriamos que conseguir cada codigo sin la coma
                    //Aca lo parseamos a Entero 
                    Sesion sesion = this.sesion.buscarSesion(Aca le dariamos el codigo ya parseado);
                    listaSesiones.add(sesion);
                }
        */
        String sesiones = "1, 2, 3, 4, 5, 6";
        String[] arraySesiones = sesiones.split("\\s*,\\s*");
        int[] codigosFormatoInt = new int[arraySesiones.length];
        for (int i = 0; i < arraySesiones.length; i++) {
            codigosFormatoInt[i] = Integer.parseInt(arraySesiones[i]);
        }
        
        
    }
    
}
