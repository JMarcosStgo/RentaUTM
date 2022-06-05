package Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario;

/**
 *
 * @author Marcos
 * @version 1.0
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private int telefono;
    private String correo;
    private String matricula;
    private String contraseña;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Usuario (){}

    public Usuario(String nombre, int telefono, String correo, String contraseña,String matricula,int idUsuario) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.matricula= matricula;
        this.idUsuario=idUsuario;
    }
    public Usuario(String nombre, int telefono, String correo, String contraseña,String matricula,String tipo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.matricula= matricula;
        this.tipo=tipo;
    }
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
}
