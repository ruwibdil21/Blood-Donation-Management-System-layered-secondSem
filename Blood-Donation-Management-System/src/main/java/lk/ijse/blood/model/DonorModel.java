package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.DonorDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonorModel {

    public boolean saveDonor(DonorDto dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("insert into donor values (?,?,?,?,?,?,?)",dto.getD_id(),dto.getFirstName(),dto.getLastName(),dto.getDob(),dto.getType(),dto.getTel(),dto.getLastDate());

    }

    public boolean updateDonor(DonorDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "UPDATE donor SET F_name = ?, L_name = ?, DOB = ?, Tel = ?, Blood_type = ?, L_donate_date = ? WHERE D_id = ?",dto.getFirstName(),dto.getLastName(),dto.getDob(),dto.getType(),dto.getTel(),dto.getLastDate(),dto.getD_id());

    }

    public DonorDto searchDonor(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM donor WHERE D_id = ?",id);


        if (resultSet.next()){
            return new DonorDto(
             resultSet.getString(1),
             resultSet.getString(2),
             resultSet.getString(3),
             resultSet.getString(4),
             resultSet.getString(5),
             resultSet.getInt(6),
             resultSet.getString(7));

        }
        return null;
    }

    public boolean deleteDonor(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM donor WHERE D_id = ?",id);
    }

    public static List<DonorDto> loadAllDonors() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT * FROM donor");
        List<DonorDto> donorList = new ArrayList<>();

        while (resultSet.next()) {
            donorList.add(new DonorDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            ));
        }

        return donorList;
    }
}

