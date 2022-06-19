package Renta.Utemita.ReglasDeNegocio.Busqueda;

import Renta.Utemita.Almacenamiento.AccesoBD.AccesoBD;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarPropiedad.Propiedad;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase que implementa el subsistema de busqueda de cuartos
 * @author Marcos
 * @version 1.0
 */
public class Busqueda {
    /**
     * Método para obtener todos los cuartos en el rango de precios
     * @param precioInicial
     * @param precioFinal
     * @return lista de propiedades
     */
    public ArrayList<Propiedad> obtenerCuartos(int precioInicial,int precioFinal){
    AccesoBD conexion = new AccesoBD();
    conexion.iniciarBD();
    ArrayList temp=conexion.listCuartosPrecios(precioInicial, precioFinal);
    conexion.DesconectarBD();
    return temp;
    }
    /**
     * Método para obtener información acerca de un cuarto
     * @param idPropiedad
     * @return una propiedad
     */
    public Propiedad obtenerUnCuarto(int idPropiedad){
        Propiedad temp = new Propiedad();
        try {
            AccesoBD conexion = new AccesoBD();
            conexion.iniciarBD();
            temp=conexion.getCuarto(idPropiedad);
            conexion.DesconectarBD();
        } catch (IOException e) {
            System.out.println("error en obtener cuarto "+e.getLocalizedMessage());
        }
    return temp;
    }

}
