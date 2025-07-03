/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.EquipmentsDa;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class EquipmentsController {
    private final JList<String> equipmentsList;
    private final DefaultListModel<String> model;
    private final JComboBox<String> equipmentComboBox;
    private final JButton addBtn, deleteBtn;
    private final int userId;
    private final EquipmentsDa dao;
    private Map<Integer, String> equipmentMap;

    public EquipmentsController(JList<String> equipmentsList, DefaultListModel<String> model,
                                JComboBox<String> equipmentComboBox,
                                JButton addBtn, JButton deleteBtn, int userId) {
        this.equipmentsList = equipmentsList;
        this.model = model;
        this.equipmentComboBox = equipmentComboBox;
        this.addBtn = addBtn;
        this.deleteBtn = deleteBtn;
        this.userId = userId;
        this.dao = new EquipmentsDa();

        loadEquipments();
        setupListeners();
    }

    private void loadEquipments() {
        model.clear();
        equipmentMap = dao.getEquipmentsByUserId(userId);
        for (String item : equipmentMap.values()) {
            model.addElement(item);
        }
    }
    

    private void setupListeners() {
        addBtn.addActionListener(e -> {
            String selectedItem = (String) equipmentComboBox.getSelectedItem();
            if (selectedItem != null && !selectedItem.trim().isEmpty()) {
                if (dao.addEquipment(userId, selectedItem)) {
                    loadEquipments();
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedIndex = equipmentsList.getSelectedIndex();
            if (selectedIndex >= 0) {
                int equipmentId = (int) equipmentMap.keySet().toArray()[selectedIndex];
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Delete this item?\n" + equipmentMap.get(equipmentId),
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (dao.deleteEquipmentById(equipmentId)) {
                        loadEquipments();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select an item to delete.");
            }
        });
    }
}
