package Renta.Utemita.Presentacion.VentanaCuartos;

import Renta.Utemita.ReglasDeNegocio.RegistrarModificarPropiedad.Propiedad;
import Renta.Utemita.Presentacion.VentanaPropiedad.VentanaPropiedad;
import Renta.Utemita.Presentacion.VentanaRegistroModificacion.VentanaModificacion;
import Renta.Utemita.ReglasDeNegocio.Busqueda.Busqueda;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarPropiedad.RegistrarModificarPropiedad;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Ventana para la busqueda de cuartos 
 * @author Marcos
 * @version 1.0
 */
public class VentanaCuartos extends Application {
    double ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    double altura = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    InnerShadow shadow = new InnerShadow();
    Label usuarioBienvenida = new Label();
    GridPane grid = new GridPane();
    Label bienvenido=new Label("Bienvenido a");
    Label bienvenidopt2=new Label("Renta Utemita");
    //Button btnModificar = new Button();
    //Button btnModificarUsuario = new Button();
    Button b2= new Button("apartar");
    Paint blanco = Paint.valueOf("#ffffff");
    Label bienvenidopt3;
    AnchorPane anchorPane=new AnchorPane();
    int bandera=0;
    int banderafist=0;
    /*variables*/
    private int idUsuario = 0;
    private int idCuarto;
    private int precioFinal=0;
    private int precioInicial=0;
    ArrayList<Propiedad> propiedades = new ArrayList();
    ArrayList<Propiedad> propiedadesCpy = new ArrayList();
    
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
            ArrayList<String>  ident=leeArchivo("..\\RentaUTM\\src\\Imagenes\\id.txt");
            System.out.println("ident"+ident);
            idUsuario=Integer.parseInt(ident.get(0));
            usuarioBienvenida.setText(ident.get(1));
            bienvenidopt3=new Label("Usted a ingresado como ");
    
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
        bienvenido.setStyle("-fx-background-color: #2962ff;");
        bienvenido.setFont(new Font("Arial",20));
        bienvenidopt2.setFont(new Font("Arial",28));
        bienvenidopt3.setFont(new Font("Arial",20));
        usuarioBienvenida.setTextFill(blanco);
        bienvenidopt2.setTextFill(blanco);
        usuarioBienvenida.setFont(new Font("Arial",28));
        usuarioBienvenida.setAlignment(Pos.TOP_RIGHT);
        bienvenido.setMinHeight(altura/15);
        bienvenido.setMinWidth(ancho);
        bienvenido.setAlignment(Pos.TOP_LEFT);
        bienvenido.setPadding(new Insets(0,0,0,350));
        bienvenidopt2.setAlignment(Pos.CENTER);
        bienvenidopt2.setLayoutX(500);
        bienvenidopt3.setLayoutX(1000);
        usuarioBienvenida.setLayoutX(ancho-250);
       
