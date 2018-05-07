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
                long mayor = getmayor(fechaReserv, fechaReservFin, fechaRev2, fechaRevFin2);
                long menor = getmenor(fechaReserv, fechaReservFin, fechaRev2, fechaRevFin2);
                if ((restar(fechaReservFin, fechaReserv)) + (restar(fechaRevFin2, fechaRev2)) > (mayor - menor)) {
                    System.out.println("no se pudo meter al array");
                    return false;
                }
            }
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(FechaX.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Incapas de analizar las reservas");
            return false;
        }
    }

    public static boolean doMatch(String fechaRe1, int days1, String fechaRe2, int days2) {
        try {
            Date fechaReserv = STANDARFORMAT.parse(fechaRe1);
            Date fechaReservFin = getFechafinal(fechaReserv, days1);
            Date fechaRev2 = STANDARFORMAT.parse(fechaRe2);
            Date fechaRevFin2 = getFechafinal(fechaRev2, days2);
            long mayor = getmayor(fechaReserv, fechaReservFin, fechaRev2, fechaRevFin2);
            long menor = getmenor(fechaReserv, fechaReservFin, fechaRev2, fechaRevFin2);
            if ((restar(fechaReservFin, fechaReserv)) + (restar(fechaRevFin2, fechaRev2)) > (mayor - menor)) {
                return true;
            }
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(FechaX.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Incapas de analizar las reservas");
            return false;
        }
    }

    public static void domatch() {
        try {
            Date fechaRe1 = STANDARFORMAT.parse("6#5#2018");
            Date fechaRe1Final = getFechafinal(fechaRe1, 3);
            Date fechaRe2 = STANDARFORMAT.parse("9#5#2018");
            Date fechaRe2Final = getFechafinal(fechaRe2, 3);
            long mayor = getmayor(fechaRe1, fechaRe1Final, fechaRe2, fechaRe2Final);
            long menor = getmenor(fechaRe1, fechaRe1Final, fechaRe2, fechaRe2Final);
            if ((restar(fechaRe1Final, fechaRe1)) + (restar(fechaRe2Final, fechaRe2)) <= (mayor - menor)) {
                System.out.println("se metio al array");
            } else {
                System.out.println("NO SE METIO");
            }

        } catch (ParseException ex) {
            Logger.getLogger(FechaX.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fallo el parseo");
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

    public static long getmenor(Date param1, Date param2, Date param3, Date param4) {
        Date[] karray = {param1, param2, param3, param4};
        long aux = karray[0].getTime();
        for (Date numero : karray) {
            if (aux > numero.getTime()) {
                aux = numero.getTime();
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
