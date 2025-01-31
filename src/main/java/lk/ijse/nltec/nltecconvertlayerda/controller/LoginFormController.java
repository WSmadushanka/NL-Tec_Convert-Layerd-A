package lk.ijse.nltec.nltecconvertlayerda.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.nltec.nltecconvertlayerda.bo.BOFactory;
import lk.ijse.nltec.nltecconvertlayerda.bo.custom.UserBO;
import lk.ijse.nltec.nltecconvertlayerda.util.validation.Regex;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane rootNode;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERBO);

    @FXML
    void btnLoginOnAction() {
        String userName = txtUserName.getText();
        String pw = txtPassword.getText();

        try {
            if(isValied()) { // Validated
                checkCredential(userName, pw);
            }else{
                new Alert(Alert.AlertType.INFORMATION, "The data you entered is incorrect").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void checkCredential(String user_name, String pw) throws SQLException, ClassNotFoundException, IOException {
        String db = userBO.Checkcredential(user_name);

        if (db == null) {
            new Alert(Alert.AlertType.ERROR, "Sorry, user name not found!").show();
        }else {
            if (pw.equals(db)) {

                navigateToTheDashboard(user_name);
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry, the password is incorrect!").show();
            }
        }
    }

    private void navigateToTheDashboard(String user_name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/sidepanelform.fxml"));
        Parent dashboardRoot = loader.load();
        SidepanelFormController controller = loader.getController();
        controller.setUserName(user_name); // Pass the username to the DashboardFormController

        Scene scene = new Scene(dashboardRoot);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");
    }

    @FXML
    void hyperRegistrationOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/registration.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage)this.rootNode.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtUserName.requestFocus();
    }

    // Validation -------------------------------------------------------------------------------

    @FXML
    void txtUserNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.NAME,txtUserName);
    }

    @FXML
    void txtPasswordOnActionKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PASSWORD,txtPassword);
    }

    private boolean isValied() {
        if (! Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.NAME,txtUserName))return false;
        if (! Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PASSWORD,txtPassword))return false;
        return true;
    }

}
