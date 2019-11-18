package me.pedrocodes.bans.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pedrocodes.bans.api.BansAPI;
import me.pedrocodes.bans.api.BansAPI.Causes;

public class ComandosBanip implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("banip")) {
			if(!sender.hasPermission("maing.bans")) {
				sender.sendMessage("§cVocê não tem permissão.");
				return true;
			}
			if(args.length < 2) {
				sender.sendMessage("§cUse /banip <jogador> <motivo>.");
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
	        
	        if(sender instanceof Player) {
	        	if(target == (Player)sender) {
	        		sender.sendMessage("§cVocê não banir seu propio IP.");
	        		return true;
	        	}
	        }
	        
	        if(!BansAPI.estaBanido(target.getAddress().getHostString(), Causes.BANIP)) {
	        	BansAPI.banir(target.getAddress().getHostString(), sender.getName(), motivo, Causes.BANIP);
	        	sender.sendMessage("§aVocê baniu o IP de " + target.getName() + "( " + target.getAddress().getHostString() + ")§c.");
	        	for(Player jogadores : Bukkit.getOnlinePlayers()) {
	        		if(jogadores.getAddress().getHostString().equals(target.getAddress().getHostString())) {
	        			jogadores.kickPlayer("\n§cCaro " + target.getName() +  ", seu ip foi banido permanente do servidor.\n\n§cStaff: " + sender.getName() + "\n§cMotivo: " + motivo + "\n§cIP: " + target.getAddress().getHostString() +  "\n\n§cAcesse nosso web-site para revogar o seu banimento ou até mesmo adquirir um desbanimento.");
	        			target.kickPlayer("\n§cCaro " + target.getName() +  ", seu ip foi banido permanente do servidor.\n\n§cStaff: " + sender.getName() + "\n§cMotivo: " + motivo + "\n§cIP: " + target.getAddress().getHostString() +  "\n\n§cAcesse nosso web-site para revogar o seu banimento ou até mesmo adquirir um desbanimento.");
	        		}
	        	}
	        	Bukkit.broadcastMessage("");
        		Bukkit.broadcastMessage(" §cO IP de " + target.getName() + "(" + target.getAddress().getHostString() + ") §cfoi banido por §c" + sender.getName() + "§c.");
        		Bukkit.broadcastMessage(" §cMotivo: " + motivo);
        		Bukkit.broadcastMessage("");
	        }else {
	        	sender.sendMessage("§cO IP do jogador já está banido.");
	        }
		}
		return false;
	}

}
