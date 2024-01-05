package lk.ijse.blood.DAO.Custom;

import lk.ijse.blood.DAO.CrudDAO;
import lk.ijse.blood.entity.User;

import java.sql.SQLException;


public interface AdminDAO extends CrudDAO<User> {
    boolean login(String id, String password) throws SQLException, ClassNotFoundException;

}

