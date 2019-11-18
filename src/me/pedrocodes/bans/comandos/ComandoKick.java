package me.pedrocodes.bans.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoKick implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("kick")) {
			if(!sender.hasPermission("maing.bans")) {
				sender.sendMessage("§cVocê não tem permissão.");
				return true;
			}
			if(args.length < 2) {
				sender.sendMessage("§cUse /kick <jogador> <motivo>.");
				return true;
			}
			
			String t = args[0];
			Player target = Bukkit.getPlayer(t);
			StringBuilder sb = new StringBuilder();
	        for (int i = 1; i < args.length; i++) {
	          sb.append(args[i]).append(" ");
	        }
	        String motivo = sb.toString().trim();
	        
	        if(target == null) {
	        	sender.sendMessage("§cEste jogador está offline.");
	        	return true;
	        }
	        
	        sender.sendMessage("§aVocê kikcou o jogador " + target.getName() + "§a.");
	        target.kickPlayer("\n§cVoce foi kickado do servidor\n\n§cStaff: " + sender.getName() + "\n§cMotivo: " + motivo);
	        Bukkit.broadcastMessage("");
    		Bukkit.broadcastMessage(" §c" + target.getName() + " §cfoi kickado por §c" + sender.getName() + "§c.");
    		Bukkit.broadcastMessage(" §cMotivo: " + motivo);
    		Bukkit.broadcastMessage("");
		}
		return false;
	}

}
