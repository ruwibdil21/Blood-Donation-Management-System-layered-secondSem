package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.SalaryDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {
    public boolean saveSalary(SalaryDto dto) throws SQLException, ClassNotFoundException {

       return SQLUtil.execute( "insert into Salary values (?,?,?,?,?)", dto.getSalary_id(),dto.getEmployee_id(),dto.getAmount(), dto.getMonth(),dto.getYear());
    }


    public boolean deleteSalary(String salary_id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "DELETE FROM salary WHERE Salary_id = ?",salary_id);

    }

    public List<SalaryDto> loadAllSalarys() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary");
        List<SalaryDto> salaryList= new ArrayList<>();

        while (resultSet.next()) {
            salaryList.add(new SalaryDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5)


            ));
        }

        return salaryList;
    }

    public boolean updateSalary(SalaryDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "UPDATE salary SET Emp_id = ?, Amount = ?,Month = ?,Year= ?  WHERE Salary_id = ?", dto.getEmployee_id(),dto.getAmount(),dto.getMonth(), dto.getYear(),dto.getSalary_id());
    }

    public SalaryDto searchSalary(String salaryId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary WHERE Salary_id = ?",salaryId);


        if (resultSet.next()){
            return new SalaryDto(
             resultSet.getString(1),
             resultSet.getString(2),
             Integer.valueOf(resultSet.getString(3)),
             resultSet.getString(4),
             resultSet.getString(5));


        }
        return null;
    }
}

