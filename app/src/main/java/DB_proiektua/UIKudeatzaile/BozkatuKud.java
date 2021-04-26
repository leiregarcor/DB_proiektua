package DB_proiektua.UIKudeatzaile;

import DB_proiektua.DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class BozkatuKud implements Initializable {

    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    @FXML
    private ComboBox<String> aukeratu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //select p.Izena, a.izena  from ParteHartzaile p, Abestia a, Erregistratu where a.ParteHartzaileID=p.id and YEAR(ErregistroData)=YEAR(curdate())
        String query="select p.Izena as PHIzena, a.izena as AbestiIzena  from ParteHartzaile p, Abestia a, Erregistratu where a.ParteHartzaileID=p.id and YEAR(ErregistroData)=YEAR(curdate())";

        ResultSet rs= DBKudeatzaile.getInstantzia().execSQL(query);

    }
}
