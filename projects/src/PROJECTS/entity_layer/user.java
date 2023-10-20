package PROJECTS.entity_layer;

import PROJECTS.boundary_layer.adminWorkSpace;

import PROJECTS.control_layer.systemAdmin.*;
import PROJECTS.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user {
    public boolean LoginVerify(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取连接
            connection = DButil.getConnection();
            //获取预编译的数据库操作对象
            //select  * from t_user_account join t_user_profile where (accountNo = 'admin' and password = '123456')group by accountNo;;
            //String sql = "select * from t_user_account where accountNo = ? and password = ?";

            String sql = "select  * from t_user_account join t_user_profile where (accountNo = ? and password = ?)group by accountNo";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //4.执行sql
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()&&resultSet.getBoolean("status")){

                if (resultSet.getString("description").equals("Administrator")){
                    new adminWorkSpace(resultSet.getString("name"),resultSet.getString("description"));
                    return true;

                } else if (resultSet.getString("description").equals("cafe owner")) {

                } else if (resultSet.getString("description").equals("manager")) {

                } else if (resultSet.getString("description").equals("staff")) {

                }
            }else {
                return false;
            }

        } catch (SQLException c) {
            c.printStackTrace();
        }finally {
            //释放资源
            DButil.clos(connection,preparedStatement,resultSet);
        }
        return false;
        //return new adminWorkSpace("test","test");
        //return new User("Invalid Account or Password or the account has been disabled. please contact administrator");
    }
}
