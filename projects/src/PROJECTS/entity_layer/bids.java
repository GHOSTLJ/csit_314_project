package PROJECTS.entity_layer;

import PROJECTS.util.DButil;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
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
            String[] columnNames = {"Bid ID", "Slot id","Start time","End time ","Hours", "Username","Role","Status"};
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

    public double getPersonalWorkingHour(String account){
        double workingHour = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select * from t_staff_work_hour where staff_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                workingHour = (double)resultSet.getDouble("max_hour");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }finally {
            DButil.clos(connection,preparedStatement,resultSet);
        }
        return workingHour;
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
                    "WHERE accountNo = ? and (bid_status = 'approved'or bid_status = 'allocated');";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);

            resultSet = preparedStatement.executeQuery();

            String[] columnNames = {"Bid ID", "Slot id","Start time","End time ","Hours", "Username","Role","Status"};
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


        //判断是否有申请过当前Slot(包括 pending，allocated,approved )，如果数据库有 bid 记录 ，则返回false 不创建新的bid
        if (verifyAssignedBid(slotNo,account)){
            return false;
        };
        //判断 申请的角色 和slot中的角色是否已满
        workSlots workSlots = new workSlots();
        if (!workSlots.verifyNeedAndRegisteredRole(slotNo, role)){
            return false;
        }

        //判断slot hour 和 staff max hour
        double slotHour = workSlots.getSlotHour(slotNo);
        double staffHour = getPersonalWorkingHour(account);
        if (slotHour == 0 || staffHour == 0 || staffHour < slotHour) {
            return false;
        }

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

    /**
     * for manager to see all bid list
     * @return DefaultTableModel(Bid ID, slot_id,start time,end time ,hours, accountNo,bid_role,bid_status)
     */
    public DefaultTableModel viewAllBid(){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT t_bid.bid_id, t_bid.slot_id, t_work_slot.start_time, t_work_slot.end_time, t_work_slot.hour_duration, t_bid.accountNo, t_bid.bid_role, t_bid.bid_status  \n" +
                    "FROM t_bid \n" +
                    "JOIN t_work_slot ON t_bid.slot_id = t_work_slot.slot_id \n" +
                    "WHERE bid_status != 'deleted';";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Bid ID", "Slot id","Start time","End time ","Hours", "Username","Role","Status"};
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
     * for manager to allocate work to staff (create new bid and indicate status as "allocated")
     *
     * @param SlotNO SLOT number
     * @param account staff account
     * @param role role
     * @return if add success return true
     */
    public boolean allocateWork(int SlotNO,String account,String role){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            //create new bid
            String sql = "INSERT INTO t_bid (slot_id,accountNo,bid_role,bid_status) VALUES ( ?,?,?,'allocated'); ";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, SlotNO);
            preparedStatement.setString(2, account);
            preparedStatement.setString(3, role);

            //if add succeeds count will be 1
            int count = preparedStatement.executeUpdate();

            if (count==1){
                System.out.println("4");

                double slotHour=0;
                double staffWorkingHour=0;


                    // All conditions are met, update is_full to true
                String updateQuery = "select hour_duration from t_work_slot WHERE slot_id = ?;";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, SlotNO); //
                updateStatement.executeQuery();
                ResultSet slotHourResultSet = updateStatement.executeQuery();
                while (slotHourResultSet.next()){
                    slotHour =slotHourResultSet.getDouble("hour_duration");
                }

                String updateQuery2 = "select max_hour from t_staff_work_hour WHERE staff_id = ?;";
                PreparedStatement updateStatement2 = connection.prepareStatement(updateQuery2);
                updateStatement2.setString(1, account); //
                updateStatement2.executeQuery();
                ResultSet slotHourResultSet2 = updateStatement2.executeQuery();
                while (slotHourResultSet2.next()){
                    staffWorkingHour =slotHourResultSet2.getDouble("max_hour");
                }
                int newMaxHour = (int) (staffWorkingHour-slotHour);

                // 更新 员工工作时间
                indicatePersonalWorkingHour(account,newMaxHour);

                // 更新 slot the number of role registered
                workSlots workSlots = new workSlots();
                return workSlots.updateRoleRegistered(SlotNO, role);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }

    /**
     * for manager to check if this staff have already assigned this slot
     * @param SlotNO slot no
     * @param account account
     * @return if this staff have already assigned this slot return false
     */
    public boolean verifyAssignedBid(int SlotNO,String account){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "select * from t_bid where slot_id = ? and accountNo = ? and bid_status!='deleted';";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, SlotNO);
            preparedStatement.setString(2, account);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }

    public boolean ApprovedBid(int bidNO){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_bid set bid_status = 'approved' where bid_id =?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bidNO);

            preparedStatement.executeUpdate();

            int count = preparedStatement.executeUpdate();
            if (count==1){
                Map<String, Object> bidInfo = getBidInformation(bidNO);

                double slotHour=0;
                double staffWorkingHour=0;


                // All conditions are met, update is_full to true
                String updateQuery = "select hour_duration from t_work_slot WHERE slot_id = ?;";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, (int)bidInfo.get("slot_id")); //

                updateStatement.executeQuery();
                ResultSet slotHourResultSet = updateStatement.executeQuery();
                while (slotHourResultSet.next()){
                    slotHour =slotHourResultSet.getDouble("hour_duration");
                }

                String updateQuery2 = "select max_hour from t_staff_work_hour WHERE staff_id = ?;";
                PreparedStatement updateStatement2 = connection.prepareStatement(updateQuery2);
                updateStatement2.setString(1, (String)bidInfo.get("accountNo")); //
                updateStatement2.executeQuery();
                ResultSet slotHourResultSet2 = updateStatement2.executeQuery();
                while (slotHourResultSet2.next()){
                    staffWorkingHour =slotHourResultSet2.getDouble("max_hour");
                }
                int newMaxHour = (int) (staffWorkingHour-slotHour);

                // 更新 员工工作时间
                indicatePersonalWorkingHour((String) bidInfo.get("accountNo"),newMaxHour);

                // 更新 slot the number of role registered
                workSlots workSlots = new workSlots();
                return workSlots.updateRoleRegistered((int)bidInfo.get("slot_id"), (String) bidInfo.get("bid_role"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }

    public DefaultTableModel viewPendingBid(){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT t_bid.bid_id, t_bid.slot_id, t_work_slot.start_time, t_work_slot.end_time, t_work_slot.hour_duration, t_bid.accountNo, t_bid.bid_role, t_bid.bid_status  \n" +
                    "FROM t_bid \n" +
                    "JOIN t_work_slot ON t_bid.slot_id = t_work_slot.slot_id \n" +
                    "WHERE bid_status = 'pending';";
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Bid ID", "Slot id","Start time","End time ","Hours", "Username","Role","Status"};
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


    public Map<String, Object> getBidInformation(int bidNO){
        Map<String, Object> bidInfo = new HashMap<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select * from t_bid where bid_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bidNO);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bid_id = resultSet.getInt("bid_id");
                int slot_id = resultSet.getInt("slot_id");
                String accountNo = resultSet.getString("accountNo");
                String bid_role = resultSet.getString("bid_role");

                bidInfo.put("bid_id",bid_id);
                bidInfo.put("slot_id",slot_id);
                bidInfo.put("accountNo",accountNo);
                bidInfo.put("bid_role",bid_role);
                //return bidInfo;
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }finally {
            DButil.clos(connection,preparedStatement,resultSet);
        }
        return bidInfo;

    }


    public boolean RejectBid(int bidNO){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_bid set bid_status = 'rejected' where bid_id = ? and bid_status = 'pending';";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bidNO);

            preparedStatement.executeUpdate();

            int count = preparedStatement.executeUpdate();
            return  count==1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }

}
