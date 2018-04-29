/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marilion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yury_
 */
public class GestorBase {

    //aqui deben ir los archivos de la base de datos, 4 archivos. 
    //Hay que cambiar y agregar la lista de factura
    private Huesped Db_HuespedesActivos;
    private Huesped Db_ReservaHist;
    private Reservacion reservacion;
    
    public static Connection createDatabaseConnection()
		throws SQLException, ClassNotFoundException {
	String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	Class.forName(driver);
	String url = "jdbc:derby:sampleDB";
	Connection c = DriverManager.getConnection(url);
	return c;
}

    public ArrayList<Habitacion> GetListHabitacion() {
        ArrayList<Habitacion> listaAux = new ArrayList<Habitacion>();

        return listaAux;
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
    
    public ArrayList<Administrador> GetListAdministradores(){
        ArrayList<Administrador> listaAux = new ArrayList<Administrador>();
        
        return listaAux; 
    }

}
