/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserMax;

/**
 *
 * @author yury_
 */
public class Asker {

    public static String askPerson() {
        String master = "";
        System.out.println("Ingrese el nombre de la Persona a pagar: ");
        master += Reader.consola.nextLine();
        System.out.println("Ingrese el Apellido de la Persona a pagar: ");
        master += ("#"+Reader.consola.nextLine());
        System.out.println("Ingrese el DUI de la Persona a pagar: ");
        master += ("#"+Reader.consola.nextLine());
        return master;
    }
    
    public static String askHabitad(){
    String master = "";
    
    
    return master;
    }

}