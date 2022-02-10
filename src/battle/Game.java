package battle;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.bukkit.map.MapView.Scale;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Game {
	
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Player> obser = new ArrayList<Player>();
	World world;
	Location borderloc;
	Location nextborderloc;
	Location bugeditor;
	Vector v;
	double d;
	double nextd;
	double vd;
	boolean ab = false;
	int b = 0;
	
	public Game(ArrayList<Player> players, int i) {
		for(Player p : players) {
			p.sendMessage("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n§a----------------------");
			p.sendMessage("§e게임을 시작하는 중입니다.!");
			p.sendMessage("§e월드생성중(렉걸릴 수 있음)");
			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
		}
		WorldCreator wc = new WorldCreator("battle" + i);
		wc.environment(World.Environment.NORMAL);
		wc.type(WorldType.NORMAL);
		world = wc.createWorld();
		this.players = players;
		startgame();
	}
	
	public void startgame() {
		world.setGameRuleValue("reducedDebugInfo", "true");
		world.setGameRuleValue("announceAdvancements", "false");
		for(Player p : players) {
			p.sendMessage("§a월드 생성 완료");
			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
			p.setGameMode(GameMode.SURVIVAL);
		}
		BukkitRunnable brun = new BukkitRunnable() {
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage("§a3");
					p.playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 1, 2);
				}
				BukkitRunnable brun = new BukkitRunnable() {
					@Override
					public void run() {
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage("§e2");
							p.playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 1, 2);
						}
						BukkitRunnable brun = new BukkitRunnable() {
							@Override
							public void run() {
								for(Player p : Bukkit.getOnlinePlayers()) {
									p.sendMessage("§c1");
									p.playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 1, 2);
								}
								BukkitRunnable brun = new BukkitRunnable() {
									@Override
									public void run() {
										for(Player p : Bukkit.getOnlinePlayers()) {
											p.sendMessage("§4시작!");
											p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
											p.setHealth(p.getMaxHealth());
											p.setFoodLevel(20);
										}
										borderloc = world.getSpawnLocation();
										d = 500;
										world.getWorldBorder().setSize(d);
										world.getWorldBorder().setCenter(borderloc);
										for(Player p : players) {
											Random r = new Random();
											double x = borderloc.getX() + (r.nextDouble() * d - d / 2);
											double z = borderloc.getZ() + (r.nextDouble() * d - (d / 2));
											p.teleport(new Location(world, x , 255, z));
											p.setNoDamageTicks(400);
											p.closeInventory();
											p.getInventory().clear();
											ItemStack i = new ItemStack(Material.FILLED_MAP);
											MapMeta m = (MapMeta) i.getItemMeta();
											MapView map = Bukkit.createMap(world);
											map.getRenderers().clear();
											map.addRenderer(new maprender());
											map.setCenterX(world.getSpawnLocation().getBlockX());
											map.setCenterZ(world.getSpawnLocation().getBlockZ());
											map.setScale(Scale.NORMAL);
											map.setWorld(world);
											map.setTrackingPosition(true);
											m.setMapView(map);
											i.setItemMeta(m);
											p.getInventory().addItem(i);
										}
										ab = true;
										BukkitRunnable brun = new BukkitRunnable() {
											int a = 0;
											@Override
											public void run() {
												a++;
												for(Player p : Bukkit.getOnlinePlayers()) {
													p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§a무적타임 제거까지 §e: §7" + (20 - a / 20)));
												}
												if(a >= 400) {
													for(Player p : Bukkit.getOnlinePlayers()) {
														p.sendTitle("§4무적타임이 제거 되었습니다.", "");
														p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
													}
													ab = false;
													update();
													cancel();
												}
											}
										};brun.runTaskTimer(main.getPlugin(main.class), 0, 0);
									}
								};brun.runTaskLater(main.getPlugin(main.class), 20);
							}
						};brun.runTaskLater(main.getPlugin(main.class), 20);
					}
				};brun.runTaskLater(main.getPlugin(main.class), 20);
			}
		};brun.runTaskLater(main.getPlugin(main.class), 40);
	}
	
	public void setnextborder() {
		if(b < 3) {
			Random r = new Random();
			double a = r.nextDouble() * 360;
			borderloc.setYaw((float) a);
			nextborderloc = new Location(world, borderloc.getX() + borderloc.getDirection().getX() * 50, borderloc.getY(), borderloc.getZ() + borderloc.getDirection().getZ() * 50);
		}
		nextd = d / 4 * 3;
		double x = Math.abs(borderloc.getDirection().getX() * 50);
		double z = Math.abs(borderloc.getDirection().getZ() * 50);
		double b = 0;
		if(x <= z) {
			b = d - z - 50;
		}else {
			b = d - x - 50;
		}
		if(nextd > b) {
			nextd = b;
		}
	}
	
	public boolean isend() {
		if(players.size() <= 1) {
			return true;
		}
		return false;
	}
	
	public void update() {
		b++;
		setnextborder();
		BukkitRunnable brun = new BukkitRunnable() {
			int a = 180;
			@Override
			public void run() {
				a--;
				int m = a / 60;
				int s = a % 60;
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§a다음 자기장까지 §e: §c" + m + ":" + s));
				}
				if(isend()) {
					cancel();
					main.isgame = false;
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.sendTitle(players.get(0).getName() + "승리!", "");
					}
				}
				if(a <= 0) {
					v = new Vector(nextborderloc.getX() - borderloc.getX(), nextborderloc.getY(), nextborderloc.getZ() - borderloc.getZ());
					v = new Vector(v.getX() / 2400, v.getY() / 2400, v.getZ() / 2400);
					vd = (d - nextd) / -2400;
					BukkitRunnable brun = new BukkitRunnable() {
						int a = 2400;
						@Override
						public void run() {
							a--;
							borderloc.add(v);
							world.getWorldBorder().setCenter(borderloc);
							d += vd;
							world.getWorldBorder().setSize(d);
							int m = a / 1200;
							int s = (a % 1200) / 20;
							for(Player p : Bukkit.getOnlinePlayers()) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§a자기장 접근중 §e: §c" + m + ":" + s));
							}
							if(isend()) {
								cancel();
								main.isgame = false;
								for(Player p : Bukkit.getOnlinePlayers()) {
									p.sendTitle(players.get(0).getName() + "승리!", "");
								}
							}
							if(a <= 0) {
								borderloc = nextborderloc;
								d = nextd;
								update();
								cancel();
							}
						}
					};brun.runTaskTimer(main.getPlugin(main.class), 0, 0);
					cancel();
				}
			}
		};brun.runTaskTimer(main.getPlugin(main.class), 20, 20);
	}
}
