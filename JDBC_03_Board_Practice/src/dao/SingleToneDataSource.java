package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;



public class SingleToneDataSource {
	
	private static BasicDataSource bds;
	
	private SingleToneDataSource() {};
	
	public static synchronized Connection getConnection() throws SQLException {
		if(bds ==null) {
			bds = new BasicDataSource();

			bds.setUsername("kedu");
			bds.setPassword("kedu");
			bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			bds.setInitialSize(10); //DBCP 인스턴스 초기사이즈 설정 
			
		}
		return bds.getConnection();
	}
	
	
	

}
