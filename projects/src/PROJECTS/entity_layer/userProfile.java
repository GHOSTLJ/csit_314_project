package PROJECTS.entity_layer;

import PROJECTS.util.DButil;

import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class userProfile {
    /**
     * for cafe owner to view all Slots
     * @return if found data then return a DefaultTableModel(table contain information)
     */

    public DefaultTableModel viewProfileFromDatabase(){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select pro_no, description from  t_user_profile;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Profile No", "Profile"};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String pro_no = resultSet.getString("pro_no");
                String description = resultSet.getString("description");


                // Add fetched data to the table model
                Object[] rowData = {pro_no, description};
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
     * for admin to verify that the profile NO is in or not the database
     *
     * @param profileNo profile number
     * @return if in database return true
     */
    public boolean profileNoVerification(int profileNo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select * from  t_user_profile where pro_no = ?;";
            preparedStatement = connection.prepareStatement(sql);

            // Convert java.util.Date to java.sql.Timestamp
            preparedStatement.setInt(1, profileNo);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }finally {
            DButil.clos(connection,preparedStatement,resultSet);
        }
        return false;
    }

    /**
     * for admin verify that the profile NAME is in or not the database
     * @param profileName  profile NAME
     * @return if NAME in database then return true
     */
    public boolean profileNameVerification(String profileName){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select * from  t_user_profile where description = ?;";
            preparedStatement = connection.prepareStatement(sql);

            // Convert java.util.Date to java.sql.Timestamp
            preparedStatement.setString(1, profileName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }finally {
            DButil.clos(connection,preparedStatement,resultSet);
        }
        return false;
    }

    /**
     * for admin to add new profile
     * @param profile profile name
     * @return if add success then return true
     */
    public boolean addNewProfile(String profile){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "INSERT INTO t_user_profile (description) VALUES (?); ";
            preparedStatement = connection.prepareStatement(sql);

            // Convert java.util.Date to java.sql.Timestamp
            preparedStatement.setString(1,profile);


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
     * for admin to search a profile
     * @param profile profile name
     * @return if profile name in database then return DefaultTableModel(contain profile information)
     */
    public DefaultTableModel searchProfile(String profile){
        DefaultTableModel myModel = new DefaultTableModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "SELECT * FROM t_user_profile WHERE description LIKE CONCAT('%', ?, '%')";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,profile);
            resultSet = preparedStatement.executeQuery();

            // Define column names for the table model
            String[] columnNames = {"Profile No", "Profile"};
            myModel.setColumnIdentifiers(columnNames);
            // Iterate through the result set and add data to the table model
            while (resultSet.next()) {
                String pro_no = resultSet.getString("pro_no");
                String description = resultSet.getString("description");


                // Add fetched data to the table model
                Object[] rowData = {pro_no, description};
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
     * for admin to update profile
     * @param profileNO profile number
     * @param profileName new profile name
     * @return if update success return true
     */
    public boolean updateProfileToDatabase(int profileNO, String profileName){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            //String sql = "update t_user_account set password = ?,name =?,t_u_pro_no = ? where accountNo =?;";
            String sql = "update t_user_profile set description = ?  where pro_no =?;";
            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, profileName);
            preparedStatement.setInt(2, profileNO);


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
     * for admin to suspend a profile
     * @param profileNo profile number
     * @return if suspend success return true
     */
    public boolean suspendProfile(int profileNo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_user_account set status = false where t_u_pro_no =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, profileNo);


            int count = preparedStatement.executeUpdate();
            //if suspend succeeds, count will not be 0 and return true
            return count != 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            DButil.clos(connection, preparedStatement, resultSet);

        }
        return false;
    }

    /**
     *for admin to activate a profile
     * @param profileNo profile number
     * @return if activate success return true
     */
    public boolean activateProfile(int profileNo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DButil.getConnection();
            String sql = "update t_user_account set status = true where t_u_pro_no =?;";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, profileNo);


            int count = preparedStatement.executeUpdate();
            //if activate succeeds, count will not be 0 and return true
            return count != 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            DButil.clos(connection, preparedStatement, resultSet);

        }
        return false;
    }
}
