
package View;

import javax.swing.JOptionPane;

public class NewPassword_1 extends javax.swing.JFrame {

    public NewPassword_1() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        iEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        iOpassword = new javax.swing.JPasswordField();
        iNpassword = new javax.swing.JPasswordField();
        iRpassword = new javax.swing.JPasswordField();
        changeButton = new javax.swing.JButton();
        oShow = new javax.swing.JCheckBox();
        nShow = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(126, 168, 190));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("New Password");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Email");

        iEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        iEmail.setForeground(new java.awt.Color(79, 79, 79));
        iEmail.setText("Enter your email");
        iEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                iEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                iEmailFocusLost(evt);
            }
        });
        iEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iEmailActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Old-password");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("New-password");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Re-type");

        iOpassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        iOpassword.setForeground(new java.awt.Color(79, 79, 79));
        iOpassword.setText("password");
        iOpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                iOpasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                iOpasswordFocusLost(evt);
            }
        });

        iNpassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        iNpassword.setForeground(new java.awt.Color(79, 79, 79));
        iNpassword.setText("password");
        iNpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                iNpasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                iNpasswordFocusLost(evt);
            }
        });
        iNpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iNpasswordActionPerformed(evt);
            }
        });

        iRpassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        iRpassword.setForeground(new java.awt.Color(79, 79, 79));
        iRpassword.setText("password");
        iRpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                iRpasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                iRpasswordFocusLost(evt);
            }
        });

        changeButton.setBackground(new java.awt.Color(40, 83, 107));
        changeButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        changeButton.setForeground(new java.awt.Color(255, 255, 255));
        changeButton.setText("Change");
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        oShow.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        oShow.setText("show");
        oShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oShowActionPerformed(evt);
            }
        });

        nShow.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nShow.setText("show");
        nShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nShowActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(iNpassword)
                            .addComponent(iOpassword)
                            .addComponent(iEmail)
                            .addComponent(iRpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(oShow, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nShow, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(changeButton)
                .addGap(294, 294, 294))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1)))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iOpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oShow))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(iNpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nShow)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iRpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(changeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeButtonActionPerformed
        // TODO add your handling code here:
        
        if(iEmail.getText().isEmpty() && iOpassword.getText().isEmpty() && iNpassword.getText().isEmpty() && iRpassword.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"iEmail.getText().isEmpty()");
        }
        else if(iEmail.getText().contains("sumit@gmail.com") && iRpassword.getText().contains("1234")){
            JOptionPane.showMessageDialog(null,"changed password successfully");
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null,"incorrect email and password","message",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_changeButtonActionPerformed

    private void iNpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iNpasswordActionPerformed
        
    }//GEN-LAST:event_iNpasswordActionPerformed

    private void iEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iEmailFocusGained
        
        if(iEmail.getText().equals("Enter your email")){
            iEmail.setText("");
        }
    }//GEN-LAST:event_iEmailFocusGained

    private void iEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iEmailFocusLost
        
        if(iEmail.getText().isEmpty()){
            iEmail.setText("Enter your email");
        }
    }//GEN-LAST:event_iEmailFocusLost

    private void iOpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iOpasswordFocusGained
        
        String pwd = iOpassword.getText();
        if(pwd.equals("password")){
            iOpassword.setText("");
        }
    }//GEN-LAST:event_iOpasswordFocusGained

    private void iOpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iOpasswordFocusLost
        
        String pwd = iOpassword.getText();
        if(pwd.isEmpty()){
            iOpassword.setText("password");
        }
    }//GEN-LAST:event_iOpasswordFocusLost

    private void iNpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iNpasswordFocusGained
      
        String pwd = iNpassword.getText();
        if(pwd.equals("password")){
            iNpassword.setText("");
        }
    }//GEN-LAST:event_iNpasswordFocusGained

    private void iNpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iNpasswordFocusLost
       
        String pwd = iNpassword.getText();
        if(pwd.isEmpty()){
            iNpassword.setText("password");
        }
    }//GEN-LAST:event_iNpasswordFocusLost

    private void iRpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iRpasswordFocusGained
      
        String pwd = iRpassword.getText();
        if(pwd.equals("password")){
            iRpassword.setText("");
        }
    }//GEN-LAST:event_iRpasswordFocusGained

    private void iRpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iRpasswordFocusLost
      
        String pwd = iRpassword.getText();
        if(pwd.isEmpty()){
            iRpassword.setText("password");
        }
    }//GEN-LAST:event_iRpasswordFocusLost

    private void oShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oShowActionPerformed
     
        if(oShow.isSelected()){
            iOpassword.setEchoChar((char)0);
        }else
        {
            iOpassword.setEchoChar('*');
        }
    }//GEN-LAST:event_oShowActionPerformed

    private void nShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nShowActionPerformed
    
        if(nShow.isSelected()){
            iNpassword.setEchoChar((char)0);
        }else
        {
            iNpassword.setEchoChar('*');
        }
    }//GEN-LAST:event_nShowActionPerformed

    private void iEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iEmailActionPerformed

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
            java.util.logging.Logger.getLogger(NewPassword_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewPassword_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewPassword_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewPassword_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewPassword_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeButton;
    private javax.swing.JTextField iEmail;
    private javax.swing.JPasswordField iNpassword;
    private javax.swing.JPasswordField iOpassword;
    private javax.swing.JPasswordField iRpassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox nShow;
    private javax.swing.JCheckBox oShow;
    // End of variables declaration//GEN-END:variables
}
