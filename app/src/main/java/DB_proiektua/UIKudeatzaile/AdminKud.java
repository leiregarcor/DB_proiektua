package DB_proiektua.UIKudeatzaile;


import DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import DB_proiektua.model.AdminModel;
import DB_proiektua.model.ErabiltzaileModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminKud implements Initializable {

    ObservableList<AdminModel> abeslariLista = FXCollections.observableArrayList();

    ObservableList<ErabiltzaileModel> erabiltzaileLista = FXCollections.observableArrayList();


    private Main main;


    //ABESLARI
    @FXML
    private TableView<AdminModel> taulaAbeslariak;

    @FXML
    private TableColumn<?, ?> colAbeslariID;

    @FXML
    private TableColumn<?, ?> tblAbeslari;

    @FXML
    private TableColumn<?, ?> tblGeneroa;

    @FXML
    private TableColumn<?, ?> tblAbestia;

    @FXML
    private TextField txtAbeslariID;

    @FXML
    private Label lblEzabatuMezua;

    @FXML
    private Pane paneEzabatu;


    //ERABILTZAILE
    @FXML
    private TableView<ErabiltzaileModel> taulaErabiltzaile;

    @FXML
    private TableColumn<?, ?> colErabilID;

    @FXML
    private TableColumn<?, ?> colErabIzena;

    @FXML
    private TextField txtErabiltzaile;

    @FXML
    private Pane paneEzabatuErabiltzaile;

    @FXML
    private Label lblEzabatuErabMezua;

    @FXML
    void onClick(ActionEvent event) {
        //TODO: bueltatu
        System.out.println("bueltatu");
    }

    @FXML
    void onClickEzabatuAbeslaria(ActionEvent event) {
        //DELETE FROM ParteHartzaile WHERE id=29;
        String query1="DELETE FROM ParteHartzaile WHERE id="+txtAbeslariID.getText();

        //DELETE FROM Abestia WHERE ParteHartzaileID=122;
        String query2="DELETE FROM Abestia WHERE ParteHartzaileID="+txtAbeslariID.getText();

        DBKudeatzaile.getInstantzia().execSQL(query2);
        DBKudeatzaile.getInstantzia().execSQL(query1);


        lblEzabatuMezua.setText(txtAbeslariID.getText()+" IDko abeslaria ezabatu da!");
        paneEzabatu.setVisible(true);

        AdminModel adminModel=aurkituAbeslaria();

        abeslariLista.remove(adminModel);

        //berriz seteatu taula
        taulaAbeslariak.setItems(abeslariLista);


    }

    @FXML
    void onClickEzabatuErabiltzaile(ActionEvent event) {
        //TODO: ezabatu erabiltzailea


    }

    private AdminModel aurkituAbeslaria() {
        return abeslariLista.stream()
                .filter(p->p.getAbeslariID()==Integer.parseInt(txtAbeslariID.getText()))
                .findFirst()
                .orElse(null);
    }


    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SELECT a.generoa,a.izena,p.izena FROM Abestia as a inner join ParteHartzaile as p on a.ParteHartzaileID=p.id
        String abeslariQuery="SELECT a.generoa,a.izena as abestia,p.izena,p.id, p.puntuazioa FROM Abestia as a inner join ParteHartzaile as p on a.ParteHartzaileID=p.id";

        ResultSet resultSet=DBKudeatzaile.getInstantzia().execSQL(abeslariQuery);

        tblAbeslari.setCellValueFactory(new PropertyValueFactory<>("izena"));
        tblAbestia.setCellValueFactory(new PropertyValueFactory<>("abestia"));
        tblGeneroa.setCellValueFactory((new PropertyValueFactory<>("generoa")));
        colAbeslariID.setCellValueFactory(new PropertyValueFactory<>("abeslariID"));


        //SELECT ErabiltzaileIzena,Erabiltzaileak.idErabiltzaileak FROM Eurobisio.Erabiltzaileak WHERE ModoBorbon!='admin';
        String erabiltzaileQuery="SELECT ErabiltzaileIzena, idErabiltzaileak FROM Eurobisio.Erabiltzaileak WHERE ModoBorbon!='admin'";

        ResultSet erabiltzaileRS=DBKudeatzaile.getInstantzia().execSQL(erabiltzaileQuery);

        colErabilID.setCellValueFactory(new PropertyValueFactory<>("izena"));
        colErabIzena.setCellValueFactory(new PropertyValueFactory<>("id"));



        try {
            datuakSartuAbeslari(resultSet);
            datuakSartuErabiltzaile(erabiltzaileRS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        paneEzabatu.setVisible(false);
    }

    private void datuakSartuErabiltzaile(ResultSet erabiltzaileRS) throws SQLException {
        while (erabiltzaileRS.next()){
            ErabiltzaileModel erabiltzaileModel=new ErabiltzaileModel();
            erabiltzaileModel.setId(erabiltzaileRS.getInt("idErabiltzaileak"));
            erabiltzaileModel.setIzena(erabiltzaileRS.getString("ErabiltzaileIzena"));

            erabiltzaileLista.add(erabiltzaileModel);
        }

        taulaErabiltzaile.setItems(erabiltzaileLista);
    }

    private void datuakSartuAbeslari(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            AdminModel adminModel=new AdminModel();
            adminModel.setAbestia(resultSet.getString("abestia"));
            adminModel.setIzena(resultSet.getString("izena"));
            adminModel.setGeneroa(resultSet.getString("generoa"));
            adminModel.setAbeslariID(resultSet.getInt("id"));

            abeslariLista.add(adminModel);
        }
        taulaAbeslariak.setItems(abeslariLista);

    }
}
