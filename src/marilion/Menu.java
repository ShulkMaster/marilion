/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.util.ArrayList;

/**
 *
 * @author yury_
 */
public class Menu {

    private final ArrayList<Administrador> admins;
    private final GestorHotel gestorHotel;
    private final GestorBase archivador;
    boolean Finalizado;

    public Menu() {
        gestorHotel = new GestorHotel();
        admins = gestorHotel.ListaDeAdmins;
        archivador = GestorHotel.base;
        Finalizado = false;
    }

    public boolean login(String Username, String Pass) {
        for (Administrador admin : admins) {
            if (admin.Username.equals(Username)) {
                if (admin.getPassword().equals(Pass)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void Opciones(int elect) {
        String StrMenu = "| 1. Crear Reservacion   | 2. pagar Reservacion  | 3. Cancelar Reservacion  | 4. Cambiar Reservacion | 5. Ver lista de Reservacion |\n"
                + "| 6. Entregar Habitacion | 7. Retirar Habitacion | 8. Gestion de Habitacion | 9. DB options          | 10. Exit                    |";
        String StrMenu4 = "| 1. Cambio Fecha | 2. Cambio Principal | 3. Cambio Paquete | 4. Cambio de dias | 5. Cambio de acompanniantes |";
        String StrMenu8 = "| 1. Habilitar Habitacion | 2. deshabilitar habitacion | 3. habilitar piso | 4. deshabilitar piso | ";
        String StrMenu9 = "| 1. Facturas | 2. Reservas | 3. Huespedes Actuales | 4. Habitaciones |";
        switch (elect) {
            case 1:
                System.out.println(StrMenu);
                break;
            case 4:
                System.out.println(StrMenu4);
                break;
            case 8:
                System.out.println(StrMenu8);
                break;
            case 9:
                System.out.println(StrMenu9);
                break;
        }
    }

    public void Iniciar() {
        int opcion;
        while (!Finalizado) {
            this.Opciones(1);
            opcion = Reader.consola.nextInt();

            switch (opcion) {
                case 1:
                    //crear reservacion
                    this.CrearReservacion();
                    break;
                case 2:
                    this.PagoReservacion();
                    break;
                case 3:
                    this.CancelarReservacion();
                    break;
                case 4:
                    this.Opciones(4);
                    opcion = Reader.consola.nextInt();
                    switch (opcion) {
                        case 1:
                            //this.CambioFecha(id,this.DB_Reservas(),fecha);
                            break;
                        case 2:
                            this.cambioPrincipal();
                            break;
                        case 3:
                            this.CambioPaquete();
                            break;
                        case 4:
                            this.CambioDias();
                            break;
                        case 5:
                            this.CambioDeAcompanniantes();
                            break;
                    }
                    break;
                case 5:
                    this.VerListaDeReservaciones();
                    break;
                case 6:
                    this.EntregarHabitacion();
                    break;
                case 7:
                    this.RetirarHabitacion();
                    break;
                case 8:
                    this.Opciones(8);
                    opcion = Reader.consola.nextInt();
                    switch (opcion) {
                        case 1:
                            this.habilitarHabitacion();
                            break;
                        case 2:
                            this.deshabilitarHabitacion();
                            break;
                        case 3:
                            this.HabilitarPiso();
                            break;
                        case 4:
                            this.deshaiblitarPiso();
                            break;
                    }
                    break;
                case 9:
                    this.Opciones(9);
                    opcion = Reader.consola.nextInt();
                    switch (opcion) {
                        case 1:
                            this.DB_Facturas();
                            break;
                        case 2:
                            this.DB_Reservas();
                            break;
                        case 3:
                            this.DB_HuespedesActuales();
                            break;
                        case 4:
                            this.DB_Habitaciones();
                            break;
                    }
                    break;
                case 10:
                    Finalizado = !Finalizado;
                    break;

            }

        }
    }

    private void CrearReservacion() {
        String master;
        /**
         * mantene el mismo formato de archvo al final en la cadena master
         * IDhabitacion, dias, persona a pagar, tipo de packete, fechaini los
         * demas IDs son autogenerados por la base y el estado cuando creas una
         * reservacion por defecto es activa, para mas info mira la clase reservacion
         */
        //limpiando Buffer podes cambiar el orden que se ejecuta el menu solo
        Reader.consola.nextLine();
        System.out.println("Habitaciones disponibles: ");
        gestorHotel.showListHabitDispo("5#5#2018","3");//fecha dia
        System.out.println("Ingrese las habitaciones:");
        master = Reader.consola.nextLine();//split[0]
        System.out.println("Ingrese el numero de dias para hospedarse: ");
        master += (" " + Reader.consola.nextLine());//split[1]
        master += " 1";
        System.out.println("Ingrese el nombre de la persona a pagar: ");
        master += (" " + Reader.consola.nextLine());
        System.out.println("Ingrese el apellido: ");
        master += ("#" + Reader.consola.nextLine());
        System.out.println("Ingrese el dui: ");
        master += ("#" + Reader.consola.nextLine());
        System.out.println("Ingrese tipo de paquete: ");
        System.out.println("1 = Basico 2 = Primium 3 = Ninguno");
        master += (" " + Reader.consola.nextLine());
        System.out.println("Ingrese la fecha formato d#M#yyyy:");
        System.out.println("Por ejemplo la fecha de hoy seria: " + parserMax.FechaX.fechaEjemplo());
        master += (" " + parserMax.FechaX.paser(Reader.consola.nextLine()));//split[1]
        System.out.println(master + " Debug");
        //algun metodo que defina si puede hacer la reservacion y si hay habitaciones disponibles
        //aqui adentro voy a crear las habitaciones cuando haya metodo que devvuelva habitaciones
        gestorHotel.ReservaNueva(master);
        /*
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
    }

    private void PagoReservacion() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        //Limpiando Buffer antes de seguir o el dui queda nulo
        Reader.consola.nextLine();
        String duiPago = Reader.consola.nextLine();
        System.out.println("Ingrese la fecha de la reserva:");
        String fecha = Reader.consola.nextLine();
        System.out.println(duiPago + " " + fecha);
        //metodo del gestor de compra, el gestor de compra crea factura necesito que me retornes costo segun reserva y la factura impresa
        // en pantalla
        gestorHotel.pagarReserva(duiPago, fecha);

    }

    private void CancelarReservacion() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        gestorHotel.CancelaReserva(duiPago);
    }

    private void CambioFecha() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        //metodo para saber si existe la reservacion
        while (true) {
            System.out.println("Ingrese la fecha:");
            System.out.println("Ingrese el dia:");
            int dia = Reader.consola.nextInt();
            System.out.println("Ingrese el mes:");
            int mes = Reader.consola.nextInt();
            System.out.println("Ingrese el annio:");
            int annio = Reader.consola.nextInt();
            System.out.println("Ingrese los dias a quedarse ");
            int dias = Reader.consola.nextInt();
            //algun metodo que defina si puede hacer la reservacion y si hay habitaciones disponibles
            if (true)//aquiva el metodo)
            {
                String fecha = dia + "/" + mes + "/" + annio;
                break;
            }
        }
        //aca va el verdadero cambio de fecha

    }

    /* private ArrayList<Reservacion> CambioFechaAlgo(int id ,ArrayList<Reservacion> listR,String fecha) {
        listR=gestorHotel.cambioFecha(id, listR, fecha);
        return listR;
    }*/
    private void cambioPrincipal() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        // metodo para saber si existe la reserva
        System.out.println("Ingrese el nombre del nuevo titular: ");
        String auxnombre = Reader.consola.next();
        System.out.println("Ingrese el apellido: ");
        String auxapellido = Reader.consola.next();
        System.out.println("Ingrese el dui: ");
        String auxdui = Reader.consola.next();
        Persona aux = new Persona(auxnombre, auxapellido, auxdui);
        gestorHotel.CambioPersonaP(duiPago, auxdui, auxnombre, auxapellido);
    }

    private void CambioPaquete() {
        Reader.consola.nextLine();
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.nextLine();
        System.out.print(duiPago+"\n");
        // metodo para saber si existe la reserva
        System.out.println("Ingrese 1 basico,2 premium,3 ninguno");
        int opcion = Reader.consola.nextInt();
        switch (opcion) {
            case 1:
                gestorHotel.cambioPack(duiPago, PaqueteTipo.basico);
                break;
            case 2:
                gestorHotel.cambioPack(duiPago, PaqueteTipo.premium);
                break;
            case 3:
                gestorHotel.cambioPack(duiPago, PaqueteTipo.ninguno);
                break;
        }
    }

    private void CambioDias() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();

        System.out.println("Ingrese la nueva cantidad de dias ");
        int nuevosdias = Reader.consola.nextInt();
        //aca metodo que verifique y notifique si se hizo el cambio
    }

    private void CambioDeAcompanniantes() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        // metodo para saber i existe la reserva

        ArrayList<Persona> personas = new ArrayList<Persona>();

        System.out.println("Ingrese acompanniantes:");
        while (true) {
            System.out.println("Ingrese el nombre: ");
            String auxnombre = Reader.consola.next();
            System.out.println("Ingrese el apellido: ");
            String auxapellido = Reader.consola.next();
            System.out.println("Ingrese el dui: ");
            String auxdui = Reader.consola.next();
            Persona aux = new Persona(auxnombre, auxapellido, auxdui);
            personas.add(aux);
            System.out.println("end 1");
            if (Reader.consola.nextInt() == 1) {
                break;
            }
        }
        // metodo para mandar la lista y que el gestor de hotel los cambie 
    }

    private void VerListaDeReservaciones() {
        gestorHotel.printListReservas();
    }

    private void EntregarHabitacion() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        //aca el metodo del gestor de hotel si se puede efectuar la entrega o no
    }

    private void RetirarHabitacion() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        //aca el metodo del gestor de hotel para retirar habitacion y los huespedes que estaban en ese reserva
    }

