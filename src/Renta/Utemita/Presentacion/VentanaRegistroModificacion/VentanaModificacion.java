package Renta.Utemita.Presentacion.VentanaRegistroModificacion;

import Renta.Utemita.Presentacion.MenuPrincipal1;
import Renta.Utemita.ReglasDeNegocio.IniciarSesion;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
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
     
    
    
    
    private int idUsuario;
    private String nombre;
    private int telefono;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*cargar los datos*/
        try {
                btnObtenerDatosModificar(4);
                
                //textFNombre.setText(usuario.getNombre());
                textFNombre.textProperty().addListener((obs, oldText, newText) -> {
                    System.out.println("Text changed from "+oldText+" to "+newText.length());
                    textFNombre.setText(newText);
                    
                });
                
                txtFContraseña.setText(usuario.getContraseña());
                txtFCorreo.setText(usuario.getCorreo());
                txtFMatricula.setText(usuario.getMatricula());
                txtFTelefono.setText(usuario.getTelefono()+"");
                idUsuario=usuario.getIdUsuario();
        } catch (Exception e) {
            System.out.println("error btnObtener"+e.getLocalizedMessage());
        }
        

        /*-----------------------------------------------------------------------------------------*/
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
        //grid.setMaxSize(ancho,altura);
        grid.setMinHeight(altura-(altura/10));
        //ANCHO DEL SCROLL
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
        
        ingresar.setMinSize(200,50);
        ingresar.snapPositionX(200);
        ingresar.setEffect(shadow);
        ingresar.setTextFill(blanco);
        ingresar.setBackground(Background.fill(paint2));
        
        tituloForm.setX(200);
        pane.getChildren().add(tituloForm);
        pane.setMinHeight(50);
        /*grid donde se añade cada elemento del formulario*/
        grid.setHgap(10);
        grid.setVgap(12);
        grid.add(pane, 0,1);
        grid.add(txtNombre, 0, 2);
        grid.add(textFNombre, 0, 3);
        grid.add(txtMatricula,0,4);
        grid.add(txtFMatricula,0,5);
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
        root.getChildren().add(grid);
        
        /*configuracion de la escena y los elementos que tendra*/
        anchorPane.setMinHeight(altura);
        anchorPane.setMaxWidth(ancho);
        //anchorPane.setMaxSize(ancho,altura);
        anchorPane.setStyle("-fx-background-color: #ffffff;");//color de fondo
        anchorPane.setPadding(new Insets(50,0,0,0));
        /*Se coloca el grid dentro del pane*/
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
        sp1.setStyle("-fx-background-color: #0c50cf;");//fondo del lateral izquieerdo
        sp1.setDisable(true);//no permite que se ajuste el panel
        sp1.setPadding(new Insets(altura,0,0,0));
        sp1.setMaxSize(ancho/3,altura-(altura/10));
        /*agrega al stackpane el scroll y el splitpane*/
        sp.getItems().addAll(sp1, scroll);
        sp.setDividerPositions(0.3f, 0.6f, 0.9f);
        /*se añade el splitpane al grupo y el label de bienvenida*/
        root.getChildren().add(sp);
        root.getChildren().add(bienvenido);
        Scene scene = new Scene(root, ancho, altura,gp);
        /*--------------------------------------------------------------------------------------------*/
        
        /*evento al pulsar el boton */
        ingresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    nombre=textFNombre.getText();
                    correo=txtFCorreo.getText();
                    contraseña=txtFContraseña.getText();
                    String tel=txtFTelefono.getText();
                    telefono=Integer.parseInt(tel);
                    matricula=txtFMatricula.getText();
                    System.out.println("telefono"+telefono);
                    //datos=rMU.verificarDatos(nombre, telefono, correo, matricula, contraseña);
                    modificarUsuario(nombre, telefono, correo, contraseña,matricula,idUsuario);
                    /*se abre la nueva ventana*/
                    if(datos){
                       MenuPrincipal1 inicio = new MenuPrincipal1();
                       inicio.start(primaryStage);
                    }
                } catch (NumberFormatException e) {
                    alertasUsuario();
                    System.out.println("error "+ e.getLocalizedMessage());
                }

            }
        });
        
        
        /*Define las propiedades de la escena*/ 
        primaryStage.setTitle("Modificar perfil Usuario");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    
    }
    public void alertasUsuario(){
        alerta.setOpacity(1);
        System.out.println("alertas usuario");
    }
    public void btnObtenerDatosModificar(int idUsuario){
        RegistrarModificarUsuario rModUser = new RegistrarModificarUsuario();
        usuario=rModUser.cargarDatos(idUsuario);
        System.out.println("btnObtenerDatosM"+usuario.getContraseña()+usuario.getCorreo()+usuario.getMatricula());
    }
    public void modificarUsuario(String nombre, int telefono, String correo, String contraseña,String matricula,int idUsuario){
        System.out.println("modificar usuario"+nombre+telefono+correo+contraseña+matricula+idUsuario);
        datos=rMU.verificarDatos(nombre, telefono, correo, matricula, contraseña);
        System.out.println("datos modificar  usuario"+datos+ "---"+nombre+telefono+correo+matricula+contraseña);
        if(datos)
            rMU.solicitarModificacion(nombre, telefono, correo, matricula, contraseña,idUsuario);
            /*limpiar variables*/
            
        else
            alertasUsuario();
        
    }
    
}
