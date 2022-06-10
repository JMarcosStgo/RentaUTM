package Renta.Utemita.ReglasDeNegocio;

import java.awt.Image;
import java.sql.Blob;
import java.util.ArrayList;

/**
 * Clase para asignar y recuperar datos de una propiedad
 * @author Marcos
 * @version 1.0
 */
public class Propiedad {
    private String descripcionCuarto;
    private int precio;
    private String disponibilidad;
    private String ubicacion;
    private String servicios;
    private ArrayList <String> imagenes = new ArrayList();
    private ArrayList <Blob> imagenesBlob = new ArrayList();
    private ArrayList <Image> imagenesP = new ArrayList();
    private int idPropiedad;
    private String token;
    public Propiedad(String descripcionCuarto, int precio, String disponibilidad, String ubicacion, String servicios,ArrayList <String> imagenes,String token,ArrayList <Blob> imagenesBlob,int idPropiedad) {
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
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
