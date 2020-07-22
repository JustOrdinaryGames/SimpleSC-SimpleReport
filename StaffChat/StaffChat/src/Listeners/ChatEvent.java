package Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
		String args[] = event.getMessage().split(" ");
		if(args[0].startsWith("#") && args[0].length() == 1) {
			if(event.getPlayer().hasPermission("staffchat.main")) {
				String msg = event.getMessage().replaceFirst("# ", "");
				Bukkit.broadcast(ChatColor.AQUA + "[S]" + ChatColor.DARK_AQUA + "[CHAT]" + event.getPlayer().getName()+ ChatColor.AQUA + ": " + msg, "staffchat.main");
				event.setCancelled(true);
			}
		}
   }
}
