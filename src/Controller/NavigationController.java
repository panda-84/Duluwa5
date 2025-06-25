package Controller;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import View.Dashboard;

public class NavigationController {

    private final Dashboard dashboard;
    private final String[] imagePaths = {
         "/pictures/map1Default.jpg", "/pictures/map2.jpg", "/pictures/map3.jpg", "/pictures/map4.jpg",
    "/pictures/map5.jpg", "/pictures/map6.jpg", "/pictures/map7.jpg", "/pictures/map8.jpg"
    };

    public NavigationController(Dashboard dashboard) {
        this.dashboard = dashboard;
        initNavigationEvents();
        showDefaultImage();
    }

    private void initNavigationEvents() {
        JLabel[] navLabels = dashboard.getNavigationLabels();
        JLabel mapLabel = dashboard.getMapLabel();

        for (int i = 0; i < navLabels.length; i++) {
            final int index = i;
            navLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setImageToLabel(mapLabel, imagePaths[index]);
                }
            });
        }
    }

    private void showDefaultImage() {
        JLabel mapLabel = dashboard.getMapLabel();
        SwingUtilities.invokeLater(() -> {
            setImageToLabel(mapLabel, "/pictures/map1Default.jpg"); // Or use a dedicated default.png if you want
        });
    }

    private void setImageToLabel(JLabel label, String resourcePath) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(resourcePath));
            Image img = icon.getImage().getScaledInstance(
                label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
            label.setText("Image not found");
        }
    }
}
