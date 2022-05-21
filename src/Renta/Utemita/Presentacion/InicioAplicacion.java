/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Presentacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jdk.javadoc.internal.tool.Main;

/**
 *
 * @author kAarl
 */
public class InicioAplicacion extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //se carga la interfaz principal
       // VentanaIniciarSesion inicioApp=new VentanaIniciarSesion();
       // inicioApp.setBounds(0, 0, 1920, 1080);
       // inicioApp.setVisible(true);
       launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
      // Parent root=FXMLLoader.load(getClass().getResource("Ventana.fxml"));
       //try { 
         //    URL myfile = new File(f.getAbsolutePath()).toURL();
         //  scene.getStylesheets().add(myfile.toExternalForm());
         //FXMLLoader loader = new FXMLLoader(Main.class.getResource("Ventana.fxml"));
        //System.out.println(loader); 
         //Parent root =  loader.load();
        // Scene escena=new Scene(root);
         stage.setTitle("Renta Utemita");
         stage.setMaxWidth(1920);
         //stage.setScene(escena);
         stage.show();
    
       //} 
       //catch(Exception e){
        //   System.out.println("errror------------"+e.getLocalizedMessage());
     //  }
    }
    
}
