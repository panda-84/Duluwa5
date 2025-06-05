/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Dao.GuideDa;
import Model.GuideA;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author acer
 */
public class adminDashboard extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard
     */
    
    Border default_border = BorderFactory.createMatteBorder(1,0,1,0,new Color(187,177,147));
    Border gray_border = BorderFactory.createMatteBorder(1,0,1,0,Color.gray);
    
    // create an array of jlabels
    JLabel[] menuLabels = new JLabel[5];
    
    // create an array of jlabels
    
    JPanel[] panels = new JPanel[4];
    public adminDashboard() {
        initComponents();
        
        
        
        // menuLabels array
        menuLabels[0] = jLabel_menuItem1;
        menuLabels[1] = jLabel_menuItem2;
        menuLabels[2] = jLabel_menuItem3;
        menuLabels[3] = jLabel_menuItem4;
        menuLabels[4] = jLabel_menuItem5;
        
        
        // panels array
        panels[0] = jPanel_home;
        panels[1] = jPanel_guide;
        panels[2] = jPanel_user;
        panels[3] = jPanel_payment;
        
        addActionToMenuLabels();
    }
    
    
    // function to set the label background color
    public void setLabelBackground(JLabel label){
        
        for (JLabel menuItem : menuLabels){
            menuItem.setBackground(new Color(187,177,147));
        }
        
        label.setBackground(Color.gray);
        //label.setForeground(new Color(7,8,8));
    }
    
    // selected panel
    
    public void showPanel(JPanel panel){
        for(JPanel pnl : panels){
            pnl.setVisible(false);
        }
        panel.setVisible(true);
    }
    
    
    public void addActionToMenuLabels(){
        Component[] components = jPanel_menu.getComponents();
        
        for(Component component : components){
            if(component instanceof JLabel){
                JLabel label = (JLabel) component;
                label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        setLabelBackground(label);
                        
                        switch (label.getText().trim()){
                            case "Home":
                                showPanel(jPanel_home);
                                break;
                            case "Add guide":
                                showPanel(jPanel_guide);
//                                jPanel_guide.setBackground(Color.red);
                                break;
                            case "User management":
                                showPanel(jPanel_user);
//                                jPanel_user.setBackground(Color.yellow);
                                break;
                            case "Payment status":
                                showPanel(jPanel_payment);
//                                jPanel_payment.setBackground(Color.green);
                                break;
                            case "Log Out":
                                int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure you want to log out?",
                                "Confirm Log Out",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (confirm == JOptionPane.YES_OPTION) {
                                
                                AdminLogin a = new AdminLogin();
                                a.setVisible(true);
                                a.pack();
                                a.setLocationRelativeTo(null);
                                a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                               
                                Component source = (Component) e.getSource();
                                Window window = SwingUtilities.getWindowAncestor(source);
                                if (window != null) {
                                    window.dispose();
                                }
                            }
                            break;              
                             
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        label.setBorder(gray_border);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        label.setBorder(default_border);
                    }
                });
            }
        }
        showPanel(jPanel_home);
    }
    
    String imagePath = null;
    
    //check input fields
    public boolean checkInputs(){
        if(        gFirstName.getText() == null
                || gMiddleName.getText() == null
                || gLastName.getText() == null
                || gGender.getText() == null
                || gPhoneNumber.getText() == null
                || gAge.getText() == null
                || gStatus.getText() == null
                || gBio.getText() == null
                ){
            return false;
        }
        else{
            try{
                Float.parseFloat(gPhoneNumber.getText());
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }
    
    
    
    // resize image
    public ImageIcon ResizeImage(String imagePath, byte[] pic){
        ImageIcon myImage = null;
        if(imagePath != null){
            myImage = new ImageIcon(imagePath);
        } else{
            myImage = new ImageIcon(pic);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(i_img.getWidth(),i_img.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_container = new javax.swing.JPanel();
        jPanel_menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel_head = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_menuItem1 = new javax.swing.JLabel();
        jLabel_menuItem2 = new javax.swing.JLabel();
        jLabel_menuItem3 = new javax.swing.JLabel();
        jLabel_menuItem4 = new javax.swing.JLabel();
        jLabel_menuItem5 = new javax.swing.JLabel();
        jPanel_home = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_text = new javax.swing.JLabel();
        jPanel_guide = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        gFirstName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        gMiddleName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        gLastName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        gGender = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        gPhoneNumber = new javax.swing.JTextField();
        guide_id = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        gAge = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        gStatus = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gBio = new javax.swing.JTextArea();
        imageButton = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        i_img = new javax.swing.JLabel();
        jPanel_user = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel_payment = new javax.swing.JPanel();
        jTextField10 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1460, 840));

        jPanel_container.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_container.setPreferredSize(new java.awt.Dimension(1460, 840));

        jPanel_menu.setBackground(new java.awt.Color(187, 177, 147));
        jPanel_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-admin-100.png"))); // NOI18N
        jPanel_menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 19, 177, 151));

        jPanel_head.setBackground(new java.awt.Color(187, 177, 147));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Welcome,");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Admin");

        javax.swing.GroupLayout jPanel_headLayout = new javax.swing.GroupLayout(jPanel_head);
        jPanel_head.setLayout(jPanel_headLayout);
        jPanel_headLayout.setHorizontalGroup(
            jPanel_headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_headLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26))
        );
        jPanel_headLayout.setVerticalGroup(
            jPanel_headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_headLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel_menu.add(jPanel_head, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 182, 214, -1));

        jLabel_menuItem1.setBackground(new java.awt.Color(187, 177, 147));
        jLabel_menuItem1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_menuItem1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-home-24-white.png"))); // NOI18N
        jLabel_menuItem1.setText("Home");
        jLabel_menuItem1.setOpaque(true);
        jPanel_menu.add(jLabel_menuItem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 372, 214, 40));

        jLabel_menuItem2.setBackground(new java.awt.Color(187, 177, 147));
        jLabel_menuItem2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_menuItem2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-plus-24-white.png"))); // NOI18N
        jLabel_menuItem2.setText("Add guide");
        jLabel_menuItem2.setOpaque(true);
        jPanel_menu.add(jLabel_menuItem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 424, 214, 40));

        jLabel_menuItem3.setBackground(new java.awt.Color(187, 177, 147));
        jLabel_menuItem3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_menuItem3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-plus-24-white.png"))); // NOI18N
        jLabel_menuItem3.setText("User management");
        jLabel_menuItem3.setOpaque(true);
        jPanel_menu.add(jLabel_menuItem3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 476, 214, 40));

        jLabel_menuItem4.setBackground(new java.awt.Color(187, 177, 147));
        jLabel_menuItem4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_menuItem4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-home-24-white.png"))); // NOI18N
        jLabel_menuItem4.setText("Payment status");
        jLabel_menuItem4.setOpaque(true);
        jPanel_menu.add(jLabel_menuItem4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 528, 214, 40));

        jLabel_menuItem5.setBackground(new java.awt.Color(187, 177, 147));
        jLabel_menuItem5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_menuItem5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/white_icons8-logout1-48.png"))); // NOI18N
        jLabel_menuItem5.setText("Log Out");
        jLabel_menuItem5.setOpaque(true);
        jPanel_menu.add(jLabel_menuItem5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 794, 214, 40));

        jPanel_home.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_home.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_home.setPreferredSize(new java.awt.Dimension(1195, 805));
        jPanel_home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(126, 168, 190));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );

        jPanel_home.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 88, -1, -1));

        jPanel2.setBackground(new java.awt.Color(40, 83, 107));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );

        jPanel_home.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 88, -1, -1));
        jPanel_home.add(jLabel_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 693, 1020, 53));

        jPanel_guide.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_guide.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_guide.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBackground(new java.awt.Color(217, 217, 217));
        jPanel_guide.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 21, 245, 34));

        jButton1.setBackground(new java.awt.Color(40, 83, 107));
        jPanel_guide.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 21, 48, 34));

        jButton2.setBackground(new java.awt.Color(40, 83, 107));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Filter");
        jPanel_guide.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 21, -1, 34));
        jPanel_guide.add(gFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 406, 211, 34));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("First Name:");
        jPanel_guide.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 405, -1, 34));
        jPanel_guide.add(gMiddleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 459, 211, 34));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Middle Name:");
        jPanel_guide.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 458, -1, 34));
        jPanel_guide.add(gLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 512, 211, 34));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Last Name:");
        jPanel_guide.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 511, -1, 34));
        jPanel_guide.add(gGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 565, 211, 34));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Gender:");
        jPanel_guide.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 564, -1, 34));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Phone #:");
        jPanel_guide.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 617, -1, 34));
        jPanel_guide.add(gPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 618, 211, 34));
        jPanel_guide.add(guide_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 712, 211, 34));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Guide ID:");
        jPanel_guide.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 711, -1, 34));
        jPanel_guide.add(gAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 406, 211, 34));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Age:");
        jPanel_guide.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 405, -1, 34));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Status:");
        jPanel_guide.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 458, -1, 34));

        gStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_guide.add(gStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 459, 212, 34));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Guide ID", "First Name", "Middle Name", "Last Name", "Gender", "Phone #", "Age", "Status", "Bio", "pic"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Byte.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(400);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(200);
        }

        jPanel_guide.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 73, 1152, 314));

        jButton3.setBackground(new java.awt.Color(40, 83, 107));
        jPanel_guide.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 711, 53, 34));

        jButton4.setBackground(new java.awt.Color(40, 83, 107));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Clear");
        jPanel_guide.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 711, 75, 34));

        jButton5.setBackground(new java.awt.Color(194, 148, 138));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Delete");
        jPanel_guide.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 711, -1, 34));

        jButton6.setBackground(new java.awt.Color(126, 168, 190));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Update");
        jPanel_guide.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 709, -1, 34));

        addButton.setBackground(new java.awt.Color(126, 168, 190));
        addButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel_guide.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1048, 711, 75, 34));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Bio:");
        jPanel_guide.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 511, -1, 34));

        gBio.setColumns(20);
        gBio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gBio.setRows(5);
        jScrollPane2.setViewportView(gBio);

        jPanel_guide.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 511, 212, 185));

        imageButton.setBackground(new java.awt.Color(40, 83, 107));
        imageButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        imageButton.setForeground(new java.awt.Color(255, 255, 255));
        imageButton.setText("Add photo");
        imageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageButtonActionPerformed(evt);
            }
        });
        jPanel_guide.add(imageButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(969, 645, -1, 34));

        jButton9.setBackground(new java.awt.Color(40, 83, 107));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Check review");
        jPanel_guide.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 750, -1, 34));

        i_img.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        i_img.setOpaque(true);
        jPanel_guide.add(i_img, new org.netbeans.lib.awtextra.AbsoluteConstraints(937, 511, 166, 116));

        jPanel_user.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("user");
        jPanel_user.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 430, 37, -1));

        jPanel_payment.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_payment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_payment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField10.setBackground(new java.awt.Color(217, 217, 217));
        jPanel_payment.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 18, 245, 34));

        jButton10.setBackground(new java.awt.Color(40, 83, 107));
        jPanel_payment.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 18, 48, 34));

        jButton11.setBackground(new java.awt.Color(40, 83, 107));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Filter");
        jPanel_payment.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 18, -1, 34));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Booking ID", "Guide ID", "First Name", "Middle Name", "Last Name", "Phone Number", "Email", "Start Date", "# of people", "Age", "Country", "Nationality", "Address", "Zip code", "payment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setShowHorizontalLines(true);
        jTable2.setShowVerticalLines(true);
        jScrollPane3.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(8).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(9).setPreferredWidth(150);
            jTable2.getColumnModel().getColumn(10).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(11).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(12).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(13).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(14).setPreferredWidth(200);
        }

        jPanel_payment.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 81, 1143, 298));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("First Name:");
        jPanel_payment.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 397, -1, 34));

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 398, 220, 34));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Middle Name:");
        jPanel_payment.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 450, -1, 34));

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 451, 220, 34));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Last Name:");
        jPanel_payment.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 503, -1, 34));

        jTextField13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 504, 220, 34));

        jTextField14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 557, 220, 34));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Phone Number:");
        jPanel_payment.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 556, -1, 34));

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 610, 220, 34));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Email:");
        jPanel_payment.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 609, -1, 34));

        jTextField16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 663, 220, 34));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Start Date:");
        jPanel_payment.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 662, -1, 34));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Booking ID:");
        jPanel_payment.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 720, -1, 34));

        jTextField17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 720, 220, 34));

        jButton12.setBackground(new java.awt.Color(40, 83, 107));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel_payment.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 720, 55, 34));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("# of people:");
        jPanel_payment.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 397, -1, 34));

        jTextField18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 398, 220, 34));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Age:");
        jPanel_payment.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 450, -1, 34));

        jTextField19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 451, 220, 34));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Country:");
        jPanel_payment.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 503, -1, 34));

        jTextField20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 504, 220, 34));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Nationality:");
        jPanel_payment.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 556, -1, 34));
        jPanel_payment.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 557, 220, 34));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Address:");
        jPanel_payment.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 609, -1, 34));

        jTextField22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 610, 220, 34));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Zip code:");
        jPanel_payment.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 662, -1, 34));

        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 663, 220, 34));

        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });
        jPanel_payment.add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 398, 220, 34));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Guide ID:");
        jPanel_payment.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(859, 397, -1, 34));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Payment:");
        jLabel31.setPreferredSize(new java.awt.Dimension(59, 34));
        jPanel_payment.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 450, -1, -1));

        jTextField25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField25.setPreferredSize(new java.awt.Dimension(64, 34));
        jPanel_payment.add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 451, 220, -1));
        jTextField25.getAccessibleContext().setAccessibleDescription("");

        jButton13.setBackground(new java.awt.Color(40, 83, 107));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Clear");
        jButton13.setPreferredSize(new java.awt.Dimension(72, 34));
        jPanel_payment.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 720, 81, -1));

        Delete.setBackground(new java.awt.Color(194, 148, 138));
        Delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setText("Delete");
        Delete.setPreferredSize(new java.awt.Dimension(72, 34));
        jPanel_payment.add(Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 720, 80, -1));

        jButton14.setBackground(new java.awt.Color(126, 168, 190));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Update");
        jButton14.setPreferredSize(new java.awt.Dimension(101, 34));
        jPanel_payment.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 720, -1, -1));

        jButton15.setBackground(new java.awt.Color(126, 168, 190));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Add");
        jButton15.setPreferredSize(new java.awt.Dimension(72, 34));
        jPanel_payment.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 710, -1, -1));

        javax.swing.GroupLayout jPanel_containerLayout = new javax.swing.GroupLayout(jPanel_container);
        jPanel_container.setLayout(jPanel_containerLayout);
        jPanel_containerLayout.setHorizontalGroup(
            jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_containerLayout.createSequentialGroup()
                .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1240, Short.MAX_VALUE))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addContainerGap(248, Short.MAX_VALUE)
                    .addComponent(jPanel_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addContainerGap(250, Short.MAX_VALUE)
                    .addComponent(jPanel_guide, javax.swing.GroupLayout.PREFERRED_SIZE, 1193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addContainerGap(249, Short.MAX_VALUE)
                    .addComponent(jPanel_user, javax.swing.GroupLayout.PREFERRED_SIZE, 1193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addContainerGap(248, Short.MAX_VALUE)
                    .addComponent(jPanel_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 1195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE)))
        );
        jPanel_containerLayout.setVerticalGroup(
            jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addContainerGap(18, Short.MAX_VALUE)
                    .addComponent(jPanel_home, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addContainerGap(28, Short.MAX_VALUE)
                    .addComponent(jPanel_guide, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addContainerGap(19, Short.MAX_VALUE)
                    .addComponent(jPanel_user, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addContainerGap(17, Short.MAX_VALUE)
                    .addComponent(jPanel_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void imageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageButtonActionPerformed
        JFileChooser file  = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path  = selectedFile.getAbsolutePath();
            i_img.setIcon(ResizeImage(path, null));
        }
        else{
            System.out.println("NO FILE SELECTED");
        }
        
        
    }//GEN-LAST:event_imageButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
       GuideDa dao = new GuideDa();

        String firstName = gFirstName.getText().trim();
        String middleName = gMiddleName.getText().trim();
        String lastName = gLastName.getText().trim();
        String gender = gGender.getText().trim();
        String phoneNumber = gPhoneNumber.getText().trim();
        String ageText = gAge.getText().trim();
        String status = gStatus.getText().trim();
        String bio = gBio.getText().trim();

        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        System.out.println("gender: " + gender);
        System.out.println("phone: " + phoneNumber);
        System.out.println("ageText: " + ageText);
        System.out.println("status: " + status);
        System.out.println("bio: " + bio);
        System.out.println("imagePath: " + imagePath);
        
        if (firstName.isEmpty() || lastName.isEmpty() || gender.isEmpty() || phoneNumber.isEmpty()
                || ageText.isEmpty() || status.isEmpty() || bio.isEmpty() || imagePath == null) {
            JOptionPane.showMessageDialog(null, "Please fill all the required fields and select an image.");
        } else if (!phoneNumber.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Invalid phone number. Enter 10 digits.");
        } else {
            try {
                int age = Integer.parseInt(ageText);

                byte[] imageBytes = java.nio.file.Files.readAllBytes(new File(imagePath).toPath());

                GuideA guide = new GuideA(firstName, middleName, lastName, gender, phoneNumber, age, status, bio, imageBytes);
                boolean success = dao.insertGuide(guide);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Guide added successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add guide.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid age format. Please enter a valid number.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }

        
    }//GEN-LAST:event_addButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField gAge;
    private javax.swing.JTextArea gBio;
    private javax.swing.JTextField gFirstName;
    private javax.swing.JTextField gGender;
    private javax.swing.JTextField gLastName;
    private javax.swing.JTextField gMiddleName;
    private javax.swing.JTextField gPhoneNumber;
    private javax.swing.JTextField gStatus;
    private javax.swing.JTextField guide_id;
    private javax.swing.JLabel i_img;
    private javax.swing.JButton imageButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_menuItem1;
    private javax.swing.JLabel jLabel_menuItem2;
    private javax.swing.JLabel jLabel_menuItem3;
    private javax.swing.JLabel jLabel_menuItem4;
    private javax.swing.JLabel jLabel_menuItem5;
    private javax.swing.JLabel jLabel_text;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_container;
    private javax.swing.JPanel jPanel_guide;
    private javax.swing.JPanel jPanel_head;
    private javax.swing.JPanel jPanel_home;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPanel jPanel_payment;
    private javax.swing.JPanel jPanel_user;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    // End of variables declaration//GEN-END:variables
}
