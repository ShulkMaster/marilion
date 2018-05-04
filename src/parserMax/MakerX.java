/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserMax;

import marilion.Persona;

/**
 *
 * @author yury_
 */
public class MakerX {

    /**
     * este metodo crea personas con el formato estandar leido desde archivo
     *@param personadetail
     * es la cadena maestra que contiene una linea del archivo de
     * texto en que se guarda la informacion, ese registro contiene toda la
     * informacion necesaria para recontruir una habitacion en memoria RAM
     * @return Persona
     */
    public static Persona creadoPersona(String personadetail) {
        String nombre, apellido, dui;
        nombre = personadetail.split("#")[0];
        apellido = personadetail.split("#")[1];
        dui = personadetail.split("#")[2];
        return new Persona(nombre, apellido, dui);
    }
/*
    public void add(String parte) {
        masterString += parte;
    }

    public String getValue() {
        return masterString;
    }

    public int getCxleng() {
        int base, multiplicador;
        System.out.println(masterString.split(" ")[3].substring(2));
        base = Integer.parseInt(masterString.split(" ")[3].substring(2));
        if ((base % 2) == 0) {
            base = 3;
        } else {
            base = 2;
        }

        return base;
    }

    public String getValueAt(int index) {
        return masterString.split(" ")[index];
    }
*/
}
