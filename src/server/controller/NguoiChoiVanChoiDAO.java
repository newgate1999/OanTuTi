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
import model.NguoiChoi;
import model.NguoiChoiVanChoi;
import model.TranDau;
import model.VanChoi;

/**
 *
 * @author Okami
 */
public class NguoiChoiVanChoiDAO extends DAO {
    
    private final String createNCVCSQL = "INSERT INTO nguoichoi_vanchoi (nguoichoiid, vanchoiid) VALUES (?,?)";
    private final String updateNCVCSQL = "UPDATE nguoichoi_vanchoi SET nuocChoi = ?, diem = ? WHERE id = ?";
    private final String getNCVCSQL = "SELECT * FROM nguoichoi_vanchoi WHERE vanchoiid = ?";
    
    private NguoiChoiDAO userDAO;
    private VanChoiDAO vanChoiDAO;

    public NguoiChoiVanChoiDAO() {
        super();
        userDAO = new NguoiChoiDAO();
        vanChoiDAO = new VanChoiDAO();
    }
    
    public NguoiChoiVanChoi createNCVC(int nguoichoiid, int vanchoiid) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(createNCVCSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, nguoichoiid);
            preparedStatement.setInt(2, vanchoiid);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            NguoiChoiVanChoi nvvc = new NguoiChoiVanChoi();
            if (generatedKeys.next()) {
                nvvc.setId(generatedKeys.getInt(1));
                return nvvc;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(TranDauDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean updateNCVC(NguoiChoiVanChoi ncvc) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(updateNCVCSQL);
            preparedStatement.setInt(1, ncvc.getNuocChoi());
            preparedStatement.setInt(2, ncvc.getDiem());
            preparedStatement.setInt(3, ncvc.getId());
            boolean res = preparedStatement.execute();
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(TranDauDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<NguoiChoiVanChoi> getNCVCByVanChoiId(int vanchoiid) {
        ArrayList<NguoiChoiVanChoi> rs = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(getNCVCSQL);
            preparedStatement.setInt(1, vanchoiid);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                int idUser = res.getInt("nguoichoiid");
                NguoiChoi user = userDAO.getUserById(idUser);
                NguoiChoiVanChoi ncvc = new NguoiChoiVanChoi(res.getInt("id"), user, res.getInt("diem"), res.getInt("nuocChoi"));
                rs.add(ncvc);
            }
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(NguoiChoiVanChoiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getResultTurn(VanChoi vanChoi) {
        System.out.println(vanChoi.getId());
        ArrayList<NguoiChoiVanChoi> listNCVC = getNCVCByVanChoiId(vanChoi.getId());
        
        NguoiChoi user1 = listNCVC.get(0).getNguoiChoi();
        NguoiChoi user2 = listNCVC.get(1).getNguoiChoi();
        
        int nuocChoi1 = listNCVC.get(0).getNuocChoi();
        int nuocChoi2 = listNCVC.get(1).getNuocChoi();
        
        if (nuocChoi1 == nuocChoi2) {
            return ServerController.listNuocChoi.get(nuocChoi1) + " bằng " + ServerController.listNuocChoi.get(nuocChoi2) + ". Hòa!!!";
        } else {
            if ((nuocChoi1 == 1 && nuocChoi2 == 3) || (nuocChoi1 == 3 && nuocChoi2 == 2) || (nuocChoi1 == 2 && nuocChoi2 == 1)) {
                listNCVC.get(0).setDiem(listNCVC.get(0).getDiem() + 1);
                updateNCVC(listNCVC.get(0));
                return ServerController.listNuocChoi.get(nuocChoi1) + " thắng " + ServerController.listNuocChoi.get(nuocChoi2) + ". " + user1.getUsername() + " thắng!!!";
            }
            if ((nuocChoi2 == 1 && nuocChoi1 == 3) || (nuocChoi1 == 2 && nuocChoi2 == 3) || (nuocChoi1 == 1 && nuocChoi2 == 2)) {
                listNCVC.get(1).setDiem(listNCVC.get(1).getDiem() + 1);
                updateNCVC(listNCVC.get(1));
                return ServerController.listNuocChoi.get(nuocChoi2) + " thắng " + ServerController.listNuocChoi.get(nuocChoi1) + ". " + user2.getUsername() + " thắng!!!";
            }
            if (nuocChoi1 == 0) {
                listNCVC.get(0).setDiem(listNCVC.get(0).getDiem() + 1);
                updateNCVC(listNCVC.get(0));
                return user1.getUsername() + " không chọn. " + user2.getUsername() + " thắng!!!";
            }
            if (nuocChoi2 == 0) {
                listNCVC.get(1).setDiem(listNCVC.get(1).getDiem() + 1);
                updateNCVC(listNCVC.get(1));
                return user2.getUsername() + " không chọn. " + user1.getUsername() + " thắng!!!";
            }
        }
        return null;
    }
    
    public String getResultGame(TranDau tranDau) {
        ArrayList<VanChoi> listVanChoi = vanChoiDAO.getVanChoiByTranDauId(tranDau.getId());
        
        int diem1 = 0;
        int diem2 = 0;
        
        NguoiChoi user1 = new NguoiChoi();
        NguoiChoi user2 = new NguoiChoi();
        
        for (int i = 0; i< listVanChoi.size();i++) {
            ArrayList<NguoiChoiVanChoi> listNCVC = getNCVCByVanChoiId(listVanChoi.get(i).getId());
            user1 = listNCVC.get(0).getNguoiChoi();
            user2 = listNCVC.get(1).getNguoiChoi();
            diem1 += listNCVC.get(0).getDiem();
            diem2 += listNCVC.get(1).getDiem();
        }
        
        if (diem1 == diem2) {
            return "Hòa";
        } else if (diem1 > diem2) {
            return user1.getUsername() + " thắng";
        } else {
            return user2.getUsername() + " thắng";
        }
    }
}
//0 : khong chon;    1: Keo;   2: Bua;      3: Bao
