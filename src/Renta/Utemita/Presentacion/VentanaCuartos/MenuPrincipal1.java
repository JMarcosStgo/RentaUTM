package Renta.Utemita.Presentacion.VentanaCuartos;

import Renta.Utemita.ReglasDeNegocio.Propiedad;
import Renta.Utemita.Presentacion.VentanaPropiedad.VentanaPropiedad;
import Renta.Utemita.Presentacion.VentanaRegistroModificacion.VentanaModificacion;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarPropiedad;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    String usuarioBienvenida =" -user-";
    GridPane grid = new GridPane();
    Label bienvenido=new Label("Bienvenido a");
    Label bienvenidopt2=new Label("Renta Utemita");
    Label bienvenidopt3=new Label("Usted a ingresado como "+usuarioBienvenida);
    //Button btnModificar = new Button();
    //Button btnModificarUsuario = new Button();
    Button b1= new Button("ver info");
    Button b2= new Button("apartar");
    Paint blanco = Paint.valueOf("#ffffff");
     
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
        /*bienvenida*/

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
        bienvenido.setFont(new Font("Arial",20));
        bienvenidopt2.setFont(new Font("Arial",28));
        bienvenidopt3.setFont(new Font("Arial",24));
        
        bienvenido.setMinHeight(altura/15);
        bienvenido.setMinWidth(ancho);
        bienvenido.setLayoutX(0);
        bienvenidopt2.setLayoutX(500);;
        bienvenidopt3.setLayoutX(1000);
       
        AnchorPane tituloBienvenidad = new AnchorPane();
        tituloBienvenidad.getChildren().add(bienvenido);
        tituloBienvenidad.getChildren().add(bienvenidopt2);
        tituloBienvenidad.getChildren().add(bienvenidopt3);
        tituloBienvenidad.setMaxWidth(ancho);
        /*grid del formulario para registrar modificar propiedad*/
        grid.setStyle("-fx-background-color:white");//colorde fondo  del grid
        grid.setPadding(new Insets(100,0,altura-(altura/3),0));
        grid.setMinHeight(altura);
        //ANCHO DEL SCROLL
        grid.setMinWidth(ancho-(ancho/5));
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
        anchorPane.setMaxWidth(ancho/2);
        //anchorPane.setMaxSize(ancho,altura);
        //anchorPane.setStyle("-fx-background-color: #ffffff;");//color de fondo
        anchorPane.setPadding(new Insets(50,0,0,0));
        /*Se coloca el grid dentro del pane*/
        /*Scroll del formulario*/
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(ancho, altura);
        /*Se asigna contenido al scrol*/
        scroll.setContent(anchorPane);
        //scroll.setStyle("-fx-background-color: #000000;");
        /*activar o desactivar barras laterales*/
        scroll.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        //se crea un Group para colocar dentro al splipane 
        SplitPane sp = new SplitPane();
        anchorPane.getChildren().add(grid);
        sp1.setStyle("-fx-background-color: #00ff77;");//fondo del lateral izquieerdo
        sp1.setDisable(false);//no permite que se ajuste el panel
        sp1.setMaxSize(ancho/5,altura);
        
        sp1.setPadding(new Insets(200,0,00,0));
      Text modificarProp = new Text("Modificar Propiedad"); 
      //Setting the font of the text 
      modificarProp.setFont(Font.font(null, FontWeight.BOLD, 15));     
      //Setting the color of the text 
      modificarProp.setFill(Color.CRIMSON); 
      //setting the position of the text 
      modificarProp.setX(20); 
      modificarProp.setY(00);       
       
      
      //Creating a text 
      Text modificarPer = new Text("Modificar Perfil"); 
      //Setting the font of the text 
      modificarPer.setFont(Font.font(null, FontWeight.BOLD, 15));     
      //Setting the color of the text 
      modificarPer.setFill(Color.CRIMSON); 
      //setting the position of the text 
      modificarPer.setX(20); 
      modificarPer.setY(200);       
      Group paneLateral2 = new Group();
      paneLateral2.getChildren().addAll(modificarPer,modificarProp);
      
      
      sp1.setPrefSize(400, 600);
      sp1.getChildren().add(paneLateral2);
        /*agrega al stackpane el scroll y el splitpane*/
        sp.getItems().addAll(sp1, scroll);
        sp.setDividerPositions(0.3f, 0.6f, 0.9f);
        /*se añade el splitpane al grupo y el label de bienvenida*/
        root.getChildren().add(sp);
        root.getChildren().add(tituloBienvenidad);
        
