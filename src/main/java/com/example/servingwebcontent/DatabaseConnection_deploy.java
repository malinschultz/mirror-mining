/*
This class handles direct connections to the DB, running on the base.camp server in order to execute queries on the DB.
Use this for deployment.

Jan Willruth
**/
package com.example.servingwebcontent;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.sql.*;

public class DatabaseConnection_deploy {
    public static void main(String[] args) throws IOException, SQLException {
        DatabaseConnection_deploy db = new DatabaseConnection_deploy();
        List<Map<String, Object>> data = db.executeQuery("select * from a_comments");

        for (Map<String, Object> row : data) {
            for (Map.Entry<String, Object> rowEntry : row.entrySet()) {
                System.out.print(rowEntry.getKey() + " = " + rowEntry.getValue() + ", ");
            }
            System.out.print("\n");
        }
    }

    public List<Map<String, Object>> executeQuery(String query) throws IOException, SQLException {
        List<Map<String, Object>> dataList = new ArrayList<>();

        InputStream input = DatabaseConnection_deploy.class.getClassLoader().getResourceAsStream("database_deploy.properties");
        Properties prop = new Properties();
        prop.load(input);

        String host = prop.getProperty("host");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = "jdbc:postgresql://" + host;
        String driver = "org.postgresql.Driver";
        Connection connection = null;

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, user, password);

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

            while(rs.next()) {
                Map<String, Object> data = new HashMap<>(cols);
                for(int i = 1; i <= cols; i++) {
                    data.put(md.getColumnName(i), rs.getObject(i));
                }
                dataList.add(data);
            }
            rs.close();
            st.close();
            return dataList;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return dataList;
    }
}