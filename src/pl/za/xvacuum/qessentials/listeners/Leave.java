package pl.za.xvacuum.qessentials.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.za.xvacuum.qessentials.Main;
import pl.za.xvacuum.qessentials.utils.Util;

public class Leave implements Listener {
	
	
	@EventHandler
	public void playerLeave(PlayerQuitEvent e){
		String format = Main.getInstance().getConfig().getString("leave-format");
		if (format.isEmpty()){
			e.setQuitMessage(null);
		}else{
			e.setQuitMessage(Util.setHEX(format.replace("{PLAYER}", e.getPlayer().getName())));
		}
	}

}
