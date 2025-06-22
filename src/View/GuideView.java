package View;

import Controller.FeedbackController;
import Dao.Feedback;
import Model.GuideA;
import Model.SignUp;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class GuideView extends javax.swing.JFrame {
    private GuideA guide;
    private SignUp user;
    private int selectedRating = 0;  
    
     

    
    public GuideView() {
        initComponents();
        
        

    }
    
    public GuideView(GuideA guide, SignUp user){
        initComponents();
        this.guide = guide;
        this.user = user;
        
        initStarListeners();
        setGuideProfile(guide);
         
        

        
         
        
    }
    
    

    private void setSelectedStars(int rating) {
        this.selectedRating = rating;
        System.out.println("Selected rating: " + this.selectedRating); 
        JLabel[] stars = { star1, star2, star3, star4, star5 };
        for (int i = 0; i < 5; i++) {
            stars[i].setText(i < rating ? "★" : "☆");
        }
    }
    

    public void setGuideProfile(GuideA guide){
        fullNameLabel.setText(guide.getFullName());
        ageLabel.setText(String.valueOf(guide.getAge()));
        contactLabel.setText(guide.getFullName());
        bioLabel.setText(guide.getFullName());
        byte[] imageData = guide.getPicture(); 

        if (imageData != null) {
            ImageIcon icon = new ImageIcon(imageData);
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } else {
            imageLabel.setIcon(null); // or show a default "no image" icon
        }
        
        FeedbackController controller = new FeedbackController();
        controller.updateGuideRating(guide, this);
    }
    
    private void initStarListeners(){
        star1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setSelectedStars(1);
            }
        });
        star2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setSelectedStars(2);
            }
        });
        star3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setSelectedStars(3);
            }
        });
        star4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setSelectedStars(4);
            }
        });
        star5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setSelectedStars(5);
            }
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        fullNameLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ageLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        contactLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bioLabel = new javax.swing.JTextArea();
        bookGuide = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        starSubmit = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_text = new javax.swing.JTextField();
        msg_send = new javax.swing.JButton();
        updateStar1 = new javax.swing.JLabel();
        updateStar2 = new javax.swing.JLabel();
        updateStar3 = new javax.swing.JLabel();
        updateStar4 = new javax.swing.JLabel();
        star1 = new javax.swing.JLabel();
        star2 = new javax.swing.JLabel();
        star3 = new javax.swing.JLabel();
        star4 = new javax.swing.JLabel();
        star5 = new javax.swing.JLabel();
        updateStar5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(246, 240, 237));

        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        fullNameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fullNameLabel.setText("Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Age");

        ageLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ageLabel.setText("34");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Gender");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Male");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Call Number");

        contactLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        contactLabel.setText("9870987654");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Rating");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Bio");

        bioLabel.setEditable(false);
        bioLabel.setColumns(20);
        bioLabel.setLineWrap(true);
        bioLabel.setRows(5);
        jScrollPane1.setViewportView(bioLabel);

        bookGuide.setBackground(new java.awt.Color(40, 83, 107));
        bookGuide.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bookGuide.setForeground(new java.awt.Color(255, 255, 255));
        bookGuide.setText("Book Now");
        bookGuide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookGuide.setPreferredSize(new java.awt.Dimension(101, 34));
        bookGuide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookGuideActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Comments");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Rate Me");

        starSubmit.setBackground(new java.awt.Color(40, 83, 107));
        starSubmit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        starSubmit.setForeground(new java.awt.Color(255, 255, 255));
        starSubmit.setText("submit");
        starSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        starSubmit.setPreferredSize(new java.awt.Dimension(79, 34));
        starSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                starSubmitActionPerformed(evt);
            }
        });

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        msg_area.setEditable(false);
        msg_area.setColumns(20);
        msg_area.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        msg_area.setLineWrap(true);
        msg_area.setRows(5);
        jScrollPane4.setViewportView(msg_area);

        msg_text.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        msg_text.setPreferredSize(new java.awt.Dimension(64, 34));

        msg_send.setBackground(new java.awt.Color(79, 79, 79));
        msg_send.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8-send-24.png"))); // NOI18N
        msg_send.setPreferredSize(new java.awt.Dimension(30, 34));
        msg_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_sendActionPerformed(evt);
            }
        });

        updateStar1.setText("☆");

        updateStar2.setText("☆");

        updateStar3.setText("☆");
        updateStar3.setToolTipText("");

        updateStar4.setText("☆");

        star1.setText("☆");
        star1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        star2.setText("☆");
        star2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        star3.setText("☆");
        star3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        star4.setText("☆");
        star4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        star5.setText("☆");
        star5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        updateStar5.setText("☆");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(star1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(star2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(star3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(star4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(star5))
                            .addComponent(starSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(546, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bookGuide, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(updateStar1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateStar2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateStar3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateStar4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateStar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ageLabel)
                                    .addComponent(contactLabel))
                                .addGap(209, 209, 209)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(msg_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fullNameLabel)
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ageLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(contactLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(msg_send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(updateStar1)
                        .addComponent(updateStar2)
                        .addComponent(updateStar3)
                        .addComponent(updateStar4)
                        .addComponent(updateStar5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(star1)
                                    .addComponent(star2)
                                    .addComponent(star3)
                                    .addComponent(star4)
                                    .addComponent(star5))
                                .addGap(18, 18, 18)
                                .addComponent(starSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                .addComponent(bookGuide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))))))
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

    private void bookGuideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookGuideActionPerformed
       
        if (guide != null) {
             
            Booking bookingFrame = new Booking(this.guide); 
            bookingFrame.setVisible(true);
            bookingFrame.setLocationRelativeTo(null); 
            bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            System.out.println("Guide is null.");
        }
    }//GEN-LAST:event_bookGuideActionPerformed

    private void msg_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_sendActionPerformed
        

        
    }//GEN-LAST:event_msg_sendActionPerformed

    private void starSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_starSubmitActionPerformed
         System.out.println("Submitting rating: " + this.selectedRating);
        if (this.selectedRating == 0) {
            JOptionPane.showMessageDialog(null, "Please select a star before submitting");
            return;
        }
        
        FeedbackController controller = new FeedbackController();
        controller.submitRatingAndUpdateUI(guide, user, this.selectedRating, this);
    }//GEN-LAST:event_starSubmitActionPerformed

    
    
    public JLabel getUpdateStar1() { return updateStar1; }
    public JLabel getUpdateStar2() { return updateStar2; }
    public JLabel getUpdateStar3() { return updateStar3; }
    public JLabel getUpdateStar4() { return updateStar4; }
    public JLabel getUpdateStar5() { return updateStar5; }

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuideView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ageLabel;
    private javax.swing.JTextArea bioLabel;
    private javax.swing.JButton bookGuide;
    private javax.swing.JLabel contactLabel;
    private javax.swing.JLabel fullNameLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JButton msg_send;
    private javax.swing.JTextField msg_text;
    private javax.swing.JLabel star1;
    private javax.swing.JLabel star2;
    private javax.swing.JLabel star3;
    private javax.swing.JLabel star4;
    private javax.swing.JLabel star5;
    private javax.swing.JButton starSubmit;
    private javax.swing.JLabel updateStar1;
    private javax.swing.JLabel updateStar2;
    private javax.swing.JLabel updateStar3;
    private javax.swing.JLabel updateStar4;
    private javax.swing.JLabel updateStar5;
    // End of variables declaration//GEN-END:variables
}
