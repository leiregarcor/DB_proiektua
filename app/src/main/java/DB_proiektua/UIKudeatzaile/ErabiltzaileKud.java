package DB_proiektua.UIKudeatzaile;

import DB_proiektua.Main;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class ErabiltzaileKud {


    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }

        @FXML
        void bozkatuClick(ActionEvent event) {
            main.pantailartuBozkatu();
        }

        @FXML
        void rankingClick(ActionEvent event) {
            main.pantailaratuRanking();
        }


}
