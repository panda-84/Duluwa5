/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Bibek
 */
public class UserEquipment {
    private int equipmentId;
    private int userId;
    private String equipment;

    public UserEquipment() {}

    public UserEquipment(int equipmentId, int userId, String equipment) {
        this.equipmentId = equipmentId;
        this.userId = userId;
        this.equipment = equipment;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

}