/*----------------------fin configuracion de la pantalla-----------------------------------------*/
        /* chat
        TextField searchBox = new TextField();
        boolean clickSelection = false;
        searchBox.setPromptText("Search Box");
        ComboBox comboBox = new ComboBox();
        searchBox.textProperty().addListener((ov, t, t1) -> {
            
        });
        
        ListView<Object> itemView = new ListView<>();
        itemView.setItems(comboBox.getItems());
        VBox box = new VBox(searchBox,itemView);
        grid.addRow(2, box);
        */
    /*********************campo de busquedad***************/
        TextField searchBox = new TextField();
        TextField searchBoxF = new TextField();
        searchBox.setPromptText("Precio Inicial");
        searchBox.setFont(new Font("arial",28));
        searchBox.setAlignment(Pos.TOP_LEFT);
        searchBox.setMinWidth(ancho/5);
        searchBox.setMinHeight(50);
        searchBoxF.setPromptText("Precio Final");
        searchBoxF.setFont(new Font("arial",28));
        searchBoxF.setAlignment(Pos.TOP_LEFT);
        searchBoxF.setMinWidth(ancho/5);
        searchBoxF.setMinHeight(50);
        grid.setHgap(10);
        //grid.setVgap(12);
        /*escucha para bloquear letras y solo aceptar 5 numeros*/
        searchBox.textProperty().addListener((ov, t, t1) -> {
            System.out.println("cambio busqueda"+ov);
             //Se asigna al valor anterior
            if(!t1.matches("[0-9]{0,5}") || t1.length()>8){
                ((StringProperty) ov).setValue(t);
            }else{
                //Se asigna el nuevo valor, porque sí coincide con la expresión
                ((StringProperty)ov).setValue(t1);
            }
        });
        searchBoxF.textProperty().addListener((ov, t, t1) -> {
            System.out.println("cambio busqueda"+ov);
             //Se asigna al valor anterior
            if(!t1.matches("[0-9]{0,5}") || t1.length()>8){
                ((StringProperty) ov).setValue(t);
            }else{
                //Se asigna el nuevo valor, porque sí coincide con la expresión
                ((StringProperty)ov).setValue(t1);
            }
        });
        
        VBox box = new VBox(searchBox);
        VBox box2 = new VBox(searchBoxF);
        grid.add(box,0,0);
        grid.add(box2,1,0);
        
        
        //se obtiene todas las propiedades dentro del rango de fechas
        //el array me devuelve todos las imagenes
        ArrayList<Propiedad> propiedades = new ArrayList();
        RegistrarModificarPropiedad rMP = new RegistrarModificarPropiedad();
        Propiedad prop=rMP.obtenerDatosPropiedad("9999998");
        propiedades.add(prop);
        ImageView image1,image2,image3;
         byte byteImage[] = null;
        for (int i = 0; i <propiedades.size() ; i++) {
            try {
                Blob blo1=propiedades.get(i).getImagenesBlob().get(0);
                byteImage=blo1.getBytes(1,(int)blo1.length());
                Image img1 = new Image(new ByteArrayInputStream(byteImage));
                image1 = new ImageView(img1);
                
                Blob blo2=propiedades.get(i).getImagenesBlob().get(1);
                byteImage=blo2.getBytes(1,(int)blo2.length());
                Image img2 = new Image(new ByteArrayInputStream(byteImage));
                image2 = new ImageView(img2);
                
                Blob blo3=propiedades.get(i).getImagenesBlob().get(2);
                byteImage=blo3.getBytes(1,(int)blo3.length());
                Image img3 = new Image(new ByteArrayInputStream(byteImage));
                image3 = new ImageView(img3);
                
                image1.setFitHeight(ancho/5);
                image1.setFitWidth(ancho/5);
                image2.setFitHeight(ancho/5);
                image2.setFitWidth(ancho/5);
                image3.setFitHeight(ancho/5);
                image3.setFitWidth(ancho/5);
                image1.setX(100);
                image1.setY(altura/30);
                image2.setX(450);
                image2.setY(altura/30);
                image3.setX(800);
                image3.setY(altura/30);
                
                Group group1=new Group();
                b1.setLayoutY(350);
                b1.setLayoutX(150);
                b2.setLayoutY(350);
                b2.setLayoutX(300);
                
                group1.getChildren().add(image1);
                group1.getChildren().add(b1);
                group1.getChildren().add(b2);
                
                Pane pane = new Pane();
                pane.setMinWidth(ancho-(ancho/5));
                pane.getChildren().add(group1);
                pane.getChildren().add(image2);
                pane.getChildren().add(image3);
                pane.setLayoutY(altura/3);
                pane.setStyle("-fx-background-color: white;");//fondo del pane de las imagenes
                pane.setPadding(new Insets(0,0,0,0));
                //tres propiedades por fila
                anchorPane.getChildren().add(pane);
            } catch (SQLException e) {
                System.out.println("error al cargar las imagenes de la BD"+e.getLocalizedMessage());
            }
                

        }
    /*********************campo de busquedad***************/    
/*-------------------------------------------------------------------------------------------------*/
        /*Evento al presionar el texto modificar propiedad*/
        modificarProp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
               VentanaPropiedad ventanaP = new VentanaPropiedad();
                try {
                    ventanaP.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(MenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
        /*Evento al presionar el texto modificar perfil*/
        modificarPer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                VentanaModificacion ventanaModificacionPerfil = new VentanaModificacion();
                try {
                    ventanaModificacionPerfil.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(MenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });

        /*configuracion de la escena*/
        GridPane gridTitulo = new GridPane();
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
