package Renta.Utemita.Presentacion;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Ventana de inicio de la aplicacion
 * @author Marcos
 * @version 1.0
 */
public class VentanaIniciarSesion extends Application {
    Double ancho;
    Double altura;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new IniciarSesionVista(primaryStage);
        Image image = new Image(getClass().getResourceAsStream("/imagenes/logo2.jpg")); 
        Label myLabel = new Label("Insertar frase mamalona");
        myLabel.setGraphic(new ImageView(image));
        root.getChildren().add(myLabel);
        //estilo linergradient
        Stop[] stops = new Stop[] {
           new Stop(0, Color.PURPLE),
           new Stop(1, Color.BLUE)
        };
        LinearGradient gp = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops);
        //se define el color de fondo de la parte interna
        root.setStyle("-fx-background-color: #ffffff;");
        
        Pane.layoutInArea(root, 500, 100, 100, 100, 100, Insets.EMPTY, true, true, HPos.CENTER, VPos.CENTER, true);
        Pane.positionInArea(root, 500, 0, 100, 100, 100, Insets.EMPTY, HPos.CENTER, VPos.CENTER, true);
        //padding de la parte central
        root.setPadding(new Insets(200, 400, 100, 500));
        
        ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        altura=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        System.out.println("Renta.Utemita.Presentacion.VentanaIniciarSesion.start()"+ancho+altura);
        //tamaño de la escena ancho-alto
        
        Scene scene = new Scene(root,ancho,altura,gp);
        
        primaryStage.setScene(scene);
        root.setOpacity(1);
        primaryStage.setResizable(true);
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
