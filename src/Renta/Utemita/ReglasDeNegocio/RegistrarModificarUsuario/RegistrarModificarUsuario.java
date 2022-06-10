package Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario;

import Renta.Utemita.Almacenamiento.AccesoBD.AccesoBD;

/**
 * Clase que implementa el subsistema 
 * @author Marcos
 * @version 1.0
 */
public class RegistrarModificarUsuario {
    /**
     * Método para validar los campos al registrar o modificar una propiedad
     * @param nombre
     * @param telefono
     * @param correo
     * @param matricula
     * @param contraseña
     * @return un valor boleano, verdadero si los datos son correctos
     */
    public boolean verificarDatos(String nombre,long telefono,String correo,String matricula,String contraseña){
        try {
            if(nombre.length()>0 && telefono>0 && telefono <10000000000L && correo.length()>0 && contraseña.length()>0){
            /*es alumno*/
            String tem[]=correo.split("@");
            if(matricula.length()>0 && tem[1].equals("gs.utm.mx"))
                return true;
            
            /*es casero*/
            if(matricula.length()==0 && tem[1].equals("gs.utm.mx"))
                return false;
            if(matricula.length()==0 && !tem[1].equals("gs.utm.mx"))
                return true;
            
        }
        } catch (Exception e) {
            System.out.println("error verificar datos propiedad"+e.getLocalizedMessage());
        }
       return false;
    }
    /**
     * Método para obtener los datos de un usuario a partir de su id
     * @param idUsuario
     * @return un usuario
     */
    public Usuario cargarDatos(int idUsuario){
        AccesoBD conexion = new AccesoBD();
        conexion.iniciarBD();
        Usuario temp = new Usuario();
        temp=conexion.obtencionDatos(idUsuario);
        conexion.DesconectarBD();
        return temp;
    }
    /**
     * Método para solicitar el registro de un usuario en la BD
     * @param usuario
     * @return un valor boleano, verdadero si el registro fue exitoso
     */
    public boolean solicitarRegistro(Usuario usuario){
        try {
            AccesoBD conexion = new AccesoBD();
            conexion.iniciarBD();
            conexion.registroDeDatos(usuario);
            conexion.DesconectarBD();
            return true;
            } catch (Exception e) {
                System.out.println("error solicitar registro "+e.getLocalizedMessage());
        }
        return false;
    }
    /**
     * Método para solicitar la modificación de los datos de un usaurio
     * @param nombre
     * @param telefono
     * @param correo
     * @param matricula
     * @param contraseña
     * @param idUsuario 
     */
    public void solicitarModificacion(String nombre,long telefono,String correo,String matricula,String contraseña,int idUsuario){
        AccesoBD conexion = new AccesoBD();
        Usuario usuario= new Usuario (nombre,telefono,correo,contraseña,matricula,idUsuario);
        conexion.iniciarBD();
        conexion.modificarDatos(usuario);
        conexion.DesconectarBD();
    }
}
