package DB_proiektua.DBKudeatzailea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AbeslariDB {

    private static AbeslariDB instantzia = new AbeslariDB();

    public static AbeslariDB getInstance(){
        return instantzia;
    }

    public List<String> ikusiAbestiak(int id){
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String lortuCMSAtributuak = "select abestiak from ParteHartzailea where id="+id;
        ResultSet rs = dbKudeatzaile.execSQL(lortuCMSAtributuak);
        List<String> emaitza = new ArrayList<>();

        try{
            while(rs.next()) {
                String izena = rs.getString("izena");
            }
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return emaitza;
    }

    public String ikusiNonOstatu(int id){
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String lortuCMSAtributuak = "select fk_ParteHartzaile_Herria from ParteHartzaile where id="+id;
        ResultSet rs = dbKudeatzaile.execSQL(lortuCMSAtributuak);
        String izena = "";
        try{
            while(rs.next()) {
                izena = rs.getString("izena");
            }
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return izena;
    }

    public List<String> ikusiRanking(){
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String lortuCMSAtributuak = "select izena" +
                " from ParteHartzaile" +
                " order by puntuazioa asc";
        ResultSet rs = dbKudeatzaile.execSQL(lortuCMSAtributuak);
        List<String> emaitza = new ArrayList<>();

        try{
            while(rs.next()) {
                String izena = rs.getString("izena");
            }
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return emaitza;
    }
}
