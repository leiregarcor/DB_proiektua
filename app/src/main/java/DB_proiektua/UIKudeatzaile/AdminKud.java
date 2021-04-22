package DB_proiektua.UIKudeatzaile;


import DB_proiektua.DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import DB_proiektua.model.AdminModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminKud implements Initializable {

    ObservableList<AdminModel> emaitza= FXCollections.observableArrayList();

    private Main main;

    @FXML
    private TableView<AdminModel> taulaAbeslariak;

    @FXML
    private TableColumn<?, ?> tblAbeslari;

    @FXML
    private TableColumn<?, ?> tblGeneroa;

    @FXML
    private TableColumn<?, ?> tblAbestia;

    @FXML
    void onClick(ActionEvent event) {
        //TODO: bueltatu
        System.out.println("bueltatu");
    }

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SELECT a.generoa,a.izena,p.izena FROM Abestia as a inner join ParteHartzaile as p on a.ParteHartzaileID=p.id
        String query="SELECT a.generoa,a.izena as abestia,p.izena FROM Abestia as a inner join ParteHartzaile as p on a.ParteHartzaileID=p.id";

        ResultSet resultSet=DBKudeatzaile.getInstantzia().execSQL(query);

        tblAbeslari.setCellValueFactory(new PropertyValueFactory<>("izena"));
        tblAbestia.setCellValueFactory(new PropertyValueFactory<>("abestia"));
        tblGeneroa.setCellValueFactory((new PropertyValueFactory<>("generoa")));

        try {
            datuakSartu(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private void datuakSartu(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            AdminModel adminModel=new AdminModel();
            adminModel.setAbestia(resultSet.getString("abestia"));
            adminModel.setIzena(resultSet.getString("izena"));
            adminModel.setGeneroa(resultSet.getString("generoa"));

            emaitza.add(adminModel);
        }
        taulaAbeslariak.setItems(emaitza);

    }
}
