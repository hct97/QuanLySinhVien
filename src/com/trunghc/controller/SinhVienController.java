package com.trunghc.controller;

import com.trunghc.connect.MySqlConnection;
import com.trunghc.model.SinhVien;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SinhVienController {
    public static ArrayList<SinhVien> GetAllSv(String maLop){
        ArrayList<SinhVien> ds = new ArrayList<SinhVien>();
        try{
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "select * from tblsinhvien where malop = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,maLop);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                SinhVien sv = new SinhVien();
                sv.setIdSv(resultSet.getInt(1));
                sv.setTenSv(resultSet.getString(2));
                sv.setNgaySinh(resultSet.getString(3));
                sv.setDiaChi(resultSet.getString(4));
                sv.setMaLop(maLop);
                ds.add(sv);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ds;
    }

    public static SinhVien GetInfoSv(int id){
        SinhVien sv = null;
        try{
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "select * from tblsinhvien where idsv = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                sv = new SinhVien();
                sv.setIdSv(resultSet.getInt(1));
                sv.setTenSv(resultSet.getString(2));
                sv.setNgaySinh(resultSet.getString(3));
                sv.setDiaChi(resultSet.getString(4));
                sv.setMaLop(resultSet.getString(5));
                return sv;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return sv;
    }

    public void AddSv(int id,String tenSv,String ngaySinh,String diaChi,String maLop){
        try{
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "INSERT INTO `tblsinhvien`(`idsv`, `tensv`, `ngaysinh`, `diachi`, `malop`) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.setString(2,tenSv);
            preparedStatement.setString(3,ngaySinh);
            preparedStatement.setString(4,diaChi);
            preparedStatement.setString(5,maLop);

            int x= preparedStatement.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Sucsses");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void EditSv(String tenSv,String ngaySinh,String diaChi,String maLop,int id){
        try{
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "UPDATE `tblsinhvien` SET `tensv`=?,`ngaysinh`=?,`diachi`=?,`malop`=? WHERE `idsv`=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1,tenSv);
            preparedStatement.setString(2,ngaySinh);
            preparedStatement.setString(3,diaChi);
            preparedStatement.setString(4,maLop);
            preparedStatement.setString(5, String.valueOf(id));

            int x= preparedStatement.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Sucsses");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public static void DeleteSv(int id){
        try{
            Connection connection = MySqlConnection.getMySQLConnection();
            String sql = "DELETE FROM `tblsinhvien` where idsv = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));
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
