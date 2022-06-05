package Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario;

import Renta.Utemita.Almacenamiento.AccesoBD;

/**
 * 
 * @author Marcos
 * @version 1.0
 */
public class RegistrarModificarUsuario {
    
    public boolean verificarDatos(String nombre,int telefono,String correo,String matricula,String contraseña){
       if(nombre.length()>0 && telefono>0 && telefono <100000000 && correo.length()>0 && contraseña.length()>0){return true;}
       return false;
    }
    public Usuario cargarDatos(int idUsuario){
        AccesoBD conexion = new AccesoBD();
        conexion.iniciarBD();
        Usuario temp = new Usuario();
        temp=conexion.obtencionDatos(idUsuario);
        conexion.DesconectarBD();
        return temp;
    }
    public boolean solicitarRegistro(Usuario usuario){
        try {
            AccesoBD conexion = new AccesoBD();
            conexion.iniciarBD();
            conexion.RegistroDeDatos(usuario);
            conexion.DesconectarBD();
            return true;
            } catch (Exception e) {
                System.out.println("error solicitar registro "+e.getLocalizedMessage());
        }
        return false;
    }
    public void solicitarModificacion(String nombre,int telefono,String correo,String matricula,String contraseña,int idUsuario){
        AccesoBD conexion = new AccesoBD();
        Usuario usuario= new Usuario (nombre,telefono,correo,contraseña,matricula,idUsuario);
        conexion.iniciarBD();
        conexion.modificarDatos(usuario);
        conexion.DesconectarBD();
    }
}
