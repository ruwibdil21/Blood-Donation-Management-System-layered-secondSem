package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.SupplierDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public boolean addSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "insert into supplier values (?,?,?,?,?)",dto.getSup_id(),dto.getUser_id(),dto.getName(),dto.getAddress(),dto.getTel());
    }

    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "UPDATE supplier SET User_id = ?, Name = ?, Address = ?, Mobile = ? WHERE Sup_id = ?",dto.getUser_id(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getSup_id());

    }

    public boolean deleteSupplier(String supId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM supplier WHERE Sup_id = ?",supId);

    }

    public static List<SupplierDto> loadAllSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.execute("SELECT * FROM supplier ");
        List<SupplierDto> supplierList= new ArrayList<>();

        while (resultSet.next()) {
            supplierList.add(new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)



            ));
        }

        return supplierList;
    }
}

