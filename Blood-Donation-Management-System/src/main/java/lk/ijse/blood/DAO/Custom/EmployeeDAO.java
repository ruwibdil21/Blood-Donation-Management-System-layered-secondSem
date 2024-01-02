package lk.ijse.blood.DAO.Custom;

import lk.ijse.blood.DAO.CrudDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.EmployeeDto;
import lk.ijse.blood.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends CrudDAO <Employee> {
}
