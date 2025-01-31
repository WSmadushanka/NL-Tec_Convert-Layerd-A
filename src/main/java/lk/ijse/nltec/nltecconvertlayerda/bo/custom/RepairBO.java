package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;
import lk.ijse.nltec.nltecconvertlayerda.dto.RepairDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RepairBO extends SuperBo {
    ArrayList<RepairDTO> getAllRepair() throws SQLException, ClassNotFoundException;

    boolean saveRepair(RepairDTO repair) throws SQLException, ClassNotFoundException;

    boolean updateRepair(RepairDTO repair) throws SQLException, ClassNotFoundException;

    boolean deleteRepair(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    RepairDTO searchRepair(String id) throws SQLException, ClassNotFoundException;

    List<String> getRepairId() throws SQLException, ClassNotFoundException;

    String getRepairLastId() throws SQLException, ClassNotFoundException;
}
