package Renta.Utemita.Presentacion.VentanaLogin;

import Renta.Utemita.ReglasDeNegocio.IniciarSesion.IniciarSesion;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * ventana donde se implementa el inicio de sesión
 * @author Marcos
 * @version 1.0
 */
public class VentanaLogin extends Application {
    private double ancho;
    private double altura;
    
    /**
     *@param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /*metodo que lanza la ventana*/
    @Override
    public void start(Stage primaryStage) {
        //ancho de la pantalla
        ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        altura=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        //logo de inicio
        primaryStage.getIcons().add(new Image("/imagenes/LOGO4.jpg"));
        
        Pane root = new IniciarSesion(primaryStage);
        Image image = new Image(getClass().getResourceAsStream("/imagenes/LOGO4.jpg")); 
        Label myLabel = new Label("Sistema de búsqueda de cuartos");
        myLabel.setFont(new Font("Serif", 20));
        myLabel.setAlignment(Pos.CENTER);
        myLabel.setGraphic(new ImageView(image));
        root.getChildren().add(myLabel);
        root.setMaxSize(ancho/4, altura/4);
        Stop[] stops = new Stop[] {
            new Stop(0, Color.web("blue")),
            new Stop(1, Color.web("#33ffe0")),
        };
        //color de fondo detras del grid
        LinearGradient gp = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops);
        //se define el color de fondo de la parte interna
        root.setStyle("-fx-background-color: #ffffff;");
        Pane.layoutInArea(root, 500, 80, 0, 00, 0, Insets.EMPTY, true, true, HPos.CENTER, VPos.CENTER, true);
        //padding de la parte central
        root.setPadding(new Insets(50, 00, 400, 100));
        //tamaño de la escena ancho-alto
        Scene scene = new Scene(root,ancho-(ancho/3),altura-(altura/3),gp);
        /*se muestra la escena*/
        primaryStage.setScene(scene);
        root.setOpacity(1);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("Inicio de sesión");
        primaryStage.show();
    }
}
