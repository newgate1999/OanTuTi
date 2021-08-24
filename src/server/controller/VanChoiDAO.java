/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.VanChoi;

/**
 *
 * @author Okami
 */
public class VanChoiDAO extends DAO{
    
    private final String createTurnSQL = "INSERT INTO vanchoi (trandauid) VALUES (?)";
    private final String getVanChoiSQL = "SELECT * FROM vanchoi WHERE trandauid = ?";

    public VanChoiDAO() {
        super();
    }
    
    public VanChoi createTurn(int trandauid) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(createTurnSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, trandauid);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            VanChoi vanChoi = new VanChoi();
            if (generatedKeys.next()) {
                vanChoi.setId(generatedKeys.getInt(1));
                return vanChoi;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(TranDauDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<VanChoi> getVanChoiByTranDauId(int trandauid) {
        ArrayList<VanChoi> rs = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(getVanChoiSQL);
            preparedStatement.setInt(1, trandauid);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                VanChoi vanChoi = new VanChoi(res.getInt("id"));
                rs.add(vanChoi);
            }
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(NguoiChoiVanChoiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
