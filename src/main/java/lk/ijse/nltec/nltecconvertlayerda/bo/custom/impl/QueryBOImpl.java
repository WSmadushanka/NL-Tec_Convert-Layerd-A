package lk.ijse.nltec.nltecconvertlayerda.bo.custom.impl;

import lk.ijse.nltec.nltecconvertlayerda.bo.custom.QueryBO;
import lk.ijse.nltec.nltecconvertlayerda.dao.DAOFactory;
import lk.ijse.nltec.nltecconvertlayerda.dao.custom.QueryDAO;

import java.sql.SQLException;

public class QueryBOImpl implements QueryBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    @Override
    public double getNetTot(String oId) throws SQLException, ClassNotFoundException {
        return queryDAO.getNetTot(oId);
    }
}
