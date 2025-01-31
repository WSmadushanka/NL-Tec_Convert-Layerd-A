package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;
import lk.ijse.nltec.nltecconvertlayerda.dto.*;
import lk.ijse.nltec.nltecconvertlayerda.entity.Transport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBo {
    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String con) throws SQLException, ClassNotFoundException;

    String generateOrderID() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    List<String> getlocation() throws SQLException,ClassNotFoundException;

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    ArrayList<ItemSupplierDetailDTO> getItemSupplierDetaiAll() throws SQLException, ClassNotFoundException;

    ItemSupplierDetailDTO searchItemSuppliers(String id) throws SQLException, ClassNotFoundException;

    boolean purchaseOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetails) throws Exception;

    List<String> getItemName() throws SQLException, ClassNotFoundException;

    List<String> getCustomerTel() throws SQLException, ClassNotFoundException;

    Transport searchByLocation(String loc) throws SQLException,ClassNotFoundException;

    double getNetTot(String oId) throws SQLException, ClassNotFoundException;

    String getLastOrderId() throws SQLException,ClassNotFoundException;
}
