package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.Util.TransactionUtil;
import lk.ijse.blood.db.DbConnection;
import lk.ijse.blood.dto.*;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetailsModel {
    public static boolean placeOrderDetails(SupplierOrdersDto supplierOrdersDto, InventoryDto inventoryDto, OrderDetailsDto orderDetailsDto) throws SQLException, ClassNotFoundException {

        try {
            TransactionUtil.startTransaction();
            boolean isSupplierOrdersSaved = SupplierOrderModel.saveSupplierOrders(supplierOrdersDto);
            if (isSupplierOrdersSaved) {
                boolean isInventorySaved = InventoryModel.saveInventory(inventoryDto);
                if (isInventorySaved) {
                    boolean isOrderDetailsSaved = OrderDetailsModel.saveOrderDetails(orderDetailsDto);
                    if (isOrderDetailsSaved) {
                        return true;
                    }
                }
                TransactionUtil.rollBack();
                return false;
            } TransactionUtil.rollBack();
            return false;
        }finally{
           TransactionUtil.endTransaction();
        }
    }

    public static boolean saveOrderDetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "insert into order_details values (?,?,?)",dto.getOrder_id(),dto.getMed_id(),dto.getDescription());

    }

   /* public static List<OrderDetailsDto> loadAllOrderDetails() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM orderDetails";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<OrderDetailsDto> orderDetailsList = new ArrayList<>();

        while (resultSet.next()) {
            orderDetailsList.add(new OrderDetailsDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }

        return orderDetailsList;
    }*/

}

