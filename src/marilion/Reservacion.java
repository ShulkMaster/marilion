/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

/**
 *
 * @author gerar IDreserva,IDfactura,IDhuespedes,IDhabitacion, dias,estado,
 * pagador, tipo, fechaini
 */
public class Reservacion {

    public int Id_reservacion, Id_factura, Id_huespedes, dias;
    public String Id_habitacion;
    public EstadoReservacion Estado;
    public Persona PersonaAPagar;

    public void setPersonaAPagar(Persona PersonaAPagar) {
        this.PersonaAPagar = PersonaAPagar;
    }
    public String fechaIni;
    public PaqueteTipo tipo;

    public Reservacion() {
    }

    public Reservacion(int Id_reservacion, int Id_factura, int Id_huespedes, String Id_habitacion) {
        this.Id_reservacion = Id_reservacion;
        this.Id_factura = Id_factura;
        this.Id_huespedes = Id_huespedes;
        this.Id_habitacion = (Id_habitacion+":");
    }

    public String getId_habitacion() {
        return Id_habitacion;
    }

    public String[] parseID() {
        return Id_habitacion.split("#");
    }

    @Override
    public String toString() {
        return Id_reservacion + " "
                + Id_factura + " "
                + Id_huespedes + " "
                + Id_habitacion + " "
                + dias + " "
                + Estado + " "
                + PersonaAPagar.toString() + " "
                + tipo + " "
                + fechaIni;
    }

    public void setId_huespedes(int Id_huespedes) {
        this.Id_huespedes = Id_huespedes;
    }

    public void setXtraHabitacion(String Id_habitacion) {
        this.Id_habitacion += (Id_habitacion);
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

    public int getMaxHuesped() {
        int medida = Id_habitacion.split(":").length;
        System.out.println(medida);
        System.out.println(Id_habitacion);
        System.out.println(Id_habitacion.split(":")[0]);
        System.out.println(Id_habitacion.split(":")[0].substring(1));
        int base = Integer.parseInt(Id_habitacion.split(":")[0].substring(1));
        int base2;
        System.out.println(base);
        if (medida < 2) {
            base2 = Integer.parseInt(Id_habitacion.split(":")[1].substring(1));
            System.out.println(base2);
            base = base % 2;
            if (base == 0) {
                base = 3;
            } else {
                base = 2;
            }
            base2 = base2 % 2;
            if (base2 == 0) {
                base2 = 3;
            } else {
                base2 = 2;
            }
            base += base2;
        } else {
            base = base % 2;
            if (base == 0) {
                base = 3;
            } else {
                base = 2;
            }
        }
        return base;
    }

    public String[] getHIDs() {
        System.out.println("Usted invoco la habitacion ID; " + Id_habitacion.split(":"));
        return Id_habitacion.split(":");
    }

}
