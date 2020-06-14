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
        List<Map<String, Object>> userData = db.getData();

        // Print returned data.
        for (Map<String, Object> row:userData) {
            for (Map.Entry<String, Object> rowEntry : row.entrySet()) {
                System.out.print(rowEntry.getKey() + " = " + rowEntry.getValue() + ", ");
            }
        }
    }
    public List<Map<String, Object>> getData() throws IOException, JSchException, SQLException {
        List<Map<String, Object>> usrs = new ArrayList<>();

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
        Connection conn = null;

        try{
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session=jsch.getSession(user, lhost, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            session.setPortForwardingL(lport, rhost, rport);

            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, dbuser, dbpassword);

            // Simple SQL query example
            String query = "select * from a_users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            while(rs.next()) {
                Map<String, Object> usr = new HashMap<>(columns);
                for(int i = 1; i <= columns; ++i){
                    usr.put(md.getColumnName(i), rs.getObject(i));
                }
                usrs.add(usr);
            }
            rs.close();
            stmt.close();
            return usrs;
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
            if(session != null && session.isConnected()){
                session.delPortForwardingL(lport);
                session.disconnect();
            }
        }
        return usrs;
    }
}
