package lk.ijse.nltec.nltecconvertlayerda.dao.custom;

import lk.ijse.nltec.nltecconvertlayerda.dao.CrudDAO;
import lk.ijse.nltec.nltecconvertlayerda.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    Customer searchById(String id) throws SQLException, ClassNotFoundException;

    List<String> getTel()throws SQLException,ClassNotFoundException;
}
