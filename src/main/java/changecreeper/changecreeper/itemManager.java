package changecreeper.changecreeper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class itemManager {
    public static ItemStack wand;
    public static void init(){createWand();}

    public static void createWand(){
        ItemStack item = new ItemStack(Material.SHIELD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "貧弱な盾");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "爆発に数回耐えられる");
        if (meta instanceof Damageable){
            ((Damageable) meta).setDamage(320);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        //item.setDurability((short)-50);

        wand = item;

        //ShapelessRecipe sir = new ShapelessRecipe(NamespacedKey.minecraft("wand_shapeless"),item);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("wand"),item);
        sr.shape("WIW","WWW"," W ");
        sr.setIngredient('W', new RecipeChoice.MaterialChoice(
                Material.OAK_PLANKS,
                Material.ACACIA_PLANKS,
                Material.DARK_OAK_PLANKS,
                Material.BIRCH_PLANKS,
                Material.CRIMSON_PLANKS,
                Material.JUNGLE_PLANKS,
                Material.MANGROVE_PLANKS,
                Material.SPRUCE_PLANKS,
                Material.WARPED_PLANKS));
        sr.setIngredient('I',Material.IRON_INGOT);
        Bukkit.getServer().addRecipe(sr);
    }
}
