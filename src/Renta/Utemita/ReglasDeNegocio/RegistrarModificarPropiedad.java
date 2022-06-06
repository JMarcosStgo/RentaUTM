package Renta.Utemita.ReglasDeNegocio;

import Renta.Utemita.Almacenamiento.AccesoBD;
import Renta.Utemita.Presentacion.Propiedad;
import java.sql.Blob;
import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class RegistrarModificarPropiedad {
    private Propiedad propiedad;
   // private boolean datos;
    //private boolean agregarPropiedad;
    private boolean codigo;
    /**
     * Método para leer y enviar token de la interfaz a AccesoBD
     * @param token
     * @return codigo
     */
    public boolean capturarBtnExistenciaPropiedad(String token){
        AccesoBD conexion =new AccesoBD();
        conexion.iniciarBD();
        this.codigo=conexion.verificacionCodigoPropiedad(token);
        conexion.DesconectarBD();
        return codigo;
    }
    /**
     * 
     * @param propiedad
     * @return 
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
     * 
     * @param token
     * @return 
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
     * @param token
     * @return boolean valor verdaro en caso de no encontrar errores
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
     * 
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
     * 
     * @param token
     * @return 
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
     * @return 
     */
    public boolean btnIngresarDatosPropiedad(){
        AccesoBD conexion =new AccesoBD();
        conexion.iniciarBD();
        codigo=conexion.actualizarPropiedad(propiedad);
        conexion.DesconectarBD();
        return codigo;
    }
    
}
