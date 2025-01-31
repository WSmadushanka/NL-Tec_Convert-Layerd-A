package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;
import lk.ijse.nltec.nltecconvertlayerda.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBo {
    ArrayList<EmployeeDTO> getAllEmployee()throws SQLException,ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO employee)throws SQLException,ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employee)throws SQLException,ClassNotFoundException;

    boolean deleteEmployee(String id)throws SQLException,ClassNotFoundException;

    String generateNewID()throws SQLException,ClassNotFoundException;

    List<String> getEmployeeId()throws SQLException,ClassNotFoundException;

    EmployeeDTO searchEmployee(String id)throws SQLException,ClassNotFoundException;
}
