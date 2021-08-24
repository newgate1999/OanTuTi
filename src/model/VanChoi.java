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
public class VanChoi implements Serializable {
    private int id;
    private String mota;
    private String thoigian;
    private NguoiChoiVanChoi ncvc1;
    private NguoiChoiVanChoi ncvc2;

    public VanChoi() {
    }

    public VanChoi(NguoiChoiVanChoi ncvc1, NguoiChoiVanChoi ncvc2) {
        this.ncvc1 = ncvc1;
        this.ncvc2 = ncvc2;
    }

    public VanChoi(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public NguoiChoiVanChoi getNcvc1() {
        return ncvc1;
    }

    public void setNcvc1(NguoiChoiVanChoi ncvc1) {
        this.ncvc1 = ncvc1;
    }

    public NguoiChoiVanChoi getNcvc2() {
        return ncvc2;
    }

    public void setNcvc2(NguoiChoiVanChoi ncvc2) {
        this.ncvc2 = ncvc2;
    }
}
