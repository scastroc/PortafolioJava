
package ConexionDB;

/**
 *
 * @author Sebastian
 */
public class DatosConexion {
    
    String usuario="portafolio";
    String contraseña ="123456";
    String driver = "jdbc:oracle:thin:@localhost:1521:XE";
    String Clase = "oracle.jdbc.OracleDriver";

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getClase() {
        return Clase;
    }

    public void setClase(String Clase) {
        this.Clase = Clase;
    }   
    
    
}
