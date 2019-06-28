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
import java.sql.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Sebastian
 */
public class CtrlPedido {
    
    public static ResultSet AgregarProductosPedido(String cantidad, String producto){
        ResultSet res = null;
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_InsertPedidoProd(?,?)}");
            
            cst.setString(1, cantidad);
            cst.setString(2, producto);
            cst.execute();
            
        } catch (Exception e) {
        }
        return res;
    }
    
    public static ResultSet AgregarPedido(String rutProv, String rutTrab, Date fechaOrden, Date fechaRecep){
        ResultSet res = null;
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_InsertarPedido(?,?,?,?)}");
            
            cst.setString(1, rutProv);
            cst.setString(2, rutTrab);
            cst.setDate(3, fechaOrden);
            cst.setDate(4, fechaRecep);
            cst.execute();
            
        } catch (Exception e) {
        }
        return res;    
    }
    
    public static ResultSet ListarProductos(){
        ResultSet res = null;
        try {
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_tablaProductos(?)}");
            
            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();
            res = (ResultSet)cst.getObject(1);            
            
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        return res;       
    }
    
    public static ResultSet ListarPedidos(){
        ResultSet res = null;
        try {
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_tablaPedidos(?)}");
            
            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();
            res = (ResultSet)cst.getObject(1);            
            
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        return res;       
    }
    
    public static ResultSet ModificarEstadoPedido(String idPedido, String nuevoEstado){
        // SP_modificarEstadoPedido
        ResultSet res = null;
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_modificarEstadoPedido(?,?)}");
            
            cst.setString(1, idPedido);
            cst.setString(2, nuevoEstado);            
                        
            cst.execute();            
                        
            final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Se modificó el estado a : " + nuevoEstado);
            
        } catch (Exception e) {
        }
        return res;
    }
    
  
}
