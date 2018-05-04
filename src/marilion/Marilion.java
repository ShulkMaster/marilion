package marilion;

import parserMax.Reader;

/**
 *
 * @author yury_
 */
public class Marilion {

    public static void main(String[] args) {
        // TODO code application logic here
        Menu menu = new Menu();

        while (true) {
            System.out.print("Ingrese Usuario: ");
            String user = Reader.consola.next();
            System.out.print("Ingrese contrasennia: ");
            String pass = Reader.consola.next();
            if (menu.login(user, pass)) {
                menu.Iniciar();
            } else {
                System.out.println("Usuario o contrasennia incorrectos");
                System.out.println("Ingrese 1 para salir: ");
                int ex = Reader.consola.nextInt();
                if(ex == 1)
                {
                    break;
                }
            }

        }

    }

}
