package DB_proiektua.DBKudeatzailea.AbeslariQuery;

import DB_proiektua.DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.model.InfoModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbeslariDB {

    private static final AbeslariDB instantzia = new AbeslariDB();

    public static AbeslariDB getInstance(){
        return instantzia;
    }

    public List<InfoModel> informazioaLortu(String i){
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String lortuCMSAtributuak = "select NAN, Izena, adina, herria from ParteHartzaile where Izena='"+i+"'";
        ResultSet rs = dbKudeatzaile.execSQL(lortuCMSAtributuak);
        List<InfoModel> emaitza = new ArrayList<>();

        try{
            while(rs.next()) {
                String nan = rs.getString("NAN");
                String izena = rs.getString("Izena");
                Integer adina = rs.getInt("adina");
                String herria = rs.getString("herria");
                InfoModel berria = new InfoModel(nan,izena,adina,herria);
                emaitza.add(berria);
            }
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return emaitza;
    }

    public int lortuId(String izena) throws SQLException {
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String lortuCMSAtributuak = "select id from ParteHartzaile where izena='"+izena+"'";
        ResultSet rs = dbKudeatzaile.execSQL(lortuCMSAtributuak);
        int id = 0;

        if(rs.next()){
            id = rs.getInt("id");
        }
        return id;
    }

    public String lortuIzena(int id) throws SQLException {
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String lortuCMSAtributuak = "select Izena from ParteHartzaile where id='"+id+"'";
        ResultSet rs = dbKudeatzaile.execSQL(lortuCMSAtributuak);
        String izena = "";

        if(rs.next()){
            izena = rs.getString("Izena");
        }
        return izena;
    }



    /*
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
     */


}
