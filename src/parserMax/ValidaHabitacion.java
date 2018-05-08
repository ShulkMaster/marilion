/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserMax;

/**
 *
 * @author gerar
 */
public class ValidaHabitacion {
    public static boolean Isvali(String hab)
    {
        boolean validacion = false;
        
        char indicador = hab.charAt(0);
        if(indicador == 'a' || indicador == 'b' || indicador == 'c' || indicador == 'd' || indicador == 'e' || indicador == 'f')
        {
            if(hab.length() == 3 && hab.charAt(1) == '1' && hab.charAt(2) == '0')
            {
                validacion = true;
            }
            else
            {
                if(hab.length() == 2)
                {
                    try {
                        int habitacionNumero  = Character.getNumericValue(hab.charAt(1));
                        validacion = true;
                    }
                    catch(Exception e)
                    {
                        
                    }
                }
            }
        }
        return validacion;
    }
}
