/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.util.ArrayList;
import parserMax.FechaX;
import parserMax.Asker;
import parserMax.MakerX;
import parserMax.Reader;
import parserMax.StatadosX;

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
        this.ListaDeHuespedes = base.getListHuespedes();
        this.ListaDeReservas = base.getListReservacion();
        this.ListaDeFactura = base.getListFactura();
        this.ListaDeAdmins = base.getListAdministradores();
        base.checkOutIds(ListaDeReservas);
    }

    public void CrearReservacion() {
        Reservacion reservMaz = new Reservacion();
        reservMaz.Id_reservacion = GestorBase.lastIDReserva;
        reservMaz.Id_factura = GestorBase.lastIDFactura;
        reservMaz.Id_huespedes = GestorBase.lastIDHuesped;
        reservMaz.Estado = EstadoReservacion.Activa;
        Reader.consola.nextLine();
        reservMaz.PersonaAPagar = MakerX.creadoPersona(Asker.askPerson());
        System.out.println("Ingrese el numero de dias para hospedarse: ");
        reservMaz.dias = Reader.consola.nextInt();
        System.out.println("Ingrese la fecha formato d#M#yyyy:");
        System.out.println("Por ejemplo la fecha de hoy seria: " + parserMax.FechaX.fechaEjemplo());
        Reader.consola.nextLine();
        reservMaz.fechaIni = parserMax.FechaX.paser(Reader.consola.nextLine());
        System.out.println("Habitaciones disponibles para :" + reservMaz.fechaIni);
        showListHabitDispo(reservMaz.fechaIni, reservMaz.dias);//fecha dia
        System.out.println("Ingrese las habitaciones:");
        reservMaz.Id_habitacion = (Reader.consola.nextLine()+":");
        System.out.println("¿Desea agregar la segunda Habitacion?");
        System.out.println("1 = SI \t 2 = NO");
        if (Reader.consola.nextInt() == 1) {
            Reader.consola.nextLine();
            System.out.println("nueva habitacion");
            reservMaz.setXtraHabitacion(Reader.consola.nextLine());
        }
        System.out.println("Ingrese tipo de paquete: ");
        System.out.println("1 = Basico 2 = Primium 3 = Ninguno");
        reservMaz.setTipo(StatadosX.parseStatPack(Reader.consola.nextLine()));//ON 5
        //datos de los huespedes hasta aqui bien
        System.out.println("Desea agregar mas Huespedes: ");
        System.out.println("1 = Si, 2 = No");
        if (Reader.consola.nextInt() == 1) {
            Reader.consola.nextLine();
            int aux = reservMaz.getMaxHuesped();
            for (int j = 0; j < aux; j++) {
                //Datos del huesped partial string NAME#LASTNAME#DUI[ESPACE] ON 6

            }
        }
        System.out.println("\033[35m" + reservMaz.toString() + " Debug");
        ListaDeReservas.add(reservMaz);
        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
    }

    public void cambioFecha(String dui, String fecha1, String fecha2) {
        int aux = 0;
        ArrayList<Habitacion> listN = new ArrayList<>();

        for (Reservacion e : ListaDeReservas) {

            if (dui.equals(e.PersonaAPagar.duiR()) && fecha1.equals(e.fechaIni)) {
                listN = getListHabitDispo(fecha2, e.dias);
            }
        }
        for (Habitacion e : listN) {
            if (e != null) {
                aux++;
                System.out.print(e.getHabId() + "\n");
            }
        }
        if (aux != 0) {
            System.out.print("Escoja una de las habitaciones anteriores o la misma si se puede:");
            String hab = Reader.consola.next();
            for (Reservacion e : ListaDeReservas) {
                if (dui.equals(e.PersonaAPagar.duiR()) && fecha1.equals(e.fechaIni)) {
                    e.fechaIni = fecha2;
                    e.Id_habitacion = hab;
                }
            }

        } else {
            System.out.print("No hay ninguna avitacion que se acople a la fecha a hospedarse");
        }

        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
    }

    public void CancelaReserva(String dui, String fecha) {
        ArrayList<Reservacion> listN = new ArrayList<>();
        for (Reservacion e : ListaDeReservas) {
            if (!dui.equals(e.PersonaAPagar.duiR()) && !fecha.equals(e.fechaIni)) {
                listN.add(e);
            }
        }
        ListaDeReservas = listN;
        base.Escribir(listN, GestorBase.RESERVAS);
    }

    public void cambioPack(String dui, String fecha, PaqueteTipo tipo) {
        for (Reservacion e : this.ListaDeReservas) {
            if (e.PersonaAPagar.duiR().equals(dui) && e.fechaIni.equals(fecha)) {
                e.tipo = tipo;
            }
        }

        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
    }

    public void cambioHabitacion(String dui, String habitacion, String fecha) {

        for (Habitacion p : ListaDeHabitacion) {
            for (Reservacion e : ListaDeReservas) {
                if (dui.equals(e.PersonaAPagar.duiR()) && p.habitacionEstado.equals(1) == true && e.fechaIni.equals(fecha)) {
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

    public void CambioPersonaP(String dui, String dui2, String nombre, String Apellido, String fecha) {

        Persona aux;

        for (Reservacion e : ListaDeReservas) {
            if (dui.equals(e.PersonaAPagar.duiR()) && fecha.equals(e.fechaIni)) {
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
        Prototype.dias = (Integer.parseInt(masterType[1]));
        Prototype.setEstado(EstadoReservacion.Activa);
        Prototype.setPersonaAPagar(MakerX.creadoPersona(masterType[0]));
        Prototype.setTipo(StatadosX.parseStatPack(masterType[4]));
        Prototype.setFechaIni(masterType[2]);
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
            System.out.println(auxH.getHabId());
            if (FechaX.doMatch(fecha, days, getListReserChox(auxH.getHabId()))) {
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
                    Factura fac = GestorCompra.getFactura(AuxRe);
                    ListaDeFactura.add(fac);
                    AuxRe.Estado = EstadoReservacion.pagada;
                    base.Escribir(ListaDeFactura, GestorBase.FACTURAS);
                    base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
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
        ArrayList<Reservacion> listN = getListReserX();
        base.printListReservas(listN);
        ArrayList<Reservacion> listNa = new ArrayList<>();
        for (Reservacion e : listN) {
            if (e.PersonaAPagar.duiR().equals(dui)) {
                listNa.add(e);
            }
        }
        base.printListReservas(listNa);
        System.out.println("Ingrese la fecha de la reserva con el formato:");
        String fecha = Reader.consola.next();
        pagarReserva(dui, fecha);
    }

    public void cambioDias(String dui, String fecha, int num) {
        int aux = 0;
        ArrayList<Habitacion> listN = new ArrayList<>();

        for (Reservacion e : ListaDeReservas) {

            if (dui.equals(e.PersonaAPagar.duiR()) && fecha.equals(e.fechaIni)) {
                listN = getListHabitDispo(fecha, num);
            }
        }
        for (Habitacion e : listN) {
            if (e != null) {
                aux++;
                System.out.print(e.getHabId() + "\n");
            }
        }
        if (aux != 0) {
            System.out.print("Escoja una de las habitaciones anteriores o la misma si se puede:");
            String hab = Reader.consola.next();
            for (Reservacion e : ListaDeReservas) {
                if (dui.equals(e.PersonaAPagar.duiR()) && fecha.equals(e.fechaIni)) {
                    e.dias = num;
                    e.Id_habitacion = hab;
                }
            }

        } else {
            System.out.print("No hay ninguna avitacion que se acople a los dias deseados a hospedarse en la misma fecha");
        }

        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
    }

    public void cambioFechaYDias(String dui, String fecha1, String fecha2, int num) {
        int aux = 0;
        ArrayList<Habitacion> listN = new ArrayList<>();

        for (Reservacion e : ListaDeReservas) {

            if (dui.equals(e.PersonaAPagar.duiR()) && fecha1.equals(e.fechaIni)) {
                listN = getListHabitDispo(fecha2, num);
            }
        }
        for (Habitacion e : listN) {
            if (e != null) {
                aux++;
                System.out.print(e.getHabId() + "\n");
            }
        }
        if (aux != 0) {
            System.out.print("Escoja una de las habitaciones anteriores o la misma si se puede:");
            String hab = Reader.consola.next();
            for (Reservacion e : ListaDeReservas) {
                if (dui.equals(e.PersonaAPagar.duiR()) && fecha1.equals(e.fechaIni)) {
                    e.fechaIni = fecha2;
                    e.dias = num;
                    e.Id_habitacion = hab;
                }
            }

        } else {
            System.out.print("No hay ninguna avitacion que se acople a la fecha nueva y dias nuevo a hospedarse");
        }

    }

    public void HabilitarHabitacionC(String id_habitacion) {
        for (Habitacion ha : ListaDeHabitacion) {
            if (ha.getHabId().equals(id_habitacion) && ha.habitacionEstado != EstadoHabitacion.EnUso) {
                ha.habitacionEstado = EstadoHabitacion.Habilitada;
            }
        }
    }

    public void DeshabilitarHabitacion(String id_habitacion) {
        for (Habitacion ha : ListaDeHabitacion) {
            if (ha.getHabId().equals(id_habitacion) && ha.habitacionEstado != EstadoHabitacion.EnUso) {
                ha.habitacionEstado = EstadoHabitacion.Deshabilitada;
            }
        }
    }

    public void HabilitarPiso(int pisoNumero) {
        String pisos = "abcdef";
        for (Habitacion ha : ListaDeHabitacion) {
            if (pisos.charAt(pisoNumero - 1) == ha.indicadorDePiso && ha.habitacionEstado != EstadoHabitacion.EnUso) {
                ha.habitacionEstado = EstadoHabitacion.Habilitada;
            }
        }
    }
    
    public void DeshabilitarPiso(int pisoNumero) {
        String pisos = "abcdef";
        for (Habitacion ha : ListaDeHabitacion) {
            if (pisos.charAt(pisoNumero - 1) == ha.indicadorDePiso && ha.habitacionEstado != EstadoHabitacion.EnUso) {
                ha.habitacionEstado = EstadoHabitacion.Deshabilitada;
            }
        }
    }
    
    public void EntregarHabitacion(String id_habitacion) {
        for (Habitacion ha : ListaDeHabitacion) {
            if (ha.getHabId().equals(id_habitacion)) {
                ha.habitacionEstado = EstadoHabitacion.EnUso;
            }
        }
    }
}
