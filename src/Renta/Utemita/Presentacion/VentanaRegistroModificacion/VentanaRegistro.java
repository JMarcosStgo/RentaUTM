package Renta.Utemita.Presentacion.VentanaRegistroModificacion;

import Renta.Utemita.ReglasDeNegocio.IniciarSesion.IniciarSesion;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario.RegistrarModificarUsuario;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario.Usuario;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
/**
 * Ventana para el registro de un usuario en la BD
 * @author Marcos
 * @version 1.0
 */
public class VentanaRegistro extends Application{
    double ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    double altura=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    GridPane grid = new GridPane();
    Pane pane=new Pane();
    InnerShadow shadow = new InnerShadow(); 
    Paint paint=Paint.valueOf("blue");
    Text tituloForm = new Text("Registro Usuario");
    Text txtNombre = new Text("Nombre");
    Text txtTipo = new Text("Seleccione como va ingresar");
    TextField textFNombre = new TextField();
    Text txtCorreo = new Text("Correo");
    TextField txtFCorreo = new TextField();
    Text txtMatricula = new Text("Matricula");
    TextField txtFMatricula = new TextField();
    Text txtContraseña = new Text("Contraseña");
    ChoiceBox cb = new ChoiceBox();
    Text txtTelefono = new Text("Telefono");
    TextField txtFContraseña = new TextField();
    TextField txtFTelefono = new TextField();
    Paint blanco = Paint.valueOf("#ffffff");
    private Button ingresar=new Button("Registrarse");
    private Button cancelarRegistro=new Button("Cancelar Registro");
    
    Text alerta = new Text("Error, ingresar datos correctos");
    Paint paint2 = Paint.valueOf("#2b6ff6");
     
    /*variables*/
    private int idUsuario;
    private String nombre;
    private long telefono;
    private String correo;
    private String matricula;
    private String contraseña;
    private boolean datos;
    private boolean registro;
    private String tipo;
    /**
     * @param args llama al metodo star principal de la ventana
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
   
    /*metodo star- lanza las acciones de la ventana*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        shadow.setBlurType(BlurType.GAUSSIAN);  
        shadow.setColor(javafx.scene.paint.Color.web("#eaedf2"));  
        shadow.setHeight(25);  
        shadow.setRadius(12);  
        shadow.setWidth(20);  
        shadow.setChoke(0.9);
        /*grid del formulario para registrar modificar propiedad*/
        grid.setStyle("-fx-background-color:white");//colorde fondo  del grid
        grid.setPadding(new Insets(altura/10,0,50,100));
        grid.setMinHeight(altura-(altura/10));
        //Ancho del scroll
        grid.setMinWidth(ancho);
        alerta.setStyle("-fx-background-color: #ffffff;");
        alerta.setFont(new Font("Arial",30));        
        tituloForm.setStyle("-fx-background-color: #ffffff;");
        tituloForm.setFont(new Font("Arial",30));
        tituloForm.setStyle("-fx-padding-left:600px;");
        tituloForm.setTextAlignment(TextAlignment.CENTER);
        tituloForm.setStyle("-fx-font-weight: bold;");
        tituloForm.setFill(paint);
        
        txtNombre.setStyle("-fx-background-color: #ffffff;");
        txtNombre.setFont(new Font("Arial",28));
        textFNombre.setMinWidth(600);
        textFNombre.setMinHeight(50);
        
        txtMatricula.setStyle("-fx-background-color: #ffffff;");
        txtMatricula.setFont(new Font("Arial",28));
        txtFMatricula.setMinWidth(600);
        txtFMatricula.setMinHeight(50);
        
        txtContraseña.setStyle("-fx-background-color: #ffffff;");
        txtContraseña.setFont(new Font("Arial",28));
        txtFContraseña.setMinWidth(600);
        txtFContraseña.setMinHeight(50);
        
        txtTelefono.setStyle("-fx-background-color: #ffffff;");
        txtTelefono.setFont(new Font("Arial",28));
        txtFTelefono.setMinWidth(600);
        txtFTelefono.setMinHeight(50);
        
        txtCorreo.setStyle("-fx-background-color: #ffffff;");
        txtCorreo.setFont(new Font("Arial",28));
        txtFCorreo.setMinWidth(600);
        txtFCorreo.setMinHeight(50);
        txtTipo.setStyle("-fx-background-color: #ffffff;");
        txtTipo.setFont(new Font("Arial",28));
        
        ingresar.setMinSize(200,50);
        ingresar.snapPositionX(200);
        ingresar.setEffect(shadow);
        ingresar.setTextFill(blanco);
        ingresar.setBackground(Background.fill(paint2));
        cancelarRegistro.setMinSize(200,50);
        cancelarRegistro.snapPositionX(200);
        cancelarRegistro.setEffect(shadow);
        cancelarRegistro.setTextFill(blanco);
        cancelarRegistro.setBackground(Background.fill(paint2));
        
        
        tituloForm.setX(200);
        pane.getChildren().add(tituloForm);
        pane.setMinHeight(50);
        
