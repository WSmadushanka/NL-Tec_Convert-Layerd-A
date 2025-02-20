package lk.ijse.nltec.nltecconvertlayerda.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.nltec.nltecconvertlayerda.bo.BOFactory;
import lk.ijse.nltec.nltecconvertlayerda.bo.custom.DashboardBO;
import lk.ijse.nltec.nltecconvertlayerda.dto.CustomDTO;
import lk.ijse.nltec.nltecconvertlayerda.dto.CustomerDTO;
import lk.ijse.nltec.nltecconvertlayerda.dto.ItemDTO;
import lk.ijse.nltec.nltecconvertlayerda.dto.OrderDTO;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class DashboardFormController {



    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colOrderCount;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label labCutCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblOrderCountlab;

    @FXML
    private Label lblItemQty;

    @FXML
    private PieChart pieChart;

    @FXML
    public TableView<CustomDTO> tblMostSellItems;

    @FXML
    public Label txtMonthlyProfit;

    @FXML
    private TextField txtOrderDate;

    DashboardBO dashboardBO = (DashboardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARDBO);

    public void initialize() {
        loadCustomerCount();
        loadOrderCount();
        loadMostSellItemTable();
        loadAll();
        getOrderDate();
        setMonthlyProfit();

        //barChart();

        try {
            pieChartConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadMostSellItemTable() {
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colOrderCount.setCellValueFactory(new PropertyValueFactory<>("orderCount"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("sumQty"));
    }

    private void loadAll() {
        ObservableList<CustomDTO> obList = FXCollections.observableArrayList();

        try {
            List<CustomDTO> itemList = dashboardBO.getMostSellItem();
            ItemDTO item;
            for (CustomDTO sellItem : itemList) {
                item = dashboardBO.searchItemById(sellItem.getItemId());
                CustomDTO tm = new CustomDTO(
                        item.getName(),
                        sellItem.getOrderCount(),
                        sellItem.getSumQty()
                );

                obList.add(tm);
            }

            tblMostSellItems.setItems(obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerCount() {
        int count = 0;
        try {
            List<CustomerDTO> customerList = dashboardBO.getAllCustomers();
            for (CustomerDTO cust : customerList) {

                count ++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        labCutCount.setText(String.valueOf(count));
    }

    private void loadOrderCount() {
        int count = 0;
        try {
            List<OrderDTO> orderList = dashboardBO.getAllOrders();
            for (OrderDTO order : orderList) {

                count ++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        lblOrderCount.setText(String.valueOf(count));
    }

    // Pie Chart--------------------------------------------------------------------------------------------------------------------------------------------------
    private void pieChartConnect() throws SQLException, ClassNotFoundException {
        List<CustomDTO> itemList = dashboardBO.getMostSellItem();
        ItemDTO item;
        for (CustomDTO sellItem : itemList) {
            item = dashboardBO.searchItemById(sellItem.getItemId());

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data(item.getName(), sellItem.getSumQty())
                    );
            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), "  amount: ", data.pieValueProperty()
                            )
                    )
            );

            pieChart.getData().addAll(pieChartData);
        }
    }

    // Bar Chart -----------------------------------------------------------------------------------------------------------------------------------------------------
    private void barChart() {
        try {
            XYChart.Series series1 = dashboardBO.getBarChart();
            barChart.getData().addAll(series1);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void getOrderDate() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> dateList = dashboardBO.getAllDate();

            for (String date : dateList) {
                obList.add(date);
            }
            TextFields.bindAutoCompletion(txtOrderDate, obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOrderDateOnAction() throws SQLException, ClassNotFoundException {
        Date date = java.sql.Date.valueOf(txtOrderDate.getText());

        CustomDTO dailyOrders = dashboardBO.orderDaily(date);

        lblOrderCountlab.setText(String.valueOf(dailyOrders.getOrderCount()));
        lblItemQty.setText(String.valueOf(dailyOrders.getSumQty()));
    }

    @FXML
    void txtOrderDateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        btnSearchOrderDateOnAction();
    }

    private void setMonthlyProfit() {
        double monthlyProfit;
        try {
            monthlyProfit = dashboardBO.getLastMonthIncome();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }  catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtMonthlyProfit.setText(String.valueOf(monthlyProfit));
    }
}
