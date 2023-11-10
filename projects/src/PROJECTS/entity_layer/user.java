package PROJECTS.entity_layer;

import PROJECTS.boundary_layer.adminPage;


import PROJECTS.boundary_layer.managerPage;
import PROJECTS.boundary_layer.ownerPage;

import PROJECTS.boundary_layer.staffPage;
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

            String sql = "select  * from t_user_account join t_user_profile where (accountNo = ? and password = ?)group by accountNo";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //4.执行sql
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()&&resultSet.getBoolean("status")){

                if (resultSet.getInt("t_u_pro_no")==1){
                    new adminPage(resultSet.getString("name"));
                    return true;

                } else if (resultSet.getInt("t_u_pro_no")==2) {
                    new ownerPage(resultSet.getString("name"));
                    return true;
                } else if (resultSet.getInt("t_u_pro_no")==3) {
                    new managerPage();
                    return true;
                } else if (resultSet.getInt("t_u_pro_no")==4) {
                    new staffPage(resultSet.getString("name"),resultSet.getString("accountNo"));
                    return true;
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
    }
}
