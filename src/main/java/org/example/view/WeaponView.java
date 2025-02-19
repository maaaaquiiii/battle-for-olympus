package org.example.view;

import org.example.model.Weapon;
import org.example.controller.WeaponController;

public class WeaponView extends View {
    private static WeaponController weaponController = new WeaponController();
    public void displayWeaponsDetails() {
        Weapon[] weapons = weaponController.getWeapons();

        for (int i = 0; i < weapons.length; i++) {
            Weapon weapon = weapons[i];
            if (weapon != null) {
                displayMessage((i + 1) + ". " + weapon);
            }
        }
    }

    public int getWeaponIndexFromUser() {
        displayMessage("Enter the number of the weapon you want to equip:");
        return getUserInt() - 1;
    }
}
