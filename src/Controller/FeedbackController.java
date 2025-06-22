
package Controller;

import Dao.Feedback;
import Model.GuideA;
import Model.SignUp;
import View.GuideView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



public class FeedbackController {
     public void submitRatingAndUpdateUI(GuideA guide, SignUp user, int selectedRating, GuideView view) {
         
        System.out.println("Guide ID: " + (guide != null ? guide.getGuideId() : "null"));
        System.out.println("User ID: " + (user != null ? user.getUserId() : "null"));
        System.out.println("Selected Rating: " + selectedRating);
        if (guide != null && user != null && selectedRating > 0) {
            Feedback dao = new Feedback();
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
}
