package lol.sunshinevalley.core.anarchy.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class IllegalChecker {

    /*
    Static class containing useful methods to check for illegal items
    @Author: Aduhkiss
     */

    public static boolean isOverStacked(ItemStack itemstack) {
        return false;
    }

    public static boolean disallowedItem(ItemStack itemstack) {
        if(itemstack.getType() == Material.BARRIER || itemstack.getType() == Material.BEDROCK
        || itemstack.getType() == Material.END_PORTAL_FRAME || itemstack.getType() == Material.LIGHT) {
            return true;
        }
        return false;
    }
}
