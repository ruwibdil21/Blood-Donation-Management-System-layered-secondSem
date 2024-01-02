package lk.ijse.blood.DAO.Custom;

import lk.ijse.blood.DAO.CrudDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.BloodInventoryDto;
import lk.ijse.blood.entity.BloodInventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BloodInventoryDAO extends CrudDAO<BloodInventory> {

}
