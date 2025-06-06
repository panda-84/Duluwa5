/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author acer
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        
        jPanel_Dashboard.setVisible(true);
        jPanel_Guide.setVisible(false);
        jPanel_Navigation.setVisible(false);
        jPanel_Profile_Management.setVisible(false);
        jPanel_Plans.setVisible(false);
        
        //default border for menu item
//        Border default_Border = BorderFactory.createMatteBorder(1,0,1,0, new Color(46,49,49));
//       
//       //yellow border for menu item 
//        Border yellow_Border = BorderFactory.createMatteBorder(1,0,1,0, Color.yellow);
      
    }
    
   
  
    
//    public void addAction(){
//        Component[] components =
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jLabel4 = new javax.swing.JLabel();
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
        jPanel_Guide = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel_Navigation = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel_Profile_Management = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel_Plans = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons-back.png"))); // NOI18N
        jPanel_Dashboard.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

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

        jPanel_Container.add(jPanel_Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 1330, 710));

        jPanel_Guide.setPreferredSize(new java.awt.Dimension(1330, 710));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 100)); // NOI18N
        jLabel6.setText("Guide");

        javax.swing.GroupLayout jPanel_GuideLayout = new javax.swing.GroupLayout(jPanel_Guide);
        jPanel_Guide.setLayout(jPanel_GuideLayout);
        jPanel_GuideLayout.setHorizontalGroup(
            jPanel_GuideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GuideLayout.createSequentialGroup()
                .addGap(407, 407, 407)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(586, Short.MAX_VALUE))
        );
        jPanel_GuideLayout.setVerticalGroup(
            jPanel_GuideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GuideLayout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );

        jPanel_Container.add(jPanel_Guide, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 1330, 710));

        jPanel_Navigation.setPreferredSize(new java.awt.Dimension(1330, 710));

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 100)); // NOI18N
        jLabel17.setText("Navigation");

        javax.swing.GroupLayout jPanel_NavigationLayout = new javax.swing.GroupLayout(jPanel_Navigation);
        jPanel_Navigation.setLayout(jPanel_NavigationLayout);
        jPanel_NavigationLayout.setHorizontalGroup(
            jPanel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NavigationLayout.createSequentialGroup()
                .addGap(445, 445, 445)
                .addComponent(jLabel17)
                .addContainerGap(278, Short.MAX_VALUE))
        );
        jPanel_NavigationLayout.setVerticalGroup(
            jPanel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NavigationLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel17)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        jPanel_Container.add(jPanel_Navigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 1330, 710));

        jPanel_Profile_Management.setPreferredSize(new java.awt.Dimension(1330, 710));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
        jLabel18.setText("Profile Management");

        javax.swing.GroupLayout jPanel_Profile_ManagementLayout = new javax.swing.GroupLayout(jPanel_Profile_Management);
        jPanel_Profile_Management.setLayout(jPanel_Profile_ManagementLayout);
        jPanel_Profile_ManagementLayout.setHorizontalGroup(
            jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(jLabel18)
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel_Profile_ManagementLayout.setVerticalGroup(
            jPanel_Profile_ManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Profile_ManagementLayout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jLabel18)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        jPanel_Container.add(jPanel_Profile_Management, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, 710));

        jPanel_Plans.setPreferredSize(new java.awt.Dimension(1330, 710));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 56)); // NOI18N
        jLabel2.setText("plans");

        javax.swing.GroupLayout jPanel_PlansLayout = new javax.swing.GroupLayout(jPanel_Plans);
        jPanel_Plans.setLayout(jPanel_PlansLayout);
        jPanel_PlansLayout.setHorizontalGroup(
            jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PlansLayout.createSequentialGroup()
                .addGap(600, 600, 600)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(555, Short.MAX_VALUE))
        );
        jPanel_PlansLayout.setVerticalGroup(
            jPanel_PlansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PlansLayout.createSequentialGroup()
                .addGap(288, 288, 288)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
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
        // TODO add your handling code here:
        jPanel_Dashboard.setVisible(false);
        jPanel_Guide.setVisible(false);
        jPanel_Navigation.setVisible(false);
        jPanel_Profile_Management.setVisible(true);
        jPanel_Plans.setVisible(false);
        
    }//GEN-LAST:event_profileMouseClicked

    private void guideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guideMouseClicked
        // TODO add your handling code here:
        jPanel_Dashboard.setVisible(false);
        jPanel_Navigation.setVisible(false);
        jPanel_Profile_Management.setVisible(false);
        jPanel_Guide.setVisible(true);
        jPanel_Plans.setVisible(false);
    }//GEN-LAST:event_guideMouseClicked

    private void mapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapMouseClicked
        // TODO add your handling code here:
        jPanel_Dashboard.setVisible(false);
        jPanel_Guide.setVisible(false);
        jPanel_Profile_Management.setVisible(false);
        jPanel_Navigation.setVisible(true);
        jPanel_Plans.setVisible(false);
    }//GEN-LAST:event_mapMouseClicked

    private void plansMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plansMouseClicked
        // TODO add your handling code here:
        jPanel_Dashboard.setVisible(false);
        jPanel_Guide.setVisible(false);
        jPanel_Profile_Management.setVisible(false);
        jPanel_Navigation.setVisible(false);
        jPanel_Plans.setVisible(true);
    }//GEN-LAST:event_plansMouseClicked

    private void logOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutMouseClicked
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel guide;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel_Container;
    private javax.swing.JPanel jPanel_Dashboard;
    private javax.swing.JPanel jPanel_Guide;
    private javax.swing.JPanel jPanel_Head;
    private javax.swing.JPanel jPanel_Menu;
    private javax.swing.JPanel jPanel_Navigation;
    private javax.swing.JPanel jPanel_Plans;
    private javax.swing.JPanel jPanel_Profile_Management;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel logOut;
    private javax.swing.JLabel map;
    private javax.swing.JLabel plans;
    private javax.swing.JLabel profile;
    // End of variables declaration//GEN-END:variables
}
