package Renta.Utemita.Presentacion.VentanaRegistroModificacion;

import Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario.RegistrarModificarUsuario;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario.Usuario;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
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
    Paint paint=Paint.valueOf("blue");
    Text tituloForm = new Text("Registro Usuario");
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
    private Button ingresar=new Button("Registrarse");
    Text alerta = new Text("Error, ingresar datos correctos");
    
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
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
    }
    public void alertasUsuario(){
        alerta.setOpacity(1);
        System.out.println("alertas usuario");
    }
    public void btnObtenerDatosModificar(int idUsuario){
        RegistrarModificarUsuario rModUser = new RegistrarModificarUsuario();
        Usuario temp=rModUser.cargarDatos(idUsuario);
        
    }
    
}
