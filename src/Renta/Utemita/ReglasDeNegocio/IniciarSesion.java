package Renta.Utemita.ReglasDeNegocio;

import Renta.Utemita.Presentacion.VentanaLogin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Ventana de inicio de la aplicacion
 * @author Marcos
 * @version 1.0
 */
public class IniciarSesion extends Application {
    Double ancho;
    Double altura;
    @Override
    public void start(Stage primaryStage) {
        ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        altura=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        //primaryStage.setX(ancho/6);
        //primaryStage.setY(altura/5);
        ///////////////////////////////////////////////////////////////////////////77
        //Drawing a Circle 
      Circle circle = new Circle(); 
      
      //Setting the position of the circle 
      circle.setCenterX(0.0f); 
      circle.setCenterY(0.0f); 
     
      //Setting the radius of the circle 
      circle.setRadius(25.0f); 
      
      //Setting the color of the circle 
      circle.setFill(Color.GREEN); 
      
      //Setting the stroke width of the circle 
      circle.setStrokeWidth(10); 
       
      //Creating scale Transition 
      ScaleTransition scaleTransition = new ScaleTransition(); 
      
      //Setting the duration for the transition 
      scaleTransition.setDuration(Duration.millis(2000)); 
      
      //Setting the node for the transition 
      scaleTransition.setNode(circle); 
      
      //Setting the dimensions for scaling 
      scaleTransition.setByY(1.5); 
      scaleTransition.setByX(1.5); 
      
      //Setting the cycle count for the translation 
      scaleTransition.setCycleCount(50); 
      
      //Setting auto reverse value to true 
      scaleTransition.setAutoReverse(false); 
      
      //Playing the animation 
      scaleTransition.play(); 
     
      //Creating a Group object  
      Group root3 = new Group(circle); 
      ///////////////////////////////////////////////////////////////////////////////
        
        Pane root = new VentanaLogin(primaryStage);
        
        Image image = new Image(getClass().getResourceAsStream("/imagenes/LOGO4.jpg")); 
        Label myLabel = new Label("Sistema de busqueda de cuartos");
        myLabel.setGraphic(new ImageView(image));
        root.getChildren().add(myLabel);
        root.setMaxSize(ancho/4, altura/4);
        Stop[] stops = new Stop[] {
           //new Stop(0, Color.web("#3399ff")),
            new Stop(0, Color.web("blue")),
           //new Stop(1, Color.web("#33beff")),
           //new Stop(1, Color.web("#33e3ff")),
            new Stop(1, Color.web("#33ffe0")),
           //new Stop(1, Color.web("#33ff9c")),
        };
        LinearGradient gp = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops);
        //se define el color de fondo de la parte interna
        root.setStyle("-fx-background-color: #ffffff;");
        Pane.layoutInArea(root, 500, 80, 0, 00, 0, Insets.EMPTY, true, true, HPos.CENTER, VPos.CENTER, true);
        //padding de la parte central
        root.setPadding(new Insets(50, 00, 400, 100));
        //tamaño de la escena ancho-alto
        Scene scene = new Scene(root,ancho-(ancho/3),altura-(altura/3),gp);
        
        /*pasar datos entre ventanas*/
        
        /**se muestra la escena*/
        primaryStage.setScene(scene);
        root.setOpacity(1);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("Inicio de sesión");
        primaryStage.show();
    }

    /**
     *@param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
