/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserMax;

import marilion.Administrador;
import marilion.EstadoHabitacion;
import marilion.Factura;
import marilion.Habitacion;
import marilion.Huesped;
import marilion.Persona;

/**
 *
 * @author yury_
 */
public class MakerX {

    /**
     * este metodo crea personas con el formato estandar leido desde archivo
     *
     * @param personadetail es la cadena maestra que contiene una linea del
     * archivo de texto en que se guarda la informacion, ese registro contiene
     * toda la informacion necesaria para recontruir una habitacion en memoria
     * RAM
     * @return Persona
     */
    public static Persona creadoPersona(String personadetail) {
        String nombre, apellido, dui;
        nombre = personadetail.split("#")[0];
        apellido = personadetail.split("#")[1];
        dui = personadetail.split("#")[2];
        return new Persona(nombre, apellido, dui);
    }

    public static Huesped creadohues(String personadetail) {
        String nombre, apellido, dui;
        nombre = personadetail.split("#")[0];
        apellido = personadetail.split("#")[1];
        dui = personadetail.split("#")[2];
        return new Huesped(nombre, apellido, dui);
    }

    /**
     * este metodo crea habitacion para probar el gestor de bases por que a
     * gerar se le ocurrio que seria buena idea tener una ArrayList dentro de un
     * arraylist dentro del objeto y se quejan de mi menu :v
     *
     * @param master es la cadena maestra que contiene una linea del archivo de
     * texto en que se guarda la informacion, ese registro contiene toda la
     * informacion necesaria para recontruir una habitacion en memoria RAM
     * @return Habitacion
     */
    public static Habitacion creadbita(String[] master) {
        char indica = master[0].split("#")[0].charAt(0);
        int index = Integer.parseInt(master[0].split("#")[1]);
        EstadoHabitacion estado = StatadosX.parseStateHabitacion(master[1]);
        return new Habitacion(indica, index, estado);

    }

    public static Administrador creadoAdmin(String master) {
        String username, password, nombre, apellido, dui;
        username = master.split(" ")[0];
        password = master.split(" ")[1];
        nombre = master.split(" ")[2];
        apellido = master.split(" ")[3];
        dui = master.split(" ")[4];
        return new Administrador(username, password, nombre, apellido, dui);
    }

    /**
     * este metodo crea una factura para probar el gestor de bases por que a
     * gerar se le ocurrio que seria buena idea tener una ArrayList dentro de un
     * arraylist dentro del objeto y se quejan de mi menu :v
     *
     * @param master es la cadena maestra que contiene una linea del archivo de
     * texto en que se guarda la informacion, ese registro contiene toda la
     * informacion necesaria para recontruir una factura en memoria RAM
     * @return factura
     */
    public static Factura creadoFactura(String master) {
        int Id_fact;
        float monto2;
        Persona client2;
        String fecha;
        Id_fact = Integer.parseInt(master.split(" ")[0]);
        client2 = MakerX.creadoPersona(master.split(" ")[1]);
        monto2 = Float.parseFloat(master.split(" ")[2]);
        fecha = master.split(" ")[3];
        /*en el archivo los atributos de personas clientes se separan por #
          este metodo devuelve el arreglo de cadenas con los diferentes
          atributos del cliente en la reservacion
         */
        return new Factura(Id_fact, client2, monto2, fecha);

    }

}
