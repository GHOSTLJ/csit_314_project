package PROJECTS.entity_layer;

import PROJECTS.util.DButil;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Vector;

public class userAccount {

    /**
     * This method is suitable for the administrator workSpace,
     * and this function is to return information about all user's information in the database
     * @return DefaultTableModel : Write all users information into the tableModel and return it directly
     */
    public DefaultTableModel viewFromDatabase() {
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT accountNo, password, name, description,status FROM t_user_account JOIN t_user_profile ON t_user_account.t_u_pro_no = t_user_profile.pro_no;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Username", "Password", "Name", "Profile","Status"};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String accountNo = resultSet.getString("accountNo");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String status = resultSet.getBoolean("status")==true?"active":"inactive";

                // Add fetched data to the table model
                Object[] rowData = {accountNo, password, name, description,status};
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
     * for admin to refresh table
     * @return DefaultTableModel(contain user account information)
     */
    public DefaultTableModel refreshFromDataBase(){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT accountNo, password, name, description,status FROM t_user_account JOIN t_user_profile ON t_user_account.t_u_pro_no = t_user_profile.pro_no;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Username", "Password", "Name", "Description","Status"};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String accountNo = resultSet.getString("accountNo");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String status = resultSet.getBoolean("status")==true?"active":"inactive";
                // Add a new row to the table model
                myModel.addRow(new Object[]{accountNo, password, name, description, status});


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
     * This method is used to add new users to the database
     * @param userAddInfo a hashmap data type ,It contains information about the user to be added to the database
     * @return Return true if adding succeeds otherwise false
     */
    public boolean addUserToDatabase(Map<String, String> userAddInfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "INSERT INTO t_user_account (accountNo, password, name, t_u_pro_no) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userAddInfo.get("account"));
            preparedStatement.setString(2, userAddInfo.get("password"));
            preparedStatement.setString(3, userAddInfo.get("name"));
            preparedStatement.setString(4, userAddInfo.get("profile"));

            //if add succeeds count will be 1
            int count = preparedStatement.executeUpdate();

            int profileValue;
            profileValue = Integer.parseInt(userAddInfo.get("profile"));
            if (profileValue == 4) {
                     // Return false if profile value is not within database
                boolean counts = initWorkingHour(userAddInfo.get("account"));
            }

            return count == 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }



    /**
     * This method is used by administrators to find users
     * @param account account information
     * @return Returns a DefaultTableModel containing user information including "Account No", "Password", "Name", "Description","Status"
     */
    public DefaultTableModel searchUserFromDatabase(String account){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT * FROM t_user_account JOIN t_user_profile ON t_user_account.t_u_pro_no = t_user_profile.pro_no WHERE accountNo LIKE CONCAT('%', ?, '%')";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Username", "Password", "Name", "Description","Status"};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {


                String accountNo=resultSet.getString("accountNo");
                String password=resultSet.getString("password");
                String name=resultSet.getString("name");
                String description=resultSet.getString("description");
                String status=resultSet.getBoolean("status")==true?"active":"inactive";

                // Add fetched data to the table model
                Object[] rowData = {accountNo,password, name,description,status};
                myModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return myModel;
    }




    /**
     * This method is used by administrators to update user information
     *
     * @param updateAddInfo a Map<String, String>  contain new user information
     * @return boolean : if count=1 then return true(database return)
     */
    public boolean updateUserToDatabase(Map<String, String> updateAddInfo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_user_account set password = ?,name =?,t_u_pro_no = ? where accountNo =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(4, updateAddInfo.get("account"));
            preparedStatement.setString(1, updateAddInfo.get("password"));
            preparedStatement.setString(2, updateAddInfo.get("name"));
            preparedStatement.setString(3, updateAddInfo.get("profile"));

            //if add succeeds count will be 1
            int count = preparedStatement.executeUpdate();

            // if change user profile as staff ,then add new record to staff work hour;
            int profileValue;
            profileValue = Integer.parseInt(updateAddInfo.get("profile"));
            if (profileValue == 4) {
                // Return false if profile value is not within database
                boolean counts = initWorkingHour(updateAddInfo.get("account"));
            }
            return count == 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.clos(connection, preparedStatement, resultSet);
        }
        return false;
    }


    /**
     * This method is used by the administrator to deactivate（suspend） the account
     *
     * @param account account information
     * @return if suspend success return true
     */
    public boolean suspendAccountToDatabase(String account){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_user_account set status = false where accountNo =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account);

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
     * for admin to activate an account
     *
     * @param account account
     * @return if activate success return true
     */
    public boolean activateAccountToDatabase(String account){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_user_account set status = true where accountNo =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account);

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
     * for manager to view all staff list including max working hours
     * @return DefaultTableModel "Account",  "Name", "Max working Hour"
     */
    public DefaultTableModel viewStaffList() {
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select t_user_account.accountNo,t_user_account.name,t_staff_work_hour.max_hour \n" +
                    "from t_user_account \n" +
                    "join t_staff_work_hour \n" +
                    "on t_user_account.accountNo = t_staff_work_hour.staff_id\n" +
                    "where t_u_pro_no = 4 and status = true;";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Username",  "Name", "Max working Hour"};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String accountNo = resultSet.getString("accountNo");
                String name = resultSet.getString("name");
                String hour = resultSet.getString("max_hour");

                // Add fetched data to the table model
                Object[] rowData = {accountNo, name, hour};
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

    public boolean initWorkingHour(String account){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "INSERT INTO t_staff_work_hour ( staff_id, max_hour) VALUES ( ?, 40); ";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account);

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
