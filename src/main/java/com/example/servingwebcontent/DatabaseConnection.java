package com.example.servingwebcontent;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) throws JSchException, SQLException, IOException {
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> data = db.getData("documents");

        // Print returned data.
        for (Map<String, Object> row:data) {
            for (Map.Entry<String, Object> rowEntry : row.entrySet()) {
                System.out.print(rowEntry.getKey() + " = " + rowEntry.getValue() + ", ");
            }
            System.out.print("\n");
        }
    }
    public List<Map<String, Object>> getData(String table) throws IOException, JSchException, SQLException {
        // Initialize a list for table data as ArrayList.
        List<Map<String, Object>> dataList = new ArrayList<>();

        // Load properties for SSH and DB access.
        InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties");
        Properties prop = new Properties();
        prop.load(input);

        int lport = Integer.parseInt(prop.getProperty("lport"));
        int rport = Integer.parseInt(prop.getProperty("rport"));
        String lhost = prop.getProperty("lhost");
        String rhost = prop.getProperty("rhost");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String dbuser = prop.getProperty("dbuser");
        String dbpassword = prop.getProperty("dbpassword");
        String url = "jdbc:postgresql://localhost:" + lport + "/webcrawl";
        String driver = "org.postgresql.Driver";

        Session session = null;
        Connection connection = null;

        try {
            // Config for SSH tunnel.
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");

            // Connect SSH with port forwarding.
            JSch jsch = new JSch();
            session=jsch.getSession(user, lhost, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            session.setPortForwardingL(lport, rhost, rport);

            Class.forName(driver).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, dbuser, dbpassword);

            // Get specified table, save objects in ArrayList and return.
            String query = "select * from a_" + table;
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
            if(session != null && session.isConnected()){
                session.delPortForwardingL(lport);
                session.disconnect();
            }
        }
        return dataList;
    }
}
