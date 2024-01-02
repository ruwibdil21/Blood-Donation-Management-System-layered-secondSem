package lk.ijse.blood.DAO.Impl;

import lk.ijse.blood.DAO.Custom.OrderDetailsDAO;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.Util.TransactionUtil;
import lk.ijse.blood.dto.InventoryDto;
import lk.ijse.blood.dto.OrderDetailsDto;
import lk.ijse.blood.dto.SupplierOrdersDto;
import lk.ijse.blood.entity.OrderDetails;
import lk.ijse.blood.model.InventoryModel;
import lk.ijse.blood.model.OrderDetailsModel;
import lk.ijse.blood.model.SupplierOrderModel;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    public  boolean placeOrderDetails(SupplierOrdersDto supplierOrdersDto, InventoryDto inventoryDto, OrderDetailsDto orderDetailsDto) throws SQLException, ClassNotFoundException {

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
            }
            TransactionUtil.rollBack();
            return false;
        } finally {
            TransactionUtil.endTransaction();
        }
    }

    @Override
    public List<OrderDetails> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into order_details values (?,?,?)", dto.getOrder_id(), dto.getMed_id(), dto.getDescription());
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean add(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
