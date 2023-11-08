package PROJECTS.entity_layer;

import PROJECTS.util.DButil;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Map;

public class bids {
    /**
     * for staff to view personal bid records
      * @param account user account
     * @return DefaultTableModel
     */
    public DefaultTableModel viewPersonalBid(String account){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT t_bid.bid_id, t_bid.slot_id, t_work_slot.start_time, t_work_slot.end_time, t_work_slot.hour_duration, t_bid.accountNo, t_bid.bid_role, t_bid.bid_status  \n" +
                    "FROM t_bid \n" +
                    "JOIN t_work_slot ON t_bid.slot_id = t_work_slot.slot_id \n" +
                    "WHERE t_bid.accountNo = ? and bid_status != 'deleted';";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);

            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Bid ID", "slot_id","start time","end time ","hours", "accountNo","bid_role","bid_status"};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String bid_id = resultSet.getString("bid_id");
                String slot_id = resultSet.getString("slot_id");

                String start_time = resultSet.getString("start_time");
                String end_time = resultSet.getString("end_time");
                String hour_duration = resultSet.getString("hour_duration");


                String accountNo = resultSet.getString("accountNo");
                String bid_role = resultSet.getString("bid_role");
                String bid_status = resultSet.getString("bid_status");


                // Add fetched data to the table model
                Object[] rowData = {bid_id, slot_id,start_time,end_time,hour_duration, accountNo, bid_role, bid_status};
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
     * for staff to view personal working hour
     * @param account user account
     * @return DefaultTableModel
     */
    public DefaultTableModel viewPersonalWorkingHour(String account){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select * from t_staff_work_hour where staff_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);

            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Staff ID", "Max Working Hour",};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String StaffID = resultSet.getString("staff_id");
                String MaxWorkingHour = resultSet.getString("max_hour");

                // Add fetched data to the table model
                Object[] rowData = {StaffID, MaxWorkingHour};
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
     *
     * for staff to indicate max Working Hour
     * @param account account
     * @param hour max working hour
     * @return if update success return true
     */
    public boolean indicatePersonalWorkingHour(String account,int hour){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_staff_work_hour set max_hour = ? where staff_id =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, hour);
            preparedStatement.setString(2, account);

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
     * for staff to view assigned BID (approved or allocated)
     * @param account user account
     * @return DefaultTableModel
     */
    public DefaultTableModel viewAssignedSlotList(String account){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT t_bid.bid_id, t_bid.slot_id, t_work_slot.start_time, t_work_slot.end_time, t_work_slot.hour_duration, t_bid.accountNo, t_bid.bid_role, t_bid.bid_status  \n" +
                    "FROM t_bid \n" +
                    "JOIN t_work_slot ON t_bid.slot_id = t_work_slot.slot_id \n" +
                    "WHERE accountNo = ? and bid_status = 'approved'or bid_status = 'allocated';";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);

            resultSet = preparedStatement.executeQuery();

            String[] columnNames = {"Bid ID", "slot_id","start time","end time ","hours", "accountNo","bid_role","bid_status"};
            myModel.setColumnIdentifiers(columnNames);

            while (resultSet.next()) {
                String bid_id = resultSet.getString("bid_id");
                String slot_id = resultSet.getString("slot_id");

                String start_time = resultSet.getString("start_time");
                String end_time = resultSet.getString("end_time");
                String hour_duration = resultSet.getString("hour_duration");


                String accountNo = resultSet.getString("accountNo");
                String bid_role = resultSet.getString("bid_role");
                String bid_status = resultSet.getString("bid_status");

                // Add fetched data to the table model
                Object[] rowData = {bid_id, slot_id,start_time,end_time,hour_duration, accountNo, bid_role, bid_status};
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
     *for staff to bid a slot
     * @param account user account
     * @param slotNo slot number that staff want bid
     * @param role role that staff want to bid
     * @return if add bid record success return true
     */
    public boolean bidSlot(String account,int slotNo,String role){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "INSERT INTO t_bid (slot_id,accountNo,bid_role,bid_status) VALUES ( ?,?,?,'pending'); ";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, slotNo);
            preparedStatement.setString(2, account);
            preparedStatement.setString(3, role);

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
     * for staff to delete bid
     * @param account user account
     * @param bidNo the bid id that user want delete
     * @return if delete success return true
     */
    public boolean deleteBid(String account,int bidNo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_bid set bid_status = 'deleted' where bid_id =? and accountNo =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, bidNo);
            preparedStatement.setString(2, account);

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
