/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.PlansDa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author acer
 */
public class PlansController {
    private final JList<String> planList;
    private final DefaultListModel<String> planListModel;
    private final JTextField plansField;
    private final JButton addPlans, deletePlans;
    private final int userId;
    private final PlansDa dao;
    private Map<Integer, String> planMap; // plan_id → text

    public PlansController(JList<String> planList, DefaultListModel<String> model,
                           JTextField plansField, JButton addPlans, JButton deletePlans, int userId) {
        this.planList = planList;
        this.planListModel = model;
        this.plansField = plansField;
        this.addPlans = addPlans;
        this.deletePlans = deletePlans;
        this.userId = userId;
        this.dao = new PlansDa();

        loadPlans();
        setupListeners();
    }

    private void loadPlans() {
        planListModel.clear();
        planMap = dao.getPlanMapByUserId(userId);
        for (String text : planMap.values()) {
            planListModel.addElement(text);
        }
    }

    private void setupListeners() {
        addPlans.addActionListener(e -> {
            String text = plansField.getText().trim();
            if (!text.isEmpty() && dao.addPlan(userId, text)) {
                plansField.setText("");
                loadPlans();
            }
        });

        deletePlans.addActionListener(e -> {
            int selectedIndex = planList.getSelectedIndex();
            if (selectedIndex >= 0) {
                int planIdToDelete = (int) planMap.keySet().toArray()[selectedIndex];
                int confirm = JOptionPane.showConfirmDialog(null,
                    "Delete this plan?\n" + planMap.get(planIdToDelete),
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (dao.deletePlanById(planIdToDelete)) {
                        loadPlans();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a plan to delete.");
            }
        });
    }
}
