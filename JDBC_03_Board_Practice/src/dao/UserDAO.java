package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAO {
	
//DBCP 쓰는 걸로 리팩토링
	
	
	
	
	private static UserDAO instance;
	
	public static UserDAO getInstance() {
		
		if(instance == null) {
			instance = new UserDAO();
		}
		return instance;
		
	}
	
	private Connection getConnection() throws SQLException {
		return SingleToneDataSource.getConnection();
	}
	
	public boolean hasOtherUsers() throws SQLException {
		String query = "select * from user_list";
		
		try(
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				){
			
			return rs.next();

		}
		
	}
	
	
	public boolean login(String id, String pw) throws SQLException {
		String query = "select * from User_list where id=? and pw=?";
		
		try(
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				
				){
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
			
			try(ResultSet rs = pstmt.executeQuery()){
				return rs.next();
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
	
	public boolean searchById(String id) throws SQLException {
		
		String query = "select * from User_list where id=?";
		
		try (
				Connection conn = getConnection();
				PreparedStatement pstat = conn.prepareStatement(query);
				
		){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}
		}
		
	}
	
	
	public int addUser(String id, String pw, String name) throws SQLException {
		
		String query = "insert into user_list values(?, ?, ?)";
		try (
				Connection conn = getConnection();
				PreparedStatement pstat = conn.prepareStatement(query);
		){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			pstat.setString(3, name);
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	
	
	
}
