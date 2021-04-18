package DB_proiektua.UIKudeatzaile;


import DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import DB_proiektua.model.AdminModel;
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

    ObservableList<AdminModel> emaitza= FXCollections.observableArrayList();

    private Main main;

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

        emaitza.remove(adminModel);

        //berriz seteatu taula
        taulaAbeslariak.setItems(emaitza);


    }

    private AdminModel aurkituAbeslaria() {
        return emaitza.stream()
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
        String query="SELECT a.generoa,a.izena as abestia,p.izena,p.id FROM Abestia as a inner join ParteHartzaile as p on a.ParteHartzaileID=p.id";

        ResultSet resultSet=DBKudeatzaile.getInstantzia().execSQL(query);

        tblAbeslari.setCellValueFactory(new PropertyValueFactory<>("izena"));
        tblAbestia.setCellValueFactory(new PropertyValueFactory<>("abestia"));
        tblGeneroa.setCellValueFactory((new PropertyValueFactory<>("generoa")));
        colAbeslariID.setCellValueFactory(new PropertyValueFactory<>("abeslariID"));

        try {
            datuakSartu(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        paneEzabatu.setVisible(false);
    }

    private void datuakSartu(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            AdminModel adminModel=new AdminModel();
            adminModel.setAbestia(resultSet.getString("abestia"));
            adminModel.setIzena(resultSet.getString("izena"));
            adminModel.setGeneroa(resultSet.getString("generoa"));
            adminModel.setAbeslariID(resultSet.getInt("id"));

            emaitza.add(adminModel);
        }
        taulaAbeslariak.setItems(emaitza);

    }
}
