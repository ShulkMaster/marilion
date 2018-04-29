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
public class Administrador  extends Persona {
    
    public String Username;
    private String Password;

    public Administrador(String username, String password, String Nombre, String Apellido, String Dui) {
        super(Nombre, Apellido, Dui);
        this.Username = username;
        this.Password = password;
    }

    public String getPassword() {
        return Password;
    }
    
    
    
    
}
