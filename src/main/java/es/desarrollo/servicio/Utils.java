package es.desarrollo.servicio;

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
}
