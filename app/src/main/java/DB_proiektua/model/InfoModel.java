package DB_proiektua.model;

public class InfoModel {

    private String nan;
    private String izena;
    private Integer adina;
    private String herria;

    public InfoModel(String nan, String izena, Integer adina, String herria) {
        this.nan = nan;
        this.izena = izena;
        this.adina = adina;
        this.herria = herria;
    }

    public String getNan() {
        return nan;
    }

    public void setNan(String nan) {
        this.nan = nan;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public Integer getAdina() {
        return adina;
    }

    public void setAdina(Integer adina) {
        this.adina = adina;
    }

    public String getHerria() {
        return herria;
    }

    public void setHerria(String herria) {
        this.herria = herria;
    }
}
