package me.pedrocodes.bans.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoVerip implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("verip")) {
			if(!sender.hasPermission("maing.bans")) {
				sender.sendMessage("�cVoc� n�o tem permiss�o.");
				return true;
			}
			
			if(args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null) {
					sender.sendMessage("�cEste jogador est� offline.");
					return true;
				}
				
				if(sender instanceof Player) {
					if(target == (Player)sender) {
						sender.sendMessage("�eSeu IP � " + ((Player)sender).getAddress().getHostString() + "�e.");
						return true;
					}
				}
				
				sender.sendMessage("�eO IP de " + target.getName() + " � " + target.getAddress().getHostString() + "�e.");
				return true;
			}else {
				sender.sendMessage("�cUse /verip <jogador>.");
			}
		}
		return false;
	}
	

}
