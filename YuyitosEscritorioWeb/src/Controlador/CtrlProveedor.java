/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ConexionDB.Conexion;
import ConexionDB.DatosConexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Sebastian
 */
public class CtrlProveedor {
    
      public static ResultSet AgregarProveedor(String rutProv, String dvProv, String nombre, String email,
                                             String fono, String nomUsu, String calle, String dirNum, String comuna){
        ResultSet res = null;
        //SP_InsertarProveedor
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_InsertarProveedor(?,?,?,?,?,?,?,?,?)}");
            
            cst.setString(1, rutProv);
            cst.setString(2, dvProv);
            cst.setString(3, nombre);
            cst.setString(4, email);
            cst.setString(5, fono);
            cst.setString(6, nomUsu);
            cst.setString(7, calle);
            cst.setString(8, dirNum);
            cst.setString(9, comuna);
            cst.execute();
            
            final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Se agregó Nuevo Proveedor Exitosamente.");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return res;
    }
      
      public static ResultSet ListarProveedor(){
        ResultSet res = null;
        try {
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_tablaProveedor(?)}");
            
            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();
            res = (ResultSet)cst.getObject(1);            
            
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        return res;       
    }
      
      public static ResultSet EliminarProveedor(String rutProv){
          ResultSet res = null;
          
          try {
              
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_EliminarProveedor(?,?)}");
            
            cst.setString(1, rutProv);
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.execute();
            
                        
            final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Se elimino Proveedor Exitosamente.");            
            
              
          } catch (Exception e) {
          }
          return res;
          
      }
      
      public static ResultSet AgregarProductosProveedor(int  precio, Date fechaIngreso, String rutProv, String nomProd ){
        ResultSet res = null;
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_insertProdProveedor(?,?,?,?)}");
            
            cst.setInt(1, precio);
            cst.setDate(2, fechaIngreso);
            cst.setString(3, rutProv);
            cst.setString(4, nomProd);
            cst.execute();            
            
        } catch (Exception e) {
        }
        return res;
    }
      
      public static ResultSet ListarProductoProveedor(){
        ResultSet res = null;
        try {
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_tablaProductosProveedor(?)}");
            
            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();
            res = (ResultSet)cst.getObject(1);            
            
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        return res;       
    }
    
      
      
      
      
    
    
    
}
