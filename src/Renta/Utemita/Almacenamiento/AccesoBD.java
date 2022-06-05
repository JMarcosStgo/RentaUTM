/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.Almacenamiento;

import Renta.Utemita.Presentacion.Propiedad;
import Renta.Utemita.ReglasDeNegocio.RegistrarModificarUsuario.Usuario;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
 *
 * @author kAarl
 */
public class AccesoBD {
   private final String driver="com.mysql.cj.jdbc.Driver";
   private final String cadenaConeccion="jdbc:mysql://127.0.0.1/rentautemita";
   private final String usuario="root";
   private final String contraseña="";
   
   public Connection con;
   public void iniciarBD(){
       try {
            Class.forName(driver);
            con=DriverManager.getConnection(cadenaConeccion,usuario,contraseña);
           // JOptionPane.showMessageDialog(null,"Conexion exitosa a la base de datos");
            
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Error al establecer la conexion con la base de datos"+e);
       }
   }
   /**
    * 
    * @param correo
    * @param password
    * @return 
    */
   public Boolean existeUsuario(String correo,String password){
     Statement st;
       try {
           st = con.createStatement();
           ResultSet rs=st.executeQuery("SELECT * FROM usuario WHERE correo='"+correo+"' AND password='"+password+"'");
           //retorna verdadero si encuentra al usuario en la BD, sino False;
           if(rs.next()==true)
             return true;
       } catch (SQLException ex) {
           Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
   }
   
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
           } catch (Exception e) {
           }
            statment.setString(9,propiedad.getToken());
            statment.executeUpdate();
            
//          st.executeQuery("INSERT INTO propiedad SET ?"+propiedad);
//            st.executeQuery(" INSERT INTO propiedad(descripcion,precio,disponibilidad,ubicacion,servicios,imagen1,imagen2,imagen3,token) VALUES (${propiedad.getDescripcionCuarto()},'"+propiedad.getPrecio()+"','"+propiedad.getDisponibilidad()+"','"+propiedad.getUbicacion()+"','"+propiedad.getServicios()+"',' ',' ',' ','"+propiedad.getToken()+"')");
            }catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
   }
   /**
    * Metodo para verificar la existencia de una propiedad en la base de datos
    * @param token
    * @return 
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
    * Metodo para obtener una propiedad a partir del token
    * @param token
    * @return 
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
                temp.setPrecio(rs.getFloat(3));
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
            System.out.println(e.getLocalizedMessage());
        }
       return temp;
   }
   /**
    * Metodo para modificar una propiedad por su identificador
    * @param propiedad
    * @return boolean
    */
   public Boolean actualizarPropiedad(Propiedad propiedad){
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
   public boolean RegistroDeDatos(Usuario usuario){
       try {
            //Statement st = con.createStatement();
            String query="INSERT INTO usuario (correo,password,telefono,matricula,nombre) VALUES (?,?,?,?,?)";
            PreparedStatement statment=(PreparedStatement)con.prepareStatement(query);
            statment.setString(1,usuario.getCorreo());
            statment.setString(2,usuario.getContraseña());
            statment.setInt(3,usuario.getTelefono());
            statment.setString(4,usuario.getMatricula());
            statment.setString(5,usuario.getNombre());
            
            statment.execute();
            return true;    
       } catch (SQLException e) {
           System.out.println("error registro de datos"+e.getLocalizedMessage());
       }
       return false;
   }
   public Usuario obtencionDatos(int idUsuario){
       try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM usuario WHERE idUsuario='"+idUsuario+"'");
            if(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setCorreo(rs.getString(0));
                usuario.setContraseña(rs.getString(1));
                usuario.setIdUsuario(rs.getInt(2));
                usuario.setTelefono(rs.getInt(3));
                usuario.setMatricula(rs.getString(4));
                return usuario;
            }
       } catch (SQLException e) {
            System.out.println("error obtencion de datos"+e.getLocalizedMessage());
       }
       return null;
   }
   
   public boolean modificarDatos(Usuario usuario){
       try {
            //Statement st = con.createStatement();
            String query="UPDATE usuario SET correo='"+usuario.getCorreo()+"' ,password='"+usuario.getContraseña()+"'  ,telefono='"+usuario.getTelefono()+"'       ,matricula='"+usuario.getMatricula()+"'" +"where idPropiedad='"+usuario.getIdUsuario()+"'";
            PreparedStatement statment=(PreparedStatement)con.prepareStatement(query);
            statment.executeUpdate();
            return true;
       } catch (SQLException e) {
            System.out.println("error modificar datos"+e.getLocalizedMessage());
       }
       return false;
   }
}
