package Renta.Utemita.Presentacion;


import Renta.Utemita.ReglasDeNegocio.RegistrarModificarPropiedad;
import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;
import javafx.animation.RotateTransition;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.util.Duration;

/**
 * Ventana para el registro y modificación de una propiedad
 * @author Marcos
 * @version 1.0
 */
public class VentanaPropiedad extends Application{
    RegistrarModificarPropiedad rMP = new RegistrarModificarPropiedad();
    /*alto y ancho de la pantalla*/
    double ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    double altura=java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    String usuario="";
    GridPane grid = new GridPane();
    GridPane gridToken = new GridPane();
    int banderaGrid=0;
    AnchorPane anchorPane=new AnchorPane();
    ScrollPane scrollPane=new ScrollPane();
    Pane pane=new Pane();
    Text tokenTitulo=new Text("Ingrese el token de la propiedad");
    TextField inputToken=new TextField();
    Button botonToken = new Button("Verificar");
    InnerShadow shadow = new InnerShadow(); 
    Paint paint=Paint.valueOf("blue");
        
    Text tituloForm = new Text("Registro propiedad");
    Text dCuarto = new Text("Descripción del cuarto");
    TextField lCuarto = new TextField();
    Text dServicios = new Text("Servicios");
    TextField lServicios = new TextField();
    Text dPrecio = new Text("Precio");
    TextField lPrecio = new TextField();
    Text dDisponibilidad = new Text("Disponibilidad");
    ChoiceBox cb = new ChoiceBox();
    Text dUbicacion = new Text("Ubicación (link Google Maps de la propiedad)");
    TextField lUbicacion = new TextField();
    Text dImagenes = new Text("Ingrese las imagenes");
    Paint blanco = Paint.valueOf("#ffffff");
    File imgFile;
    Text errorRegPropiedad = new Text("Error,no se ha podido ingreasar los datos, revise sus datos proporcionados");
    private Button ingresar=new Button("Ingresar datos");
    
    /*variables*/
    private String descripcionCuarto="";
    private float precio=0.0f;
    private String disponibilidad="";
    private String ubicacion="";
    private String servicios="";
    private String token=null;
    private ArrayList<String> imagenes=new ArrayList();
    private ArrayList<Blob> imagenesBlob=new ArrayList();
    private int id;
    private boolean codigo;
    private boolean datos;
    
    /**
     * @param args llama al metodo star principal de la ventana
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    /**
     * Lanza la interfaz
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
/*----------------------------------------interfaz--------------------------------------------------*/        
        /*elementos del formulario del token*/
        gridToken.setStyle("-fx-background-color:white");//colorde fondo  del grid
        gridToken.setPadding(new Insets(altura/5,0,0,0));
        //grid.setMaxSize(ancho,altura);
        gridToken.setMinHeight(altura-(altura/10));
        //ANCHO DEL SCROLL
        gridToken.setMinWidth(ancho);
        tokenTitulo.setStyle("-fx-background-color: #ffffff;");
        tokenTitulo.setFont(new Font("Arial",28));
        inputToken.setFont(new Font("Arial",28));
        inputToken.setMinWidth(600);
        inputToken.setMinHeight(50);
        shadow.setBlurType(BlurType.GAUSSIAN);  
        shadow.setColor(javafx.scene.paint.Color.web("#eaedf2"));  
        shadow.setHeight(25);  
        shadow.setRadius(12);  
        shadow.setWidth(20);  
        shadow.setChoke(0.9);
        Paint paint2 = Paint.valueOf("#2b6ff6");
        botonToken.setFont(new Font("Serif", 28));
        botonToken.setEffect(shadow);
        botonToken.setTextFill(blanco);
        botonToken.setBackground(Background.fill(paint2));
        /*grid del token*/
        gridToken.add(tokenTitulo,0,0);
        gridToken.add(inputToken,0,1);
        gridToken.add(botonToken,1,1);
        /*grid del formulario para registrar modificar propiedad*/
        grid.setStyle("-fx-background-color:white");//colorde fondo  del grid
        grid.setPadding(new Insets(altura/5,0,0,0));
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
        
