package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;
import lk.ijse.nltec.nltecconvertlayerda.dto.ItemDTO;
import lk.ijse.nltec.nltecconvertlayerda.dto.ItemSupplierDetailDTO;
import lk.ijse.nltec.nltecconvertlayerda.dto.SupplierDTO;
import lk.ijse.nltec.nltecconvertlayerda.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlaceItemBO extends SuperBo {
    boolean saveItemSupplierDetail(ItemSupplierDetailDTO is) throws SQLException, ClassNotFoundException;

    boolean updateItemSupplierDetail(ItemSupplierDetailDTO is) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getAllSuppliers()throws SQLException,ClassNotFoundException;

    ArrayList<ItemSupplierDetailDTO> getAllItemSupplierDetail()throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems()throws SQLException,ClassNotFoundException;

    boolean palceItem(ItemDTO item, ItemSupplierDetailDTO itemSupplierDetailDTO)throws SQLException,ClassNotFoundException;

    boolean updateItem(ItemDTO item, ItemSupplierDetailDTO itemSupplierDetailDTO)throws SQLException,ClassNotFoundException;

    boolean deleteItem(String id)throws SQLException,ClassNotFoundException;

    boolean updateItemQ(List<OrderDetail> isList) throws SQLException, ClassNotFoundException;

    boolean updateItemQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    String generateNewID()throws SQLException,ClassNotFoundException;

    List<String> getSupplierId()throws SQLException,ClassNotFoundException;

    SupplierDTO searchByIdSupplier(String id)throws SQLException,ClassNotFoundException;

    ItemSupplierDetailDTO searchItemSupplierDetail(String id)throws SQLException,ClassNotFoundException;

    boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;

    ItemDTO searchItemByName(String name)throws SQLException,ClassNotFoundException;

    ItemDTO searchItemById(String id) throws SQLException, ClassNotFoundException;

    List<String> getItemDate() throws SQLException, ClassNotFoundException;

    List<String> getItemName()throws SQLException,ClassNotFoundException;
}
