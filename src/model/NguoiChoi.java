/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Okami
 */
public class NguoiChoi implements Serializable{
    private int id;
    private String username;
    private String password;
    private String fullName;
    private int userStatus;
    private int win;
    private int draw;
    private int lose;

    public NguoiChoi() {
    }

    public NguoiChoi(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public NguoiChoi(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public NguoiChoi(int id, String username, String password, String fullName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public NguoiChoi(int id, String username, String password, String fullName, int userStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userStatus = userStatus;
    }
    
    

    public NguoiChoi(int id, String username, String password, String fullName, int statusUser, int win, int draw, int lose) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userStatus = statusUser;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.fullName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NguoiChoi other = (NguoiChoi) obj;
        System.out.println(other);
        if (this.id == other.getId() &&
            this.username.equals(other.getUsername())&&
            this.password.equals(other.getPassword())) {
                return true;
        } else {
            return false;
        }
    }
}
