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
    ArrayList<Administrador> ListaDeAdmins;
    
    GestorBase base;

    public GestorHotel() {
        this.ListaDeHabitacion = base.getListHabitacion();
        this.ListaDeHuespedes = base.getListHuespedesActivos();
        this.ListaDeReservas = base.getListReservacion();
        this.ListaDeFactura = base.getListFactura();
        this.ListaDeAdmins = base.getListAdministradores();
    }
    
    public ArrayList<Reservacion> cambioFecha(int id,ArrayList<Reservacion> listR,String fecha){
        ArrayList<Reservacion> listN=new ArrayList<>();
        
        for (Reservacion e : listR){
            if(id==e.Id_reservacion){
                e.fechaIni=fecha;
                listN.add(e);
            }
            else{
                listN.add(e);
            }
        }
        return listN;
    }
}
