package lk.ijse.blood.DAO.Impl;

import lk.ijse.blood.DAO.Custom.AdminDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public List<User> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user");
        List<User> userList = new ArrayList<>();

        while (resultSet.next()){
            User userDto = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        userList.add(userDto);
        }
        return userList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "insert into user values (?,?,?)",dto.getUser_id(),dto.getName(),dto.getPassword());
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean add(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean login(String id, String password) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE User_id =? AND Password = ?",id,password);
        return resultSet.next();
    }
}
