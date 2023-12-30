package lk.ijse.blood.model;

import lk.ijse.blood.SQLUtil;
import lk.ijse.blood.db.DbConnection;
import lk.ijse.blood.dto.DonationDto;
import lk.ijse.blood.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "insert into employee values (?,?,?,?,?,?)",dto.getEmp_id(),dto.getUser_id(),dto.getName(),dto.getAddress(),dto.getRole(),dto.getDOB());

    }


    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM employee WHERE Emp_id = ?",id);

    }

    public List<EmployeeDto> loadAllEmployees() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee ");

        List<EmployeeDto> employeeList= new ArrayList<>();

        while (resultSet.next()) {
            employeeList.add(new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDate(6)

            ));
        }

        return employeeList;
    }
}
