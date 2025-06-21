
package Controller;

import Dao.GuideDa;
import Model.GuideA;
import View.Dashboard;
import View.GuideLists;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;




public class GuidesController {
     public static void loadGuideCards(Dashboard view) {
        view.getGuideArrayPanel().removeAll();
        view.getGuideArrayPanel().setLayout(new BoxLayout(view.getGuideArrayPanel(), BoxLayout.Y_AXIS));
        view.getGuideArrayPanel().setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        GuideDa dao = new GuideDa();
        ArrayList<GuideA> guideList = dao.getGuides();

        for (GuideA guide : guideList) {
            GuideLists panel = new GuideLists(guide, null);  // you can pass the user if needed
            panel.setGuide(guide);
            view.getGuideArrayPanel().add(panel);
            view.getGuideArrayPanel().add(Box.createVerticalStrut(0));
        }

        view.getGuideArrayPanel().revalidate();
        view.getGuideArrayPanel().repaint();
    }
}
