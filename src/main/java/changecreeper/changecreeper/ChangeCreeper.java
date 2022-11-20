package changecreeper.changecreeper;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChangeCreeper extends JavaPlugin {
    public static ChangeCreeper plugin;
    public static ChangeCreeper instance;
    public ChangeCreeper(){instance = this;}
    private int amount;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        amount = 0;
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new EventListner(),this);
        getCommand("creeper").setExecutor(new AzCommand());
        itemManager.init();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static ChangeCreeper getInstance(){return instance;}
    public int getAmount(){return amount;}
    public void setAmount(int amount){this.amount = amount;}
    public static ChangeCreeper getPlugin(){
        return plugin;
    }
}
