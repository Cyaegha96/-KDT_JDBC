package dao;

import java.io.DataInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.CafeDTO;

public class CafeDAO {
	
	
	/* 예외 전가는 콜리 메서드가 전가하고, 콜러 메서드가 전가 받음
	 * 
	 */
	
	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbId = "kedu";
		String dbPw = "kedu";
	
		return DriverManager.getConnection(dbUrl, dbId, dbPw);
		
	}
	
	public int update(CafeDTO dto) throws SQLException {
		try(Connection conn = getConnection();
		Statement stat = conn.createStatement();){
			String sqlString = "update cafe_menu set price="+ dto.getPrice()+"where id = "+dto.getId();
			int result = stat.executeUpdate(sqlString);
			
			return result;
		}
		
	}
	
	
	public int insert(CafeDTO dto) throws SQLException {
		//try with resources
		try(	Connection conn = getConnection();
				Statement stat = conn.createStatement(); ){
			//반드시 close 해줌
			String sqlString = "insert into cafe_menu values(cafe_seq.nextval, '"+dto.getNameString()+"', "+dto.getPrice()+",'"+dto.getIceAble()+"')";
			int result = stat.executeUpdate(sqlString);

			return result;
			
		}
	
	}
	
	public int deleteByID(int id) throws SQLException {
		try(Connection conn = getConnection();
		Statement stat = conn.createStatement();){
			String sqlString = "Delete from cafe_menu where id="+id;
			int result = stat.executeUpdate(sqlString);
			return result;
			
		}
		
		
	}
	
	public List<CafeDTO> selectAll() throws Exception{
		
		String sqlString = "select * from cafe_menu";
		
		try(Connection conn = getConnection();
		Statement statement = conn.createStatement();
				ResultSet rs =  statement.executeQuery(sqlString);
				){
		
			List<CafeDTO> list = new ArrayList<>();
			
			
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String nameString = rs.getString(2);
				int price = rs.getInt(3);
				String iceable = rs.getString(4);
				
				CafeDTO dto = new CafeDTO(id, nameString, price, iceable);
				list.add(dto);
			}
			
			
			return list;
		}
		
		
	}
	
	

}
