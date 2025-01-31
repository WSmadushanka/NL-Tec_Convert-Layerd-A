package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;
import lk.ijse.nltec.nltecconvertlayerda.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBo {
    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    SupplierDTO searchByIdSupplier(String id) throws SQLException, ClassNotFoundException;

    SupplierDTO searchByNameSupplier(String name) throws SQLException, ClassNotFoundException;

    List<String> getSupplierId() throws SQLException, ClassNotFoundException;

    List<String> getSupplierName() throws SQLException, ClassNotFoundException;
}
