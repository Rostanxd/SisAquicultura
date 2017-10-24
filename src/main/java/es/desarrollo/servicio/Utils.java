package es.desarrollo.servicio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rostan on 15/08/2017.
 */
public class Utils {
    /**
     * Metodo que devuelvo el mes en número, para uso del framework de primefaces (ComboBoxes)
     * @param mesString es el mes en String
     */
    public static int mesInt(String mesString){
        int mes = 0;
        switch (mesString){
            case "Enero":
                mes = 1;
                break;
            case "Febrero":
                mes = 2;
                break;
            case "Marzo":
                mes = 3;
                break;
            case "Abril":
                mes = 4;
                break;
            case "Mayo":
                mes = 5;
                break;
            case "Junio":
                mes = 6;
                break;
            case "Julio":
                mes = 7;
                break;
            case "Agosto":
                mes = 8;
                break;
            case "Septiembre":
                mes = 9;
                break;
            case "Octubre":
                mes = 10;
                break;
            case "Noviembre":
                mes = 11;
                break;
            case "Diciembre":
                mes = 12;
                break;
        }
        return mes;
    }

    public static String mesString(int numMes){
        String mes = "";
        switch (numMes){
            case 1:
                mes = "Enero";
                break;
            case 2:
                mes = "Febrero";
                break;
            case 3:
                mes = "Marzo";
                break;
            case 4:
                mes = "Abril";
                break;
            case 5:
                mes = "Mayo";
                break;
            case 6:
                mes = "Junio";
                break;
            case 7:
                mes = "Julio";
                break;
            case 8:
                mes = "Agosto";
                break;
            case 9:
                mes = "Septiembre";
                break;
            case 10:
                mes = "Octubre";
                break;
            case 11:
                mes = "Noviembre";
                break;
            case 12:
                mes = "Diciembre";
                break;
        }
        return mes;
    }

    public static String estadoString(String estadoLetra){
        String estadoString = "";
        switch (estadoLetra){
            case "E":
                estadoString = "Pendiente";
                break;
            case "A":
                estadoString = "Activo";
                break;
            case "R":
                estadoString = "Revisado";
                break;
        }
        return estadoString;
    }

    public static String estadoLetra(String estadoString){
        String estadoLetra = "";
        switch (estadoString){
            case "Pendiente":
                estadoString = "E";
                break;
            case "Activo":
                estadoString = "A";
                break;
            case "Revisado":
                estadoString = "R";
                break;
        }
        return estadoString;
    }

    public static String dateFormat(Date date){
        String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return newstring;

    }

    public static Map<Integer, String> listarMeses(){
        Map<Integer, String> listMeses;
        listMeses = new HashMap<Integer, String>();
        listMeses.put(1, "Enero");
        listMeses.put(2,"Febrero");
        listMeses.put(3,"Marzo");
        listMeses.put(4,"Abril");
        listMeses.put(5,"Mayo");
        listMeses.put(6,"Junio");
        listMeses.put(7,"Julio");
        listMeses.put(8,"Agosto");
        listMeses.put(9,"Septiembre");
        listMeses.put(10,"Octubre");
        listMeses.put(11,"Noviembre");
        listMeses.put(12,"Diciembre");
        return listMeses;
    }

    public static Map<String, String> listarEstado(){
        Map<String, String> listEstados;
        listEstados = new HashMap<String, String>();
        listEstados.put("E", "Pendiente");
        listEstados.put("A", "Activo");
        listEstados.put("R", "Revisado");
        return listEstados;
    }
}
