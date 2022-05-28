package Renta.Utemita.Presentacion;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Ventana para el registro y modificación de una propiedad
 * @author Marcos
 * @version 1.0
 */
public class VentanaPropiedad extends Application{

    /**
     * @param args llama al metodo star principal de la ventana
     */
    
    Double ancho;
    Double altura;
    String descripcionCuarto;
    float precio;
    int disponibilidad;
    String ubicacion;
    String servicios;
    String usuario="";
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
/*
necesitare un Vbox,MenuBar,Label,SplitPane,AnchorPane,ScrollPane,Hbox
	1.-Descripción del cuarto: Tipo String.
	2.-Precio: Tipo float.
	3.-Disponibilidad:Tipo int.
	4.-Ubicación (link de Google Maps):Tipo String.
	5.-Imágenes del cuarto:Tipo blob
	6.-Servicios:Tipo:String
    
*/
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        altura=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        //se crea el Vbox donde iran los elementos de la escena
         VBox vbox = new VBox(8); // spacing = 8
         vbox.setBackground(Background.fill(Paint.valueOf("#fff123")));
         
         MenuBar menuBar=new MenuBar();
         menuBar.setBackground(Background.fill(Paint.valueOf("#000000")));
         vbox.getChildren().add(menuBar);
         
         final double MAX_FONT_SIZE =80; // define max font size you need
        //label.setFont(new Font(MAX_FONT_SIZE)); // set to Label
         Label bienvenido=new Label("Buenvenido"+usuario);
         bienvenido.setFont(new Font(MAX_FONT_SIZE));
         bienvenido.setScaleX(1);
         bienvenido.setPadding(new Insets(10, 10, 10, 1200));
         bienvenido.setAlignment(Pos.CENTER);
         bienvenido.setStyle("-fx-background-color: #ffffff;");
         //bienvenido.setBackground(Background.fill(Paint.valueOf("#000000")));
         vbox.getChildren().add(bienvenido);
        //MenuBar  
        //vbox.getChildren().addAll(new Button("Cut"), new Button("Copy"), new Button("Paste"));
        //Define las propiedades de la escena 
        Scene escena=new Scene(vbox,ancho,altura);
        
        //se configura el stage principal
        primaryStage.setTitle("Registro Propiedad");
        primaryStage.setScene(escena);
        primaryStage.show();
    }
    
}
