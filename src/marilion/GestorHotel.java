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
    GestorBase base;

    public GestorHotel() {
        this.ListaDeHabitacion = base.GetListHabitacion();
        this.ListaDeHuespedes = base.GetListHuespedesActivos();
        this.ListaDeReservas = base.GetListReservacion();
        this.ListaDeFactura = base.GetListFactura();
    }
    
    
}
