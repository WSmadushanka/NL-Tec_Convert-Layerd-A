package lk.ijse.nltec.nltecconvertlayerda.dao.custom;

import lk.ijse.nltec.nltecconvertlayerda.dao.CrudDAO;
import lk.ijse.nltec.nltecconvertlayerda.entity.Location;
import lk.ijse.nltec.nltecconvertlayerda.entity.Transport;

import java.sql.SQLException;
import java.util.List;

public interface TransportDAO extends CrudDAO<Transport> {
    List<String> getlocation() throws SQLException, ClassNotFoundException;

    Location searchByPath(String name)throws SQLException,ClassNotFoundException;

    List<String> getPlace()throws SQLException,ClassNotFoundException;
}
