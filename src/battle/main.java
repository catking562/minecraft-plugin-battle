package battle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class main extends JavaPlugin implements Listener {
	
	static Game g;
	static boolean isgame = false;
	static ArrayList<Recipe> recipe = new ArrayList<Recipe>();
	
	int i;
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		//Bukkit.getPluginManager().registerEvents(new gun(), this);
		File file = new File(getDataFolder() + "/config.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		if(config.getString("int") == null) {
			config.set("int", 0);
		}
		i = config.getInt("int");
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//gun();
	}
	
	public void gun() {
		ItemStack i = new ItemStack(Material.WOODEN_HOE);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName("§a권총 (12/12)");
		i.setItemMeta(m);
		ShapedRecipe r = new ShapedRecipe(i);
		r.shape("aaa","0ba");
		r.setIngredient('0', Material.AIR);
		r.setIngredient('a', Material.IRON_INGOT);
		r.setIngredient('b', Material.STONE_BUTTON);
		Bukkit.addRecipe(r);
	}
	
	public void onDisable() {
		System.gc();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || sender.getName().equalsIgnoreCase("taewookim562")) {
			if(command.getName().contentEquals("battle")) {
				if(!isgame) {
					isgame = true;
					ArrayList<Player> list = new ArrayList<Player>();
					for(Player p : Bukkit.getOnlinePlayers()) {
						list.add(p);
					}
					g = new Game(list, i);
					i++;
					File file = new File(getDataFolder() + "/config.yml");
					YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
					config.set("int", i);
					try {
						config.save(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return super.onCommand(sender, command, label, args);
	}
	
	@EventHandler
	public void entitydamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player && isgame && g.ab) {
			e.setCancelled(true);
		}
		if(isgame && g.b == 0) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void gate(PortalCreateEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		String string = "안녕! 친구, 반가워";
		Random r = new Random();
		switch (r.nextInt(10)) {
		case 0:
			string = "어서와!";
			break;
		case 1:
			string = "게임참가!";
			break;
		case 2:
			string = ":>";
			break;
		case 3:
			string = "^^;";
			break;
		case 4:
			string = "왔어?";
			break;
		case 5:
			string = "와! 아시는구나";
			break;
		case 6:
			string = "오! 왔네~";
			break;
		case 7:
			string = "반가워!";
			break;
		case 8:
			string = "대박!";
			break;
		case 9:
			string = "우와~";
			break;
		}
		e.setJoinMessage("§f[§a+§f] §7" + e.getPlayer().getName() + " §8" + string);
		if(isgame) {
			g.obser.add(e.getPlayer());
			e.getPlayer().teleport(g.world.getSpawnLocation());
			e.getPlayer().setGameMode(GameMode.SPECTATOR);
		}
	}
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		e.setQuitMessage("§f[§c-§f] §7" + e.getPlayer().getName() + " §8" + "ㅠㅠ");
		if(isgame) {
			g.obser.remove(e.getPlayer());
			g.players.remove(e.getPlayer());
		}
	}
	
	@EventHandler
	public void death(PlayerDeathEvent e) {
		if(isgame) {
			g.obser.remove(e.getEntity());
			g.players.remove(e.getEntity());
		}
	}

}
