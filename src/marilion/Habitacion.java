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

    public Habitacion(char indicadorDePiso, int numeroHabitacion, EstadoHabitacion habitacionEstado) {
        this.indicadorDePiso = indicadorDePiso;
        this.numeroHabitacion = numeroHabitacion;
        this.habitacionEstado = habitacionEstado;
        this.listaHuesped = new ArrayList<>();
    }

    public Habitacion(char indicadorDePiso, int numeroHabitacion, EstadoHabitacion habitacionEstado, ArrayList<Huesped> listaHuesped) {
        this.indicadorDePiso = indicadorDePiso;
        this.numeroHabitacion = numeroHabitacion;
        this.habitacionEstado = habitacionEstado;
        this.listaHuesped = listaHuesped;
    }

    //Devuelve una linea para la base de dato
    @Override
    public String toString() {
        String re;
        re = indicadorDePiso + "#" + numeroHabitacion + " " + habitacionEstado + " ";
        for (Huesped h : listaHuesped) {
            re += h.ToString();
        }
        return re;
    }

    public String getHabId() {
        String aux = String.valueOf(indicadorDePiso)+numeroHabitacion;
        return aux;
    }

    public void setIndicadorDePiso(char indicadorDePiso) {
        this.indicadorDePiso = indicadorDePiso;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public void setHabitacionEstado(EstadoHabitacion habitacionEstado) {
        this.habitacionEstado = habitacionEstado;
    }

    public void setListaHuesped(ArrayList<Huesped> listaHuesped) {
        this.listaHuesped = listaHuesped;
    }

}
