package com.example.jsf.dao.dto;

import com.example.jsf.dao.User;
import com.example.jsf.modele.UserModel;

import java.sql.*;

public class UserImp implements User {

    private Connection con;
    private boolean connexion(){
        String DB="jdbc:mysql://mysql-2e122153-fatimaezzahra2002dbibi-05ec.a.aivencloud.com:26117/USER";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(DB,"avnadmin","AVNS_oDekJxsmJIiU5lrvjbi");
            return true;
        }catch (ClassNotFoundException| SQLException e){
                return false;
        }

    }
    public boolean close(){
        try {
            if(con!=null){
                con.close();
                return true;
            }
            return true;
        }catch (SQLException e){
            return false;

        }
    }


    @Override
    public boolean setUser(UserModel user) {
        try {
            if(connexion()) {
                PreparedStatement creat = con.prepareStatement("INSERT INTO USER(NAME,DEPARTEMENT,DATE) VALUES(?,?,?)");
                creat.setString(1,user.getName());
                creat.setString(2,user.getDept());
                creat.setString(3,user.getDATE());
                creat.execute();
                while(!close()){

                }
            }else {
                throw new SQLException();
            }
            return true;
        }catch (SQLException e){
            return false;

        }

    }

    @Override
    public ResultSet findAll() {
        try {
            if(connexion()) {
                PreparedStatement read = con.prepareStatement("SELECT * FROM USER");
                ResultSet res= read.executeQuery();



                return res;
            }else {
                throw new SQLException();
            }

        }catch (SQLException e){
            return null;

        }

    }

    @Override
    public UserModel find(int id) {
        try {
            if(connexion()) {
                PreparedStatement read = con.prepareStatement("SELECT * FROM USER WHERE ID=?");
                read.setInt(1,id);
                ResultSet res= read.executeQuery();
                UserModel user =new UserModel();
                res.next();
                user.setId(id);
                user.setName(res.getString("NAME"));
                user.setDept(res.getString("DEPARTEMENT"));
                user.setDATE(res.getString("DATE"));


                while(!close()){

                }
                return user;
            }else {
                throw new SQLException();
            }

        }catch (SQLException e){
            return null;

        }

    }

    @Override
    public ResultSet findByName(String name) {
        try {
            if(connexion()) {
                PreparedStatement read = con.prepareStatement("SELECT * FROM USER WHERE NAME LIKE ?");
                read.setString(1,name);
                ResultSet res= read.executeQuery();
                UserModel user =new UserModel();




                return res;
            }else {
                throw new SQLException();
            }

        }catch (SQLException e){
            return null;

        }
    }

    @Override
    public boolean updateUser(UserModel user) {
        try {
            if(connexion()) {
                PreparedStatement update = con.prepareStatement("UPDATE USER SET NAME=?, DEPARTEMENT=?, DATE=? WHERE ID=?");
                update.setString(1, user.getName());
                update.setString(2, user.getDept());
                update.setString(3, user.getDATE());
                update.setInt(4, user.getId());
                update.executeUpdate();

                while(!close()){

                }
            }else {
                throw new SQLException();
            }
            return true;
        }catch (SQLException e){
            return false;

        }
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            if(connexion()) {
                PreparedStatement delete = con.prepareStatement("DELETE FROM USER WHERE ID=?");
                delete.setInt(1,id);
                delete.execute();
                close();
            }else {
                throw new SQLException();
            }
            return true;
        }catch (SQLException e){
            return false;

        }
    }
}
