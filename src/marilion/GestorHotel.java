/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author gerar
 */

public class GestorHotel {
    ArrayList<Habitacion> ListaDeHabitacion;
    ArrayList<Huesped> ListaDeHuespedes;
    ArrayList<Reservacion> ListaDeReservas;
    ArrayList<Factura> ListaDeFactura;
    ArrayList<Administrador> ListaDeAdmins;
    
    GestorBase base;

    public GestorHotel() {
        this.ListaDeHabitacion = base.getListHabitacion();
        this.ListaDeHuespedes = base.getListHuespedesActivos();
        this.ListaDeReservas = base.getListReservacion();
        this.ListaDeFactura = base.getListFactura();
        this.ListaDeAdmins = base.getListAdministradores();
    }
    
    public void cambioFecha(){
        ArrayList<Reservacion> listN = new ArrayList<>();
        Scanner leer = new Scanner(System.in);
        String fecha;
        listN=base.getListReservacion();
        int id = leer.nextInt();
        fecha = leer.nextLine();
        
        
        for (Reservacion e : listN){
            if(id==e.Id_reservacion){
                e.fechaIni=fecha;
                listN.add(e);
            }
            else{
                listN.add(e);
            }
        }
        base.Escribir("reservacion.txt", listN);

    }
}
