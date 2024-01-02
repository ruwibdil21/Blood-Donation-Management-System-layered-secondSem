package lk.ijse.blood.DAO.Custom;

import lk.ijse.blood.DAO.CrudDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.InventoryDto;
import lk.ijse.blood.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InventoryDAO extends CrudDAO<Inventory> {
}