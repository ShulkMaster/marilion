/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.util.ArrayList;
import parserMax.Reader;
import parserMax.Asker;
import parserMax.MakerX;

/**
 *
 * @author gerar IDreserva,IDfactura,huespedes,IDhabitacion, requerido por el
 * contructor 1 dias,estado,pagador, tipo, fechaini
 */
public class Reservacion {

    public int dias;
    public EstadoReservacion Estado;
    public Persona PersonaAPagar;
    public String fechaIni;
    public PaqueteTipo tipo;
    public ArrayList<Huesped> Huespesdes;
    private int Id_reservacion, Id_factura;
    private String Id_habitacion;

    public Reservacion() {
        Huespesdes = new ArrayList<>();
    }

    public Reservacion(int Id_reservacion, int Id_factura, String Id_habitacion) {
        this.Id_reservacion = Id_reservacion;
        this.Id_factura = Id_factura;
        this.Id_habitacion = Id_habitacion;
        Huespesdes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return Id_reservacion + " "
                + Id_factura + " "
                + huesped2String() + " "
                + Id_habitacion + " "
                + dias + " "
                + Estado + " "
                + PersonaAPagar.toString() + " "
                + tipo + " "
                + fechaIni;
    }

    private String huesped2String() {
        String huespedeX = "";
        for (Huesped auxHues : Huespesdes) {
            huespedeX += auxHues.ToString();
        }
        return huespedeX;
    }

    public String[] parseID() {
        return Id_habitacion.split("#");
    }

    public String[] getHIDs() {
        return Id_habitacion.split(":");
    }

    public void selfAddHuesped(String masterRecord) {
        int contador = masterRecord.split(":").length;
        for (int i = 0; i < contador; i++) {
            Huespesdes.add(MakerX.creadohues(masterRecord.split(":")[i]));
        }
    }

    public void addHUesped(String HID, int hIndex) {
        int HHuesMax = Integer.parseInt(HID.split(":")[hIndex].substring(1));
        HHuesMax = HHuesMax % 2;
        if (HHuesMax == 0) {
            HHuesMax = 3;
        } else {
            HHuesMax = 2;
        }
        System.out.println("Ingrese " + HHuesMax + " huespedes");
        for (int i = 0; i < HHuesMax; i++) {
            Huespesdes.add(MakerX.creadohues(Asker.askHuesp()));
            System.out.println("¿Desea agregar mas huespedes?");
            System.out.println("1 = si  2=no");
            if (!Reader.consola.nextLine().equals("1")) {
                break;
            }
        }

    }

    public int getId_reservacion() {
        return Id_reservacion;
    }

    public void setId_reservacion(int Id_reservacion) {
        this.Id_reservacion = Id_reservacion;
    }

    public int getId_factura() {
        return Id_factura;
    }

    public void setId_factura(int Id_factura) {
        this.Id_factura = Id_factura;
    }

    public String getId_habitacion() {
        return Id_habitacion;
    }

    public void setId_habitacion(String Id_habitacion) {
        this.Id_habitacion = Id_habitacion;
    }

    public void setXtraHabitacion(String Id_habitacion) {
        Id_habitacion += (Id_habitacion);
    }
}