        errorRegPropiedad.setStyle("-fx-background-color: #ffffff;");
        errorRegPropiedad.setFont(new Font("Arial",30));
        errorRegPropiedad.setTextAlignment(TextAlignment.CENTER);
        errorRegPropiedad.setOpacity(0);
                
        tituloForm.setStyle("-fx-background-color: #ffffff;");
        tituloForm.setFont(new Font("Arial",30));
        tituloForm.setStyle("-fx-padding-left:600px;");
        tituloForm.setTextAlignment(TextAlignment.CENTER);
        tituloForm.setStyle("-fx-font-weight: bold;");
        tituloForm.setFill(paint);
        
        dCuarto.setStyle("-fx-background-color: #ffffff;");
        dCuarto.setFont(new Font("Arial",28));
        lCuarto.setMinWidth(600);
        lCuarto.setMinHeight(50);
        dPrecio.setStyle("-fx-background-color: #ffffff;");
        dPrecio.setFont(new Font("Arial",28));
        lPrecio.setMaxWidth(100);
        lPrecio.setMaxHeight(50);
        dDisponibilidad.setStyle("-fx-background-color: #ffffff;");
        dDisponibilidad.setFont(new Font("Arial",28));
        cb.getItems().addAll("Disponible","No disponible");
        cb.setValue(cb.getItems().get(0));
        cb.setMaxWidth(100);
        cb.setMinHeight(30);
        cb.setStyle("-fx-font-weight: bold;");
        dUbicacion.setStyle("-fx-background-color: #ffffff;");
        dUbicacion.setFont(new Font("Arial",28));
        lUbicacion.setMinWidth(600);
        lUbicacion.setMinHeight(30);
        lServicios.setMinWidth(600);
        lServicios.setMinHeight(30);
        
        dImagenes.setStyle("-fx-background-color: #000000;");
        dImagenes.setFont(new Font("Arial",28));
        Button imagenesP=new Button("Seleccionar 3 Imagenes");
        imagenesP.setMinWidth(100);
        
        tituloForm.setX(ancho/4);
        pane.getChildren().add(tituloForm);
        pane.setMinHeight(50);
        
        /*Abre un pop Up para permitir seleccionar al usuario si desea agregar otra propiedad*/
        Button button = new Button("Click to open a Popup");
        GridPane grid3=new GridPane();
        TilePane tilePane = new TilePane();
        Label label = new Label("¿Desea agregar otra propiedad?");
        label.setStyle("-fx-background-color: #ffffff;");
        label.setFont(new Font("Arial",28));        
        label.setStyle("-fx-background-color: #ffffff;");
        label.setFont(new Font("Arial",28));
        Popup popup = new Popup();
        label.setStyle("-fx-background-color: #89dab9;");
        popup.getContent().add(label);
        Button cancelar = new Button("Cancelar");
        Button agregar = new Button("Agregar");
        grid3.add(cancelar, 0, 0);
        grid3.add(agregar, 1, 0);
        cancelar.setStyle("-fx-background-color: #2b6ff6;");
        cancelar.setFont(new Font("Arial",28));
        agregar.setStyle("-fx-background-color: #2b6ff6;");
        agregar.setFont(new Font("Arial",28));
        popup.getContent().add(grid3);
        //popup.getContent().add(agregar);
        popup.setX(ancho/2);
        popup.setY(altura/4);
        label.setMinWidth(altura/2);
        label.setMinHeight(ancho/4);
        /*grid donde se añade cada elemento del formulario*/
        grid.setHgap(10);
        grid.setVgap(12);
        grid.add(pane, 0,1);
        grid.add(dCuarto, 0, 2);
        grid.add(lCuarto, 0, 3);
        grid.add(dPrecio,0,4);
        grid.add(lPrecio,0,5);
        grid.add(dDisponibilidad,0,6);
        grid.add(cb,0,7);
        grid.add(dUbicacion,0,8);
        grid.add(lUbicacion,0,9);
        grid.add(dServicios,0,10);
        grid.add(lServicios,0,11);
        grid.add(imagenesP,0,12);
        grid.add(ingresar,0,13);
        grid.add(errorRegPropiedad,0,14);
        
