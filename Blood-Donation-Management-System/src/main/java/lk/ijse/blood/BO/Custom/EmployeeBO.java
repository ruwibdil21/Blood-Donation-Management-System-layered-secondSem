package lk.ijse.blood.BO.Custom;

import lk.ijse.blood.BO.SuperBO;
import lk.ijse.blood.dto.DonorDto;
import lk.ijse.blood.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    List<EmployeeDto> loadAllEmployee() throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    EmployeeDto searchEmployee(String id) throws SQLException, ClassNotFoundException ;
    boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException ;
    boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    String generateEmployee() throws SQLException,ClassNotFoundException;
}
