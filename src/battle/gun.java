package battle;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class gun implements Listener {
	
	@EventHandler
	public void right(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(!e.getPlayer().getItemInHand().getType().equals(Material.AIR) && e.getPlayer().getCooldown(e.getPlayer().getItemInHand().getType()) == 0 && e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()) {
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName())
				switch(e.getPlayer().getItemInHand().getItemMeta().getDisplayName()) {
				case "¡×a±ÇÃÑ (12/12)":
					e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 2);
					e.getPlayer().setCooldown(e.getPlayer().getItemInHand().getType(), 10);
					ItemMeta m = e.getPlayer().getItemInHand().getItemMeta();
					m.setDisplayName("¡×a±ÇÃÑ (11/12)");
					ItemStack i = new ItemStack(e.getPlayer().getItemInHand().getType());
					i.setItemMeta(m);
					e.getPlayer().getInventory().addItem(i);
					Snowball sb = (Snowball) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation().add(0, 1, 0), EntityType.SNOWBALL);
					sb.setItem(new ItemStack(Material.STONE_BUTTON));
					sb.setShooter(e.getPlayer());
					sb.addScoreboardTag("shot1");
					sb.setVelocity(e.getPlayer().getLocation().getDirection().multiply(10));
					break;
				}
			}
		}
	}
	
	public void gun1(Player p) {
		
	}
	
	@EventHandler
	public void shop(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Snowball && e.getDamager().getScoreboardTags().size() != 0) {
			if(e.getDamager().getScoreboardTags().contains("shot1")) {
				e.setDamage(8);
			}
		}
	}

}
