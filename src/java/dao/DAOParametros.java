package dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import util.Parametros;

public class DAOParametros {

    private Parametros parametros;
    private static DAOParametros uniqueDAOParametros;
    private static final String separadorParametro = "=";
    private static final int qtdeParam = 6;

    public DAOParametros() throws IOException {
        this.parametros = new Parametros();
        CarregaParametros();
    }
    
    private void CarregaParametros() throws IOException {
        try (FileReader r = new FileReader(System.getProperty("user.dir") + "\\parametros.txt");
             Scanner s = new Scanner(r);)
            {
                for (int i = 0; i < qtdeParam; i++) {
                    String linha = s.nextLine();
                    int posicaoSeparador = linha.indexOf(separadorParametro);
                    if (posicaoSeparador != -1) {
                        String param = linha.substring(0, posicaoSeparador);
                        if (param.equalsIgnoreCase("host")) {
                            parametros.setHost(linha.substring(posicaoSeparador + 1, linha.length()));
                        } else if (param.equalsIgnoreCase("porta")) {
                            parametros.setPorta(linha.substring(posicaoSeparador + 1, linha.length()));
                        } else if (param.equalsIgnoreCase("usuario")) {
                            parametros.setUsuario(linha.substring(posicaoSeparador + 1, linha.length()));
                        } else if (param.equalsIgnoreCase("senha")) {
                            parametros.setSenha(linha.substring(posicaoSeparador + 1, linha.length()));
                        } else if (param.equalsIgnoreCase("sid")) {
                            parametros.setSid(linha.substring(posicaoSeparador + 1, linha.length()));
                        } else if (param.equalsIgnoreCase("caminhocsv")) {
                            parametros.setCaminhoCSV(linha.substring(posicaoSeparador + 1, linha.length()));
                        }
                    }
                }
            }
    }
    
    public Parametros getParametros() {
        return parametros;
    }
    
    public static DAOParametros getUniqueDAOParametros() throws IOException {
        if (uniqueDAOParametros == null) {
            uniqueDAOParametros = new DAOParametros();
        }
        return uniqueDAOParametros;
    }
    
    
}
