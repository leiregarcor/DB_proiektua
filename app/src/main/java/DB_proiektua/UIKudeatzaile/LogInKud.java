package DB_proiektua.UIKudeatzaile;

import DB_proiektua.DBKudeatzailea.DBKudeatzaile;
import DB_proiektua.Main;
import com.google.common.base.CharMatcher;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.Zifraketa;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class LogInKud {

    @FXML
    private TextField txtIzena;

    @FXML
    private PasswordField txtPass;

    private Main main;

    public void setMain(Main mainApp){
        this.main=mainApp;
    }


    @FXML
    void onClick() {

        try {
            lortuErabiltzailea();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * bi erabiltzaile daude:
     * ERABILTZAILE | PASAHITZA
     * admin        | admin             hau admin da(dudak baldin bazenuten)
     * rita         | poyo              erabiltzaile arrunta
     * songExpert13 | bebelin           erabiltzaile arrunta
     * Perea        | tresly            erabiltzaile arrunta
     * bakunin      | eliza             erabiltzaile arrunta
     * pikachu      | pikapika          erabiltzaile arrunta
     * mesi         | txorizo           abeslaria
     * CR7          | SIUU              abeslaria
     * bisbol       | avemaria          abeslaria
     * perro        | sanche
     */


    private boolean lortuErabiltzailea() throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {

        boolean bool=false;

        String izena=CharMatcher.anyOf("'#/@").removeFrom(txtIzena.getText());

        //SELECT ErabiltzaileIzena, ModoBorbon,idErabiltzaileak FROM Erabiltzaileak WHERE ErabiltzaileIzena='admin' AND ErabiltzaileGako='21232f297a57a5a743894a0e4a801fc3'
        String query="SELECT ErabiltzaileIzena, ModoBorbon,idErabiltzaileak FROM Erabiltzaileak WHERE ErabiltzaileIzena='"+
                izena+"' AND ErabiltzaileGako='"+ Zifraketa.getInstance().zifratuGakoa(txtPass.getText())+"'";

        var emaitza= DBKudeatzaile.getInstantzia().execSQL(query);

        txtPass.setText("");
        txtIzena.setText("");

        if(emaitza.next()){
            switch (emaitza.getString("ModoBorbon")){
                case("admin"):
                    main.pantailaratuAdmin();
                    bool=true;
                    break;
                case("abeslari"):
                    main.pantailaratuAbeslari();
                    main.getAbeslariKud().setIzena(emaitza.getString("ErabiltzaileIzena"));
                    main.getAbeslariKud().informazioaKargatu();
                    bool=true;
                    break;
                case("erabiltzaile"):
                    main.getBozkatuKud().setErabID(emaitza.getInt("idErabiltzaileak"));
                    main.pantailaratuErabiltzaile();
                    bool=true;
                    break;
            }
        }

        return bool;

    }




}
