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

    ArrayList<Administrador> admins;
    GestorHotel gestorHotel;
    private final GestorBase archivador = new GestorBase();
    boolean Finalizado;

    public Menu() {
        //quitar comentario cuando se cree el gestor de base de datos
        
        admins = new ArrayList<Administrador>();
        //admins = gestorHotel.ListaDeAdmins;
        Finalizado = false;

    }
    
    public boolean login(String Username, String Pass)
    {
        for(Administrador admin: admins)
        {
            if(admin.Username.equals(Username))
            {
                if(admin.getPassword().equals(Pass))
                {
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
        String fecha="30#4#2018";
        int id=0353715;
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
                    switch(opcion){
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
                    switch(opcion)
                    {
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
                    switch(opcion)
                    {
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
        int dias;
        String fecha;
        ArrayList<Persona> personas = new ArrayList<Persona>();
        Persona perpagar;
        
        while(true)
        {
            System.out.println("Ingrese la fecha:");
            System.out.println("Ingrese el dia:");
            int dia = Reader.consola.nextInt();
            System.out.println("Ingrese el mes:");
            int mes = Reader.consola.nextInt();
            System.out.println("Ingrese el annio:");
            int annio = Reader.consola.nextInt();
            System.out.println("Ingrese los dias a quedarse ");
            dias = Reader.consola.nextInt();
            //algun metodo que defina si puede hacer la reservacion y si hay habitaciones disponibles
            //aqui adentro voy a crear las habitaciones cuando haya metodo que devvuelva habitaciones
            System.out.println("Ingrese id habitaciones:");
            String habitaciones_ids = Reader.consola.next();
            if(true)//aquiva el metodo)
            {
                fecha = dia +"/"+ mes + "/"+annio;
                
                break;
            }
        }
        
        System.out.println("Ingrese acompanniantes:");
        while(true)
        {
            System.out.println("Ingrese el nombre: ");
            String auxnombre = Reader.consola.next();
            System.out.println("Ingrese el apellido: ");
            String auxapellido = Reader.consola.next();
            System.out.println("Ingrese el dui: ");
            String auxdui = Reader.consola.next();
            Persona aux = new Persona(auxnombre,auxapellido,auxdui);
            personas.add(aux);
            System.out.println("end 1");
            if(Reader.consola.nextInt() == 1)
            {
                break;
            }
        }
        
        System.out.println("Ingrese el nombre de la persona a pagar: ");
            String nombrepagar = Reader.consola.next();
            System.out.println("Ingrese el apellido: ");
            String apellidopagar = Reader.consola.next();
            System.out.println("Ingrese el dui: ");
            String duipagar = Reader.consola.next();
        
    }

    private void PagoReservacion() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        //metodo del gestor de compra, el gestor de compra crea factura necesito que me retornes costo segun reserva y la factura impresa
        // en pantalla
        
    }

    private void CancelarReservacion() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        //metodo del gestor de hotel
    }
    
    private void CambioFecha()
    {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        //metodo para saber si existe la reservacion
        while(true)
        {
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
            if(true)//aquiva el metodo)
            {
                String fecha = dia +"/"+ mes + "/"+annio;
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
            Persona aux = new Persona(auxnombre,auxapellido,auxdui);
        //aca el metodo del gestor que reciba esto y cambie todo en el gestor
    }

    private void CambioPaquete() {
        System.out.println("Ingrese el dui de la persona a pagar:");
        String duiPago = Reader.consola.next();
        // metodo para saber si existe la reserva
        System.out.println("Ingrese 1 basico,2 premium,3 ninguno");
        int opcion = Reader.consola.nextInt();
        switch(opcion)
        {
            case 1:
                //aca va el cambio dentro de la reserva del tipo o el llamado a gestor de hotel
                break;
            case 2:
                
                break;
            case 3:
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
        while(true)
        {
            System.out.println("Ingrese el nombre: ");
            String auxnombre = Reader.consola.next();
            System.out.println("Ingrese el apellido: ");
            String auxapellido = Reader.consola.next();
            System.out.println("Ingrese el dui: ");
            String auxdui = Reader.consola.next();
            Persona aux = new Persona(auxnombre,auxapellido,auxdui);
            personas.add(aux);
            System.out.println("end 1");
            if(Reader.consola.nextInt() == 1)
            {
                break;
            }
        }
        // metodo para mandar la lista y que el gestor de hotel los cambie 
    }

    private void VerListaDeReservaciones() {
        archivador.printListReservas();
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
        archivador.printListFacturas();
    }

    private void DB_Reservas() {
        archivador.printListReservas();
    }

    private void DB_HuespedesActuales() {
        archivador.printListAdmin();
    }

    private void DB_Habitaciones() {
        archivador.printListHabitacion();
    }

}
