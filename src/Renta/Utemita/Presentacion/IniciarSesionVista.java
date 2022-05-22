/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Presentacion;

import Renta.Utemita.ServicioBD.AccesoBD;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.Icon;
import javax.swing.JOptionPane;
/**
 * se define los elementos que habra en el escenario de inicio de sesión
 * @author Marcos
 * @version 1.0
 */
public class IniciarSesionVista extends FlowPane{

    Timeline timeline;
/*
     *constructor por default
     *
     */
     Stage primaryStage;
     Label lb1Correo = new Label("Correo");
     TextField txtCorreo = new TextField();
     Label lb2Password = new Label("Password");
     final PasswordField password = new PasswordField();
     Button bt1IniciarSesion = new Button("Iniciar sesión");
     Boolean sesionStatus;
     Text registrarse = new Text("Registrarse");
     Text ingresarMsj = new Text("Ingresar a Renta Utemita");
     Paint paint = Paint.valueOf("blue");
     Paint paint2 = Paint.valueOf("#2b6ff6");
     Paint blanco = Paint.valueOf("#ffffff");
     Paint negro = Paint.valueOf("#000000");
     Reflection reflection = new Reflection();
     GridPane grid = new GridPane();
     Glow glow = new Glow();
     Bloom blom = new Bloom();
     InnerShadow shadow = new InnerShadow(); 
     
     public IniciarSesionVista(Stage escenario){
         primaryStage=escenario; 
         init();
     }
     public void init(){
         lb1Correo.setFont(new Font("Serif", 28));
         txtCorreo.setFont(new Font("Serif", 28));
         password.setFont(new Font("Serif", 28));
         bt1IniciarSesion.setFont(new Font("Serif", 28));
         lb2Password.setFont(new Font("Serif", 28));
         registrarse.setFont(new Font("Serif", 28));
         registrarse.setFill(paint);
         ingresarMsj.setFont(new Font("Arial", 38));
         ingresarMsj.setStyle("-fx-font-weight: bold;");
         ingresarMsj.setFill(paint);
         //ingresarMsj.setEffect(reflection);
         //ingresarMsj.setEffect(new DropShadow(1, 20, 10,javafx.scene.paint.Color.web("#333333")));

         ingresarMsj.setLineSpacing(50);
         
         //setting the level property 
         shadow.setBlurType(BlurType.GAUSSIAN);  
        shadow.setColor(javafx.scene.paint.Color.web("#eaedf2"));  
        shadow.setHeight(25);  
        shadow.setRadius(12);  
        shadow.setWidth(20);  
        shadow.setChoke(0.9);
        
        glow.setLevel(0.4);
        blom.setThreshold(0.8);
        bt1IniciarSesion.setEffect(shadow);
        //bt1IniciarSesion.setEffect(glow);
        bt1IniciarSesion.setTextFill(blanco);
        bt1IniciarSesion.setBackground(Background.fill(paint2));
    
         /*
         lb1Correo.setAlignment(Pos.CENTER);
         lb1Correo.setLayoutY(500);
         lb2Password.setLayoutX(500);
         lb2Password.setLayoutY(800);
         bt1IniciarSesion.setLayoutX(1000);
         */
        /*grid.setHgap(10);
        grid.setVgap(12);
        grid.add(lb1Correo, 0, 0);
        grid.add(txtCorreo, 1, 0);
        grid.add(lb2Password, 0, 1);
        grid.add(password, 1, 1);
        */
        
        
         getChildren().add(ingresarMsj);
         getChildren().add(lb1Correo);
         getChildren().add(txtCorreo);        
         getChildren().add(lb2Password);
         getChildren().add(password);
         getChildren().add(bt1IniciarSesion);
         getChildren().add(registrarse);
         getChildren().add(grid);
         
         //accion al pulsar el boton
         bt1IniciarSesion.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent t) {
                 bt1IniciarSesion.setBackground(Background.fill(blanco));
                 bt1IniciarSesion.setTextFill(negro);
                 
                 //si se presiona el boton llama al metodo nuevoVentana para abrir la escena donde esta la vista general
                if((t).getSource()==bt1IniciarSesion){
                    System.out.println("entra al pulsar"); 
                    
                    //conexion con la base de datos
                    AccesoBD conexion=new AccesoBD();
                    conexion.iniciarBD();
                    sesionStatus=conexion.existeUsuario(txtCorreo.getText(), password.getText());
                    System.out.println("entro"+sesionStatus);
                    conexion.DesconectarBD();
                    if(sesionStatus)
                        nuevaVentana();
                    else{
                        System.out.println("error");
                        /*
                        Alert mensaje = new Alert(Alert.AlertType.ERROR);
                        mensaje.setTitle("Error de inicio de sesión");
                        mensaje.setHeaderText("Password o Correo incorrectos");
                        mensaje.show();
                        */
                       JOptionPane.showMessageDialog(null,"Correo o Password invalidos");
                        //JOptionPane.showMessageDialog(null,"Usuario o Password son incorrectos");
                        //new Alert(Alert.AlertType.ERROR, "This is an error!").showAndWait();
                    }
               }
                 bt1IniciarSesion.setBackground(Background.fill(negro));
                 bt1IniciarSesion.setTextFill(blanco);
                 
             }
             
         });
     }
    public void nuevaVentana(){
        try {
            MenuPrincipal1 menuPrincipal1=new MenuPrincipal1();
            menuPrincipal1.start(primaryStage);
        } catch (Exception e) {
        }
    }
   
}
