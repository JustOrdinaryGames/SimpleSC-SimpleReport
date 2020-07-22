package StaffChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import Listeners.*;

public class Main extends JavaPlugin {

	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ChatEvent(), this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sc") || cmd.getName().equalsIgnoreCase("staffchat")) {
			if(sender instanceof Player && args.length >= 1) {
				if(sender.hasPermission("staffchat.main")) {
					for(Player nerd : Bukkit.getOnlinePlayers()) {
						if(nerd.hasPermission("staffchat.main")) {
							StringBuilder buffer = new StringBuilder();
							buffer.append(args[0]);
							for(int i = 1; i < args.length; i++)
							{
							    buffer.append(' ').append(args[i]);
							}
							String msg = buffer.toString();
							nerd.sendMessage(ChatColor.AQUA + "[S]" + ChatColor.DARK_AQUA + "[CHAT]" + sender.getName()+ ChatColor.AQUA + ": " + msg);
							return true;
						}
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("report")) {
			if(sender instanceof Player && args.length >= 2) {
				if(Bukkit.getPlayer(args[0]).isOnline()) {
					sender.sendMessage(ChatColor.GREEN + "Thanks for reporting this, we will review the case shortly, remember abuse of this commmand is " + ChatColor.RED + "bannable!");
					StringBuilder buffer = new StringBuilder();
					buffer.append(args[1]);
					for(int i = 2; i < args.length; i++)
					{
						buffer.append(' ').append(args[i]);
					}
					String msg = buffer.toString();			
					Bukkit.broadcast(ChatColor.AQUA + "[S]" + ChatColor.DARK_AQUA + "[REPORT]" + ChatColor.GREEN + sender.getName()+ ChatColor.AQUA + " has reported " + ChatColor.GREEN + args[0] + ChatColor.AQUA + " for " + ChatColor.RED + msg + ChatColor.AQUA + ".", "staffchat.report");
					return true;
				}
			}else if(sender instanceof Player && args.length <= 2) {
				sender.sendMessage(ChatColor.RED + "Incorrect Command Usage, Usage: " + ChatColor.GREEN + "/report <User> <Reason>");
			}
		}
		return false;
	}
	
}
