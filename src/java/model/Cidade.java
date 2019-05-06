package model;

public class Cidade {

    private int id;
    private String nome;
    private boolean capital;
    private String uf;
    private int idIBGE;
    private int posX;
    private int posY;

    public Cidade(int id, String nome, boolean capital, String uf, int idIBGE, int posX, int posY) {
        this.id = id;
        this.nome = nome;
        this.capital = capital;
        this.uf = uf;
        this.idIBGE = idIBGE;
        this.posX = posX;
        this.posY = posY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getIdIBGE() {
        return idIBGE;
    }

    public void setIdIBGE(int idIBGE) {
        this.idIBGE = idIBGE;
    }    

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }   
    
}
