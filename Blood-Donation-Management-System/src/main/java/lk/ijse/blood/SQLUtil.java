package lk.ijse.blood;

import lk.ijse.blood.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql,Object... args) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i =0 ;i<args.length;i ++){
            pstm.setObject(i+1,args[i]);
        }

        if (sql.startsWith("SELECT")){
            return  (T) pstm.executeQuery();
        }else {
            return (T)(Boolean) (pstm.executeUpdate()>0);
        }

    }

}
