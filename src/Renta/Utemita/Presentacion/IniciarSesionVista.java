/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Presentacion;

import Renta.Utemita.Almacenamiento.AccesoBD;
import static java.awt.Event.ENTER;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
     Text mensajeError=new Text("Error al ingresar su Correo o Password");
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
         capturarEnter(txtCorreo);
         capturarEnter2(password);
         
         init();
     }
     public void init(){
         
         txtCorreo.setStyle("-fx-width:1000px;");
         lb1Correo.setFont(new Font("Serif", 28));
         txtCorreo.setFont(new Font("Serif", 28));
         password.setFont(new Font("Serif", 28));
         bt1IniciarSesion.setFont(new Font("Serif", 28));
         lb2Password.setFont(new Font("Serif", 28));
         registrarse.setFont(new Font("Serif", 28));
         mensajeError.setFont(new Font("Serif", 28));
         registrarse.setFill(paint);
         ingresarMsj.setFont(new Font("Arial", 38));
         ingresarMsj.setStyle("-fx-font-weight: bold;");
         ingresarMsj.setFill(paint);
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
        bt1IniciarSesion.setTextFill(blanco);
        bt1IniciarSesion.setBackground(Background.fill(paint2));

        grid.setHgap(10);
        grid.setVgap(12);
        grid.add(lb1Correo, 0, 0);//10,01,11
        grid.add(txtCorreo, 1, 0);
        grid.add(lb2Password, 0, 1);
        grid.add(password, 1, 1);
        grid.add(bt1IniciarSesion,1,2);
        grid.add(registrarse,1,4);
        
        getChildren().add(ingresarMsj);
        getChildren().add(grid);
         
         /*accion al pulsar el boton*/
         bt1IniciarSesion.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent t) {
                 bt1IniciarSesion.setBackground(Background.fill(blanco));
                 bt1IniciarSesion.setTextFill(negro);
                
                /*si se presiona el boton llama al metodo nuevoVentana para abrir la escena donde esta la vista general*/
                if((t).getSource()==bt1IniciarSesion){
                    System.out.println("entra al pulsar"); 
                    
                    //conexion con la base de datos
                    AccesoBD conexion=new AccesoBD();
                    conexion.iniciarBD();
                    sesionStatus=conexion.existeUsuario(txtCorreo.getText(), password.getText());
                    System.out.println("entro"+sesionStatus);
                    conexion.DesconectarBD();
                    try {
                        if(sesionStatus)
                            nuevaVentana();
                        else{
                            /*si el usuario ingresa un dato incorrecto se muestra el mensaje de error en pantala*/
                            System.out.println("error");
                            grid.add(mensajeError,1,3);
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
               }
                /*Se cambia de color el boton cuando hay un inicio incorrecto*/
                 bt1IniciarSesion.setBackground(Background.fill(negro));
                 bt1IniciarSesion.setTextFill(blanco);
             }
             
         });
     }
     
    public void capturarEnter(TextField entrada){
        
        //capturar enter
        entrada.addEventFilter(KeyEvent.KEY_PRESSED,(KeyEvent E)->{
            //si se presiona enter
            if(E.equals(ENTER)){
                System.out.println("entro cuando se pulso en correo");
            }
        });
        
    }
    public void capturarEnter2(PasswordField entrada){
        
        //capturar enter
        entrada.addEventFilter(KeyEvent.KEY_PRESSED,(KeyEvent E)->{
            //si se presiona enter
            if(E.equals(ENTER)){
                System.out.println("entro cuando se pulso en contraseña");
            }
        });
        
    }
/**
 *metodo para lanzar la ventana
 */
    public void nuevaVentana(){
        try {
            MenuPrincipal1 menuPrincipal1=new MenuPrincipal1();
            menuPrincipal1.start(primaryStage);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
