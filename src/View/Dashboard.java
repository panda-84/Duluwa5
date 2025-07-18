
package View;

import Controller.EquipmentsController;
import Controller.GuidesController;
import Controller.NavigationController;
import Controller.PlansController;
import Controller.ProfileController;
import Controller.UserController;
import Controller.userDeleteController;
import Dao.GuideDa;
import Dao.UserDa;
import Model.GuideA;
import Model.SignUp;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Dashboard extends javax.swing.JFrame {
    private SignUp user;
    private int userId;
    private UserController controller;
    private byte[] currentImageBytes;
    
    
 
    
    public Dashboard() {
        initComponents();
        jScrollPane2.setViewportView(guideArrayPanel);
        showOnlyPanel(jPanel_Dashboard);
        

      
    }
    

    
  

public Dashboard(SignUp user) {
    this.user = user;
    this.userId = user.getUserId(); // you can keep using this too if needed
    initComponents();
    //plans
    DefaultListModel<String> planListModel = new DefaultListModel<>();
    planList.setModel(planListModel);
    new PlansController(planList, planListModel, plansField, addPlans, deletePlans, userId);

    //equipments
    DefaultListModel<String> equipmentModel = new DefaultListModel<>();
    equipmentsList.setModel(equipmentModel);

    new EquipmentsController(
        equipmentsList,
        equipmentModel,
        equipmentComboBox,
        addEquipments,
        deleteEquipments,
        userId
    );
    
    NavigationController nav = new NavigationController(this);
    showOnlyPanel(jPanel_Dashboard);
    
    controller = new UserController(userId, null); 
    controller.displayProfileSummary(this);
    controller.loadProfileImage(this);
    GuidesController.loadGuideCards(this);   // or any setup
}

    public SignUp getCurrentUser(){
        return user;
    }
    
    
    
    
    private void showOnlyPanel(JPanel panelToShow) {
        JPanel[] allPanels = {
            jPanel_Dashboard,
            jPanel_Guide,
            jPanel_Navigation,
            jPanel_Profile_Management,
            jPanel_Plans
        };

        for (JPanel panel : allPanels) {
            panel.setVisible(false);
        }

        panelToShow.setVisible(true);

    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Container = new javax.swing.JPanel();
        jPanel_Menu = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        logOut = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel_Head = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel_Dashboard = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        guide = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        map = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        plans = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel_Guide = new javax.swing.JPanel();
        guideBack = new javax.swing.JLabel();
        labelGuide = new javax.swing.JTextField();
        searchGuideName = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        guideArrayPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel_Navigation = new javax.swing.JPanel();
        navigationBack = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        mapLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        label5 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        label6 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        label8 = new javax.swing.JLabel();
        jPanel_Profile_Management = new javax.swing.JPanel();
        profileBack = new javax.swing.JLabel();
        label_img = new javax.swing.JLabel();
        changePhoto = new javax.swing.JButton();
        editProfile = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        changePButton = new javax.swing.JButton();
        deleteAccount = new javax.swing.JButton();
        bankLinkButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        labelFullName = new javax.swing.JLabel();
        labelAge = new javax.swing.JLabel();
        labelGender = new javax.swing.JLabel();
        labelCountry = new javax.swing.JLabel();
        labelNationality = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        labelBio = new javax.swing.JTextArea();
        jPanel_Plans = new javax.swing.JPanel();
        plansBack = new javax.swing.JLabel();
        addPlans = new javax.swing.JButton();
        plansField = new javax.swing.JTextField();
        deletePlans = new javax.swing.JButton();
        equipmentComboBox = new javax.swing.JComboBox<>();
        addEquipments = new javax.swing.JButton();
        deleteEquipments = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        planList = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        equipmentsList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(246, 240, 237));

        jPanel_Container.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_Menu.setBackground(new java.awt.Color(126, 168, 190));
        jPanel_Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(187, 177, 147));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logOut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        logOut.setForeground(new java.awt.Color(255, 255, 255));
        logOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/white_icons8-logout1-48.png"))); // NOI18N
        logOut.setText("Log Out");
        logOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutMouseClicked(evt);
            }
        });
        jPanel3.add(logOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jPanel_Menu.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 793, 120, 43));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/white_icons8-user-100.png"))); // NOI18N
        jPanel_Menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 99, -1));

        jPanel4.setBackground(new java.awt.Color(40, 83, 107));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/white_icons8-admin-settings-male-24.png"))); // NOI18N
        profile.setText("profile");
        profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        jPanel4.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, -1));

        jPanel_Menu.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 750, 120, 43));

        jSeparator1.setForeground(new java.awt.Color(79, 79, 79));
        jPanel_Menu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 100, 8));

        jPanel_Container.add(jPanel_Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel_Head.setBackground(new java.awt.Color(40, 83, 107));
        jPanel_Head.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 56)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("DULUWA");
        jPanel_Head.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(583, 21, -1, 67));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/logo1.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        jPanel_Head.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 6, 120, 108));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/logo1.png"))); // NOI18N
        jLabel12.setText("jLabel12");
        jPanel_Head.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 11, 113, -1));

        jPanel_Container.add(jPanel_Head, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 1338, 120));

        jPanel_Dashboard.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_Dashboard.setName(""); // NOI18N
        jPanel_Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("You can view the map by clicking the map board.");
        jPanel_Dashboard.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        jPanel5.setBackground(new java.awt.Color(246, 240, 237));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/navigation.jpg"))); // NOI18N
        jLabel19.setText("jLabel19");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_Dashboard.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 1200, 280));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(210, 160));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        guide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/guids1.png"))); // NOI18N
        guide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guideMouseClicked(evt);
            }
        });
        jPanel6.add(guide, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 170, -1));

        jPanel_Dashboard.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setForeground(new java.awt.Color(204, 255, 153));
        jPanel7.setPreferredSize(new java.awt.Dimension(210, 160));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        map.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/map1.png"))); // NOI18N
        map.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        map.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mapMouseClicked(evt);
            }
        });
        jPanel7.add(map, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, -1));

        jPanel_Dashboard.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 410, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(210, 160));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        plans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/plans1.png"))); // NOI18N
        plans.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plansMouseClicked(evt);
            }
        });
        jPanel8.add(plans, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 150, -1));

        jPanel_Dashboard.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 410, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Guide");
        jPanel_Dashboard.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Navigation");
        jPanel_Dashboard.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 590, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Plans");
        jPanel_Dashboard.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 590, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Our Tour Guide Management System connects travelers with guides, enabling easy bookings, real-time schedules, and secure payments. It simplifies tour planning and enhances the overall travel experience efficiently.");
        jPanel_Dashboard.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 670, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons-back.png"))); // NOI18N
        jPanel_Dashboard.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, -1));

        jPanel_Container.add(jPanel_Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 1330, 710));

        jPanel_Guide.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_Guide.setPreferredSize(new java.awt.Dimension(1330, 710));

        guideBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons-back.png"))); // NOI18N
        guideBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guideBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guideBackMouseClicked(evt);
            }
        });

        labelGuide.setPreferredSize(new java.awt.Dimension(73, 34));

        searchGuideName.setBackground(new java.awt.Color(40, 83, 107));
        searchGuideName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-search-24 (1).png"))); // NOI18N
        searchGuideName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchGuideName.setPreferredSize(new java.awt.Dimension(76, 34));

        jButton7.setBackground(new java.awt.Color(40, 83, 107));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Filter");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setPreferredSize(new java.awt.Dimension(76, 34));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportView(guideArrayPanel);

        guideArrayPanel.setBackground(new java.awt.Color(246, 240, 237));
        jScrollPane2.setViewportView(guideArrayPanel);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Users are able to evaluate guides by their ratings and select the most suitable option.");

        javax.swing.GroupLayout jPanel_GuideLayout = new javax.swing.GroupLayout(jPanel_Guide);
        jPanel_Guide.setLayout(jPanel_GuideLayout);
        jPanel_GuideLayout.setHorizontalGroup(
            jPanel_GuideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GuideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_GuideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_GuideLayout.createSequentialGroup()
                        .addComponent(guideBack)
                        .addGap(387, 387, 387)
                        .addComponent(labelGuide, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchGuideName, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_GuideLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel_GuideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(247, Short.MAX_VALUE))
        );
        jPanel_GuideLayout.setVerticalGroup(
            jPanel_GuideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GuideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_GuideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_GuideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelGuide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchGuideName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(guideBack)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel_Container.add(jPanel_Guide, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 1330, 710));

        jPanel_Navigation.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_Navigation.setPreferredSize(new java.awt.Dimension(1330, 710));

        navigationBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons-back.png"))); // NOI18N
        navigationBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navigationBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigationBackMouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Explore Nepal's top tourist areas with our interactive map. Discover breathtaking destinations, from the Himalayas to cultural heritage sites. Find the best spots and plan your journey with ease.");

        mapLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(187, 177, 147));
        jPanel1.setPreferredSize(new java.awt.Dimension(162, 34));

        label5.setBackground(new java.awt.Color(187, 177, 147));
        label5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label5.setForeground(new java.awt.Color(255, 255, 255));
        label5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label5.setText("Highway");
        label5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        label4.setBackground(new java.awt.Color(187, 177, 147));
        label4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label4.setForeground(new java.awt.Color(255, 255, 255));
        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setText("Vehicle path");
        label4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        label3.setBackground(new java.awt.Color(187, 177, 147));
        label3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label3.setForeground(new java.awt.Color(255, 255, 255));
        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText("Walkway");
        label3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        label2.setBackground(new java.awt.Color(187, 177, 147));
        label2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setText("Destination");
        label2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        label1.setBackground(new java.awt.Color(187, 177, 147));
        label1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("Trek Route");
        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBackground(new java.awt.Color(194, 148, 138));

        label6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label6.setForeground(new java.awt.Color(255, 255, 255));
        label6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label6.setText("Resort ");
        label6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label6.setPreferredSize(new java.awt.Dimension(44, 34));

        label7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label7.setForeground(new java.awt.Color(255, 255, 255));
        label7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label7.setText("Restaurant ");
        label7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label7.setPreferredSize(new java.awt.Dimension(44, 34));

        label8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label8.setForeground(new java.awt.Color(255, 255, 255));
        label8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label8.setText("Cafe");
        label8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label8.setPreferredSize(new java.awt.Dimension(52, 34));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(label7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_NavigationLayout = new javax.swing.GroupLayout(jPanel_Navigation);
        jPanel_Navigation.setLayout(jPanel_NavigationLayout);
        jPanel_NavigationLayout.setHorizontalGroup(
            jPanel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)
            .addGroup(jPanel_NavigationLayout.createSequentialGroup()
                .addGroup(jPanel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_NavigationLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 1215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_NavigationLayout.createSequentialGroup()
                                .addComponent(mapLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_NavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(navigationBack)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel_NavigationLayout.setVerticalGroup(
            jPanel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NavigationLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(navigationBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mapLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jLabel27)
                .addGap(34, 34, 34))
        );

        jPanel_Container.add(jPanel_Navigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 1330, 710));

        jPanel_Profile_Management.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_Profile_Management.setPreferredSize(new java.awt.Dimension(1330, 710));

        profileBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons-back.png"))); // NOI18N
        profileBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profileBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileBackMouseClicked(evt);
            }
        });

        label_img.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        changePhoto.setBackground(new java.awt.Color(40, 83, 107));
        changePhoto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        changePhoto.setForeground(new java.awt.Color(255, 255, 255));
        changePhoto.setText("Change Photo");
        changePhoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePhotoActionPerformed(evt);
            }
        });

        editProfile.setBackground(new java.awt.Color(40, 83, 107));
        editProfile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editProfile.setForeground(new java.awt.Color(255, 255, 255));
        editProfile.setText("Edit");
        editProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editProfile.setPreferredSize(new java.awt.Dimension(72, 34));
        editProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProfileActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(187, 177, 147));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Booked History");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setPreferredSize(new java.awt.Dimension(76, 40));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        changePButton.setBackground(new java.awt.Color(40, 83, 107));
        changePButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        changePButton.setForeground(new java.awt.Color(255, 255, 255));
        changePButton.setText("Change Password");
        changePButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePButton.setPreferredSize(new java.awt.Dimension(76, 40));
        changePButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePButtonActionPerformed(evt);
            }
        });

        deleteAccount.setBackground(new java.awt.Color(194, 148, 138));
        deleteAccount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteAccount.setForeground(new java.awt.Color(255, 255, 255));
        deleteAccount.setText("Delete Account");
        deleteAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteAccount.setPreferredSize(new java.awt.Dimension(137, 40));
        deleteAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccountActionPerformed(evt);
            }
        });

        bankLinkButton.setBackground(new java.awt.Color(40, 83, 107));
        bankLinkButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bankLinkButton.setForeground(new java.awt.Color(255, 255, 255));
        bankLinkButton.setText("Bank Link");
        bankLinkButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bankLinkButton.setPreferredSize(new java.awt.Dimension(99, 34));
        bankLinkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankLinkButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("If you need assistance, please contact us by email or phone, and our team will be happy to help you.");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Phone Number: ");

        jLabel14.setText("+977-9856784539");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Email:");

        jLabel17.setText("duluwa@gmail.com");

        jLabel26.setText("Name:");

        jLabel28.setText("Age:");

        jLabel29.setText("Gender:");

        jLabel30.setText("Country:");

        jLabel31.setText("Nationality:");

        jLabel32.setText("Bio:");

        labelFullName.setText("jLabel33");

        labelAge.setText("jLabel34");

        labelGender.setText("jLabel35");

        labelCountry.setText("jLabel36");

        labelNationality.setText("jLabel37");

        labelBio.setEditable(false);
        labelBio.setColumns(20);
        labelBio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelBio.setLineWrap(true);
        labelBio.setRows(5);
        jScrollPane3.setViewportView(labelBio);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelFullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(labelFullName))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(labelAge))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(labelGender))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(labelCountry))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(labelNationality))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_Profile_ManagementLayout = new javax.swing.GroupLayout(jPanel_Profile_Management);
        jPanel_Profile_Management.setLayout(jPanel_Profile_ManagementLayout);
        jPanel_Profile_ManagementLayout.setHorizontalGroup(
            jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Profile_ManagementLayout.createSequentialGroup()
                .addGap(515, 515, 515)
                .addComponent(editProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deleteAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(changePButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(320, 320, 320))
            .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))))
                    .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                        .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bankLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_img, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(changePhoto)))
                        .addGap(44, 44, 44)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Profile_ManagementLayout.setVerticalGroup(
            jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(profileBack))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                        .addComponent(label_img, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changePhoto)
                        .addGap(214, 214, 214))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(changePButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bankLinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel4)
                .addGap(11, 11, 11)
                .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17))
                .addGap(29, 29, 29))
        );

        jPanel_Container.add(jPanel_Profile_Management, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, 710));

        jPanel_Plans.setBackground(new java.awt.Color(246, 240, 237));
        jPanel_Plans.setPreferredSize(new java.awt.Dimension(1330, 710));

        plansBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons-back.png"))); // NOI18N
        plansBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plansBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plansBackMouseClicked(evt);
            }
        });

        addPlans.setBackground(new java.awt.Color(40, 83, 107));
        addPlans.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addPlans.setForeground(new java.awt.Color(255, 255, 255));
        addPlans.setText("ADD");
        addPlans.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPlans.setPreferredSize(new java.awt.Dimension(72, 34));

        plansField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        plansField.setText("Enter your plans");
        plansField.setPreferredSize(new java.awt.Dimension(73, 34));
        plansField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                plansFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                plansFieldFocusLost(evt);
            }
        });

        deletePlans.setBackground(new java.awt.Color(194, 148, 138));
        deletePlans.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deletePlans.setForeground(new java.awt.Color(255, 255, 255));
        deletePlans.setText("DELETE");
        deletePlans.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletePlans.setPreferredSize(new java.awt.Dimension(76, 34));

        equipmentComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        equipmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hiking Boots - Rs. 6000", "Trekking Pants - Rs. 2000", "Thermal Innerwear (Top) - Rs. 1500", "Thermal Innerwear (Bottom) - Rs. 1500", "Fleece Jacket - Rs. 2500", "Down Jacket - Rs. 8000", "Waterproof Jacket - Rs. 5000", "Quick-Dry T-Shirt - Rs. 800", "Woolen Hat - Rs. 400", "Sun Hat / Cap - Rs. 500", "Gloves Set - Rs. 1200", "Woolen Socks - Rs. 250", "Rucksack (60L) - Rs. 7000", "Daypack (25L) - Rs. 2500", "Rain Cover for Bag - Rs. 700", "Dry Bag - Rs. 800", "Sleeping Bag - Rs. 7000", "Sleeping Pad - Rs. 2000", "Tent (2-person) - Rs. 10000", "Portable Stove - Rs. 3000", "Gas Canister - Rs. 600", "Cooking Pot Set - Rs. 2000", "Spoon/Fork Set - Rs. 400", "Water Bottle (Insulated) - Rs. 1500", "Water Purifier - Rs. 3000", "Trekking Poles - Rs. 2500", "Headlamp - Rs. 1500", "Sunglasses (UV) - Rs. 1200", "Power Bank (10000mAh) - Rs. 2500", "Multi-tool Knife - Rs. 1200", "Map & Compass - Rs. 700", " " }));
        equipmentComboBox.setPreferredSize(new java.awt.Dimension(73, 34));

        addEquipments.setBackground(new java.awt.Color(40, 83, 107));
        addEquipments.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addEquipments.setForeground(new java.awt.Color(255, 255, 255));
        addEquipments.setText("ADD");
        addEquipments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addEquipments.setPreferredSize(new java.awt.Dimension(72, 34));

        deleteEquipments.setBackground(new java.awt.Color(194, 148, 138));
        deleteEquipments.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteEquipments.setForeground(new java.awt.Color(255, 255, 255));
        deleteEquipments.setText("DELETE");
        deleteEquipments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteEquipments.setPreferredSize(new java.awt.Dimension(76, 34));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel13.setText("Plans");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel18.setText("Equipments");

        jScrollPane5.setViewportView(planList);

        jScrollPane6.setViewportView(equipmentsList);

        javax.swing.GroupLayout jPanel_PlansLayout = new javax.swing.GroupLayout(jPanel_Plans);
        jPanel_Plans.setLayout(jPanel_PlansLayout);
        jPanel_PlansLayout.setHorizontalGroup(
            jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PlansLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(234, 234, 234))
            .addGroup(jPanel_PlansLayout.createSequentialGroup()
                .addGroup(jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_PlansLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(plansBack))
                    .addGroup(jPanel_PlansLayout.createSequentialGroup()
                        .addGroup(jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_PlansLayout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addGroup(jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(plansField, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel_PlansLayout.createSequentialGroup()
                                        .addComponent(addPlans, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(deletePlans, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_PlansLayout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(129, 129, 129)
                        .addGroup(jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_PlansLayout.createSequentialGroup()
                                .addComponent(addEquipments, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deleteEquipments, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(equipmentComboBox, 0, 469, Short.MAX_VALUE)
                            .addComponent(jScrollPane6))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel_PlansLayout.setVerticalGroup(
            jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PlansLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plansBack)
                .addGap(34, 34, 34)
                .addGroup(jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_PlansLayout.createSequentialGroup()
                        .addGroup(jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plansField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equipmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPlans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletePlans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addEquipments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteEquipments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jPanel_Container.add(jPanel_Plans, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 1340, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 1460, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
        
       showOnlyPanel(jPanel_Profile_Management);
        
    }//GEN-LAST:event_profileMouseClicked

    private void guideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guideMouseClicked
       
        showOnlyPanel(jPanel_Guide);
        GuidesController.loadGuideCards(this);

        
        

    }//GEN-LAST:event_guideMouseClicked

    private void mapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapMouseClicked
        
        showOnlyPanel(jPanel_Navigation);
    }//GEN-LAST:event_mapMouseClicked

    private void plansMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plansMouseClicked
        
        showOnlyPanel(jPanel_Plans);
    }//GEN-LAST:event_plansMouseClicked

    private void logOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutMouseClicked
        
        int response = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to log out?",
        "Confirm Logout",
        javax.swing.JOptionPane.YES_NO_OPTION,
        javax.swing.JOptionPane.QUESTION_MESSAGE
    );

    if (response == javax.swing.JOptionPane.YES_OPTION) {
        Login l = new Login();
        l.setVisible(true);
        l.pack();
        l.setLocationRelativeTo(null);
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }
        
    }//GEN-LAST:event_logOutMouseClicked

    private void profileBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileBackMouseClicked
       
        showOnlyPanel(jPanel_Dashboard);
    }//GEN-LAST:event_profileBackMouseClicked

    private void plansBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plansBackMouseClicked
       
        showOnlyPanel(jPanel_Dashboard);
    }//GEN-LAST:event_plansBackMouseClicked

    private void guideBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guideBackMouseClicked
       
        showOnlyPanel(jPanel_Dashboard);
    }//GEN-LAST:event_guideBackMouseClicked

    private void navigationBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navigationBackMouseClicked
       
        showOnlyPanel(jPanel_Dashboard);
    }//GEN-LAST:event_navigationBackMouseClicked

    private void deleteAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAccountActionPerformed
        UserController controller = new UserController(userId, this);
        controller.deleteLoggedInUserAccount();

    }//GEN-LAST:event_deleteAccountActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void changePButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePButtonActionPerformed
        
        NewPassword n = new NewPassword();
                n.setVisible(true);
                n.pack();
                n.setLocationRelativeTo(null);
                n.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
    }//GEN-LAST:event_changePButtonActionPerformed

    private void editProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProfileActionPerformed
        
        userProfileEdit userEdit = new userProfileEdit(this.userId, this); 
            userEdit.setVisible(true);
            userEdit.setLocationRelativeTo(null); 
            userEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_editProfileActionPerformed

    private void changePhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePhotoActionPerformed
        // TODO add your handling code here:
        controller.changeUserPhoto(this);
    }//GEN-LAST:event_changePhotoActionPerformed

    private void bankLinkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankLinkButtonActionPerformed
        JOptionPane.showMessageDialog(null, "While this feature is not available at the moment, it is planned for a future release.");
    }//GEN-LAST:event_bankLinkButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null, "While this feature is not available at the moment, it is planned for a future release.");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void plansFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_plansFieldFocusGained
        if(plansField.getText().equals("Enter your plans")){
            plansField.setText("");
        }
    }//GEN-LAST:event_plansFieldFocusGained

    private void plansFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_plansFieldFocusLost
        if(plansField.getText().isEmpty()){
            plansField.setText("Enter your plans");
        }
    }//GEN-LAST:event_plansFieldFocusLost

    //navigation
    public JLabel[] getNavigationLabels() {
        return new JLabel[]{label1, label2, label3, label4, label5, label6, label7, label8};
    }

    public JLabel getMapLabel() {
        return mapLabel;
    }

    
    
    
    
    public javax.swing.JPanel getGuideArrayPanel() {
        return guideArrayPanel;
    }
    
    

    
    public void setLabelFullName(String fullName) {
    labelFullName.setText(fullName);
    }

    public void setLabelAge(String age) {
        labelAge.setText(age);
    }

    public void setLabelGender(String gender) {
        labelGender.setText(gender);
    }

    public void setLabelCountry(String country) {
        labelCountry.setText(country);
    }

    public void setLabelNationality(String nationality) {
        labelNationality.setText(nationality);
    }

    public void setLabelBio(String bio) {
        labelBio.setText(bio);
    }

    public JLabel getLabelImg() {
    return label_img;
    }

    public void setCurrentImageBytes(byte[] imageBytes) {
        this.currentImageBytes = imageBytes;
    }

    public byte[] getCurrentImageBytes() {
        return this.currentImageBytes;
    }
   


  
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEquipments;
    private javax.swing.JButton addPlans;
    private javax.swing.JButton bankLinkButton;
    private javax.swing.JButton changePButton;
    private javax.swing.JButton changePhoto;
    private javax.swing.JButton deleteAccount;
    private javax.swing.JButton deleteEquipments;
    private javax.swing.JButton deletePlans;
    private javax.swing.JButton editProfile;
    private javax.swing.JComboBox<String> equipmentComboBox;
    private javax.swing.JList<String> equipmentsList;
    private javax.swing.JLabel guide;
    private javax.swing.JPanel guideArrayPanel;
    private javax.swing.JLabel guideBack;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_Container;
    private javax.swing.JPanel jPanel_Dashboard;
    private javax.swing.JPanel jPanel_Guide;
    private javax.swing.JPanel jPanel_Head;
    private javax.swing.JPanel jPanel_Menu;
    private javax.swing.JPanel jPanel_Navigation;
    private javax.swing.JPanel jPanel_Plans;
    private javax.swing.JPanel jPanel_Profile_Management;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel labelAge;
    private javax.swing.JTextArea labelBio;
    private javax.swing.JLabel labelCountry;
    private javax.swing.JLabel labelFullName;
    private javax.swing.JLabel labelGender;
    private javax.swing.JTextField labelGuide;
    private javax.swing.JLabel labelNationality;
    private javax.swing.JLabel label_img;
    private javax.swing.JLabel logOut;
    private javax.swing.JLabel map;
    private javax.swing.JLabel mapLabel;
    private javax.swing.JLabel navigationBack;
    private javax.swing.JList<String> planList;
    private javax.swing.JLabel plans;
    private javax.swing.JLabel plansBack;
    private javax.swing.JTextField plansField;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel profileBack;
    private javax.swing.JButton searchGuideName;
    // End of variables declaration//GEN-END:variables
}
