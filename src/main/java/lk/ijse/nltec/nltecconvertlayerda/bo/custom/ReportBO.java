package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface ReportBO extends SuperBo {
    List<String> getCustomerTel() throws SQLException, ClassNotFoundException;

    List<String> getItemDate() throws SQLException, ClassNotFoundException;
}
