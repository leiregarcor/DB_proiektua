package DB_proiektua.UIKudeatzaile;

import DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class LogInKud {

    @FXML
    private TextField txtIzena;

    @FXML
    private PasswordField txtPass;

    private Main main;

    @FXML
    void onClick(ActionEvent event) {
        //TODO: klik egitean erabiltzailea bilatu datu basean
        System.out.println("ez mesedez:( min egiten du");
        try {
            boolean bool = lortuErabiltzailea();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * bi erabiltzaile daude:
     * ERABILTZAILE | PASAHITZA
     * admin        | admin             hau admin da(dudak baldin bazenuten)
     * bisbol       | avemaria          honek ez du admin baimenik
     * rita         | poyo              erabiltzaile arrunta
     */


    private boolean lortuErabiltzailea() throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        //TODO: oraindik ere SQLi erasoa izan dezake programak....

        boolean bool=false;
        //SELECT ErabiltzaileIzena, ModoBorbon FROM Erabiltzaileak WHERE ErabiltzaileIzena='admin' AND ErabiltzaileGako='21232f297a57a5a743894a0e4a801fc3'
        String query="SELECT ErabiltzaileIzena, ModoBorbon FROM Erabiltzaileak WHERE ErabiltzaileIzena='"+
                txtIzena.getText()+"' AND ErabiltzaileGako='"+zifratuGakoa(txtPass.getText())+"'";

        var emaitza=DBKudeatzaile.getInstantzia().execSQL(query);

        if(emaitza.next()){
            switch (emaitza.getString("ModoBorbon")){
                case("admin"):
                    main.pantailaratuAdmin();
                    bool=true;
                    break;
                case("abeslari"):
                    main.pantailaratuAbeslari();
                    bool=true;
                    break;
                case("erabiltzaile"):
                    main.pantailaratuErabiltzaile();
                    bool=true;
                    break;
            }
        }


        return bool;

    }

    //Javan String bat zifratzeko
    private String zifratuGakoa(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hashedBytes = digest.digest(text.getBytes("UTF-8"));

        return convertByteArrayToHexString(hashedBytes);
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }



    public void setMain(Main mainApp){
        this.main=mainApp;
    }


}
