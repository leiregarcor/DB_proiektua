package DB_proiektua.UIKudeatzaile;

import DB_proiektua.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import DB_proiektua.DBKudeatzailea.AbeslariDB;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class AbeslariKud {

    private int id;

    @FXML
    private Label lbIzena;

    @FXML
    private Label lblOstatu;

    @FXML
    private Button btnAbestiak;

    @FXML
    private Button btnRanking;


    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    public void setId(int i){
        id = i;
    }

    @FXML
    void OnClick(ActionEvent event) {
        Button btn = (Button)event.getSource();

        if(btn.equals(btnAbestiak)){
            AbeslariDB.getInstance().ikusiAbestiak(id);
        }
        if(btn.equals(btnRanking)){
            AbeslariDB.getInstance().ikusiRanking();
        }
    }


}
