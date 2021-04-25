package DB_proiektua.DBKudeatzailea;

import DB_proiektua.model.RankingInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankingDB {

    private static final RankingDB instantzia = new RankingDB();

    public static RankingDB getInstance(){
        return instantzia;
    }

    public List<RankingInfo> informazioaLortu(){
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String lortuCMSAtributuak = "select idAbeslari, count(*) as puntuazioa from Bozkaketa group by idAbeslari having puntuazioa>5 order by puntuazioa asc";
        ResultSet rs = dbKudeatzaile.execSQL(lortuCMSAtributuak);
        List<RankingInfo> emaitza = new ArrayList<>();

        try{
            while(rs.next()) {
                int id = rs.getInt("idAbeslari");
                int puntuazioa = rs.getInt("puntuazioa");
                RankingInfo berria = new RankingInfo(AbeslariDB.getInstance().lortuIzena(id),puntuazioa);
                emaitza.add(berria);
            }
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return emaitza;
    }
}
