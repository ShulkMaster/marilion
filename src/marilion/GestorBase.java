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
    public File habitaciones;
    public ArrayList<Huesped> huespedTest;
    private Huesped Db_HuespedesActivos;
    private Huesped Db_ReservaHist;
    private Reservacion reservacion;

    public ArrayList<Habitacion> GetListHabitacion() {
        ArrayList<Habitacion> listaAux = new ArrayList<Habitacion>();
        CrearHuespedTest(3);
        crearTestfile("habitacion","a 15 true doble 1 yury.mario.cecil.gerardo", 5);
        for (int i = 0; i < 5; i++) {
            listaAux.add(creadoHabitacion());
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
    private void crearTestfile(String filename,String baseRecord, int times) {
        habitaciones = new File(filename+".txt");
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(habitaciones);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < times; i++) {
                pw.println(baseRecord + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    /**
     * este metodo crea habitaciones de prueba para probar el gestor de bases por
     * que a gerar se le ocurrio que seria buena idea tener una ArrayList dentro
     * de un arraylist dentro del objeto
     * y se quejan de mi menu :v
     * @return Habitacion
     */
    private Habitacion creadoHabitacion() {
        FileReader fichero;
        String indicadorDePiso,numeroDeHabitacion,piso;
        Habitacion haux;
        try {
            fichero = new FileReader(habitaciones);
            BufferedReader br = new BufferedReader(fichero);
            String
            fichero.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorBase.class.getName()).log(Level.SEVERE, null, ex);
            return new Habitacion('a', 20, EstadoHabitacion.Habilitada, TipoDeHabitacion.Doble, 1, huespedTest);
        } catch (IOException ex) {
            Logger.getLogger(GestorBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            return haux;
        
    }
    /**
     * metodo de prueba para crear huespedes por que si 
     * @param cantidad  indica el numero de huespedes a crear por habitacion. prueba
     */
    private void CrearHuespedTest(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            huespedTest.add(new Huesped(("gerardo" + i), ("mariposon" + i), ("00045671" + i)));
        }
    }

    public ArrayList<Factura> GetListFactura() {
        ArrayList<Factura> listaAux = new ArrayList<Factura>();

        return listaAux;
    }

    public ArrayList<Reservacion> GetListReservacion() {
        ArrayList<Reservacion> listaAux = new ArrayList<Reservacion>();

        return listaAux;
    }

    public ArrayList<Huesped> GetListHuespedesActivos() {
        ArrayList<Huesped> listaAux = new ArrayList<Huesped>();

        return listaAux;
    }

    public ArrayList<Administrador> GetListAdministradores() {
        ArrayList<Administrador> listaAux = new ArrayList<Administrador>();

        return listaAux;
    }

}
