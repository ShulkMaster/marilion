/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.util.ArrayList;

/**
 *
 * @author gerar
 */
public class GestorHotel {
    ArrayList<Habitacion> ListaDeHabitacion;
    ArrayList<Huesped> ListaDeHuespedes;
    ArrayList<Reservacion> ListaDeReservas;
    ArrayList<Factura> ListaDeFactura;

    public GestorHotel(ArrayList<Habitacion> ListaDeHabitacion, ArrayList<Huesped> ListaDeHuespedes, ArrayList<Reservacion> ListaDeReservas, ArrayList<Factura> ListaDeFactura) {
        this.ListaDeHabitacion = ListaDeHabitacion;
        this.ListaDeHuespedes = ListaDeHuespedes;
        this.ListaDeReservas = ListaDeReservas;
        this.ListaDeFactura = ListaDeFactura;
    }
    
    
}
