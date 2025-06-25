
package Controller;

import Dao.Feedback;
import Model.GuideA;
import Model.SignUp;
import View.GuideView;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class FeedbackController {
     public void submitRatingAndUpdateUI(GuideA guide, SignUp user, int selectedRating, GuideView view) {
         
        System.out.println("Guide ID: " + (guide != null ? guide.getGuideId() : "null"));
        System.out.println("User ID: " + (user != null ? user.getUserId() : "null"));
        System.out.println("Selected Rating: " + selectedRating);
        if (guide != null && user != null && selectedRating > 0) {
            Feedback dao = new Feedback();
            
             if (dao.hasUserRatedGuide(guide.getGuideId(), user.getUserId())) {
                JOptionPane.showMessageDialog(view, "You have already rated this guide.");
                return;
            }
            boolean success = dao.submitRating(guide.getGuideId(), user.getUserId(), selectedRating);

            if (success) {
                JOptionPane.showMessageDialog(view, "Thanks for rating!");
                updateGuideRating(guide, view); // ⬅ update after success
            } else {
                JOptionPane.showMessageDialog(view, "Failed to submit rating.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a star before submitting.");
        }
    }

    // Update star labels based on average
    public void updateGuideRating(GuideA guide, GuideView view) {
        Feedback dao = new Feedback();
        double avgRating = dao.getAverageRating(guide.getGuideId());

        JLabel[] updateStars = {
            view.getUpdateStar1(),
            view.getUpdateStar2(),
            view.getUpdateStar3(),
            view.getUpdateStar4(),
            view.getUpdateStar5()
        };

        for (int i = 0; i < 5; i++) {
            updateStars[i].setText(i < Math.round(avgRating) ? "★" : "☆");
        }
    }
    
    public void loadRatingPercentagesIntoTable(JTable rateTable) {
        Feedback dao = new Feedback();
        Map<Integer, Double> percentages = dao.getGuideRatingPercentages();

        DefaultTableModel model = (DefaultTableModel) rateTable.getModel();
        model.setRowCount(0); // Clear old data

        for (Map.Entry<Integer, Double> entry : percentages.entrySet()) {
            model.addRow(new Object[] {
                entry.getKey(), 
                String.format("%.2f%%", entry.getValue())
            });
        }
    }

}
