/*
        System.out.println("\033[30mEste texto es Negro");
        System.out.println("\033[31mEste texto es Rojo");
        System.out.println("\033[32mEste texto es Verde");
        System.out.println("\033[33mEste texto es Amarillo");
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

/**
 *
 * @author yury_
 */
public class GestorBase {

    //se cambiaro los 4 archivos por uno generico que lo carga 1 a la vez
    public static int lastIDReserva = 0;
    public static int lastIDFactura = 0;
    public static int lastIDHuesped = 0;
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
                    System.out.println(cadenaAux);
                    filecontent.add(cadenaAux);
                }
            }
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
    public Persona creadoPersona(String master) {
        String nombre, apellido, dui;
        nombre = master.split("#")[0];
        apellido = master.split("#")[1];
        dui = master.split("#")[2];
        return new Persona(nombre, apellido, dui);
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
        estado = StatadosX.parseStateHabitacion(master.split(" ")[2]);
        huespedes = master.split(" ")[3].split(":");
        /*en el archivo los nombre de huespedes se separan por :
          este metodo devuelve el arreglo de cadenas con los diferentes
          nombres de los huespedes en la Habitacion
         */
        return new Habitacion(indicadorDePiso, numeroDeHabitacion, estado, creadoHuesped(huespedes));

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
        Reservacion auxReser = new Reservacion(reserva, factura, huesped, Id_Habitacion);
        auxReser.setDias(Integer.parseInt(master.split(" ")[4]));
        auxReser.setEstado(StatadosX.parseStateReserva(master.split(" ")[5]));
        auxReser.setPersonaAPagar(creadoHuesped(master.split(" ")[6]));
        auxReser.setTipo(StatadosX.parseStatPack(master.split(" ")[7]));
        auxReser.setFechaIni(master.split(" ")[8]);
        return auxReser;
    }

    private Administrador creadoAdmin(String master) {
        String username, password, nombre, apellido, dui;
        username = master.split(" ")[0];
        password = master.split(" ")[1];
        nombre = master.split(" ")[2];
        apellido = master.split(" ")[3];
        dui = master.split(" ")[4];
        return new Administrador(username, password, nombre, apellido, dui);
    }

    public void printListHabitacion(ArrayList<Habitacion> param) {
        int contador = 1;
        for (Habitacion auxH : param) {
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

    public void printListReservas(ArrayList<Reservacion> master) {
        int contador = 1;
        for (Reservacion auxF : master) {
            System.out.println("<-------------- Reservacion " + contador + "-------------------->");
            System.out.println("ID reservacion: " + auxF.Id_reservacion);
            System.out.println("ID factura: " + auxF.Id_factura);
            System.out.println("ID huespedes: " + auxF.Id_huespedes);
            System.out.println("ID habitacion: " + auxF.Id_habitacion);
            System.out.println("Dias a hospedarse: " + auxF.dias);
            System.out.println("Estado : " + auxF.Estado);
            System.out.println("Persona que paga: " + auxF.PersonaAPagar.nombre() + auxF.PersonaAPagar.Apellido());
            System.out.println("DUI : " + auxF.PersonaAPagar.duiR());
            System.out.println("Paquete : " + auxF.tipo);
            System.out.println("Fecha inicial : " + auxF.fechaIni);
            contador++;
        }
        EscribirReservacion(getListReservacion(), "reservacion.txt");
    }

    public void printListAdmin( ArrayList<Administrador> master ) {
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

    public void printListHusped() {
        int contador = 1;
        for (Huesped auxF : getListHuespedes()) {
            System.out.println("<-------------- Huespedes: " + contador + "-------------------->");
            System.out.println("Nombre: " + auxF.Nombre);
            System.out.println("Apellido: " + auxF.Apellido);
            System.out.println("Dui: " + auxF.duiR());
            contador++;
        }
    }

    public void checkOutIds(ArrayList<Reservacion> masterRay) {
        for (Reservacion auxF : masterRay) {
            if (auxF.Id_reservacion > lastIDReserva) {
                lastIDReserva = auxF.Id_reservacion;
            }
            if (auxF.Id_factura > lastIDFactura) {
                lastIDFactura = auxF.Id_factura;
            }
            if (auxF.Id_huespedes > lastIDHuesped) {
                lastIDHuesped = auxF.Id_huespedes;
            }
        }
        AutoIncrement();
        System.out.println(lastIDReserva + " " + lastIDFactura + " " + lastIDHuesped + " CheckOut");
    }

    public void AutoIncrement() {
        lastIDReserva += 1;
        lastIDFactura += 1;
        lastIDHuesped += 1;

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

    public ArrayList<Huesped> getListHuespedes() {
        ArrayList<Huesped> listaAux;
        System.out.println("Las Huespedes obtenidos de archivo son: ");
        String registro = getFileContent("huespedesTEST").get(0);
        listaAux = creadoHuesped(registro.split(" ")[1].split(":"));
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

    //aca estan todos los metodos de escritura
    private void EscribirReservacion(ArrayList<Reservacion> lista, String ficheroe) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ficheroe);
            pw = new PrintWriter(fichero);

            //aca va el verdadero codigo de escritura de DB
            for (Reservacion re : lista) {
                pw.println(re.toString());
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
                pw.print(admin.toString() + " ");
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
                pw.println(h.toString());
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
