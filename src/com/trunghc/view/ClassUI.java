package com.trunghc.view;

import com.trunghc.controller.LopController;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassUI extends JFrame {

    JTextField txtMaLop,txtTenLop;
    JButton btnSave,btnExit;
    Boolean edit = false;

    public ClassUI(String title){
        super(title);
        addControlls();
        addEvents();
    }

    private void addControlls() {

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel pnTitle = new JPanel();
        JLabel lblTittle = new JLabel("Thông tin lớp");
        lblTittle.setForeground(Color.blue);
        Font ft = new Font("segeo ui",Font.BOLD,20);
        lblTittle.setFont(ft);
        pnTitle.add(lblTittle);
        container.add(pnTitle, BorderLayout.NORTH);

        JPanel pnClass = new JPanel();
        pnClass.setLayout(new BoxLayout(pnClass,BoxLayout.Y_AXIS));
        JPanel pnMa = new JPanel();
        JLabel lblMa = new JLabel("Mã Lớp: ");
        txtMaLop = new JTextField(20);
        pnMa.add(lblMa);
        pnMa.add(txtMaLop);
        pnClass.add(pnMa);

        JPanel pnTen = new JPanel();
        JLabel lblTen = new JLabel("Tên Lớp: ");
        txtTenLop = new JTextField(20);
        pnTen.add(lblTen);
        pnTen.add(txtTenLop);

        pnClass.add(pnTen);

        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout());
        btnSave = new JButton("Save");
        btnExit = new JButton("Exit");
        pnButton.add(btnSave);
        pnButton.add(btnExit);

        pnClass.add(pnButton);

        TitledBorder borderClass = new TitledBorder(BorderFactory.createLineBorder(Color.blue),"Thong tin lop");
        pnClass.setBorder(borderClass);

        container.add(pnClass,BorderLayout.CENTER);
    }

    private void addEvents() {
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(edit)
                    xyLySuaLop();
                else {
                    xyLyThemLop();
                    System.out.print("Xoa");
                }

            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void xyLyThemLop() {
        LopController l = new LopController();
        l.AddLop(txtMaLop.getText(),txtTenLop.getText());
    }

    private void xyLySuaLop() {
        LopController l = new LopController();
        l.EditLop(txtTenLop.getText(),txtMaLop.getText());

    }

    protected void showInfoClass(String maLop,String tenLop,Boolean edit){
        this.edit = edit;
        txtMaLop.setText(maLop);
        txtTenLop.setText(tenLop);
    }

    public void showWindow(){
        this.setSize(450,250);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
