package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;

import java.sql.SQLException;

public interface QueryBO extends SuperBo {
    double getNetTot(String oId) throws SQLException, ClassNotFoundException;
}
