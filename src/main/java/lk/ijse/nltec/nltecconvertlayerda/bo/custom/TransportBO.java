package lk.ijse.nltec.nltecconvertlayerda.bo.custom;

import lk.ijse.nltec.nltecconvertlayerda.bo.SuperBo;
import lk.ijse.nltec.nltecconvertlayerda.dto.LocationDTO;
import lk.ijse.nltec.nltecconvertlayerda.dto.TransportDTO;
import lk.ijse.nltec.nltecconvertlayerda.entity.Transport;

import java.sql.SQLException;
import java.util.List;

public interface TransportBO extends SuperBo {
    boolean saveTransport(TransportDTO transport) throws SQLException,ClassNotFoundException;

    boolean updateTransport(TransportDTO transport) throws SQLException,ClassNotFoundException;

    boolean deleteTransport(String id) throws SQLException,ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    List<TransportDTO> getAllTransport() throws SQLException, ClassNotFoundException;

    Transport searchByLocation(String loc) throws SQLException,ClassNotFoundException;

    List<String> getlocation() throws SQLException,ClassNotFoundException;

    LocationDTO searchByPath(String name) throws SQLException, ClassNotFoundException;

    List<String> getPlace() throws SQLException, ClassNotFoundException;
}