    private void habilitarHabitacion() {
        //metodo para ver todas las habitaciones deshabilitadas;
        System.out.println("Ingrese el id de la habitacion eje b3");
        String habiHabilitar = Reader.consola.next();
        // aca el metodo donde envie esa string y se cambie el estado de la habitacion
    }

    private void deshabilitarHabitacion() {
        //metodo para ver todas las habitaciones habilitadas;
        System.out.println("Ingrese el id de la habitacion eje b3");
        String habideshabilitar = Reader.consola.next();
        // aca el metodo donde envie esa string y se cambie el estado de la habitacion
    }

    private void HabilitarPiso() {
        //metodo para ver todas los pisos deshabilitados
        System.out.println("Ingrese el numero del piso");
        int pisohabilitar = Reader.consola.nextInt();
        // aca el metodo donde envie el int y el gestor se encarga de todo
    }

    private void deshaiblitarPiso() {
        //metodo para ver todos los pisos haabilitados
        System.out.println("Ingrese el numero del piso");
        int pisodeshabilitar = Reader.consola.nextInt();
        // aca el metodo donde envie el int y el gestor se encarga de todo
    }

    private void DB_Facturas() {
        archivador.printListFacturas(gestorHotel.ListaDeFactura);
    }

    private void DB_Reservas() {
        archivador.printListReservas(gestorHotel.ListaDeReservas);
    }

    private void DB_HuespedesActuales() {
        archivador.printListAdmin(gestorHotel.ListaDeAdmins);
    }

    private void DB_Habitaciones() {
        archivador.printListHabitacion(gestorHotel.ListaDeHabitacion);
    }

}
