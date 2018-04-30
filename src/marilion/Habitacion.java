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
    public TipoDeHabitacion Tipo;
    public int piso;
    public ArrayList<Huesped> listaHuesped; 

    public Habitacion(char indicadorDePiso, int numeroHabitacion, EstadoHabitacion habitacionEstado, TipoDeHabitacion Tipo, int piso, ArrayList<Huesped> listaHuesped) {
        this.indicadorDePiso = indicadorDePiso;
        this.numeroHabitacion = numeroHabitacion;
        this.habitacionEstado = habitacionEstado;
        this.Tipo = Tipo;
        this.piso = piso;
        this.listaHuesped = listaHuesped;
    }
    
    @Override
    public String toString()
    {
        String re;
        
        re = indicadorDePiso + " " + numeroHabitacion + " "+ habitacionEstado +
                " "+ Tipo +" "+ piso +" ";
        for(Huesped h: listaHuesped)
        {
             re = re + ":" + h.ToString();
        }
        
        return re;
    }
    
}
