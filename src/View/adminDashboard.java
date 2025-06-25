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
import Controller.BookingController;
import Controller.FeedbackController;
import Controller.ProfileController;
import Controller.adminBookingController;
import Dao.AdminBookingDa;
import Dao.UserDa;
import Model.BookingT;
import Model.ProfileModel;
import View.AdminLogin;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;

/**
 *
 * @author acer
 */
public class adminDashboard extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard
     */
    private String imagePath = null;
    private String picturePath = null;
    private GuideA currentGuide;
    private ProfileModel currentProfile;
    private BookingT currentBook;
   
    
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
        updateUserCountLabel();
        
        // booking update on JTable
        adminBookingController controller = new adminBookingController();

        controller.loadPaymentDataIntoTable(paymentTable);
        
        ProfileController profileController = new ProfileController();
        profileController.loadAllUserProfilesIntoTable(this);
        
        FeedbackController rate = new FeedbackController();
        rate.loadRatingPercentagesIntoTable(rateTable);
        

        
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
    
   
    
    //check guide input fields
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
    
    
    
    // resize guide image
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
    
    // for user profile
    public ImageIcon ResizePicture(String picturePath, byte[] pic){
        ImageIcon myImage = null;
        if(picturePath != null){
            myImage = new ImageIcon(picturePath);
        } else{
            myImage = new ImageIcon(pic);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(u_img.getWidth(),u_img.getHeight(), Image.SCALE_SMOOTH);
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
        userTotalLabel = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        adminTotalLabel = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        rateTable = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        jPanel_guide = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
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
        uFirstName = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        uMiddleName = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        uLastName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        uNationality = new javax.swing.JTextField();
        uProfile_id = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        uCountry = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        uImageButton = new javax.swing.JButton();
        u_img = new javax.swing.JLabel();
        uSearch = new javax.swing.JButton();
        uClear = new javax.swing.JButton();
        uDelete = new javax.swing.JButton();
        uUpdate = new javax.swing.JButton();
        uGender = new javax.swing.JComboBox<>();
        uAge = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        uBio = new javax.swing.JTextArea();
        jPanel_payment = new javax.swing.JPanel();
        jTextField10 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        bFirstName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        bMiddleName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        bLastName = new javax.swing.JTextField();
        bNumber = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        bEmail = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        bBookingId = new javax.swing.JTextField();
        searchBooking = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        bNumberOfPeople = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        bAge = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        bCountry = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        bNationality = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        bAddress = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        bZipCode = new javax.swing.JTextField();
        bGuide_ID = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        clearBooking = new javax.swing.JButton();
        DeleteBooking = new javax.swing.JButton();
        updateBooking = new javax.swing.JButton();
        addPayment = new javax.swing.JButton();
        bStartDate = new com.toedter.calendar.JDateChooser();
        bPayment = new javax.swing.JComboBox<>();
        bEndDate = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();

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

        userTotalLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        userTotalLabel.setForeground(new java.awt.Color(255, 255, 255));
        userTotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userTotalLabel.setText("00");

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-user-64.png"))); // NOI18N

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-plus-24.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(userTotalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userTotalLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel_home.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 88, -1, 250));

        jPanel2.setBackground(new java.awt.Color(40, 83, 107));
        jPanel2.setPreferredSize(new java.awt.Dimension(309, 253));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Guide");

        adminTotalLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        adminTotalLabel.setForeground(new java.awt.Color(255, 255, 255));
        adminTotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminTotalLabel.setText("00");

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-signpost-50.png"))); // NOI18N

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-plus-24.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(adminTotalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adminTotalLabel)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel_home.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 88, -1, -1));

        jLabel32.setText("An admin manages guide data by adding, updating, or removing guide profiles and verifying their details. They handle user data by monitoring activity, editing profiles, and ensuring security.");
        jPanel_home.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 700, 1030, -1));

        jLabel33.setText("Payment data is managed by tracking transactions, verifying successful payments, resolving disputes, and generating reports for transparency and financial accuracy.");
        jPanel_home.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 720, 910, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("These are the numbers of users and admins who have access to our application.");
        jPanel_home.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 700, -1));

        rateTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Guide ID", "Rating rate"
            }
        ));
        rateTable.setShowHorizontalLines(true);
        rateTable.setShowVerticalLines(true);
        jScrollPane4.setViewportView(rateTable);

        jPanel_home.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 400, 230));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("Guide rating percentage");
        jPanel_home.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 580, -1));

        jPanel_guide.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_guide.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField1.setBackground(new java.awt.Color(217, 217, 217));

        jButton1.setBackground(new java.awt.Color(40, 83, 107));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N

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
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        userTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "First Name", "Middle Name", "Last Name", "Age", "Nationality", "Bio", "Country", "Gender"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        userTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        userTable.setShowHorizontalLines(true);
        userTable.setShowVerticalLines(true);
        jScrollPane2.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setPreferredWidth(200);
            userTable.getColumnModel().getColumn(1).setPreferredWidth(250);
            userTable.getColumnModel().getColumn(2).setPreferredWidth(250);
            userTable.getColumnModel().getColumn(3).setPreferredWidth(250);
            userTable.getColumnModel().getColumn(4).setPreferredWidth(200);
            userTable.getColumnModel().getColumn(5).setPreferredWidth(250);
            userTable.getColumnModel().getColumn(6).setPreferredWidth(300);
            userTable.getColumnModel().getColumn(7).setPreferredWidth(250);
            userTable.getColumnModel().getColumn(8).setPreferredWidth(200);
        }

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("Gender:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Nationality:");

        uNationality.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        uNationality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uNationalityActionPerformed(evt);
            }
        });

        uProfile_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("User ID:");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("Age:");

        uCountry.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("Country:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Bio:");
        jLabel6.setPreferredSize(new java.awt.Dimension(38, 34));

        uImageButton.setBackground(new java.awt.Color(40, 83, 107));
        uImageButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        uImageButton.setForeground(new java.awt.Color(255, 255, 255));
        uImageButton.setText("Add photo");
        uImageButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uImageButton.setPreferredSize(new java.awt.Dimension(76, 34));
        uImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uImageButtonActionPerformed(evt);
            }
        });

        u_img.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u_img.setOpaque(true);

        uSearch.setBackground(new java.awt.Color(40, 83, 107));
        uSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        uSearch.setForeground(new java.awt.Color(255, 255, 255));
        uSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N
        uSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uSearch.setPreferredSize(new java.awt.Dimension(76, 34));
        uSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uSearchActionPerformed(evt);
            }
        });

        uClear.setBackground(new java.awt.Color(40, 83, 107));
        uClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        uClear.setForeground(new java.awt.Color(255, 255, 255));
        uClear.setText("Clear");
        uClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uClear.setPreferredSize(new java.awt.Dimension(76, 34));
        uClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uClearActionPerformed(evt);
            }
        });

        uDelete.setBackground(new java.awt.Color(194, 148, 138));
        uDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        uDelete.setForeground(new java.awt.Color(255, 255, 255));
        uDelete.setText("Delete");
        uDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uDelete.setPreferredSize(new java.awt.Dimension(76, 34));
        uDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uDeleteActionPerformed(evt);
            }
        });

        uUpdate.setBackground(new java.awt.Color(126, 168, 190));
        uUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        uUpdate.setForeground(new java.awt.Color(255, 255, 255));
        uUpdate.setText("Update");
        uUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uUpdate.setPreferredSize(new java.awt.Dimension(82, 34));
        uUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uUpdateActionPerformed(evt);
            }
        });

        uGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "male", "female" }));
        uGender.setPreferredSize(new java.awt.Dimension(72, 34));

        uAge.setPreferredSize(new java.awt.Dimension(73, 34));

        uBio.setColumns(20);
        uBio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        uBio.setLineWrap(true);
        uBio.setRows(5);
        uBio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane5.setViewportView(uBio);

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
                                .addComponent(uNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel_userLayout.createSequentialGroup()
                                    .addComponent(uGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(2, 2, 2)))))
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(uCountry)
                    .addComponent(uAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_userLayout.createSequentialGroup()
                        .addComponent(uImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_userLayout.createSequentialGroup()
                        .addComponent(u_img, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
            .addGroup(jPanel_userLayout.createSequentialGroup()
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_userLayout.createSequentialGroup()
                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel40)
                        .addGap(27, 27, 27)
                        .addComponent(uProfile_id, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(uClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(uDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(uUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel_userLayout.setVerticalGroup(
            jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_userLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addComponent(u_img, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(uImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(uGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_userLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(uCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_userLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uProfile_id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(uClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(uDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(uUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_payment.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 18, 48, 34));

        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Guide ID", "First Name", "Middle Name", "Last Name", "Phone Number", "Email", "Start Date", "# of people", "Age", "Country", "Nationality", "Address", "Zip code", "payment", "End Date", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        paymentTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        paymentTable.setShowHorizontalLines(true);
        paymentTable.setShowVerticalLines(true);
        jScrollPane3.setViewportView(paymentTable);
        if (paymentTable.getColumnModel().getColumnCount() > 0) {
            paymentTable.getColumnModel().getColumn(0).setPreferredWidth(200);
            paymentTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            paymentTable.getColumnModel().getColumn(2).setPreferredWidth(250);
            paymentTable.getColumnModel().getColumn(3).setPreferredWidth(250);
            paymentTable.getColumnModel().getColumn(4).setPreferredWidth(250);
            paymentTable.getColumnModel().getColumn(5).setPreferredWidth(250);
            paymentTable.getColumnModel().getColumn(6).setPreferredWidth(250);
            paymentTable.getColumnModel().getColumn(7).setPreferredWidth(250);
            paymentTable.getColumnModel().getColumn(8).setPreferredWidth(200);
            paymentTable.getColumnModel().getColumn(9).setPreferredWidth(150);
            paymentTable.getColumnModel().getColumn(10).setPreferredWidth(250);
            paymentTable.getColumnModel().getColumn(11).setPreferredWidth(200);
            paymentTable.getColumnModel().getColumn(12).setPreferredWidth(250);
            paymentTable.getColumnModel().getColumn(13).setPreferredWidth(200);
            paymentTable.getColumnModel().getColumn(14).setPreferredWidth(200);
            paymentTable.getColumnModel().getColumn(15).setPreferredWidth(250);
            paymentTable.getColumnModel().getColumn(16).setPreferredWidth(200);
        }

        jPanel_payment.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 81, 1143, 298));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("First Name:");
        jPanel_payment.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 397, -1, 34));

        bFirstName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 398, 220, 34));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Middle Name:");
        jPanel_payment.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 450, -1, 34));

        bMiddleName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bMiddleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 451, 220, 34));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Last Name:");
        jPanel_payment.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 503, -1, 34));

        bLastName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 504, 220, 34));

        bNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 557, 220, 34));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Phone Number:");
        jPanel_payment.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 556, -1, 34));

        bEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 610, 220, 34));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Email:");
        jPanel_payment.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 609, -1, 34));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Start Date:");
        jPanel_payment.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 500, -1, 34));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Booking ID:");
        jPanel_payment.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 720, -1, 34));

        bBookingId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bBookingId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 720, 220, 34));

        searchBooking.setBackground(new java.awt.Color(40, 83, 107));
        searchBooking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N
        searchBooking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBookingActionPerformed(evt);
            }
        });
        jPanel_payment.add(searchBooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 720, 55, 34));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("# of people:");
        jPanel_payment.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 397, -1, 34));

        bNumberOfPeople.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bNumberOfPeople.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNumberOfPeopleActionPerformed(evt);
            }
        });
        jPanel_payment.add(bNumberOfPeople, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 398, 220, 34));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Age:");
        jPanel_payment.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 450, -1, 34));

        bAge.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 451, 220, 34));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Country:");
        jPanel_payment.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 503, -1, 34));

        bCountry.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 504, 220, 34));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Nationality:");
        jPanel_payment.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 556, -1, 34));
        jPanel_payment.add(bNationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 557, 220, 34));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Address:");
        jPanel_payment.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 609, -1, 34));

        bAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 610, 220, 34));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Zip code:");
        jPanel_payment.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 662, -1, 34));

        bZipCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_payment.add(bZipCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 663, 220, 34));

        bGuide_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuide_IDActionPerformed(evt);
            }
        });
        jPanel_payment.add(bGuide_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 398, 220, 34));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Guide ID:");
        jPanel_payment.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(859, 397, -1, 34));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Payment:");
        jLabel31.setPreferredSize(new java.awt.Dimension(59, 34));
        jPanel_payment.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 450, -1, -1));

        clearBooking.setBackground(new java.awt.Color(40, 83, 107));
        clearBooking.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearBooking.setForeground(new java.awt.Color(255, 255, 255));
        clearBooking.setText("Clear");
        clearBooking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearBooking.setPreferredSize(new java.awt.Dimension(72, 34));
        clearBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBookingActionPerformed(evt);
            }
        });
        jPanel_payment.add(clearBooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 720, 81, -1));

        DeleteBooking.setBackground(new java.awt.Color(194, 148, 138));
        DeleteBooking.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DeleteBooking.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBooking.setText("Delete");
        DeleteBooking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteBooking.setPreferredSize(new java.awt.Dimension(72, 34));
        DeleteBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBookingActionPerformed(evt);
            }
        });
        jPanel_payment.add(DeleteBooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 720, 80, -1));

        updateBooking.setBackground(new java.awt.Color(126, 168, 190));
        updateBooking.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updateBooking.setForeground(new java.awt.Color(255, 255, 255));
        updateBooking.setText("Update");
        updateBooking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateBooking.setPreferredSize(new java.awt.Dimension(101, 34));
        updateBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingActionPerformed(evt);
            }
        });
        jPanel_payment.add(updateBooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 720, -1, -1));

        addPayment.setBackground(new java.awt.Color(126, 168, 190));
        addPayment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addPayment.setForeground(new java.awt.Color(255, 255, 255));
        addPayment.setText("Add");
        addPayment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPayment.setPreferredSize(new java.awt.Dimension(72, 34));
        addPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPaymentActionPerformed(evt);
            }
        });
        jPanel_payment.add(addPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 710, -1, -1));

        bStartDate.setPreferredSize(new java.awt.Dimension(88, 34));
        jPanel_payment.add(bStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 500, 130, -1));

        bPayment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Esewa" }));
        bPayment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bPayment.setPreferredSize(new java.awt.Dimension(72, 34));
        jPanel_payment.add(bPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 450, 130, -1));

        bEndDate.setPreferredSize(new java.awt.Dimension(88, 34));
        jPanel_payment.add(bEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 550, 130, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("End Date:");
        jLabel30.setPreferredSize(new java.awt.Dimension(44, 34));
        jPanel_payment.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 550, 70, -1));

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

    private void searchBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBookingActionPerformed
        // TODO add your handling code here:
        String boookingIdText = bBookingId.getText().trim();
        if (boookingIdText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a booking ID.");
            return;
        }

        try {
            int id = Integer.parseInt(boookingIdText);
            AdminBookingDa dao = new AdminBookingDa();
            BookingT book = dao.getBookById(id);
            
           

            if (book != null) {
                currentBook = book;
               
                
                // Fill the form fields
                bFirstName.setText(book.getFirstName());
                bMiddleName.setText(book.getMiddleName());
                bLastName.setText(book.getLastName());
                bNumber.setText(book.getPhoneNumber());
                bEmail.setText(book.getEmail());
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(book.getStartDate());
                    bStartDate.setDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
 
                bNumberOfPeople.setText(book.getNumberOfPeople());
                bAge.setText(String.valueOf(book.getAge()));
                bCountry.setText(book.getCountry());
                bNationality.setText(book.getNationality());
                bAddress.setText(book.getAddress());
                bZipCode.setText(book.getZipCode());
                bPayment.setSelectedItem(book.getPayment());
                try {
                    SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd");
                    Date endDate = end.parse(book.getEndDate());
                    bEndDate.setDate(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                bGuide_ID.setText(String.valueOf(book.getGuideID()));
                
                

               
            } else {
                JOptionPane.showMessageDialog(null, "No guide found with this ID.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid number.");
        }
    }//GEN-LAST:event_searchBookingActionPerformed

    private void bGuide_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuide_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bGuide_IDActionPerformed

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
                    loadGuideDataIntoTable(); 
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
                    loadGuideDataIntoTable(); 
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

    private void uNationalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uNationalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uNationalityActionPerformed

    private void addPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPaymentActionPerformed
        // TODO add your handling code here:
        Date selectedDate = bStartDate.getDate(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        Date endDate = bEndDate.getDate(); 
        SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd"); 
        String firstNameText = bFirstName.getText();
        String middleNameText = bMiddleName.getText();
        String lastNameText = bLastName.getText();
        String phoneNumberText = bNumber.getText();
        String emailText = bEmail.getText();
        String startDateText = sdf.format(selectedDate);
        String peopleText = bNumberOfPeople.getText();
        String ageText = bAge.getText();
        String countryText = bCountry.getText();
        String nationalityText = bNationality.getText();
        String addressText = bAddress.getText();
        String zipCodeText = bZipCode.getText();
        String paymentText =(String) bPayment.getSelectedItem();
        String endDateText = end.format(endDate);
        String guideIdText = bGuide_ID.getText();
        
        

        
        adminBookingController.adminBookingAll(guideIdText, firstNameText, middleNameText, lastNameText, phoneNumberText, emailText, startDateText, peopleText, ageText, countryText, nationalityText, addressText, zipCodeText, paymentText,endDateText ,adminDashboard.this);
        adminBookingController controller = new adminBookingController();
        controller.loadPaymentDataIntoTable(paymentTable);


    }//GEN-LAST:event_addPaymentActionPerformed

    private void clearBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBookingActionPerformed
        // TODO add your handling code here:
        clearBookingFields();
    }//GEN-LAST:event_clearBookingActionPerformed

    private void DeleteBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBookingActionPerformed
        // TODO add your handling code here:
        String bookingIdText = bBookingId.getText().trim();

        adminBookingController delete = new adminBookingController();
        delete.deleteBookingUpdate(bookingIdText);
        
        //table update
        adminBookingController controller = new adminBookingController();
        controller.loadPaymentDataIntoTable(paymentTable);
        
        
    }//GEN-LAST:event_DeleteBookingActionPerformed

    private void updateBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBookingActionPerformed
        // TODO add your handling code here:
        adminBookingController controller = new adminBookingController();
        controller.updateBookingSubmit(paymentTable, bBookingId, bGuide_ID, bFirstName, bMiddleName, bLastName,
            bNumber, bEmail, bStartDate, bEndDate, bNumberOfPeople, bAge, bCountry, bNationality,
            bAddress, bZipCode, bPayment);
        
    }//GEN-LAST:event_updateBookingActionPerformed

    private void bNumberOfPeopleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNumberOfPeopleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNumberOfPeopleActionPerformed

    private void uSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uSearchActionPerformed
        // TODO add your handling code here:
        String idText = uProfile_id.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a user ID.");
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid user ID format.");
            return;
        }

        ProfileController controller = new ProfileController(); // create controller
        controller.searchUserProfileById(userId, this);
    }//GEN-LAST:event_uSearchActionPerformed

    private void uClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uClearActionPerformed
        // TODO add your handling code here:
         ProfileController controller = new ProfileController();
         controller.clearUserProfileFields(this);
    }//GEN-LAST:event_uClearActionPerformed
    public void setUserIdText(String text){
        uProfile_id.setText(text);
    }
    private void uDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uDeleteActionPerformed
        // TODO add your handling code here:
        ProfileController controller = new ProfileController();
        controller.deleteUserProfileFromAdmin(this);
    }//GEN-LAST:event_uDeleteActionPerformed
    
    
    private void uUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uUpdateActionPerformed
        // TODO add your handling code here:
        ProfileController controller = new ProfileController();
        controller.updateUserProfileFromAdmin(this);
    }//GEN-LAST:event_uUpdateActionPerformed

    private void uImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uImageButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser file  = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path  = selectedFile.getAbsolutePath();
            u_img.setIcon(ResizePicture(path, null));
            picturePath = path;
        }
        else{
            System.out.println("NO FILE SELECTED");
        }
        
    }//GEN-LAST:event_uImageButtonActionPerformed
    
    
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
     public void clearBookingFields(){
        bBookingId.setText("");
        bGuide_ID.setText("");
        bFirstName.setText("");
        bMiddleName.setText("");
        bLastName.setText("");
        bNumber.setText("");
        bEmail.setText("");
        bNumberOfPeople.setText("");
        bAge.setText("");
        bCountry.setText("");
        bNationality.setText("");
        bAddress.setText("");
        bZipCode.setText("");
        bPayment.setSelectedIndex(0);
        bStartDate.setDate(null);
        bEndDate.setDate(null);
        
    }
    
    
    
    private void updateGuideCountLabel() {
    GuideDa dao = new GuideDa();
    int count = dao.getGuideCount();
    adminTotalLabel.setText(String.valueOf(count));
}
     private void updateUserCountLabel() {
    UserDa dao = new UserDa();
    int count = dao.getUserCount();
    userTotalLabel.setText(String.valueOf(count));
}
     
    public JLabel getUImageLabel() {
        return u_img;
    }

    public void setUImageIcon(ImageIcon icon) {
        u_img.setIcon(icon);
    }

    public void setUFirstName(String firstName) {
        uFirstName.setText(firstName);
    }
    public void setUMiddleName(String middleName) {
        uMiddleName.setText(middleName);
    }
    public void setULastName(String lastName) {
        uLastName.setText(lastName);
    }
    public void setUNationality(String nationality) {
        uNationality.setText(nationality);
    }
    public void setUCountry(String country) {
        uCountry.setText(country);
    }
    public void setUGender(String gender) {
        uGender.setSelectedItem(gender);
    }
    public void setUAge(String age) {
        uAge.setText(age);
    }
    public void setUBio(String bio) {
        uBio.setText(bio);
    }

    public String getUFirstName() {
        return uFirstName.getText().trim();
    }

    public String getUMiddleName() {
        return uMiddleName.getText().trim();
    }

    public String getULastName() {
        return uLastName.getText().trim();
    }

    public String getUNationality() {
        return uNationality.getText().trim();
    }

    public String getUCountry() {
        return uCountry.getText().trim();
    }

    public String getUGender() {
        return (String) uGender.getSelectedItem();
    }

    public String getUBio() {
        return uBio.getText().trim();
    }

    public String getUAge() {
        return uAge.getText().trim();
    }

    public String getUserIdText() {
        return uProfile_id.getText().trim();
    }

