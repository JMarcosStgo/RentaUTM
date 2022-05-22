/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Presentacion;

import java.awt.GradientPaint;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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
        
        //AnchorPane root2 = new AnchorPane();
        //Button b1= new Button("PRUEBA");
        // place button in the top right corner
        //AnchorPane.setRightAnchor(b1, 500d); // distance 0 from right side of 
        //root2.setTopAnchor(b1, 500d); // distance 0 from top
        //root.getChildren().add(b1);
        
        //estilo linergradient
        Stop[] stops = new Stop[] {
           new Stop(0, Color.PURPLE),
           new Stop(1, Color.BLUE)
        };
        LinearGradient gp = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        //root.setBackground(Background.fill(gp));
        
        //se define el color de fondo de la parte interna
        root.setStyle("-fx-background-color: #b0bec5;");
        
        Pane.layoutInArea(root, 400, 100, 100, 100, 100, Insets.EMPTY, true, true, HPos.CENTER, VPos.CENTER, true);
        Pane.positionInArea(root, 400, 100, 100, 100, 100, Insets.EMPTY, HPos.CENTER, VPos.CENTER, true);
        root.setPadding(new Insets(200, 400, 100, 500));
        
        //tamaño de la escena ancho-alto
        Scene scene = new Scene(root,1550, 800,gp);
        
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
