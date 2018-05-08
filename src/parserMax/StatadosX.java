/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserMax;

import marilion.EstadoHabitacion;
import marilion.EstadoReservacion;
import marilion.PaqueteTipo;

/**
 *
 * @author yury_
 */
public class StatadosX {

    /**
     * este metodo convierte datos tipo String a EstadoHabitacion default si no
     * funciona EstadoHabitacion.Deshabilitada
     *
     * @param value cadena con el valor de 1, 2 o 3
     * @return EstadoHabitacion abilitada, deshabilitada o en uso
     */
    public static EstadoHabitacion parseStateHabitacion(String value) {
        EstadoHabitacion aux;
        switch (value) {
            case "Habilitada":
                aux = EstadoHabitacion.Habilitada;
                break;
            case "Deshabilitada":
                aux = EstadoHabitacion.Deshabilitada;
                break;
            case "EnUso":
                aux = EstadoHabitacion.EnUso;
                break;
            default:
                System.err.println("Archivo potencialemte corrupto, estado desconocido");
                aux = EstadoHabitacion.Deshabilitada;
                break;
        }
        return aux;
    }

    /**
     * este metodo convierte datos tipo String a EstadoReservacion default si no
     * funciona EstadoReservacion.Cancelada
     *
     * @param value cadena con el valor de 1, 2, 3 y 4
     * @return EstadoReservacion Activa, Cancelada, EnUSo y Finalizada
     */
    public static EstadoReservacion parseStateReserva(String value) {
        EstadoReservacion aux;
        switch (value) {
            case "Activa":
                aux = EstadoReservacion.Activa;
                break;
            case "Cancelada":
                aux = EstadoReservacion.Cancelada;
                break;
            case "EnUso":
                aux = EstadoReservacion.EnUso;
                break;
            case "Finalizada":
                aux = EstadoReservacion.Finalizada;
                break;
            case "pagada":
                aux = EstadoReservacion.pagada;
                break;
            default:
                System.err.println("Archivo u Objeto RESERVA potencialemte corrupto, estado desconocido");
                aux = EstadoReservacion.Cancelada;
                break;
        }
        return aux;
    }

    /**
     * este metodo convierte datos tipo String a PaqueteTipo default si no
     * funciona PaqueteTipo.ninguno
     *
     * @param value cadena con el valor de 1, 2 o 3
     * @return PaqueteTipo basico, premium y ninguno
     */
    public static PaqueteTipo parseStatPack(String value) {
        PaqueteTipo aux;
        switch (value) {
            case "basico":
                aux = PaqueteTipo.basico;
                break;
            case "1":
                aux = PaqueteTipo.basico;
                break;
            case "premium":
                aux = PaqueteTipo.premium;
                break;
            case "2":
                aux = PaqueteTipo.premium;
                break;
            case "ninguno":
                aux = PaqueteTipo.ninguno;
                break;
            case "3":
                aux = PaqueteTipo.ninguno;
                break;
            default:
                System.err.println("Archivo potencialemte corrupto, estado desconocido");
                aux = PaqueteTipo.ninguno;
                break;
        }
        return aux;
    }

}
