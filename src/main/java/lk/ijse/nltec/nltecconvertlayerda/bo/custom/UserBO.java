package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import java.sql.SQLException;

public interface UserBO {
    boolean saveUser(String userName, String password) throws SQLException,ClassNotFoundException;

    String Checkcredential(String username) throws SQLException,ClassNotFoundException;
}
