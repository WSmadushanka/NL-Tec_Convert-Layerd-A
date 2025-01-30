package lk.ijse.nltec.nltecconvertlayerda.dao.custom;

import lk.ijse.nltec.nltecconvertlayerda.dao.SuperDAO;

import java.sql.SQLException;

public interface UserDAO extends SuperDAO {
    boolean saveUser(String userName, String password)throws SQLException,ClassNotFoundException;

    String Checkcredential(String username)throws SQLException,ClassNotFoundException;
}
