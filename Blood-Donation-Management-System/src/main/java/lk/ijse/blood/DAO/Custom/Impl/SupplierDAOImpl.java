package lk.ijse.blood.DAO.Custom.Impl;

import lk.ijse.blood.DAO.Custom.SupplierDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public List<Supplier> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.execute("SELECT * FROM supplier ");
        List<Supplier> supplierList= new ArrayList<>();

        while (resultSet.next()) {
            Supplier supplierDto =new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            supplierList.add(supplierDto);
        }

        return supplierList;    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE Sup_id = ?",id);
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE supplier SET User_id = ?, Name = ?, Address = ?, Mobile = ? WHERE Sup_id = ?",dto.getUser_id(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getSup_id());
    }

    @Override
    public boolean add(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "insert into supplier values (?,?,?,?,?)",dto.getSup_id(),dto.getUser_id(),dto.getName(),dto.getAddress(),dto.getTel());
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
