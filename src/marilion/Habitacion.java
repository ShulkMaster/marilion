/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

/**
 *
 * @author mcdre
 */
import java.util.ArrayList;

public class Habitacion {

    public char indicadorDePiso;
    public int numeroHabitacion;
    public EstadoHabitacion habitacionEstado;
    public ArrayList<Huesped> listaHuesped;

    public Habitacion() {

    }

    public Habitacion(char indicadorDePiso, int numeroHabitacion, EstadoHabitacion habitacionEstado, ArrayList<Huesped> listaHuesped) {
        this.indicadorDePiso = indicadorDePiso;
        this.numeroHabitacion = numeroHabitacion;
        this.habitacionEstado = habitacionEstado;
        this.listaHuesped = listaHuesped;
    }

    @Override

    //Devuelve una linea para la base de dato
    public String toString() {
        String re;

        re = indicadorDePiso + " " + numeroHabitacion + " " + habitacionEstado + " ";
        for (Huesped h : listaHuesped) {
            re = re + h.ToString();
        }
        
        return re;
    }

}
