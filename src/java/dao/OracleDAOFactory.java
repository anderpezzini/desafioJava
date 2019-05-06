package dao;

import daocidade.OracleDAOCidade;
import daocidade.DAOCidade;

public class OracleDAOFactory extends DAOFactory {

    public OracleDAOFactory() {
        super();
    }
    
    @Override
    public DAOCidade getDAOCidade() {
        return new OracleDAOCidade();
    }
    
}
