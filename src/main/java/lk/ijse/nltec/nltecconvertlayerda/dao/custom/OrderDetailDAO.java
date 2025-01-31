package lk.ijse.nltec.nltecconvertlayerda.dao.custom;

import lk.ijse.nltec.nltecconvertlayerda.dao.CrudDAO;
import lk.ijse.nltec.nltecconvertlayerda.entity.Custom;
import lk.ijse.nltec.nltecconvertlayerda.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {
    boolean save(List<OrderDetail> odList)throws SQLException,ClassNotFoundException;
    List<Custom> getMostSellItem()throws SQLException,ClassNotFoundException;
}