        AnchorPane tituloBienvenidad = new AnchorPane();
        tituloBienvenidad.getChildren().add(bienvenido);
        tituloBienvenidad.getChildren().add(bienvenidopt2);
        tituloBienvenidad.getChildren().add(bienvenidopt3);
        tituloBienvenidad.getChildren().add(usuarioBienvenida);
        tituloBienvenidad.setMaxWidth(ancho);
        /*grid del formulario para registrar modificar propiedad*/
        grid.setStyle("-fx-background-color:white");//colorde fondo  del grid
        grid.setPadding(new Insets(100,0,altura-(altura/3),0));
        grid.setMinHeight(altura);
        //ANCHO DEL SCROLL
        grid.setMinWidth(ancho-(ancho/3));
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
        //scroll.setStyle("-fx-background-color: black;");//fondo del lateral izquieerdo
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
        sp1.setMaxSize(ancho/6,altura);
        sp1.setPadding(new Insets(200,0,00,0));
        Text modificarProp = new Text("Modificar Propiedad"); 
        //Setting the font of the text 
        modificarProp.setFont(Font.font(null, FontWeight.BOLD, 15));     
        //Setting the color of the text 
        modificarProp.setFill(Color.CRIMSON); 
        //setting the position of the text 
        modificarProp.setX(20); 
        modificarProp.setY(00);       
        Text salir = new Text("Cerrar sesión"); 
        //Setting the font of the text 
        salir.setFont(Font.font(null, FontWeight.BOLD, 15));     
        //Setting the color of the text 
        salir.setFill(Color.CRIMSON); 
        //setting the position of the text 
        salir.setX(20); 
        salir.setY(400);       
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
        /*se determina si se muestra modificar propiedad o no*/
        if(usuarioBienvenida.getText().equals("Estudiante"))
            paneLateral2.getChildren().addAll(modificarPer,salir);
        else
            paneLateral2.getChildren().addAll(modificarPer,modificarProp,salir);
        sp1.getChildren().add(paneLateral2);
        //panel de la derecha
        final StackPane panelDerecho = new StackPane();
        panelDerecho.setStyle("-fx-background-color: #00ff77;");
        panelDerecho.setMaxSize(650,altura);
        panelDerecho.setDisable(false);
        panelDerecho.setAlignment(Pos.CENTER_LEFT);
/******corrijiendo*/
        TextField buscador = new TextField();
        //buscador.setMinSize(20,50);
        buscador.setPromptText("Search Box");
        
        //buscador.setPadding(new Insets(0,300,0,0));
        ComboBox comboBox = new ComboBox();
        buscador.textProperty().addListener((ov, t, t1) -> {
        });
        ListView<Object> itemView = new ListView<>();
        itemView.setItems(comboBox.getItems());
        VBox boxDerecho = new VBox(buscador,itemView);
        boxDerecho.setLayoutX(0);
        //itemView.setPrefSize(200,900);
        Group paneLateralDer = new Group();
        paneLateralDer.getChildren().addAll(boxDerecho);
        paneLateralDer.setLayoutX(0);
        
        //paneLateralDer.prefWidth(300);
        //paneLateralDer.prefHeight(altura);
        //boxDerecho.setPadding(new Insets(0,300,0,0));
        panelDerecho.getChildren().add(paneLateralDer);
        //panelDerecho.snapPositionX(0);
/******corrijiendo*/
        /*agrega al stackpane el scroll y el splitpane*/
        sp.getItems().addAll(sp1, scroll,panelDerecho);
        sp.setDividerPositions(0.3f, 0.6f, 0.9f);
        /*se añade el splitpane al grupo y el label de bienvenida*/
        root.getChildren().add(sp);
        root.getChildren().add(tituloBienvenidad);
        
        /*configuracion de la escena*/
        GridPane gridTitulo = new GridPane();
        root.getChildren().add(gridTitulo);
        primaryStage.setResizable(false);
        
