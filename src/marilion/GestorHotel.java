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
        // base.escribirHabitacion();
        this.ListaDeHabitacion = base.getListHabitacion();
        this.ListaDeHuespedes = base.getListHuespedes();
        this.ListaDeReservas = base.getListReservacion();
        this.ListaDeFactura = base.getListFactura();
        this.ListaDeAdmins = base.getListAdministradores();
        base.checkOutIds(ListaDeReservas);
    }

    /* old New reservation
        System.out.println("Ingrese acompanniantes:");
        boolean anadex = true;
        while (anadex) {
            System.out.println("Ingrese el nombre: ");
            master += (Reader.consola.next());
            System.out.println("Ingrese el apellido: ");
            //master += ("#" + Reader.consola.next();
            System.out.println("Ingrese el dui: ");
            String auxdui = Reader.consola.next();
            //Persona aux = new Persona(auxnombre, auxapellido, auxdui);
            System.out.println("end 1");
            if (Reader.consola.nextInt() == 1) {
                anadex = false;
            }
        }
     */
    public void CrearReservacion() {
        //limpiando Buffer podes cambiar el orden que se ejecuta el menu solo
        Reader.consola.nextLine();

        String master;
        /**
         * mantene el mismo formato de archvo al final en la cadena master
         * IDhabitacion, dias, persona a pagar, tipo de packete, fechaini los
         * demas IDs son autogenerados por la base y el estado cuando creas una
         * reservacion por defecto es activa, para mas info mira la clase
         * reservacion
         */

        //Datos del cliente partial string NAME#LASTNAME#DUI[ESPACE]
        System.out.println("Ingrese el nombre de la persona a pagar: ");
        master = Reader.consola.nextLine();
        System.out.println("Ingrese el apellido: ");
        master += ("#" + Reader.consola.nextLine());
        System.out.println("Ingrese el dui: ");
        master += ("#" + Reader.consola.nextLine());//on [0]

        //Datos de la Reserva
        System.out.println("Ingrese el numero de dias para hospedarse: ");
        master += (" " + Reader.consola.nextLine());//split[1]
        System.out.println("Ingrese la fecha formato d#M#yyyy:");
        System.out.println("Por ejemplo la fecha de hoy seria: " + parserMax.FechaX.fechaEjemplo());
        master += (" " + parserMax.FechaX.paser(Reader.consola.nextLine()));//split[2]
        System.out.println("Habitaciones disponibles: ");
        System.out.println("\033[35m" + master.split(" ")[2] + " Dias: " + Integer.parseInt(master.split(" ")[1]));
        showListHabitDispo(master.split(" ")[2], Integer.parseInt(master.split(" ")[1]));//fecha dia
        System.out.println("Ingrese las habitaciones:");
        master += (" " + Reader.consola.nextLine());
        System.out.println("¿Desea agregar la segunda Habitacion?");
        System.out.println("1 = SI \t 2 = NO");
        if (Reader.consola.nextInt() == 1) {
            System.out.println("nueva habitacion");
            //limpiando Buffer podes
            Reader.consola.nextLine();
            master += (":" + Reader.consola.nextLine());
        }
        //limpiando Buffer podes
        Reader.consola.nextLine();
        System.out.println("Ingrese tipo de paquete: ");
        System.out.println("1 = Basico 2 = Primium 3 = Ninguno");
        master += (" " + Reader.consola.nextLine());
        //algun metodo que defina si puede hacer la reservacion y si hay habitaciones disponibles
        //aqui adentro voy a crear las habitaciones cuando haya metodo que devvuelva habitaciones
        System.out.println("\033[35m" + master + " Debug");
        ReservaNueva(master.split(" "));
    }

    public void cambioFecha(String dui, String fecha) {

        for (Reservacion e : ListaDeReservas) {
            if (dui.equals(e.PersonaAPagar.duiR())) {
                e.fechaIni = fecha;
            }
        }
        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
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

    public void CancelaReserva(String dui,String fecha) {
        ArrayList<Reservacion> listN = new ArrayList<>();
        for (Reservacion e : ListaDeReservas) {
            if (!dui.equals(e.PersonaAPagar.duiR())&&!fecha.equals(e.fechaIni)) {
                listN.add(e);
            }
        }
        ListaDeReservas = listN;
        base.Escribir(listN, GestorBase.RESERVAS);
    }

    public void cambioPack(String dui, PaqueteTipo tipo) {
        for (Reservacion e : this.ListaDeReservas) {
            if (e.PersonaAPagar.duiR().equals(dui)) {
                e.tipo = tipo;
            }
        }

        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
    }

    public void cambioHabitacion(String dui, String habitacion) {

        for (Habitacion p : ListaDeHabitacion) {
            for (Reservacion e : ListaDeReservas) {
                if (dui.equals(e.PersonaAPagar.duiR()) && p.habitacionEstado.equals(1) == true) {
                    e.Id_habitacion = habitacion;
                }
            }
        }
        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
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

        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
    }

    /**
     *
     * @param masterType String whit the following format
     * IDreserva,IDfactura,IDhuespedes,IDhabitacion, dias,estado, pagador, tipo,
     * fechaini
     */
    public void ReservaNueva(String[] masterType) {
        System.out.println(masterType[3]);
        Reservacion Prototype = new Reservacion(GestorBase.lastIDReserva, GestorBase.lastIDFactura, GestorBase.lastIDHuesped, masterType[3]);
        Prototype.setDias(Integer.parseInt(masterType[1]));
        Prototype.setEstado(EstadoReservacion.Activa);
        Prototype.setPersonaAPagar(base.creadoPersona(masterType[0]));
        Prototype.setTipo(StatadosX.parseStatPack(masterType[4]));
        Prototype.setFechaIni(masterType[2]);
        ListaDeReservas.add(Prototype);
        base.AutoIncrement();
        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
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

    public void showListHabitDispo(String fecha, int days) {
        ArrayList<Habitacion> listaAux = getListHabitDispo(fecha, days);
        base.printListHabitacion(listaAux);
    }

    public ArrayList<Habitacion> getListHabitDispo(String fecha, int days) {
        ArrayList<Habitacion> listaAux = new ArrayList<>();
        for (Habitacion auxH : getListHabitaReady()) {
            System.out.println("\033[32mHabitacion " + auxH.indicadorDePiso + auxH.numeroHabitacion);
            System.out.println(auxH.getHabId());
            if (FechaX.doMatch(fecha, days, getListReserChox(auxH.getHabId()))) {
                System.err.println("Esta limpio");
                listaAux.add(auxH);
            }
        }
        return listaAux;
    }

    private ArrayList<Habitacion> getListHabitaReady() {
        ArrayList<Habitacion> listaAux = new ArrayList<>();
        for (Habitacion auxH : ListaDeHabitacion) {
            if (auxH.habitacionEstado.equals(EstadoHabitacion.Habilitada) || auxH.habitacionEstado.equals(EstadoHabitacion.EnUso)) {
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

    public boolean Comprobador(String dui) {
        boolean bool = false;
        for (Reservacion e : ListaDeReservas) {
            if (e.PersonaAPagar.duiR().equals(dui)) {
                bool = true;
            }
        }

        return bool;
    }
    
    public void PagarReservacion(String dui) {
        ArrayList<Reservacion> listN=getListReserX();
        base.printListReservas(listN);
        ArrayList<Reservacion> listNa=new ArrayList<>();
        for(Reservacion e : listN){
            if(e.PersonaAPagar.duiR().equals(dui)){
                listNa.add(e);
            }
        }
        base.printListReservas(listNa);
        System.out.println("Ingrese la fecha de la reserva con el formato:");
        String fecha=Reader.consola.next();
        pagarReserva(dui, fecha);
    }
    
}
