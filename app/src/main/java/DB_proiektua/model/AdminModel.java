package DB_proiektua.model;

public class AdminModel {
    private String izena;
    private String abestia;
    private String generoa;
    private int abeslariID;

    public int getAbeslariID() {
        return abeslariID;
    }

    public void setAbeslariID(int abeslariID) {
        this.abeslariID = abeslariID;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getAbestia() {
        return abestia;
    }

    public void setAbestia(String abestia) {
        this.abestia = abestia;
    }

    public String getGeneroa() {
        return generoa;
    }

    public void setGeneroa(String generoa) {
        this.generoa = generoa;
    }
}
