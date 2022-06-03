package Renta.Utemita.Presentacion;

import java.awt.Image;
import java.sql.Blob;
import java.util.ArrayList;

/**
 * Clase para instanciar un objeto de tipo propiedad
 * @author Marcos
 */
public class Propiedad {
    String descripcionCuarto;
    float precio;
    String disponibilidad;
    String ubicacion;
    String servicios;
    ArrayList <String> imagenes = new ArrayList();
    ArrayList <Blob> imagenesBlob = new ArrayList();
    ArrayList <Image> imagenesP = new ArrayList();
    
    
    int idPropiedad;
    String token;
    public Propiedad(String descripcionCuarto, float precio, String disponibilidad, String ubicacion, String servicios,ArrayList <String> imagenes,String token,ArrayList <Blob> imagenesBlob,int idPropiedad) {
        this.descripcionCuarto = descripcionCuarto;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.ubicacion = ubicacion;
        this.servicios = servicios;
        this.imagenes = imagenes;
        this.imagenesBlob=imagenesBlob;
        this.token=token;
        this.idPropiedad=idPropiedad;
    }
    

    public Propiedad(){}
    
    public ArrayList<Blob> getImagenesBlob() {
        return imagenesBlob;
    }

    public void setImagenesBlob(ArrayList<Blob> imagenesBlob) {
        this.imagenesBlob = imagenesBlob;
    }

    public String getDescripcionCuarto() {
        return descripcionCuarto;
    }

    public void setDescripcionCuarto(String descripcionCuarto) {
        this.descripcionCuarto = descripcionCuarto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Image> getImagenesP() {
        return imagenesP;
    }

    public void setImagenesP(ArrayList<Image> imagenesP) {
        this.imagenesP = imagenesP;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
