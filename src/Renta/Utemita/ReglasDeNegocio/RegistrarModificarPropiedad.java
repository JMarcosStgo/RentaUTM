package Renta.Utemita.ReglasDeNegocio;

import Renta.Utemita.Almacenamiento.AccesoBD;
import Renta.Utemita.Presentacion.Propiedad;
import java.util.ArrayList;

/**
 * Clase que implementa el subsistema para registrar y modificar una propiedad
 * @author Marcos
 * @version 1.0
 */
public class RegistrarModificarPropiedad {
    private Propiedad propiedad;
    private boolean codigo;
    /**
     * Método para leer y enviar token de la interfaz a AccesoBD
     * @param token
     * @return valor boleano, verdadero si existe un usuario en la BD con el token
     */
    public boolean capturarBtnExistenciaPropiedad(String token){
        AccesoBD conexion =new AccesoBD();
        conexion.iniciarBD();
        this.codigo=conexion.verificacionCodigoPropiedad(token);
        conexion.DesconectarBD();
        return codigo;
    }
    /**
     * Método para dar de alta una propiedad
     * @param propiedad
     * @return valor verdadero, si no ocurre ningun error
     */
    public boolean ingresarPropiedad(Propiedad propiedad){
        try {
            AccesoBD conexion =new AccesoBD();
            conexion.iniciarBD();
            conexion.altaPropiedad(propiedad);
            conexion.DesconectarBD();
            return true;
        }catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return false;
    }
    /**
     * Método para Verificar la existencia de una propiedad mediante el token
     * @param token
     * @return valor booleano, verdadero si existe la propiedad
     */
    public boolean btnExisteCodigoPropiedad(String token){
        AccesoBD conexion =new AccesoBD();
        conexion.iniciarBD();
        codigo=conexion.verificacionCodigoPropiedad(token);
        System.out.println("codigo "+codigo);
        conexion.DesconectarBD();
        return codigo;
    }
    /**
     * Método para validacion de los datos ingresados por el usuario
     * @param descripcionCuarto
     * @param precio
     * @param disponibilidad
     * @param ubicacion
     * @param imagenes
     * @param servicios
     * @return valor boleano, verdaro en caso de no encontrar errores
     */
    public boolean verificarDatos(String descripcionCuarto,float precio,String disponibilidad,String ubicacion, ArrayList<String> imagenes,String servicios){
        /*caso 1: sea distinto de null, imagenes menor a 3 y precio sea invalido*/
        if(descripcionCuarto.length()>0 && precio>0 && precio<50000 && disponibilidad.length()>0 && ubicacion.length()>0 && imagenes.size()==3 &&servicios.length()>0){
            return true;
        }
        /*caso 2: en cualquier otro caso se toma como datos invalidos*/
        return false;
    }
    /**
     * 
     */
    public void agregarMasPropiedad(){
        //limpia 
    }
    /**
     * Metodo para hacer una modificacion en una propiedad
     * @param propiedad
     */
    public void modificarPropiedad(Propiedad propiedad){
        
        try {
            AccesoBD conexion =new AccesoBD();
            conexion.iniciarBD();
            conexion.actualizarPropiedad(propiedad);
            conexion.DesconectarBD();
            } catch (Exception e) {
            System.out.println("error modificar propiedad"+e.getLocalizedMessage());    
        }
   
    }
    /**
     *  método para obtener una propiedad a partir de el token de la propiedad
     * @param token
     * @return una propiedad
     */
    public Propiedad obtenerDatosPropiedad(String token){
        AccesoBD conexion =new AccesoBD();
        conexion.iniciarBD();
        Propiedad temp=conexion.obtenerPropiedadModificar(token);
        conexion.DesconectarBD();
        return temp;
    }
    /**
     * 
     * @return un valor boleano, si se actualizo correctamente la propiedad 
     */
    public boolean btnIngresarDatosPropiedad(){
        AccesoBD conexion =new AccesoBD();
        conexion.iniciarBD();
        codigo=conexion.actualizarPropiedad(propiedad);
        conexion.DesconectarBD();
        return codigo;
    }
    
}