        primaryStage.setFullScreen(true);
        Scene scene = new Scene(root, ancho, altura,gp);
        primaryStage.setTitle("Busqueda de cuartos");
        primaryStage.setScene(scene);
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
        //propiedades=buscarCuartos(precioInicial,precioFinal);
        /*escucha para bloquear letras y solo aceptar 5 numeros*/
        searchBox.textProperty().addListener((ov, t, t1) -> {
           // System.out.println("cambio busqueda"+ov);
             //Se asigna al valor anterior
            if(!t1.matches("[0-9]{0,5}") || t1.length()>8){
                ((StringProperty) ov).setValue(t);
            }else{
                //Se asigna el nuevo valor, porque sí coincide con la expresión
                ((StringProperty)ov).setValue(t1);
                try {
                    if(ov.getValue().equals("") || ov.getValue().equals(" "))
                        precioInicial=0;
                    else
                        precioInicial=Integer.parseInt(ov.getValue());
                }catch (NumberFormatException e) {
                    System.out.println("error"+e.getLocalizedMessage());
                }
                //fila=new GridPane();
                
                //fila.getChildren().clear();
                propiedades.removeAll(propiedades);
                propiedades=buscarCuartos(precioInicial,precioFinal);
                for (int i = 0; i <propiedades.size(); i++) {
                    propiedadesCpy.add(propiedades.get(i));
                }
                //propiedadesCpy.addAll(propiedades);
                ver();
               // eliminaDibujo();
                
        //Node node = fila.getChildren().get(0);
        //fila.getChildren().clear();
        //fila.getChildren().add(0,node);
              //  System.out.println("cambio .."+ov + precioInicial);
              //  System.out.println("tamaño de propiedades"+propiedades.size());
            }
        });
        searchBoxF.textProperty().addListener((ov, t, t1) -> {
            //System.out.println("cambio busqueda"+ov);
             //Se asigna al valor anterior
            if(!t1.matches("[0-9]{0,5}") || t1.length()>8){
                ((StringProperty) ov).setValue(t);
            }else{
                //Se asigna el nuevo valor, porque sí coincide con la expresión
                ((StringProperty)ov).setValue(t1);
                try {
                    if(ov.getValue().equals("") || ov.getValue().equals(" ") )
                        precioFinal=0;
                   else
                        precioFinal=Integer.parseInt(ov.getValue());
                }catch (NumberFormatException e) {
                    System.out.println("error"+e.getLocalizedMessage());
                }
               // System.out.println("cambio .."+ov + precioInicial);
                //fila=new GridPane();
                
               // fila.getChildren().clear();
                propiedades.removeAll(propiedades);
                propiedades=buscarCuartos(precioInicial,precioFinal);
                for (int i = 0; i <propiedades.size(); i++) {
                    propiedadesCpy.add(propiedades.get(i));
                    //System.out.println("prop evento"+propiedades.get(i).getDescripcionCuarto()+propiedades.get(i).getIdPropiedad()+propiedades.get(i).getToken());
                }
                
                ver();
                //eliminaDibujo();
                //System.out.println("tamaño de propiedades"+propiedades);
                
            }
        });
        
        //campos de busqueda
        VBox box = new VBox(searchBox);
        VBox box2 = new VBox(searchBoxF);
        grid.add(box,0,0);
        grid.add(box2,1,0);
        
        /*prubea ver*/
        //propiedades=buscarCuartos(1,50000);
        //ver();
        //se obtiene todas las propiedades dentro del rango de fechas
        //el array me devuelve todos las imagenes
        
        //RegistrarModificarPropiedad rMP = new RegistrarModificarPropiedad();
        //Propiedad prop=rMP.obtenerDatosPropiedad("9999998");
        //propiedades.add(prop);
        
