package DB_proiektua.UIKudeatzaile;

import DB_proiektua.DBKudeatzailea.AbeslariQuery.RankingDB;
import DB_proiektua.Main;
import DB_proiektua.model.RankingInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RankingKud {

    private boolean chivato;

    //chivato - true ---> viene de erabiltzailea
    //chivato - false ---> viene de abeslaria

    @FXML
    private TableView<RankingInfo> tvTaula;

    @FXML
    private TableColumn<RankingInfo, String> tcIzena;

    @FXML
    private TableColumn<RankingInfo, Integer> tcPuntuazioa;

    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    public void setChivato(boolean a){
        chivato = a;
    }

    @FXML
    void OnClick() {
        if(chivato){
            main.pantailaratuErabiltzaile();
        }
        else{
            main.pantailaratuAbeslari();
        }
    }

    public void informazioaKargatu(){
        List<RankingInfo> kargatzekoa = RankingDB.getInstance().informazioaLortu();
        ObservableList<RankingInfo> Partaideak = FXCollections.observableArrayList(kargatzekoa);

        tvTaula.setItems(Partaideak);

        tcIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        tcPuntuazioa.setCellValueFactory(new PropertyValueFactory<>("puntuazioa"));

    }
}
