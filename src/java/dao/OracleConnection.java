package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.Parametros;

public class OracleConnection {

    public Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("oracle.jdbc.OracleDriver");
        Parametros p = DAOParametros.getUniqueDAOParametros().getParametros();
        return DriverManager.getConnection("jdbc:oracle:thin:@"+p.getHost()+":"+p.getPorta()+":"+p.getSid(), p.getUsuario(), p.getSenha());
    }
    
}
