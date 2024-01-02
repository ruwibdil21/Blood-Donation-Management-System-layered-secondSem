package lk.ijse.blood.DAO.Impl;

import lk.ijse.blood.DAO.Custom.EmployeeDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.EmployeeDto;
import lk.ijse.blood.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee ");

        List<Employee> employeeList= new ArrayList<>();

        while (resultSet.next()) {
            Employee employeeDto = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDate(6));
            employeeList.add(employeeDto);
        }

        return employeeList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM employee WHERE Emp_id = ?",id);
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "insert into employee values (?,?,?,?,?,?)",dto.getEmp_id(),dto.getUser_id(),dto.getName(),dto.getAddress(),dto.getRole(),dto.getDOB());

    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean add(Employee dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
