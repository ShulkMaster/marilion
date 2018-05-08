/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserMax;

import java.util.Scanner;

/**
 *
 * @author yury_
 */
public class Reader {
    public static final Scanner consola = new Scanner(System.in);
    Scanner sc;
    public String getWord(String word){
        String palabra ="";
        while(palabra.equals("")){
            System.out.println(word);
            palabra = sc.nextLine();
            if (!palabra.matches("^[A-Za-z]*$\\D{0}")){palabra = "";}
        }
        return palabra;
    }
}
