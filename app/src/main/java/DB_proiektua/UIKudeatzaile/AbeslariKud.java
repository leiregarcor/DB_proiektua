package DB_proiektua.UIKudeatzaile;

import DB_proiektua.DBKudeatzailea.AbestiListaDB;
import DB_proiektua.Main;
import DB_proiektua.model.InfoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import DB_proiektua.DBKudeatzailea.AbeslariDB;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.sql.SQLException;
import java.util.List;


public class AbeslariKud {

    private String izena;

    @FXML
    private Label lbIzena;

    @FXML
    private Label lblOstatu;

    @FXML
    private Button btnAbestiak;

    @FXML
    private Button btnRanking;

    @FXML
    private Button btnBack;

    @FXML
    private TableView<InfoModel> tvTaula;

    @FXML
    private TableColumn<InfoModel, String> tcNan;

    @FXML
    private TableColumn<InfoModel, String> tcIzena;

    @FXML
    private TableColumn<InfoModel, Integer> tcAdina;

    @FXML
    private TableColumn<InfoModel, String> tcHerria;


    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    public void setIzena(String i){
        izena = i;
    }


    @FXML
    void OnClick(ActionEvent event) throws SQLException {
        Button btn = (Button)event.getSource();

        if(btn.equals(btnAbestiak)){
            main.getAbestiListaKud().informazioaKargatu(AbeslariDB.getInstance().lortuId(izena));
            main.pantailaratuAbestiLista();
        }
        else if(btn.equals(btnRanking)){
            main.getRankingKud().setChivato(false);
            main.getRankingKud().informazioaKargatu();
            main.pantailaratuRanking();
        }
        else if(btn.equals(btnBack)){
            main.pantailaratuLogin();
        }
    }

    public void informazioaKargatu(){
        List<InfoModel> kargatzekoa = AbeslariDB.getInstance().informazioaLortu(izena);
        ObservableList<InfoModel> Partaideak = FXCollections.observableArrayList(kargatzekoa);

        //add your data to the table here.
        tvTaula.setItems(Partaideak);
        tvTaula.setEditable(false);


        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class

        tcNan.setCellValueFactory(new PropertyValueFactory<>("nan"));
        tcIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        tcAdina.setCellValueFactory(new PropertyValueFactory<>("adina"));
        tcHerria.setCellValueFactory(new PropertyValueFactory<>("herria"));

    }

}
