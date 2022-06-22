package Renta.Utemita.Presentacion.VentanaCuartos;

import Renta.Utemita.ReglasDeNegocio.RegistrarModificarPropiedad.Propiedad;
import Renta.Utemita.Presentacion.VentanaPropiedad.VentanaPropiedad;
import Renta.Utemita.Presentacion.VentanaRegistroModificacion.VentanaModificacion;
import Renta.Utemita.ReglasDeNegocio.ApartarCuarto.ApartarCuarto;
import Renta.Utemita.ReglasDeNegocio.ApartarCuarto.Notificaciones;
import Renta.Utemita.ReglasDeNegocio.Busqueda.Busqueda;
import Renta.Utemita.ReglasDeNegocio.IniciarSesion.IniciarSesion;
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
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
    //dimensiones de la ventana
    double ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    double altura = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    InnerShadow shadow = new InnerShadow();
    Label usuarioBienvenida = new Label();
    GridPane grid = new GridPane();
    Label bienvenido=new Label("Bienvenido a");
    Label bienvenidopt2=new Label("Renta Utemita");
    Button b2= new Button("apartar");
    Paint blanco = Paint.valueOf("#ffffff");
    Label bienvenidopt3;
    AnchorPane anchorPane=new AnchorPane();
    int banderaActual=0;
    int banderaAnterior=0;
    int banderafist=0;
    ArrayList<Integer> color = new ArrayList();
        
    /*variables*/
    private int idUsuario = 0;
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
        primaryStage.setX(0);
        primaryStage.setY(0);
        /*lectura del archivo*/
        try {
            ArrayList<String>  ident=leeArchivo("..\\RentaUTM\\src\\Imagenes\\id.txt");
            //System.out.println("ident"+ident);
            idUsuario=Integer.parseInt(ident.get(0));
            usuarioBienvenida.setText(ident.get(1));
            bienvenidopt3=new Label("Usted a ingresado como ");
    
        } catch (NumberFormatException e) {
            System.out.println("error conversion al leer archivo"+e.getLocalizedMessage());
        }
        /*----------------config        uracion de la pantalla------------------------*/
        shadow.setBlurType(BlurType.GAUSSIAN);  
        shadow.setColor(javafx.scene.paint.Color.web("#eaedf2"));  
        shadow.setHeight(25);  
        shadow.setRadius(12);  
        shadow.setWidth(20);  
        shadow.setChoke(0.9);
        bienvenido.setStyle("-fx-background-color: #03A9F4;");
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
        
        //Ancho del scroll
        grid.setMinWidth(ancho-(ancho/3));
        /*configuracion de la escena y los elementos que tendra*/
        Group root = new Group();
        final StackPane sp1 = new StackPane();
        //fondo del pandel lateral
        /*
        Stop[] stops = new Stop[] {
           new Stop(0, Color.PURPLE),
           new Stop(1, Color.BLUE)
        };
        /*fondo*/
        //LinearGradient gp = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops);
        Stop[] stops = new Stop[] {
            new Stop(0, Color.web("blue")),
            new Stop(1, Color.web("#33ffe0")),
        };
        //color de fondo detras del grid
        LinearGradient gp = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops);
        //se define el color de fondo de la parte interna
        
        grid.setAlignment(Pos.CENTER);
        
        root.getChildren().add(grid);
        /*configuracion de la escena y los elementos que tendra*/
        anchorPane.setMinHeight(altura);
        anchorPane.setMaxWidth(ancho/2);
        anchorPane.setPadding(new Insets(50,0,0,0));
        /*Scroll del formulario*/
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(ancho, altura);
        /*Se asigna contenido al scrol*/
        scroll.setContent(anchorPane);
        /*activar o desactivar barras laterales*/
        scroll.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        //se crea un Group para colocar dentro al splipane 
        SplitPane sp = new SplitPane();
        /*Se coloca el grid dentro del pane*/
        anchorPane.getChildren().add(grid);
        sp1.setStyle("-fx-background-color: #00ff77;");//fondo del lateral izquieerdo
        
        sp1.setDisable(false);//no permite que se ajuste el panel
        sp1.setMaxSize(ancho/6,altura);
        sp1.setPadding(new Insets(200,0,00,0));
        Text modificarProp = new Text("Modificar Propiedad"); 
        /*Configuración de la fuente del texto*/
        modificarProp.setFont(Font.font(null, FontWeight.BOLD, 15));     
        //asignando el color
        modificarProp.setFill(Color.CRIMSON); 
        //asignando la posicion del texto
        modificarProp.setX(20); 
        modificarProp.setY(00);       
        Text salir = new Text("Cerrar sesión"); 
        //Configuración de la fuente del texto
        salir.setFont(Font.font(null, FontWeight.BOLD, 15));     
        //asignando el color
        salir.setFill(Color.CRIMSON); 
        //asignando la posicion del texto
        salir.setX(20); 
        salir.setY(400);       
        Text modificarPer = new Text("Modificar Perfil"); 
        modificarPer.setFont(Font.font(null, FontWeight.BOLD, 15));     
        modificarPer.setFill(Color.CRIMSON); 
        modificarPer.setX(20); 
        modificarPer.setY(200);       
        /*grupo para añadir elementos*/
        Group paneLateral2 = new Group();
        /*se determina si se muestra modificar propiedad o no*/
        if(usuarioBienvenida.getText().equals("Estudiante"))
            paneLateral2.getChildren().addAll(modificarPer,salir);
        else
            paneLateral2.getChildren().addAll(modificarPer,modificarProp,salir);
        sp1.getChildren().add(paneLateral2);
        
        //panel de la derecha
        final StackPane panelDerecho = new StackPane();
        panelDerecho.setStyle("-fx-background-color: #B4E5FC;");
        panelDerecho.setMaxSize(650,altura);
        panelDerecho.setDisable(false);
        panelDerecho.setAlignment(Pos.CENTER_LEFT);
        TextField buscador = new TextField();
        buscador.setPromptText("Search Box");
        buscador.textProperty().addListener((ov, t, t1) -> {
        });
        ListView<Object> itemView = new ListView<>();
        VBox boxDerecho = new VBox(buscador,itemView);
        boxDerecho.setLayoutX(0);
        //itemView.setPrefSize(200,900);
        Group paneLateralDer = new Group();
        paneLateralDer.getChildren().addAll(boxDerecho);
        paneLateralDer.setLayoutX(0);
        /*------------------------obtencion de notificaciones--------------------*/
        GridPane notificaciones = new GridPane();
        Label prueba = new Label("prueba");
        notificaciones.add(prueba,0,0);
        
        Label label = new Label("Notificaciones:");
        Label label2 = new Label("Alumnos que han apartado cuartos");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        label.setFont(font);
        //lista de notificaciones
        ApartarCuarto notificacionesCuarto = new ApartarCuarto();
        ArrayList<Notificaciones> notiTmp=notificacionesCuarto.obtenerNotificaciones(idUsuario);
        ObservableList<String> names = FXCollections.observableArrayList();//("Engineering", "MCA", "MBA", "Graduation", "MTECH", "Mphil", "Phd");
        
        for (int i = 0; i < notiTmp.size(); i++) {
            names.add(notiTmp.get(i).getNombreAlumno());
        }
        
        ListView<String> listView = new ListView<String>(names);
        listView.setMaxSize(200, 160);
        listView.setPadding(new Insets(0,50,0,0));
        //Creando el layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5,50,0, 0));
        layout.getChildren().addAll(label,label2, listView);
        layout.setStyle("-fx-background-color: BEIGE");
        /*se agrega el layout al box que va al panel lateral derecho*/
        if(usuarioBienvenida.getText().equals("Arrendador"))
            boxDerecho.getChildren().add(layout);
        /*--------------------fin obtencion-----------------------------------*/
        panelDerecho.getChildren().add(paneLateralDer);
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
        Scene scene = new Scene(root, ancho, altura);
        primaryStage.setTitle("Busqueda de cuartos");
        primaryStage.setScene(scene);
        /*-----------------fin configuracion de la pantalla---------------------*/
        
        /***************************campo de busquedad*************************/
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
        
        /*escucha para bloquear letras y solo aceptar 5 numeros*/
        searchBox.textProperty().addListener((ov, t, t1) -> {
        if(!t1.matches("[0-9]{0,5}") || t1.length()>8){
                ((StringProperty) ov).setValue(t);
        }
        else{
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
                propiedades.removeAll(propiedades);
                propiedades=buscarCuartos(precioInicial,precioFinal);
                for (int i = 0; i <propiedades.size(); i++) {
                    propiedadesCpy.add(propiedades.get(i));
                }
                ver();
            }
        });
        searchBoxF.textProperty().addListener((ov, t, t1) -> {
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
                propiedades.removeAll(propiedades);
                propiedades=buscarCuartos(precioInicial,precioFinal);
                for (int i = 0; i <propiedades.size(); i++) {
                    propiedadesCpy.add(propiedades.get(i));
                }
                ver();
            }
        });
        
        //campos de busqueda
        VBox box = new VBox(searchBox);
        VBox box2 = new VBox(searchBoxF);
        grid.add(box,0,0);
        grid.add(box2,1,0);
        /*********************fin campo de busquedad***************************/    

        /*Evento para cerrar sesión*/
        salir.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                try {
                    //primaryStage.close();
                    IniciarSesion inicio = new IniciarSesion();
                    inicio.start(primaryStage);
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
    /**
     * Método para leer un archivo
     * @param direccion
     * @return un ArrayList de Strings
     */
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
    /**
     * Metodo para crear la vista para listar las propiedades que esten en el rango
     */
    public void ver(){
        ImageView image11;
        byte byteImage[] = null;
        double posY=(ancho/6)+600;
        double posX=ancho/6;
        ArrayList<ImageView>imagenesBuscador = new ArrayList();
        GridPane fila=new GridPane();
        fila.setMinWidth(ancho-(ancho/3));
        fila.setBackground(Background.fill(blanco));
        fila.setPadding(new Insets(0,0,0,0));
        fila.setLayoutY(altura/3);
        fila.setVgap(12);
        fila.setHgap(50);
         
        /***********************lista todas las propiedades*******************/
        banderaActual=propiedades.size();
        banderaAnterior=propiedadesCpy.size();
        if (banderaActual<banderaAnterior) {
            Node nodo1 = anchorPane.getChildren().get(0);
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(0,nodo1); 
        }
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
                //System.out.println("i "+i);
                image11=imagenesBuscador.get(i);
                image11.setFitHeight(posX+200);
                image11.setFitWidth(posX+200);
                image11.setX(100);
                image11.setY(posY);
                Button btnInformacion= new Button("Apartar");
                btnInformacion.setId(""+i);
                /*Grid para agregar la informacion y el boton al grid fila*/
                Propiedad eleccionPropiedad = propiedades.get(i);
                //System.out.println("eleccion propiedad info"+eleccionPropiedad.getToken());
                RegistrarModificarPropiedad rModProp = new RegistrarModificarPropiedad();
                Propiedad tempo=rModProp.obtenerDatosPropiedad(eleccionPropiedad.getToken());
                
                GridPane infoCuartos = new GridPane();
                Label descripcionCuarto = new Label(tempo.getDescripcionCuarto());
                Label precio = new Label(tempo.getPrecio()+"");
                Label disponibilidad = new Label(tempo.getDisponibilidad());
                Label ubicacion =  new Label(tempo.getUbicacion());
                Label servicios = new Label(tempo.getServicios());
                
                Text descripcioncto = new Text("Descripción del cuarto: ");
                Text preciocto = new Text("Precio: ");
                Text disponibilidadcto = new Text("Disponibilidad: ");
                Text ubicacioncto = new Text("Ubicación: ");
                Text servicioscto = new Text("Servicios: ");
                
                descripcioncto.setStyle("-fx-background-color: #212121;");
                descripcioncto.setFont(new Font("Arial",28));
                preciocto.setStyle("-fx-background-color: #2b6ff6;");
                preciocto.setFont(new Font("Arial",28));
                disponibilidadcto.setStyle("-fx-background-color: #212121;");
                disponibilidadcto.setFont(new Font("Arial",28));
                ubicacioncto.setStyle("-fx-background-color: #212121;");
                ubicacioncto.setFont(new Font("Arial",28));
                servicioscto.setStyle("-fx-background-color: #212121;");
                servicioscto.setFont(new Font("Arial",28));
                btnInformacion.setStyle("-fx-background-color: #00BCD4;");
                btnInformacion.setFont(new Font("Arial",28));
                
                descripcionCuarto.setFont(new Font("Arial",22));
                precio.setFont(new Font("Arial",22));
                disponibilidad.setFont(new Font("Arial",22));
                ubicacion.setFont(new Font("Arial",22));
                servicios.setFont(new Font("Arial",22));
                descripcionCuarto.setTextFill(Color.web("#757575"));//#757575
                precio.setTextFill(Color.web("#757575"));
                disponibilidad.setTextFill(Color.web("#757575"));
                ubicacion.setTextFill(Color.web("#757575"));
                servicios.setTextFill(Color.web("#757575"));
        
                infoCuartos.add(descripcioncto,0, 0);
                infoCuartos.add(preciocto,0, 2);
                infoCuartos.add(disponibilidadcto,0, 4);
                infoCuartos.add(ubicacioncto,0, 6);
                infoCuartos.add(servicioscto,0, 8);
                if(usuarioBienvenida.getText().equals("Arrendador")){
                    btnInformacion.setDisable(true);
                }
                infoCuartos.add(btnInformacion, 0, 10);
                infoCuartos.add(descripcionCuarto,0, 1);
                infoCuartos.add(precio,0, 3);
                infoCuartos.add(disponibilidad,0, 5);
                infoCuartos.add(ubicacion,0, 7);
                infoCuartos.add(servicios,0, 9);
                
                /*Accion al pulsar el boton */
                btnInformacion.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                       /*no mostrar boton si propiedad ya no esta disponible*/
                       /*ignorar acciones */
                       RegistrarModificarPropiedad rModPropiedad = new RegistrarModificarPropiedad();
                       String x=btnInformacion.getId();
                       int poss=Integer.parseInt(x);
                       Propiedad actual = rModPropiedad.obtenerDatosPropiedad(propiedades.get(poss).getToken());
                       //System.out.println("actual ------->" +"btnID:"+btnInformacion.getId()+" -"+ actual.getDisponibilidad()+actual.getToken()+actual.getServicios()+actual.getIdPropiedad());
                       if(actual.getDisponibilidad().equals("No disponible")){
                           btnInformacion.setOpacity(0);
                       }
                       else{       
                
                            Stage inicio = new Stage();
                            Group elementosVenEmer= new Group();
                            Popup po = new Popup();
                            po.setX(ancho/2);
                            po.setY(altura/2);
                            po.setHeight(altura/10);
                            po.setWidth(altura/8);
                            Label tituloVentanaE = new Label ("¿Desea realmente apartar esta propiedad?");
                            Button apartar = new Button("Apartar");
                            HBox hb = new HBox(17);
                            hb.setStyle("-fx-background-color: violet; -fx-padding: 13px;");
                            Button cancelar = new Button("Cancelar");
                           /*Evento cuando el alumno confirma apartar el cuarto*/
                            apartar.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                    ApartarCuarto apartarBD = new ApartarCuarto();
                                    apartarBD.apartarCuarto(eleccionPropiedad.getToken());
                                    /*desaparecer el boton de apartar*/
                                    apartar.setOpacity(0);
                                    inicio.close();
                                    }
                            });
                            /*Evento cuadno pulsa en cancelar*/
                            cancelar.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                    inicio.close();
                                }
                            });
                            
                            elementosVenEmer.setStyle("-fx-background-color: violet; -fx-padding: 13px;");
                            elementosVenEmer.getChildren().add(hb);
                            RegistrarModificarPropiedad rModiProp = new RegistrarModificarPropiedad();
                            String id=btnInformacion.getId();
                            int pos=Integer.parseInt(id);
                            Propiedad eleccionPropiedad = propiedadesCpy.get(pos);
                            Propiedad elejida = new Propiedad();
                            hb.getChildren().addAll(tituloVentanaE,apartar, cancelar);
                            
                            
                            Scene emergente = new Scene(elementosVenEmer,500,100,Color.web("violet"));
                            inicio.setScene(emergente);
                            inicio.setResizable(false);
                            inicio.setTitle("Confirmar apartado");
                            inicio.show();

                        }
                    }
                });
                 /*aumenta en y*/
                posY+=posY;
                fila.add(image11, 0, i);
                fila.add(infoCuartos, 1, i);
            }
            anchorPane.getChildren().add(fila);
            imagenesBuscador.removeAll(propiedades);
        }else{
               /*limpiar grid de las imagenes antiguas*/
                //System.out.println("entra seccion borrar");
                Node nodo1 = anchorPane.getChildren().get(0);
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(0,nodo1);
        }
    }
}
