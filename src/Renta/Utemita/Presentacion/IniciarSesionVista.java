/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Presentacion;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
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
     Label lb1Nombre = new Label("Correo");
     TextField txtNombre = new TextField();
     Label lb2Password = new Label("password");
     TextField txt2Paswoord = new TextField();
     
     Button bt1IniciarSesion = new Button("Iniciar sesión");
     public IniciarSesionVista(Stage escenario){
         primaryStage=escenario;
         
        // lb1Nombre.setLayoutX(500);
         //lb1Nombre.setLayoutY(500);
         //lb2Password.setLayoutX(500);
         //lb2Password.setLayoutY(800);
         
         getChildren().add(lb1Nombre);
         getChildren().add(txtNombre);
         
         getChildren().add(lb2Password);
         getChildren().add(txt2Paswoord);
         getChildren().add(bt1IniciarSesion);
     }
   
}
