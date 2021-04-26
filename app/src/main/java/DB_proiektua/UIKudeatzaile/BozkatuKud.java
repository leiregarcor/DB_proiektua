package DB_proiektua.UIKudeatzaile;

import DB_proiektua.DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import DB_proiektua.model.AbestiaInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BozkatuKud implements Initializable {

    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    @FXML
    private ComboBox<AbestiaInfo> aukeratu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // select p.Izena as PHIzena, a.izena as AbestiIzena  from ParteHartzaile p, Abestia a, Erregistratu e where e.ParteHartzaileID=p.id and YEAR(ErregistroData)=YEAR(curdate()) and a.ParteHartzaileID=p.id
        String query="select p.Izena as PHIzena, a.izena as AbestiIzena  from ParteHartzaile p, Abestia a, Erregistratu e where e.ParteHartzaileID=p.id and YEAR(ErregistroData)=YEAR(curdate()) and a.ParteHartzaileID=p.id";

        ResultSet rs= DBKudeatzaile.getInstantzia().execSQL(query);

        ObservableList<AbestiaInfo> emaitza= FXCollections.observableArrayList();

        try{
            String abIz;
            String ptIz;
            while (rs.next()){
                ptIz=rs.getString("PHIzena");
                abIz=rs.getString("AbestiIzena");
                emaitza.add(new AbestiaInfo(" ", abIz, ptIz));
            }
            aukeratu.setItems(emaitza);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
