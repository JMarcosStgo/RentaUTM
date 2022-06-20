package Renta.Utemita.ReglasDeNegocio.ApartarCuarto;

import Renta.Utemita.Almacenamiento.AccesoBD.AccesoBD;
import java.util.ArrayList;

/**
 * Clase que implementa al subsistema de apartado de cuartos
 * @author kAarl
 * @version 1.0
 */
public class ApartarCuarto {
    /**
     * Método para poder apartar un cuarto
     * @param token 
     */
    public void apartarCuarto(String token){
        AccesoBD conexion = new AccesoBD();
        conexion.iniciarBD();
        conexion.modificarDisponibilidad(token);
        conexion.DesconectarBD();
    }
    public ArrayList obtenerNotificaciones(int idCasero){
        AccesoBD conexion = new AccesoBD();
        conexion.iniciarBD();
        ArrayList<Notificaciones> noti=conexion.obtenerNotificaciones(idCasero);
        conexion.DesconectarBD();
        return noti;
    }
}
