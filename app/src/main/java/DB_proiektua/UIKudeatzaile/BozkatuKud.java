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

    private int erabID;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    public void setErabID(int erabID) {
        this.erabID = erabID;
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
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void bozkatuClick(){
        AbestiaInfo selectedAI= aukeratu.getSelectionModel().getSelectedItem();
        if(selectedAI!=null){
            //select p.id from ParteHartzaile p, Abestia a where p.Izena like "mesi" and a.izena like  "Gazte Arruntaren koplak" and p.id=a.ParteHartzaileID
            String query="select p.id as id from ParteHartzaile p, Abestia a where p.Izena like '"+
                    selectedAI.getAbeslariIz() +
                    "' and a.izena like '"+
                    selectedAI.getIzena() +
                    "' and p.id=a.ParteHartzaileID";


            ResultSet rs= DBKudeatzaile.getInstantzia().execSQL(query); //partehartzailearen id-a lortzen dugu

            //UPDATE Eurobisio.Erregistratu SET puntuazioa = puntuazioa+1 WHERE (year(ErregistroData) = year(curdate())) and (ParteHartzaileID = '43')
            try {
                rs.next();
                int id = rs.getInt("id");

                if (!bozkatuDu(id)){
                    String query2 = "UPDATE Eurobisio.Erregistratu SET puntuazioa = puntuazioa+1 WHERE (year(ErregistroData) = year(curdate())) and (ParteHartzaileID = '" + id + "')";
                    DBKudeatzaile.getInstantzia().execSQL(query2);
                    main.getRankingKud().informazioaKargatu();
                    main.pantailaratuRanking();

                    //INSERT INTO `Eurobisio`.`Bozkaketa` (`idErab`, `idAbeslari`, `dataPH`) VALUES ('15', '13', (SELECT data FROM Erregistro WHERE year(data)=year(curdate()) limit 1));

                    String query3="INSERT INTO `Eurobisio`.`Bozkaketa` (`idErab`, `idAbeslari`, `dataPH`) VALUES ('"+
                            erabID+ //erabiltzaile ID
                            "', '"+
                            id+  //abeslari ID
                            "', (SELECT data FROM Erregistro WHERE year(data)=year(curdate()) limit 1) )"; //urte honetan egingo den probaren data bueltatu

                    DBKudeatzaile.getInstantzia().execSQL(query3);

                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private boolean bozkatuDu(int id) throws SQLException {
        String bozkatuDu="SELECT idErab, idAbeslari, dataPH FROM Bozkaketa WHERE idErab='"+
                erabID+"' AND idAbeslari='"+
                id+"' AND YEAR(dataPH)=YEAR(CURDATE())";

        ResultSet rs=DBKudeatzaile.getInstantzia().execSQL(bozkatuDu);
        return rs.next();
    }

}
