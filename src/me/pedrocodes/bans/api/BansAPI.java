package me.pedrocodes.bans.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BansAPI extends Conexao{
	
	public static enum Causes{
		BAN, BANIP, KICK;
	}
	
	public static void banir(String direction, String autor, String motivo, Causes cause) {
		switch(cause) {
		case BAN : {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("INSERT INTO `bans` VALUES (?, ?, ?, ?);");
					st.setString(1, direction);
					st.setString(2, autor);
					st.setString(3, motivo);
					Date data = new Date();
			        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm:ss");
			        st.setString(4, dataFormat.format(data));
			        st.execute();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		}
		case BANIP : {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("INSERT INTO `bansip` VALUES (?, ?, ?, ?);");
					st.setString(1, direction);
					st.setString(2, autor);
					st.setString(3, motivo);
					Date data = new Date();
			        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm:ss");
			        st.setString(4, dataFormat.format(data));
			        st.execute();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		}
		case KICK : {
			break;
		}
		}
	}
	
	public static void desbanir(String direction, Causes cause) {
		if(cause == Causes.BAN) {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("DELETE FROM `bans` WHERE `jogador` = ?;");
					st.setString(1, direction);
					st.execute();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}else if(cause == Causes.BANIP) {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("DELETE FROM `bansip` WHERE `ip` = ?;");
					st.setString(1, direction);
					st.execute();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getAutor(String direction, Causes cause) {
		String autor = "Nenhum";
		if(cause == Causes.BAN) {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("SELECT * FROM `bans` WHERE `jogador` = ?;");
					st.setString(1, direction);
					ResultSet rs = st.executeQuery();
					if(rs.next()) {
						autor = rs.getString("autor");
					}
					rs.close();
					st.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}else if(cause == Causes.BANIP) {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("SELECT * FROM `bansip` WHERE `ip` = ?;");
					st.setString(1, direction);
					ResultSet rs = st.executeQuery();
					if(rs.next()) {
						autor = rs.getString("autor");
					}
					rs.close();
					st.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return autor;
	}
	
	public static String getMotivo(String direction, Causes cause) {
		String motivo = "Nenhum";
		if(cause == Causes.BAN) {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("SELECT * FROM `bans` WHERE `jogador` = ?;");
					st.setString(1, direction);
					ResultSet rs = st.executeQuery();
					if(rs.next()) {
						motivo = rs.getString("motivo");
					}
					rs.close();
					st.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}else if(cause == Causes.BANIP) {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("SELECT * FROM `bansip` WHERE `jogador` = ?;");
					st.setString(1, direction);
					ResultSet rs = st.executeQuery();
					if(rs.next()) {
						motivo = rs.getString("motivo");
					}
					rs.close();
					st.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return motivo;
	}
	
	public static boolean estaBanido(String direction, Causes cause) {
		boolean banido = false;
		if(cause == Causes.BAN) {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("SELECT * FROM `bans` WHERE `jogador` = ?;");
					st.setString(1, direction);
					ResultSet rs = st.executeQuery();
					if(rs.next()) {
						return true;
					}
					rs.close();
					st.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}else if(cause == Causes.BANIP) {
			if(getConnection() != null) {
				try {
					PreparedStatement st = getConnection().prepareStatement("SELECT * FROM `bansip` WHERE `ip` = ?;");
					st.setString(1, direction);
					ResultSet rs = st.executeQuery();
					if(rs.next()) {
						return true;
					}
					rs.close();
					st.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return banido;
	}

}
