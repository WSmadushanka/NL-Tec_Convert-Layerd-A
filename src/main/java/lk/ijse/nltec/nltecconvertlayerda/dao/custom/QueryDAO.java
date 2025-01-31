package lk.ijse.nltec.nltecconvertlayerda.dao.custom;

import lk.ijse.nltec.nltecconvertlayerda.dao.CrudDAO;
import lk.ijse.nltec.nltecconvertlayerda.dao.SuperDAO;
import lk.ijse.nltec.nltecconvertlayerda.entity.Custom;

import javax.management.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    Custom orderDaily(Date date)throws SQLException,ClassNotFoundException;

    double getLastMonthIncome()throws SQLException,ClassNotFoundException;

    ArrayList<Custom> getBarChart()throws SQLException,ClassNotFoundException;
    double getNetTot(String oId)throws SQLException,ClassNotFoundException;
}
