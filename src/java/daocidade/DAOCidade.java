package daocidade;

import model.Cidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface DAOCidade {
    
    /**
     * Requisito 1 - Ler o arquivo CSV das cidades para a base de dados.
     */
    public void importaDados(ArrayList<Cidade> lista) throws SQLException, ClassNotFoundException;
    
    /**
     * Requisito 2 - Retornar somente as cidades que são capitais ordenadas por nome.
     */
    public ArrayList<Cidade> getCapitais() throws SQLException, ClassNotFoundException; 
    
    /**
     * Requisito 3 - Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades.
     */
    public Map<String, Integer> getMaiorEMenorEstados() throws SQLException, ClassNotFoundException;
    
    /**
     * Requisito 4 - Retornar a quantidade de cidades por estado.
     */
    public int getQuantidadeUF(String uf) throws SQLException, ClassNotFoundException; 
    
    /**
     * Requisito 5 - Obter os dados da cidade informando o id do IBGE.
     */
    public ArrayList<Cidade> getCidades(int idIBGE) throws SQLException, ClassNotFoundException; 
    
    /**
     * Requisito 6 - Retornar o nome das cidades baseado em um estado selecionado.
     */
    public ArrayList<String> getNomes(String uf) throws SQLException, ClassNotFoundException; 
    
    /**
     * Requisito 7 - Permitir adicionar uma nova Cidade.
     */
    public boolean inserir(Cidade cidade) throws SQLException, ClassNotFoundException; 
    
    /**
     * Requisito 8 - Permitir deletar uma cidade.
     */
    public boolean excluir(int id) throws SQLException, ClassNotFoundException; 
    
    /**
     * Requisito 10 - Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais.
     */
    public int getQuantidade(String coluna) throws SQLException, ClassNotFoundException;
    
    /**
     * Requisito 11 - Retornar a quantidade de registros total.
     */
    public int getQuantidade() throws SQLException, ClassNotFoundException;
    
    /**
     * Requisito 12 - Dentre todas as cidades, obter as duas cidades mais distantes uma da 
     * outra com base na localização (distância em KM em linha reta).
     */
    public ArrayList<Cidade> getCidadesMaisDistantes() throws SQLException, ClassNotFoundException;
    
    
}
