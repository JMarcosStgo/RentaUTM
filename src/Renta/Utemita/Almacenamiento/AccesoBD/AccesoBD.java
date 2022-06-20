package Renta.Utemita.Almacenamiento.AccesoBD;

import Renta.Utemita.ReglasDeNegocio.ApartarCuarto.Notificaciones;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarPropiedad.Propiedad;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario.Usuario;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Clase donde se implementan metodos que realizan las llamadas al gestor de base de datos
 * @author Marcos
 * @version 1.0
 */
public class AccesoBD {
   private final String driver="com.mysql.cj.jdbc.Driver";
   private final String cadenaConeccion="jdbc:mysql://127.0.0.1/rentautemita";
   private final String usuario="root";
   private final String contraseña="";
   private int idUsuario=0;
   public Connection con;
   public void iniciarBD(){
       try {
            Class.forName(driver);
            con=DriverManager.getConnection(cadenaConeccion,usuario,contraseña);
           // JOptionPane.showMessageDialog(null,"Conexion exitosa a la base de datos");
            
       } catch (ClassNotFoundException | SQLException e) {
           JOptionPane.showMessageDialog(null,"Error al establecer la conexion con la base de datos"+e);
       }
   }
   /**
    * Metodo para verificar la existencia de un usuario en la base de datos
    * @param correo
    * @param password
    * @return valor boleano verdadero si el usuario existe en la base de datos,
    *         si no retorna false
    */
   public Boolean existeUsuario(String correo,String password){
     Statement st;
       try {
           st = con.createStatement();
           ResultSet rs=st.executeQuery("SELECT * FROM usuario WHERE correo='"+correo+"' AND password='"+password+"'");
           //retorna verdadero si encuentra al usuario en la BD, sino False;
           if(rs.next()==true){
               idUsuario=rs.getInt(3);
               System.out.println("id usuario existeusuario: "+idUsuario);
               escribeArchivo(rs.getInt(3),rs.getString(7));
               return true;
           }
       } catch (SQLException ex) {
           Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
   }
   /**
    * Metodo para cerrar la conexion en la base de datos
    */
   public void DesconectarBD(){
       try {con.close();
           //JOptionPane.showMessageDialog(null,"Sesion BD terminada de manera exitosa");           
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"A ocurrido un error al desconectar la base de datos");
       }
   }
   /**
    * Metodo para dar de alta una propiedad en la base de datos
    * @param propiedad 
    */
   public void altaPropiedad(Propiedad propiedad){
       try {
            Statement st = con.createStatement();
            String query="INSERT INTO propiedad (descripcion,precio,disponibilidad,ubicacion,servicios,imagen1,imagen2,imagen3,token) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statment=(PreparedStatement)con.prepareStatement(query);
            statment.setString(1,propiedad.getDescripcionCuarto());
            statment.setFloat(2,propiedad.getPrecio());
            statment.setString(3,propiedad.getDisponibilidad());
            statment.setString(4,propiedad.getUbicacion());
            statment.setString(5,propiedad.getServicios());
            try {
               FileInputStream input = null;
               FileInputStream input2 = null;
               FileInputStream input3 = null;
               
                File file = new File(propiedad.getImagenes().get(0));
                input = new FileInputStream(file);
                statment.setBinaryStream(6, input);
                file = new File(propiedad.getImagenes().get(1));
                input2 = new FileInputStream(file);
                statment.setBinaryStream(7, input2);
                file = new File(propiedad.getImagenes().get(2));
                input3 = new FileInputStream(file);
                statment.setBinaryStream(8, input3);
           } catch (FileNotFoundException | SQLException e) {
                System.out.println("error al dar de alta una propiedad"+e.getLocalizedMessage());
           }
            statment.setString(9,propiedad.getToken());
            statment.executeUpdate();
            ArrayList<String>  ident=leeArchivo("..\\RentaUTM\\src\\Imagenes\\id.txt");
            System.out.println("ident"+ident.get(0));
            idUsuario=Integer.parseInt(ident.get(0));
            caseropropiedad(idUsuario,propiedad.getToken());
            }catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
   }
   /**
    * Metodo para verificar la existencia de una propiedad en la base de datos
    * @param token
    * @return verdadero o falso,para una propiedad en la BD
    */
   public Boolean verificacionCodigoPropiedad(String token){
        boolean retorno=false;
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs=st.executeQuery("SELECT * FROM propiedad WHERE token='"+token+"'");
            
            if(rs.next()==true){retorno=true;}
        } catch (SQLException ex) {
           Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
       }
       return retorno;
   }
   /**
    * Metodo para obtener una propiedad de la BD a partir del token
    * @param token
    * @return una propiedad
    */
   public Propiedad obtenerPropiedadModificar(String token){
       Propiedad temp=new Propiedad();
       try {
            Statement st = con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM propiedad WHERE token='"+token+"'");
            System.out.println("rs"+rs);
            if(rs.next()==true){
                temp.setIdPropiedad(rs.getInt(1));
                temp.setDescripcionCuarto(rs.getString(2));
                temp.setPrecio(rs.getInt(3));
                temp.setDisponibilidad(rs.getString(4));
                temp.setUbicacion(rs.getString(5));
                temp.setServicios(rs.getString(6));
                /*lectura de los archivos blob y se convierten en tipo Image*/
                try {
                    ArrayList <Image> imagenes = new ArrayList();
                    ArrayList <Blob> imagenesBlob = new ArrayList();
                    
                    InputStream in = rs.getBinaryStream(7);
                    BufferedImage image = ImageIO.read(in);
                    imagenes.add(image);
                    in = rs.getBinaryStream(8);
                    image = ImageIO.read(in);
                    imagenes.add(image);
                    in = rs.getBinaryStream(9);
                    image = ImageIO.read(in);
                    imagenes.add(image);
                    temp.setImagenesP(imagenes);
                    imagenesBlob.add(rs.getBlob(7));
                    imagenesBlob.add(rs.getBlob(8));
                    imagenesBlob.add(rs.getBlob(9));
                    temp.setImagenesBlob(imagenesBlob);
                } catch (IOException | SQLException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                return temp;
            }
            System.out.println(temp);
        }catch (SQLException e) {
            System.out.println("error alta propiedad"+e.getLocalizedMessage());
        }
       return temp;
   }
   /**
    * Metodo para modificar una propiedad por su identificador
    * @param propiedad
    * @return valor boleano, verdadero si se actualiza sin error la propiedad
    */
   public boolean actualizarPropiedad(Propiedad propiedad){
        try {
            Statement st = con.createStatement();
            String query="UPDATE propiedad SET descripcion='"+propiedad.getDescripcionCuarto()+"' ,precio='"+propiedad.getPrecio()+"'  ,disponibilidad='"+propiedad.getDisponibilidad()+"'       ,ubicacion='"+propiedad.getUbicacion()+"'  ,servicios='"+propiedad.getServicios()+"'  ,imagen1='"+propiedad.getImagenesBlob().get(0)+"'     ,imagen2='"+propiedad.getImagenesBlob().get(1)+"'    ,imagen3='"+propiedad.getImagenesBlob().get(2)+"'"   +",token='"+propiedad.getToken()+"'" +"where idPropiedad='"+propiedad.getIdPropiedad()+"'";
            PreparedStatement statment=(PreparedStatement)con.prepareStatement(query);
            //ResultSet rs=st.executeQuery("UPDATE propiedad SET ? WHERE idPropiedad='"+propiedad.getIdPropiedad()+"'"+propiedad);
            statment.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("actualizar propiedad"+e.getLocalizedMessage());
        }
       return false;
   }
   /**
    * Método para dar de alta un usuario en la BD
    * @param usuario
    * @return valor boleano, verdadero si se ingreso correctamente a la BD
    */
   public boolean registroDeDatos(Usuario usuario){
       try {
            String query="INSERT INTO usuario (correo,password,telefono,matricula,nombre,tipo) VALUES (?,?,?,?,?,?)";
            PreparedStatement statment=(PreparedStatement)con.prepareStatement(query);
            statment.setString(1,usuario.getCorreo());
            statment.setString(2,usuario.getContraseña());
            statment.setLong(3,usuario.getTelefono());
            statment.setString(4,usuario.getMatricula());
            statment.setString(5,usuario.getNombre());
            statment.setString(6,usuario.getTipo());
            statment.execute();
            return true;    
       } catch (SQLException e) {
           System.out.println("error registro de datos"+e.getLocalizedMessage());
       }
       return false;
   }
   /**
    * Método para obtener los datos de un usuario a partir de su id
    * @param idUsuario
    * @return retorna un usuario
    */
   public Usuario obtencionDatos(int idUsuario){
       try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM usuario WHERE idUsuario='"+idUsuario+"'");
            if(rs.next()==true){
                Usuario usuarioBD = new Usuario();
                usuarioBD.setCorreo(rs.getString(1));
                usuarioBD.setContraseña(rs.getString(2));
                usuarioBD.setIdUsuario(rs.getInt(3));
                usuarioBD.setTelefono(rs.getLong(4));
                usuarioBD.setMatricula(rs.getString(5));
                usuarioBD.setNombre(rs.getString(6));
                
                return usuarioBD;
            }
       } catch (SQLException e) {
            System.out.println("error obtencion de datos"+e.getLocalizedMessage());
       }
       return null;
   }
   /**
    * Método para modificar los datos de un usuario en la BD
    * @param usuario
    * @return valor boleano, verdadero si si modifica exitosamente un usuario
    */
   public boolean modificarDatos(Usuario usuario){
       try {
            String query="UPDATE usuario SET correo='"+usuario.getCorreo()+"' ,password='"+usuario.getContraseña()+"'  ,telefono='"+usuario.getTelefono()+"'       ,matricula='"+usuario.getMatricula()+"'       ,nombre='"+usuario.getNombre()+"'  " +"where idUsuario='"+usuario.getIdUsuario()+"'";
            PreparedStatement statment=(PreparedStatement)con.prepareStatement(query);
            statment.executeUpdate();
            return true;
       } catch (SQLException e) {
            System.out.println("error modificar datos"+e.getLocalizedMessage());
       }
       return false;
   }
   
   public void escribeArchivo(int idUsuario,String tipo){
        //Un texto cualquiera guardado en una variable
        this.idUsuario=idUsuario;
        String saludo =String.valueOf(idUsuario);
        try {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivo = new File("..\\RentaUTM\\src\\Imagenes\\id.txt");
            archivo.delete();
            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter escribir = new FileWriter(archivo, true);
            //escribir.write("");
            escribir.write(saludo);
            escribir.write("\n");
            escribir.write(tipo);
            
            

            //Cerramos la conexion
            escribir.close();
        } //Si existe un problema al escribir cae aqui
        catch (IOException e) {
            System.out.println("Error al escribir"+e.getLocalizedMessage());
        }
    }
   /**
    * Método para listar todos los cuartos dentro del rango del precio
    * @param precioInicial
    * @param precioFianl
    * @return valor Propiedad, una lista de cuartos 
    */
   public ArrayList<Propiedad> listCuartosPrecios(int precioInicial , int precioFianl){
            //String query="SELECT * FROM propiedad WHERE precio>='"+precioInicial+"' AND precio<='"+precioFianl+"'";
            //st = con.createStatement();
           //ResultSet rs=st.executeQuery("SELECT * FROM usuario WHERE correo='"+correo+"' AND password='"+password+"'");
        ArrayList <Propiedad> tem = new ArrayList();
        try{
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("SELECT token,imagen1,imagen2,imagen3 FROM propiedad WHERE precio>='"+precioInicial+"' AND precio<='"+precioFianl+"'");
            //System.out.println("try listar cuartos");
            while (rs.next()) {
                //System.out.println("lista cuartos x precio");
                Propiedad temp = new Propiedad();
                //temp.setIdPropiedad(rs.getInt(1));
                //temp.setDescripcionCuarto(rs.getString(2));
                //temp.setPrecio(rs.getInt(3));
                //temp.setDisponibilidad(rs.getString(4));
                //temp.setUbicacion(rs.getString(5));
                //temp.setServicios(rs.getString(6));
                temp.setToken(rs.getString(1));
                /*lectura de los archivos blob y se convierten en tipo Image*/
                try {
                    ArrayList <Image> imagenes = new ArrayList();
                    ArrayList <Blob> imagenesBlob = new ArrayList();
                    
                    InputStream in = rs.getBinaryStream(2);
                    BufferedImage image = ImageIO.read(in);
                    imagenes.add(image);
                    in = rs.getBinaryStream(3);
                    image = ImageIO.read(in);
                    imagenes.add(image);
                    in = rs.getBinaryStream(4);
                    image = ImageIO.read(in);
                    imagenes.add(image);
                    //temp.setImagenesP(imagenes);
                    imagenesBlob.add(rs.getBlob(2));
                    //imagenesBlob.add(rs.getBlob(3));
                    //imagenesBlob.add(rs.getBlob(4));
                    temp.setImagenesBlob(imagenesBlob);
                    tem.add(temp);
                }catch (IOException | SQLException e) {
                    System.out.println("error al cargar datos en listar cuartos precio "+e.getLocalizedMessage());
                }
            }
        } catch(SQLException e){
            System.out.println("error consulta lista cuartos precio "+e.getLocalizedMessage());
        }
        return tem;
   }
   /**
    * Método para devolver la información de un cuarto en especifico
    * @param idPropiedad
    * @return valor propiedad
    * @throws java.io.IOException
    */
   public Propiedad getCuarto(int idPropiedad) throws IOException{
       Propiedad temp = new Propiedad();
       try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM propiedad WHERE idPropiedad='"+idPropiedad+"'");
                temp.setDescripcionCuarto(rs.getString(contraseña));
                temp.setIdPropiedad(rs.getInt(1));
                temp.setDescripcionCuarto(rs.getString(2));
                temp.setPrecio(rs.getInt(3));
                temp.setDisponibilidad(rs.getString(4));
                temp.setUbicacion(rs.getString(5));
                temp.setServicios(rs.getString(6));
                
                ArrayList <Image> imagenes = new ArrayList();
                    ArrayList <Blob> imagenesBlob = new ArrayList();
                    
                    InputStream in = rs.getBinaryStream(7);
                    BufferedImage image = ImageIO.read(in);
                    imagenes.add(image);
                    in = rs.getBinaryStream(8);
                    image = ImageIO.read(in);
                    imagenes.add(image);
                    in = rs.getBinaryStream(9);
                    image = ImageIO.read(in);
                    imagenes.add(image);
                    temp.setImagenesP(imagenes);
                    imagenesBlob.add(rs.getBlob(7));
                    imagenesBlob.add(rs.getBlob(8));
                    imagenesBlob.add(rs.getBlob(9));
                    temp.setImagenesBlob(imagenesBlob);
                    
                
        } catch (SQLException e) {
           System.out.println("error consulta get cuarto "+e.getLocalizedMessage());
       }
       return temp;
   }
   /**
    * Método para actualizar la disponibiliad de un cuarto  y gurdar los
    * datos del alumno,propiedad y casero en la BD
    * @param token 
    */
   public void modificarDisponibilidad(String token){
       int idcasero=0;
        try {
            String query="UPDATE propiedad SET disponibilidad='No disponible' WHERE token='"+token+"'";//+"where idPropiedad='"+propiedad.getIdPropiedad()+"'
            PreparedStatement statment=(PreparedStatement)con.prepareStatement(query);
            statment.executeUpdate();
            
            Propiedad prop=obtenerPropiedadModificar(token);
            System.out.println("propiedad notificaciones "+prop.getIdPropiedad()+prop.getDescripcionCuarto()+prop.getToken());
            
           // try {
                /*obtener id del casero de la propiedad con el token*/
                Statement st = con.createStatement();
                System.out.println("------");
                
                ResultSet rs=st.executeQuery("SELECT * FROM caseropropiedad WHERE token='"+token+"'");
                if(rs.next()){
                    System.out.println("rs notificaiones"+rs.getInt(1)+rs.getInt(2));//+rs.getInt(2));
                
                    idcasero=rs.getInt(2);
                }
          //  } catch (SQLException ee) {
           //         System.out.println("error caseropropieda"+ee.getLocalizedMessage());
            ///}
            /* -----------*/
            ArrayList<String>  ident=leeArchivo("..\\RentaUTM\\src\\Imagenes\\id.txt");
            System.out.println("ident"+ident.get(0));
            idUsuario=Integer.parseInt(ident.get(0));
            System.out.println("idUsuario notificaiones"+idUsuario);
            /* ------- */
            //try {
                String query2="INSERT INTO notificaciones(idAlumno,nombreAlumno,idPropiedad,idCasero) VALUES (?,?,?,?)";
                PreparedStatement statment2=(PreparedStatement)con.prepareStatement(query2);
                statment2.setInt(1,idUsuario);
                Usuario tmp=obtencionDatos(idUsuario);
                System.out.println("tmp notificaciones"+tmp.getNombre());
                System.out.println("id usuario "+this.idUsuario+ tmp.getNombre()+tmp.getCorreo()+tmp.getIdUsuario());
                statment2.setString(2,tmp.getNombre());
                statment2.setInt(3,prop.getIdPropiedad());
                statment2.setInt(4,idcasero);
                statment2.execute();
            //} catch (SQLException error) {
             //   System.out.println("error al ingresar datos en la tabla notificaciones "+error.getLocalizedMessage());
            //}
//          notificaciones(idUsuario, token);
       } catch (SQLException e) {
            System.out.println("Error al actualizar cuarto "+e.getLocalizedMessage());
       }
    }
   /**
    * 
    * @param direccion
    * @return 
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
    * Método para guardar datos del alumno que ha apartado el cuarto
    * @param idUsuario
    * @param token 
    */
   public void caseropropiedad(int idUsuario,String token){
        /*insertar tupla en tabla propiedadAlumno*/
        try {
            String query=" INSERT INTO caseropropiedad(idCasero,token) VALUES (?,?)";
            PreparedStatement statment=(PreparedStatement)con.prepareStatement(query);
            statment.setInt(1,idUsuario);
            //Propiedad temp=obtenerPropiedadModificar(token);
            statment.setString(2,token);
            statment.execute();
        } catch (SQLException e) {
            System.out.println("Error al ingresar datos para notificar al casero "+e.getLocalizedMessage());
        }
   }
   /**
    * Método para obtener las notificaciones de un casero
    * @param idCasero 
     * @return  
    */
   public ArrayList obtenerNotificaciones(int  idCasero){
       ArrayList<Notificaciones> notificaciones = new ArrayList();
       try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery(" SELECT *FROM notificaciones WHERE idCasero='"+idCasero+"'");
            //statment=(PreparedStatement)con.prepareStatement(query);
            while(rs.next()){
                Notificaciones notiTmp = new Notificaciones(rs.getInt(1),rs.getInt(2),rs.getInt(4),rs.getInt(5),rs.getString(3));
                notificaciones.add(notiTmp);
            }
        }catch (SQLException e) {
            System.out.println("Error al obtener las notificaciones"+e.getLocalizedMessage());
        }
       return notificaciones;
   }
}
