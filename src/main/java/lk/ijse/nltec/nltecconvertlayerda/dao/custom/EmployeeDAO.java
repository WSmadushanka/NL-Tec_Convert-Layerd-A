package lk.ijse.nltec.nltecconvertlayerda.dao.custom;

import lk.ijse.nltec.nltecconvertlayerda.dao.CrudDAO;
import lk.ijse.nltec.nltecconvertlayerda.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {
    List<String> getId() throws SQLException, ClassNotFoundException;
}
