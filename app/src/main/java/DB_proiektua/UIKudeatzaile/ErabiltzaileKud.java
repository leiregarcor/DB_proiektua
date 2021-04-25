package DB_proiektua.UIKudeatzaile;

import DB_proiektua.DBKudeatzailea.AbeslariDB;
import DB_proiektua.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ErabiltzaileKud {


    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }


    @FXML
    private Button btnRanking;

    @FXML
    private Button btnBotoa;

    @FXML
    private Button btnBack;


    @FXML
    void OnClick(ActionEvent event) {
        Button btn = (Button)event.getSource();

        if(btn.equals(btnBotoa)){
            //TODO aqui se vota
        }
        else if(btn.equals(btnRanking)){
            main.getRankingKud().setChivato(true);
            main.getRankingKud().informazioaKargatu();
            main.pantailaratuRanking();
        }
        else if(btn.equals(btnBack)){
            main.pantailaratuLogin();
        }
    }
}
