package daocsv;

import model.Cidade;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import dao.DAOParametros;

public class DAOCSV {

    private final String divisor = ",";

    public ArrayList<Cidade> getRegistros() throws IOException {
        return getRegistros(-1, "");
    }
    
    /**
     * Requisito 9 - Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar.
     * retornar assim todos os objetos que contenham tal string.
     */
    public ArrayList<Cidade> getRegistros(int coluna, String filtro) throws IOException {
        ArrayList<Cidade> lista = new ArrayList<>();
        String linha;
        String path = DAOParametros.getUniqueDAOParametros().getParametros().getCaminhoCSV();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            linha = br.readLine(); //Ignorar linha de cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] cid = linha.split(divisor);
                if ((coluna < 0) || ((coluna < cid.length) && (cid[coluna].contains(filtro)))) {
                    Cidade cidade = new Cidade(Integer.parseInt(cid[0]), 
                                               cid[1],
                                               cid[2],
                                               cid[3].equals("true"),
                                               Double.parseDouble(cid[4]),
                                               Double.parseDouble(cid[5]),
                                               cid[6],
                                               cid[7],
                                               cid[8],
                                               cid[9]);
                    lista.add(cidade);
                }
            }
        }
        return lista;
    }
}
