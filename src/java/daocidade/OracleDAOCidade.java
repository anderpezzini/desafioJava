package daocidade;

import model.Cidade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OracleDAOCidade implements DAOCidade {

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@ander-nb:1521:xe", "xpto", "xpto");
    }

    private ArrayList<Cidade> getCidades(String query) throws SQLException, ClassNotFoundException{
        ArrayList<Cidade> lista = new ArrayList<Cidade>();
        try (Connection conn = getConnection();
             Statement s = conn.createStatement();) 
        {        
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                Cidade cidade = new Cidade(r.getInt("id"), 
                                           r.getString("nome"),
                                           r.getBoolean("capital"),
                                           r.getString("uf"),
                                           r.getInt("idibge"),
                                           r.getInt("posX"),
                                           r.getInt("posY"));
                lista.add(cidade);
            }
        }
        return lista;
    }
    
    private boolean executaDML(String dml) throws SQLException, ClassNotFoundException  {
        boolean executou = false;
        try (Connection conn = getConnection(); Statement s = conn.createStatement();) {
            executou = s.executeUpdate(dml) != 0;
        }
        return executou;
    }
    
    private int getQtde(String query) throws SQLException, ClassNotFoundException {
        int qtde = 0;
        try (Connection conn = getConnection();
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
    public ArrayList<Cidade> getCapitais() throws SQLException, ClassNotFoundException {
        String query = "SELECT c.id, c.nome, c.capital, c.uf, c.idibge, c.posX, c.posY \n" +
                       "  FROM Cidade c \n" +
                       " WHERE c.capital = 1 \n" +
                       " ORDER BY c.nome";
        return getCidades(query);
    }    

    @Override
    public Map<String, Integer> getMaiorEMenorEstados() throws SQLException, ClassNotFoundException{
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
        try (Connection conn = getConnection(); 
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
    public int getQuantidadeUF(String uf) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(1) AS qtde FROM Cidade c WHERE c.uf = '" + uf + "'";
        return getQtde(query);
    }

    @Override
    public ArrayList<Cidade> getCidades(int idIBGE) throws SQLException, ClassNotFoundException {        
        String query = "SELECT c.id, c.nome, c.capital, c.uf, c.idibge, c.posX, c.posY \n"
                     + "  FROM Cidade c \n"
                     + " WHERE c.idibge = " + idIBGE;
        return getCidades(query);
    }

    @Override
    public ArrayList<String> getNomes(String uf) throws SQLException, ClassNotFoundException {
        String query = "SELECT c.nome FROM Cidade c WHERE c.uf = '" + uf + "'";
        ArrayList<String> lista = new ArrayList<String>();
        try (Connection conn = getConnection();
             Statement s = conn.createStatement()) 
        {
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                lista.add(r.getString("nome"));
            }
        }
        return lista;
    }

    @Override
    public boolean inserir(Cidade cidade) throws SQLException, ClassNotFoundException {
        String dml = "INSERT INTO Cidade c (c.id, c.nome, c.capital, c.uf, c.idibge, c.posX, c.posY) \n"
                   + "VALUES (" + cidade.getId() + ", "
                   + "        '" + cidade.getNome() + "', "
                   + "        " + (cidade.isCapital() ? "1" : "0") + ", "
                   + "        '" + cidade.getUf() + "', "
                   + "        " + cidade.getIdIBGE() + ", "
                   + "        " + cidade.getPosX() + ", "
                   + "        " + cidade.getPosY() + ")";
        return executaDML(dml);
    }

    @Override
    public boolean excluir(int id) throws SQLException, ClassNotFoundException {
        String dml = "DELETE FROM Cidade c WHERE c.id = " + id;
        return executaDML(dml);
    }

    @Override
    public int getQuantidade() throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(1) AS qtde FROM Cidade";
        return getQtde(query);
    }

    @Override
    public ArrayList<Cidade> getCidadesMaisDistantes() throws SQLException, ClassNotFoundException {
        String query = "SELECT c.id, c.nome, c.capital, c.uf, c.idibge, c.posX, c.posY \n" 
                     + "  FROM Cidade c \n" 
                     + "  JOIN (SELECT c1.id as id1, \n" 
                     + "               c2.id as id2, \n" 
                     + "               SQRT(POWER(c1.posX - c2.posX, 2) + POWER(c1.posY - c2.posY, 2)) as distancia \n" 
                     + "          FROM Cidade c1 \n" 
                     + "          JOIN Cidade c2 \n" 
                     + "            ON c1.id < c2.id \n" 
                     + "         ORDER BY distancia DESC \n" 
                     + "         FETCH FIRST 1 ROWS ONLY) d \n" 
                     + "    ON c.id = d.id1 \n" 
                     + "    OR c.id = d.id2 ";
        return getCidades(query);
    }

    @Override
    public void importaDados(ArrayList<Cidade> lista) throws SQLException, ClassNotFoundException {
        if (lista.size() > 0) {
            try (Connection conn = getConnection();) {  
                conn.setAutoCommit(false);
                try (Statement s = conn.createStatement();) {
                    s.executeUpdate("DELETE FROM Cidade");
                    String dml = "INSERT ALL \n";
                    for (Cidade c: lista) {
                        dml += " INTO Cidade (id, nome, capital, uf, idibge, posX, posY) "
                             + " VALUES ("+c.getId()+", '"+c.getNome()+"', "+(c.isCapital()?"1":"0")+", '"+c.getUf()+"', "+c.getIdIBGE()+", "+c.getPosX()+", "+c.getPosY()+") \n";
                    }
                    dml += " SELECT 1 FROM dual";
                    s.executeUpdate(dml);
                    conn.commit();
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            }
        }
    }

    @Override
    public int getQuantidade(String coluna) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(DISTINCT "+coluna+") AS qtde FROM Cidade";
        return getQtde(query);
    }
    
}
