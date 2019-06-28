
package ConexionDB;

/**
 *
 * @author Sebastian
 */
public class DatosConexion {
    
    private String usuario="portafolio";
    private String contraseña ="123456";
    private String driver = "jdbc:oracle:thin:@localhost:1521:XE";
    private String Clase = "oracle.jdbc.OracleDriver";

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
