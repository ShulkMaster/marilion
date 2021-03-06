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
    ArrayList<Huesped[]> ListaDehues;
    ArrayList<Reservacion> ListaDeReservas;
    ArrayList<Factura> ListaDeFactura;
    ArrayList<Administrador> ListaDeAdmins;

    public static GestorBase base = new GestorBase();

    public GestorHotel() {
        this.ListaDeHabitacion = base.getListHabitacion();
        this.ListaDeReservas = base.getListReservacion();
        this.ListaDeFactura = base.getListFactura();
        this.ListaDeAdmins = base.getListAdministradores();
        base.checkOutIds(ListaDeReservas);
    }

    public void CrearReservacion() {
        Reservacion reservMaz = new Reservacion();
        reservMaz.setId_reservacion(GestorBase.lastIDReserva);
        reservMaz.setId_factura(GestorBase.lastIDFactura);
        reservMaz.Estado = EstadoReservacion.Activa;
        Reader.consola.nextLine();
        reservMaz.PersonaAPagar = MakerX.creadoPersona(Asker.askPerson());
        System.out.println("Ingrese el numero de dias para hospedarse: ");
        reservMaz.dias = Reader.consola.nextInt();
        if (reservMaz.dias>=8){
            do{
               System.out.println("La estadia maxima por cliente es de 7 dias");
               System.out.println("Ingrese el numero de dias para hospedarse: ");
               reservMaz.dias = Reader.consola.nextInt(); 
            }while (reservMaz.dias >=8);
        }
        while (reservMaz.dias<8){
        System.out.println("Ingrese la fecha formato d#M#yyyy:");
        System.out.println("Por ejemplo la fecha de hoy seria: " + FechaX.fechaEjemplo());
        Reader.consola.nextLine();
        reservMaz.fechaIni = FechaX.paser(Reader.consola.nextLine());
        System.out.println("Habitaciones disponibles para :" + reservMaz.fechaIni);
        showListHabitDispo(reservMaz.fechaIni, reservMaz.dias);//fecha dia
        System.out.println("Ingrese las habitaciones:");
        reservMaz.setId_habitacion(Reader.consola.nextLine() + ":");
        reservMaz.addHUesped(reservMaz.getId_habitacion(), 0);
        System.out.println("¿Desea agregar la segunda Habitacion?");
        System.out.println("1 = SI \t 2 = NO");
        if (Reader.consola.nextLine().equals("1")) {
            System.out.println("nueva habitacion");
            reservMaz.setXtraHabitacion(Reader.consola.nextLine());
            reservMaz.addHUesped(reservMaz.getId_habitacion(), 1);
        }
        System.out.println("Ingrese tipo de paquete: ");
        System.out.println("1 = Basico 2 = Primium 3 = Ninguno");
        reservMaz.tipo = StatadosX.parseStatPack(Reader.consola.nextLine());//ON 5
        //datos de los huespedes hasta aqui bien
        System.out.println("\033[35m" + reservMaz.toString() + " Debug");
        System.out.print((char) 27 + "[30;47m");
        ListaDeReservas.add(reservMaz);
        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);}
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
                    e.setId_habitacion(hab);
                }
            }

        } else {
            System.out.print("No hay ninguna avitacion que se acople a la fecha a hospedarse");
        }

        System.out.print("\033[32m");

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
                    e.setId_habitacion(habitacion);
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

    public void printListReservas() {
        int contador = 1;
        for (Reservacion auxF : ListaDeReservas) {
            System.out.println("<-------------- Reservacion " + contador + "-------------------->");
            System.out.println("ID reservacion: " + auxF.getId_reservacion());
            System.out.println("ID factura: " + auxF.getId_factura());
            //System.out.println("ID huespedes: " + auxF.Id_huespedes);
            System.out.println("ID habitacion: " + auxF.getId_habitacion());
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
        System.out.println("Habitaciones supuestamente disponibles");

        for (Habitacion p : ListaDeHabitacion) {
            if (listaAux.contains(p)) {
                System.out.print("\033[32m[" + p.getHabId() + "] ");
            } else {
                System.out.print("\033[31m[" + p.getHabId() + "] ");
            }
            if (p.numeroHabitacion == 10) {
                System.out.println();
            }
        }
        System.out.println("\033[32m█ Disponible");
        System.out.println("\033[31m█ No Disponible");
        System.out.print("\033[30m");

    }

    public ArrayList<Habitacion> getListHabitDispo(String fecha, int days) {
        ArrayList<Habitacion> aux = new ArrayList<>();
        ArrayList<Habitacion> lisReady = getListHabitaReady();
        ArrayList<Reservacion> ReservorioActivo = getListReserX();
        ArrayList<Integer> contex = new ArrayList<>();
        for (Reservacion reserva : ReservorioActivo) {
            if (FechaX.doMatch(fecha, days, reserva.fechaIni, reserva.dias)) {
                for (String str : reserva.getHIDs()) {
                    int contador = 0;
                    for (Habitacion habita : lisReady) {
                        if (habita.getHabId().equals(str)) {
                            contex.add(contador);
                        }
                        contador++;
                    }
                }
            }
        }
        int contador2 = 0;
        for (Habitacion hab : lisReady) {
            if (contex.contains(contador2)) {

            } else {
                aux.add(hab);
            }
            contador2++;
        }
        return aux;
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
    private ArrayList<Reservacion> getListReserChox(String CurrenHabiID, ArrayList<Reservacion> Reservorio) {
        ArrayList<Reservacion> listaAux = new ArrayList<>();
        for (Reservacion Reservex : Reservorio) {
            if (Reservex.getHIDs()[0].equals(CurrenHabiID)) {
                System.out.println("\033[35mReservas Potencialmente problematica : " + Reservex.getId_reservacion() + "con habitacion " + Reservex.getHIDs()[0]);
                listaAux.add(Reservex);
            }
        }
        System.out.print("\033[32m");
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
        for (Reservacion auxRex : ListaDeReservas) {
            if (auxRex.Estado.equals(EstadoReservacion.Activa) || auxRex.Estado.equals(EstadoReservacion.EnUso) || auxRex.Estado.equals(EstadoReservacion.pagada)) {
                listaAux.add(auxRex);
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
                    System.out.println("Reserva ID: " + AuxRe.getId_habitacion() + " Ha sido pagada");
                    Factura fac = GestorCompra.getFactura(AuxRe);
                    ListaDeFactura.add(fac);
                    AuxRe.Estado = EstadoReservacion.pagada;
                    System.out.println("El costo es: " + GestorCompra.RegresarPrecio(AuxRe) + " Dolares");
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
                    e.setId_habitacion(hab);
                }
            }

        } else {
            System.out.print("No hay ninguna habitacion que se acople a los dias deseados a hospedarse en la misma fecha");
        }

        System.out.print("\033[32m");

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
                    e.setId_habitacion(hab);
                }
            }

        } else {
            System.out.print("No hay ninguna habitacion que se acople a la fecha nueva y dias nuevo a hospedarse");
        }

        System.out.print("\033[32m");

        base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
    }

    public void HabilitarHabitacionC(String id_habitacion) {
        for (Habitacion ha : ListaDeHabitacion) {
            if (ha.getHabId().equals(id_habitacion) && ha.habitacionEstado != EstadoHabitacion.EnUso) {
                ha.habitacionEstado = EstadoHabitacion.Habilitada;
            }
        }
        base.Escribir(ListaDeHabitacion, GestorBase.HABITACIONES);
    }

    public void DeshabilitarHabitacion(String id_habitacion) {
        for (Habitacion ha : ListaDeHabitacion) {
            if (ha.getHabId().equals(id_habitacion) && ha.habitacionEstado != EstadoHabitacion.EnUso) {
                ha.habitacionEstado = EstadoHabitacion.Deshabilitada;
            }
        }
        base.Escribir(ListaDeHabitacion, GestorBase.HABITACIONES);
    }

    public void HabilitarPiso(int pisoNumero) {
        String pisos = "abcdef";
        for (Habitacion ha : ListaDeHabitacion) {
            if (pisos.charAt(pisoNumero - 1) == ha.indicadorDePiso && ha.habitacionEstado != EstadoHabitacion.EnUso) {
                ha.habitacionEstado = EstadoHabitacion.Habilitada;
            }
        }
        base.Escribir(ListaDeHabitacion, GestorBase.HABITACIONES);
    }

    public void DeshabilitarPiso(int pisoNumero) {
        String pisos = "abcdef";
        for (Habitacion ha : ListaDeHabitacion) {
            if (pisos.charAt(pisoNumero - 1) == ha.indicadorDePiso && ha.habitacionEstado != EstadoHabitacion.EnUso) {
                ha.habitacionEstado = EstadoHabitacion.Deshabilitada;
            }
        }
        base.Escribir(ListaDeHabitacion, GestorBase.HABITACIONES);
    }

    public void EntregarHabitacion(String id_habitacion) {
        for (Habitacion ha : ListaDeHabitacion) {
            if (ha.getHabId().equals(id_habitacion)) {
                ha.habitacionEstado = EstadoHabitacion.EnUso;
            }
        }
        base.Escribir(ListaDeHabitacion, GestorBase.HABITACIONES);
    }

    public void EntregarHabitacionV(String dui, String fecha) {
        for (Reservacion e : ListaDeReservas) {
            if (e.PersonaAPagar.duiR().equals(dui) && fecha.equals(e.fechaIni) && e.Estado == EstadoReservacion.pagada) {
                e.Estado = EstadoReservacion.EnUso;
                base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
                for (String p : e.getHIDs()) {
                    EntregarHabitacion(p);
                    System.out.println("Se ha entregado correctamente la habitacion" + p);
                }
                break;
            }
            if (e.PersonaAPagar.duiR().equals(dui) && fecha.equals(e.fechaIni) && e.Estado == EstadoReservacion.Activa) {
                System.out.println("\033[35mNo ha pagado la reserva.");
            }
        }
        System.out.print("\033[32m");
    }

    public void HabilitarHabitacionF(String id_habitacion) {
        for (Habitacion ha : ListaDeHabitacion) {
            if (ha.getHabId().equals(id_habitacion) && ha.habitacionEstado == EstadoHabitacion.EnUso) {
                ha.habitacionEstado = EstadoHabitacion.Habilitada;
            }
        }
        base.Escribir(ListaDeHabitacion, GestorBase.HABITACIONES);
    }

    public void RetirarHabitacionP(String dui, String fecha) {
        int cont = 0;

        for (Reservacion e : ListaDeReservas) {
            if (e.PersonaAPagar.duiR().equals(dui) && fecha.equals(e.fechaIni) && e.Estado == EstadoReservacion.EnUso) {
                e.Estado = EstadoReservacion.Finalizada;
                for (String p : e.getHIDs()) {
                    HabilitarHabitacionF(p);
                    System.out.println("\033[35mSe ha retirado correctamente la habitacion " + p);
                }
                base.Escribir(ListaDeReservas, GestorBase.RESERVAS);
                cont=1;
                break;
            }
            cont = 10;
        }
        if (cont == 10) {
            System.out.println("\033[35mLos datos que ingreso no concuerda con ninguna reservacion que tenga una habitacion en uso");
        }
        System.out.print("\033[30m");

    }
    public void PrintH(){
        for(Reservacion p : ListaDeReservas){
            if(p.Estado==EstadoReservacion.EnUso){
                System.out.println("\033[32mLa habitacion \033[34m"+p.getId_habitacion()+"\033[32m tiene a los siguientes huespedes:");
                for(Huesped j : p.Huespesdes){
                    System.out.println("\033[34m"+j.Nombre+" "+j.Apellido+" "+j.duiR());
                }
            }
        }
        System.out.print("\033[30m");
    }
}
