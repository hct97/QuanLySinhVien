package com.trunghc.view;

import com.trunghc.controller.LoginController;
import com.trunghc.model.Login;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {

    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin,btnExit;

    public LoginUI(String title){
        super(title);
        addControlls();
        addEvents();
    }

    private void addControlls() {
        //Layout đăng nhập
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel pnTitle = new JPanel();
        JLabel lblTittle = new JLabel("Đăng nhập hệ thống");
        lblTittle.setForeground(Color.blue);
        Font ft = new Font("segeo ui",Font.BOLD,20);
        lblTittle.setFont(ft);
        pnTitle.add(lblTittle);
        container.add(pnTitle, BorderLayout.NORTH);

        JPanel pnDangNhap = new JPanel();
        pnDangNhap.setLayout(new BoxLayout(pnDangNhap,BoxLayout.Y_AXIS));
        JPanel pnUser = new JPanel();
        JLabel lblUser = new JLabel("Username: ");
        txtUsername = new JTextField(20);
        txtUsername.setText("test");//tai khoan test
        pnUser.add(lblUser);
        pnUser.add(txtUsername);
        pnDangNhap.add(pnUser);

        JPanel pnPassword = new JPanel();
        JLabel lblPassword = new JLabel("Password: ");
        txtPassword = new JPasswordField(20);
        pnPassword.add(lblPassword);
        pnPassword.add(txtPassword);

        pnDangNhap.add(pnPassword);

        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout());
        btnLogin = new JButton("Login");
        btnExit = new JButton("Exit");
        pnButton.add(btnLogin);
        pnButton.add(btnExit);

        pnDangNhap.add(pnButton);

        TitledBorder borderDangNhap = new TitledBorder(BorderFactory.createLineBorder(Color.blue),"Login");
        pnDangNhap.setBorder(borderDangNhap);

        container.add(pnDangNhap,BorderLayout.CENTER);


    }

    private void addEvents() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void login() {
        //Gọi hàm kiểm tra người dùng
        Login nguoiDung = LoginController.Login(txtUsername.getText(), String.valueOf(txtPassword.getPassword()));
        if(nguoiDung == null){
            JOptionPane.showMessageDialog(null,"Đăng nhập thất bại"); //Người dùng nhập không chính xác
        }
        else {
            //Gọi hàm hiển thị Giao diện chính nếu đăng nhập thành công
            MainUI ui = new MainUI("Main");
            ui.showWindow();
            dispose();
        }
    }


    public void showWindows(){
        this.setSize(450,250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
