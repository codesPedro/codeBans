package me.pedrocodes.bans.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pedrocodes.bans.api.BansAPI;
import me.pedrocodes.bans.api.BansAPI.Causes;

public class ComandoBan implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ban")) {
			if(!sender.hasPermission("maing.bans")) {
				sender.sendMessage("§cVocê não tem permissão.");
				return true;
			}
			if(args.length < 2) {
				sender.sendMessage("§cUse /ban <jogador> <motivo>.");
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
	        	if(!BansAPI.estaBanido(t.toLowerCase(), Causes.BAN)) {
	        		BansAPI.banir(t.toLowerCase(), sender.getName(), motivo, Causes.BAN);
	        		sender.sendMessage("§aVocê baniu " + t + " pelo motivo " + motivo + ".");
	        		Bukkit.broadcastMessage("");
	        		Bukkit.broadcastMessage(" §c" + t + " §cfoi banido por §c" + sender.getName() + "§c.");
	        		Bukkit.broadcastMessage(" §cMotivo: " + motivo);
	        		Bukkit.broadcastMessage("");
	        	}else {
	        		sender.sendMessage("§cO jogador já está banido.");
	        	}
	        	return true;
	        }
	        
	        if(sender instanceof Player) {
	        	if(target == (Player)sender) {
	        		sender.sendMessage("§cVocê não pode se banir.");
	        		return true;
	        	}
	        }
	        
	        if(!BansAPI.estaBanido(target.getName().toLowerCase(), Causes.BAN)) {
	        	BansAPI.banir(target.getName().toLowerCase(), sender.getName(), motivo, Causes.BAN);
	        	sender.sendMessage("§aVocê baniu " + target.getName() + " pelo motivo " + motivo + ".");
	        	target.kickPlayer("\n§cCaro " + target.getName() +  ", você foi banido permanente do servidor.\n\n§cStaff: " + sender.getName() + "\n§cMotivo: " + motivo + "\n\n§cAcesse nosso web-site para revogar o seu banimento ou até mesmo adquirir um desbanimento.");
	        	Bukkit.broadcastMessage("");
        		Bukkit.broadcastMessage(" §c" + target.getName() + " §cfoi banido por §c" + sender.getName() + "§c.");
        		Bukkit.broadcastMessage(" §cMotivo: " + motivo);
        		Bukkit.broadcastMessage("");
	        }else {
	        	sender.sendMessage("§cO jogador já está banido.");
	        }
		}
		return false;
	}

}
