package Renta.Utemita.ReglasDeNegocio.IniciarSesion;

import Renta.Utemita.Almacenamiento.AccesoBD.AccesoBD;
import Renta.Utemita.Presentacion.VentanaCuartos.VentanaCuartos;
import Renta.Utemita.Presentacion.VentanaRegistroModificacion.VentanaRegistro;
import static java.awt.Event.ENTER;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * se define los elementos que habra en el escenario de inicio de sesión
 * @author Marcos
 * @version 1.0
 */
public class IniciarSesion extends FlowPane{
     Timeline timeline;
     Stage primaryStage;
     Label lb1Correo = new Label("Correo");
     Text mensajeError=new Text("Error, Correo o Password invalidos, ingresar datos nuevamente");
     TextField txtCorreo = new TextField();
     Label lb2Password = new Label("Password");
     final PasswordField password = new PasswordField();
     Button bt1IniciarSesion = new Button("Iniciar sesión");
     Boolean datosVerificar;
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
     int bandera=0;
     
     /*
     *constructor por default
     */
     public IniciarSesion(Stage escenario){
         primaryStage=escenario;
         init();
     }
     /**
      * Metodo donde se inicializan elementos que estaran presentes en la escene de inicio de sesión
      */
     public void init(){
        txtCorreo.setStyle("-fx-width:1000px;");
        lb1Correo.setFont(new Font("Serif", 28));
        txtCorreo.setFont(new Font("Serif", 26));
        password.setFont(new Font("Serif", 28));
        bt1IniciarSesion.setFont(new Font("Serif", 28));
        lb2Password.setFont(new Font("Serif", 26));
        registrarse.setFont(new Font("Serif", 28));
        mensajeError.setFont(new Font("Serif", 20));
        registrarse.setFill(paint);
        ingresarMsj.setFont(new Font("Arial", 38));
        ingresarMsj.setStyle("-fx-font-weight: bold;");
        ingresarMsj.setFill(paint);
        ingresarMsj.setLineSpacing(50);
        
        //asignando propiedades a un shadow para el efecto del boton 
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
        
        //fondo del grid de inicio de sesion
        grid.add(lb1Correo, 1, 0);//10,01,11
        grid.add(txtCorreo, 2, 0);
        grid.add(lb2Password, 1, 1);
        grid.add(password, 2, 1);
        grid.add(bt1IniciarSesion,2,2);
        grid.add(registrarse,2,4);
        mensajeError.setOpacity(0.0);
        grid.add(mensajeError,2,3);
        grid.setMaxWidth(800);
        grid.setMaxHeight(200);
        //se añaden los elementos al grid
        getChildren().add(ingresarMsj);
        getChildren().add(grid);
        
        /*evento para capturar enter y accionar el boton iniciar*/
        setOnKeyPressed(ke -> {
        KeyCode keyCode = ke.getCode();
        if (keyCode.equals(KeyCode.ENTER)) {
            bandera=1;
            iniciarSesion();
        }else
            bandera=0;
        });
           
        /*accion al dar clic al boton iniciar sesión*/
        if(bandera!=1){
            bt1IniciarSesion.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent t) {
                 bt1IniciarSesion.setBackground(Background.fill(blanco));
                 bt1IniciarSesion.setTextFill(negro);
                if((t).getSource()==bt1IniciarSesion){
                    iniciarSesion();
               }
             } 
         });
        }
        
        registrarse.setOnMouseClicked(new EventHandler<MouseEvent>(){
             @Override
             public void handle(MouseEvent t) {
                 //System.out.println("CLIC EN REGISTRARSE");
                 VentanaRegistro vRM = new VentanaRegistro();
                 try {
                     //Stage stage = new Stage();
                     //primaryStage.close();
                     vRM.start(primaryStage);
                 } catch (Exception ex) {
                     Logger.getLogger(IniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
            
        });
     }
     /**
      * Método para detectar la acción al pulsar enter
      * @param entrada 
      */
    public void capturarEnter(TextField entrada){
        entrada.addEventFilter(KeyEvent.KEY_PRESSED,(KeyEvent E)->{
            //si se presiona enter
            if(E.equals(ENTER)){
                System.out.println("entro cuando se pulso en correo");
            }
        });
        
    }
    /**
     *metodo para lanzar la ventana despues de haberse logueado
     */
    public void nuevaVentana(){
        try {
            double ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            double altura = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            System.out.println("nueva ventana"+ancho+altura);
            Stage stage = new Stage();
            stage.setWidth(ancho);
            stage.setHeight(altura);
            VentanaCuartos menuPrincipal1=new VentanaCuartos();
            menuPrincipal1.start(stage);
            primaryStage.close();
            //menuPrincipal1.init();
        } catch (HeadlessException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    /**
     * Metodo que captura la entrada del usuario y la procesa
     */
    public void iniciarSesion(){
                    //System.out.println("entra al pulsar"); 
                    /*conexion con la base de datos*/
                    AccesoBD conexion=new AccesoBD();
                    conexion.iniciarBD();
                    
                    datosVerificar=conexion.existeUsuario(txtCorreo.getText(), password.getText());
                    //System.out.println("entro"+datosVerificar);
                    conexion.DesconectarBD();
                    /*si se presiona el boton llama al metodo nuevoVentana para abrir la escena donde esta la vista general*/
                    try {
                        if(datosVerificar)
                            nuevaVentana();
                        else{
                            /*Se cambia de color el boton cuando hay un inicio incorrecto*/
                             bt1IniciarSesion.setBackground(Background.fill(negro));
                             bt1IniciarSesion.setTextFill(blanco);
                            /*si el usuario ingresa un dato incorrecto se muestra el mensaje de error en pantala*/
                            mostrarAlertas();
                        }
                    } catch (InterruptedException e) {
                        System.out.println("error al iniciar sesión "+e.getLocalizedMessage());
                    }
    }
    /**
     * Metodo que muestra mensaje de error al iniciar sesión
     * @throws java.lang.InterruptedException
     */
    public void mostrarAlertas() throws InterruptedException{
        mensajeError.setOpacity(1);
    }
}
