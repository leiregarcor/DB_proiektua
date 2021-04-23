package DB_proiektua.model;

public class RankingInfo {

    private String izena;
    private Integer puntuazioa;

    public RankingInfo(String izena, Integer puntuazioa) {
        this.izena = izena;
        this.puntuazioa = puntuazioa;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public Integer getPuntuazioa() {
        return puntuazioa;
    }

    public void setPuntuazioa(Integer puntuazioa) {
        this.puntuazioa = puntuazioa;
    }
}
