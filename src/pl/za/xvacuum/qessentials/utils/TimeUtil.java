package pl.za.xvacuum.qessentials.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import pl.za.xvacuum.qessentials.Main;

public class TimeUtil implements Listener{
	
	public final static List<Player> list = new ArrayList<Player>();
	
	public static void teleportDelay(final Player player, final Location loc)
	{
		list.add(player);
		Util.sendMessage(player, "&7Zaczekaj &c" + Main.getInstance().getConfig().getLong("tp-delay") + "&7 sekund!".replace("L", ""));
		Main.getInstance().getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
		    @SuppressWarnings("deprecation")
			public void run() {
		    	if(list.contains(player)){
			       player.teleport(loc);
			       list.remove(player);
			       Util.sendMessage(player, "&7Zostales przeteleportowany!");
			       player.playEffect(player.getLocation(), Effect.FLAME, 5);
			       Main.getInstance().getServer().getScheduler().cancelAllTasks();
		    	}
		    }
		}, Main.getInstance().getConfig().getLong("tp-delay") * 20); 
	}

	
	@EventHandler(priority = EventPriority.LOW)
	public void onmove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(list.contains(p)){
			list.remove(p);
			Util.sendMessage(p, "&cRuszyles sie! Teleport przerwany...");
		}
		
	}
}
