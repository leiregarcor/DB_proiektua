package DB_proiektua.UIKudeatzaile;

import DB_proiektua.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BozkatuKud implements Initializable {

    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

    @FXML
    private ComboBox<String> aukeratu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
