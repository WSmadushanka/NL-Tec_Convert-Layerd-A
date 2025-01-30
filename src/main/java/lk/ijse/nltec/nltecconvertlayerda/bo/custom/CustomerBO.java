package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO {
    List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String tel) throws SQLException, ClassNotFoundException;

    CustomerDTO searchByCustomerId(String id) throws SQLException, ClassNotFoundException;

    List<String> getCustomerTel()throws SQLException,ClassNotFoundException;

    String generateNewID()throws SQLException,ClassNotFoundException;

}
