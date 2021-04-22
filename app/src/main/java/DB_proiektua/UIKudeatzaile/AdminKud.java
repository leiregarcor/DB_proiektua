package DB_proiektua.UIKudeatzaile;


import DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import DB_proiektua.model.AdminModel;
import DB_proiektua.model.ErabiltzaileModel;
import DB_proiektua.model.PuntuazioAdminModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import utils.Zifraketa;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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

    @FXML
    private TextField txtAbeslariBerria;



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

    @FXML
    private TextField txtErabiltzaileBerria;



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


    public void setMain(Main mainApp){
        this.main=mainApp;
    }



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
    void onClickAbeslariBerria(ActionEvent event) {
        String[] lista=txtAbeslariBerria.getText().split(",\\s+");

        // 7, 234567, Pedro Picapiedra, 9, Sestao City
        //INSERT INTO `Eurobisio`.`ParteHartzaile` (`id`, `NAN`, `Izena`, `adina`, `herria`) VALUES ('8', '213', 'Lenin', '1917', 'Sestao City');
        String query="INSERT INTO `Eurobisio`.`ParteHartzaile` (`id`, `NAN`, `Izena`, `adina`, `herria`) VALUES ('"+
                lista[0]+ //id
                "', '"+
                lista[1]+ //NAN
                "', '"+
                lista[2]+ //izena
                "', '"+
                lista[3]+ //adina
                "', '"+
                lista[4]+ //herria
                "')";

        DBKudeatzaile.getInstantzia().execSQL(query);

        lblEzabatuMezua.setText(lista[2]+" Abeslaria sartu da!");

        paneEzabatu.setVisible(true);

        AdminModel aM=new AdminModel();
        aM.setAbeslariID(Integer.parseInt(lista[0]));
        aM.setIzena(lista[2]);
        aM.setAbestia("---");
        aM.setGeneroa("---");
        abeslariLista.add(aM);

        taulaAbeslariak.setItems(abeslariLista);

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

    @FXML
    void onClickErabiltzaileBerria(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String[] lista=txtErabiltzaileBerria.getText().split(",\\s+");

        lista[1]=Zifraketa.getInstance().zifratuGakoa(lista[1]);

        //INSERT INTO `Eurobisio`.`Erabiltzaileak` (`ErabiltzaileIzena`, `ErabiltzaileGako`, `ModoBorbon`) VALUES ('cr7', 'ghjk', 'erabiltzaile');
        String query="INSERT INTO `Eurobisio`.`Erabiltzaileak` (`ErabiltzaileIzena`, `ErabiltzaileGako`, `ModoBorbon`) VALUES ('"+
                lista[0]+ //izena
                "', '"+
                lista[1]+  //pasahitza
                "', 'erabiltzaile')";

        DBKudeatzaile.getInstantzia().execSQL(query);

        lblEzabatuErabMezua.setText(lista[0]+" Erabiltzailea sortu da!");

        paneEzabatuErabiltzaile.setVisible(true);

        //Taulan erabiltzaile berria ager dadin
        erabiltzaileLista.remove(0,erabiltzaileLista.size());
        taulaErabiltzaile.refresh();

        ResultSet erabiltzaileRS=kargatuErabiltzaileTaula();
        datuakSartuErabiltzaile(erabiltzaileRS);



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