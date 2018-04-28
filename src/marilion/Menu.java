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
public class Menu {
    GestorHotel gestorHotel;
    public static Menu menu;

    private Menu() {
    }
    
    public static void Init(){
        if(menu == null){
            menu = new Menu();
        }
        else{
            System.out.println("El menu ya existe");
        }
    }
    
    
    
}
