package DB_proiektua;

import DB_proiektua.UIKudeatzaile.AbeslariKud;
import DB_proiektua.UIKudeatzaile.AdminKud;
import DB_proiektua.UIKudeatzaile.ErabiltzaileKud;
import DB_proiektua.UIKudeatzaile.LogInKud;
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

    private Stage stage;

    //kontrolatzaileak
    private LogInKud logInKud;
    private AdminKud adminKud;
    private AbeslariKud abeslariKud;
    private ErabiltzaileKud erabiltzaileKud;

    //scene ak
    private Scene sceneM;
    private Scene sceneAdmin;
    private Scene sceneErabiltzaile;
    private Scene sceneAbeslari;


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

        //erabiltzaile pantaila
        FXMLLoader erabiltzaileLoader = new FXMLLoader(getClass().getResource("/ErabiltzaileScene.fxml"));
        erabiltzaileUI = (Parent) adminLoader.load();
        erabiltzaileKud=adminLoader.getController();
        erabiltzaileKud.setMain(this);


         */

        //kargatu pantailak
        sceneM = new Scene(mainUI);
        sceneAdmin=new Scene(adminUI);
//        sceneAbeslari=new Scene(abeslariUI);
//        sceneErabiltzaile=new Scene(erabiltzaileUI);

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
}
