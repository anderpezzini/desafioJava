package util;

public class Parametros {
    
    private String host;
    private String porta;
    private String usuario;
    private String senha;
    private String sid;
    private String caminhoCSV;

    public Parametros() {
        this.host = "";
        this.porta = "";
        this.usuario = "";
        this.senha = "";
        this.sid = "";
        this.caminhoCSV = "";
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCaminhoCSV() {
        return caminhoCSV;
    }

    public void setCaminhoCSV(String caminhoCSV) {
        this.caminhoCSV = caminhoCSV;
    }
    
    
}
