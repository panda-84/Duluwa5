
package View;

import Model.GuideA;
import Model.SignUp;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class GuideLists extends javax.swing.JPanel {
    private GuideA guide;
    private SignUp user;
   
    
    public GuideLists() {
        initComponents();
       
    }
    public GuideLists(GuideA guide, SignUp user) {
        this.guide = guide;
        this.user = user;
        initComponents();
        setGuide(guide);

    }
//    
//    public void setGuide(GuideA guide) {
//        nameLabel.setText(guide.getFullName());
//        ageLabel.setText(String.valueOf(guide.getAge()));
//        bioLabel.setText(guide.getBio());
//        statusLabel.setText(guide.getStatus());
//        byte[] imageData = guide.getPicture(); 
//
//        if (imageData != null) {
//            ImageIcon icon = new ImageIcon(imageData);
//            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//            imageLabel.setIcon(new ImageIcon(img));
//        } else {
//            imageLabel.setIcon(null); // or show a default "no image" icon
//        }
//    }
    
    public void setGuide(GuideA guide) {
        this.guide = guide; // make sure it's saved
        nameLabel.setText(guide.getFullName());
        ageLabel.setText(String.valueOf(guide.getAge()));
        bioLabel.setText(guide.getBio());
        statusLabel.setText(guide.getStatus());

        byte[] imageData = guide.getPicture(); 
        if (imageData != null) {
            ImageIcon icon = new ImageIcon(imageData);
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } else {
            imageLabel.setIcon(null);
        }

        // 👇 Fetch rating from DB and show stars
        Dao.Feedback feedbackDao = new Dao.Feedback();
        double avgRating = feedbackDao.getAverageRatingForGuide(guide.getGuideId());

        StringBuilder stars = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            stars.append(i <= Math.round(avgRating) ? "★" : "☆");
        }
        ratingLabel.setText(stars.toString());
        }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guideCard = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        seeMeButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        ageLabel = new javax.swing.JLabel();
        ratingLabel = new javax.swing.JLabel();
        bioLabel = new javax.swing.JLabel();
        reviewLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();

        guideCard.setBackground(new java.awt.Color(246, 240, 237));
        guideCard.setPreferredSize(new java.awt.Dimension(960, 234));

        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        seeMeButton.setBackground(new java.awt.Color(40, 83, 107));
        seeMeButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        seeMeButton.setForeground(new java.awt.Color(255, 255, 255));
        seeMeButton.setText("see me");
        seeMeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seeMeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeMeButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Age");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Rating");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Bio");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Review");

        nameLabel.setText("ram lama");

        ageLabel.setText("45");

        bioLabel.setText("ram is tenawejwa ");

        reviewLabel.setText("see reviews ");

        statusLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        statusLabel.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(ratingLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 422, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(reviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statusLabel)
                        .addGap(55, 55, 55)))
                .addComponent(seeMeButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ratingLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(bioLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seeMeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(reviewLabel)
                    .addComponent(statusLabel))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout guideCardLayout = new javax.swing.GroupLayout(guideCard);
        guideCard.setLayout(guideCardLayout);
        guideCardLayout.setHorizontalGroup(
            guideCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guideCardLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        guideCardLayout.setVerticalGroup(
            guideCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guideCardLayout.createSequentialGroup()
                .addGroup(guideCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(guideCardLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(guideCardLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(guideCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(guideCard, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(346, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void seeMeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeMeButtonActionPerformed

System.out.println("Guide full name: " + this.guide.getFullName());
        GuideView guideView = new GuideView(this.guide, this.user); 
    guideView.setVisible(true);

    guideView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    

    }//GEN-LAST:event_seeMeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ageLabel;
    private javax.swing.JLabel bioLabel;
    private javax.swing.JPanel guideCard;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JLabel reviewLabel;
    private javax.swing.JButton seeMeButton;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
