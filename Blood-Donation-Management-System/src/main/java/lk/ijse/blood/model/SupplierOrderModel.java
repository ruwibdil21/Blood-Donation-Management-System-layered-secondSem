package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.SupplierOrdersDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderModel {

    public SupplierOrdersDto searchSupplierOrders(String supplierId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier_orders WHERE Supplier_id = ?",supplierId);

        if (resultSet.next()){
            return new SupplierOrdersDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));

        }
        return null;
    }

    public boolean deleteSupplierOrder(String supplierId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier_orders WHERE Supplier_id = ?",supplierId);
    }

    public static boolean saveSupplierOrders(SupplierOrdersDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "insert into supplier_orders values (?,?,?,?)",dto.getSupOrder_id(),dto.getSupplier_id(),dto.getDate(),dto.getAmount());
    }

    public List<SupplierOrdersDto> loadAllSupplierOrders() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier_orders");
        List<SupplierOrdersDto> SupplierOrdersList= new ArrayList<>();

        while (resultSet.next()) {
            SupplierOrdersList.add(new SupplierOrdersDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));
        }

        return SupplierOrdersList;
    }

}
