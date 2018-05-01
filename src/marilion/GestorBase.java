/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yury_
 */
public class GestorBase {

    //aqui deben ir los archivos de la base de datos, 4 archivos. 
    //Hay que cambiar y agregar la lista de factura
    private File archivoTXT;

    private ArrayList<String> getFileContent(String filename) {
        ArrayList<String> filecontent = new ArrayList<>();
        String cadenaAux;
        archivoTXT = new File(filename + ".txt");
        FileReader lectorArchivo;
        try {
            lectorArchivo = new FileReader(archivoTXT);
            BufferedReader lectorLines = new BufferedReader(lectorArchivo);
            while ((cadenaAux = lectorLines.readLine()) != null) {
                System.out.println(cadenaAux);
                filecontent.add(cadenaAux);
            }
            lectorLines.close();
            lectorArchivo.close();
            archivoTXT = null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorBase.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se encontro el archivo " + archivoTXT.getName());
        } catch (IOException ex) {
            Logger.getLogger(GestorBase.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se puede leer el archivo " + archivoTXT.getName());
        }
        return filecontent;
    }

    /**
     * este metodo crea personas con el formato estandar leido desde archivo
     *
     * @param master es la cadena maestra que contiene una linea del archivo de
     * texto en que se guarda la informacion, ese registro contiene toda la
     * informacion necesaria para recontruir una habitacion en memoria RAM
     * @return Persona
     */
    private Persona creadoPersona(String master) {
        String nombre, apellido, dui;
        nombre = master.split("#")[0];
        apellido = master.split("#")[1];
        dui = master.split("#")[2];
        return new Persona(nombre, apellido, dui);
    }

    /**
     * este metodo crea habitacion para probar el gestor de bases por que a
     * gerar se le ocurrio que seria buena idea tener una ArrayList dentro de un
     * arraylist dentro del objeto y se quejan de mi menu :v
     *
     * @param master es la cadena maestra que contiene una linea del archivo de
     * texto en que se guarda la informacion, ese registro contiene toda la
     * informacion necesaria para recontruir una habitacion en memoria RAM
     * @return Habitacion
     */
    private Habitacion creadoHabitacion(String master) {
        char indicadorDePiso;
        int numeroDeHabitacion;
        String[] huespedes;
        EstadoHabitacion estado;
        indicadorDePiso = master.split(" ")[0].charAt(0);
        numeroDeHabitacion = Integer.parseInt(master.split(" ")[1]);
        estado = parseState(master.split(" ")[2]);
        huespedes = master.split(" ")[3].split(":");
        /*en el archivo los nombre de huespedes se separan por :
          este metodo devuelve el arreglo de cadenas con los diferentes
          nombres de los huespedes en la Habitacion
         */
        return new Habitacion(indicadorDePiso, numeroDeHabitacion, estado, crearHuesped(huespedes));

    }

    /**
     * este metodo crea una factura para probar el gestor de bases por que a
     * gerar se le ocurrio que seria buena idea tener una ArrayList dentro de un
     * arraylist dentro del objeto y se quejan de mi menu :v
     *
     * @param master es la cadena maestra que contiene una linea del archivo de
     * texto en que se guarda la informacion, ese registro contiene toda la
     * informacion necesaria para recontruir una factura en memoria RAM
     * @return factura
     */
    private Factura creadoFactura(String master) {
        int Id_fact;
        float monto2;
        Persona client2;
        String fecha;
        Id_fact = Integer.parseInt(master.split(" ")[0]);
        client2 = creadoPersona(master.split(" ")[1]);
        monto2 = Float.parseFloat(master.split(" ")[2]);
        fecha = master.split(" ")[3];
        /*en el archivo los atributos de personas clientes se separan por #
          este metodo devuelve el arreglo de cadenas con los diferentes
          atributos del cliente en la reservacion
         */
        return new Factura(Id_fact, client2, monto2, fecha);

    }

    private Reservacion creadoReservacion(String master) {
        int reserva, factura, huesped;
        String Id_Habitacion;
        reserva = Integer.parseInt(master.split(" ")[0]);
        factura = Integer.parseInt(master.split(" ")[1]);
        huesped = Integer.parseInt(master.split(" ")[2]);
        Id_Habitacion = master.split(" ")[3];
        return new Reservacion(reserva, factura, huesped, Id_Habitacion);
    }
    
    private Administrador creadoAdmin(String master){
        String username, password, nombre, apellido,dui;
        username = master.split(" ")[0];
        password = master.split(" ")[1];
        nombre = master.split(" ")[2];
        apellido = master.split(" ")[3];
        dui = master.split(" ")[4];
        return new Administrador(username, password, nombre, apellido, dui);
    }

    /**
     * este metodo convierte datos tipo String a EstadoHabitacion
     *
     * @param value cadena con el valor de 1, 2 o 3
     * @return EstadoHabitacion abilitada, deshabilitada o en uso
     */
    private EstadoHabitacion parseState(String value) {
        EstadoHabitacion aux;
        switch (Integer.parseInt(value)) {
            case 1:
                aux = EstadoHabitacion.Habilitada;
                break;
            case 2:
                aux = EstadoHabitacion.Deshabilitada;
                break;
            case 3:
                aux = EstadoHabitacion.EnUso;
                break;
            default:
                System.err.println("Archivo potencialemte corrupto, estado desconocido");
                aux = EstadoHabitacion.Deshabilitada;
                break;
        }
        return aux;
    }

    public void printListHabitacion() {
        int contador = 1;
        for (Habitacion auxH : getListHabitacion()) {
            System.out.println("<-------------- Habitacion " + contador + "-------------------->");
            System.out.println("indicador de piso: " + auxH.indicadorDePiso);
            System.out.println("estado de habitacion: " + auxH.habitacionEstado);
            System.out.println("numero de habitacion: " + auxH.numeroHabitacion);
            System.out.println("lista de huespedes");
            for (Huesped auxhues : auxH.listaHuesped) {
                System.out.println("\t" + auxhues.Nombre + " " + auxhues.Apellido);
            }
            contador++;
        }
    }

    public void printListFacturas() {
        int contador = 1;
        for (Factura auxF : getListFactura()) {
            System.out.println("<-------------- Factura " + contador + "-------------------->");
            System.out.println("ID factura: " + auxF.Id_factura);
            System.out.println("Cliente: " + auxF.cliente.Nombre + " " + auxF.cliente.Apellido);
            System.out.println("Monto a pagar: " + auxF.monto);
            System.out.println("Fecha: " + auxF.fecha);
            contador++;
        }
    }

    public void printListReservas() {
        int contador = 1;
        for (Reservacion auxF : getListReservacion()) {
            System.out.println("<-------------- Reservacion " + contador + "-------------------->");
            System.out.println("ID reservacion: " + auxF.Id_reservacion);
            System.out.println("ID Huespedes: " + auxF.Id_huespedes);
            System.out.println("ID habitacion: " + auxF.Id_habitacion);
            contador++;
        }
    }
    public void printListAdmin() {
        int contador = 1;
        for (Administrador auxF : getListAdministradores()) {
            System.out.println("<-------------- Administrador " + contador + "-------------------->");
            System.out.println("Username: " + auxF.Username);
            System.out.println("password: " + auxF.getPassword());
            System.out.println("Nombre: " + auxF.Nombre);
            System.out.println("Apellido: " + auxF.Apellido);
            contador++;
        }
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
    private ArrayList<Huesped> crearHuesped(String[] huespedes) {
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

    public ArrayList<Habitacion> getListHabitacion() {
        ArrayList<Habitacion> listaAux = new ArrayList<>();
        System.out.println("Las habitaciones leidas desde archivo son: ");
        for (String registro : getFileContent("habitacionTEST")) {
            Habitacion currenthabitacion = creadoHabitacion(registro);
            listaAux.add(currenthabitacion);
        }
        return listaAux;
    }

    public ArrayList<Factura> getListFactura() {
        ArrayList<Factura> listaAux = new ArrayList<>();
        System.out.println("Las facturas leidas desde archivo son: ");
        for (String registro : getFileContent("facturaTEST")) {
            Factura currenFactura = creadoFactura(registro);
            listaAux.add(currenFactura);
        }
        return listaAux;
    }

    public ArrayList<Reservacion> getListReservacion() {
        ArrayList<Reservacion> listaAux = new ArrayList<>();
        System.out.println("Las reservaciones obtenidas de archivo son : ");
        for (String registro : getFileContent("reservacionTEST")) {
            Reservacion currenReservacion = creadoReservacion(registro);
            listaAux.add(currenReservacion);
        }
        return listaAux;
    }

    public ArrayList<Administrador> getListAdministradores() {
        ArrayList<Administrador> listaAux = new ArrayList<>();
        System.out.println("Las administradores obtenidos de archivo son : ");
        for (String registro : getFileContent("adminTEST")) {
            Administrador currenAdmin = creadoAdmin(registro);
            listaAux.add(currenAdmin);
        }
        return listaAux;
    }

    public ArrayList<Huesped> getListHuespedesActivos() {
        ArrayList<Huesped> listaAux = new ArrayList<>();

        return listaAux;
    }

    //metodo para escribir las listas universal 
    public <E> void Escribir(String fichero, ArrayList<E> lista) {
        switch (fichero) {
            case "reservacion.txt":
                this.EscribirReservacion((ArrayList<Reservacion>) lista, fichero);
                break;
            case "admins.txt":
                this.EscribirAdmins((ArrayList<Administrador>) lista, fichero);
                break;
            case "habitaciones.txt":
                this.EscribirHabitaciones((ArrayList<Habitacion>) lista, fichero);
                break;
            case "huespedes.txt":
                this.EscribirHuespedesActuales((ArrayList<Huesped>) lista, fichero);
                break;
            case "facturas.txt":
                this.EscribirFacturas((ArrayList<Factura>) lista, fichero);
                break;
        }
    }

    //aca estan todos los metodos de escritura solo faltan los de lectura
    private void EscribirReservacion(ArrayList<Reservacion> lista, String ficheroe) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ficheroe);
            pw = new PrintWriter(fichero);

            //aca va el verdadero codigo de escritura de DB
            for (Reservacion re : lista) {

                pw.print(" ");

                pw.print(" ");
                pw.print(re.Estado + " ");
                pw.print(re.PersonaAPagar.ToString() + " ");
                pw.print(re.fechaIni + " ");
                pw.print(re.tipo);
                pw.print("\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void EscribirAdmins(ArrayList<Administrador> arrayList, String ficheroe) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ficheroe);
            pw = new PrintWriter(fichero);
            for (Administrador admin : arrayList) {
                pw.print(admin.ToString() + " ");
                pw.print(admin.Username + " ");
                pw.print(admin.getPassword() + " ");
                pw.print("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //Metodo para escribir el archivo a utilizar de base para las habitaciones donde se tendra una habitacion por linea
    private void EscribirHabitaciones(ArrayList<Habitacion> arrayList, String ficheroe) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ficheroe);
            pw = new PrintWriter(fichero);
            for (Habitacion h : arrayList) {
                pw.println(h.toString() + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void EscribirHuespedesActuales(ArrayList<Huesped> arrayList, String ficheroe) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ficheroe);
            pw = new PrintWriter(fichero);

            for (Huesped h : arrayList) {
                pw.println(h.ToString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void EscribirFacturas(ArrayList<Factura> arrayList, String ficheroe) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ficheroe);
            pw = new PrintWriter(fichero);

            for (Factura f : arrayList) {
                pw.println(f.toString() + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
