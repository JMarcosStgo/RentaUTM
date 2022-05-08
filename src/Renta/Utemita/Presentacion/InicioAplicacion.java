/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Presentacion;

/**
 *
 * @author kAarl
 */
public class InicioAplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //se carga la interfaz principal
        VentanaIniciarSesion inicioApp=new VentanaIniciarSesion();
        inicioApp.setBounds(0, 0, 1920, 1080);
        inicioApp.setVisible(true);
    }
    
}
