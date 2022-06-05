/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Presentacion;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Marcos
 */
public class MenuPrincipal1 extends Application {
    double ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    double altura = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
       
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setX(0);
        primaryStage.setY(0);
//        primaryStage.showAndWait();
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
                
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                VentanaPropiedad ventanaP = new VentanaPropiedad();
                try {
                    ventanaP.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(MenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, ancho, altura);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
