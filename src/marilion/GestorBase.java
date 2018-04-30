/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Huesped Db_HuespedesActivos;
    private Huesped Db_ReservaHist;
    private Reservacion reservacion;

    public ArrayList<Habitacion> GetListHabitacion() {
        ArrayList<Habitacion> listaAux = new ArrayList<>();
        System.out.println("Las habitaciones leidas desde archivo son: ");
        for (String registro : getFileContent("habitacion")) {
            Habitacion currenthabitacion = creadoHabitacion(registro);
            listaAux.add(currenthabitacion);
        }
        return listaAux;
    }
    
    public void printListHabitacion(){
        int contador = 1;
        for(Habitacion auxH : GetListHabitacion()){
            System.out.println("<-------------- Habitacion "+contador+"-------------------->");
            System.out.println("indicador de piso: "+auxH.indicadorDePiso);
            System.out.println("estado de habitacion: "+auxH.habitacionEstado);
            System.out.println("numero de habitacion: "+auxH.numeroHabitacion);
            System.out.println("lista de huespedes"+auxH.listaHuesped);
            contador++;
        }
    }

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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filecontent;
    }

    /**
     * este metodo crea un archivo de prueva para sus metodos get
     *
     * @param baseRecord es una plantilla para crear records falsos a modo de
     * prueba en sus metodos
     * @param times indica el numero de veces que se creara un registro
     *
     */
    private void crearTestfile(String filename, String baseRecord, int times) {
        archivoTXT = new File(filename + ".txt");
        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter(archivoTXT);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < times; i++) {
                pw.println(baseRecord + "\n");
            }

        } catch (IOException e) {
            System.err.println("El archivo " + archivoTXT.getName() + "No existe ni pudo ser creado");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
                System.err.println("FileWritter not nut but not able to close");
            }
        }

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
        String indicadorDePiso, numeroDeHabitacion, huespedes;
        EstadoHabitacion estado;
        Habitacion haux;
        indicadorDePiso = master.split(" ")[0];
        numeroDeHabitacion = master.split(" ")[1];
        estado = parseState(master.split(" ")[2]);
        huespedes = master.split(" ")[3];
        System.out.println(huespedes);
        /*en el archivo los nombre de huespedes se separan por :
          este metodo devuelve el arreglo de cadenas con los diferentes
          nombres de los huespedes en la Habitacion
         */
        System.out.println(Arrays.toString(huespedes.split(":")));
        haux = new Habitacion(indicadorDePiso.charAt(0), Integer.parseInt(numeroDeHabitacion), estado, CrearHuesped(huespedes.split(":")));
        //retorno quemado mientras el metodo esta en desarrollo, trabajando para usted @fovialito
        return haux;

    }

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

    /**
     * metodo de prueba para crear huespedes
     *
     * @param huespedes sera un arreglo de cadenas y cada cadena lleva la
     * informacion nesesaria para poder crear un huesped, es decir Nombre,
     * Apellido, Dui
     * @return Un grupo de huespedes basadoes en el arreglo que se pasa como
     * parametro
     */
    private ArrayList<Huesped> CrearHuesped(String[] huespedes) {
        ArrayList<Huesped> listaAux = new ArrayList<>();
        String name, lastname, dui;
        for (String huespedIndex : huespedes) {
            name = huespedIndex.split("#")[0];
            lastname = huespedIndex.split("#")[1];
            dui = huespedIndex.split("#")[2];
            listaAux.add(new Huesped(name, lastname, dui));
        }
        return listaAux;
    }

    public ArrayList<Factura> GetListFactura() {
        ArrayList<Factura> listaAux = new ArrayList<>();

        return listaAux;
    }

    public ArrayList<Reservacion> GetListReservacion() {
        ArrayList<Reservacion> listaAux = new ArrayList<>();

        return listaAux;
    }

    public ArrayList<Huesped> GetListHuespedesActivos() {
        ArrayList<Huesped> listaAux = new ArrayList<>();

        return listaAux;
    }

    public ArrayList<Administrador> GetListAdministradores() {
        ArrayList<Administrador> listaAux = new ArrayList<>();

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

                for (Habitacion hab : re.ListaHabitacionR) {
                    pw.print(hab.indicadorDePiso + "" + hab.numeroHabitacion + "$");
                }
                pw.print(" ");

                for (Persona p : re.ListaPersonas) {
                    pw.print(p.ToString() + ":");
                }
                pw.print(" ");
                pw.print(re.NumeroDeHabitacion + " ");
                pw.print(re.Estado + " ");
                pw.print(re.Dias + " ");
                pw.print(re.Id_Reservacion + " ");
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
