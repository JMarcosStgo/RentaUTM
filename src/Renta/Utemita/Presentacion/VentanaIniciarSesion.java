/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Presentacion;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Ventana de inicio de la aplicacion
 * @author Marcos
 * @version 1.0
 */
public class VentanaIniciarSesion extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new IniciarSesionVista(primaryStage);
        
        AnchorPane root2 = new AnchorPane();
        Button b1= new Button("PRUEBA");
        // place button in the top right corner
        root2.setRightAnchor(b1, 500d); // distance 0 from right side of 
        root2.setTopAnchor(b1, 500d); // distance 0 from top

        root.getChildren().add(b1);
        //se define el color
        root.setStyle("-fx-background-color: #336699;");
        Pane.layoutInArea(root, 100, 100, 100, 100, 100, Insets.EMPTY, true, true, HPos.CENTER, VPos.CENTER, true);
        Pane.positionInArea(root, 100, 100, 100, 100, 100, Insets.EMPTY, HPos.CENTER, VPos.CENTER, true);
        root.setPadding(new Insets(200, 400, 100, 500));
        
        //tamaño de la escena ancho-alto
        Scene scene = new Scene(root,1550, 800,Color.CORAL);
        
        primaryStage.setScene(scene);
        //primaryStage.setOpacity(0.9);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Inicio de sesión");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
