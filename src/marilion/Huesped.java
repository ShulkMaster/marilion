/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

/**
 *
 * @author mcdre
 */
public class Huesped extends Persona {

    public Huesped(String Nombre, String Apellido, String Dui) {
        super(Nombre, Apellido, Dui);
    }

    /**
     *
     * @return String lo mismo que persona solo que las personas son inicas
     * y los huespedes no xd
     */
    @Override
    public String ToString() {
        return super.ToString() + ":";
    }

}
