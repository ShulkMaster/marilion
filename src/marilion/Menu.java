/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import parserMax.Reader;
import java.util.ArrayList;
import parserMax.ValidaHabitacion;
import parserMax.FechaX;
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
        String StrMenu4 = "| 1. Cambio Fecha | 2. Cambio Principal | 3. Cambio Paquete | 4. Cambio de dias | 5. Cambio de acompanniantes | 6. Cambio fecha y dias |";
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
                    this.crearReservacion();
                   
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
                            this.CambioFechaND();
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
                        case 6:
                            this.CambioDeFechaYDias();
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

    private void PagoReservacion() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        //Limpiando Buffer antes de seguir o el dui queda nulo
        Reader.consola.nextLine();
        String duiPago = Reader.consola.nextLine();
        gestorHotel.PagarReservacion(duiPago);
        

    }

    private void CancelarReservacion() {
        System.out.println("Ingrese el dui de la persona a cancelar:");
        String duiPago = Reader.consola.next();
        System.out.println("Ingrese la fecha de la reserva a cancelar con el formato dd#mm#yy:");
        String fecha = Reader.consola.next();
        gestorHotel.CancelaReserva(duiPago,fecha);
    }

    private void CambioFechaND() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        boolean bool=gestorHotel.Comprobador(duiPago);
        while (bool==true) {
            System.out.println("Ingrese la fecha de la reserva:");
            System.out.println("Ingrese el dia:");
            String dia = Reader.consola.next();
            System.out.println("Ingrese el mes:");
            String mes = Reader.consola.next();
            System.out.println("Ingrese el annio:");
            String annio = Reader.consola.next();
            
            System.out.println("Ingrese la fecha de la nueva reserva:");
            System.out.println("Ingrese el dia:");
            String diaN = Reader.consola.next();
            System.out.println("Ingrese el mes:");
            String mesN = Reader.consola.next();
            System.out.println("Ingrese el annio:");
            String annioN = Reader.consola.next();

            String fecha = dia + "#" + mes + "#" + annio;
            String fechaN = diaN + "#" + mesN + "#" + annioN;
            gestorHotel.cambioFecha(duiPago,fecha,fechaN);
            break;
        }


    }
    
    private void CambioDias() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        boolean bool=gestorHotel.Comprobador(duiPago);
        while (bool==true) {
            System.out.println("Ingrese la fecha de la reserva:");
            System.out.println("Ingrese el dia:");
            String dia = Reader.consola.next();
            System.out.println("Ingrese el mes:");
            String mes = Reader.consola.next();
            System.out.println("Ingrese el annio:");
            String annio = Reader.consola.next();
            
            System.out.println("Ingrese los nuevos dias a quedarse:");
            int num = Reader.consola.nextInt();

            String fecha = dia + "#" + mes + "#" + annio;
            gestorHotel.cambioDias(duiPago,fecha,num);
            break;
        }


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
        
        System.out.println("Ingrese la fecha: ");
        String fecha = Reader.consola.next();
        
        Persona aux = new Persona(auxnombre, auxapellido, auxdui);
        gestorHotel.CambioPersonaP(duiPago, auxdui, auxnombre, auxapellido,fecha);
    }

    private void CambioPaquete() {
        Reader.consola.nextLine();
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.nextLine();
        System.out.print(duiPago+"\n");
        // metodo para saber si existe la reserva
        System.out.println("Ingrese la fecha: ");
        String fecha = Reader.consola.next();
        System.out.println("Ingrese 1 basico,2 premium,3 ninguno");
        int opcion = Reader.consola.nextInt();
        switch (opcion) {
            case 1:
                gestorHotel.cambioPack(duiPago, fecha ,PaqueteTipo.basico);
                break;
            case 2:
                gestorHotel.cambioPack(duiPago, fecha ,PaqueteTipo.premium);
                break;
            case 3:
                gestorHotel.cambioPack(duiPago, fecha ,PaqueteTipo.ninguno);
                break;
        }
    }



    private void CambioDeAcompanniantes() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        boolean bool=gestorHotel.Comprobador(duiPago);

        ArrayList<Persona> personas = new ArrayList<Persona>();

        System.out.println("Ingrese acompanniantes:");
        while (bool==true) {
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
        System.out.println("Ingrese el dui de la persona a pagar en la reserva:");
        String dui = Reader.consola.next();
        
        gestorHotel.EntregarHabitacionV(dui,FechaX.fechaEjemplo());
    }

    private void RetirarHabitacion() {
        System.out.println("Ingrese el dui de la persona a pagar en la reserva:");
        String dui = Reader.consola.next();
        System.out.println("Ingrese la fecha de la reserva:");
        String fecha=Reader.consola.next();
        
        gestorHotel.RetirarHabitacionP(dui, fecha);
    }

    private void habilitarHabitacion() {
        //metodo para ver todas las habitaciones deshabilitadas;
        System.out.println("Ingrese el id de la habitacion eje b3");
        String habiHabilitar = Reader.consola.next();
        if(/*ValidaHabitacion.Isvali(habiHabilitar)*/true)
        {
            gestorHotel.HabilitarHabitacionC(habiHabilitar);
        
        }
        
    }

    private void deshabilitarHabitacion() {
        //metodo para ver todas las habitaciones habilitadas;
        System.out.println("Ingrese el id de la habitacion eje b3");
        String habideshabilitar = Reader.consola.next();
        if(/*ValidaHabitacion.Isvali(habideshabilitar)*/true)
        {
            gestorHotel.DeshabilitarHabitacion(habideshabilitar);
        }
        
    }

    private void HabilitarPiso() {
        //metodo para ver todas los pisos deshabilitados
        System.out.println("Ingrese el numero del piso");
        int pisohabilitar = Reader.consola.nextInt();
        if(pisohabilitar <= 6)
        {
            gestorHotel.HabilitarPiso(pisohabilitar);
        }
        
    }

    private void deshaiblitarPiso() {
        //metodo para ver todos los pisos haabilitados
        System.out.println("Ingrese el numero del piso");
        int pisodeshabilitar = Reader.consola.nextInt();
        if(pisodeshabilitar <= 6)
        {
            gestorHotel.DeshabilitarPiso(pisodeshabilitar);
        }
        
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

    private void CambioDeFechaYDias() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        boolean bool=gestorHotel.Comprobador(duiPago);
        while (bool==true) {
            System.out.println("Ingrese la fecha de la reserva:");
            System.out.println("Ingrese el dia:");
            String dia = Reader.consola.next();
            System.out.println("Ingrese el mes:");
            String mes = Reader.consola.next();
            System.out.println("Ingrese el annio:");
            String annio = Reader.consola.next();
            
            System.out.println("Ingrese la fecha de la nueva reserva:");
            System.out.println("Ingrese el dia:");
            String diaN = Reader.consola.next();
            System.out.println("Ingrese el mes:");
            String mesN = Reader.consola.next();
            System.out.println("Ingrese el annio:");
            String annioN = Reader.consola.next();

            String fecha = dia + "#" + mes + "#" + annio;
            String fechaN = diaN + "#" + mesN + "#" + annioN;
            gestorHotel.cambioFecha(duiPago,fecha,fechaN);

            System.out.println("Ingrese los nuevos dias a quedarse:");
            int num = Reader.consola.nextInt();
            
            gestorHotel.cambioFechaYDias(duiPago,fecha,fechaN, num);
            break;
            
        }
    }

    private void crearReservacion() {
        gestorHotel.CrearReservacion();
    }

}
