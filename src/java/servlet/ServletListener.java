package servlet;

import daocidade.DAOCidade;
import dao.DAOFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import daocsv.DAOCSV;
import java.io.IOException;
import java.sql.SQLException;

@WebListener
public class ServletListener implements ServletContextListener {
        
    @Override
    public void contextInitialized(ServletContextEvent event) {        
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        DAOCSV daoCsv = new DAOCSV();
        try {
            daoCidade.importaDados(daoCsv.getRegistros());
        } catch (IOException | SQLException | ClassNotFoundException ex) {            
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Do stuff during webapp's shutdown.
    }
}
