package Renta.Utemita.ReglasDeNegocio.ApartarCuarto;

/**
 *  
 * @author Marcos
 * @version 1.0
 */
public class Notificaciones {
    private int idNotificacion;
    private int idAlumno;
    private int idPropiedad;
    private int idCasero;
    private String nombreAlumno;

    public Notificaciones(int idNotificaion, int idAlumno, int idPropiedad, int idCasero, String nombreAlumno) {
        this.idNotificacion = idNotificaion;
        this.idAlumno = idAlumno;
        this.idPropiedad = idPropiedad;
        this.idCasero = idCasero;
        this.nombreAlumno = nombreAlumno;
    }

    public int getIdNotificaion() {
        return idNotificacion;
    }

    public void setIdNotificaion(int idNotificaion) {
        this.idNotificacion = idNotificaion;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public int getIdCasero() {
        return idCasero;
    }

    public void setIdCasero(int idCasero) {
        this.idCasero = idCasero;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }
    
}
