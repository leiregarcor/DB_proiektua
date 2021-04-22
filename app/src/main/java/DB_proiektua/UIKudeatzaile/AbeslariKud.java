package DB_proiektua.UIKudeatzaile;

import DB_proiektua.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.checkerframework.checker.initialization.qual.Initialized;
import DB_proiektua.*;
import DB_proiektua.DBKudeatzaile.AbeslariDB;

import java.awt.*;

public class AbeslariKud {

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

    @FXML
    void OnClick(ActionEvent event) {
        Button btn = (Button)event.getSource();

        if(btn.equals(btnAbestiak)){
            AbeslariDB.getInstance().ikusiAbestiak();
        }
        if(btn.equals(btnRanking)){
            AbeslariDB.getInstance().ikusiRanking();
        }
    }


}
