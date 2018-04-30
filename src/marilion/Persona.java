/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

/**
 *
 * @author yury_
 */
public class Persona {
    
    public String Nombre;
    public String Apellido;
    private String Dui;

    public Persona(String Nombre, String Apellido, String Dui) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Dui = Dui;
    }
    
    
    public String ToString()
    {
        return Nombre+"\n"+Apellido + "\n"+ Dui;
    }
    
    
}
