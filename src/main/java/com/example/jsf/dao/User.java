package com.example.jsf.dao;

import com.example.jsf.modele.UserModel;

import java.sql.ResultSet;

public interface User {

    public boolean setUser(UserModel user);
    public ResultSet findAll();
    public UserModel find(int id);
    public ResultSet findByName(String name);
    public boolean updateUser(UserModel user);
    public  boolean deleteUser(int id);

}
