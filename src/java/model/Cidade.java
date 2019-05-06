package model;

public class Cidade {

    private int idIBGE;
    private String uf;
    private String name;
    private boolean capital;
    private double lon;
    private double lat;
    private String noAccents;
    private String alternativeNames;
    private String microregion;
    private String mesoregion;

    public Cidade(int idIBGE, String uf, String name, boolean capital, double lon, double lat, String noAccents, String alternativeNames, String microregion, String mesoregion) {
        this.idIBGE = idIBGE;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.lon = lon;
        this.lat = lat;
        this.noAccents = noAccents;
        this.alternativeNames = alternativeNames;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
    }

    public int getIdIBGE() {
        return idIBGE;
    }

    public void setIdIBGE(int idIBGE) {
        this.idIBGE = idIBGE;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getNoAccents() {
        return noAccents;
    }

    public void setNoAccents(String noAccents) {
        this.noAccents = noAccents;
    }

    public String getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(String alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getMicroregion() {
        return microregion;
    }

    public void setMicroregion(String microregion) {
        this.microregion = microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }
    
}
