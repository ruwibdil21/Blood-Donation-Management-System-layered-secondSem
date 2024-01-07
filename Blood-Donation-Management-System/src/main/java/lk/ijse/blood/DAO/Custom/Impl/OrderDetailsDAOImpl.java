package lk.ijse.blood.DAO.Custom.Impl;

import lk.ijse.blood.DAO.Custom.InventoryDAO;
import lk.ijse.blood.DAO.Custom.OrderDetailsDAO;
import lk.ijse.blood.DAO.Custom.SupplierOrderDAO;
import lk.ijse.blood.DAO.DAOFactory;
import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.Util.TransactionUtil;
import lk.ijse.blood.dto.InventoryDto;
import lk.ijse.blood.dto.OrderDetailsDto;
import lk.ijse.blood.dto.SupplierOrdersDto;
import lk.ijse.blood.entity.Inventory;
import lk.ijse.blood.entity.OrderDetails;
import lk.ijse.blood.entity.SupplierOrders;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    SupplierOrderDAO supplierOrderDAO = (SupplierOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIERORDER);
    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    public  boolean placeOrderDetails(SupplierOrders supplierOrdersDto, Inventory inventoryDto, OrderDetails orderDetailsDto) throws SQLException, ClassNotFoundException {

        try {
            TransactionUtil.startTransaction();
            boolean isSupplierOrdersSaved = supplierOrderDAO.save(supplierOrdersDto);
            if (isSupplierOrdersSaved) {
                boolean isInventorySaved = inventoryDAO.save(inventoryDto);
                if (isInventorySaved) {
                    boolean isOrderDetailsSaved = orderDetailsDAO.save(orderDetailsDto);
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

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
