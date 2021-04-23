package DB_proiektua.model;

public class AbestiaInfo {

    private String generoa;
    private String izena;

    public AbestiaInfo(String generoa, String izena) {
        this.generoa = generoa;
        this.izena = izena;
    }

    public String getGeneroa() {
        return generoa;
    }

    public void setGeneroa(String generoa) {
        this.generoa = generoa;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }
}
