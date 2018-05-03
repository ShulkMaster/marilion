/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.util.ArrayList;
import parserMax.StatadosX;
import parserMax.FechaX;

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

    public static GestorBase base = new GestorBase();

    public GestorHotel() {
        this.ListaDeHabitacion = base.getListHabitacion();
        //this.ListaDeHuespedes = base.getListHuespedesActivos();
        this.ListaDeReservas = base.getListReservacion();
        this.ListaDeFactura = base.getListFactura();
        this.ListaDeAdmins = base.getListAdministradores();
        base.checkOutIds(ListaDeReservas);
    }

    public void cambioFecha(String dui, String fecha) {

        for (Reservacion e : ListaDeReservas) {
            if (dui.equals(e.PersonaAPagar.duiR())) {
                e.fechaIni = fecha;
            }
        }
        base.Escribir("reservacion.txt", ListaDeReservas);
    }

    public boolean FechaYhabitacion(String dui, String fecha) {
        boolean bool = false;

        for (Habitacion p : ListaDeHabitacion) {
            for (Reservacion e : ListaDeReservas) {
                if ((fecha == null ? e.fechaIni != null : !fecha.equals(e.fechaIni)) && e.PersonaAPagar.duiR().equals(dui)) {
                    if (p.habitacionEstado.equals(1)) {
                        bool = true;
                    }
                }
            }
        }

        return bool;
    }

    public void CancelaReserva(String dui) {
        ArrayList<Reservacion> listN = new ArrayList<>();
        for (Reservacion e : this.ListaDeReservas) {
            if (!dui.equals(e.PersonaAPagar.duiR())) {
                listN.add(e);
            }
        }
        ListaDeReservas = listN;
        base.Escribir("reservacion.txt", listN);
    }

    public void cambioPack(String dui, PaqueteTipo tipo) {
        for (Reservacion e : this.ListaDeReservas) {
            if (e.PersonaAPagar.duiR().equals(dui)) {
                e.tipo = tipo;
            }
        }

        base.Escribir("reservacion.txt", ListaDeReservas);
    }

    public void cambioHabitacion(String dui, String habitacion) {

        for (Habitacion p : ListaDeHabitacion) {
            for (Reservacion e : ListaDeReservas) {
                if (dui.equals(e.PersonaAPagar.duiR()) && p.habitacionEstado.equals(1) == true) {
                    e.Id_habitacion = habitacion;
                }
            }
        }
        base.Escribir("reservacion.txt", ListaDeReservas);
    }

    public void Actualizar() {
        this.ListaDeHabitacion = base.getListHabitacion();
        //this.ListaDeHuespedes = base.getListHuespedesActivos();
        this.ListaDeReservas = base.getListReservacion();
        this.ListaDeFactura = base.getListFactura();
        this.ListaDeAdmins = base.getListAdministradores();
    }

    public void mostrarHabitacion() {

        for (Habitacion p : ListaDeHabitacion) {
            if (p.habitacionEstado.equals(1) == true) {
                System.out.println(p.getHabId());
            }
        }

    }

    public void CambioPersonaP(String dui, String dui2, String nombre, String Apellido) {

        Persona aux;

        for (Reservacion e : ListaDeReservas) {
            if (dui.equals(e.PersonaAPagar.duiR())) {
                aux = e.PersonaAPagar;
                e.PersonaAPagar.cambioDui(dui2);
                e.PersonaAPagar.Nombre = nombre;
                e.PersonaAPagar.Apellido = Apellido;
            }
        }
        /*for(){
            
        }*/

        base.Escribir("reservacion.txt", ListaDeReservas);
    }

    /**
     *
     * @param masterType String whit the following format
     * IDreserva,IDfactura,IDhuespedes,IDhabitacion, dias,estado, pagador, tipo,
     * fechaini
     */
    public void ReservaNueva(String masterType) {
        Reservacion Prototype = new Reservacion(GestorBase.lastIDReserva, GestorBase.lastIDFactura, GestorBase.lastIDHuesped, masterType.split(" ")[0]);
        Prototype.setDias(Integer.parseInt(masterType.split(" ")[1]));
        Prototype.setEstado(EstadoReservacion.Activa);
        Prototype.setPersonaAPagar(base.creadoPersona(masterType.split(" ")[3]));
        Prototype.setTipo(StatadosX.parseStatPack(masterType.split(" ")[4]));
        Prototype.setFechaIni(masterType.split(" ")[5]);
        ListaDeReservas.add(Prototype);
        base.AutoIncrement();
        base.Escribir("reservacion.txt", ListaDeReservas);
    }

    public void printListReservas() {
        int contador = 1;
        for (Reservacion auxF : ListaDeReservas) {
            System.out.println("<-------------- Reservacion " + contador + "-------------------->");
            System.out.println("ID reservacion: " + auxF.Id_reservacion);
            System.out.println("ID factura: " + auxF.Id_factura);
            System.out.println("ID huespedes: " + auxF.Id_huespedes);
            System.out.println("ID habitacion: " + auxF.Id_habitacion);
            System.out.println("ID Dias duracion: " + auxF.dias);
            System.out.println("Estado : " + auxF.Estado);
            System.out.println("Persona que paga: " + auxF.PersonaAPagar.nombre() + auxF.PersonaAPagar.Apellido());
            System.out.println("DUI : " + auxF.PersonaAPagar.duiR());
            System.out.println("Paquete : " + auxF.tipo);
            System.out.println("Fecha inicial : " + auxF.fechaIni);
            contador++;
        }
    }

    public void showListHabitDispo(String fecha, String days) {
        ArrayList<Habitacion> listaAux = getListHabitDispo(fecha, days);
        base.printListHabitacion(listaAux);
    }

    public ArrayList<Habitacion> getListHabitDispo(String fecha, String days) {
        ArrayList<Habitacion> listaAux = new ArrayList<>();
        for (Habitacion auxH : getListHabitaReady()) {
            if (FechaX.doMatch(fecha, days, getListReserChox(auxH.getHabId()))) {
                System.err.println("Que raro?");
            } else {
                listaAux.add(auxH);
            }
        }
        return listaAux;
    }

    private ArrayList<Habitacion> getListHabitaReady() {
        ArrayList<Habitacion> listaAux = new ArrayList<>();
        for (Habitacion auxH : ListaDeHabitacion) {
            if (auxH.habitacionEstado.equals(EstadoHabitacion.Habilitada) || auxH.habitacionEstado.equals(EstadoHabitacion.EnUso)) {
                System.out.println("\033[32mHabitacion " + auxH.indicadorDePiso + auxH.numeroHabitacion);
                listaAux.add(auxH);
            }

        }
        return listaAux;
    }

    /**
     *
     * @param CurrenHabiID id de llas habitacion a buscar en las reservas
     * problematcas este ID sera el iterador para las 60 o N habitaciones que
     * haya que revisar
     * @return Reservaciones con posible conflicto cronologico
     */
    private ArrayList<Reservacion> getListReserChox(String CurrenHabiID) {
        ArrayList<Reservacion> listaAux = new ArrayList<>();
        for (Reservacion auxH : getListReserX()) {
            if (auxH.getId_habitacion().equals(CurrenHabiID)) {
                System.out.println("\033[35mReservas Potencialmente problematica :" + auxH.Id_reservacion);
                listaAux.add(auxH);
            }
        }
        return listaAux;
    }

    /**
     * Filtra todas las reservaciones para solo leer las que estan activas o en
     * uso que logicamente son las unicas que podrian tener conflicto con nuevas
     * Reservas
     *
     * @return Reservaciones con posible conflicto espacial
     */
    private ArrayList<Reservacion> getListReserX() {
        ArrayList<Reservacion> listaAux = new ArrayList<>();
        for (Reservacion auxH : ListaDeReservas) {
            if (auxH.Estado.equals(EstadoReservacion.Activa) || auxH.Estado.equals(EstadoReservacion.EnUso)) {
                System.out.println("\033[34mReservas Activas y EnUso :" + auxH.Id_reservacion);
                listaAux.add(auxH);
            }

        }
        return listaAux;
    }

    public void pagarReserva(String dui, String fecha) {
        int contador = 0;
        for (Reservacion AuxRe : ListaDeReservas) {
            System.out.println("Prosesadondo registro " + (contador + 1) + " de " + (ListaDeReservas.size() + 1));
            if (AuxRe.PersonaAPagar.duiR().equals(dui)) {
                if (AuxRe.fechaIni.equals(fecha)) {
                    System.out.println(AuxRe.fechaIni);
                    System.out.println("Reserva ID: " + AuxRe.Id_reservacion + " Ha sido pagada");
                    System.out.println("Detalles: " + AuxRe.toString());
                    System.out.println("Se pagan solas hasta que gestor de pago este activo");
                    break;
                }
            }
            contador++;
        }
    }
}
