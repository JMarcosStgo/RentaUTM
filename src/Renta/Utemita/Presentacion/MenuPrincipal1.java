package Renta.Utemita.Presentacion;

import Renta.Utemita.Presentacion.VentanaRegistroModificacion.VentanaModificacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Ventana para la busqueda de cuartos 
 * @author Marcos
 * @version 1.0
 */
public class MenuPrincipal1 extends Application {
    double ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    double altura = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    InnerShadow shadow = new InnerShadow();
    String usuarioBienvenida;
    GridPane grid = new GridPane();
    Label bienvenido=new Label("Bienvenido, usted a ingresado como"+usuarioBienvenida);
    AnchorPane anchorPane=new AnchorPane();
    
    /*variables*/
    private int idUsuario = 0;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * método para inciar los elementos de la interfaz 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setX(0);
        primaryStage.setY(0);
        /*lectura del archivo*/
        try {
            String ident=leeArchivo("..\\RentaUTM\\src\\Imagenes\\id.txt");
            System.out.println("ident"+ident);
            idUsuario=Integer.parseInt(ident);
        } catch (NumberFormatException e) {
            System.out.println("error conversion al leer archivo"+e.getLocalizedMessage());
        }
/*----------------------configuracion de la pantalla-----------------------------------------*/
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
        //ANCHO DEL SCROLL
        grid.setMinWidth(ancho);
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
/*----------------------fin configuracion de la pantalla-----------------------------------------*/
        Button btnModificar = new Button();
        btnModificar.setText("Modificar propiedad'");
        btnModificar.setOnAction(new EventHandler<ActionEvent>() {
                
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                VentanaPropiedad ventanaP = new VentanaPropiedad();
                try {
                    ventanaP.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(MenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Button btnModificarUsuario = new Button();
        btnModificarUsuario.setText("Modificar usuario'");
        btnModificarUsuario.setOnAction(new EventHandler<ActionEvent>() {
                
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                VentanaModificacion ventanaP = new VentanaModificacion();
                try {
                    ventanaP.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(MenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        /*configuracion de la escena*/
        GridPane gridTitulo = new GridPane();
        gridTitulo.add(btnModificar,0,0);
        gridTitulo.add(btnModificarUsuario,1,0);
        //root.getChildren().add(btnModificar);
        //btnModificarUsuario.setAlignment(Pos.CENTER);
        root.getChildren().add(gridTitulo);
        
        Scene scene = new Scene(root, ancho, altura);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void idUsuarioOnline(int idUsuario){
        this.idUsuario=idUsuario;
    }
    
    public String leeArchivo(String direccion) {
		String texto="";
		try{
			BufferedReader bf =new BufferedReader(new FileReader(direccion));
			String temp="";
			String bfRead;
			while((bfRead=bf.readLine())!= null){
				temp=temp+bfRead;
			}
			texto=temp;
		}catch(IOException e){
			System.out.println("no se encontro el archivo txt"+e.getLocalizedMessage());
		}
		return texto;
    }
}
