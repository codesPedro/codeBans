package me.pedrocodes.bans.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import me.pedrocodes.bans.Main;

public class Conexao {
	
private static Connection con = null;
	
	public static void carregarTabelas() {
		if(getConnection() != null) {
			try {
				PreparedStatement stBans = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `bans` (`jogador` VARCHAR(32), `autor` VARCHAR(240), `motivo` TEXT, `data` TEXT);");
				stBans.execute();
				PreparedStatement stIps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `bansip` (`ip` VARCHAR(32), `autor` VARCHAR(240), `motivo` TEXT, `data` TEXT);");
				stIps.execute();
				Main.getInstance().getLogger().info("codeBans - Tabelas MySQL carregadas.");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void abrirConexao() {
		String host = "localhost";
		String port = "3306";
		String database = "codeBans";
		String type = "jdbc:mysql://";
		String url = type + host + ":" + port + "/" + database;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "root");
	        properties.setProperty("password", "");
			con = DriverManager.getConnection(url, properties);
			
			Main.getInstance().getLogger().info("codeBans - Conexão com MYSQL estabelecida.");
		}catch(SQLException e) {
			e.printStackTrace();
			Main.getInstance().getLogger().info("codeBans - Erro ao estabelecer Conexão com MYSQL.");
		}
	}
	
	public static void fecharConexao() {
		if(getConnection() != null) {
			try {
				getConnection().close();
				Main.getInstance().getLogger().info("codeBans - Conexão com MYSQL fechada com sucesso.");
			}catch(SQLException e) {
				e.printStackTrace();
				Main.getInstance().getLogger().info("codeBans - Erro ao fechar Conexão com MYSQL.");
			}
		}
	}
	
	public static Connection getConnection() {
		return con;
	}

}
