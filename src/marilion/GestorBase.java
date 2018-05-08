/*
        System.out.println("\033[30mEste texto es Negro");
        System.out.println("\033[31mEste texto es Rojo");
        System.out.println("\033[32m Este texto es Verde");
        System.out.println("\033[33m Este texto es Amarillo");
        System.out.println("\033[34mEste texto es Azul");
        System.out.println("\033[35mEste texto es Magenta");
        System.out.println("\033[36mEste texto es Cyan");
        System.out.println("\033[37mEste texto es Blanco");
        System.out.println((char)27 + "[34;43mEjemplo de texto azul y fondo amarillo");
 */
package marilion;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import parserMax.StatadosX;
import parserMax.MakerX;

/**
 *
 * @author yury_
 */
public class GestorBase {

    //se cambiaro los 4 archivos por uno generico que lo carga 1 a la vez
    public static int lastIDReserva = 0;
    public static int lastIDFactura = 0;
    public static final String RESERVAS = "reservas";
    public static final String FACTURAS = "facturas";
    public static final String HABITACIONES = "habitaciones";
    private File archivoTXT;

    public GestorBase() {
    }

    private ArrayList<String> getFileContent(String filename) {
        ArrayList<String> filecontent = new ArrayList<>();
        String cadenaAux;
        archivoTXT = new File(filename + ".txt");
        FileReader lectorArchivo;
        try {
            lectorArchivo = new FileReader(archivoTXT);
            try (BufferedReader lectorLines = new BufferedReader(lectorArchivo)) {
                while ((cadenaAux = lectorLines.readLine()) != null) {
                    filecontent.add(cadenaAux);
                }
            }
            lectorArchivo.close();
            archivoTXT = null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorBase.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se encontro el archivo " + archivoTXT.getName());
            System.out.println("\033[34mCreando nuevo;");

        } catch (IOException ex) {
            Logger.getLogger(GestorBase.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se puede leer el archivo " + archivoTXT.getName());
        }
        return filecontent;
    }

    /**
     * metodo crear huespedes
     *
     * @param huespedes sera un arreglo de cadenas y cada cadena lleva la
     * informacion nesesaria para poder crear un huesped, es decir Nombre,
     * Apellido, Dui
     * @return Un grupo de huespedes basadoes en el arreglo que se pasa como
     * parametro
     */
    private Huesped creadoHuesped(String master) {
        String nombre, apellido, dui;
        nombre = master.split("#")[0];
        apellido = master.split("#")[1];
        dui = master.split("#")[2];
        return new Huesped(nombre, apellido, dui);
    }

    private ArrayList<Huesped> creadoHuesped(String[] huespedes) {
        ArrayList<Huesped> listaAux = new ArrayList<>();
        String name, lastname, dui;
        //en el archito de texto los atributos de cada huesped se separan con #
        for (String huespedIndex : huespedes) {
            name = huespedIndex.split("#")[0];
            lastname = huespedIndex.split("#")[1];
            dui = huespedIndex.split("#")[2];
            listaAux.add(new Huesped(name, lastname, dui));
        }
        return listaAux;
    }

    private Reservacion creadoReservacion(String master) {
        int reserva, factura;
        String Id_Habitacion;
        reserva = Integer.parseInt(master.split(" ")[0]);
        factura = Integer.parseInt(master.split(" ")[1]);
        System.out.println(reserva + " " + factura);
        Id_Habitacion = master.split(" ")[3];
        Reservacion auxReser = new Reservacion(reserva, factura, Id_Habitacion);
        auxReser.selfAddHuesped(master.split(" ")[2]);
        auxReser.dias = Integer.parseInt(master.split(" ")[4]);
        auxReser.Estado = StatadosX.parseStateReserva(master.split(" ")[5]);
        auxReser.PersonaAPagar = creadoHuesped(master.split(" ")[6]);
        auxReser.tipo = StatadosX.parseStatPack(master.split(" ")[7]);
        auxReser.fechaIni = master.split(" ")[8];
        return auxReser;
    }

    public void printListHabitacion(ArrayList<Habitacion> param) {
        char maxChar = 'f';
        int maxHabid = 10;
        int controles = 0;
        for (int i = 'a'; i < maxChar + 1; i++) {
            for (int j = 0; j < maxHabid; j++) {
                switch (param.get(controles).habitacionEstado) {
                    case Habilitada:
                        System.out.print("\033[32m[" + param.get(controles).getHabId().toUpperCase() + "]" + " ");
                        break;
                    case EnUso:
                        System.out.print("\033[34m[" + param.get(controles).getHabId().toUpperCase() + "]" + " ");
                        break;
                    case Deshabilitada:
                        System.out.print("\033[35m[" + param.get(controles).getHabId().toUpperCase() + "]" + " ");
                        break;
                    default:
                        System.out.print((char) 27 + "[34;43m[?]" + " ");
                        break;
                }
                controles++;
            }
            System.out.println();
        }
        System.out.println("\033[32m█ Habilitada");
        System.out.println("\033[34m█ En uso");
        System.out.println("\033[35m█ Deshabilitada");
        System.out.println((char) 27 + "[34;43m[?] Desconocido");
        System.out.print((char) 27 + "[30;47m");
    }

    public void printListFacturas(ArrayList<Factura> master) {
        int contador = 1;
        for (Factura auxF : master) {
            System.out.println("<-------------- Factura " + contador + "-------------------->");
            System.out.println("ID factura: " + auxF.Id_factura);
            System.out.println("Cliente: " + auxF.cliente.Nombre + " " + auxF.cliente.Apellido);
            System.out.println("Monto a pagar: " + auxF.monto);
            System.out.println("Fecha: " + auxF.fecha);
            contador++;
        }
    }

    public void printListReservas(ArrayList<Reservacion> master) {
        int contador = 1;
        for (Reservacion auxF : master) {
            System.out.println("<-------------- Reservacion " + contador + "-------------------->");
            System.out.println("ID reservacion: " + auxF.getId_reservacion());
            System.out.println("ID factura: " + auxF.getId_factura());
            System.out.println("ID habitacion: " + auxF.getId_habitacion());
            System.out.println("Dias a hospedarse: " + auxF.dias);
            System.out.println("Estado : " + auxF.Estado);
            System.out.println("Persona que paga: " + auxF.PersonaAPagar.nombre() + auxF.PersonaAPagar.Apellido());
            System.out.println("DUI : " + auxF.PersonaAPagar.duiR());
            System.out.println("Paquete : " + auxF.tipo);
            System.out.println("Fecha inicial : " + auxF.fechaIni);
            System.out.println("Huespedes: ");
            printListHusped(auxF.Huespesdes);
            contador++;
        }
    }

    public void printListAdmin(ArrayList<Administrador> master) {
        int contador = 1;
        for (Administrador auxF : master) {
            System.out.println("<-------------- Administrador " + contador + "-------------------->");
            System.out.println("Username: " + auxF.Username);
            System.out.println("password: " + auxF.getPassword());
            System.out.println("Nombre: " + auxF.Nombre);
            System.out.println("Apellido: " + auxF.Apellido);
            contador++;
        }
    }

    public void printListHusped(ArrayList<Huesped> master) {
        int contador = 1;
        for (Huesped auxF : master) {
            System.out.println("<-------------- Huespedes: " + contador + "-------------------->");
            System.out.println("Nombre: " + auxF.Nombre);
            System.out.println("Apellido: " + auxF.Apellido);
            System.out.println("Dui: " + auxF.duiR());
            contador++;
        }
    }

    public void checkOutIds(ArrayList<Reservacion> masterRay) {
        for (Reservacion auxF : masterRay) {
            if (auxF.getId_reservacion() > lastIDReserva) {
                lastIDReserva = auxF.getId_reservacion();
            }
            if (auxF.getId_factura() > lastIDFactura) {
                lastIDFactura = auxF.getId_factura();
            }
        }
        AutoIncrement();
        System.out.println(lastIDFactura + " " + lastIDReserva + "checkout");
    }

    public void AutoIncrement() {
        lastIDReserva += 1;
        lastIDFactura += 1;
    }

    public ArrayList<Habitacion> getListHabitacion() {
        ArrayList<Habitacion> listaAux = new ArrayList<>();
        for (String registro : getFileContent(HABITACIONES)) {
            listaAux.add(MakerX.creadbita(registro.split(" ")));
        }
        return listaAux;
    }

    public ArrayList<Factura> getListFactura() {
        ArrayList<Factura> listaAux = new ArrayList<>();
        for (String registro : getFileContent(FACTURAS)) {
            Factura currenFactura = MakerX.creadoFactura(registro);
            listaAux.add(currenFactura);
        }
        return listaAux;
    }

    public ArrayList<Reservacion> getListReservacion() {
        ArrayList<Reservacion> listaAux = new ArrayList<>();
        for (String registro : getFileContent(RESERVAS)) {
            Reservacion currenReservacion = creadoReservacion(registro);
            listaAux.add(currenReservacion);
        }
        return listaAux;
    }

    public ArrayList<Administrador> getListAdministradores() {
        ArrayList<Administrador> listaAux = new ArrayList<>();
        for (String registro : getFileContent("adminTEST")) {
            Administrador currenAdmin = MakerX.creadoAdmin(registro);
            listaAux.add(currenAdmin);
        }
        return listaAux;
    }

    public ArrayList<Huesped> getListHuespedes() {
        ArrayList<Huesped> listaAux = new ArrayList<>();
        for (String registro : getFileContent("huespedes")) {
            listaAux.add(creadoHuesped(registro.split(" ")[1].split(":")[0]));
        }
        return listaAux;
    }

    //metodo para escribir las listas universal 
    public <E> void Escribir(ArrayList<E> lista, String filename) {
        FileWriter fichero;
        PrintWriter pw;
        archivoTXT = new File(filename + ".txt");
        try {
            fichero = new FileWriter(archivoTXT);
            pw = new PrintWriter(fichero);
            for (E elemento : lista) {
                pw.print(elemento.toString() + "\n");
            }
            pw.close();
            fichero.close();
            archivoTXT = null;
        } catch (IOException e) {
            System.err.println("Imposible escribir a " + archivoTXT.getName() + "correctamente");
        }
    }

    /*escribe las 60 habitaciones bacias en un txt*/
    public void refractorhabitaciones() {
        int iterador;
        char indicador;
        archivoTXT = new File("habitaciones.txt");
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(archivoTXT);
            pw = new PrintWriter(fichero);
            for (indicador = 'a'; indicador < ('f' + 1); indicador++) {
                for (iterador = 1; iterador < 11; iterador++) {
                    String parser = (indicador + "#" + iterador);
                    pw.println(parser);
                }
            }
            pw.close();
            fichero.close();
        } catch (IOException e) {
            System.err.println("Imposible escribir a " + archivoTXT.getName() + "correctamente");
        }
    }
}
