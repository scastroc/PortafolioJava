
package ConexionDB;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author Sebastian
 */
public class Conexion {
    
    public Connection getConnection(DatosConexion datosDB) throws SQLException {
        Connection conDB = null;
        try {
            Class.forName(datosDB.getClase());
            conDB = DriverManager.getConnection(datosDB.getDriver(), datosDB.getUsuario(), datosDB.getContraseña());
            if (conDB != null) {
                System.out.println("Conexion Exitosa BDD");
            } else {
                System.out.println("Conexion Fallida BDD");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error BDD : " + e.getMessage());
            throw new ClassCastException(e.getMessage());
            
        } catch (SQLException e) {
            System.out.println("Error BDD : " + e.getMessage());
            throw new SQLException(e.getMessage());
        }
        return conDB;
    }

}
