package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;
import lk.ijse.nltec.nltecconvertlayerda.dto.CustomDTO;
import lk.ijse.nltec.nltecconvertlayerda.dto.CustomerDTO;
import lk.ijse.nltec.nltecconvertlayerda.dto.ItemDTO;
import lk.ijse.nltec.nltecconvertlayerda.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface DashboardBO extends SuperBo {
    List<CustomDTO> getMostSellItem()throws SQLException,ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCustomers()throws SQLException,ClassNotFoundException;
    ArrayList<OrderDTO> getAllOrders()throws SQLException,ClassNotFoundException;
    XYChart.Series getBarChart()throws SQLException,ClassNotFoundException;
    ItemDTO searchItemById(String id)throws SQLException,ClassNotFoundException;

    List<String> getAllDate()throws SQLException,ClassNotFoundException;

    CustomDTO orderDaily(Date date)throws SQLException,ClassNotFoundException;


    double getLastMonthIncome()throws SQLException,ClassNotFoundException;
}
