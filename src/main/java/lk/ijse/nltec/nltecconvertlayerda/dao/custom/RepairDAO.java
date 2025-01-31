package lk.ijse.nltec.nltecconvertlayerda.dao.custom;

import lk.ijse.nltec.nltecconvertlayerda.dao.CrudDAO;
import lk.ijse.nltec.nltecconvertlayerda.entity.Repair;

import java.sql.SQLException;
import java.util.List;

public interface RepairDAO extends CrudDAO<Repair> {
    String getLastId()throws SQLException,ClassNotFoundException;

    List<String> getId()throws SQLException,ClassNotFoundException;
}
