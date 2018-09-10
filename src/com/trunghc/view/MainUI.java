package com.trunghc.view;

import com.trunghc.controller.LopController;
import com.trunghc.controller.SinhVienController;
import com.trunghc.model.Login;
import com.trunghc.model.Lop;
import com.trunghc.model.SinhVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

public class MainUI extends JFrame {

    DefaultMutableTreeNode root;
    JTree treeClass;
    DefaultTableModel dtmSinhVien;
    JTable tblSinhVien;
    ArrayList<SinhVien> dsSinhVien = null;
    JTextField txtIdSv, txtNameSv,txtDateOfBirth,txtMaLop;
    JTextArea txtAddress;
    JButton btnAddSv,btnEditSv,btnDeleteSv;

    JMenuItem mnNew,mnEdit,mnDelete;
    JPopupMenu popup;
    ArrayList<Lop> dsLop;
    Lop selectedLop;
    LopController lopController;
    SinhVienController sinhVienController;

    public MainUI(String title){
        super(title);
        addControlls();
        addEvents();

        showAllClass();
    }

    private void showAllClass() {
        root.removeAllChildren();
        if(lopController == null)
            lopController=new LopController();
        dsLop = lopController.getAllClass();
        for(Lop lop:dsLop){
            DefaultMutableTreeNode nodeClass = new DefaultMutableTreeNode(lop);
            root.add(nodeClass);
        }
        treeClass.expandRow(0);
    }

