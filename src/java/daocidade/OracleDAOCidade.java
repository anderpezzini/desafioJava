package daocidade;

import dao.OracleConnection;
import java.io.IOException;
import model.Cidade;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OracleDAOCidade implements DAOCidade {

    private OracleConnection oraConn;

    public OracleDAOCidade() {
        this.oraConn = new OracleConnection();
    }

    private ArrayList<Cidade> getCidades(String query) throws SQLException, ClassNotFoundException, IOException{
        ArrayList<Cidade> lista = new ArrayList<Cidade>();
        try (Connection conn = oraConn.getConnection();
             Statement s = conn.createStatement();) 
        {        
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                Cidade cidade = new Cidade(r.getInt("idibge"), 
                                           r.getString("uf"),
                                           r.getString("name"),
                                           r.getBoolean("capital"),
                                           r.getDouble("lon"),
                                           r.getDouble("lat"),
                                           r.getString("no_accents"),
                                           r.getString("alternative_names"),
                                           r.getString("microregion"),
                                           r.getString("mesoregion"));
                lista.add(cidade);
            }
        }
        return lista;
    }
    
    private boolean executaDML(String dml) throws SQLException, ClassNotFoundException, IOException  {
        boolean executou = false;
        try (Connection conn = oraConn.getConnection(); Statement s = conn.createStatement();) {
            executou = s.executeUpdate(dml) != 0;
        }
        return executou;
    }
    
    private int getQtde(String query) throws SQLException, ClassNotFoundException, IOException {
        int qtde = 0;
        try (Connection conn = oraConn.getConnection();
             Statement s = conn.createStatement();)
        {
            ResultSet r = s.executeQuery(query);
            if (r.next()) {
                qtde = r.getInt("qtde");
            }
        }
        return qtde;
    }
    
    @Override
    public ArrayList<Cidade> getCapitais() throws SQLException, ClassNotFoundException, IOException {
        String query = "SELECT c.idibge, c.uf, c.name, c.capital, c.lon, c.lat, c.no_accents, c.alternative_names, c.microregion, c.mesoregion \n" +
                       "  FROM Cidade c \n" +
                       " WHERE c.capital = 1 \n" +
                       " ORDER BY c.name";
        return getCidades(query);
    }    

    @Override
    public Map<String, Integer> getMaiorEMenorEstados() throws SQLException, ClassNotFoundException, IOException {
        Map<String, Integer> map = new HashMap<>();
        String query = "  (SELECT c.uf, COUNT(1) as quantidadecidades\n" 
                     + "     FROM Cidade c \n" 
                     + "    GROUP BY c.uf \n" 
                     + "    ORDER BY quantidadecidades DESC \n" 
                     + "    FETCH FIRST 1 ROWS ONLY) \n" 
                     + " UNION \n" 
                     + "  (SELECT c.uf, COUNT(1) as quantidadecidades \n" 
                     + "     FROM Cidade c \n" 
                     + "    GROUP BY c.uf \n" 
                     + "    ORDER BY quantidadecidades ASC \n" 
                     + "    FETCH FIRST 1 ROWS ONLY)";
        try (Connection conn = oraConn.getConnection(); 
             Statement s = conn.createStatement();)
        {
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                map.put(r.getString("uf"), r.getInt("quantidadecidades"));
            }
        }
        return map;
    }
    
    @Override
    public int getQuantidadeUF(String uf) throws SQLException, ClassNotFoundException, IOException {
        String query = "SELECT COUNT(1) AS qtde FROM Cidade c WHERE c.uf = '" + uf + "'";
        return getQtde(query);
    }

    @Override
    public Cidade getCidade(int idIBGE) throws SQLException, ClassNotFoundException, IOException {        
        String query = "SELECT c.idibge, c.uf, c.name, c.capital, c.lon, c.lat, c.no_accents, c.alternative_names, c.microregion, c.mesoregion \n"
                     + "  FROM Cidade c \n"
                     + " WHERE c.idibge = " + idIBGE;
        Cidade cidade = null;
        try (Connection conn = oraConn.getConnection();
             Statement s = conn.createStatement();) 
        {        
            ResultSet r = s.executeQuery(query);
            if (r.next()) {
                cidade = new Cidade(r.getInt("idibge"), 
                                    r.getString("uf"),
                                    r.getString("name"),
                                    r.getBoolean("capital"),
                                    r.getDouble("lon"),
                                    r.getDouble("lat"),
                                    r.getString("no_accents"),
                                    r.getString("alternative_names"),
                                    r.getString("microregion"),
                                    r.getString("mesoregion"));
            }
        }
        return cidade;
    }

    @Override
    public ArrayList<String> getNomes(String uf) throws SQLException, ClassNotFoundException, IOException {
        String query = "SELECT c.name FROM Cidade c WHERE c.uf = '" + uf + "'";
        ArrayList<String> lista = new ArrayList<String>();
        try (Connection conn = oraConn.getConnection();
             Statement s = conn.createStatement()) 
        {
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                lista.add(r.getString("name"));
            }
        }
        return lista;
    }

    @Override
    public boolean inserir(Cidade cidade) throws SQLException, ClassNotFoundException, IOException {
        String dml = "INSERT INTO Cidade c (c.idibge, c.uf, c.name, c.capital, c.lon, c.lat, c.no_accents, c.alternative_names, c.microregion, c.mesoregion) \n"
                   + "VALUES (" + cidade.getIdIBGE()+ ", "
                   + "        '" + cidade.getUf() + "', "
                   + "        '" + cidade.getName().replace("'","''") + "', "
                   + "        " + (cidade.isCapital() ? "1" : "0") + ", "
                   + "        " + cidade.getLon() + ", "
                   + "        " + cidade.getLat() + ", "
                   + "        '" + cidade.getNoAccents().replace("'","''") + "', "
                   + "        '" + cidade.getAlternativeNames().replace("'","''") + "', "
                   + "        '" + cidade.getMicroregion().replace("'","''") + "', "
                   + "        '" + cidade.getMesoregion().replace("'","''") + "')";
        return executaDML(dml);
    }

    @Override
    public boolean excluir(int idIBGE) throws SQLException, ClassNotFoundException, IOException {
        String dml = "DELETE FROM Cidade c WHERE c.idibge = " + idIBGE;
        return executaDML(dml);
    }

    @Override
    public int getQuantidade() throws SQLException, ClassNotFoundException, IOException {
        String query = "SELECT COUNT(1) AS qtde FROM Cidade";
        return getQtde(query);
    }

    @Override
    public ArrayList<Cidade> getCidadesMaisDistantes() throws SQLException, ClassNotFoundException, IOException {
        String query = "SELECT c.idibge, c.uf, c.name, c.capital, c.lon, c.lat, c.no_accents, c.alternative_names, c.microregion, c.mesoregion \n" 
                     + "  FROM Cidade c \n" 
                     + "  JOIN (SELECT c1.idibge as id1, \n" 
                     + "               c2.idibge as id2, \n" 
                     + "               SQRT(POWER(c1.lon - c2.lon, 2) + POWER(c1.lat - c2.lat, 2)) as distancia \n" 
                     + "          FROM Cidade c1 \n" 
                     + "          JOIN Cidade c2 \n" 
                     + "            ON c1.idibge < c2.idibge \n" 
                     + "         ORDER BY distancia DESC \n" 
                     + "         FETCH FIRST 1 ROWS ONLY) d \n" 
                     + "    ON c.idibge = d.id1 \n" 
                     + "    OR c.idibge = d.id2 ";
        return getCidades(query);
    }

    @Override
    public void importaDados(ArrayList<Cidade> lista) throws SQLException, ClassNotFoundException, IOException {
        if (lista.size() > 0) {
            try (Connection conn = oraConn.getConnection();) {  
                conn.setAutoCommit(false);
                try (Statement s = conn.createStatement();) {
                    String dml;
                    for (Cidade c: lista) {
                        dml = "  MERGE INTO Cidade c "
                            + "  USING (SELECT "+c.getIdIBGE()+" as idibge FROM DUAL) x"
                            + "     ON (c.idibge = x.idibge)"
                            + "   WHEN NOT MATCHED THEN "
                            + " INSERT (idibge, uf, name,  capital, lon, lat, no_accents, alternative_names, microregion, mesoregion) "
                            + " VALUES ("+c.getIdIBGE()+", '"
                                         +c.getUf()+"', '"
                                         +c.getName().replace("'","''")+"', "
                                         +(c.isCapital()?"1":"0")+", "
                                         +c.getLon()+", "
                                         +c.getLat()+", '"
                                         +c.getNoAccents().replace("'","''")+"', '"
                                         +c.getAlternativeNames().replace("'","''")+"', '"
                                         +c.getMicroregion().replace("'","''")+"', '"
                                         +c.getMesoregion().replace("'","''")+"') ";
                        s.executeUpdate(dml);
                    }
                    conn.commit();
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            }
        }
    }

    @Override
    public int getQuantidade(String coluna) throws SQLException, ClassNotFoundException, IOException {
        String query = "SELECT COUNT(DISTINCT "+coluna+") AS qtde FROM Cidade";
        return getQtde(query);
    }
    
}
