/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.view.ServerView;

/**
 *
 * @author Okami
 */
public class DAO{
    Connection con;
    private final String dbServer = "localhost";
    private final String dbName = "oantuti";
    private final String dbUsername = "OanTuTi";
    private final String dbPassword = "123456";

    public DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://" + this.dbServer +":3306/" + dbName +"?useSSL=false"
                    ,this.dbUsername,this.dbPassword);
            ServerView.log("Connect database success!!!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
