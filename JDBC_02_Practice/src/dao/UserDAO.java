package dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.dbcp2.BasicDataSource;

public class UserDAO {
	
//DBCP 쓰는 걸로 리팩토링
	
	private BasicDataSource bds =new BasicDataSource();
	
	
	private static UserDAO instance;
	
	public static UserDAO getInstance() {
		
		if(instance == null) {
			instance = new UserDAO();
		}
		return instance;
		
	}
	
	private UserDAO() {
		bds.setUsername("kedu");
		bds.setPassword("kedu");
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setInitialSize(10); //DBCP 인스턴스 초기사이즈 설정 
		
	}
	
	
	
	
	private Connection getConnection() throws SQLException {
		return bds.getConnection();
	}
	
	public boolean hasOtherUsers() throws SQLException {
		String query = "select * from user_list";
		
		try(
				Connection conn = getConnection();
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(query);
				){
			
			return rs.next();

		}
		
	}
	
	
	public String login(String id, String pw) throws SQLException {
		String query = String.format("select name from User_list where id='%s' and pw='%s'" ,id, pw);
		
		try(
				Connection conn = getConnection();
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(query);
				){
			
			if(rs.next()) {
				return rs.getString("name");
			}
			else {
				return null;
			}
			
			
		}
	}
	
	
	public boolean incorrectName(String name) {
		if(name.length() > 10) return false;
		
		//
		String bannedPattern = "(\\*|--|;|'|\")";
		Pattern pattern = Pattern.compile(bannedPattern);
		Matcher matcher = pattern.matcher(name);
		    if (matcher.find()) {
		        return true;
		    }
		if(name.equals("kedu") || name.contains("admin")){
			return true;
		}
		
		return false;
	}
	
	public boolean incorrectId(String id) {
		
		//아이디의 문자열이 DB에 기록된 문자열보다 큰경우
		if(id.length() > 30) return false;
		
		//
		String bannedPattern = "(\\*|--|;|'|\")";
		Pattern pattern = Pattern.compile(bannedPattern);
		Matcher matcher = pattern.matcher(id);
		    if (matcher.find()) {
		        return true;
		    }

		if(id.equals("kedu") || id.contains("admin")){
			return true;
		}
		
		return false;
		
	}
	
	public boolean hasId(String id) throws SQLException {
		
		String query = "select * from User_list where id=?";
		
		try (
				Connection conn = getConnection();
				PreparedStatement pstat = conn.prepareStatement(query);
				
		){
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}
		}
		
	}
	
	
	public int addUser(String id, String pw, String name) throws SQLException {
		try (
				Connection conn = getConnection();
				Statement stat = conn.createStatement();
		){
			
			String queryString = String.format("insert into user_list values('%s', '%s', '%s')",id, pw, name );
			int result = stat.executeUpdate(queryString);
			return result;
		}
	}
	
	
	
	
}
