package lk.ijse.nltec.nltecconvertlayerda.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import lk.ijse.nltec.nltecconvertlayerda.bo.BOFactory;
import lk.ijse.nltec.nltecconvertlayerda.bo.custom.EmployeeBO;
import lk.ijse.nltec.nltecconvertlayerda.db.DbConnection;
import lk.ijse.nltec.nltecconvertlayerda.dto.EmployeeDTO;
import lk.ijse.nltec.nltecconvertlayerda.entity.QrResult;
import lk.ijse.nltec.nltecconvertlayerda.util.QrGenerateor;
import lk.ijse.nltec.nltecconvertlayerda.util.QrReader;
import lk.ijse.nltec.nltecconvertlayerda.util.validation.Regex;
import lk.ijse.nltec.nltecconvertlayerda.view.tdm.tm.CustomerTm;
import lk.ijse.nltec.nltecconvertlayerda.view.tdm.tm.EmployeeTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeFormController {

    @FXML
    private ImageView ImgView;

    @FXML
    private TableColumn<?, ?> colDOR;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colEid;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private Pane main_pain;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<EmployeeTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtEmail;

    @FXML
    private DatePicker txtEnrollDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearchEmployee;

    @FXML
    private TextField txtTel;

    private Image image;

    private QrReader qr;

    private QrResult qrResultModel;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEEBO);

    public EmployeeFormController(){
        qrResultModel = new QrResult();
    }

    public void initialize() {
        setCellValueFactory();
        loadAllEmployee();
        getEmpId();
        getCurrentId();
    }

    private void setCellValueFactory() {
        colEid.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("empNic"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("empTel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colDOR.setCellValueFactory(new PropertyValueFactory<>("dateRegister"));
    }

    private void loadAllEmployee() {// Load All Employees In EmployeeTm Table
    ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
        List<EmployeeDTO> employeeList = employeeBO.getAllEmployee();
        for (EmployeeDTO employee : employeeList) {
            EmployeeTm tm = new EmployeeTm(
                    employee.getEmpId(),
                    employee.getEmpName(),
                    employee.getEmpNic(),
                    employee.getPosition(),
                    employee.getEmpTel(),
                    employee.getDob(),
                    employee.getDateRegister(),
                    employee.getEmpEmail(),
                    employee.getSalary()
            );

            obList.add(tm);
        }

        tblCustomer.setItems(obList);
    } catch (
    SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String nic = txtNIC.getText();
        String position = txtPosition.getText();
        String contact = txtTel.getText();
        Date dob = Date.valueOf(txtDOB.getValue());
        Date dateRegistration = Date.valueOf(txtEnrollDate.getValue());
        String email = txtEmail.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String path = image.getUrl();

        EmployeeDTO employee = new EmployeeDTO(id, name, address, nic, position, contact , dob, dateRegistration, email, salary, path); // Set Employee Data

        try {
            if(isValidate()) {
                boolean isSaved = employeeBO.saveEmployee(employee);
                if (isSaved) {
                    QrGenerateor.setData(id, email, 3);
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                    clearFields();
                    initialize();
                }
            }else{
                new Alert(Alert.AlertType.INFORMATION, "The data you entered is incorrect").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Update Employee?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String nic = txtNIC.getText();
            String position = txtPosition.getText();
            String contact = txtTel.getText();
            Date dob = Date.valueOf(txtDOB.getValue());
            Date dateRegistration = Date.valueOf(txtEnrollDate.getValue());
            String email = txtEmail.getText();
            double salary = Double.parseDouble(txtSalary.getText());
            String path = image.getUrl();

            EmployeeDTO employee = new EmployeeDTO(id, name, address, nic, position, contact, dob, dateRegistration, email, salary, path); // Set Employee Data

            try {
                if (isValidate()) {
                    boolean isUpdated = employeeBO.updateEmployee(employee);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                        initialize();
                        clearFields();
                    }
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "The data you entered is incorrect").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete Employee?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            String id = txtId.getText();

            try {
                boolean isDeleted = employeeBO.deleteEmployee(id);// Delete Employee Data
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtNIC.setText("");
        txtPosition.setText("");
        txtTel.setText("");
        txtDOB.setValue(null);
        txtEnrollDate.setValue(null);
        txtEmail.setText("");
        txtSalary.setText("");
        ImgView.setImage(null);
        txtSearchEmployee.setText("");
    }

    private String getCurrentId() {
        String nextId = "";

        try {
            nextId = employeeBO.generateNewID();
            txtId.setText(nextId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }

    @FXML
    void btnImportImgOnAction() {// Search Image Path in Your PC
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(main_pain.getScene().getWindow());

        if (file != null) {
            image = new Image(file.toURI().toString(), 153, 176, false, true);
            ImgView.setImage(image);
        }
    }

    private void getEmpId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> telList = employeeBO.getEmployeeId();

            for(String tel : telList) {
                obList.add(tel);
            }
            TextFields.bindAutoCompletion(txtSearchEmployee,obList); // Load Employee Ids In Text Field

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchEmployeeOnAction(ActionEvent event) {
        try {
            btnSearchEmployeeOnAction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchEmployeeOnAction() throws SQLException, ClassNotFoundException {
        String tel = txtSearchEmployee.getText();

        EmployeeDTO emp = employeeBO.searchEmployee(tel); // Search Employees In Employee Id
        if (emp != null) {
            txtId.setText(emp.getEmpId());
            txtName.setText(emp.getEmpName());
            txtAddress.setText(emp.getEmpAddress());
            txtNIC.setText(emp.getEmpNic());
            txtPosition.setText(emp.getPosition());
            txtTel.setText(emp.getEmpTel());
            txtDOB.setValue(emp.getDob().toLocalDate());
            txtEnrollDate.setValue(emp.getDateRegister().toLocalDate());
            txtEmail.setText(emp.getEmpEmail());
            txtSalary.setText(String.valueOf(emp.getSalary()));
            image = new Image(emp.getPath(), 153, 176, false, true);
            ImgView.setImage(image);

        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    @FXML
    void btnScanOnAction(ActionEvent event) {
        qr = new QrReader(qrResultModel);
        new Thread(() -> {
            while (qrResultModel.getResult() == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            txtSearchEmployee.setText(qrResultModel.getResult());
        }).start();

        txtSearchEmployee.requestFocus();
    }

    @FXML
    void addressOnAction(ActionEvent event) {
        txtNIC.requestFocus();
    }

    @FXML
    void nameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void nicOnAction(ActionEvent event) {
        txtTel.requestFocus();
    }

    @FXML
    void telOnAction(ActionEvent event) {
        txtDOB.requestFocus();
    }

    @FXML
    void txtPositionOnAction(ActionEvent event) {
        btnImportImgOnAction();
    }

    @FXML
   public void txtSalOnAction(ActionEvent event) {
        txtPosition.requestFocus();
    }

    @FXML
    public void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    public void txtDOBOnAction(ActionEvent event) {
        txtEnrollDate.requestFocus();
    }

    @FXML
    public void txtRegDateOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    public void txtEmailOnAction(ActionEvent event) {
        txtSalary.requestFocus();
    }

    // Validation Part
    @FXML
    void txtAddressOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.ADDRESS,txtAddress);
    }

    @FXML
    void txtTelOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PHONENO,txtTel);
    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.EMAIL,txtEmail);
    }

    @FXML
    void txtEmpIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.EID,txtId);
    }

    @FXML
    void txtNicOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.NIC,txtNIC);
    }

    @FXML
    void txtSalOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PRICE,txtSalary);
    }

    public boolean isValidate() {
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.EID,txtId))return false;
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.NIC,txtNIC))return false;
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.ADDRESS,txtAddress))return false;
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PHONENO,txtTel))return false;
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.EMAIL,txtEmail))return false;
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.PRICE,txtSalary))return false;

        return true;
    }

    public boolean isIdValidate() {
        if(!Regex.setTextColor(lk.ijse.nltec.nltecconvertlayerda.util.validation.TextField.EID,txtId)) return false;

        return true;
    }

    // Employee Report Generate ----------------------------------------------------------------------------
    public void btnEmployeeReportOnAction(ActionEvent event) throws JRException, SQLException {
    JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/EmployeeReport.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

    Map<String,Object> data = new HashMap<>();
        if(isIdValidate()) {
        data.put("emp_id", txtId.getText());
    } else{
        new Alert(Alert.AlertType.INFORMATION, "Employee Id you entered is incorrect").show();
    }
    JasperPrint jasperPrint =
            JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
  }
}
