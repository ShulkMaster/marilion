/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserMax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import marilion.Reservacion;

/**
 *
 * @author yury_
 */
public class FechaX {

    private static final SimpleDateFormat STANDARFORMAT = new SimpleDateFormat("d#M#yyyy");

    public static String paser(String param) {
        String provisional;
        String fechaActual;
        try {
            fechaActual = STANDARFORMAT.format(new Date());
            if (STANDARFORMAT.parse(param).compareTo(STANDARFORMAT.parse(fechaActual)) < 0) {
                provisional = STANDARFORMAT.format(new Date());
                System.err.println("No se pueden asignar fechas anteriores");
                System.out.println("fecha asignada: " + provisional);
            } else {
                provisional = param;
            }
            return provisional;
        } catch (ParseException ex) {
            Logger.getLogger(FechaX.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al procesar la fecha: Fecha de la reserva es hoy");
            return STANDARFORMAT.format(new Date());
        }
    }

    public static boolean doMatch(String fechaIni, String days1, ArrayList<Reservacion> problematica) {
        try {
            Date fechaProhibida1 = STANDARFORMAT.parse(days1);
            for(Reservacion problem : problematica){
                
            
            
            }
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(FechaX.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }        
    }

    public static String fechaEjemplo() {
        return STANDARFORMAT.format(new Date());
    }
}
