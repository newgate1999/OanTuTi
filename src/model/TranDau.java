/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Okami
 */
public class TranDau implements Serializable {
    private int id;
    private ArrayList<VanChoi> listVanChoi;

    public TranDau() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TranDau(ArrayList<VanChoi> listVanChoi) {
        this.listVanChoi = listVanChoi;
    }

    public ArrayList<VanChoi> getListVanChoi() {
        return listVanChoi;
    }

    public void setListVanChoi(ArrayList<VanChoi> listVanChoi) {
        this.listVanChoi = listVanChoi;
    }
}
