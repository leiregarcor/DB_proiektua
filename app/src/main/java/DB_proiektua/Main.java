package DB_proiektua;

import DB_proiektua.UIKudeatzaile.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Parent mainUI;
    private Parent adminUI;
    private Parent abeslariUI;
    private Parent erabiltzaileUI;
    private Parent abestiListaUI;
    private Parent rankingUI;

    private Stage stage;

    //kontrolatzaileak
    private LogInKud logInKud;
    private AdminKud adminKud;
    private AbeslariKud abeslariKud;
    private ErabiltzaileKud erabiltzaileKud;
    private AbestiListaKud abestiListaKud;
    private RankingKud rankingKud;

    //scene ak
    private Scene sceneM;
    private Scene sceneAdmin;
    private Scene sceneErabiltzaile;
    private Scene sceneAbeslari;
    private Scene sceneAbestiLista;
    private Scene sceneRanking;

    @Override
    public void start(Stage primaryStage) throws Exception {
        baliabideakKargatu();

        stage = primaryStage;
        stage.setTitle("Song Contest");

        //kargatu pantailak
        stage.setScene(sceneM);
        stage.show();
    }

    private void baliabideakKargatu() throws IOException {
        //main pantaila
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        mainUI = (Parent) mainLoader.load();
        logInKud=mainLoader.getController();
        logInKud.setMain(this);
        sceneM = new Scene(mainUI);

        //admin pantaila
        FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/AdminScene.fxml"));
        adminUI = (Parent) adminLoader.load();
        adminKud=adminLoader.getController();
        adminKud.setMain(this);
        sceneAdmin = new Scene(adminUI);

        //abeslari pantaila
        FXMLLoader abeslariLoader = new FXMLLoader(getClass().getResource("/AbeslariScene.fxml"));
        abeslariUI = (Parent) abeslariLoader.load();
        abeslariKud=abeslariLoader.getController();
        abeslariKud.setMain(this);
        sceneAbeslari = new Scene(abeslariUI);

        //erabiltzaile pantaila
        FXMLLoader erabiltzaileLoader = new FXMLLoader(getClass().getResource("/ErabiltzaileaScene.fxml"));
        erabiltzaileUI = (Parent) erabiltzaileLoader.load();
        erabiltzaileKud=erabiltzaileLoader.getController();
        erabiltzaileKud.setMain(this);
        sceneErabiltzaile = new Scene(erabiltzaileUI);

        //abestien lista pantaila
        FXMLLoader abestiListaLoader = new FXMLLoader(getClass().getResource("/abestiLista.fxml"));
        abestiListaUI = (Parent) abestiListaLoader.load();
        abestiListaKud=abestiListaLoader.getController();
        abestiListaKud.setMain(this);
        sceneAbestiLista = new Scene(abestiListaUI);

        //ranking pantaila
        FXMLLoader rankingLoader = new FXMLLoader(getClass().getResource("/ranking.fxml"));
        rankingUI = (Parent) rankingLoader.load();
        rankingKud=rankingLoader.getController();
        rankingKud.setMain(this);
        sceneRanking = new Scene(rankingUI);

        /*
        //erabiltzaile pantaila
        FXMLLoader erabiltzaileLoader = new FXMLLoader(getClass().getResource("/ErabiltzaileScene.fxml"));
        erabiltzaileUI = (Parent) adminLoader.load();
        erabiltzaileKud=adminLoader.getController();
        erabiltzaileKud.setMain(this);

         */

    }

    public void pantailaratuAdmin(){
        stage.setScene(sceneAdmin);
    }

    public void pantailaratuAbeslari() {
        stage.setScene(sceneAbeslari);
    }

    public void pantailaratuErabiltzaile() {
        stage.setScene(sceneErabiltzaile);
    }

    public void pantailaratuAbestiLista(){stage.setScene(sceneAbestiLista);}

    public void pantailaratuRanking(){stage.setScene(sceneRanking);}

    public void pantailaratuLogin(){stage.setScene(sceneM);}



    //GET
    public AbestiListaKud getAbestiListaKud() {
        return abestiListaKud;
    }

    public AbeslariKud getAbeslariKud(){
        return this.abeslariKud;
    }

    public RankingKud getRankingKud(){return rankingKud;}
}
