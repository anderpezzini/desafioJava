package dao;

import daocidade.DAOCidade;

public abstract class DAOFactory {

    public DAOFactory() {
    }
    
    public abstract DAOCidade getDAOCidade();
    
    public static DAOFactory getDAOFactory() {       
        return new OracleDAOFactory();    
    }
}
