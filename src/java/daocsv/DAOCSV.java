package daocsv;

import model.Cidade;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DAOCSV {

    private final String divisor = ";";
    private final String path = "C:\\Users\\Ander\\Google Drive\\API_XPTO\\xpto\\cidades.csv";

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
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((linha = br.readLine()) != null) {
                String[] cid = linha.split(divisor);
                if ((coluna < 0) || ((coluna < cid.length) && (cid[coluna].contains(filtro)))) {
                    Cidade cidade = new Cidade(Integer.parseInt(cid[0]), 
                                               cid[1],
                                               cid[2].equals("1"),
                                               cid[3],
                                               Integer.parseInt(cid[4]),
                                               Integer.parseInt(cid[5]),
                                               Integer.parseInt(cid[6]));
                    lista.add(cidade);
                }
            }
        }
        return lista;
    }
}
