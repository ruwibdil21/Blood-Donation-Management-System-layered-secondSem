package lk.ijse.blood.model;

import lk.ijse.blood.Util.SQLUtil;
import lk.ijse.blood.dto.AttendanceDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceModel {

    public List<AttendanceDto> loadAllAttendans() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM attendance");
        List<AttendanceDto> attendanceList= new ArrayList<>();

        while (resultSet.next()) {
            attendanceList.add(new AttendanceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));
        }

        return attendanceList;
    }



    public boolean deleteAttendance(String attId) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "DELETE FROM attendance WHERE Att_id = ?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        statement.setString(1, attId);
//        return statement.executeUpdate() > 0;
        return SQLUtil.execute("DELETE FROM attendance WHERE Att_id = ?",attId);
    }




    public AttendanceDto searchAttendance(String attId) throws SQLException, ClassNotFoundException {
       /* Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM attendance WHERE Att_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, attId);*/

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM attendance WHERE Att_id = ?");


        if (resultSet.next()){
            return new AttendanceDto(
             resultSet.getString(1),
             resultSet.getString(2),
             resultSet.getString(3),
             resultSet.getString(4)
             );

        }
        return null;
    }


    public boolean saveAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException {

       return SQLUtil.execute( "insert into attendance values (?,?,?,?)",dto.getAtt_id(),dto.getEmp_id(),dto.getDate(),dto.getStatus());

    }


    public boolean updateAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE attendance SET Emp_id = ?, Date= ?, Status = ? WHERE Att_id = ?",dto.getEmp_id(),dto.getDate(),dto.getStatus(),dto.getAtt_id());

    }
}

