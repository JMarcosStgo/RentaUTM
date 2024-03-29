package Renta.Utemita.Presentacion.VentanaRegistroModificacion;

import Renta.Utemita.Presentacion.VentanaCuartos.VentanaCuartos;
import Renta.Utemita.Presentacion.VentanaPropiedad.VentanaPropiedad;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario.RegistrarModificarUsuario;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Ventana para la modificación de los datos de un usuario
 * @author Marcos
 * @version 1.0
 */
public class VentanaModificacion extends Application{
    GridPane grid = new GridPane();
    Pane pane=new Pane();
    InnerShadow shadow = new InnerShadow();
    AnchorPane anchorPane=new AnchorPane();
    Paint paint=Paint.valueOf("blue");
    Text tituloForm = new Text("Modificar perfil Usuario");
    Text txtNombre = new Text("Nombre");
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
    private Button ingresar=new Button("Guardar cambios");
    Text alerta = new Text("Error, ingresar datos correctos");
    String usuarioBienvenida;
    Label bienvenido=new Label("Bienvenido, usted a ingresado como"+usuarioBienvenida);
    double ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    double altura=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    Paint paint2 = Paint.valueOf("#2b6ff6");
    String user;
    
    /*variables*/
    private int idUsuario;
    private String nombre;
    private long telefono;
    private String correo;
    private String matricula;
    private String contraseña;
    private boolean datos;
    boolean registro;
    RegistrarModificarUsuario rMU=new RegistrarModificarUsuario();
    Usuario usuario = new Usuario();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
 
