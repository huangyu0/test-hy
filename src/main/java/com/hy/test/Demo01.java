package com.hy.test;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Demo01 {

    public List<Map<String, String>> showInfo() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Map<String, String>> list = new ArrayList();

        try {
            Class.forName(OracleDriver.class.getName());
            String url = "jdbc:oracle:thin:@210.22.155.62:1521:pkdb";
            //建立连接
            connection = DriverManager.getConnection(url, "osschk", "osschk123");
            String sql = "select * from MM_TABLE";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            //处理执行结果
            while (rs.next()) {
                Map<String, String> map = new HashMap();
                String id = rs.getString("id");
                String code = rs.getString("code");

                map.put("id", id);
                map.put("code", code);
                list.add(map);


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return list;
    }

    public List<Map<String, String>> showField(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String, String>> list = new ArrayList();

        try {
            Class.forName(OracleDriver.class.getName());
            String url = "jdbc:oracle:thin:@210.22.155.62:1521:pkdb";
            //建立连接
            connection = DriverManager.getConnection(url, "osschk", "osschk123");
            String sql = "select * from MM_FIELD where TABLE_ID = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            //处理执行结果
            while (rs.next()) {
                Map<String, String> map = new HashMap();
                String code = rs.getString("code");
                String name = rs.getString("name");
                String fieldId = rs.getString(String.valueOf("id"));
                map.put("fieldId", fieldId);
                map.put("code", code);
                map.put("name", name);
                list.add(map);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }


    public List<Map<String, String>> queryFiledDate(String filedCodes, String tableCode) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String, String>> list = new ArrayList();

        try {
            Class.forName(OracleDriver.class.getName());
            String url = "jdbc:oracle:thin:@210.22.155.62:1521:pkdb";
            //建立连接
            connection = DriverManager.getConnection(url, "osschk", "osschk123");

//            String[] stringArr = filedCodes.split(",");
//            for (int j = 0; j < stringArr.length; j++) {
//                String i = stringArr[j];
//                System.out.println("这是i================" + i);
//            }

            // --直接把字符串拼进sql,要用加法       --gx
            String sql = "select " + filedCodes + " from " + tableCode;
            // 打印一下需要执行的sql         --gx
            System.out.println(sql);
            pstmt = connection.prepareStatement(sql);

            // 如果数据库没数据会执行失败        --gx

            rs = pstmt.executeQuery();

            // 在分成字段去结果
            String[] fields = filedCodes.split(",");
            //处理执行结果
            while (rs.next()) {
                Map<String, String> map = new HashMap();
                for (String field : fields) {
                    map.put(field, rs.getString(field));
                }
                list.add(map);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                // 关闭conn就足够了   --gx
//                rs.close();
//                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }


    //测试
    public static void main(String[] args) {
        Demo01 demo01 = new Demo01();
        demo01.showInfo();
    }
}
