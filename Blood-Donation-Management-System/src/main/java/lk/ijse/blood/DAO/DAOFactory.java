package lk.ijse.blood.DAO;

import lk.ijse.blood.DAO.Impl.*;

import javax.management.Query;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){

        return daoFactory==null? new DAOFactory():daoFactory;
    }

    public enum DAOTypes{

        ADMIN,ATTENDANCE,BLOODINVENTORY,DONATION,DONOR,EMPLOYEE,INVENTORY,NEEDER,NEEDERREQUEST,ORDERDETAILS,REQUESTDETAILS,SALARY,SUPPLIER,SUPPLIERORDER//,QUERY

    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ADMIN:
                return new AdminDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case BLOODINVENTORY:
                return new BloodInventoryDAOImpl();
            case DONATION:
                return new DonationDAOImpl();
            case DONOR:
                return new DonorDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case NEEDER:
                return new NeederDAOImpl();
            case NEEDERREQUEST:
                return new NeederReuestDAOImpl();
            case ORDERDETAILS:
                return new RequestDetailsDAOImpl();
            case REQUESTDETAILS:
                return new RequestDetailsDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIERORDER:
                return new SupplierDAOImpl();
           // case QUERY:
             //   return new Query();


        }
       return null;
    }

}
