package com.example.servingwebcontent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) throws IOException, JSchException, SQLException {
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
        Connection conn = null;
        Session session = null;

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
            ResultSet usrs = stmt.executeQuery(query);

            while(usrs.next()){
                int id = usrs.getInt("id");
                String ct = usrs.getString("comment_tone");
                String pt = usrs.getString("personality");

                System.out.format("%s, %s, %s\n", id, ct, pt);
            }
            stmt.close();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
            if(session !=null && session.isConnected()){
                session.delPortForwardingL(lport);
                session.disconnect();
            }
        }
    }
}
