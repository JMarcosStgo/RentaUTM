package Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario;

import Renta.Utemita.Almacenamiento.AccesoBD;

/**
 * 
 * @author Marcos
 * @version 1.0
 */
public class RegistrarModificarUsuario {
    
    public boolean verificarDatos(String nombre,int telefono,String correo,String matricula,String contraseña){
       return nombre!=null && telefono>0 && telefono <100000000 && correo!=null && matricula!=null && contraseña!=null;
    }
    public Usuario cargarDatos(int idUsuario){
        AccesoBD conexion = new AccesoBD();
        conexion.iniciarBD();
        Usuario temp = new Usuario();
        conexion.DesconectarBD();
        return temp=conexion.obtencionDatos(idUsuario);
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
    public void solicitarModificacion(String nombre,int telefono,String correo,String matricula,String contraseña){
        AccesoBD conexion = new AccesoBD();
        Usuario usuario= new Usuario (nombre,telefono,correo,contraseña,matricula);
        conexion.iniciarBD();
        conexion.modificarDatos(usuario);
        conexion.DesconectarBD();
    }
}
