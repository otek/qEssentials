package pl.za.xvacuum.qessentials.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.za.xvacuum.qessentials.utils.Util;
import pl.za.xvacuum.qessentials.utils.newCommand;

public class ClearInv extends newCommand{

	public ClearInv() {
		super("clear", "Wyczyszczenie ekwipunku", "/clear", "clear", Arrays.asList(new String[] { "clearinventory", "ci", "c", "qclearinventory", "qci", "qc" }));
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		Player p = (Player)sender;
	    p.getInventory().clear();
	    p.getInventory().setHelmet(null);
	    p.getInventory().setChestplate(null);
	    p.getInventory().setLeggings(null);
	    p.getInventory().setBoots(null);
	    p.updateInventory();
	    p.getInventory().setHeldItemSlot(0);
	    Util.sendMessage(p, "&7Twoj ekwipunek zostal wyczyszczony!");
	}
	
	

}