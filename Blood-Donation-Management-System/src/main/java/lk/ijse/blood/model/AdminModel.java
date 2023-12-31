package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminModel {
    public static List<UserDto> loadAllUsers() throws SQLException {

        ResultSet resultSet = null;
        try {
            resultSet = SQLUtil.execute("SELECT * FROM user");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<UserDto> userList = new ArrayList<>();

        while (resultSet.next()) {
            userList.add(new UserDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return userList;
    }

    public boolean loginAdmin(String id, String password) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE User_id =? AND Password = ?",id,password);
        return resultSet.next();
    }

    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "insert into user values (?,?,?)",dto.getUser_id(),dto.getName(),dto.getPassword());
    }
}
