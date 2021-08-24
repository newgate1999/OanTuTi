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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TranDau;

/**
 *
 * @author Okami
 */
public class TranDauDAO extends DAO {

    private final String createGameSQL = "INSERT INTO trandau (ten) VALUES ('')";

    public TranDauDAO() {
        super();
    }

    public TranDau createTranDau() {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(createGameSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            TranDau tranDau = new TranDau();
            if (generatedKeys.next()) {
                tranDau.setId(generatedKeys.getInt(1));
                return tranDau;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(TranDauDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
