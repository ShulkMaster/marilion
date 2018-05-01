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
    
    public void cambioFecha(String fecha, int id){
        Scanner leer = new Scanner(System.in);
        fecha = leer.nextLine();
        
        
        for (Reservacion e : ListaDeReservas){
            if(id==e.Id_reservacion){
                e.fechaIni=fecha;
            }
        }
        base.Escribir("reservacion.txt", ListaDeReservas);

    }
    
    public boolean FechaYhabitacion(String fecha,int id){
        boolean bool=false;
        
        for(Habitacion p : ListaDeHabitacion){
            for(Reservacion e :ListaDeReservas){
                if((fecha == null ? e.fechaIni != null : !fecha.equals(e.fechaIni)) && e.Id_reservacion == id){
                    if (p.habitacionEstado.equals(1)) {
                        bool=true;
                    }
                }
            }
        }
        
        
        return bool;
    }
    
    public void CancelaReserva(String dui){
        ArrayList<Reservacion> listN = new ArrayList<>();
        for (Reservacion e : ListaDeReservas){
            //hacer metodo que retorne el dui para poder dejarlo en privado siempre
            if(!dui.equals(e.PersonaAPagar.duiR())){
                listN.add(e);
            }
        }
        base.Escribir("reservacion.txt", listN);
        
    }
    
    public void cambioPack(String dui,PaqueteTipo tipo){

        for (Reservacion e : ListaDeReservas){
            //hacer metodo que retorne el dui para poder dejarlo en privado siempre
            if(dui.equals(e.PersonaAPagar.duiR())){
                e.tipo=tipo;
            }
        }
        
        base.Escribir("reservacion.txt", ListaDeReservas);
        
    }
    
    public void cambioHabitacion(String dui,String habitacion){

        for (Reservacion e : ListaDeReservas){
            //hacer metodo que retorne el dui para poder dejarlo en privado siempre
            if(dui.equals(e.PersonaAPagar.duiR())/*un metodo que diga que la habitacion no este en usoo deshabilitada*/){
                e.Id_habitacion=habitacion;
            }
        }
        
        base.Escribir("reservacion.txt", ListaDeReservas);
        
    }
    
}
