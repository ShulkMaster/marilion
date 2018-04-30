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
    public Reservacion reserv;
    public Huesped cliente;
    public float monto;
    public String fecha;
    
    
    @Override
    public String toString()
    {
        String re = reserv.Id_Reservacion + "\n"
                + cliente.ToString() + "\n"
                + monto + "\n"
                + fecha;
                
        
        return re;
    }
    
}
