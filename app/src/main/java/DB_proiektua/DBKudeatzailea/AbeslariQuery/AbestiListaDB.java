package DB_proiektua.DBKudeatzailea.AbeslariQuery;

import DB_proiektua.DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.model.AbestiaInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbestiListaDB {

    private static final AbestiListaDB instantzia = new AbestiListaDB();

    public static AbestiListaDB getInstance(){
        return instantzia;
    }

    public List<AbestiaInfo> ikusiAbestiak(int id){
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String lortuCMSAtributuak = "select generoa, izena from Abestia where ParteHartzaileID="+id;
        ResultSet rs = dbKudeatzaile.execSQL(lortuCMSAtributuak);
        List<AbestiaInfo> emaitza = new ArrayList<>();

        try{
            while(rs.next()) {
                String generoa = rs.getString("generoa");
                String izena = rs.getString("izena");
                AbestiaInfo berria = new AbestiaInfo(generoa,izena," ");
                emaitza.add(berria);
            }
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return emaitza;
    }
}
