package DB_proiektua;

import DB_proiektua.UIKudeatzaile.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Parent mainUI;
    private Parent adminUI;
    private Parent abeslariUI;
    private Parent erabiltzaileUI;
    private Parent bozkatuUI;

    private Stage stage;


    //kontrolatzaileak
    private LogInKud logInKud;
    private AdminKud adminKud;
    private AbeslariKud abeslariKud;
    private ErabiltzaileKud erabiltzaileKud;
    private BozkatuKud bozkatuKud;

    //scene ak
    private Scene sceneM;
    private Scene sceneAdmin;
    private Scene sceneErabiltzaile;
    private Scene sceneAbeslari;
    private Scene sceneBozkatu;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        stage.setTitle("Song Contest");

        //main pantaila
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        mainUI = (Parent) mainLoader.load();
        logInKud=mainLoader.getController();
        logInKud.setMain(this);

        //admin pantaila
        FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/AdminScene.fxml"));
        adminUI = (Parent) adminLoader.load();
        adminKud=adminLoader.getController();
        adminKud.setMain(this);

        /*
        //abeslari pantaila
        FXMLLoader abeslariLoader = new FXMLLoader(getClass().getResource("/AbeslariScene.fxml"));
        abeslariUI = (Parent) adminLoader.load();
        abeslariKud=adminLoader.getController();
        abeslariKud.setMain(this);

        */

        //erabiltzaile pantaila
        FXMLLoader erabiltzaileLoader = new FXMLLoader(getClass().getResource("/ErabiltzaileScene.fxml"));
        erabiltzaileUI = (Parent) erabiltzaileLoader.load();
        erabiltzaileKud=erabiltzaileLoader.getController();
        erabiltzaileKud.setMain(this);

        //bozkatu pantaila
        FXMLLoader BozkatuLoader = new FXMLLoader(getClass().getResource("/BozkatuScene.fxml"));
        bozkatuUI = (Parent) BozkatuLoader.load();
        bozkatuKud=BozkatuLoader.getController();
        bozkatuKud.setMain(this);



        //kargatu pantailak
        sceneM = new Scene(mainUI);
        sceneAdmin=new Scene(adminUI);
//        sceneAbeslari=new Scene(abeslariUI);
        sceneErabiltzaile=new Scene(erabiltzaileUI);
        sceneBozkatu=new Scene(bozkatuUI);

        stage.setScene(sceneM);
        stage.show();
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

    public void pantailartuBozkatu() { stage.setScene(sceneBozkatu); }

    public void pantailaratuRanking() {

    }
}
