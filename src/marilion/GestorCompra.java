/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import parserMax.FechaX;

/**
 *
 * @author mcdre
 */
public class GestorCompra {


    public static float PrecioDoble;
    public static float PrecioSencilla;

    public GestorCompra() {
        PrecioDoble = 120;
        PrecioSencilla = 70;

    }

    public static float RegresarPrecio(Reservacion re) {
        float costo = 0;
        float extra = 1.0f;
        float add = 0f;
        try{
        switch (re.tipo) {
            case basico:
                add = 10f;
                break;
            case premium:
                add = 150f;
                break;
                
            case ninguno:
                add = 0f;
                break;
        }
        for (String str : re.getHIDs()) {
            if (str.charAt(0) == 'e' || str.charAt(0) == 'f') {
                extra = 1.1f;
            } else {
                extra = 1.0f;
            }
            if (str.length() < 3 && Character.getNumericValue(str.charAt(1)) % 2 == 0) {
                costo += ((PrecioDoble + add) * re.dias) * extra;
            } else {
                if (str.length() == 3) {
                    costo += ((PrecioDoble + add) * re.dias) * extra;
                } else {
                    costo += ((PrecioSencilla + add) * re.dias) * extra;
                }

            }
        }

        return costo;}catch(Exception e){
            System.err.println("Error al procesar los datos.");
            return costo;
        }
    }

    public static Factura getFactura(Reservacion re) {
        return new Factura(re.getId_reservacion(), re.PersonaAPagar, GestorCompra.RegresarPrecio(re), FechaX.fechaEjemplo());
    }
}
