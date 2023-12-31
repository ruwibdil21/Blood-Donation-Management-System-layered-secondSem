package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.NeederDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NeederModel {
    public boolean saveNeeder(NeederDto dto) throws SQLException, ClassNotFoundException {

       return SQLUtil.execute( "insert into needer values (?,?,?,?,?,?)",dto.getNeederId(),dto.getUserId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getEmail());

    }

    public boolean updateNeeder(NeederDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "UPDATE needer SET User_id = ?, Name = ?, Address = ?, Contact = ?, Email = ? WHERE Needer_id = ?",dto.getUserId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getEmail(),dto.getNeederId());


    }

    public boolean deleteNeeder(String neederId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "DELETE FROM needer WHERE Needer_id = ?",neederId);
    }

    public static List<NeederDto> loadAllNeeders() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM needer ");
        List<NeederDto> neederList= new ArrayList<>();

        while (resultSet.next()) {
            neederList.add(new NeederDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }

        return neederList;
    }
}

