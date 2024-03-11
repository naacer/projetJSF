package com.example.jsf;

import com.example.jsf.dao.dto.UserImp;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import com.example.jsf.modele.UserModel;

import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;

import java.awt.*;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
@ViewScoped
public class MyBean implements Serializable {



    private int id;



    private String name;
    private String dep;
    private String date;
    private UserModel user;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private List<Map<String, Object>> dataList;

    private ResultSet res;

    public MyBean() throws SQLException {
        UserImp userSql=new UserImp();
        this.res=userSql.findAll();
         dataList = new ArrayList<>();
        while (res.next()) {
            Map<String, Object> row = new HashMap<>();
            ResultSetMetaData metaData = res.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), res.getObject(i));
            }
            dataList.add(row);
        }

    }
    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }

    public void setNAME(String name) {
        this.name = name;
    }
    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    @PostConstruct
    public void init(){
        user=new UserModel();
        UserImp usersql=new UserImp();
        ResultSet res=usersql.findAll();



    }
    public UserModel getUser(){
        return this.user;
    }
    public void   delet(int id) throws ServletException {

        UserImp userS=new UserImp();
        UserModel users=new UserModel();
        users.setId(id);
        userS.deleteUser(users.getId());

    }

    public void addUser() {
        UserImp userS = new UserImp();
        UserModel newUser = user; // Récupérer les informations du nouvel utilisateur depuis le bean
        userS.setUser(newUser); // Ajouter le nouvel utilisateur dans la base de données
    }

    public void update() {
        UserImp userS = new UserImp();
        UserModel userToUpdate = user; // Récupérer l'utilisateur depuis le bean
        userS.updateUser(userToUpdate); // Mettre à jour l'utilisateur dans la base de données
    }




    public void setUser(UserModel user) {
        this.user = user;
    }
    public ResultSet getRes() {
        return res;
    }

    public void setRes(ResultSet res) {
        this.res = res;
    }



}
