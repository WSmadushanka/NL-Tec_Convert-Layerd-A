package lk.ijse.nltec.nltecconvertlayerda.dao.custom;

import lk.ijse.nltec.nltecconvertlayerda.dao.CrudDAO;
import lk.ijse.nltec.nltecconvertlayerda.entity.Item;
import lk.ijse.nltec.nltecconvertlayerda.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {

    Item searchByName(String name) throws SQLException ,ClassNotFoundException;
    boolean updateQ(List<OrderDetail> isList) throws SQLException,ClassNotFoundException;
    boolean updateQty(String itemCode, int qty) throws SQLException,ClassNotFoundException;
    List<String> getDate() throws SQLException,ClassNotFoundException;
    List<String> getName() throws SQLException,ClassNotFoundException;
}
