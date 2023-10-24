package PROJECTS.entity_layer;
import PROJECTS.util.DButil;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Map;

public class workSlots {
    public boolean addNewSlot(Map<String, Object> slotAddInfo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "INSERT INTO t_work_slot (start_time, end_time, hour_duration, chef_need, cashier_need, waiter_need) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Convert java.util.Date to java.sql.Timestamp
            preparedStatement.setTimestamp(1, (Timestamp) slotAddInfo.get("startTime"));
            preparedStatement.setTimestamp(2, (Timestamp) slotAddInfo.get("endTime"));
            preparedStatement.setDouble(3, (Double) slotAddInfo.get("hours"));
            preparedStatement.setInt(4, (Integer) slotAddInfo.get("chef"));
            preparedStatement.setInt(5, (Integer) slotAddInfo.get("cashier"));
            preparedStatement.setInt(6, (Integer) slotAddInfo.get("waiter"));

            // If the add succeeds, the count will be 1
            int count = preparedStatement.executeUpdate();

            return count == 1;

        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }finally {
            DButil.clos(connection,preparedStatement,resultSet);
        }
        return true;
    }

    public DefaultTableModel viewSlotFromDatabase(){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select slot_id,start_time ,end_time,hour_duration, chef_need,cashier_need,waiter_need,chef_registed,cashier_registed,waiter_registed,is_full from t_work_slot;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Slot No", "start time", "end time", "hours","chef need","cashier need","waiter need","chef reg","cashier reg","waiter reg"};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String slotNo = resultSet.getString("slot_id");
                String startTime = resultSet.getString("start_time");
                String endTime = resultSet.getString("end_time");
                String hours = resultSet.getString("hour_duration");
                String chefNeed = resultSet.getString("chef_need");
                String cashierNeed = resultSet.getString("cashier_need");
                String waiterNeed = resultSet.getString("waiter_need");
                String chefReg = resultSet.getString("chef_registed");
                String cashierReg = resultSet.getString("cashier_registed");
                String waiterReg = resultSet.getString("waiter_registed");

                // Add fetched data to the table model
                Object[] rowData = {slotNo, startTime, endTime, hours, chefNeed, cashierNeed, waiterNeed, chefReg, cashierReg, waiterReg};
                myModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }finally {
            DButil.clos(connection,preparedStatement,resultSet);
        }
        return myModel;
    }


}
