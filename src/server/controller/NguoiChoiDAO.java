/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NguoiChoi;

/**
 *
 * @author Okami
 */
public class NguoiChoiDAO extends DAO{
    
    private final String checkLoginSQL = "SELECT * FROM nguoichoi WHERE username = ? AND password = ?" ;
    private final String getUserSQL = "SELECT * FROM nguoichoi WHERE id = ?" ;
    private final String updateStatusSQL = "UPDATE nguoichoi SET userStatus = ?, win = ?, draw = ?, lose = ? WHERE id = ?" ;
    private final String getListUserSQL = "SELECT * FROM nguoichoi WHERE userStatus >= ?" ;

    public NguoiChoiDAO() {
        super();
    }
    
    public NguoiChoi checkLogin(NguoiChoi user) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(checkLoginSQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                NguoiChoi rs = new NguoiChoi(res.getInt("id"), res.getString("username"),res.getString("password"),res.getString("fullName"));
                return rs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiChoiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public NguoiChoi getUserById(int id) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(getUserSQL);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                NguoiChoi rs = new NguoiChoi(res.getInt("id"), res.getString("username"),res.getString("password"),res.getString("fullName"));
                return rs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiChoiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public boolean updateStatus(NguoiChoi user) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(updateStatusSQL);
            preparedStatement.setInt(1, user.getUserStatus());
            preparedStatement.setInt(2, user.getWin());
            preparedStatement.setInt(3, user.getDraw());
            preparedStatement.setInt(4, user.getLose());
            preparedStatement.setInt(5, user.getId());
            boolean res = preparedStatement.execute();
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(NguoiChoiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<NguoiChoi> getListUser(int status){
        ArrayList<NguoiChoi> listUser = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(getListUserSQL);
            preparedStatement.setInt(1, status);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                NguoiChoi rs = new NguoiChoi(res.getInt("id"), res.getString("username"),res.getString("password"),res.getString("fullName"),res.getInt("userStatus"));
                listUser.add(rs);
            }
            return listUser;
        } catch (SQLException ex) {
            Logger.getLogger(NguoiChoiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
