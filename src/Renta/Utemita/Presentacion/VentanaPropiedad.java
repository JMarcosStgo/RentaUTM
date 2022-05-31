package Renta.Utemita.Presentacion;


import java.io.File;
import java.sql.Blob;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
/**
 * Ventana para el registro y modificación de una propiedad
 * @author Marcos
 * @version 1.0
 */
public class VentanaPropiedad extends Application{

    /**
     * @param args llama al metodo star principal de la ventana
     */
    
    Double ancho;
    Double altura;
    String usuario="";
    GridPane grid = new GridPane();
    ScrollPane scrollPane=new ScrollPane();
    Text dCuarto = new Text("Descripción del cuarto");
    TextField lCuarto = new TextField();
    Text dPrecio = new Text("Precio");
    TextField lPrecio = new TextField();
    Text dDisponibilidad = new Text("Disponibilidad");
    ChoiceBox cb = new ChoiceBox();
    Text dUbicacion = new Text("Ubicación");
    TextField lUbicacion = new TextField();
    Text dImagenes = new Text("Ingrese las imagenes");
    Paint blanco = Paint.valueOf("#ffffff");
    
    String descripcionCuarto;
    float precio;
    int disponibilidad;
    String ubicacion;
    String servicios;
    Blob imagenes;
    boolean codigo;
    
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*alto y ancho de la pantalla*/
        ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        altura=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        
        grid.setStyle("-fx-background-color:yellow");//colorde fondo  del grid
        grid.setPadding(new Insets(altura/10,0,0,0));
        //grid.setMaxSize(ancho,altura);
        grid.setMinHeight(altura-(altura/10));
        //ANCHO DEL SCROLL
        grid.setMinWidth(ancho);
        
        Label bienvenido=new Label("Bienvenido, usted a ingresado como"+usuario);
        //fondo del label de bienvenida
        bienvenido.setStyle("-fx-background-color: #ff3f5f;");
        bienvenido.setFont(new Font("Arial",28));
        bienvenido.setMinHeight(altura/10);
        bienvenido.setMinWidth(ancho);
        bienvenido.setAlignment(Pos.CENTER);
        
        dCuarto.setStyle("-fx-background-color: #ffffff;");
        dCuarto.setFont(new Font("Arial",28));
        
        dPrecio.setStyle("-fx-background-color: #ffffff;");
        dPrecio.setFont(new Font("Arial",28));
        
        dDisponibilidad.setStyle("-fx-background-color: #ffffff;");
        dDisponibilidad.setFont(new Font("Arial",28));
        cb.getItems().addAll("Disponible","No disponible");
        cb.setValue(cb.getItems().get(0));
        dUbicacion.setStyle("-fx-background-color: #ffffff;");
        dUbicacion.setFont(new Font("Arial",28));
        
        dImagenes.setStyle("-fx-background-color: #000000;");
        dImagenes.setFont(new Font("Arial",28));
        Button imagenesP=new Button("Seleccionar Imagenes");
        
        
        imagenesP.setOnAction(event -> {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
         // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);
            System.out.println("file"+imgFile.getAbsolutePath());
        // Mostar la imagen
        /*
        if (imgFile != null) {
            Image image = new Image("\""+imgFile.getAbsolutePath()+"\"");
            System.out.println("Ruta imagen"+image);
            // show the image
            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            
        }*/
        
    });
        /*grid donde se añade cada elemento del formulario*/
        //grid.add(bienvenido, 0, 1);
        grid.add(dCuarto, 0, 2);
        grid.add(lCuarto, 0, 3);
        grid.add(dPrecio,0,4);
        grid.add(lPrecio,0,5);
        grid.add(dDisponibilidad,0,6);
        grid.add(cb,0,7);
        grid.add(dUbicacion,0,8);
        grid.add(lUbicacion,0,9);
        grid.add(imagenesP,0,10);
        
        AnchorPane anchorPane=new AnchorPane();
        anchorPane.setMinHeight(altura);
        anchorPane.setMaxWidth(ancho);
        //anchorPane.setMaxSize(ancho,altura);
        anchorPane.setStyle("-fx-background-color: #000000;");//color de fondo
        anchorPane.setPadding(new Insets(50,0,0,0));
        /*Se coloca el grid dentro del pane*/
        anchorPane.getChildren().add(grid);
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
        Group root = new Group();
        SplitPane sp = new SplitPane();
        final StackPane sp1 = new StackPane();
        //fondo del pandel lateral
        sp1.setStyle("-fx-background-color: #123456;");
        //sp1.setMaxWidth(ancho/3);
        sp1.setDisable(true);//no permite que se ajuste el panel
        sp1.setPadding(new Insets(altura,0,0,0));
        sp1.setMaxSize(ancho/3,altura-(altura/10));
        /*agrega al stackpane el scroll y el splitpane*/
        sp.getItems().addAll(sp1, scroll);
        sp.setDividerPositions(0.3f, 0.6f, 0.9f);
        /*se añade el splitpane al grupo y el label de bienvenida*/
        root.getChildren().add(sp);
        root.getChildren().add(bienvenido);
        Scene scene = new Scene(root, ancho, altura);
        /*Define las propiedades de la escena*/ 
        //se configura el stage principal
        primaryStage.setTitle("Registro Propiedad");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
