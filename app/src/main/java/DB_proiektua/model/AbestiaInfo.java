package DB_proiektua.model;

public class AbestiaInfo {

    private String generoa;
    private String abIzena;
    //bozkatzeko abeslariaren izena gorde behar da
    private String abeslariIz;

    public AbestiaInfo(String generoa, String izena, String abIz) {
        this.generoa = generoa;
        this.abIzena = izena;
        this.abeslariIz=abIz;
    }

    public String getGeneroa() {
        return generoa;
    }

    public void setGeneroa(String generoa) {
        this.generoa = generoa;
    }

    public String getIzena() {
        return abIzena;
    }

    public void setIzena(String izena) {
        this.abIzena = izena;
    }

    public String getAbeslariIz() {
        return abeslariIz;
    }

    public void setAbeslariIz(String abeslariIz) {
        this.abeslariIz = abeslariIz;
    }

    @Override
    public String toString() {
        return abIzena +" | "+ abeslariIz ;
    }
}
