package CosasInutiles;
/*
 * @author Amadeo_09
 */
/*Cree esta clase con el proposito de que nos puede servir para registrar la hora de entrada y la hora que el cliente tiene que ntregar la habitacion
  ya que asi funciona ese tipo de registro en los hoteles sino se puede dar el case que se quede tiempo de mas y una reservacion choque con la otra*/
import java.util.Calendar;
import java.util.GregorianCalendar;//Con esto se le dice a java que el sistema de referencia a utilizar es
                                  //el calendario gregoriano que para muchos es el mas exacto y usado.
public class Reloj {
	public static void main(String[] args) {
		// Ambas maneras son validas ahi depende de cual se quiera usar por comodidad
		//Calendar calen = new GregorianCalendar();
		Calendar calen = Calendar.getInstance();
		int hora, minutos, segundos, tardedia;//tarde dia sera el unico valorque dentro de la ejecucion se trabajara como un binario para determinar si es am o pm
		hora =calen.get(Calendar.HOUR);// esta es para tirar la hora en formato de 12 horas que es el mas usado por nosotros
                //hora =calen.get(Calendar.HOUR_OF_DAY); esta es para tirar la hora en formato de 24 horas
		minutos = calen.get(Calendar.MINUTE);
		segundos = calen.get(Calendar.SECOND);
                tardedia = calen.get(Calendar.AM);//genera un valor binario de 1 y 0 donde 1 determina que es pm y 0 que es am.
                tardedia = calen.get(Calendar.AM_PM);//genera un valores mayores a 9 y menores a 9 donde los mayores nos determinan que es pm y menores que es am.
                                                     // a mi parecer este es mas factible si se trabaja en formato de 24 horas.
                //Esto solo nos va tirar la hora actual porque lo que nos interesa son las horas que el cliente marca la entrada y entrega de su habitacion.
                if(tardedia == 1){//Este monton de if es para corregir el formato que la consola muestra las horas minutos y segundos
                                  // porque no existe 70 segundos ni 40 horas y eso asi las muestra como 07 y 04 y asi.
                    if (hora<10&&minutos<10&&segundos<10){System.out.println("0" + hora + ":" + "0" + minutos + ":" + "0" + segundos + " " + "PM");}
                    if (hora<10&&minutos<10&&segundos>10){System.out.println("0" + hora + ":" + "0" + minutos + ":" + segundos + " " + "PM");}
                    if (hora<10&&minutos>10&&segundos<10){System.out.println("0" + hora + ":" + minutos + ":" + "0" + segundos + " " + "PM");}
                    if (hora<10&&minutos>10&&segundos>10){System.out.println("0" + hora + ":" + minutos + ":" + segundos + " " + "PM");}
                    if (hora>10&&minutos<10&&segundos<10){System.out.println(hora + ":" + "0" + minutos + ":" + "0" + segundos + " " + "PM");}
                    if (hora>10&&minutos<10&&segundos>10){System.out.println(hora + ":" + "0" + minutos + ":" + segundos + " " + "PM");}
                    if (hora>10&&minutos>10&&segundos<10){System.out.println(hora + ":" + minutos + ":" + "0" + segundos + " " + "PM");}
                    if (hora>10&&minutos>10&&segundos>10){System.out.println(hora + ":" + minutos + ":" + segundos + " " + "PM");}
                }
                else{
                    if (hora<10&&minutos<10&&segundos<10){System.out.println("0" + hora + ":" + "0" + minutos + ":" + "0" + segundos + " " + "AM");}
                    if (hora<10&&minutos<10&&segundos>10){System.out.println("0" + hora + ":" + "0" + minutos + ":" + segundos + " " + "AM");}
                    if (hora<10&&minutos>10&&segundos<10){System.out.println("0" + hora + ":" + minutos + ":" + "0" + segundos + " " + "AM");}
                    if (hora<10&&minutos>10&&segundos>10){System.out.println("0" + hora + ":" + minutos + ":" + segundos + " " + "AM");}
                    if (hora>10&&minutos<10&&segundos<10){System.out.println(hora + ":" + "0" + minutos + ":" + "0" + segundos + " " + "AM");}
                    if (hora>10&&minutos<10&&segundos>10){System.out.println(hora + ":" + "0" + minutos + ":" + segundos + " " + "AM");}
                    if (hora>10&&minutos>10&&segundos<10){System.out.println(hora + ":" + minutos + ":" + "0" + segundos + " " + "AM");}
                    if (hora>10&&minutos>10&&segundos>10){System.out.println(hora + ":" + minutos + ":" + segundos + " " + "AM");}
                }//Todo este "calibrado" del reloj es para el reloj en formato de 12 horas.
		
	}
}