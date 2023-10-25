package PROJECTS.entity_layer;
import PROJECTS.util.DButil;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Map;
import java.util.Vector;

public class workSlots {
    /**
     * for cafe owner to add new slot
     * @param slotAddInfo is a map ,It contains slot data(startTime,endTime,hours,chef,cashier,waiter）
     * @return if add success then return true
     */
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

    /**
     * for cafe owner to view all Slots
     * @return if found data then return a DefaultTableModel(table contain information)
     */

    public DefaultTableModel viewSlotFromDatabase(){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select slot_id,start_time ,end_time,hour_duration, chef_need,cashier_need,waiter_need,chef_registed,cashier_registed,waiter_registed,is_full from t_work_slot WHERE status = true;";
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

    /**
     * for cafe owner to search Slots
     * @param selectedStartTime the user input date
     * @return if found data then return a DefaultTableModel(table contain information)
     */
    public DefaultTableModel searchSlotFromDatabase(String selectedStartTime){
        DefaultTableModel updatedModel = new DefaultTableModel();
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT slot_id, start_time, end_time, hour_duration, chef_need, cashier_need, waiter_need FROM t_work_slot where DATE_FORMAT(start_time, '%Y-%m-%d') = ? and status = true;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, Date.valueOf(selectedStartTime));

            resultSet = preparedStatement.executeQuery();

            columnNames.add("slot ID");
            columnNames.add("start date");
            columnNames.add("end date");
            columnNames.add("hours");
            columnNames.add("chef need");
            columnNames.add("cashier need");
            columnNames.add("waiter need");

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("slot_id"));
                row.add(resultSet.getString("start_time"));
                row.add(resultSet.getString("end_time"));
                row.add(resultSet.getString("hour_duration"));
                row.add(resultSet.getString("chef_need"));
                row.add(resultSet.getString("cashier_need"));
                row.add(resultSet.getString("waiter_need"));
                data.add(row);
            }
            updatedModel.setDataVector(data, columnNames);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return updatedModel;
    }

    /**
     * for cafe owner to update Slots
     * @param updateSlotInfo is a map ,It contains slot data(slotNo,startTime,endTime,hours,chef,cashier,waiter）
     * @return if update success then return true
     */
    public boolean updateSlotToDatabase(Map<String, Object> updateSlotInfo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_work_slot set start_time = ?,end_time =?,hour_duration=?,chef_need = ?,cashier_need=?,waiter_need=? where slot_id =? and status = true;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(7, (Integer) updateSlotInfo.get("SlotNo"));
            preparedStatement.setTimestamp(1, (Timestamp) updateSlotInfo.get("startTime"));
            preparedStatement.setTimestamp(2, (Timestamp) updateSlotInfo.get("endTime"));
            preparedStatement.setDouble(3, (Double) updateSlotInfo.get("hours"));
            preparedStatement.setInt(4, (Integer) updateSlotInfo.get("chef"));
            preparedStatement.setInt(5, (Integer) updateSlotInfo.get("cashier"));
            preparedStatement.setInt(6, (Integer) updateSlotInfo.get("waiter"));

            //if add succeeds count will be 1
            int count = preparedStatement.executeUpdate();

            return count == 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }

    /**
     * for cafe owner to suspend Slots
     * @param SlotNo the user enter the slot no
     * @return if suspend success then return true
     */
    public boolean suspendSlotToDatabase(int SlotNo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_work_slot set status = false where slot_id =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, SlotNo);

            //if add succeeds count will be 1
            int count = preparedStatement.executeUpdate();

            return count == 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }
}
