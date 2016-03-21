package com.photocontest.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 3/15/16
 * Time: 7:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectSysdba {
    public static final Logger logger = Logger.getLogger(ConnectSysdba.class);
    private String driver;
    private String url;
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection con;

    public ConnectSysdba(){
    }

    public void connect() throws Exception {
        driver = "oracle.jdbc.Driver";
        url = "jdbc:oracle:thin:@localhost:1521:xe";
        con = null;
        // load jdbc driver
        Class.forName(driver);
        // connection info
        Properties info = new Properties();
        info.put("user","sys");
        info.put("password","sys");
        info.put("internal_logon","sysdba");
        // connect as sysdba
        con = DriverManager.getConnection(url, info);
    }

    public void query() throws Exception {
        // check username
        String sql = "select user from dual";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        System.out.println("Username" + rs.getString(1));
    }

    public void disconnect() throws Exception{
        rs.close();
        ps.close();
        con.close();
    }
}

/*
    String driverName = "oracle.jdbc.driver.OracleDriver";
    Class.forName(driverName).newInstance();
    String nameForConnect = "sys as sysdba";
    String pass = "password";
    String url = "jdbc:oracle:thin:@192.168.0.1:1521:ORCL";
    Connection conn = DriverManager.getConnection(url, nameForConnect, pass);
*/