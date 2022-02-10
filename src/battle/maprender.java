package battle;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

public class maprender extends MapRenderer {
	
	int x = 9999;
	int y = 9999;
	
	public maprender() {
		super(true);
	}

	@Override
	public void render(MapView arg0, MapCanvas arg1, Player arg2) {
		if(main.g.nextborderloc != null && x == 9999 && y == 9999) {
			int x = (int) (((double) main.g.nextborderloc.getBlockX() - main.g.world.getSpawnLocation().getBlockX()) / 125 * 32 + 64);
			int y = (int) (((double) main.g.nextborderloc.getBlockZ() - main.g.world.getSpawnLocation().getBlockZ()) / 125 * 32 + 64);
			if(main.g.b == 3) {
				this.x = x;
				this.y = y;
			}
			int d = (int) (main.g.nextd / 125 * 32);
			int ax = x + d / 2;
			int bx = x - d / 2;
			int ay = y + d / 2;
			int by = y - d / 2;
			for (int i = 0; i < d; i++) {
				arg1.setPixel(ax, y - d / 2 + i, (byte) 200);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(bx, y - d / 2 + i, (byte) 200);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(x - d / 2 + i, ay, (byte) 200);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(x - d / 2 + i, by, (byte) 200);
			}
		}else {
			int d = (int) (main.g.nextd / 125 * 32);
			int ax = x + d / 2;
			int bx = x - d / 2;
			int ay = y + d / 2;
			int by = y - d / 2;
			for (int i = 0; i < d; i++) {
				arg1.setPixel(ax, y - d / 2 + i, (byte) 200);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(bx, y - d / 2 + i, (byte) 200);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(x - d / 2 + i, ay, (byte) 200);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(x - d / 2 + i, by, (byte) 200);
			}
		}
		if(x == 9999 && y == 9999) {
			int x = (int) (((double) main.g.borderloc.getBlockX() - main.g.world.getSpawnLocation().getBlockX()) / 125 * 32 + 64);
			int y = (int) (((double) main.g.borderloc.getBlockZ() - main.g.world.getSpawnLocation().getBlockZ()) / 125 * 32 + 64);
			int d = (int) (main.g.d / 125 * 32);
			int ax = x + d / 2;
			int bx = x - d / 2;
			int ay = y + d / 2;
			int by = y - d / 2;
			for (int i = 0; i < d; i++) {
				arg1.setPixel(ax, y - d / 2 + i, (byte) 125);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(bx, y - d / 2 + i, (byte) 125);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(x - d / 2 + i, ay, (byte) 125);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(x - d / 2 + i, by, (byte) 125);
			}
		}else {
			int d = (int) (main.g.d / 125 * 32);
			int ax = x + d / 2;
			int bx = x - d / 2;
			int ay = y + d / 2;
			int by = y - d / 2;
			for (int i = 0; i < d; i++) {
				arg1.setPixel(ax, y - d / 2 + i, (byte) 125);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(bx, y - d / 2 + i, (byte) 125);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(x - d / 2 + i, ay, (byte) 125);
			}
			for (int i = 0; i < d; i++) {
				arg1.setPixel(x - d / 2 + i, by, (byte) 125);
			}
		}
	}

}
