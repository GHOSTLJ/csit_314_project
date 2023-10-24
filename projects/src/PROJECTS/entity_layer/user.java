package PROJECTS.entity_layer;

import PROJECTS.boundary_layer.adminWorkSpace;

import PROJECTS.boundary_layer.ownerWorkSpace;
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

                if (resultSet.getInt("t_u_pro_no")==1){
                    new adminWorkSpace(resultSet.getString("name"),resultSet.getString("description"));
                    return true;

                } else if (resultSet.getInt("t_u_pro_no")==2) {
                    new ownerWorkSpace();
                    return true;
                } else if (resultSet.getInt("t_u_pro_no")==3) {

                } else if (resultSet.getInt("t_u_pro_no")==4) {

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