        launch(args);
    }
    /**
     * Método para iniciar la interfaz
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*lectura del archivo*/
        try {
            ArrayList<String> ident=leeArchivo("..\\RentaUTM\\src\\Imagenes\\id.txt");
            //System.out.println("ident"+ident);
            idUsuario=Integer.parseInt(ident.get(0));
            user=ident.get(1);
                
        } catch (NumberFormatException e) {
            System.out.println("error conversion al leer archivo"+e.getLocalizedMessage());
        }
        /*cargar los datos al formulario*/
        try {
                btnObtenerDatosModificar(idUsuario);
                textFNombre.textProperty().addListener((obs, oldText, newText) -> {
                    //System.out.println("Text changed from "+oldText+" to "+newText.length());
                    textFNombre.setText(newText);
                });
                textFNombre.setText(usuario.getNombre());
                txtFContraseña.setText(usuario.getContraseña());
                txtFCorreo.setText(usuario.getCorreo());
                txtFMatricula.setText(usuario.getMatricula());
                txtFTelefono.setText(usuario.getTelefono()+"");
                idUsuario=usuario.getIdUsuario();
        } catch (Exception e) {
            System.out.println("error btnObtener"+e.getLocalizedMessage());
        }

        /*--------------------configuracion interfaz -------------------------*/
        shadow.setBlurType(BlurType.GAUSSIAN);  
        shadow.setColor(javafx.scene.paint.Color.web("#eaedf2"));  
        shadow.setHeight(25);  
        shadow.setRadius(12);  
        shadow.setWidth(20);  
        shadow.setChoke(0.9);
        bienvenido.setStyle("-fx-background-color: #ff3f5f;");
        bienvenido.setFont(new Font("Arial",28));
        bienvenido.setMinHeight(altura/10);
        bienvenido.setMinWidth(ancho);
        bienvenido.setAlignment(Pos.CENTER);
       
        /*grid del formulario para registrar modificar propiedad*/
        grid.setStyle("-fx-background-color:white");//colorde fondo  del grid
        grid.setPadding(new Insets(altura/8,0,50,100));
        grid.setMinHeight(altura-(altura/10));
        //Ancho del grid
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
        
        textFNombre.setFont(new Font("Arial",24));
        txtFContraseña.setFont(new Font("Arial",24));
        txtFCorreo.setFont(new Font("Arial",24));
        txtFMatricula.setFont(new Font("Arial",24));
        txtFTelefono.setFont(new Font("Arial",24));
        
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
        
        ingresar.setMinSize(200,50);
        ingresar.snapPositionX(200);
        ingresar.setEffect(shadow);
        ingresar.setTextFill(blanco);
        ingresar.setBackground(Background.fill(paint2));
        
        tituloForm.setX(200);
        pane.getChildren().add(tituloForm);
        pane.setMinHeight(50);
        /*si es alumno se muestra la matricula*/
        if(user.equals("Estudiante")){
            grid.add(txtMatricula,0,4);
            grid.add(txtFMatricula,0,5);
        }
        /*grid donde se añade cada elemento del formulario*/
        grid.setHgap(10);
        grid.setVgap(12);
        grid.add(pane, 0,1);
        grid.add(txtNombre, 0, 2);
        grid.add(textFNombre, 0, 3);
        grid.add(txtContraseña,0,6);
        grid.add(txtFContraseña,0,7);
        grid.add(txtCorreo,0,8);
        
       // grid.add(lUbicacion,0,9);
        grid.add(txtFCorreo,0,9);
        grid.add(txtTelefono,0,10);
        grid.add(txtFTelefono,0,11);
        grid.add(ingresar,0,12);
        grid.add(alerta,0,13);
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
        /*Se coloca el grid dentro del root*/
        root.getChildren().add(grid);
        /*configuracion de la escena y los elementos que tendra*/
        anchorPane.setMinHeight(altura);
        anchorPane.setMaxWidth(ancho);
        anchorPane.setStyle("-fx-background-color: #ffffff;");//color de fondo
        anchorPane.setPadding(new Insets(50,0,0,0));
        /*Scroll del formulario*/
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(ancho, altura);
        /*Se asigna contenido al scrol*/
        scroll.setContent(anchorPane);
        scroll.setStyle("-fx-background-color: #ffffff;");
        /*activar o desactivar barras laterales*/
        scroll.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        //se crea un Group para colocar dentro al splipane 
        SplitPane sp = new SplitPane();
        anchorPane.getChildren().add(grid);
        sp1.setStyle("-fx-background-color: #B4E5FC;");//fondo del lateral izquieerdo
        sp1.setDisable(false);//no permite que se ajuste el panel
        sp1.setMaxSize(ancho/5,altura);
        Text modificarProp = new Text("Modificar/Registrar Propiedad"); 
        modificarProp.setFont(Font.font(null, FontWeight.BOLD, 15));     
        modificarProp.setFill(Color.PURPLE); 
        modificarProp.setX(20); 
        modificarProp.setY(00);       
        Text menu = new Text("Menú principal"); 
        menu.setFont(Font.font(null, FontWeight.BOLD, 15));     
        menu.setFill(Color.PURPLE); 
        menu.setX(20); 
        menu.setY(200);       
        Group paneLateral2 = new Group();
        /*valida vista de menu lateral*/
        if(user.equals("Estudiante"))
            paneLateral2.getChildren().addAll(menu);
        else
            paneLateral2.getChildren().addAll(menu,modificarProp);
        sp1.getChildren().add(paneLateral2);
        /*agrega al stackpane el scroll y el splitpane*/
        sp.getItems().addAll(sp1, scroll);
        sp.setDividerPositions(0.3f, 0.6f, 0.9f);
        /*se añade el splitpane al grupo y el label de bienvenida*/
        root.getChildren().add(sp);
        Scene scene = new Scene(root, ancho, altura,gp);
        
        /*Define las propiedades de la escena*/ 
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Modificar perfil Usuario");
        primaryStage.setScene(scene);
        /*---------------------fin interfaz-----------------------------------*/
        
        /*Evento al presionar el texto modificar propiedad*/
        modificarProp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
               VentanaPropiedad ventanaP = new VentanaPropiedad();
                try {
                    ventanaP.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(VentanaCuartos.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
        /*Evento al presionar el texto menu principal*/
        menu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                 VentanaCuartos busqueda = new VentanaCuartos();
                try {
                    busqueda.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(VentanaCuartos.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });

        /*evento al pulsar el boton de iniciar sesion */
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
                    modificarUsuario(nombre, telefono, correo, contraseña,matricula,idUsuario);
                    /*se abre la nueva ventana*/
                    if(datos){
                       VentanaCuartos inicio = new VentanaCuartos();
                       inicio.start(primaryStage);
                    }
                } catch (NumberFormatException e) {
                    alertasUsuario();
                    System.out.println("error "+ e.getLocalizedMessage());
                }
            }
        });
        /*muestra la interfaz*/
        primaryStage.show();
    
    }
    /**
     * Método para mostrar alertas de datos incorrectos ingresados por el usuario
     */
    public void alertasUsuario(){
        alerta.setOpacity(1);
        //System.out.println("alertas usuario");
    }
    /**
     * Método para obtener los datos a modificar y se puedan cargar en el formulario
     * @param idUsuario 
     */
    public void btnObtenerDatosModificar(int idUsuario){
        RegistrarModificarUsuario rModUser = new RegistrarModificarUsuario();
        usuario=rModUser.cargarDatos(idUsuario);
        //System.out.println("btnObtenerDatosM"+usuario.getContraseña()+usuario.getCorreo()+usuario.getMatricula());
    }
    /**
     * Método para pasar los datos a modificar de un usuario
     * @param nombre
     * @param telefono
     * @param correo
     * @param contraseña
     * @param matricula
     * @param idUsuario 
     */
    public void modificarUsuario(String nombre, long telefono, String correo, String contraseña,String matricula,int idUsuario){
        //System.out.println("modificar usuario"+nombre+telefono+correo+contraseña+matricula+idUsuario);
        datos=rMU.verificarDatos(nombre, telefono, correo, matricula, contraseña);
        //System.out.println("datos modificar  usuario"+datos+ "---"+nombre+telefono+correo+matricula+contraseña);
        if(datos)
            rMU.solicitarModificacion(nombre, telefono, correo, matricula, contraseña,idUsuario);
        else
            alertasUsuario();
    }
    /**
     * Lee un archivo txt
     * @param direccion
     * @return 
     */
    public ArrayList<String>  leeArchivo(String direccion) {
        ArrayList<String> tmp = new ArrayList();
        try{
            BufferedReader bf =new BufferedReader(new FileReader(direccion));
            String temp="";
	    String bfRead;
	    while((bfRead=bf.readLine())!= null){
		temp=temp+bfRead;
                tmp.add(temp);
                temp="";
            }
            }catch(IOException e){
		System.out.println("no se encontro el archivo txt"+e.getLocalizedMessage());
	}
	return tmp;
    }
    
}
