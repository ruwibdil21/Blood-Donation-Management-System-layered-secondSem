package lk.ijse.blood.DAO.Custom;

import lk.ijse.blood.DAO.CrudDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.AttendanceDto;
import lk.ijse.blood.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AttendanceDAO extends CrudDAO<Attendance> {

}
