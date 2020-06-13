package com.example.servingwebcontent;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) throws SQLException, JSchException {
        int lport=7070;
        int rport=5432;
        String lhost="rzssh1.informatik.uni-hamburg.de";
        String rhost="basecamp-bigdata.informatik.uni-hamburg.de";
        String user="6willrut";
        String password="7TuB8binGL";
        String dbuser="webcrawl2020";
        String dbpassword="BcN#DvUAgp";
        String url="jdbc:postgresql://localhost:" + lport + "/webcrawl";
        String driver="org.postgresql.Driver";
        Connection conn = null;
        Session session= null;
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
        }catch(Exception e){
            e.printStackTrace();
        }finally{
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
