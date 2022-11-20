package changecreeper.changecreeper;

import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class start {

    public static int count = 6;
    public static int c;
    public static boolean ss = true;
    public static int i = 2700;

    public static void start(){


        new BukkitRunnable(){
            @Override
            public void run() {
                c = count - 1;
                count--;
                for (Player p : Bukkit.getOnlinePlayers()) {

                    p.sendMessage("ゲーム開始まで" + c);
                    Location loc = p.getLocation();
                    loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1F, 10);
                    p.chat("/clear @a");
                }

                if(count == 0){
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    String command = "time set 0";
                    Bukkit.dispatchCommand(console, command);
                    for(Player p : Bukkit.getOnlinePlayers()){
                        Location loc = p.getLocation();
                        p.sendTitle(ChatColor.AQUA + "GAME START", "クリーパー1000体の世界", 40, 80, 40);
                        loc.getWorld().playSound(loc, Sound.ENTITY_WITHER_SPAWN, 0.5F, 1);
                        p.chat("/advancement revoke " + p.getName() + " everything");
                        p.setFoodLevel(20);
                        p.setHealth(20);

                        this.cancel();

                        Bukkit.getOnlinePlayers().forEach((player)->{
                            player.setLevel(2700);
                        });

                        BukkitRunnable task = new BukkitRunnable() {

                            public void run(){
                                if(i == 0){
                                    Bukkit.getOnlinePlayers().forEach((player)->{
                                        player.setGameMode(GameMode.SPECTATOR);
                                        if(ss == false)return;
                                        player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH,0.4F,1);
                                        player.sendTitle(net.md_5.bungee.api.ChatColor.DARK_PURPLE + "FINISH!",ChangeCreeper.getPlugin().getConfig().getString("title"), 40, 250, 40);
                                    });
                                    cancel();
                                    return;
                                } else if( i < 5){
                                    Bukkit.getOnlinePlayers().forEach((player)->{
                                        Location loc = player.getLocation();
                                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                                    });
                                } else if(i == 180){
                                    Bukkit.getOnlinePlayers().forEach((player)->{
                                        Location loc = player.getLocation();
                                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                                        player.sendMessage("残り3分");
                                    });
                                }else if( i == 600){
                                    Bukkit.getOnlinePlayers().forEach((player)->{
                                        Location loc = player.getLocation();
                                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                                        player.sendMessage("残り10分");
                                    });
                                } else if( i == 1800){
                                    Bukkit.getOnlinePlayers().forEach((player)->{
                                        Location loc = player.getLocation();
                                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                                        player.sendMessage("残り30分");
                                    });
                                }
                                Bukkit.getOnlinePlayers().forEach((player)->{
                                    player.setLevel(i);
                                });

                                i--;
                            }
                        };
                        task.runTaskTimer(ChangeCreeper.getPlugin(),0L,20L);



                        return;
                    }
                }
            }
        }.runTaskTimer(ChangeCreeper.getPlugin(), 60L, 20L);
    }
}
