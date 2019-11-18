package me.pedrocodes.bans;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.pedrocodes.bans.api.Conexao;
import me.pedrocodes.bans.comandos.ComandoBan;
import me.pedrocodes.bans.comandos.ComandoKick;
import me.pedrocodes.bans.comandos.ComandoUnban;
import me.pedrocodes.bans.comandos.ComandoUnbanip;
import me.pedrocodes.bans.comandos.ComandoVerip;
import me.pedrocodes.bans.comandos.ComandosBanip;
import me.pedrocodes.bans.eventos.LoginEvent;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	public static Main getInstance() {
		return instance;
	}
	
	public void onEnable() {
		instance = this;
		
		Conexao.abrirConexao();
		Conexao.carregarTabelas();
		
		registrarComandos();
		registrarEventos();
		
		getLogger().info("codeBans - Plugin ativado com sucesso.");
	}
	
	public void onDisable() {
		Conexao.fecharConexao();
		
		instance = null;
		getLogger().info("codeBans - Plugin desativado com sucesso.");
	}
	
	public void registrarComandos() {
		getCommand("ban").setExecutor(new ComandoBan());
		getCommand("kick").setExecutor(new ComandoKick());
		getCommand("banip").setExecutor(new ComandosBanip());
		getCommand("unban").setExecutor(new ComandoUnban());
		getCommand("unbanip").setExecutor(new ComandoUnbanip());
		getCommand("verip").setExecutor(new ComandoVerip());
	}
	
	public void registrarEventos() {
		Bukkit.getPluginManager().registerEvents(new LoginEvent(), this);
	}
	
}
