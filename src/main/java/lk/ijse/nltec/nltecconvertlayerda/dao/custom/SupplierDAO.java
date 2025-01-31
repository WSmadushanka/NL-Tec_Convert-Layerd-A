package lk.ijse.nltec.nltecconvertlayerda.dao.custom;

import lk.ijse.nltec.nltecconvertlayerda.dao.CrudDAO;
import lk.ijse.nltec.nltecconvertlayerda.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {
    List<String> getId()throws SQLException,ClassNotFoundException;

    Supplier searchByName(String name)throws SQLException,ClassNotFoundException;

    List<String> getName()throws SQLException,ClassNotFoundException;
}
