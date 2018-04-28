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

    //controla el while principal del programa
    private Boolean alive;
    private GestorHotel gestorHotel;
    private int opcionSelected;
    //son submenus del menu y recibe un array de cadenas
    private final tabes[] submenus = new tabes[3];
    public static Menu menu;

    //esto es solo el texto del menu aqui pueden lo que dicen las opciones
    private final String[] reservaSubMenu = {
        "<---------------------------------->\n",
        "         Submenu: Reservas\n",
        "\t1) Crear nueva reserva\n",
        "\t2) Cancelar Reserva\n",
        "\t3) Modificar\n",
        "\t0) Regresar al menu anterior\n",
        "<---------------------------------->\n"
    };
    private final String[] habitacionSubMenu = {
        "<---------------------------------->\n",
        "         Submenu: Habitaciones\n",
        "\t1) Verificar Habitacion\n",
        "\t2) Deshabilitar o rehabilitar Habitacion\n",
        "\t0) Regresar al menu anterior\n",
        "<---------------------------------->\n"
    };
    private final String[] CobroSubMenu = {
        "<---------------------------------->\n",
        "        Submenu: cobro\n",
        "\t1) Pagar Reserva\n",
        "\t2) Registra algo\n",
        "\t0) Regresar al menu anterior\n",
        "<---------------------------------->\n"
    };

    private Menu() {
        alive = true;
    }

    public static void Init() {
        if (menu == null) {
            menu = new Menu();
            menu.InitSubmenus();
        } else {
            System.out.println("El menu ya existe");
        }
    }

    private void InitSubmenus() {
        submenus[0] = new tabes(reservaSubMenu);
        submenus[1] = new tabes(habitacionSubMenu);
        submenus[2] = new tabes(CobroSubMenu);
    }

    public void Start() {
        while (alive) {
            ShowMenu();
            GaderAnw();
        }

    }

    private void ShowMenu() {
        System.out.print("<---------------------------------->\n"
                + "           MENU PRINCIPAL\n"
                + "<---------------------------------->\n"
                + "\t1) Reservaciones\n"
                + "\t2) Habitaciones\n"
                + "\t3) Cobrar\n"
                + "\t0) salir\n"
                + "<---------------------------------->\n");
    }

    private void GaderAnw() {
        opcionSelected = Reader.consola.nextInt();
        switch (opcionSelected) {
            case 1:
                submenus[0].isActive = true;
                submenus[0].launchSubMenu();
                break;
            case 2:
                submenus[1].isActive = true;
                submenus[1].launchSubMenu();
                break;
            case 3:
                submenus[2].isActive = true;
                submenus[2].launchSubMenu();
                break;
            case 0:
                System.out.println("Adios");
                alive = false;
            default:
                System.err.println("Opcion no valida");
                break;
        }

    }

    private class tabes {

        public Boolean isActive;
        private int subOption;
        private final String[] menuText;

        public tabes(String[] menulines) {
            menuText = menulines;
            isActive = true;
        }

        private void impresor() {
            for (String printosa1 : menuText) {
                System.out.println(printosa1);
            }
        }

        private void exeSubOption() {
            subOption = Reader.consola.nextInt();
            switch (subOption) {
                case 1:
                    //aqui se agrega el codigo de lo que hace
                    System.out.println("La accion se ejecuto");
                    break;
                case 2:
                    //aqui se agrega el codigo de lo que hace
                    System.out.println("La accion se ejecuto");
                    break;
                case 3:
                    //aqui se agrega el codigo de lo que hace
                    System.out.println("La accion se ejecuto");
                    break;
                case 0:
                    System.out.println("Adios");
                    isActive = false;
                default:
                    System.err.println("Opcion no valida");
                    break;
            }
        }

        public void launchSubMenu() {
            while (isActive) {
                impresor();
                exeSubOption();
            }
        }

    }

}
