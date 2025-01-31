package lk.ijse.nltec.nltecconvertlayerda.dao.custom.impl;

import lk.ijse.nltec.nltecconvertlayerda.dao.SQLUtill;
import lk.ijse.nltec.nltecconvertlayerda.dao.custom.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean saveUser(String userName, String password) throws SQLException,ClassNotFoundException {
        boolean result = SQLUtill.execute("INSERT INTO users VALUES(?, ?)",userName,password);
        return result;
    }
    @Override
    public String Checkcredential(String username) throws SQLException,ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT user_name,password from users where user_name=?",username);
        if (resultSet.next()) {
            String dbpw = resultSet.getString("password");
            return dbpw;
        }
        return null;
    }


}