        /*configuracion de la escena y los elementos que tendra*/
        anchorPane.setMinHeight(altura);
        anchorPane.setMaxWidth(ancho);
        //anchorPane.setMaxSize(ancho,altura);
        anchorPane.setStyle("-fx-background-color: #ffffff;");//color de fondo
        anchorPane.setPadding(new Insets(50,0,0,0));
        /*Se coloca el grid dentro del pane*/
        anchorPane.getChildren().add(gridToken);
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
        Stop[] stops = new Stop[] {
           new Stop(0, Color.PURPLE),
           new Stop(1, Color.BLUE)
        };
        LinearGradient gp = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops);
        sp1.setStyle("-fx-background-color: #0c50cf;");//fondo del lateral izquieerdo
        //sp1.setOpacity(1);
        //sp1.setMaxWidth(ancho/3);
        sp1.setDisable(true);//no permite que se ajuste el panel
        sp1.setPadding(new Insets(altura,0,0,0));
        sp1.setMaxSize(ancho/3,altura-(altura/10));
        
        /*agrega al stackpane el scroll y el splitpane*/
        sp.getItems().addAll(sp1, scroll);
        sp.setDividerPositions(0.3f, 0.6f, 0.9f);
        /*se añade el splitpane al grupo y el label de bienvenida*/
        sp.setMaxHeight(500);
        root.getChildren().add(sp);
        root.getChildren().add(bienvenido);
