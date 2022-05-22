/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Presentacion;

import Renta.Utemita.ServicioBD.AccesoBD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * se define los elementos que habra en el escenario de inicio de sesión
 * @author Marcos
 * @version 1.0
 */
public class IniciarSesionVista extends FlowPane{
    /*
     *constructor por default 
     * 
     */
     Stage primaryStage;
     Label lb1Correo = new Label("Correo");
     TextField txtCorreo = new TextField();
     Label lb2Password = new Label("password");
     final PasswordField password = new PasswordField();
     Button bt1IniciarSesion = new Button("Iniciar sesión");
     Boolean sesionStatus;
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
         lb1Correo.setLayoutX(500);
         lb1Correo.setLayoutY(500);
         lb2Password.setLayoutX(500);
         lb2Password.setLayoutY(800);
         getChildren().add(lb1Correo);
         getChildren().add(txtCorreo);        
         getChildren().add(lb2Password);
         getChildren().add(password);
         getChildren().add(bt1IniciarSesion);
         //accion al pulsar el boton
         bt1IniciarSesion.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent t) {
             //conexion con la base de datos
             AccesoBD conexion=new AccesoBD();
             conexion.iniciarBD();
             sesionStatus=conexion.existeUsuario(txtCorreo.getText(), password.getText());
             System.out.println("entro"+sesionStatus);
             conexion.DesconectarBD();
             }
             
         });
     }
   
}
