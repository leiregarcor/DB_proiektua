package DB_proiektua.DBKudeatzailea.AbeslariQuery;

import DB_proiektua.DBKudeatzailea.DBKudeatzaile;
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
        //String lortuCMSAtributuak = "select idAbeslari, count(*) as puntuazioa from Bozkaketa group by idAbeslari having puntuazioa>5 order by puntuazioa asc";

        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String lortuCMSAtributuak = "select id, puntuazioa from Erregistratu, ParteHartzaile where ParteHartzaileID=ParteHartzaile.id having puntuazioa>=5 order by puntuazioa desc";
        ResultSet rs = dbKudeatzaile.execSQL(lortuCMSAtributuak);
        List<RankingInfo> emaitza = new ArrayList<>();

        try{
            while(rs.next()) {
                int id = rs.getInt("id");
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