/*--------------------------------------fin interfaz--------------------------------------------------*/
      //Creating a hexagon 
      Polygon hexagon = new Polygon();        
      
      //Adding coordinates to the hexagon 
      hexagon.getPoints().addAll(new Double[]{        
         200.0, 50.0, 
         400.0, 50.0, 
         450.0, 150.0,          
         400.0, 250.0, 
         200.0, 250.0,                   
         150.0, 150.0, 
      }); 
      //Setting the fill color for the hexagon 
      hexagon.setFill(Color.CORAL); 
     
      //Creating a rotate transition    
      RotateTransition rotateTransition = new RotateTransition(); 
      
      //Setting the duration for the transition 
      rotateTransition.setDuration(Duration.millis(10000)); 
      
      //Setting the node for the transition 
      rotateTransition.setNode(hexagon);       
      
      //Setting the angle of the rotation 
      rotateTransition.setByAngle(360); 
      
      //Setting the cycle count for the transition 
      rotateTransition.setCycleCount(100); 
      
      //Setting auto reverse value to false 
      rotateTransition.setAutoReverse(false); 
      
      //Playing the animation 
      rotateTransition.play(); 
         
      //Creating a Group object   
      
      Group root2 = new Group(hexagon); 
      //root2.setLayoutX(0);
      //anchorPane.setLayoutY(1000);
      //gridToken.add(root2,1,5);
        
        ///////////////////----------ventana emergente----------------/////////////////////////
        Popup po = new Popup();
        po.setX(205);
        po.setY(304);
        po.setHeight(altura/2);
        po.getContent().addAll(new Circle(35, 45, 64, Color.RED));
        Label tituloVentanaE = new Label ("Desea agregar otra propiedad");
        Button agregarBtn = new Button("Agregar");
        HBox hb = new HBox(17);
        hb.setStyle("-fx-background-color: violet; -fx-padding: 13px;");
        Button continuar = new Button("Continuar");
        hb.getChildren().addAll(tituloVentanaE,agregarBtn, continuar);
        
        /*acciones a realizar si pulsa en agregar*/
        agregarBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent en) {
            /*se limpian los campos del formulario y las variables usadas*/
            lCuarto.setText(null);
            lServicios.setText(null);
            lPrecio.setText(null);
            lUbicacion.setText(null);
            lServicios.setText(null);
            imagenes.removeAll(imagenes);
            token=getRandomString();
            hb.setOpacity(0);
        }
        });
        /*acciones a realizar en continuar*/
        continuar.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent en) {
            //po.hide();
            MenuPrincipal1 menuP=new MenuPrincipal1();
            menuP.start(primaryStage);
           
        }});
        ///////////////////////fin ventana emergente/////////////////////////////////////
          /*evento para cuando seleecione la disponibilidad*/
        disponibilidad=(String) cb.getItems().get(0);
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
             disponibilidad=(String) cb.getItems().get((Integer)t1);
                System.out.println("disponibilidad " + disponibilidad);
            }
        });
        /*define la accion al pulsar sobre el boton verificar*/
        botonToken.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                token=inputToken.getText();
                ingresarCodigoPropiedad(token);
           }
        });
        /*Evento para leer imagenes*/
        imagenesP.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");
             // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            try{
            // Obtener la imagen seleccionada
            imgFile = fileChooser.showOpenDialog(null);
            System.out.println("file"+imgFile.getAbsolutePath());
            if(this.imagenes.size()<3){
                this.imagenes.add(imgFile.getAbsolutePath());
            }
            }catch(Exception e){
            System.out.println("Error no se ingresaron imagenes"+e.getLocalizedMessage());
            }
        });
        
      
        
        /*Evento para ingresar datos del formulario*/
        ingresar.setMaxWidth(100);
        ingresar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {
                try {
                    System.out.println("token al pulsar boton registro"+token);
                        //Propiedad temp=rMP.obtenerDatosPropiedad(token);
                        descripcionCuarto=lCuarto.getText();
                        precio= Float.valueOf(lPrecio.getText());
                        ubicacion=lUbicacion.getText();
                        servicios=lServicios.getText();
                        //token=inputToken.getText();
                        
                        registrarPropiedad(descripcionCuarto,precio,disponibilidad,ubicacion,servicios,imagenes,token,imagenesBlob);
                        descripcionCuarto=null;
                        precio=0.0f;
                        //disponibilidad=null;
                        ubicacion=null;
                        servicios=null;
                        token=null;
                        try {
                        if(banderaGrid==0){
                            grid.add(hb,0,15);
                            banderaGrid=1;
                        }else{
                            hb.setOpacity(1);
                        }
                        imagenes.removeAll(imagenes);
                        
                        } catch (Exception e) {
                            System.out.println("error grid y imagenes"+e.getLocalizedMessage());
                        }
                } catch (NumberFormatException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            
        });

        /*Define las propiedades de la escena*/ 
        Scene scene = new Scene(root, ancho, altura,gp);
        primaryStage.setTitle("Registro Propiedad");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    
    /**
     * Metodo para mostrar alertas si el casero no ingresar datos correctos en el
     * formulario de registro.
     */
    public void mostrarAlertas(){
        System.out.println("estado de datos: "+codigo+"error al ingresar datos a la propiedad");
        errorRegPropiedad.setOpacity(1);
    }
    public  String getRandomString() 
    {   int i=10;
        String theAlphaNumericS;
        StringBuilder builder;
        
        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"; 

        //create the StringBuffer
        builder = new StringBuilder(i); 

        for (int m = 0; m < i; m++) { 

            // generate numeric
            int myindex 
                = (int)(theAlphaNumericS.length() 
                        * Math.random()); 

            // add the characters
            builder.append(theAlphaNumericS 
                        .charAt(myindex)); 
        }
        return builder.toString();
    }
    
    /**
     * Método para verificar la existencia de una propiedad, si existe el token,
     * se muestra el formulario con los datos precargados, en caso contrario se 
     * muestra un formulario con los campos vacios.
     * @param token  
     */    
    public void ingresarCodigoPropiedad(String token){
        this.codigo=rMP.btnExisteCodigoPropiedad(token);
        System.out.println("token ingresarPropiedad"+token);
        System.out.println("codigo"+codigo);
        /*si codigo es falso se muestra el formulario en limpio*/
        if(codigo==false){
            anchorPane.getChildren().add(grid);
            System.out.println("token generado"+this.token);
        }/*se carga los datos en el formulario*/
        else{
            /*codigo==true se obtiene los datos de la base de datos*/
            Propiedad temp=rMP.obtenerDatosPropiedad(token);
            System.out.println("temp"+temp.getDescripcionCuarto()+temp.getDisponibilidad()+temp.getServicios()+temp.getToken());
            id=temp.getIdPropiedad();
            lCuarto.setText(temp.getDescripcionCuarto());
            String x=temp.getPrecio()+"";
            lPrecio.setText(x);
            lUbicacion.setText(temp.getUbicacion());
            lServicios.setText(temp.getServicios());
            imagenesBlob.add(temp.getImagenesBlob().get(0));
            imagenesBlob.add(temp.getImagenesBlob().get(1));
            imagenesBlob.add(temp.getImagenesBlob().get(2));
            imagenes.add(" ");
            imagenes.add(" ");
            imagenes.add(" ");
            token=temp.getToken();
            System.out.println("token ingresar prop"+this.token);
            anchorPane.getChildren().add(grid);
        }
    }
    /**
     * Método para dar de alta una propiedad
     * @param descripcionCuarto
     * @param precio
     * @param disponibilidad
     * @param ubicacion
     * @param servicios
     * @param imagenes 
     * @param token 
     * @param imagenesBlob 
     */
    public void registrarPropiedad(String descripcionCuarto,float precio,String disponibilidad,String ubicacion,String servicios,ArrayList <String> imagenes,String token,ArrayList <Blob> imagenesBlob){
        System.out.println("registro propiedad ->>>>>"+descripcionCuarto+"--"+precio+"--"+disponibilidad+"--"+ubicacion+"--"+servicios+"--"+imagenes.size()+"--token: "+token+"--"+imagenesBlob.size());
        System.out.println("token registro propiedad"+this.token);
        
        System.out.println("token registrarPropiedad"+token);
        System.out.println("codigoregistrarPropiedad"+codigo);
        
        RegistrarModificarPropiedad rMP2 = new RegistrarModificarPropiedad();
        this.datos=rMP2.verificarDatos(descripcionCuarto,precio,disponibilidad,ubicacion,imagenes,servicios);
            
        System.out.println("datos regprop"+datos);
        if(datos==true)
        {       RegistrarModificarPropiedad rMP = new RegistrarModificarPropiedad();
                Propiedad temp=new Propiedad();
            this.codigo=rMP.btnExisteCodigoPropiedad(token);
            if(this.codigo==false){
                token=getRandomString();
                rMP = new RegistrarModificarPropiedad();
                temp=new Propiedad(descripcionCuarto,precio,disponibilidad,ubicacion,servicios,imagenes,token,null,0);
                rMP.ingresarPropiedad(temp);
                this.descripcionCuarto=null;
                this.precio=0.0f;
                this.disponibilidad=null;
                this.ubicacion=null;
                this.servicios=null;
                this.token=null;
                this.imagenes.removeAll(imagenes);
                errorRegPropiedad.setOpacity(0);
            }
            else{
                token=inputToken.getText();
                Propiedad temp2=new Propiedad(descripcionCuarto,precio,disponibilidad,ubicacion,servicios,imagenes,token,imagenesBlob,id);
                System.out.println("modificar propieadad"+temp2.getDescripcionCuarto()+"--"+temp2.getPrecio()+"--"+temp2.getDisponibilidad()+"--"+temp2.getUbicacion()+"--"+temp2.getServicios()+"--"+imagenes.size()+"--token: "+inputToken.getText()+"--"+imagenesBlob.size()+"id"+temp2.getIdPropiedad());
                rMP.modificarPropiedad(temp2);
                this.token=null;
                id=0;
            }
        }
        else{
            mostrarAlertas();
        }
    }
}
