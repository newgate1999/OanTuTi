/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Okami
 */
public class NguoiChoiVanChoi implements Serializable {
    private int id;
    private NguoiChoi nguoiChoi;
    private int diem;
    private String mota;
    private int nuocChoi;
    private VanChoi vanChoi;

    public NguoiChoiVanChoi() {
    }

    public NguoiChoiVanChoi(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public NguoiChoiVanChoi(int id, NguoiChoi nguoiChoi, int diem, int nuocChoi) {
        this.id = id;
        this.nguoiChoi = nguoiChoi;
        this.diem = diem;
        this.nuocChoi = nuocChoi;
    }

    public NguoiChoiVanChoi(int id, NguoiChoi nguoiChoi, int diem, String mota, int nuocChoi) {
        this.id = id;
        this.nguoiChoi = nguoiChoi;
        this.diem = diem;
        this.mota = mota;
        this.nuocChoi = nuocChoi;
    }

    public NguoiChoiVanChoi(int id, NguoiChoi nguoiChoi, int diem, String mota, int nuocChoi, VanChoi vanChoi) {
        this.id = id;
        this.nguoiChoi = nguoiChoi;
        this.diem = diem;
        this.mota = mota;
        this.nuocChoi = nuocChoi;
        this.vanChoi = vanChoi;
    }

    public VanChoi getVanChoi() {
        return vanChoi;
    }

    public void setVanChoi(VanChoi vanChoi) {
        this.vanChoi = vanChoi;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NguoiChoi getNguoiChoi() {
        return nguoiChoi;
    }

    public void setNguoiChoi(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getNuocChoi() {
        return nuocChoi;
    }

    public void setNuocChoi(int nuocChoi) {
        this.nuocChoi = nuocChoi;
    }
    
}
