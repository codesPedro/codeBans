package me.pedrocodes.bans.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pedrocodes.bans.api.BansAPI;
import me.pedrocodes.bans.api.BansAPI.Causes;

public class ComandoUnban implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("unban")) {
			if(!sender.hasPermission("maing.bans")) {
				sender.sendMessage("§cVocê não tem permissão.");
				return true;
			}
			
			if(args.length == 1) {
				String t = args[0];
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null) {
					if(BansAPI.estaBanido(t.toLowerCase(), Causes.BAN)) {
						BansAPI.desbanir(t.toLowerCase(), Causes.BAN);
						sender.sendMessage("§aVocê desbaniu o jogador " + t + "§a.");
					}else {
						sender.sendMessage("§cEste jogador não está banido.");
					}
					return true;
				}
				
				if(BansAPI.estaBanido(target.getName().toLowerCase(), Causes.BAN)) {
					BansAPI.desbanir(target.getName().toLowerCase(), Causes.BAN);
					sender.sendMessage("§aVocê desbaniu o jogador " + target.getName() + "§a.");
				}else {
					sender.sendMessage("§cEste jogador não está banido.");
				}
			}else {
				sender.sendMessage("§cUse /unban <jogador>.");
			}
		}
		return false;
	}

}
