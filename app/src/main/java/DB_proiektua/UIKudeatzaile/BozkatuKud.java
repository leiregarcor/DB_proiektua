package DB_proiektua.UIKudeatzaile;

import DB_proiektua.DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import DB_proiektua.model.AbestiaInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.awt.event.ActionEvent;
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
    @FXML
    void bozkatuClick() throws SQLException {
        AbestiaInfo selectedAI= aukeratu.getSelectionModel().getSelectedItem();
        //select p.id from ParteHartzaile p, Abestia a where p.Izena like "mesi" and a.izena like  "Gazte Arruntaren koplak" and p.id=a.ParteHartzaileID
        String query="select p.id as id from ParteHartzaile p, Abestia a where p.Izena like '"+ selectedAI.getAbeslariIz() +"' and a.izena like '"+ selectedAI.getIzena() +"' and p.id=a.ParteHartzaileID";
        ResultSet rs= DBKudeatzaile.getInstantzia().execSQL(query); //partehartzailearen id-a lortzen dugu

        //UPDATE Eurobisio.Erregistratu SET puntuazioa = puntuazioa+1 WHERE (year(ErregistroData) = year(curdate())) and (ParteHartzaileID = '43')
        try {
            rs.next();
            int id = rs.getInt("id");
            System.out.println(id);
            String query2 = "UPDATE Eurobisio.Erregistratu SET puntuazioa = puntuazioa+1 WHERE (year(ErregistroData) = year(curdate())) and (ParteHartzaileID = '" + rs.getInt("id") + "')";
            DBKudeatzaile.getInstantzia().execSQL(query2);
            main.pantailaratuRanking();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
