/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

/**
 *
 * @author gerar
 */
public class Reservacion {
    public int dias, Id_reservacion, Id_factura, Id_huespedes;
    String Id_habitacion;
    public EstadoReservacion Estado;
    public Persona PersonaAPagar;
    public String fechaIni;
    public PaqueteTipo tipo;

    public Reservacion(int Id_reservacion, int Id_factura, int Id_huespedes, String Id_habitacion) {
        this.Id_reservacion = Id_reservacion;
        this.Id_factura = Id_factura;
        this.Id_huespedes = Id_huespedes;
        this.Id_habitacion = Id_habitacion;
    }

    @Override
    public String toString() {
        return Id_reservacion+" "+
               Id_factura+" "+
               Id_huespedes+" "+
               Id_habitacion;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void setId_habitacion(String Id_habitacion) {
        this.Id_habitacion = Id_habitacion;
    }

    public void setEstado(EstadoReservacion Estado) {
        this.Estado = Estado;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public void setTipo(PaqueteTipo tipo) {
        this.tipo = tipo;
    }
    
    
    
    
    
}
