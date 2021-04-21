package DB_proiektua.UIKudeatzaile;


import DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import DB_proiektua.model.AdminModel;
import DB_proiektua.model.ErabiltzaileModel;
import DB_proiektua.model.PuntuazioAdminModel;
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

    ObservableList<PuntuazioAdminModel> puntuLista = FXCollections.observableArrayList();



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


    //----------------------------------------------------------------//
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


    //-----------------------------------------------------------------//
    //ERABILTZAILE-ABESLARI-PUNTU
    @FXML
    private TableView<PuntuazioAdminModel> taulaPuntuak;

    @FXML
    private TableColumn<?, ?> colErabiltzailePuntu;

    @FXML
    private TableColumn<?, ?> colNoizPuntu;

    @FXML
    private TableColumn<?, ?> colAbeslariPuntu;

    @FXML
    private TableColumn<?, ?> colPuntuak;


    @FXML
    void onClickBueltatu(ActionEvent event) {
        //TODO: bueltatu
        System.out.println("bueltatu");
    }

    @FXML
    void onClickEzabatuAbeslaria(ActionEvent event) {
        //TODO: agian beste tauletatik ezabatu??

        //DELETE FROM ParteHartzaile WHERE id=29;
        String query1="DELETE FROM ParteHartzaile WHERE id="+txtAbeslariID.getText();

        DBKudeatzaile.getInstantzia().execSQL(query1);


        lblEzabatuMezua.setText(txtAbeslariID.getText()+" IDko abeslaria ezabatu da!");
        paneEzabatu.setVisible(true);

        AdminModel adminModel=aurkituAbeslaria();

        abeslariLista.remove(adminModel);

        //berriz seteatu taula
        taulaAbeslariak.setItems(abeslariLista);


    }

    private AdminModel aurkituAbeslaria() {
        return abeslariLista.stream()
                .filter(p->p.getAbeslariID()==Integer.parseInt(txtAbeslariID.getText()))
                .findFirst()
                .orElse(null);
    }


    @FXML
    void onClickEzabatuErabiltzaile(ActionEvent event) {
        //DELETE FROM Erabiltzaileak WHERE idErabiltzaileak=2;
        String query="DELETE FROM Erabiltzaileak WHERE idErabiltzaileak="+txtErabiltzaile.getText();

        DBKudeatzaile.getInstantzia().execSQL(query);

        lblEzabatuErabMezua.setText(txtErabiltzaile.getText()+" IDko abeslaria ezabatu da!");
        paneEzabatuErabiltzaile.setVisible(true);

        ErabiltzaileModel adminModel=aurkituErabiltzailea();

        erabiltzaileLista.remove(adminModel);

        //berriz seteatu taula
        taulaErabiltzaile.setItems(erabiltzaileLista);

    }

    private ErabiltzaileModel aurkituErabiltzailea() {
        return erabiltzaileLista.stream()
                .filter(p->p.getId()==Integer.parseInt(txtErabiltzaile.getText()))
                .findFirst()
                .orElse(null);
    }


    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ResultSet abeslariRS=kargatuAbeslariTaula();

        ResultSet erabiltzaileRS=kargatuErabiltzaileTaula();

        ResultSet puntuRS=kargatuPuntuTaula();

        try {
            //sartu DBko datuak taulan (abeslari)
            datuakSartuAbeslari(abeslariRS);

            //sartu DBko datuak taulan (erabiltzaile)
            datuakSartuErabiltzaile(erabiltzaileRS);

            //sartu DBko datuak taulan (puntuen movida hori)
            datuakSartuPuntu(puntuRS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        paneEzabatu.setVisible(false);
        paneEzabatuErabiltzaile.setVisible(false);
    }


    //KARGATU TAULAK --> RETURN RESULTSET

    private ResultSet kargatuErabiltzaileTaula() {
        //SELECT ErabiltzaileIzena,Erabiltzaileak.idErabiltzaileak FROM Eurobisio.Erabiltzaileak WHERE ModoBorbon!='admin';
        String erabiltzaileQuery="SELECT ErabiltzaileIzena, idErabiltzaileak FROM Eurobisio.Erabiltzaileak WHERE ModoBorbon!='admin'";

        ResultSet erabiltzaileRS=DBKudeatzaile.getInstantzia().execSQL(erabiltzaileQuery);

        colErabilID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colErabIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));

        return erabiltzaileRS;
    }

    private ResultSet kargatuAbeslariTaula() {
        //SELECT a.generoa,a.izena,p.izena FROM Abestia as a inner join ParteHartzaile as p on a.ParteHartzaileID=p.id
        String abeslariQuery="SELECT a.generoa,a.izena as abestia,p.izena,p.id FROM Abestia as a inner join ParteHartzaile as p on a.ParteHartzaileID=p.id";

        ResultSet resultSet=DBKudeatzaile.getInstantzia().execSQL(abeslariQuery);

        tblAbeslari.setCellValueFactory(new PropertyValueFactory<>("izena"));
        tblAbestia.setCellValueFactory(new PropertyValueFactory<>("abestia"));
        tblGeneroa.setCellValueFactory((new PropertyValueFactory<>("generoa")));
        colAbeslariID.setCellValueFactory(new PropertyValueFactory<>("abeslariID"));

        return resultSet;
    }

    private ResultSet kargatuPuntuTaula() {
        //SELECT e.ErabiltzaileIzena,b.dataPH,p.Izena, err.puntuazioa FROM Bozkaketa as b,ParteHartzaile as p,Erabiltzaileak as e, Erregistratu as err WHERE b.idAbeslari=p.id AND e.idErabiltzaileak=b.idErab AND err.ParteHartzaileID=p.id ORDER BY puntuazioa DESC
        String puntuazioQuery="SELECT e.ErabiltzaileIzena as erabIzen,b.dataPH as data,p.Izena as abeslariIzen, err.puntuazioa FROM Bozkaketa as b,ParteHartzaile as p,Erabiltzaileak as e, Erregistratu as err WHERE b.idAbeslari=p.id AND e.idErabiltzaileak=b.idErab AND err.ParteHartzaileID=p.id ORDER BY puntuazioa DESC";

        ResultSet resultSet=DBKudeatzaile.getInstantzia().execSQL(puntuazioQuery);

        colErabiltzailePuntu.setCellValueFactory(new PropertyValueFactory<>("erabIzen"));
        colNoizPuntu.setCellValueFactory(new PropertyValueFactory<>("data"));
        colAbeslariPuntu.setCellValueFactory(new PropertyValueFactory<>("abeslariIzen"));
        colPuntuak.setCellValueFactory(new PropertyValueFactory<>("puntuazioa"));

        return resultSet;

    }


    //GUIko TAULAK KARGATU

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

    private void datuakSartuPuntu(ResultSet puntuRS) throws SQLException {
        while (puntuRS.next()){
            PuntuazioAdminModel puntuazioAdminModel=new PuntuazioAdminModel();
            puntuazioAdminModel.setErabIzen(puntuRS.getString("erabIzen"));
            puntuazioAdminModel.setData(puntuRS.getString("data"));
            puntuazioAdminModel.setAbeslariIzen(puntuRS.getString("abeslariIzen"));
            puntuazioAdminModel.setPuntuazioa(puntuRS.getInt("puntuazioa"));

            puntuLista.add(puntuazioAdminModel);
        }
        taulaPuntuak.setItems(puntuLista);
    }

}
