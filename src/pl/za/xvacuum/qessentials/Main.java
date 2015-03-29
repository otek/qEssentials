package pl.za.xvacuum.qessentials;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.milkbowl.vault.chat.Chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pl.za.xvacuum.qessentials.commands.AdminChat;
import pl.za.xvacuum.qessentials.commands.Broadcast;
import pl.za.xvacuum.qessentials.commands.ChangeLore;
import pl.za.xvacuum.qessentials.commands.ChangeName;
import pl.za.xvacuum.qessentials.commands.ClearInv;
import pl.za.xvacuum.qessentials.commands.Enchant;
import pl.za.xvacuum.qessentials.commands.Gamemode;
import pl.za.xvacuum.qessentials.commands.Gc;
import pl.za.xvacuum.qessentials.commands.Help;
import pl.za.xvacuum.qessentials.commands.Helpop;
import pl.za.xvacuum.qessentials.commands.Info;
import pl.za.xvacuum.qessentials.commands.Motd;
import pl.za.xvacuum.qessentials.commands.Reload;
import pl.za.xvacuum.qessentials.commands.Time;
import pl.za.xvacuum.qessentials.commands.Weather;
import pl.za.xvacuum.qessentials.listeners.Join;
import pl.za.xvacuum.qessentials.listeners.Leave;
import pl.za.xvacuum.qessentials.listeners.PlayerChat;
import pl.za.xvacuum.qessentials.listeners.ServerList;
import pl.za.xvacuum.qessentials.utils.LogUtil;

public class Main extends JavaPlugin
{
	private static Main instance;
	private static CommandMap cmdMap;
	private final List<Command> cmds = new ArrayList<Command>();
	private static Chat chat = null;
	
	public static Main getInstance()
	{
		return instance;
	}
	
	public static boolean getBarAPI()
	{
		if(Main.getInstance().getConfig().getBoolean("barapi") == true){
			return true;
		}else{
			return false;
		}
	}
	
	public void onEnable()
	{
		LogUtil.info("Ladowanie systemu...");
		saveDefaultConfig();
		LogUtil.info("Zaladowano konfiguracje serwera!");
	    setupChat();
		LogUtil.info("Znaleziono plugin: BarAPI");
		LogUtil.info("Implementacja API zakonczona!");
		LogUtil.info("Znaleziono plugin: Vault");
		LogUtil.info("Implementacja API zakonczona!");
		registerCommands();
		LogUtil.info("Zaladowano komendy!");
		registerEvents();
		LogUtil.info("Zaladowano wydarzenia!");
		LogUtil.info("System zostal zaladowany!");
	}
	
    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }
	
	public void onLoad()
	{
		instance = this;
	}
	
	private void registerCommands()
	{
		registerCommand(new Info());
		registerCommand(new Gc());
		registerCommand(new Reload());
		registerCommand(new AdminChat());
		registerCommand(new ClearInv());
		registerCommand(new Helpop());
		registerCommand(new Enchant());
		registerCommand(new ChangeName());
		registerCommand(new ChangeLore());
		registerCommand(new Help());
		registerCommand(new Gamemode());
		registerCommand(new Motd());
		registerCommand(new Broadcast());
		registerCommand(new Time());
		registerCommand(new Weather());
	}
	
	private void registerEvents()
	{
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new ServerList(), this);
		pm.registerEvents(new Join(), this);
		pm.registerEvents(new Leave(), this);
	}
	
	public static Chat getChat()
	{
		return chat;
	}
	
	protected boolean registerCommand(Command command)
	{
	  if (cmdMap == null) {
	     try {
	        Field field = SimplePluginManager.class.getDeclaredField("commandMap");
	        field.setAccessible(true);
	        cmdMap = (CommandMap)field.get(Bukkit.getServer().getPluginManager());
	     }
	     catch (Exception e) {
	        e.printStackTrace();
	        return false;
	     }
	 }

	 this.cmds.add(command);
	 cmdMap.register("", command);
	 return true;
	 }
	
}