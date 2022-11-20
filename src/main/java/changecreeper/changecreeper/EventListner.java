package changecreeper.changecreeper;

import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EventListner implements Listener {
    @EventHandler
    public void onSpawn(CreatureSpawnEvent e){
        if(e.getEntityType() == EntityType.CREEPER){
            Creeper creeper = (Creeper) e.getEntity();
            creeper.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE,1,false,false));

            return;
        }
        Location l = e.getLocation();
        World w = l.getWorld();

        if(e.getEntityType() == EntityType.SQUID)return;
        if(e.getEntityType() == EntityType.GLOW_SQUID)return;
        if(e.getEntityType() == EntityType.DOLPHIN)return;
        if(e.getEntityType() == EntityType.TURTLE)return;
        if(e.getEntityType() == EntityType.COD)return;
        if(e.getEntityType() == EntityType.SALMON)return;
        if(e.getEntityType() == EntityType.PUFFERFISH)return;
        if(e.getEntityType() == EntityType.TROPICAL_FISH)return;
        if(e.getEntityType() == EntityType.AXOLOTL)return;
        if(e.getEntityType() == EntityType.BAT)return;

        for(int i = 0;i<12;i++){
            w.spawnEntity(l,EntityType.CREEPER);
        }


        e.setCancelled(true);
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        event.blockList().clear();
        if (event.getEntityType() == EntityType.CREEPER) {
            Creeper creeper = (Creeper) event.getEntity();
            creeper.getActivePotionEffects().stream()
                    .map(PotionEffect::getType)
                    .forEach(creeper::removePotionEffect);
        }
    }

    @EventHandler
    public void onClickEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Location l = p.getEyeLocation();
        if((e.getClickedBlock() != null) && (e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            if(e.getClickedBlock().getType() == Material.DIAMOND_BLOCK){
                if (p.getInventory().getItemInMainHand().getType() == Material.COBBLED_DEEPSLATE||p.getInventory().getItemInMainHand().getType() == Material.OBSIDIAN){
                    p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
                    Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + ChatColor.AQUA + "が設置しました！");
                    p.setDisplayName(ChatColor.RED + p.getName() +ChatColor.RESET);
                    p.setPlayerListName(ChatColor.RED + p.getName() +ChatColor.RESET);
                    start.ss = false;
                    start.i = 1;
                    for(Player op: Bukkit.getOnlinePlayers()){
                        Firework f = op.getWorld().spawn(op.getLocation(),Firework.class);
                        FireworkMeta data = (FireworkMeta) f.getFireworkMeta();
                        data.addEffects(FireworkEffect.builder().withColor(Color.PURPLE).withColor(Color.GREEN).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
                        data.setPower(1);
                        f.setFireworkMeta(data);
                        op.sendTitle(net.md_5.bungee.api.ChatColor.AQUA + "GAME CLEAR!!!", "クリーパー１０００体の世界", 40, 110, 40);
                        op.getLocation().getWorld().playSound(op.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE,0.5F,1);
                    }
                }
            }
        }
    }

    @EventHandler
    public void eatGunpower(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Location l = p.getLocation();
        Location pl = l.add(0,2,0);
        World w = p.getWorld();
        if(p.getInventory().getItemInMainHand().getType() != Material.GUNPOWDER)return;
        if (p.getFoodLevel() > 20 ) return;
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR )){
            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
            p.setFoodLevel(p.getFoodLevel()+4);
            w.spawnParticle(Particle.COMPOSTER,pl,20,1,1,1);
            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT,1,1);
        }
    }

    @EventHandler
    public void onBreakDiamondBlock(BlockBreakEvent e){
        Player p = e.getPlayer();

        if(p.hasPermission("AzCommand.commands.creeper"))return;
        if(e.getBlock().getType() == Material.DIAMOND_BLOCK){
            e.setCancelled(true);
            p.sendMessage("ダイアブロックの破壊は禁止されています");
            p.chat("/tell az_aka " + "ダイヤブロックを破壊しました");
        }

        if(e.getBlock().getType() == Material.OBSIDIAN){
            e.setCancelled(true);
            p.getInventory().addItem(new ItemStack(Material.OBSIDIAN,1));
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "say " + ChatColor.RED + p.getName() + ChatColor.RED + " が黒曜石をゲットしました！";
            p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,100000,1,false,false));
            Bukkit.dispatchCommand(console, command);

        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getPlayer();
        p.getInventory().removeItem(new ItemStack(Material.OBSIDIAN,100));
    }
}
