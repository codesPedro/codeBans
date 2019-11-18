package me.pedrocodes.bans.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.pedrocodes.bans.api.BansAPI;
import me.pedrocodes.bans.api.BansAPI.Causes;

public class ComandoUnbanip implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("unbanip")) {
			if(!sender.hasPermission("maing.bans")) {
				sender.sendMessage("�cVoc� n�o tem permiss�o.");
				return true;
			}
			
			if(args.length == 1) {
				String t = args[0];
				
				if(BansAPI.estaBanido(t.toLowerCase(), Causes.BANIP)) {
					BansAPI.desbanir(t.toLowerCase(), Causes.BANIP);
					sender.sendMessage("�aVoc� desbaniu o IP " + t + "�a.");
				}else {
					sender.sendMessage("�cEste jogador n�o est� banido.");
				}
			}else {
				sender.sendMessage("�cUse /unbanip <ip>.");
			}
		}
		return false;
	}

}