//    public JLabel getU_img() {
//        return u_img;
//    }
    
    public javax.swing.JTable getUserTable() {
    return userTable;
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
    private javax.swing.JButton DeleteBooking;
    private javax.swing.JButton addButton;
    private javax.swing.JButton addPayment;
    private javax.swing.JLabel adminTotalLabel;
    private javax.swing.JTextField bAddress;
    private javax.swing.JTextField bAge;
    private javax.swing.JTextField bBookingId;
    private javax.swing.JTextField bCountry;
    private javax.swing.JTextField bEmail;
    private com.toedter.calendar.JDateChooser bEndDate;
    private javax.swing.JTextField bFirstName;
    private javax.swing.JTextField bGuide_ID;
    private javax.swing.JTextField bLastName;
    private javax.swing.JTextField bMiddleName;
    private javax.swing.JTextField bNationality;
    private javax.swing.JTextField bNumber;
    private javax.swing.JTextField bNumberOfPeople;
    private javax.swing.JComboBox<String> bPayment;
    private com.toedter.calendar.JDateChooser bStartDate;
    private javax.swing.JTextField bZipCode;
    private javax.swing.JButton clearBooking;
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
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
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
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTable paymentTable;
    private javax.swing.JTable rateTable;
    private javax.swing.JButton searchBooking;
    private javax.swing.JTextField uAge;
    private javax.swing.JTextArea uBio;
    private javax.swing.JButton uClear;
    private javax.swing.JTextField uCountry;
    private javax.swing.JButton uDelete;
    private javax.swing.JTextField uFirstName;
    private javax.swing.JComboBox<String> uGender;
    private javax.swing.JButton uImageButton;
    private javax.swing.JTextField uLastName;
    private javax.swing.JTextField uMiddleName;
    private javax.swing.JTextField uNationality;
    private javax.swing.JTextField uProfile_id;
    private javax.swing.JButton uSearch;
    private javax.swing.JButton uUpdate;
    private javax.swing.JLabel u_img;
    private javax.swing.JButton updateBooking;
    private javax.swing.JButton updateButton;
    private javax.swing.JTable userTable;
    private javax.swing.JLabel userTotalLabel;
    // End of variables declaration//GEN-END:variables
}
