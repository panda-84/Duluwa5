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
//import java.awt.List;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
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
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author acer
 */
public class adminDashboard extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard
     */
    private String imagePath = null;
    private GuideA currentGuide;
    
    Border default_border = BorderFactory.createMatteBorder(1,0,1,0,new Color(187,177,147));
    Border gray_border = BorderFactory.createMatteBorder(1,0,1,0,Color.gray);
    
    // create an array of jlabels
    JLabel[] menuLabels = new JLabel[5];
    
    // create an array of jlabels
    
    JPanel[] panels = new JPanel[4];
    public adminDashboard() {
        initComponents();
        
        loadGuideDataIntoTable(); 
        updateGuideCountLabel();
        
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
    
   
    
    //check input fields
    public boolean checkInputs(){
        if(        gFirstName.getText() == null
                || gMiddleName.getText() == null
                || gLastName.getText() == null
                || gGender.getSelectedItem() == null
                || gPhoneNumber.getText() == null
                || gAge.getText() == null
                || gStatus.getSelectedItem() == null
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
    
   private void loadGuideDataIntoTable() {
        GuideDa dao = new GuideDa();
        List<GuideA> guideList = dao.getAllGuides();

        DefaultTableModel model = (DefaultTableModel) guideTable.getModel();
        model.setRowCount(0); // Clear old rows

        for (GuideA guide : guideList) {
            Object[] row = new Object[]{
                guide.getGuideId(),
                guide.getFirstName(),
                guide.getMiddleName(),
                guide.getLastName(),
                guide.getGender(),
                guide.getNumber(),
                guide.getAge(),
                guide.getStatus(),
                guide.getBio()
                // Exclude image from table for performance
            };
            model.addRow(row);
        }
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
        jLabel4 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        adminTotalLabel = new javax.swing.JLabel();
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        gPhoneNumber = new javax.swing.JTextField();
        guide_id = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        gAge = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        guideTable = new javax.swing.JTable();
        fetchButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        gBio = new javax.swing.JTextArea();
        imageButton = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        i_img = new javax.swing.JLabel();
        gGender = new javax.swing.JComboBox<>();
        gStatus = new javax.swing.JComboBox<>();
        jPanel_user = new javax.swing.JPanel();
        jTextField26 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        uFirstName = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        uMiddleName = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        uLastName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton3 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
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
        jLabel_menuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-payment-24.png"))); // NOI18N
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
        jPanel1.setPreferredSize(new java.awt.Dimension(309, 253));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Users");

        jLabel33.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(61, 61, 61))
        );

        jPanel_home.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 88, -1, 250));

        jPanel2.setBackground(new java.awt.Color(40, 83, 107));
        jPanel2.setPreferredSize(new java.awt.Dimension(309, 253));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Admin");

        adminTotalLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        adminTotalLabel.setForeground(new java.awt.Color(255, 255, 255));
        adminTotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminTotalLabel.setText("00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(adminTotalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(adminTotalLabel)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(58, 58, 58))
        );

        jPanel_home.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 88, -1, -1));

        jPanel_guide.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_guide.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField1.setBackground(new java.awt.Color(217, 217, 217));

        jButton1.setBackground(new java.awt.Color(40, 83, 107));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N

        jButton2.setBackground(new java.awt.Color(40, 83, 107));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Filter");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("First Name:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Middle Name:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Last Name:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Gender:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Phone #:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Guide ID:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Age:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Status:");

        guideTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        guideTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Guide ID", "First Name", "Middle Name", "Last Name", "Gender", "Phone #", "Age", "Status", "Bio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        guideTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        guideTable.setShowHorizontalLines(true);
        guideTable.setShowVerticalLines(true);
        guideTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guideTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(guideTable);
        if (guideTable.getColumnModel().getColumnCount() > 0) {
            guideTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            guideTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            guideTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            guideTable.getColumnModel().getColumn(3).setPreferredWidth(200);
            guideTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            guideTable.getColumnModel().getColumn(5).setPreferredWidth(250);
            guideTable.getColumnModel().getColumn(6).setPreferredWidth(200);
            guideTable.getColumnModel().getColumn(7).setPreferredWidth(200);
            guideTable.getColumnModel().getColumn(8).setPreferredWidth(400);
        }

        fetchButton.setBackground(new java.awt.Color(40, 83, 107));
        fetchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N
        fetchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fetchButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(40, 83, 107));
        clearButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(194, 148, 138));
        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(126, 168, 190));
        updateButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(126, 168, 190));
        addButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Bio:");

        gBio.setColumns(20);
        gBio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gBio.setLineWrap(true);
        gBio.setRows(5);
        gBio.setWrapStyleWord(true);
        gBio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        imageButton.setBackground(new java.awt.Color(40, 83, 107));
        imageButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        imageButton.setForeground(new java.awt.Color(255, 255, 255));
        imageButton.setText("Add photo");
        imageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageButtonActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(40, 83, 107));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Check review");

        i_img.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        i_img.setOpaque(true);

        gGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "male", "female" }));
        gGender.setPreferredSize(new java.awt.Dimension(72, 34));

        gStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "not Available" }));
        gStatus.setPreferredSize(new java.awt.Dimension(72, 34));

        javax.swing.GroupLayout jPanel_guideLayout = new javax.swing.GroupLayout(jPanel_guide);
        jPanel_guide.setLayout(jPanel_guideLayout);
        jPanel_guideLayout.setHorizontalGroup(
            jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_guideLayout.createSequentialGroup()
                .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton2))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel5)
                        .addGap(21, 21, 21)
                        .addComponent(gFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)
                        .addComponent(jLabel13)
                        .addGap(27, 27, 27)
                        .addComponent(gAge, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel12)
                        .addGap(22, 22, 22)
                        .addComponent(guide_id, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fetchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(deleteButton)
                        .addGap(36, 36, 36)
                        .addComponent(updateButton)
                        .addGap(221, 221, 221)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(519, 519, 519)
                        .addComponent(jButton9))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_guideLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel11)))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(gLastName)
                                    .addComponent(gPhoneNumber)
                                    .addComponent(gGender, 0, 211, Short.MAX_VALUE))
                                .addGap(149, 149, 149)
                                .addComponent(jLabel15))
                            .addGroup(jPanel_guideLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel8)
                                .addGap(21, 21, 21)
                                .addComponent(gMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129)
                                .addComponent(jLabel14)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel_guideLayout.createSequentialGroup()
                                .addComponent(gBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(i_img, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(imageButton))))
                            .addGroup(jPanel_guideLayout.createSequentialGroup()
                                .addComponent(gStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(297, 297, 297)))))
                .addGap(22, 22, 22))
        );
        jPanel_guideLayout.setVerticalGroup(
            jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_guideLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gAge, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(gStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(gMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(gLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(gPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addComponent(i_img, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(imageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gBio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel_guideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(guide_id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(fetchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_guideLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel_user.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_user.setPreferredSize(new java.awt.Dimension(1174, 754));

        jTextField26.setBackground(new java.awt.Color(217, 217, 217));
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(40, 83, 107));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N

        jButton17.setBackground(new java.awt.Color(40, 83, 107));
        jButton17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Filter");

        uFirstName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("First Name:");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Middle Name:");

        uMiddleName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("Last Name:");

        uLastName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 412));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "User ID", "First Name", "Middle Name", "Last Name", "Gender", "Nationality", "Age", "Country", "Bio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(300);
        }

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("Gender:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Nationality:");

        jTextField31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        jTextField32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("User ID:");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("Age:");

        jTextField34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("Country:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Bio:");
        jLabel6.setPreferredSize(new java.awt.Dimension(38, 34));

        jScrollPane4.setViewportView(jTextPane1);

        jButton3.setBackground(new java.awt.Color(40, 83, 107));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add photo");
        jButton3.setPreferredSize(new java.awt.Dimension(76, 34));

        jLabel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton4.setBackground(new java.awt.Color(40, 83, 107));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(76, 34));

        jButton5.setBackground(new java.awt.Color(40, 83, 107));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Clear");
        jButton5.setPreferredSize(new java.awt.Dimension(76, 34));

        jButton6.setBackground(new java.awt.Color(194, 148, 138));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Delete");
        jButton6.setPreferredSize(new java.awt.Dimension(76, 34));

        jButton7.setBackground(new java.awt.Color(126, 168, 190));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Update");
        jButton7.setPreferredSize(new java.awt.Dimension(82, 34));

        jButton8.setBackground(new java.awt.Color(126, 168, 190));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Add");
        jButton8.setPreferredSize(new java.awt.Dimension(76, 34));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "male", "female" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(72, 34));

        jSpinner1.setPreferredSize(new java.awt.Dimension(64, 34));

        javax.swing.GroupLayout jPanel_userLayout = new javax.swing.GroupLayout(jPanel_user);
        jPanel_user.setLayout(jPanel_userLayout);
        jPanel_userLayout.setHorizontalGroup(
            jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_userLayout.createSequentialGroup()
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel_userLayout.createSequentialGroup()
                            .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel_userLayout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(jLabel35)
                                    .addGap(27, 27, 27)
                                    .addComponent(uFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel_userLayout.createSequentialGroup()
                                    .addGap(44, 44, 44)
                                    .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel37)
                                        .addComponent(jLabel36))
                                    .addGap(27, 27, 27)
                                    .addComponent(uMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(124, 124, 124)
                            .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel41)
                                .addComponent(jLabel42)))
                        .addGroup(jPanel_userLayout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_userLayout.createSequentialGroup()
                                    .addComponent(jLabel39)
                                    .addGap(29, 29, 29))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_userLayout.createSequentialGroup()
                                    .addComponent(jLabel38)
                                    .addGap(28, 28, 28)))
                            .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel_userLayout.createSequentialGroup()
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(2, 2, 2)))))
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField34, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_userLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_userLayout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
            .addGroup(jPanel_userLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton17))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(jPanel_userLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel40)
                .addGap(27, 27, 27)
                .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        jPanel_userLayout.setVerticalGroup(
            jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_userLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_userLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_userLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(uFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_userLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(uMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_userLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(uLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_userLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(81, 81, 81)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel_payment.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_payment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_payment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField10.setBackground(new java.awt.Color(217, 217, 217));
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jPanel_payment.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 18, 245, 34));

        jButton10.setBackground(new java.awt.Color(40, 83, 107));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N
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
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N
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
                    .addComponent(jPanel_guide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jPanel_guide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            imagePath = path;
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
        String gender = (String) gGender.getSelectedItem();
        String phoneNumber = gPhoneNumber.getText().trim();
        String ageText = gAge.getText().trim();
        String status = (String) gStatus.getSelectedItem();
        String bio = gBio.getText().trim();

//        System.out.println("firstName: " + firstName);
//        System.out.println("lastName: " + lastName);
//        System.out.println("gender: " + gender);
//        System.out.println("phone: " + phoneNumber);
//        System.out.println("ageText: " + ageText);
//        System.out.println("status: " + status);
//        System.out.println("bio: " + bio);
//        System.out.println("imagePath: " + imagePath);
        
        if (firstName.trim().isEmpty() || lastName.trim().isEmpty() || gender.trim().isEmpty() || phoneNumber.trim().isEmpty()
                || ageText.trim().isEmpty() || status.trim().isEmpty() || bio.trim().isEmpty() || imagePath == null) {
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
                    loadGuideDataIntoTable(); 
                    int rowCount = guideTable.getRowCount();
                    if (rowCount > 0) {
                        guideTable.setRowSelectionInterval(rowCount - 1, rowCount - 1);
                        guideTable.scrollRectToVisible(guideTable.getCellRect(rowCount - 1, 0, true));
                    }
                    clearFields(); 
                    updateGuideCountLabel(); 
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

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        
        String firstName = gFirstName.getText().trim();
        String middleName = gMiddleName.getText().trim();
        String lastName = gLastName.getText().trim();
        String gender = (String) gGender.getSelectedItem();
        String phoneNumber = gPhoneNumber.getText().trim();
        String ageText = gAge.getText().trim();
        String status = (String) gStatus.getSelectedItem();
        String bio = gBio.getText().trim();

        if (checkInputs() && guide_id.getText() != null && !guide_id.getText().trim().isEmpty()) {
            try {
                int age = Integer.parseInt(ageText);
                int id = Integer.parseInt(guide_id.getText().trim());
                byte[] imageBytes;

                if (imagePath != null) {
                    imageBytes = java.nio.file.Files.readAllBytes(new File(imagePath).toPath());
                } else if (currentGuide != null) {
                    imageBytes = currentGuide.getPicture(); // Reuse existing image
                } else {
                    JOptionPane.showMessageDialog(null, "No image selected or found. Please select one.");
                    return;
                }

                
                GuideA guide = new GuideA(firstName, middleName, lastName, gender, phoneNumber, age, status, bio, imageBytes);

                
                guide.setGuideId(id);

                
                GuideDa dao = new GuideDa();
                boolean success = dao.updateGuide(guide);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Guide updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update guide.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }


    }//GEN-LAST:event_updateButtonActionPerformed

    private void fetchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fetchButtonActionPerformed
        
        String idText = guide_id.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a guide ID.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            GuideDa dao = new GuideDa();
            GuideA guide = dao.getGuideById(id);

            if (guide != null) {
                currentGuide = guide;
                // Fill the form fields
                gFirstName.setText(guide.getFirstName());
                gMiddleName.setText(guide.getMiddleName());
                gLastName.setText(guide.getLastName());
                gGender.setSelectedItem(guide.getGender());
                gPhoneNumber.setText(guide.getNumber());
                gAge.setText(String.valueOf(guide.getAge()));
                gStatus.setSelectedItem(guide.getStatus());
                gBio.setText(guide.getBio());

                
                ImageIcon imageIcon = ResizeImage(null, guide.getPicture());
                i_img.setIcon(imageIcon);

                
                imagePath = null;

            } else {
                JOptionPane.showMessageDialog(null, "No guide found with this ID.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid number.");
        }
    }//GEN-LAST:event_fetchButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        String idText = guide_id.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Guide ID to delete.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);

            // Confirm with user
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete guide ID " + id + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                GuideDa dao = new GuideDa();
                boolean success = dao.deleteGuideById(id);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Guide deleted successfully.");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "No guide found with ID: " + id);
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Guide ID format.");
        }
        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        
        gFirstName.setText("");
        gMiddleName.setText("");
        gLastName.setText("");
        gPhoneNumber.setText("");
        gAge.setText("");
        gBio.setText("");

       
        gGender.setSelectedIndex(0); 
        gStatus.setSelectedIndex(0); 

        
        i_img.setIcon(null);

        imagePath = null;
    }//GEN-LAST:event_clearButtonActionPerformed

    private void guideTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guideTableMouseClicked
        // TODO add your handling code here:
        
            int rowIndex = guideTable.getSelectedRow();
            if (rowIndex >= 0) {
                loadRowDataToFields(rowIndex);
            }
       
    }//GEN-LAST:event_guideTableMouseClicked

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed
    
    
    private void loadRowDataToFields(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) guideTable.getModel();

        int id = Integer.parseInt(model.getValueAt(rowIndex, 0).toString());

        
        guide_id.setText(String.valueOf(id));
        gFirstName.setText(model.getValueAt(rowIndex, 1).toString());
        gMiddleName.setText(model.getValueAt(rowIndex, 2).toString());
        gLastName.setText(model.getValueAt(rowIndex, 3).toString());
        gGender.setSelectedItem(model.getValueAt(rowIndex, 4).toString());
        gPhoneNumber.setText(model.getValueAt(rowIndex, 5).toString());
        gAge.setText(model.getValueAt(rowIndex, 6).toString());
        gStatus.setSelectedItem(model.getValueAt(rowIndex, 7).toString());
        gBio.setText(model.getValueAt(rowIndex, 8).toString());

        
        GuideDa dao = new GuideDa();
        currentGuide = dao.getGuideById(id);

        if (currentGuide != null && currentGuide.getPicture() != null) {
            ImageIcon imageIcon = ResizeImage(null, currentGuide.getPicture());
            i_img.setIcon(imageIcon);
        } else {
            i_img.setIcon(null); 
        }

        imagePath = null; 
    }


    private void clearFields(){
        guide_id.setText("");
        gFirstName.setText("");
        gMiddleName.setText("");
        gLastName.setText("");
        gPhoneNumber.setText("");
        gAge.setText("");
        gBio.setText("");
        gGender.setSelectedIndex(0);
        gStatus.setSelectedIndex(0);
        i_img.setIcon(null);
        imagePath = null;
    }
    
    
    private void updateGuideCountLabel() {
    GuideDa dao = new GuideDa();
    int count = dao.getGuideCount();
    adminTotalLabel.setText(String.valueOf(count));
}

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
    private javax.swing.JLabel adminTotalLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton fetchButton;
    private javax.swing.JTextField gAge;
    private javax.swing.JTextArea gBio;
    private javax.swing.JTextField gFirstName;
    private javax.swing.JComboBox<String> gGender;
    private javax.swing.JTextField gLastName;
    private javax.swing.JTextField gMiddleName;
    private javax.swing.JTextField gPhoneNumber;
    private javax.swing.JComboBox<String> gStatus;
    private javax.swing.JTable guideTable;
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
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinner1;
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
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField uFirstName;
    private javax.swing.JTextField uLastName;
    private javax.swing.JTextField uMiddleName;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
