
package Vista;

import ConexionDB.Conexion;
import ConexionDB.DatosConexion;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import javax.swing.JComboBox;
import Controlador.CtrlTrabajador;
import Controlador.CtrlUsuario;
import Controlador.CtrlPedido;
import Controlador.CtrlProveedor;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import javax.swing.JDialog;

/**
 *
 * @author Sebastian
 */
public class HomeAdmin extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public HomeAdmin() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        OcultarPaneles();
        LlenarComboComunas();
        LlenarComboTrabajadores();
        LlenarComboPerfil();
        LlenarComboUsuarioInactivo();
        LlenarComboUsuarioActivo();
        LlenarCmbProveedorAct();
        LlenarCmbProducto();
        LlenarCmbPedidos();
        LlenarCmbEstadoPed();
        pnl_trabajador.setVisible(true);
        
        
    }
    
    public static java.sql.Date ConvertirFecha(java.util.Date javaDate){
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }
    
    public void OcultarPaneles(){
        
        pnl_principal.setVisible(false);
        pnl_trabajador.setVisible(false);
        pnl_agregarTrabajador.setVisible(false);
        pnl_eliminarTrabajador.setVisible(false);
        pnl_listarTrabajador.setVisible(false);
        pnl_modifTrabajador.setVisible(false);
        pnl_usuario.setVisible(false);
        pnl_agregarUsuario.setVisible(false);
        pnl_modificarUsuario.setVisible(false);
        pnl_listarUsuario.setVisible(false);
        pnl_aprobarUsuario.setVisible(false);
        pnl_eliminarUsuario.setVisible(false);
        pnl_pedido.setVisible(false);
        pnl_pedidoAgregar.setVisible(false);
        pnl_pedidoModificar.setVisible(false);
        pnl_pedidoEstado.setVisible(false);
        pnl_pedidoListar.setVisible(false);
        pnl_pedidoEliminar.setVisible(false);
        pnl_proveedor.setVisible(false);
        pnl_proveedorAgregar.setVisible(false);
        pnl_proveedorListar.setVisible(false);
        pnl_proveedorEliminar.setVisible(false);
        jDialogCrearUserProv.setVisible(false);
        jDialogAgregarProductos.setVisible(false);
                       
    }
    
    public void LlenarComboComunas(){
        
        this.cmb_comuna.removeAllItems();
        cmb_comuna.addItem("Seleccione");
        this.cmb_comuna2.removeAllItems();
        cmb_comuna2.addItem("Seleccione"); 
     
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboComunas(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            while (rs.next()) {
                this.cmb_comuna.addItem(rs.getString(1));
                this.cmb_comuna2.addItem(rs.getString(1));
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void LlenarComboTrabajadores(){
        
        this.cmb_elegirTrab.removeAllItems();
        cmb_elegirTrab.addItem("Seleccione Trabajador");
        this.cmb_elegirTrab2.removeAllItems();
        cmb_elegirTrab2.addItem("Seleccione");
        this.cmb_rutTrabPedido.removeAllItems();
        cmb_rutTrabPedido.addItem("Seleccione");
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboTrabajador(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            while (rs.next()) {
                this.cmb_elegirTrab.addItem(rs.getString(1));
                this.cmb_elegirTrab2.addItem(rs.getString(1));
                this.cmb_rutTrabPedido.addItem(rs.getString(1));
                
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }      
    }
    
    public void LlenarComboPerfil(){
        
        this.cmb_perfilUsuario.removeAllItems();
        cmb_perfilUsuario.addItem("Seleccione");
        this.cmb_perfilUsuProv.removeAllItems();
        cmb_perfilUsuProv.addItem("Seleccione");
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboPerfil(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            
            while (rs.next()) {
                //this.cmb_perfilUsuario.addItem(rs.getString(1));
                this.cmb_perfilUsuario.addItem(rs.getString(2));
                this.cmb_perfilUsuProv.addItem(rs.getString(2));
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }       
    }
    
    public void LlenarComboUsuarioInactivo(){
        
        this.cmb_usuarioInactivo.removeAllItems();
        cmb_usuarioInactivo.addItem("Seleccione");
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboUsuarioInactivo(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            
            while (rs.next()) {
                this.cmb_usuarioInactivo.addItem(rs.getString(1));              
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }       
    }
    
    public void LlenarComboUsuarioActivo(){
        
        this.cmb_eliminarUsuario.removeAllItems();
        cmb_eliminarUsuario.addItem("Seleccione");
        this.cmb_usuarioProveedor.removeAllItems();
        cmb_usuarioProveedor.addItem("Seleccione");
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboUsuarioActivo(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            
            while (rs.next()) {
                this.cmb_eliminarUsuario.addItem(rs.getString(1)); 
                this.cmb_usuarioProveedor.addItem(rs.getString(1)); 
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }       
    }
    
    public void LlenarCmbProveedorAct(){
        
        this.cmb_eliminarProveedor.removeAllItems();
        cmb_eliminarProveedor.addItem("Seleccione");
        this.cmb_rutProvPedido.removeAllItems();
        cmb_rutProvPedido.addItem("Seleccione");
        
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboProveedor(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            
            while (rs.next()) {
                this.cmb_eliminarProveedor.addItem(rs.getString(1)); 
                this.cmb_rutProvPedido.addItem(rs.getString(1)); 
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }       
    }
    
    public void LlenarCmbProducto(){
        
        this.cmb_productosPedido.removeAllItems();
        cmb_productosPedido.addItem("Seleccione");
        
        
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboProductos(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            
            while (rs.next()) {
                this.cmb_productosPedido.addItem(rs.getString(1)); 
                 
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }       
    }
    
    public void LlenarCmbPedidos(){
        
        this.cmb_selecPedido.removeAllItems();
        cmb_selecPedido.addItem("Seleccione");
        
        
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboPedidos(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            
            while (rs.next()) {
                this.cmb_selecPedido.addItem(rs.getString(1)); 
                 
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }       
    }
    
    public void LlenarCmbEstadoPed(){
        
        this.cmb_estadoPedido.removeAllItems();
        cmb_estadoPedido.addItem("Seleccione");
        
        
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboPedEstado(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            
            while (rs.next()) {
                this.cmb_estadoPedido.addItem(rs.getString(1)); 
                 
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }       
    }
    
    
    
    private static DefaultTableModel buildTableModel(ResultSet res)
            throws SQLException {

        ResultSetMetaData metaData = res.getMetaData();

        // Nombres de columnas
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Datos de la tabla
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (res.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(res.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
    
 
    
    public void CerrarConexion(){
        
        try {            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            conn.close();
            System.out.println("Conexion cerrada");
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
//    public void TablaUsuarioActivo(){
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogCrearUserProv = new javax.swing.JDialog();
        jLabel47 = new javax.swing.JLabel();
        txt_nomUsuProv = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txt_contraUsuProv = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        cmb_perfilUsuProv = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        btn_crearUsuario1 = new javax.swing.JButton();
        txt_rutUsuProv = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jDialogAgregarProductos = new javax.swing.JDialog();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        cmb_productosPedido = new javax.swing.JComboBox<>();
        txt_cantidad = new javax.swing.JTextField();
        btn_enviarProductosOrden = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbl_productosPedido = new javax.swing.JTable();
        btn_enviarPedido = new javax.swing.JButton();
        jPanelNavegacion = new javax.swing.JPanel();
        LogoYuyitos = new org.edisoncor.gui.panel.PanelImage();
        btn_irPnlTrabajadores = new javax.swing.JButton();
        btn_IrPnlUsuarios = new javax.swing.JButton();
        btn_irPnlPedidos = new javax.swing.JButton();
        btn_irPnlProveedor = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        pnl_principal = new javax.swing.JPanel();
        pnl_trabajador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_irPnlAgregarTrabajador = new javax.swing.JButton();
        btn_irPnlModificarTrabajador = new javax.swing.JButton();
        btn_irPnlEliminarTrabajador = new javax.swing.JButton();
        btn_irPnlListarTrabajador = new javax.swing.JButton();
        pnl_agregarTrabajador = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_rut = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_dv = new javax.swing.JTextField();
        txt_nombres = new javax.swing.JTextField();
        txt_apePaterno = new javax.swing.JTextField();
        txt_apeMaterno = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_fono = new javax.swing.JTextField();
        txt_calle = new javax.swing.JTextField();
        txt_dirNumero = new javax.swing.JTextField();
        txt_deptoNum = new javax.swing.JTextField();
        txt_dirVilla = new javax.swing.JTextField();
        cmb_comuna = new javax.swing.JComboBox<>();
        dateFechaContratacion = new datechooser.beans.DateChooserCombo();
        btn_nuevoTrabajador = new javax.swing.JButton();
        pnl_listarTrabajador = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_listarTrab = new javax.swing.JTable();
        btn_listarTrab = new javax.swing.JButton();
        pnl_eliminarTrabajador = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmb_elegirTrab = new javax.swing.JComboBox<>();
        btn_eliminarTrab = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_eliminarTrab = new javax.swing.JTable();
        pnl_modifTrabajador = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        pnl_usuario = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_irPnlAgregarUsuario = new javax.swing.JButton();
        btn_irPnMmodificarUsuario = new javax.swing.JButton();
        btn_irPnlEliminarUsuario = new javax.swing.JButton();
        btn_irPnLlistarUsuario = new javax.swing.JButton();
        btn_irPnlAprobarUsuario = new javax.swing.JButton();
        pnl_agregarUsuario = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_nomUsuario = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txt_contraUsuario = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        cmb_perfilUsuario = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        btn_crearUsuario = new javax.swing.JButton();
        cmb_elegirTrab2 = new javax.swing.JComboBox<>();
        pnl_modificarUsuario = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_modificarUsuarios = new javax.swing.JTable();
        cmb_elegirTrab3 = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        txt_modificarNomUsu = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txt_modificarContraUsu = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        btn_ModificarUsuario = new javax.swing.JButton();
        pnl_listarUsuario = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_listarUsuarios = new javax.swing.JTable();
        btn_usuListar = new javax.swing.JButton();
        pnl_aprobarUsuario = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_listarUsuarios3 = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_listarUsuarios2 = new javax.swing.JTable();
        btn_aprobarUsuario = new javax.swing.JButton();
        cmb_usuarioInactivo = new javax.swing.JComboBox<>();
        pnl_eliminarUsuario = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        cmb_eliminarUsuario = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_usuEliminar = new javax.swing.JTable();
        btn_usuarioEliminar = new javax.swing.JButton();
        pnl_pedido = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        btn_irPnlAgregarPedido = new javax.swing.JButton();
        btn_irPnlModificarPedido = new javax.swing.JButton();
        btn_irPnlEliminarPedido = new javax.swing.JButton();
        btn_irPnlListarPedido = new javax.swing.JButton();
        btn_irPnlEstadoPedido = new javax.swing.JButton();
        pnl_pedidoAgregar = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        cmb_rutProvPedido = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        cmb_rutTrabPedido = new javax.swing.JComboBox<>();
        btn_agregarProdPedido = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        dateChOrdenPedido = new datechooser.beans.DateChooserCombo();
        pnl_pedidoModificar = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        pnl_pedidoEstado = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        cmb_selecPedido = new javax.swing.JComboBox<>();
        jLabel68 = new javax.swing.JLabel();
        cmb_estadoPedido = new javax.swing.JComboBox<>();
        btn_actulizarEstadoPedido = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbl_listarEstadoPedidos = new javax.swing.JTable();
        pnl_pedidoListar = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_listarPedidos = new javax.swing.JTable();
        btn_listarPedido = new javax.swing.JButton();
        pnl_pedidoEliminar = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        pnl_proveedor = new javax.swing.JPanel();
        btn_irPnlAgregarProv = new javax.swing.JButton();
        btn_irPnlListarProv = new javax.swing.JButton();
        btn_irPnlEliminarProv = new javax.swing.JButton();
        lbl_proveedor = new javax.swing.JLabel();
        pnl_proveedorAgregar = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txt_rutProveedor = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txt_dvProveedor = new javax.swing.JTextField();
        txt_nombreProveedor = new javax.swing.JTextField();
        txt_emailProveedor = new javax.swing.JTextField();
        txt_fonoProveedor = new javax.swing.JTextField();
        txt_calleProveedor = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txt_dirNumProveedor = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        cmb_comuna2 = new javax.swing.JComboBox<>();
        btn_proveedorAgregar = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        cmb_usuarioProveedor = new javax.swing.JComboBox<>();
        btn_pnlCrearUserProv = new javax.swing.JButton();
        pnl_proveedorListar = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_listarProveedor = new javax.swing.JTable();
        btn_listarProveedor = new javax.swing.JButton();
        pnl_proveedorEliminar = new javax.swing.JPanel();
        btn_proveedorEliminar = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        cmb_eliminarProveedor = new javax.swing.JComboBox<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_proveedorEliminar = new javax.swing.JTable();

        jDialogCrearUserProv.setAlwaysOnTop(true);
        jDialogCrearUserProv.setLocation(new java.awt.Point(525, 250));
        jDialogCrearUserProv.setMaximumSize(new java.awt.Dimension(350, 340));
        jDialogCrearUserProv.setMinimumSize(new java.awt.Dimension(350, 340));
        jDialogCrearUserProv.setResizable(false);

        jLabel47.setText("Nombre Usuario:  ");

        jLabel48.setText("Contraseña :  ");

        jLabel54.setText("Tipo de Usuario: ");

        jLabel55.setText("Rut Proveedor : ");

        btn_crearUsuario1.setText("Aceptar");
        btn_crearUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearUsuario1ActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("SansSerif", 2, 10)); // NOI18N
        jLabel56.setText("Sin punto, guion ni digito verificador");

        jLabel57.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Agregar Usuario");

        javax.swing.GroupLayout jDialogCrearUserProvLayout = new javax.swing.GroupLayout(jDialogCrearUserProv.getContentPane());
        jDialogCrearUserProv.getContentPane().setLayout(jDialogCrearUserProvLayout);
        jDialogCrearUserProvLayout.setHorizontalGroup(
            jDialogCrearUserProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogCrearUserProvLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jDialogCrearUserProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(jLabel48)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55))
                .addGap(22, 22, 22)
                .addGroup(jDialogCrearUserProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialogCrearUserProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_nomUsuProv)
                        .addComponent(txt_contraUsuProv)
                        .addComponent(cmb_perfilUsuProv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel56)
                    .addComponent(txt_rutUsuProv, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(jDialogCrearUserProvLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(btn_crearUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDialogCrearUserProvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogCrearUserProvLayout.setVerticalGroup(
            jDialogCrearUserProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogCrearUserProvLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel57)
                .addGap(18, 18, 18)
                .addGroup(jDialogCrearUserProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47)
                    .addComponent(txt_nomUsuProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jDialogCrearUserProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel48)
                    .addComponent(txt_contraUsuProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jDialogCrearUserProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel54)
                    .addComponent(cmb_perfilUsuProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jDialogCrearUserProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addGroup(jDialogCrearUserProvLayout.createSequentialGroup()
                        .addComponent(txt_rutUsuProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56)))
                .addGap(20, 20, 20)
                .addComponent(btn_crearUsuario1)
                .addGap(50, 50, 50))
        );

        jDialogAgregarProductos.setLocation(new java.awt.Point(525, 250));
        jDialogAgregarProductos.setMaximumSize(new java.awt.Dimension(530, 570));
        jDialogAgregarProductos.setMinimumSize(new java.awt.Dimension(530, 570));
        jDialogAgregarProductos.setResizable(false);

        jLabel65.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Agregar Productos");

        jLabel66.setText("Productos : ");

        jLabel67.setText("Cantidad : ");

        btn_enviarProductosOrden.setText("Agregar");
        btn_enviarProductosOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarProductosOrdenActionPerformed(evt);
            }
        });

        tbl_productosPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "NOMBRE PRODUCTO", "CANTIDAD"
            }
        ));
        jScrollPane11.setViewportView(tbl_productosPedido);

        btn_enviarPedido.setText("Enviar Solicitud");
        btn_enviarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogAgregarProductosLayout = new javax.swing.GroupLayout(jDialogAgregarProductos.getContentPane());
        jDialogAgregarProductos.getContentPane().setLayout(jDialogAgregarProductosLayout);
        jDialogAgregarProductosLayout.setHorizontalGroup(
            jDialogAgregarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAgregarProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAgregarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogAgregarProductosLayout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAgregarProductosLayout.createSequentialGroup()
                        .addGap(0, 53, Short.MAX_VALUE)
                        .addGroup(jDialogAgregarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAgregarProductosLayout.createSequentialGroup()
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAgregarProductosLayout.createSequentialGroup()
                                .addGroup(jDialogAgregarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jDialogAgregarProductosLayout.createSequentialGroup()
                                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_enviarProductosOrden))
                                    .addGroup(jDialogAgregarProductosLayout.createSequentialGroup()
                                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmb_productosPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(133, 133, 133))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAgregarProductosLayout.createSequentialGroup()
                                .addComponent(btn_enviarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(193, 193, 193))))))
        );
        jDialogAgregarProductosLayout.setVerticalGroup(
            jDialogAgregarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAgregarProductosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel65)
                .addGap(25, 25, 25)
                .addGroup(jDialogAgregarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(cmb_productosPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogAgregarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67)
                    .addComponent(btn_enviarProductosOrden))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_enviarPedido)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1100, 800));

        jPanelNavegacion.setBackground(new java.awt.Color(204, 204, 204));
        jPanelNavegacion.setPreferredSize(new java.awt.Dimension(270, 790));

        LogoYuyitos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/YUYITOS3.png"))); // NOI18N

        javax.swing.GroupLayout LogoYuyitosLayout = new javax.swing.GroupLayout(LogoYuyitos);
        LogoYuyitos.setLayout(LogoYuyitosLayout);
        LogoYuyitosLayout.setHorizontalGroup(
            LogoYuyitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        LogoYuyitosLayout.setVerticalGroup(
            LogoYuyitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );

        btn_irPnlTrabajadores.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_irPnlTrabajadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-icono.png"))); // NOI18N
        btn_irPnlTrabajadores.setText("Trabajadores");
        btn_irPnlTrabajadores.setAutoscrolls(true);
        btn_irPnlTrabajadores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_irPnlTrabajadores.setDefaultCapable(false);
        btn_irPnlTrabajadores.setFocusPainted(false);
        btn_irPnlTrabajadores.setIconTextGap(9);
        btn_irPnlTrabajadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlTrabajadoresActionPerformed(evt);
            }
        });

        btn_IrPnlUsuarios.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_IrPnlUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/usuario-icono.png"))); // NOI18N
        btn_IrPnlUsuarios.setText("Usuarios");
        btn_IrPnlUsuarios.setIconTextGap(8);
        btn_IrPnlUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IrPnlUsuariosActionPerformed(evt);
            }
        });

        btn_irPnlPedidos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_irPnlPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-icono.png"))); // NOI18N
        btn_irPnlPedidos.setText("Pedidos");
        btn_irPnlPedidos.setIconTextGap(8);
        btn_irPnlPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlPedidosActionPerformed(evt);
            }
        });

        btn_irPnlProveedor.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_irPnlProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/productos-icono.png"))); // NOI18N
        btn_irPnlProveedor.setText("Proveedor");
        btn_irPnlProveedor.setIconTextGap(9);
        btn_irPnlProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlProveedorActionPerformed(evt);
            }
        });

        btn_cerrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cerrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/salir-icono1.png"))); // NOI18N
        btn_cerrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_cerrar.setBorderPainted(false);
        btn_cerrar.setContentAreaFilled(false);
        btn_cerrar.setDefaultCapable(false);
        btn_cerrar.setFocusPainted(false);
        btn_cerrar.setPreferredSize(new java.awt.Dimension(96, 96));
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNavegacionLayout = new javax.swing.GroupLayout(jPanelNavegacion);
        jPanelNavegacion.setLayout(jPanelNavegacionLayout);
        jPanelNavegacionLayout.setHorizontalGroup(
            jPanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavegacionLayout.createSequentialGroup()
                .addGroup(jPanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNavegacionLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LogoYuyitos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_irPnlTrabajadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_IrPnlUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_irPnlPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_irPnlProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelNavegacionLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanelNavegacionLayout.setVerticalGroup(
            jPanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavegacionLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(LogoYuyitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btn_irPnlTrabajadores, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_IrPnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_irPnlPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_irPnlProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pnl_principal.setPreferredSize(new java.awt.Dimension(827, 600));

        javax.swing.GroupLayout pnl_principalLayout = new javax.swing.GroupLayout(pnl_principal);
        pnl_principal.setLayout(pnl_principalLayout);
        pnl_principalLayout.setHorizontalGroup(
            pnl_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        pnl_principalLayout.setVerticalGroup(
            pnl_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajadores-chico.png"))); // NOI18N
        jLabel1.setText("Trabajadores");

        btn_irPnlAgregarTrabajador.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlAgregarTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-agregar.png"))); // NOI18N
        btn_irPnlAgregarTrabajador.setText("Agregar");
        btn_irPnlAgregarTrabajador.setIconTextGap(8);
        btn_irPnlAgregarTrabajador.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlAgregarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlAgregarTrabajadorActionPerformed(evt);
            }
        });

        btn_irPnlModificarTrabajador.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlModificarTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-modificar.png"))); // NOI18N
        btn_irPnlModificarTrabajador.setText("Modificar");
        btn_irPnlModificarTrabajador.setIconTextGap(8);
        btn_irPnlModificarTrabajador.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlModificarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlModificarTrabajadorActionPerformed(evt);
            }
        });

        btn_irPnlEliminarTrabajador.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlEliminarTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-eliminar.png"))); // NOI18N
        btn_irPnlEliminarTrabajador.setText("Eliminar");
        btn_irPnlEliminarTrabajador.setIconTextGap(8);
        btn_irPnlEliminarTrabajador.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlEliminarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlEliminarTrabajadorActionPerformed(evt);
            }
        });

        btn_irPnlListarTrabajador.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlListarTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-listar.png"))); // NOI18N
        btn_irPnlListarTrabajador.setText("Listar");
        btn_irPnlListarTrabajador.setIconTextGap(8);
        btn_irPnlListarTrabajador.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlListarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlListarTrabajadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_trabajadorLayout = new javax.swing.GroupLayout(pnl_trabajador);
        pnl_trabajador.setLayout(pnl_trabajadorLayout);
        pnl_trabajadorLayout.setHorizontalGroup(
            pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_trabajadorLayout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addGroup(pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_trabajadorLayout.createSequentialGroup()
                        .addComponent(btn_irPnlListarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_irPnlEliminarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_trabajadorLayout.createSequentialGroup()
                        .addComponent(btn_irPnlAgregarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(btn_irPnlModificarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_trabajadorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        pnl_trabajadorLayout.setVerticalGroup(
            pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_trabajadorLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_irPnlAgregarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_irPnlModificarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_irPnlListarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_irPnlEliminarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-agregar.png"))); // NOI18N
        jLabel2.setText("Agregar Trabajador");
        jLabel2.setIconTextGap(6);

        jLabel3.setText("RUT :  ");

        jLabel4.setText("Nombres :  ");

        jLabel5.setText("Apellido Paterno :  ");

        jLabel6.setText("Apellido Materno :  ");

        jLabel7.setText("Email :  ");

        jLabel8.setText("Telefono :  ");

        jLabel9.setText("Calle :  ");

        jLabel10.setText("Numero :  ");

        jLabel11.setText("Departamento:  ");

        jLabel12.setText("Villa/Poblacion:  ");

        jLabel13.setText("Comuna :  ");

        jLabel14.setText("Fecha de Contratacion: ");

        jLabel15.setText("-");

        txt_dv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dvActionPerformed(evt);
            }
        });

        txt_nombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombresActionPerformed(evt);
            }
        });

        txt_apePaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apePaternoActionPerformed(evt);
            }
        });

        cmb_comuna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cmb_comuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_comunaActionPerformed(evt);
            }
        });

        btn_nuevoTrabajador.setText("Agregar");
        btn_nuevoTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoTrabajadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_agregarTrabajadorLayout = new javax.swing.GroupLayout(pnl_agregarTrabajador);
        pnl_agregarTrabajador.setLayout(pnl_agregarTrabajadorLayout);
        pnl_agregarTrabajadorLayout.setHorizontalGroup(
            pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel10)
                        .addGap(91, 91, 91)
                        .addComponent(txt_dirNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel11)
                        .addGap(58, 58, 58)
                        .addComponent(txt_deptoNum, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel12)
                        .addGap(56, 56, 56)
                        .addComponent(txt_dirVilla, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(152, 152, 152)
                                .addComponent(txt_fono, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(41, 41, 41)))
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_apeMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                        .addComponent(txt_calle, javax.swing.GroupLayout.Alignment.TRAILING))))))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(txt_apePaterno))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dv, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(12, 12, 12)
                                .addComponent(dateFechaContratacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(cmb_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13))
                                .addGap(27, 27, 27)))
                        .addGap(117, 117, 117))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addComponent(btn_nuevoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(226, 226, 226))))
        );
        pnl_agregarTrabajadorLayout.setVerticalGroup(
            pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txt_dv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5))
                    .addComponent(txt_apePaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6))
                    .addComponent(txt_apeMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7))
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel8))
                    .addComponent(txt_fono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_calle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel10))
                    .addComponent(txt_dirNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel11))
                    .addComponent(txt_deptoNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel12))
                    .addComponent(txt_dirVilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14))
                    .addComponent(dateFechaContratacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(btn_nuevoTrabajador)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-buscar.png"))); // NOI18N
        jLabel18.setText("Listar Trabajadores");
        jLabel18.setIconTextGap(8);

        tbl_listarTrab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RUT", "NOMBRES", "A PATERNO", "A MATERNO", "EMAIL", "TELEFONO", "FECHA DE CONTRATACION"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_listarTrab);

        btn_listarTrab.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_listarTrab.setText("Listar");
        btn_listarTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listarTrabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_listarTrabajadorLayout = new javax.swing.GroupLayout(pnl_listarTrabajador);
        pnl_listarTrabajador.setLayout(pnl_listarTrabajadorLayout);
        pnl_listarTrabajadorLayout.setHorizontalGroup(
            pnl_listarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listarTrabajadorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(pnl_listarTrabajadorLayout.createSequentialGroup()
                .addGroup(pnl_listarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_listarTrabajadorLayout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(jLabel18))
                    .addGroup(pnl_listarTrabajadorLayout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(btn_listarTrab, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(299, Short.MAX_VALUE))
        );
        pnl_listarTrabajadorLayout.setVerticalGroup(
            pnl_listarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listarTrabajadorLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel18)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btn_listarTrab)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-eliminar.png"))); // NOI18N
        jLabel16.setText("Eliminar Trabajador");
        jLabel16.setIconTextGap(8);

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setText("Rut : ");

        cmb_elegirTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_elegirTrabActionPerformed(evt);
            }
        });

        btn_eliminarTrab.setText("Eliminar");
        btn_eliminarTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarTrabActionPerformed(evt);
            }
        });

        tbl_eliminarTrab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbl_eliminarTrab);

        javax.swing.GroupLayout pnl_eliminarTrabajadorLayout = new javax.swing.GroupLayout(pnl_eliminarTrabajador);
        pnl_eliminarTrabajador.setLayout(pnl_eliminarTrabajadorLayout);
        pnl_eliminarTrabajadorLayout.setHorizontalGroup(
            pnl_eliminarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_eliminarTrabajadorLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(pnl_eliminarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarTrabajadorLayout.createSequentialGroup()
                        .addComponent(btn_eliminarTrab)
                        .addGap(283, 283, 283))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarTrabajadorLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarTrabajadorLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarTrabajadorLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_elegirTrab, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223))))
        );
        pnl_eliminarTrabajadorLayout.setVerticalGroup(
            pnl_eliminarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_eliminarTrabajadorLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel16)
                .addGap(40, 40, 40)
                .addGroup(pnl_eliminarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_elegirTrab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_eliminarTrab)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-modificar.png"))); // NOI18N
        jLabel19.setText("Modificar Trabajador");

        javax.swing.GroupLayout pnl_modifTrabajadorLayout = new javax.swing.GroupLayout(pnl_modifTrabajador);
        pnl_modifTrabajador.setLayout(pnl_modifTrabajadorLayout);
        pnl_modifTrabajadorLayout.setHorizontalGroup(
            pnl_modifTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_modifTrabajadorLayout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel19)
                .addContainerGap(249, Short.MAX_VALUE))
        );
        pnl_modifTrabajadorLayout.setVerticalGroup(
            pnl_modifTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_modifTrabajadorLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel19)
                .addContainerGap(494, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/usuarios-iconos.png"))); // NOI18N
        jLabel20.setText("  Usuarios");

        btn_irPnlAgregarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlAgregarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-agregar.png"))); // NOI18N
        btn_irPnlAgregarUsuario.setText("Agregar");
        btn_irPnlAgregarUsuario.setIconTextGap(8);
        btn_irPnlAgregarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlAgregarUsuarioActionPerformed(evt);
            }
        });

        btn_irPnMmodificarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnMmodificarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-modificar.png"))); // NOI18N
        btn_irPnMmodificarUsuario.setText("Modificar");
        btn_irPnMmodificarUsuario.setIconTextGap(8);
        btn_irPnMmodificarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnMmodificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnMmodificarUsuarioActionPerformed(evt);
            }
        });

        btn_irPnlEliminarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-eliminar.png"))); // NOI18N
        btn_irPnlEliminarUsuario.setText("Eliminar");
        btn_irPnlEliminarUsuario.setIconTextGap(8);
        btn_irPnlEliminarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlEliminarUsuarioActionPerformed(evt);
            }
        });

        btn_irPnLlistarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnLlistarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-listar.png"))); // NOI18N
        btn_irPnLlistarUsuario.setText("Listar");
        btn_irPnLlistarUsuario.setIconTextGap(8);
        btn_irPnLlistarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnLlistarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnLlistarUsuarioActionPerformed(evt);
            }
        });

        btn_irPnlAprobarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlAprobarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-aceptar.png"))); // NOI18N
        btn_irPnlAprobarUsuario.setText("Aprobar");
        btn_irPnlAprobarUsuario.setIconTextGap(8);
        btn_irPnlAprobarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlAprobarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlAprobarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_usuarioLayout = new javax.swing.GroupLayout(pnl_usuario);
        pnl_usuario.setLayout(pnl_usuarioLayout);
        pnl_usuarioLayout.setHorizontalGroup(
            pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_usuarioLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btn_irPnlAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_irPnMmodificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_irPnLlistarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_usuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_irPnlAprobarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btn_irPnlEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
            .addGroup(pnl_usuarioLayout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_usuarioLayout.setVerticalGroup(
            pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_usuarioLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_irPnlAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_irPnMmodificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_irPnLlistarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_irPnlEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_irPnlAprobarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-agregar.png"))); // NOI18N
        jLabel21.setText("Agregar Usuario");

        jLabel32.setText("Nombre Usuario:  ");

        jLabel33.setText("Contraseña :  ");

        jLabel34.setText("Tipo de Usuario: ");

        jLabel35.setText("Rut Trabajador : ");

        btn_crearUsuario.setText("Aceptar");
        btn_crearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearUsuarioActionPerformed(evt);
            }
        });

        cmb_elegirTrab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_elegirTrab2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_agregarUsuarioLayout = new javax.swing.GroupLayout(pnl_agregarUsuario);
        pnl_agregarUsuario.setLayout(pnl_agregarUsuarioLayout);
        pnl_agregarUsuarioLayout.setHorizontalGroup(
            pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_agregarUsuarioLayout.createSequentialGroup()
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarUsuarioLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarUsuarioLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(63, 63, 63)
                        .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_nomUsuario)
                            .addComponent(txt_contraUsuario)
                            .addComponent(cmb_perfilUsuario, 0, 160, Short.MAX_VALUE)
                            .addComponent(cmb_elegirTrab2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(173, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarUsuarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_crearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(285, 285, 285))
        );
        pnl_agregarUsuarioLayout.setVerticalGroup(
            pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_agregarUsuarioLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32)
                    .addComponent(txt_nomUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33)
                    .addComponent(txt_contraUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addComponent(cmb_perfilUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(cmb_elegirTrab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(btn_crearUsuario)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-modificar.png"))); // NOI18N
        jLabel22.setText("Modificar Usuario");

        tbl_modificarUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID DE USUARIO", "NOMBRE USUARIO", "RUT USUARIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tbl_modificarUsuarios);

        cmb_elegirTrab3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_elegirTrab3ActionPerformed(evt);
            }
        });

        jLabel39.setText("Nombre Usuario:  ");

        jLabel40.setText("Contraseña :  ");

        jLabel42.setText("Rut Trabajador : ");

        btn_ModificarUsuario.setText("Modificar");

        javax.swing.GroupLayout pnl_modificarUsuarioLayout = new javax.swing.GroupLayout(pnl_modificarUsuario);
        pnl_modificarUsuario.setLayout(pnl_modificarUsuarioLayout);
        pnl_modificarUsuarioLayout.setHorizontalGroup(
            pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_modificarUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(241, 241, 241))
            .addGroup(pnl_modificarUsuarioLayout.createSequentialGroup()
                .addGroup(pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_modificarUsuarioLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addGroup(pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39))
                        .addGap(26, 26, 26)
                        .addGroup(pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_modificarContraUsu)
                            .addComponent(txt_modificarNomUsu)
                            .addComponent(cmb_elegirTrab3, 0, 170, Short.MAX_VALUE)))
                    .addGroup(pnl_modificarUsuarioLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_modificarUsuarioLayout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(btn_ModificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        pnl_modificarUsuarioLayout.setVerticalGroup(
            pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_modificarUsuarioLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel22)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39)
                    .addComponent(txt_modificarNomUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40)
                    .addComponent(txt_modificarContraUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmb_elegirTrab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(27, 27, 27)
                .addComponent(btn_ModificarUsuario)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-listar.png"))); // NOI18N
        jLabel23.setText("Listar Usuarios");

        tbl_listarUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Usuario ID", "Usuario Nombre", "Usuario RUT"
            }
        ));
        jScrollPane3.setViewportView(tbl_listarUsuarios);

        btn_usuListar.setText("Listar");
        btn_usuListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuListarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_listarUsuarioLayout = new javax.swing.GroupLayout(pnl_listarUsuario);
        pnl_listarUsuario.setLayout(pnl_listarUsuarioLayout);
        pnl_listarUsuarioLayout.setHorizontalGroup(
            pnl_listarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listarUsuarioLayout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addGroup(pnl_listarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_listarUsuarioLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_listarUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_listarUsuarioLayout.createSequentialGroup()
                        .addComponent(btn_usuListar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(309, 309, 309))))
        );
        pnl_listarUsuarioLayout.setVerticalGroup(
            pnl_listarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listarUsuarioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(btn_usuListar)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-aceptar.png"))); // NOI18N
        jLabel24.setText("Aprobar Usuario");

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel36.setText("Usuarios Inactivos");

        tbl_listarUsuarios3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tbl_listarUsuarios3);

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel37.setText("Usuarios Activos");

        tbl_listarUsuarios2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tbl_listarUsuarios2);

        btn_aprobarUsuario.setText("Aprobar Usuario");
        btn_aprobarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aprobarUsuarioActionPerformed(evt);
            }
        });

        cmb_usuarioInactivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnl_aprobarUsuarioLayout = new javax.swing.GroupLayout(pnl_aprobarUsuario);
        pnl_aprobarUsuario.setLayout(pnl_aprobarUsuarioLayout);
        pnl_aprobarUsuarioLayout.setHorizontalGroup(
            pnl_aprobarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_aprobarUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_aprobarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_aprobarUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_aprobarUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(305, 305, 305))
                    .addGroup(pnl_aprobarUsuarioLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel37)
                        .addContainerGap())))
            .addGroup(pnl_aprobarUsuarioLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(pnl_aprobarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_aprobarUsuarioLayout.createSequentialGroup()
                        .addComponent(cmb_usuarioInactivo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btn_aprobarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(pnl_aprobarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 151, Short.MAX_VALUE))
        );
        pnl_aprobarUsuarioLayout.setVerticalGroup(
            pnl_aprobarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_aprobarUsuarioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel36)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel37)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(pnl_aprobarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_aprobarUsuario)
                    .addComponent(cmb_usuarioInactivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-eliminar.png"))); // NOI18N
        jLabel25.setText("Eliminar Usuario");

        jLabel38.setText("Usuario :  ");

        cmb_eliminarUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tbl_usuEliminar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane6.setViewportView(tbl_usuEliminar);
        if (tbl_usuEliminar.getColumnModel().getColumnCount() > 0) {
            tbl_usuEliminar.getColumnModel().getColumn(2).setHeaderValue("Title 3");
        }

        btn_usuarioEliminar.setText("Eliminar");
        btn_usuarioEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarioEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_eliminarUsuarioLayout = new javax.swing.GroupLayout(pnl_eliminarUsuario);
        pnl_eliminarUsuario.setLayout(pnl_eliminarUsuarioLayout);
        pnl_eliminarUsuarioLayout.setHorizontalGroup(
            pnl_eliminarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_eliminarUsuarioLayout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addGroup(pnl_eliminarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarUsuarioLayout.createSequentialGroup()
                        .addGroup(pnl_eliminarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnl_eliminarUsuarioLayout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmb_eliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(295, 295, 295))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarUsuarioLayout.createSequentialGroup()
                        .addComponent(btn_usuarioEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(319, 319, 319))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarUsuarioLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146))))
        );
        pnl_eliminarUsuarioLayout.setVerticalGroup(
            pnl_eliminarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_eliminarUsuarioLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel25)
                .addGap(30, 30, 30)
                .addGroup(pnl_eliminarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel38)
                    .addComponent(cmb_eliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btn_usuarioEliminar)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/note_edit_12872.png"))); // NOI18N
        jLabel26.setText("  Pedidos");

        btn_irPnlAgregarPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlAgregarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-agregar.png"))); // NOI18N
        btn_irPnlAgregarPedido.setText("Realizar");
        btn_irPnlAgregarPedido.setIconTextGap(8);
        btn_irPnlAgregarPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlAgregarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlAgregarPedidoActionPerformed(evt);
            }
        });

        btn_irPnlModificarPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlModificarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-modificar.png"))); // NOI18N
        btn_irPnlModificarPedido.setText("Modificar");
        btn_irPnlModificarPedido.setIconTextGap(8);
        btn_irPnlModificarPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlModificarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlModificarPedidoActionPerformed(evt);
            }
        });

        btn_irPnlEliminarPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlEliminarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-eliminar.png"))); // NOI18N
        btn_irPnlEliminarPedido.setText("Eliminar");
        btn_irPnlEliminarPedido.setIconTextGap(8);
        btn_irPnlEliminarPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlEliminarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlEliminarPedidoActionPerformed(evt);
            }
        });

        btn_irPnlListarPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlListarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-listar.png"))); // NOI18N
        btn_irPnlListarPedido.setText("Listar");
        btn_irPnlListarPedido.setIconTextGap(8);
        btn_irPnlListarPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlListarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlListarPedidoActionPerformed(evt);
            }
        });

        btn_irPnlEstadoPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_irPnlEstadoPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-listar.png"))); // NOI18N
        btn_irPnlEstadoPedido.setText("Estado");
        btn_irPnlEstadoPedido.setIconTextGap(8);
        btn_irPnlEstadoPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_irPnlEstadoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlEstadoPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_pedidoLayout = new javax.swing.GroupLayout(pnl_pedido);
        pnl_pedido.setLayout(pnl_pedidoLayout);
        pnl_pedidoLayout.setHorizontalGroup(
            pnl_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btn_irPnlListarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_irPnlEliminarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnl_pedidoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btn_irPnlAgregarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btn_irPnlModificarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btn_irPnlEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
            .addGroup(pnl_pedidoLayout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_pedidoLayout.setVerticalGroup(
            pnl_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(pnl_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_irPnlAgregarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_irPnlModificarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_irPnlEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnl_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_irPnlListarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_irPnlEliminarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-agregar.png"))); // NOI18N
        jLabel27.setText("Solicitar Pedidos");

        jLabel61.setText("RUT Proveedor :  ");

        jLabel62.setText("RUT Trabajador :  ");

        btn_agregarProdPedido.setText("Agregar Productos");
        btn_agregarProdPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarProdPedidoActionPerformed(evt);
            }
        });

        jLabel64.setText("Fecha de Solicitud : ");

        javax.swing.GroupLayout pnl_pedidoAgregarLayout = new javax.swing.GroupLayout(pnl_pedidoAgregar);
        pnl_pedidoAgregar.setLayout(pnl_pedidoAgregarLayout);
        pnl_pedidoAgregarLayout.setHorizontalGroup(
            pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoAgregarLayout.createSequentialGroup()
                .addGroup(pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_pedidoAgregarLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_pedidoAgregarLayout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addGroup(pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62))
                        .addGap(104, 104, 104)
                        .addGroup(pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmb_rutTrabPedido, 0, 170, Short.MAX_VALUE)
                            .addComponent(cmb_rutProvPedido, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnl_pedidoAgregarLayout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jLabel64)
                        .addGap(20, 20, 20)
                        .addComponent(dateChOrdenPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_pedidoAgregarLayout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(btn_agregarProdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(239, Short.MAX_VALUE))
        );
        pnl_pedidoAgregarLayout.setVerticalGroup(
            pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoAgregarLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel61)
                    .addComponent(cmb_rutProvPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel62)
                    .addComponent(cmb_rutTrabPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel64)
                    .addComponent(dateChOrdenPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(btn_agregarProdPedido)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-modificar.png"))); // NOI18N
        jLabel28.setText("Modificar Pedido");

        javax.swing.GroupLayout pnl_pedidoModificarLayout = new javax.swing.GroupLayout(pnl_pedidoModificar);
        pnl_pedidoModificar.setLayout(pnl_pedidoModificarLayout);
        pnl_pedidoModificarLayout.setHorizontalGroup(
            pnl_pedidoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoModificarLayout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );
        pnl_pedidoModificarLayout.setVerticalGroup(
            pnl_pedidoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoModificarLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel28)
                .addContainerGap(512, Short.MAX_VALUE))
        );

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-estados.png"))); // NOI18N
        jLabel29.setText("Estado Pedidos");

        jLabel63.setText("Seleccionar Pedido : ");

        jLabel68.setText("Seleccionar Estado: ");

        btn_actulizarEstadoPedido.setText("Actualizar Estado");
        btn_actulizarEstadoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actulizarEstadoPedidoActionPerformed(evt);
            }
        });

        tbl_listarEstadoPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NUMERO ORDEN", "RUT PROVEEDOR", "RUT TRABAJADOR", "FECHA", "ESTADO"
            }
        ));
        jScrollPane13.setViewportView(tbl_listarEstadoPedidos);

        javax.swing.GroupLayout pnl_pedidoEstadoLayout = new javax.swing.GroupLayout(pnl_pedidoEstado);
        pnl_pedidoEstado.setLayout(pnl_pedidoEstadoLayout);
        pnl_pedidoEstadoLayout.setHorizontalGroup(
            pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoEstadoLayout.createSequentialGroup()
                .addGroup(pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_pedidoEstadoLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_pedidoEstadoLayout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addGroup(pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63)
                            .addComponent(jLabel68))
                        .addGap(68, 68, 68)
                        .addGroup(pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmb_estadoPedido, 0, 125, Short.MAX_VALUE)
                            .addComponent(cmb_selecPedido, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoEstadoLayout.createSequentialGroup()
                .addGap(0, 85, Short.MAX_VALUE)
                .addGroup(pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoEstadoLayout.createSequentialGroup()
                        .addComponent(btn_actulizarEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(296, 296, 296))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoEstadoLayout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
        );
        pnl_pedidoEstadoLayout.setVerticalGroup(
            pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoEstadoLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel63)
                    .addComponent(cmb_selecPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel68)
                    .addComponent(cmb_estadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btn_actulizarEstadoPedido)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-listar.png"))); // NOI18N
        jLabel30.setText("Listar Pedidos");

        tbl_listarPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NUMERO ORDEN", "RUT PROVEEDOR", "RUT TRABAJADOR", "FECHA", "ESTADO"
            }
        ));
        jScrollPane7.setViewportView(tbl_listarPedidos);

        btn_listarPedido.setText("Listar");
        btn_listarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_pedidoListarLayout = new javax.swing.GroupLayout(pnl_pedidoListar);
        pnl_pedidoListar.setLayout(pnl_pedidoListarLayout);
        pnl_pedidoListarLayout.setHorizontalGroup(
            pnl_pedidoListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoListarLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(pnl_pedidoListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoListarLayout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(234, 234, 234))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoListarLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
            .addGroup(pnl_pedidoListarLayout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(btn_listarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnl_pedidoListarLayout.setVerticalGroup(
            pnl_pedidoListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoListarLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btn_listarPedido)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-eliminar.png"))); // NOI18N
        jLabel31.setText("Eliminar Pedido");

        javax.swing.GroupLayout pnl_pedidoEliminarLayout = new javax.swing.GroupLayout(pnl_pedidoEliminar);
        pnl_pedidoEliminar.setLayout(pnl_pedidoEliminarLayout);
        pnl_pedidoEliminarLayout.setHorizontalGroup(
            pnl_pedidoEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoEliminarLayout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247))
        );
        pnl_pedidoEliminarLayout.setVerticalGroup(
            pnl_pedidoEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoEliminarLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(413, Short.MAX_VALUE))
        );

        btn_irPnlAgregarProv.setFont(new java.awt.Font("Arial", 2, 24)); // NOI18N
        btn_irPnlAgregarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/Producto.png"))); // NOI18N
        btn_irPnlAgregarProv.setText("Agregar ");
        btn_irPnlAgregarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlAgregarProvActionPerformed(evt);
            }
        });

        btn_irPnlListarProv.setFont(new java.awt.Font("Arial", 2, 24)); // NOI18N
        btn_irPnlListarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-buscar.png"))); // NOI18N
        btn_irPnlListarProv.setText("Listar");
        btn_irPnlListarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlListarProvActionPerformed(evt);
            }
        });

        btn_irPnlEliminarProv.setFont(new java.awt.Font("Arial", 2, 24)); // NOI18N
        btn_irPnlEliminarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/Basurero Vacio.png"))); // NOI18N
        btn_irPnlEliminarProv.setText("Eliminar");
        btn_irPnlEliminarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlEliminarProvActionPerformed(evt);
            }
        });

        lbl_proveedor.setBackground(new java.awt.Color(102, 102, 102));
        lbl_proveedor.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbl_proveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/productos.png"))); // NOI18N
        lbl_proveedor.setText("Proveedor");

        javax.swing.GroupLayout pnl_proveedorLayout = new javax.swing.GroupLayout(pnl_proveedor);
        pnl_proveedor.setLayout(pnl_proveedorLayout);
        pnl_proveedorLayout.setHorizontalGroup(
            pnl_proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_proveedorLayout.createSequentialGroup()
                .addGroup(pnl_proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_proveedorLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btn_irPnlAgregarProv)
                        .addGap(18, 18, 18)
                        .addComponent(btn_irPnlListarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_irPnlEliminarProv))
                    .addGroup(pnl_proveedorLayout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(lbl_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        pnl_proveedorLayout.setVerticalGroup(
            pnl_proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_proveedorLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lbl_proveedor)
                .addGap(70, 70, 70)
                .addGroup(pnl_proveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_irPnlAgregarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_irPnlListarProv)
                    .addComponent(btn_irPnlEliminarProv))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jLabel43.setText("Email :  ");

        jLabel44.setText("Telefono :  ");

        jLabel45.setText("Calle :  ");

        jLabel46.setText("Numero :  ");

        jLabel49.setText("Comuna :  ");

        jLabel50.setText("-");

        txt_dvProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dvProveedorActionPerformed(evt);
            }
        });

        txt_nombreProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreProveedorActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-agregar.png"))); // NOI18N
        jLabel51.setText("Agregar Proveedor");
        jLabel51.setIconTextGap(6);

        jLabel52.setText("RUT :  ");

        jLabel53.setText("Nombre :  ");

        cmb_comuna2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cmb_comuna2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_comuna2ActionPerformed(evt);
            }
        });

        btn_proveedorAgregar.setText("Agregar");
        btn_proveedorAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_proveedorAgregarActionPerformed(evt);
            }
        });

        jLabel41.setText("Usuario : ");

        btn_pnlCrearUserProv.setText("Crear Nuevo Usuario");
        btn_pnlCrearUserProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pnlCrearUserProvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_proveedorAgregarLayout = new javax.swing.GroupLayout(pnl_proveedorAgregar);
        pnl_proveedorAgregar.setLayout(pnl_proveedorAgregarLayout);
        pnl_proveedorAgregarLayout.setHorizontalGroup(
            pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_proveedorAgregarLayout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorAgregarLayout.createSequentialGroup()
                        .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorAgregarLayout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_rutProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dvProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorAgregarLayout.createSequentialGroup()
                                .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel44)
                                        .addGroup(pnl_proveedorAgregarLayout.createSequentialGroup()
                                            .addComponent(jLabel43)
                                            .addGap(18, 18, 18)))
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel41))
                                .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_proveedorAgregarLayout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_pnlCrearUserProv, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cmb_usuarioProveedor, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txt_fonoProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(pnl_proveedorAgregarLayout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_dirNumProveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txt_calleProveedor)
                                            .addComponent(txt_emailProveedor)))))
                            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_nombreProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorAgregarLayout.createSequentialGroup()
                        .addComponent(btn_proveedorAgregar)
                        .addGap(260, 260, 260))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorAgregarLayout.createSequentialGroup()
                        .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnl_proveedorAgregarLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(cmb_comuna2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel49))
                        .addGap(179, 179, 179))))
        );
        pnl_proveedorAgregarLayout.setVerticalGroup(
            pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorAgregarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_proveedorAgregarLayout.createSequentialGroup()
                        .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_rutProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50)
                            .addComponent(txt_dvProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_nombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel53))
                .addGap(15, 15, 15)
                .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorAgregarLayout.createSequentialGroup()
                        .addComponent(txt_emailProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_fonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorAgregarLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel44)))
                .addGap(15, 15, 15)
                .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel41)
                    .addComponent(cmb_usuarioProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pnlCrearUserProv)
                .addGap(20, 20, 20)
                .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_calleProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_proveedorAgregarLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel46))
                    .addComponent(txt_dirNumProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_proveedorAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_comuna2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(33, 33, 33)
                .addComponent(btn_proveedorAgregar)
                .addGap(73, 73, 73))
        );

        jLabel58.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-buscar.png"))); // NOI18N
        jLabel58.setText("Listar Proveedores");

        tbl_listarProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "RUT PROVEEDOR", "NOMBRE PROVEEDOR", "TELEFONO", "EMAIL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tbl_listarProveedor);

        btn_listarProveedor.setText("Listar");
        btn_listarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listarProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_proveedorListarLayout = new javax.swing.GroupLayout(pnl_proveedorListar);
        pnl_proveedorListar.setLayout(pnl_proveedorListarLayout);
        pnl_proveedorListarLayout.setHorizontalGroup(
            pnl_proveedorListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_proveedorListarLayout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorListarLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(pnl_proveedorListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorListarLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorListarLayout.createSequentialGroup()
                        .addComponent(btn_listarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(292, 292, 292))))
        );
        pnl_proveedorListarLayout.setVerticalGroup(
            pnl_proveedorListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_proveedorListarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btn_listarProveedor)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        btn_proveedorEliminar.setText("Eliminar");
        btn_proveedorEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_proveedorEliminarActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-eliminar.png"))); // NOI18N
        jLabel59.setText("Eliminar Proveedor");

        jLabel60.setText("Proveedor  :  ");

        cmb_eliminarProveedor.setAutoscrolls(true);

        tbl_proveedorEliminar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane10.setViewportView(tbl_proveedorEliminar);

        javax.swing.GroupLayout pnl_proveedorEliminarLayout = new javax.swing.GroupLayout(pnl_proveedorEliminar);
        pnl_proveedorEliminar.setLayout(pnl_proveedorEliminarLayout);
        pnl_proveedorEliminarLayout.setHorizontalGroup(
            pnl_proveedorEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorEliminarLayout.createSequentialGroup()
                .addContainerGap(271, Short.MAX_VALUE)
                .addGroup(pnl_proveedorEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorEliminarLayout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_proveedorEliminarLayout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(32, 32, 32)
                        .addComponent(cmb_eliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(242, 242, 242))))
            .addGroup(pnl_proveedorEliminarLayout.createSequentialGroup()
                .addGroup(pnl_proveedorEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_proveedorEliminarLayout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(btn_proveedorEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_proveedorEliminarLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnl_proveedorEliminarLayout.setVerticalGroup(
            pnl_proveedorEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_proveedorEliminarLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(pnl_proveedorEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_eliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btn_proveedorEliminar)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnl_principal, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(332, 332, 332)
                    .addComponent(pnl_trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(167, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(336, 336, 336)
                    .addComponent(pnl_agregarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(203, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(296, Short.MAX_VALUE)
                    .addComponent(pnl_listarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(289, Short.MAX_VALUE)
                    .addComponent(pnl_modifTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(134, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(423, 423, 423)
                    .addComponent(pnl_eliminarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(96, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(322, Short.MAX_VALUE)
                    .addComponent(pnl_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(156, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(342, Short.MAX_VALUE)
                    .addComponent(pnl_agregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(196, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(403, Short.MAX_VALUE)
                    .addComponent(pnl_modificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(277, Short.MAX_VALUE)
                    .addComponent(pnl_listarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(148, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(319, Short.MAX_VALUE)
                    .addComponent(pnl_aprobarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(125, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(248, Short.MAX_VALUE)
                    .addComponent(pnl_eliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(110, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(307, Short.MAX_VALUE)
                    .addComponent(pnl_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(148, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(271, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(77, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(335, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(179, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(305, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(129, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(325, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(177, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(285, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(337, Short.MAX_VALUE)
                    .addComponent(pnl_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(95, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(301, Short.MAX_VALUE)
                    .addComponent(pnl_proveedorAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(192, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(289, Short.MAX_VALUE)
                    .addComponent(pnl_proveedorListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(172, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(264, Short.MAX_VALUE)
                    .addComponent(pnl_proveedorEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(161, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnl_principal, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(pnl_trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(200, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(pnl_agregarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(19, Short.MAX_VALUE)
                    .addComponent(pnl_listarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(121, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(41, Short.MAX_VALUE)
                    .addComponent(pnl_modifTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(123, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(113, 113, 113)
                    .addComponent(pnl_eliminarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(144, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .addComponent(pnl_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(241, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(34, Short.MAX_VALUE)
                    .addComponent(pnl_agregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(252, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addComponent(pnl_modificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(267, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_listarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(228, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(51, Short.MAX_VALUE)
                    .addComponent(pnl_aprobarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(79, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(22, Short.MAX_VALUE)
                    .addComponent(pnl_eliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(119, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(31, Short.MAX_VALUE)
                    .addComponent(pnl_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(172, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(28, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(257, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(65, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(73, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(9, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(111, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(63, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(150, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(72, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(168, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(66, Short.MAX_VALUE)
                    .addComponent(pnl_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(228, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(7, Short.MAX_VALUE)
                    .addComponent(pnl_proveedorAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(139, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(25, Short.MAX_VALUE)
                    .addComponent(pnl_proveedorListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(144, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(30, Short.MAX_VALUE)
                    .addComponent(pnl_proveedorEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(160, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_irPnlPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlPedidosActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedido.setVisible(true);       
                
    }//GEN-LAST:event_btn_irPnlPedidosActionPerformed

    private void btn_irPnlProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlProveedorActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_proveedor.setVisible(true);      
        
    }//GEN-LAST:event_btn_irPnlProveedorActionPerformed

    private void btn_irPnlTrabajadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlTrabajadoresActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_trabajador.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlTrabajadoresActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:        
        this.dispose(); 
        Login login = new Login();
        login.setVisible(true);
        CerrarConexion();       
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_irPnlAgregarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlAgregarTrabajadorActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_agregarTrabajador.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlAgregarTrabajadorActionPerformed

    private void btn_irPnlModificarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlModificarTrabajadorActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_modifTrabajador.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlModificarTrabajadorActionPerformed

    private void btn_irPnlEliminarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlEliminarTrabajadorActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_eliminarTrabajador.setVisible(true);
        
        try {
            ResultSet res = CtrlTrabajador.ListarTrab();
            
            tbl_eliminarTrab.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btn_irPnlEliminarTrabajadorActionPerformed

    private void btn_irPnlListarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlListarTrabajadorActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_listarTrabajador.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlListarTrabajadorActionPerformed

    private void txt_nombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombresActionPerformed

    private void txt_dvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dvActionPerformed

    private void cmb_comunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_comunaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cmb_comunaActionPerformed

    private void btn_nuevoTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoTrabajadorActionPerformed
        // TODO add your handling code here:
        java.sql.Date sqlDate = null;
        sqlDate = ConvertirFecha(dateFechaContratacion.getSelectedDate().getTime());
        
        CtrlTrabajador.AgregarTrabajador(txt_rut.getText(), txt_dv.getText(), txt_nombres.getText(), txt_apePaterno.getText(), 
                                        txt_apeMaterno.getText(), txt_email.getText(), txt_fono.getText(), sqlDate, txt_calle.getText(), 
                                        txt_dirNumero.getText(), txt_deptoNum.getText(), txt_dirVilla.getText(), cmb_comuna.getSelectedItem().toString());
        
        CerrarConexion();        
    }//GEN-LAST:event_btn_nuevoTrabajadorActionPerformed

    private void txt_apePaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apePaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apePaternoActionPerformed

    private void cmb_elegirTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_elegirTrabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_elegirTrabActionPerformed

    private void btn_eliminarTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarTrabActionPerformed
        // TODO add your handling code here:
        CtrlTrabajador.EliminarTrabajador(cmb_elegirTrab.getSelectedItem().toString());
        
        try {
            ResultSet res = CtrlTrabajador.ListarTrab();
            
            tbl_eliminarTrab.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        LlenarComboTrabajadores();
    }//GEN-LAST:event_btn_eliminarTrabActionPerformed

    private void btn_listarTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listarTrabActionPerformed
        // TODO add your handling code here:
        try {
            ResultSet res = CtrlTrabajador.ListarTrab();
            
            tbl_listarTrab.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btn_listarTrabActionPerformed

    private void btn_irPnlAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlAgregarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_agregarUsuario.setVisible(true);        
        
    }//GEN-LAST:event_btn_irPnlAgregarUsuarioActionPerformed

    private void btn_irPnMmodificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnMmodificarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_modificarUsuario.setVisible(true);
        
        try {
            ResultSet res = CtrlUsuario.ListarUsuario();
            
            tbl_modificarUsuarios.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
//        int row = tbl_modificarUsuarios.getSelectedRow();
//        
//        String id = tbl_modificarUsuarios.getValueAt(row,0).toString();
//        String nombre = tbl_modificarUsuarios.getValueAt(row,2).toString();
//        
//        txt_modificarNomUsu.setText(id);
//        txt_modificarContraUsu.setText(nombre);
        
        
    }//GEN-LAST:event_btn_irPnMmodificarUsuarioActionPerformed

    private void btn_irPnlEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlEliminarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_eliminarUsuario.setVisible(true);
        
        try {
            ResultSet res = CtrlUsuario.ListarUsuario();
            
            tbl_usuEliminar.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        LlenarComboUsuarioActivo();        
    }//GEN-LAST:event_btn_irPnlEliminarUsuarioActionPerformed

    private void btn_irPnLlistarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnLlistarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_listarUsuario.setVisible(true);
   
    }//GEN-LAST:event_btn_irPnLlistarUsuarioActionPerformed

    private void btn_irPnlAprobarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlAprobarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_aprobarUsuario.setVisible(true);
        
        try {
            ResultSet res = CtrlUsuario.ListarUsuario();
            
            tbl_listarUsuarios2.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            ResultSet res = CtrlUsuario.ListarUsuarioInactivo();
            
            tbl_listarUsuarios3.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        LlenarComboUsuarioInactivo();
        LlenarComboUsuarioActivo();
        
    }//GEN-LAST:event_btn_irPnlAprobarUsuarioActionPerformed

    private void btn_IrPnlUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IrPnlUsuariosActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_usuario.setVisible(true);
        
    }//GEN-LAST:event_btn_IrPnlUsuariosActionPerformed

    private void btn_irPnlAgregarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlAgregarPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoAgregar.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlAgregarPedidoActionPerformed

    private void btn_irPnlModificarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlModificarPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoModificar.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlModificarPedidoActionPerformed

    private void btn_irPnlEliminarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlEliminarPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoEliminar.setVisible(true);
    }//GEN-LAST:event_btn_irPnlEliminarPedidoActionPerformed

    private void btn_irPnlListarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlListarPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoListar.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlListarPedidoActionPerformed

    private void btn_irPnlEstadoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlEstadoPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoEstado.setVisible(true);
        
        try {
            ResultSet res = CtrlPedido.ListarPedidos();
            
            tbl_listarEstadoPedidos.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btn_irPnlEstadoPedidoActionPerformed

    private void btn_crearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearUsuarioActionPerformed
        // TODO add your handling code here:
        int perfilID = 0;
        
        if ("Administrador".equals(this.cmb_perfilUsuario.getSelectedItem().toString())) {
            perfilID = 1;            
        }else{
            if ("Vendedor".equals(this.cmb_perfilUsuario.getSelectedItem().toString())) {
            perfilID = 2;
        }else{
            if ("Proveedor".equals(this.cmb_perfilUsuario.getSelectedItem().toString())) {
            perfilID = 3;
        }else{
            if ("Supervisor".equals(this.cmb_perfilUsuario.getSelectedItem().toString())){
            perfilID = 4;
        }
            }
           }
        }  
        
        CtrlUsuario.AgregarUsuario(txt_nomUsuario.getText(), txt_contraUsuario.getText(), 
                                   cmb_elegirTrab2.getSelectedItem().toString(), perfilID);
        txt_nomUsuario.setText("");
        txt_contraUsuario.setText("");
        
        LlenarComboPerfil();
        LlenarComboTrabajadores();
        CerrarConexion();      
    }//GEN-LAST:event_btn_crearUsuarioActionPerformed

    private void cmb_elegirTrab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_elegirTrab2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_elegirTrab2ActionPerformed

    private void btn_usuListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuListarActionPerformed
        // TODO add your handling code here:
        try {
            ResultSet res = CtrlUsuario.ListarUsuario();
            
            tbl_listarUsuarios.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        CerrarConexion();
    }//GEN-LAST:event_btn_usuListarActionPerformed

    private void btn_aprobarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aprobarUsuarioActionPerformed
        // TODO add your handling code here:
        CtrlUsuario.ActivarUsuario(cmb_usuarioInactivo.getSelectedItem().toString());
        
        try {
            ResultSet res = CtrlUsuario.ListarUsuario();
            
            tbl_listarUsuarios2.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            ResultSet res = CtrlUsuario.ListarUsuarioInactivo();
            
            tbl_listarUsuarios3.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        CerrarConexion();
        
    }//GEN-LAST:event_btn_aprobarUsuarioActionPerformed

    private void btn_usuarioEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarioEliminarActionPerformed
        // TODO add your handling code here:
        CtrlUsuario.EliminarUsuario(cmb_eliminarUsuario.getSelectedItem().toString());
        
        try {
            ResultSet res = CtrlUsuario.ListarUsuario();
            
            tbl_usuEliminar.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        LlenarComboUsuarioActivo();
        CerrarConexion();
    }//GEN-LAST:event_btn_usuarioEliminarActionPerformed

    private void btn_listarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listarPedidoActionPerformed
        // TODO add your handling code here:
        try {
            ResultSet res = CtrlPedido.ListarPedidos();
            
            tbl_listarPedidos.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_listarPedidoActionPerformed

    private void cmb_elegirTrab3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_elegirTrab3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_elegirTrab3ActionPerformed

    private void btn_irPnlAgregarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlAgregarProvActionPerformed
        OcultarPaneles();
        pnl_proveedorAgregar.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlAgregarProvActionPerformed

    private void btn_irPnlListarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlListarProvActionPerformed
        OcultarPaneles();
        pnl_proveedorListar.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlListarProvActionPerformed

    private void btn_irPnlEliminarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlEliminarProvActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_proveedorEliminar.setVisible(true);
        
        try {
            ResultSet res = CtrlProveedor.ListarProveedor();
            
            tbl_proveedorEliminar.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btn_irPnlEliminarProvActionPerformed

    private void txt_dvProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dvProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dvProveedorActionPerformed

    private void txt_nombreProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreProveedorActionPerformed

    private void cmb_comuna2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_comuna2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_comuna2ActionPerformed

    private void btn_pnlCrearUserProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pnlCrearUserProvActionPerformed
        // TODO add your handling code here:
        jDialogCrearUserProv.setVisible(true);        
    }//GEN-LAST:event_btn_pnlCrearUserProvActionPerformed

    private void btn_proveedorAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_proveedorAgregarActionPerformed
        // TODO add your handling code here:
        
        
        CtrlProveedor.AgregarProveedor(txt_rutProveedor.getText(), txt_dvProveedor.getText(), txt_nombreProveedor.getText(), txt_emailProveedor.getText(), 
                                       txt_fonoProveedor.getText(), cmb_usuarioProveedor.getSelectedItem().toString(), txt_calleProveedor.getText(), txt_dirNumProveedor.getText(), cmb_comuna2.getSelectedItem().toString());
        
        LlenarComboUsuarioActivo();
        LlenarComboComunas();
        
        txt_rutProveedor.setText("");
        txt_dvProveedor.setText(""); 
        txt_nombreProveedor.setText("");
        txt_emailProveedor.setText("");
        txt_fonoProveedor.setText("");
        txt_calleProveedor.setText("");
        txt_dirNumProveedor.setText("");
        
        CerrarConexion();
    }//GEN-LAST:event_btn_proveedorAgregarActionPerformed

    private void btn_crearUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearUsuario1ActionPerformed
        // TODO add your handling code here:
        int perfilID = 0;
        
        if ("Administrador".equals(this.cmb_perfilUsuProv.getSelectedItem().toString())) {
            perfilID = 1;            
        }else{
            if ("Vendedor".equals(this.cmb_perfilUsuProv.getSelectedItem().toString())) {
            perfilID = 2;
        }else{
            if ("Proveedor".equals(this.cmb_perfilUsuProv.getSelectedItem().toString())) {
            perfilID = 3;
        }else{
            if ("Supervisor".equals(this.cmb_perfilUsuProv.getSelectedItem().toString())){
            perfilID = 4;
        }
            }
           }
        }  
        
        CtrlUsuario.AgregarUsuario(txt_nomUsuProv.getText(), txt_contraUsuProv.getText(), 
                                   txt_rutUsuProv.getText(), perfilID);
        txt_nomUsuProv.setText("");
        txt_contraUsuProv.setText("");
        txt_rutUsuProv.setText("");        
        LlenarComboPerfil();
        LlenarComboUsuarioActivo();
        CerrarConexion();
        jDialogCrearUserProv.dispose();
        
        CerrarConexion();
          
    }//GEN-LAST:event_btn_crearUsuario1ActionPerformed

    private void btn_proveedorEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_proveedorEliminarActionPerformed
        // TODO add your handling code here:
        CtrlProveedor.EliminarProveedor(cmb_eliminarProveedor.getSelectedItem().toString());
        LlenarCmbProveedorAct();
        
        try {
            ResultSet res = CtrlProveedor.ListarProveedor();
            
            tbl_proveedorEliminar.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        CerrarConexion();
    }//GEN-LAST:event_btn_proveedorEliminarActionPerformed

    private void btn_listarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listarProveedorActionPerformed
        // TODO add your handling code here:
        try {
            ResultSet res = CtrlProveedor.ListarProveedor();
            
            tbl_listarProveedor.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        CerrarConexion();
    }//GEN-LAST:event_btn_listarProveedorActionPerformed

    private void btn_enviarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarPedidoActionPerformed
        // TODO add your handling code here:
        
        final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Pedido Solicitado Exitosamente");
        jDialogAgregarProductos.dispose();
        
        LlenarCmbProveedorAct();
        LlenarComboTrabajadores();
    }//GEN-LAST:event_btn_enviarPedidoActionPerformed

    private void btn_agregarProdPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarProdPedidoActionPerformed
        // TODO add your handling code here:
        java.sql.Date sqlDate;
        sqlDate = ConvertirFecha(dateChOrdenPedido.getSelectedDate().getTime());
        CtrlPedido.AgregarPedido(cmb_rutProvPedido.getSelectedItem().toString(), cmb_rutTrabPedido.getSelectedItem().toString(), sqlDate, sqlDate);
        jDialogAgregarProductos.setVisible(true);
    }//GEN-LAST:event_btn_agregarProdPedidoActionPerformed

    private void btn_enviarProductosOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarProductosOrdenActionPerformed
        // TODO add your handling code here:
        CtrlPedido.AgregarProductosPedido(txt_cantidad.getText(), cmb_productosPedido.getSelectedItem().toString());
        
        try {
            ResultSet res = CtrlPedido.ListarProductos();
            
            tbl_productosPedido.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        txt_cantidad.setText("");
        LlenarCmbProducto();        
        
    }//GEN-LAST:event_btn_enviarProductosOrdenActionPerformed

    private void btn_actulizarEstadoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actulizarEstadoPedidoActionPerformed
        // TODO add your handling code here:
        CtrlPedido.ModificarEstadoPedido(cmb_selecPedido.getSelectedItem().toString(), cmb_estadoPedido.getSelectedItem().toString());
        
        try {
            ResultSet res = CtrlPedido.ListarPedidos();
            
            tbl_listarEstadoPedidos.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_actulizarEstadoPedidoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage LogoYuyitos;
    private javax.swing.JButton btn_IrPnlUsuarios;
    private javax.swing.JButton btn_ModificarUsuario;
    private javax.swing.JButton btn_actulizarEstadoPedido;
    private javax.swing.JButton btn_agregarProdPedido;
    private javax.swing.JButton btn_aprobarUsuario;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_crearUsuario;
    private javax.swing.JButton btn_crearUsuario1;
    private javax.swing.JButton btn_eliminarTrab;
    private javax.swing.JButton btn_enviarPedido;
    private javax.swing.JButton btn_enviarProductosOrden;
    private javax.swing.JButton btn_irPnLlistarUsuario;
    private javax.swing.JButton btn_irPnMmodificarUsuario;
    private javax.swing.JButton btn_irPnlAgregarPedido;
    private javax.swing.JButton btn_irPnlAgregarProv;
    private javax.swing.JButton btn_irPnlAgregarTrabajador;
    private javax.swing.JButton btn_irPnlAgregarUsuario;
    private javax.swing.JButton btn_irPnlAprobarUsuario;
    private javax.swing.JButton btn_irPnlEliminarPedido;
    private javax.swing.JButton btn_irPnlEliminarProv;
    private javax.swing.JButton btn_irPnlEliminarTrabajador;
    private javax.swing.JButton btn_irPnlEliminarUsuario;
    private javax.swing.JButton btn_irPnlEstadoPedido;
    private javax.swing.JButton btn_irPnlListarPedido;
    private javax.swing.JButton btn_irPnlListarProv;
    private javax.swing.JButton btn_irPnlListarTrabajador;
    private javax.swing.JButton btn_irPnlModificarPedido;
    private javax.swing.JButton btn_irPnlModificarTrabajador;
    private javax.swing.JButton btn_irPnlPedidos;
    private javax.swing.JButton btn_irPnlProveedor;
    private javax.swing.JButton btn_irPnlTrabajadores;
    private javax.swing.JButton btn_listarPedido;
    private javax.swing.JButton btn_listarProveedor;
    private javax.swing.JButton btn_listarTrab;
    private javax.swing.JButton btn_nuevoTrabajador;
    private javax.swing.JButton btn_pnlCrearUserProv;
    private javax.swing.JButton btn_proveedorAgregar;
    private javax.swing.JButton btn_proveedorEliminar;
    private javax.swing.JButton btn_usuListar;
    private javax.swing.JButton btn_usuarioEliminar;
    private javax.swing.JComboBox<String> cmb_comuna;
    private javax.swing.JComboBox<String> cmb_comuna2;
    private javax.swing.JComboBox<String> cmb_elegirTrab;
    private javax.swing.JComboBox<String> cmb_elegirTrab2;
    private javax.swing.JComboBox<String> cmb_elegirTrab3;
    private javax.swing.JComboBox<String> cmb_eliminarProveedor;
    private javax.swing.JComboBox<String> cmb_eliminarUsuario;
    private javax.swing.JComboBox<String> cmb_estadoPedido;
    private javax.swing.JComboBox<String> cmb_perfilUsuProv;
    private javax.swing.JComboBox<String> cmb_perfilUsuario;
    private javax.swing.JComboBox<String> cmb_productosPedido;
    private javax.swing.JComboBox<String> cmb_rutProvPedido;
    private javax.swing.JComboBox<String> cmb_rutTrabPedido;
    private javax.swing.JComboBox<String> cmb_selecPedido;
    private javax.swing.JComboBox<String> cmb_usuarioInactivo;
    private javax.swing.JComboBox<String> cmb_usuarioProveedor;
    private datechooser.beans.DateChooserCombo dateChOrdenPedido;
    private datechooser.beans.DateChooserCombo dateFechaContratacion;
    private javax.swing.JDialog jDialogAgregarProductos;
    private javax.swing.JDialog jDialogCrearUserProv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelNavegacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lbl_proveedor;
    private javax.swing.JPanel pnl_agregarTrabajador;
    private javax.swing.JPanel pnl_agregarUsuario;
    private javax.swing.JPanel pnl_aprobarUsuario;
    private javax.swing.JPanel pnl_eliminarTrabajador;
    private javax.swing.JPanel pnl_eliminarUsuario;
    private javax.swing.JPanel pnl_listarTrabajador;
    private javax.swing.JPanel pnl_listarUsuario;
    private javax.swing.JPanel pnl_modifTrabajador;
    private javax.swing.JPanel pnl_modificarUsuario;
    private javax.swing.JPanel pnl_pedido;
    private javax.swing.JPanel pnl_pedidoAgregar;
    private javax.swing.JPanel pnl_pedidoEliminar;
    private javax.swing.JPanel pnl_pedidoEstado;
    private javax.swing.JPanel pnl_pedidoListar;
    private javax.swing.JPanel pnl_pedidoModificar;
    private javax.swing.JPanel pnl_principal;
    private javax.swing.JPanel pnl_proveedor;
    private javax.swing.JPanel pnl_proveedorAgregar;
    private javax.swing.JPanel pnl_proveedorEliminar;
    private javax.swing.JPanel pnl_proveedorListar;
    private javax.swing.JPanel pnl_trabajador;
    private javax.swing.JPanel pnl_usuario;
    private javax.swing.JTable tbl_eliminarTrab;
    private javax.swing.JTable tbl_listarEstadoPedidos;
    private javax.swing.JTable tbl_listarPedidos;
    private javax.swing.JTable tbl_listarProveedor;
    private javax.swing.JTable tbl_listarTrab;
    private javax.swing.JTable tbl_listarUsuarios;
    private javax.swing.JTable tbl_listarUsuarios2;
    private javax.swing.JTable tbl_listarUsuarios3;
    private javax.swing.JTable tbl_modificarUsuarios;
    private javax.swing.JTable tbl_productosPedido;
    private javax.swing.JTable tbl_proveedorEliminar;
    private javax.swing.JTable tbl_usuEliminar;
    private javax.swing.JTextField txt_apeMaterno;
    private javax.swing.JTextField txt_apePaterno;
    private javax.swing.JTextField txt_calle;
    private javax.swing.JTextField txt_calleProveedor;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_contraUsuProv;
    private javax.swing.JTextField txt_contraUsuario;
    private javax.swing.JTextField txt_deptoNum;
    private javax.swing.JTextField txt_dirNumProveedor;
    private javax.swing.JTextField txt_dirNumero;
    private javax.swing.JTextField txt_dirVilla;
    private javax.swing.JTextField txt_dv;
    private javax.swing.JTextField txt_dvProveedor;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_emailProveedor;
    private javax.swing.JTextField txt_fono;
    private javax.swing.JTextField txt_fonoProveedor;
    private javax.swing.JTextField txt_modificarContraUsu;
    private javax.swing.JTextField txt_modificarNomUsu;
    private javax.swing.JTextField txt_nomUsuProv;
    private javax.swing.JTextField txt_nomUsuario;
    private javax.swing.JTextField txt_nombreProveedor;
    private javax.swing.JTextField txt_nombres;
    private javax.swing.JTextField txt_rut;
    private javax.swing.JTextField txt_rutProveedor;
    private javax.swing.JTextField txt_rutUsuProv;
    // End of variables declaration//GEN-END:variables
}
