package lk.ijse.nltec.nltecconvertlayerda.dao;

import lk.ijse.nltec.nltecconvertlayerda.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    ArrayList<T> getAll()throws SQLException,ClassNotFoundException;

    boolean save(T dto)throws SQLException,ClassNotFoundException;

    boolean update(T dto)throws SQLException,ClassNotFoundException;

    String generateNewID()throws SQLException,ClassNotFoundException;

    boolean delete(String id)throws SQLException,ClassNotFoundException;

    T search(String id)throws SQLException,ClassNotFoundException;
}
