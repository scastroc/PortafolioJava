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
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Sebastian
 */
public class CtrlUsuario {
    
    public static ResultSet AgregarUsuario(String nombre, String password, String rut, int perfil){
        ResultSet res = null;
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_InsertarUsuario(?,?,?,?)}");
            
            cst.setString(1, nombre);
            cst.setString(2, password);            
            cst.setString(3, rut);
            cst.setInt(4, perfil);
            
            cst.execute();            
                        
            final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Se agregó Nuevo Usuario Exitosamente.");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        return  res;
    }
    
    public static ResultSet ListarUsuario(){
        ResultSet res = null;
        try {
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_tablaUsuarios(?)}");
            
            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();
            res = (ResultSet)cst.getObject(1);            
            
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        return res;       
    }
    
    
    public static ResultSet ListarUsuarioInactivo(){
        ResultSet res = null;
        try {
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_UsuariosInactivos(?)}");
            
            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();
            res = (ResultSet)cst.getObject(1);            
            
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        return res;       
    }
    
    public static ResultSet ActivarUsuario(String nombre){
        ResultSet res = null;
        int userID = 0;
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_activarUsuario(?,?)}");
            
            cst.setString(1, nombre);
            cst.registerOutParameter(2, java.sql.Types.NUMERIC);
            cst.execute();
            
            userID = cst.getInt(2);

            if (userID > 0) {

                final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Se Aprobo el usuario: " + nombre);

            }
            
            //res = (ResultSet)cst.getObject(1);   
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return res;
    }
    
    public static ResultSet EliminarUsuario(String nombre){
        ResultSet res = null;
        int userID = 0;
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_eliminarUsuario(?,?)}");
            
            cst.setString(1, nombre);
            cst.registerOutParameter(2, java.sql.Types.NUMERIC);
            cst.execute();
            
            userID = cst.getInt(2);

            if (userID > 0) {

                final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Se Elimino el usuario: " + nombre);

            }           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return res;
    }
    
}