    /*********************campo de busquedad***************/    
/*-------------------------------------------------------------------------------------------------*/
        /*Evento para cerrar sesión*/
        salir.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                try {
                    primaryStage.close();
                } catch (Exception e) {
                }
            }
        });
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
        /*Evento al presionar el texto modificar perfil*/
        modificarPer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                VentanaModificacion ventanaModificacionPerfil = new VentanaModificacion();
                try {
                    ventanaModificacionPerfil.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(VentanaCuartos.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
        /*muestra interfaz*/
        primaryStage.show();
    }

    public void idUsuarioOnline(int idUsuario){
        this.idUsuario=idUsuario;
    }
    
    public ArrayList<String> leeArchivo(String direccion) {
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
    /**
     * Método para obtener los cuartos dentro del rango ingresado por el usuario
     * @param precioInicial
     * @param precioFinal
     * @return un arreglo de propiedades
     */
    public ArrayList<Propiedad> buscarCuartos(int precioInicial, int precioFinal){
        Busqueda busqueda = new Busqueda();
        return busqueda.obtenerCuartos(precioInicial,precioFinal);
    }
    /**
     * Método para mostrar la informacion de una propiedad
     * @param idPropiedad 
     */
    public void btnObtnerInformacion(int idPropiedad){
        /*cargar ventana emergente con informacion de la propiedad*/
    }
    public void ver(){
         ImageView image11,image2,image3;
         byte byteImage[] = null;
         double posY=(ancho/6)+600;
         double posX=ancho/6;
         System.out.println("entra ve -------------------------------------------"+posY+posX);    
         ArrayList<ImageView>imagenesBuscador = new ArrayList();
         GridPane fila=new GridPane();
          /**lista todas las propiedades*/
         fila.setMinWidth(ancho-(ancho/3));
         fila.setBackground(Background.fill(blanco));
         fila.setPadding(new Insets(0,0,0,0));
         fila.setLayoutY(altura/3);
         fila.setVgap(12);
         
         bandera=propiedades.size();
        fila.getChildren().clear();
        if(propiedades.size()>0){
            /*cargar todas las imagens*/
            for (int j = 0; j < propiedades.size(); j++) {
               try {
                   Blob blo1=propiedades.get(j).getImagenesBlob().get(0);
                   byteImage=blo1.getBytes(1,(int)blo1.length());
                   Image img1 = new Image(new ByteArrayInputStream(byteImage));
                   ImageView image1 = new ImageView(img1);
                   imagenesBuscador.add(image1);
               } catch (SQLException e) {
                   System.out.println("Error, no se pudieron cargar las imagenes, "+e.getLocalizedMessage());
               }
           }

           for (int i = 0; i <imagenesBuscador.size(); i++) {
                System.out.println("i "+i);
                image11=imagenesBuscador.get(i);
                image11.setFitHeight(posX+200);
                image11.setFitWidth(posX+200);
                image11.setX(100);
                image11.setY(posY);
                Button btnInformacion= new Button("Apartar");
                btnInformacion.setId(""+i);
                btnInformacion.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        
                        Stage inicio = new Stage();
                        Group elementosVenEmer= new Group();
                        
                        
                        Popup po = new Popup();
                        po.setX(1020);
                        po.setY(704);
                        po.setHeight(altura/2);
                        po.getContent().addAll(new Circle(35, 45, 64, Color.RED));
                        Label tituloVentanaE = new Label ("Desea agregar otra propiedad");
                        Button agregarBtn = new Button("Apartar");
                        HBox hb = new HBox(17);
                        hb.setStyle("-fx-background-color: violet; -fx-padding: 13px;");
                        Button continuar = new Button("Cancelar");
                        hb.getChildren().addAll(tituloVentanaE,agregarBtn, continuar);
                        elementosVenEmer.getChildren().add(hb);
                        //anchorPane.getChildren().add(elementosVenEmer);
                        /*obtener la informacion de la propiedad elejida*/
                        RegistrarModificarPropiedad rModiProp = new RegistrarModificarPropiedad();
                        String id=btnInformacion.getId();
                        int pos=Integer.parseInt(id);
                        
                        System.out.println("get id "+id +"--"+pos+" tam propiedadesCPY"+propiedadesCpy.size());
                        Propiedad eleccionPropiedad = propiedadesCpy.get(pos);
                        //Propiedad eleccionPropiedad =rModiProp.obtenerDatosPropiedad(tokenn);
                        System.out.println("informacion de propiedad elejida" + eleccionPropiedad.getDescripcionCuarto() +" - "+ eleccionPropiedad.getToken()+ " - " +eleccionPropiedad.getIdPropiedad());
                        Propiedad elejida = new Propiedad();
                        
                        Scene emergente = new Scene(elementosVenEmer,600,400);
                        inicio.setScene(emergente);
                        inicio.setTitle("Información de cuartos");
                        inicio.show();
                        

                    }
                });
                 /*aumenta en y*/
                posY+=posY;
                fila.add(image11, 0, i);
                fila.add(btnInformacion, 1, i);
            }
            anchorPane.getChildren().add(fila);
            
            imagenesBuscador.removeAll(propiedades);
        }else{
               /*limpiar grid de las imagenes antiguas*/
                System.out.println("entra seccion borrar");
                Node nodo1 = anchorPane.getChildren().get(0);
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(0,nodo1);
        }
        bandera=1;
    }
}
