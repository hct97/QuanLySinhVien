package com.trunghc.controller;

import com.trunghc.connect.MySqlConnection;
import com.trunghc.model.Lop;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LopController {
    public static ArrayList<Lop> getAllClass(){
        ArrayList<Lop> ds = new ArrayList<Lop>();
        try{
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "select * from tbllop";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Lop l = new Lop();
                l.setMaLop(resultSet.getString(1));
                l.setTenLop(resultSet.getString(2));
                ds.add(l);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ds;
    }


    public void AddLop(String maLop,String tenLop){
        try{
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "INSERT INTO `tbllop`(`malop`, `tenlop`) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maLop);
            preparedStatement.setString(2,tenLop);

            int x= preparedStatement.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Sucsses");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void EditLop(String tenLop,String maLop){
        try{
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "UPDATE `tbllop` SET `tenlop`=? WHERE `malop`=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,tenLop);
            preparedStatement.setString(2,maLop);

            int x= preparedStatement.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Sucsses");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public static void DeleteLop(String maLop){
        try{
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "DELETE FROM `tbllop` where malop = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maLop);
            int x= preparedStatement.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Sucsses");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
