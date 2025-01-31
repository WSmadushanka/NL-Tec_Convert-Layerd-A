package lk.ijse.nltec.nltecconvertlayerda.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.nltec.nltecconvertlayerda.bo.BOFactory;
import lk.ijse.nltec.nltecconvertlayerda.bo.custom.UserBO;
import lk.ijse.nltec.nltecconvertlayerda.util.validation.Regex;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationFormController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERBO);

    @FXML
    void logInOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage)this.rootNode.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Log In");

        stage.centerOnScreen();
        stage.show();
    }


    @FXML
    void registrationOnAction() {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            if(isValied()){ // Add Validation
                boolean isSaved = userBO.saveUser(userName,password);
                if(isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
                    callLogIn();
                }
            }else{
                new Alert(Alert.AlertType.INFORMATION, "The data you entered is incorrect").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void callLogIn() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage)this.rootNode.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Log In");

        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void userNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        registrationOnAction();
    }

    @FXML
    void txtPasswordOnKeyAction(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PASSWORD,txtPassword);
    }

    @FXML
    void txtUserNameOnKeyAction(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.NAME,txtUserName);
    }

    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.NAME,txtUserName)) return false;
        if (!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PASSWORD,txtPassword)) return false;
        return true;
    }
}
