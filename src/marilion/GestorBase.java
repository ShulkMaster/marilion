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
    public File habitacion;
    private Huesped Db_HuespedesActivos;
    private Huesped Db_ReservaHist;
    private Reservacion reservacion;

    public ArrayList<Habitacion> GetListHabitacion() {
        ArrayList<Habitacion> listaAux = new ArrayList<>();
        CrearHuespedTest(3);
        //si van a mandar una lista separen los objetos con : o algotro caracter que no sea el "." sino da error nullperro >:V
        crearTestfile("habitacion", "a 15 true doble 1 yury:mario:cecil:gerardo", 5);
        System.out.println("Las habitaciones desde archivo son: ");
        for (int i = 0; i < 4; i++) {
            listaAux.add(creadoHabitacion());
        }
        System.out.println("Habitaciones creadas de objeto");
        int i = 0;
        for (Habitacion listaAux1 : listaAux) {
            System.out.println("Indicador de piso: " + listaAux1.indicadorDePiso);
            System.out.println("Numero de Habitacion: " + listaAux1.numeroHabitacion);
            System.out.println("piso: " + listaAux1.piso);
            System.out.println("Huesped name: " + listaAux1.listaHuesped.get(i).Nombre);
            System.out.println("Huesped apellido: " + listaAux1.listaHuesped.get(i).Apellido);
            i++;
            System.out.println("");
        }
        return listaAux;
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
        habitacion = new File(filename + ".txt");
        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter(habitacion);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < times; i++) {
                pw.println(baseRecord + "\n");
            }

        } catch (IOException e) {
            System.out.println("El archivo " + habitacion.getName() + "No existe ni pudo ser creado");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
                System.out.println("FileWritter not nut but not able to close");
            }
        }

    }

    /**
     * este metodo crea habitacion de prueba para probar el gestor de bases por
     * que a gerar se le ocurrio que seria buena idea tener una ArrayList dentro
     * de un arraylist dentro del objeto y se quejan de mi menu :v
     *
     * @return Habitacion
     */
    private Habitacion creadoHabitacion() {
        FileReader fichero;
        String master, indicadorDePiso, numeroDeHabitacion, piso, huesoed;
        Habitacion haux;
        try {
            fichero = new FileReader(habitacion);
            BufferedReader br = new BufferedReader(fichero);
            master = br.readLine();
            indicadorDePiso = master.split(" ")[0];
            numeroDeHabitacion = master.split(" ")[1];
            piso = master.split(" ")[4];
            huesoed = master.split(" ")[5];
            haux = new Habitacion(indicadorDePiso.charAt(0),
                    Integer.parseInt(numeroDeHabitacion),
                    EstadoHabitacion.Habilitada,
                    TipoDeHabitacion.Doble,
                    Integer.parseInt(piso),
                    /*en el archivo los nombre de huespedes se separan por :
                    este metodo devuelve el arreglo de cadenas con los diferentes
                    nombres de los huespedes en la Habitacion
                     */
                    CrearHuespedTest(4, huesoed.split(":")));
            fichero.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo Retorno habitacion por defecto");
            return new Habitacion('a', 20, EstadoHabitacion.Habilitada, TipoDeHabitacion.Doble, 1, CrearHuespedTest(3));
        } catch (IOException ex) {
            Logger.getLogger(GestorBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se pudo leer el buffer, retorno x defectt");
            return new Habitacion('b', 25, EstadoHabitacion.Habilitada, TipoDeHabitacion.Sencilla, 1, CrearHuespedTest(2));
        }

        //retorno quemado mientras el metodo esta en desarrollo, trabajando para usted @fovialito
        return haux;

    }

    /**
     * metodo de prueba para crear huespedes por que si
     *
     * @param cantidad indica el numero de huespedes a crear por habitacion.
     * prueba
     */
    private ArrayList<Huesped> CrearHuespedTest(int cantidad) {
        ArrayList<Huesped> listaAux = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            listaAux.add(new Huesped(("gerardo" + i), ("mariposon" + i), ("00045671" + i)));
        }
        return listaAux;
    }

    /**
     * metodo de prueba para crear huespedes por que si y circulex solo es una
     * varianle para ir seleccionando un nombre diferente del array en cada
     * iteracion
     *
     * @param cantidad indica el numero de huespedes a crear por habitacion.
     * @param majes es un arreglo de cadenas con los nombres de los huespedes
     *
     * prueba
     */
    private ArrayList<Huesped> CrearHuespedTest(int cantidad, String[] majes) {
        ArrayList<Huesped> listaAux = new ArrayList<>();
        int circulex = 0;
        for (int i = 0; i < cantidad; i++) {
            listaAux.add(new Huesped((majes[circulex] + i), ("mariposon" + i), ("00045671" + i * circulex)));
            circulex++;
            circulex = circulex % (majes.length);
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
                    pw.print(hab.indicadorDePiso + hab.numeroHabitacion + "$");
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
                pw.println(h.toString()+"\n");
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
                pw.println("##");
                pw.print(f.toString());
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
