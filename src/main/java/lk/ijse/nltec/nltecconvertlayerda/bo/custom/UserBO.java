package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;

import java.sql.SQLException;

public interface UserBO extends SuperBo {
    boolean saveUser(String userName, String password) throws SQLException,ClassNotFoundException;

    String Checkcredential(String username) throws SQLException,ClassNotFoundException;
}
