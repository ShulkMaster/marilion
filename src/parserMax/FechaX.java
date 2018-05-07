/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserMax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private static final Calendar CALENTAR = Calendar.getInstance();

    public static String paser(String param) {
        String provisional = fechaEjemplo();
        try {
            if (STANDARFORMAT.parse(param).compareTo(STANDARFORMAT.parse(provisional)) < 0) {
                System.err.println("No se pueden asignar fechas anteriores");
                System.out.println("fecha asignada: " + provisional);
                return provisional;
            }
            provisional = param;
            return provisional;
        } catch (ParseException ex) {
            System.err.println("Error al procesar la fecha: Fecha de la reserva es hoy");
            return provisional;
        }
    }

    public static boolean donotMatch(String fechaDeseada, int days1, ArrayList<Reservacion> problematica) {
        try {
            Date fechaReserv = STANDARFORMAT.parse(fechaDeseada);
            Date fechaReservFin = getFechafinal(fechaReserv, days1);
            for (Reservacion problem : problematica) {
                Date fechaRev2 = STANDARFORMAT.parse(problem.fechaIni);
                Date fechaRevFin2 = getFechafinal(fechaRev2, problem.dias);
                System.out.println(fechaDeseada+" "+fechaReservFin+" "+fechaRev2+""+fechaRevFin2);
                long mayor = getmayor(fechaReserv, fechaReservFin, fechaRev2, fechaRevFin2);
                long menor = getmenor(fechaReserv.getTime(), fechaReservFin.getTime(), fechaRev2.getTime(), fechaRevFin2.getTime());
                if ((restar(fechaReservFin, fechaReserv))+(restar(fechaRevFin2, fechaRev2)) <= (mayor - menor)) {
                    System.out.println((restar(fechaReservFin, fechaReserv))+(restar(fechaRevFin2, fechaRev2)) <= (mayor - menor));
                    System.out.println("No se metio al array");
                    return false;
                }
            }
            System.out.println("Si se metio al array");
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(FechaX.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Incapas de analizar las reservas");
            return false;
        }
    }

    public static void StartCalendar(String fechaDeseada) {
        try {
            CALENTAR.setTime(STANDARFORMAT.parse(fechaDeseada));
        } catch (ParseException ex) {
            Logger.getLogger(FechaX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Date getFechafinal(Date fechaINI, int newday) {
        CALENTAR.setTime(fechaINI);
        CALENTAR.add(Calendar.DAY_OF_YEAR, newday);
        return CALENTAR.getTime();
    }

    public static long getmayor(Date param1, Date param2, Date param3, Date param4) {
        Date[] karray = {param1, param2, param3, param4};
        long aux = karray[0].getTime();
        for (Date numero : karray) {
            if (aux < numero.getTime()) {
                aux = numero.getTime();
            }
        }
        return aux;
    }

    public static long getmenor(long param1, long param2, long param3, long param4) {
        long[] karray = {param1, param2, param3, param4};
        long aux = karray[0];
        for (long numero : karray) {
            if (aux > numero) {
                aux = numero;
            }
        }
        return aux;
    }

    public static long restar(Date x, Date y) {
        return x.getTime() - y.getTime();
    }

    public static String fechaEjemplo() {
        return STANDARFORMAT.format(new Date());
    }
}