    private void addEvents() {
        treeClass.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode) treeClass.getLastSelectedPathComponent();
                if(nodeSelected == null)
                    sinhVienController = new SinhVienController();
                selectedLop = (Lop) nodeSelected.getUserObject();
                if(selectedLop!=null)
                    dsSinhVien = sinhVienController.GetAllSv(selectedLop.getMaLop());
                showAllSv();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.isPopupTrigger()){
                    int row = treeClass.getClosestRowForLocation(e.getX(),e.getY());
                    treeClass.setSelectionRow(row);
                    popup.show(e.getComponent(),e.getX(),e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        tblSinhVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblSinhVien.getSelectedRow();
                if(row ==-1)
                    return;
                SinhVien sv = dsSinhVien.get(row);
                showInfoSv(sv);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        btnAddSv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSv();
            }
        });
        btnEditSv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditSv();
            }
        });
        btnDeleteSv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteSv();
            }
        });
        mnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddLop();
            }
        });
        mnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteLop();
            }
        });
        mnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditLop();
            }
        });
    }

    private void DeleteLop() {
        LopController l = new LopController();
        l.DeleteLop(selectedLop.getMaLop());
        showAllClass();
        treeClass.updateUI();
    }

    private void EditLop() {
        ClassUI c = new ClassUI("Sửa lớp");
        c.showWindow();
        c.showInfoClass(selectedLop.getMaLop(),selectedLop.getTenLop(),true);
        showAllClass();
        treeClass.updateUI();
    }

    private void AddLop() {
        ClassUI c = new ClassUI("Thêm mới lớp");
        c.showWindow();
        showAllClass();
        treeClass.updateUI();
    }

    private void DeleteSv() {
        SinhVienController sv = new SinhVienController();
        sv.DeleteSv(Integer.parseInt(txtIdSv.getText()));
        if(selectedLop!=null)
            dsSinhVien = sinhVienController.GetAllSv(selectedLop.getMaLop());
        showAllSv();
    }

    private void EditSv() {
        SinhVienController sv = new SinhVienController();
        sv.EditSv(txtNameSv.getText(),txtDateOfBirth.getText(),txtAddress.getText(),txtMaLop.getText(),Integer.parseInt(txtIdSv.getText()));
        if(selectedLop!=null)
            dsSinhVien = sinhVienController.GetAllSv(selectedLop.getMaLop());
        showAllSv();
    }

    private void AddSv() {
        SinhVienController sv = new SinhVienController();
        sv.AddSv(Integer.parseInt(txtIdSv.getText()),txtNameSv.getText(),txtDateOfBirth.getText(),txtAddress.getText(),txtMaLop.getText());
        showAllSv();
    }

    private void showInfoSv(SinhVien sv) {
        sv = SinhVienController.GetInfoSv(sv.getIdSv());
        txtIdSv.setText(String.valueOf(sv.getIdSv()));
        txtNameSv.setText(sv.getTenSv());
        txtDateOfBirth.setText(sv.getNgaySinh());
        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);
        txtAddress.setText(sv.getDiaChi());
        txtMaLop.setText(sv.getMaLop());
    }

    private void showAllSv() {
        dtmSinhVien.setRowCount(0);
        for(SinhVien sv:dsSinhVien){
            Vector<Object> vector = new Vector<Object>();
            vector.add(sv.getIdSv());
            vector.add(sv.getTenSv());
            vector.add(sv.getNgaySinh());
            vector.add(sv.getDiaChi());
            dtmSinhVien.addRow(vector);
        }
    }

    private void addControlls() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel pnTop = new JPanel();
        JPanel pnLeft = new JPanel();
        JPanel pnRight = new JPanel();
        pnLeft.setPreferredSize(new Dimension(300,0));
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pnLeft,pnRight);
        container.add(sp,BorderLayout.CENTER);
        container.add(pnTop,BorderLayout.NORTH);

        JPanel pnTitle = new JPanel();
        JLabel lblTitle = new JLabel("Quản lý sinh viên");
        Font ft =  new Font("segeo ui",Font.BOLD,20);
        lblTitle.setFont(ft);
        lblTitle.setForeground(Color.blue);
        pnTitle.add(lblTitle);
        pnTop.add(pnTitle);

        pnLeft.setLayout(new BorderLayout());
        root = new DefaultMutableTreeNode("Class");
        treeClass = new JTree(root);
        JScrollPane scTree = new JScrollPane(treeClass,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnLeft.add(scTree,BorderLayout.CENTER);

        pnRight.setLayout(new BorderLayout());
        JPanel pnTopOfRight = new JPanel();
        JPanel pnBottomOfRight = new JPanel();
        pnRight.add(pnTopOfRight,BorderLayout.CENTER);
        pnRight.add(pnBottomOfRight,BorderLayout.SOUTH);
        pnTopOfRight.setLayout(new BorderLayout());
        dtmSinhVien =new DefaultTableModel();
        dtmSinhVien.addColumn("Mã sinh viên");
        dtmSinhVien.addColumn("Tên sinh viên");
        dtmSinhVien.addColumn("Ngày sinh");
        dtmSinhVien.addColumn("Địa chỉ");
        tblSinhVien = new JTable(dtmSinhVien);

        JScrollPane scTbl = new JScrollPane(tblSinhVien,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnTopOfRight.add(scTbl,BorderLayout.CENTER);

        pnBottomOfRight.setLayout(new BorderLayout());

        JPanel pnInfoSV = new JPanel();
        pnInfoSV.setLayout(new BoxLayout(pnInfoSV,BoxLayout.Y_AXIS));

        JPanel pnIdSv = new JPanel();
        JLabel lblIdSv = new JLabel("Ma Sinh Vien: ");
        txtIdSv = new JTextField(20);
        pnIdSv.add(lblIdSv);
        pnIdSv.add(txtIdSv);
        pnInfoSV.add(pnIdSv);

        JPanel pnNameSv = new JPanel();
        JLabel lblNameSv = new JLabel("Ten Sinh Vien: ");
        txtNameSv = new JTextField(20);
        pnNameSv.add(lblNameSv);
        pnNameSv.add(txtNameSv);
        pnInfoSV.add(pnNameSv);

        JPanel pnDateOfBirth = new JPanel();
        JLabel lblDateOfBirth = new JLabel("Ngay Sinh :");
        txtDateOfBirth = new JTextField(20);
        pnDateOfBirth.add(lblDateOfBirth);
        pnDateOfBirth.add(txtDateOfBirth);
        pnInfoSV.add(pnDateOfBirth);

        JPanel pnMaLop = new JPanel();
        JLabel lblMaLop = new JLabel("Ma Lop :");
        txtMaLop = new JTextField(20);
        pnMaLop.add(lblMaLop);
        pnMaLop.add(txtMaLop);
        pnInfoSV.add(pnMaLop);

        JPanel pnAddress = new JPanel();
        JLabel lblAddress = new JLabel("Dia Chi: ");
        txtAddress = new JTextArea(2,20);
        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);
        pnAddress.add(lblAddress);
        pnAddress.add(txtAddress);
        pnInfoSV.add(pnAddress);

        lblIdSv.setPreferredSize(lblNameSv.getPreferredSize());
        lblMaLop.setPreferredSize(lblNameSv.getPreferredSize());
        lblDateOfBirth.setPreferredSize(lblNameSv.getPreferredSize());
        lblAddress.setPreferredSize(lblNameSv.getPreferredSize());

        pnBottomOfRight.add(pnInfoSV,BorderLayout.CENTER);

        JPanel pnButtonSv = new JPanel();
        btnAddSv = new JButton("ADD");
        btnEditSv = new JButton("EDIT");
        btnDeleteSv = new JButton("DELETE");
        pnButtonSv.add(btnAddSv);
        pnButtonSv.add(btnEditSv);
        pnButtonSv.add(btnDeleteSv);

        pnBottomOfRight.add(pnButtonSv,BorderLayout.SOUTH);
        setupMenu();

    }

    private void setupMenu() {
        mnNew = new JMenuItem("ADD CLASS");
        mnEdit = new JMenuItem("EDIT CLASS");
        mnDelete = new JMenuItem("DELETE CLASS!");
        popup = new JPopupMenu();
        popup.add(mnNew);
        popup.add(mnEdit);
        popup.add(mnDelete);
    }

    public void showWindow(){
        this.setSize(900,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
