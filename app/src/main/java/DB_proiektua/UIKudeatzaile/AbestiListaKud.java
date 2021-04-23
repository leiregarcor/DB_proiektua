package DB_proiektua.UIKudeatzaile;

import DB_proiektua.DBKudeatzailea.AbeslariDB;
import DB_proiektua.DBKudeatzailea.AbestiListaDB;
import DB_proiektua.Main;
import DB_proiektua.model.AbestiaInfo;
import DB_proiektua.model.InfoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AbestiListaKud {

    @FXML
    private TableView<AbestiaInfo> tvTaula;

    @FXML
    private TableColumn<AbestiaInfo, String> tcGeneroa;

    @FXML
    private TableColumn<AbestiaInfo, String> tcIzena;

    @FXML
    private Button btnBack;

    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    @FXML
    void OnClick(ActionEvent event) {
        main.pantailaratuAbeslari();
    }

    public void informazioaKargatu(int id){
        List<AbestiaInfo> kargatzekoa = AbestiListaDB.getInstance().ikusiAbestiak(id);
        ObservableList<AbestiaInfo> Partaideak = FXCollections.observableArrayList(kargatzekoa);

        //add your data to the table here.
        tvTaula.setItems(Partaideak);
        tvTaula.setEditable(false);


        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class

        tcGeneroa.setCellValueFactory(new PropertyValueFactory<>("generoa"));
        tcIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));

    }

}

