package com.qw.util;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator on 2017/2/17.
 */
public class DBUtil {

    public static void testDB_SQLServer2008(){
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://192.168.0.185:1433;DatabaseName=ksoa_htls";
        String userName="sa";
        String userPwd="ht8007";
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("加载驱动失败！");
        }
        try{
            @SuppressWarnings("unused")
            Connection dbConn= DriverManager.getConnection(dbURL,userName,userPwd);

            String sql = "select * from maxbh where biaoshi='LGZ'";
            PreparedStatement pstmt = dbConn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");


            System.out.println("\n\n\n=============存储过程begin===============");
            //CallableStatement cs = dbConn.prepareCall("{call dbo.test_procedure(?,?,?)}");
            CallableStatement cs = dbConn.prepareCall("{call dbo.SOF_getmaxbh(?,?,?)}");
            //cs.registerOutParameter(1,Types.INTEGER);
            cs.setString(1,"LGZ");
            cs.setInt(2,3);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.execute();
            //System.out.println (cs.getString(1));
            System.out.println (cs.getString(3));
            System.out.println("=============存储过程end===============");
            dbConn.close();

            System.out.println("连接数据库成功！");
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.print("SQL Server连接失败！");
        }
    }

    public static void testDB_SyBase(){
        try {
            String url = "jdbc:sybase:Tds:192.168.5.167:5000/szdb";// 数据库名
            Properties sysProps = System.getProperties();
            sysProps.put("user", "sa"); // 设置数据库访问用户名
            sysProps.put("password", ""); // 密码

            Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();
            System.out.println("加载驱动成功！");
            Connection conn = DriverManager.getConnection(url, sysProps);
            System.out.println("连接数据库成功！");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select mc_id,mc_mem_id, mc_start_date from pos.mem_card c where c.mc_id ='178990'";// 表
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("oject_id:"+rs.getString(1)+",oject_name:"+rs.getString(2)); // 取得第二列的值
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("数据库访问失败"+e.getMessage());

        }
    }
    public static void testDB_Oracle(){
        try {
            String url = "jdbc:oracle:thin:@124.202.158.54:1521:orcl";// 数据库名
            String user = "puan5"; // 设置数据库访问用户名
            String password = "test"; // 密码
            // 加载Oracle驱动程序
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("加载驱动成功！");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("连接数据库成功！");

            String sql = "select INSIDERID,MOBILE from gpcs_insider where INSIDERID is not null and mobile = ?";
            PreparedStatement  pre = conn.prepareStatement(sql);
            pre.setString(1, "13593619895");
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                // 当结果集不为空时
                System.out.println("会员卡号:" + result.getString("INSIDERID") + "手机号:"
                        + result.getString("MOBILE"));
            }
            result.close();
            pre.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("数据库访问失败"+e.getMessage());

        }
    }

    public static void main(String[] args)
    {
        //testDB_SQLServer2008();

        //testDB_SyBase();
        testDB_Oracle();

    }
}
