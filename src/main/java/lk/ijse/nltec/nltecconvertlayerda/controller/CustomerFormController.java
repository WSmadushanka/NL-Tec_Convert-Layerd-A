package lk.ijse.nltec.nltecconvertlayerda.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.nltec.nltecconvertlayerda.bo.BOFactory;
import lk.ijse.nltec.nltecconvertlayerda.bo.custom.CustomerBO;
import lk.ijse.nltec.nltecconvertlayerda.dto.CustomerDTO;
import lk.ijse.nltec.nltecconvertlayerda.util.QrGenerateor;
import lk.ijse.nltec.nltecconvertlayerda.util.validation.Regex;
import lk.ijse.nltec.nltecconvertlayerda.view.tdm.tm.CustomerTm;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearchCustomers;

    @FXML
    private TextField txtTel;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMERBO);

    public void initialize(){
        setCellValueFactory();
        loadAllCustomers();
        getCustomerTel();
        getCurrentId();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("cName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("cAddress"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("cNIC"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("cEmail"));
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDTO> customerList = customerBO.getAllCustomers();
            for (CustomerDTO customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getCustId(),
                        customer.getCName(),
                        customer.getCAddress(),
                        customer.getCNIC(),
                        customer.getContactNo(),
                        customer.getCEmail()
                );

                obList.add(tm);
            }

            tblCustomer.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerTel() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> telList = customerBO.getCustomerTel();

            for (String tel : telList) {
                obList.add(tel);
            }

            TextFields.bindAutoCompletion(txtSearchCustomers, obList); // Set Data List in Text Field

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getCurrentId() {
        String nextId = "";

        try {

            nextId = customerBO.generateNewID();
            txtId.setText(nextId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException,ClassNotFoundException{
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String nic = txtNIC.getText();
        String contact = txtTel.getText();
        String email = txtEmail.getText();

        CustomerDTO customer = new CustomerDTO(id,name,address,nic,contact,email);

        try {
            if (isValidate()){
                boolean isSaved = customerBO.saveCustomer(customer);
                if (isSaved){
                    QrGenerateor.setData(contact,email,1);
                    new Alert(Alert.AlertType.CONFIRMATION,"customer saved!").show();
                    clearFields();
                    initialize();
                }
            }else {
                new Alert(Alert.AlertType.INFORMATION,"The data you entered is incorrect").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update Customer?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String nic = txtNIC.getText();
            String contact = txtTel.getText();
            String email = txtEmail.getText();

            CustomerDTO customer = new CustomerDTO(id, name, address, nic, contact, email); // Set Customer Data

            try {
                if (isValidate()) { // Add Validation
                    System.out.println(customer);
                    boolean isUpdated = customerBO.updateCustomer(customer);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                        clearFields();
                        initialize();
                    }
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "The data you entered is incorrect").show();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnDeleteOnAction() {// Delete Customers
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete Customer?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtId.getText();

            try {
                boolean isDeleted = customerBO.deleteCustomer(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                    clearFields();
                    initialize();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtNIC.setText("");
        txtTel.setText("");
        txtEmail.setText("");
        txtSearchCustomers.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void txtSearchCustomersOnAction(ActionEvent event) {
        btnSearchCustomersOnAction();
    }

    @FXML
    void btnSearchCustomersOnAction( ) {
        try {
            String tel = txtSearchCustomers.getText();

            CustomerDTO customer = customerBO.searchCustomer(tel);
            ;
            if (customer != null) {
                txtId.setText(customer.getCustId());
                txtName.setText(customer.getCName());
                txtNIC.setText(customer.getCNIC());
                txtAddress.setText(customer.getCAddress());
                txtEmail.setText(customer.getCEmail());
                txtTel.setText(customer.getContactNo());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Focus Actions -------------------------------------------------------
    @FXML
    void nameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void addressOnAction(ActionEvent event) {
        txtNIC.requestFocus();
    }

    @FXML
    void nicOnAction(ActionEvent event) {
        txtTel.requestFocus();
    }

    @FXML
    void telOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    // Validation---------------------------------------------------------------------------------------------------------------------------------
    @FXML
    void txtAddressOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.ADDRESS,txtAddress);
    }

    @FXML
    void txtCustIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.CID,txtId);
    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.EMAIL,txtEmail);
    }

    @FXML
    void txtNicOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.NIC,txtNIC);
    }

    @FXML
    void txtTelOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PHONENO,txtTel);
    }

    private boolean isValidate() {
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.CID,txtId)) return false;
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.NIC,txtNIC)) return false;
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.ADDRESS,txtAddress))return false;
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PHONENO,txtTel)) return false;
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.EMAIL,txtEmail)) return false;

        return true;
    }

}
