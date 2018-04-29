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
                            this.CambioFecha();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void PagoReservacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void CancelarReservacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void CambioFecha() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cambioPrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void CambioPaquete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void CambioDias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void CambioDeAcompanniantes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void VerListaDeReservaciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EntregarHabitacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void RetirarHabitacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void habilitarHabitacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deshabilitarHabitacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void HabilitarPiso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deshaiblitarPiso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void DB_Facturas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void DB_Reservas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void DB_HuespedesActuales() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void DB_Habitaciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
