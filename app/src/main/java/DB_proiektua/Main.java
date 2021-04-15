package DB_proiektua;

import DB_proiektua.UIKudeatzaile.AdminKud;
import DB_proiektua.UIKudeatzaile.LogInKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Parent mainUI;
    private Parent adminUI;

    private Stage stage;

    //kontrolatzaileak
    private LogInKud logInKud;
    private AdminKud adminKud;

    //scene ak
    private Scene sceneM;
    private Scene sceneAdmin;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        stage.setTitle("Song Contest");

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        mainUI = (Parent) mainLoader.load();
        logInKud=mainLoader.getController();
        logInKud.setMain(this);

        FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/AdminScene.fxml"));
        adminUI = (Parent) adminLoader.load();
        adminKud=adminLoader.getController();
        adminKud.setMain(this);


        //kargatu pantailak
        sceneM = new Scene(mainUI);
        sceneAdmin=new Scene(adminUI);


        stage.setScene(sceneM);
        stage.show();
    }

    public void pantailaratuAdmin(){
        stage.setScene(sceneAdmin);
    }

}
