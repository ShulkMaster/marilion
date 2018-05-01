/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.util.Date;

/**
 *
 * @author mcdre
 */
public class Factura {

    public int Id_factura;
    public Persona cliente;
    public float monto;
    public String fecha;

    public Factura(int reserv, Persona cliente, float monto, String fecha) {
        this.Id_factura = reserv;
        this.cliente = cliente;
        this.monto = monto;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return Id_factura+" "+cliente.ToString()+" "+monto+" "+fecha;
    }

}