        cb.getItems().addAll("Estudiante","Arrendador");
        cb.setValue(cb.getItems().get(0));
        cb.setMaxWidth(200);
        cb.setMinHeight(50);
        cb.setStyle("-fx-font-weight: bold;-fx-font-size:20px");
        Group botones = new Group();
        botones.getChildren().add(ingresar);
        cancelarRegistro.setLayoutX(ancho/3);
        botones.getChildren().add(cancelarRegistro);
        
        /*letra de los inputs del formulario*/
        textFNombre.setFont(new Font("Serif", 22));
        txtFContraseña.setFont(new Font("Serif", 22));
        txtFCorreo.setFont(new Font("Serif", 22));
        txtFMatricula.setFont(new Font("Serif", 22));
        txtFTelefono.setFont(new Font("Serif", 22));
        /*grid donde se añade cada elemento del formulario*/
        grid.setHgap(5);
        grid.setVgap(6);
        grid.add(pane, 0,1);
        grid.add(txtNombre, 0, 2);
        grid.add(textFNombre, 0, 3);
        grid.add(txtTipo, 0, 4);
        grid.add(cb, 0, 5);
        grid.add(txtMatricula,0,6);
        grid.add(txtFMatricula,0,7);
        grid.add(txtContraseña,0,8);
        grid.add(txtFContraseña,0,9);
        grid.add(txtCorreo,0,10);
        grid.add(txtFCorreo,0,11);
        grid.add(txtTelefono,0,12);
        grid.add(txtFTelefono,0,13);
        grid.add(botones,0,14);
        //grid.add(cancelarRegistro,1,14);
        grid.add(alerta,0,15);
        alerta.setOpacity(0);
        /*configuracion de la escena y los elementos que tendra*/
        Group root = new Group();
        final StackPane sp1 = new StackPane();
        //fondo del pandel lateral
        Stop[] stops = new Stop[] {
           new Stop(0, Color.PURPLE),
           new Stop(1, Color.BLUE)
        };
        LinearGradient gp = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops);
        grid.setAlignment(Pos.CENTER);
        root.getChildren().add(grid);
        /*Define las propiedades de la escena*/ 
        Scene scene = new Scene(root, ancho, altura,gp);
        primaryStage.setTitle("Registro Usuario");
        primaryStage.setScene(scene);
        
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        
        /* accion cuando selecciona cb como estudiante y arrendador*/
        tipo=(String) cb.getItems().get(0);
        //System.out.println("tipo default"+tipo);
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                 tipo=(String) cb.getItems().get((Integer)t1);
                 //System.out.println(cb.getItems().get((Integer) t1)+"tipo"+tipo);
                 if(tipo.equals("Arrendador")){
                     txtMatricula.setOpacity(0);
                     txtFMatricula.setOpacity(0);
                     matricula="";
                     //System.out.println("entra if cb"+matricula);
                 }else{
                    matricula=txtFMatricula.getText();
                    txtMatricula.setOpacity(1);
                    txtFMatricula.setOpacity(1);
                    //System.out.println("entra else cb"+matricula);
                 }
                 
            }
        });
            
        /*evento al pulsar el boton */
        ingresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    nombre=textFNombre.getText();
                    correo=txtFCorreo.getText();
                    contraseña=txtFContraseña.getText();
                    String tel=txtFTelefono.getText();
                    telefono=Long.parseLong(tel);
                    matricula=txtFMatricula.getText();
                    //System.out.println("telefono"+telefono);
                    registrarUsuario(nombre, telefono, correo, contraseña,matricula,tipo);
                    /*se abre la nueva ventana*/
                    if(registro){
                        IniciarSesion inicio = new IniciarSesion();
                        primaryStage.setX(ancho/6);
                        inicio.start(primaryStage);
                    }
                } catch (NumberFormatException e) {
                    alertasUsuario();
                    System.out.println("error "+ e.getLocalizedMessage());
                }
            }
        });
        
        /*evento cuando da clic en cancelar registro*/
        cancelarRegistro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                IniciarSesion sesion = new IniciarSesion();
                sesion.start(primaryStage);
            }
        });
        /*muestra interfaz*/
        primaryStage.show();
    
    
    }
    /**
     * Método para hacer la solicitud de registro de un usuario
     * @param nombre
     * @param telefono
     * @param correo
     * @param contraseña
     * @param matricula
     * @param tipo 
     */
    public void registrarUsuario(String nombre, long telefono, String correo, String contraseña,String matricula,String tipo){
        RegistrarModificarUsuario rModUser = new RegistrarModificarUsuario();
        Usuario usuario=new Usuario(nombre, telefono, correo, contraseña, matricula,tipo);
        datos=rModUser.verificarDatos(nombre, telefono, correo, matricula, contraseña); 
        //System.out.println("datos registrar usuario"+datos+"longitud matricula"+matricula.length()+ "---"+nombre+telefono+correo+matricula+contraseña);
        if(datos){
            registro=rModUser.solicitarRegistro(usuario);
            //System.out.println("registro "+registro);
        }
        else
            alertasUsuario();
        
    }
    /**
     * Método para mostrar la alerta cuando uno o mas campos no se ingresan correctamente
     */
    public void alertasUsuario(){
        alerta.setOpacity(1);
        //System.out.println("alertas usuario");
    }
}
