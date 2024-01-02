package lk.ijse.blood.DAO;

import lk.ijse.blood.dto.AttendanceDto;
import lk.ijse.blood.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO{
     List<T> loadAll() throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     T search(String id) throws SQLException, ClassNotFoundException ;
     boolean save(T dto) throws SQLException, ClassNotFoundException ;
     boolean update(T dto) throws SQLException, ClassNotFoundException;
     boolean add(T dto) throws SQLException, ClassNotFoundException ;
}

