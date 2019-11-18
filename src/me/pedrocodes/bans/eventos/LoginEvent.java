package me.pedrocodes.bans.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.pedrocodes.bans.api.BansAPI;
import me.pedrocodes.bans.api.BansAPI.Causes;

public class LoginEvent implements Listener{
	
	@EventHandler
	public void aoLogar(PlayerLoginEvent e) {
		Player jogador = e.getPlayer();
		if(BansAPI.estaBanido(e.getAddress().getHostAddress(), Causes.BANIP)) {
			e.setResult(Result.KICK_OTHER);
			e.setKickMessage("\n§cCaro " + jogador.getName() +  ", seu ip está banido permanente do servidor.\n\n§cStaff: " + BansAPI.getAutor(e.getAddress().getHostAddress(), Causes.BANIP) + "\n§cMotivo: " + BansAPI.getMotivo(e.getAddress().getHostAddress(), Causes.BANIP) + "\n§cIP: " + e.getAddress().getHostAddress() + "\n\n§cAcesse nosso web-site para revogar o seu banimento ou até mesmo adquirir um desbanimento.");
		}
		if(BansAPI.estaBanido(jogador.getName().toLowerCase(), Causes.BAN)) {
			e.setResult(Result.KICK_OTHER);
			e.setKickMessage("\n§cCaro " + jogador.getName() +  ", você está banido permanente do servidor.\n\n§cStaff: " + BansAPI.getAutor(jogador.getName().toLowerCase(), Causes.BAN) + "\n§cMotivo: " + BansAPI.getMotivo(jogador.getName().toLowerCase(), Causes.BAN) + "\n\n§cAcesse nosso web-site para revogar o seu banimento ou até mesmo adquirir um desbanimento.");
		}
	}

}
