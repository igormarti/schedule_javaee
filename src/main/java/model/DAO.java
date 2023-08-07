package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO  {
	
	protected static String driver = "com.mysql.cj.jdbc.Driver";
	protected static String url = "jdbc:mysql://127.0.0.1:3306/agenda?useTimezone=true&serverTimezone=UTC";
	protected static String user = "root";
	protected static String password = "root";
	
	Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return con;
		}
	}
	
	public void inserirContato(JavaBeans contato) {
		String sql =  "INSERT INTO contatos(nome,fone,email) VALUES(?,?,?)";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public Boolean atualizarContato(JavaBeans contato) {
		String sql =  "UPDATE contatos SET nome=?, fone=?, email=? WHERE idcon=?";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());
			ps.setInt(4, contato.getIdcon());
			Boolean retorno =  ps.executeUpdate() > 0 ? true: false;
			con.close();
			return retorno;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}
	
	public Boolean deletarContato(Integer id) {
		String sql =  "DELETE FROM contatos WHERE idcon=?";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			Boolean retorno =  ps.executeUpdate() > 0 ? true: false;
			con.close();
			return retorno;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}
	
	public ArrayList<JavaBeans> listarContatos(){
		String sql = "SELECT * FROM contatos order by nome";
		
		try {
			ArrayList<JavaBeans> contatos = new ArrayList<JavaBeans>();
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer idcon = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	
	public JavaBeans pegarContatoPorID(Integer idcon) {
		String sql = "SELECT * FROM contatos WHERE idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idcon);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				return new JavaBeans(rs.getInt("idcon"),rs.getString("nome"), rs.getString("fone"), rs.getString("email"));
			}
			
			return null;
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	
}
