package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import dto.MovieDTO;


//DBMS에 동시 접속자가 100명 이상일 경우
//DBMS 가 뻗는 현상 발생 가능성 있음
// DBCP (DataBase Connection Pool): 미리 만들어둔 Connection 인스턴스를
//접속자에게 대여하고 반환받는 매커니즘으로, DBMS에 도달하는 충격을 완화하는 버퍼 라이브러리


public class MovieDAO {
	
	private BasicDataSource bds = new BasicDataSource(); //DBCP 인스턴스 생성.
	
	
	public static MovieDAO instance;
	
	public synchronized static MovieDAO getInstance() {
		if(instance == null) {
			instance = new MovieDAO();
		}
		return instance;
	}
	
	private MovieDAO() {
		bds.setUsername("kedu");
		bds.setPassword("kedu");
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setInitialSize(10); //DBCP 인스턴스 초기사이즈 설정 
	}
	
	private Connection getConnection() throws SQLException {

		return bds.getConnection();
	}
	
	
//	private Connection getConnection() throws SQLException {
//		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
//		String dbId = "kedu";
//		String dbPw = "kedu";
//	
//		return DriverManager.getConnection(dbUrl, dbId, dbPw);
//	
//	}
	
	
	public List<MovieDTO> selectAllMovie() throws SQLException{
		

		String sqlQuery= "SELECT * FROM Movie_list";
		
		
		
		try(Connection conn = getConnection();
				Statement statement = conn.createStatement();
				
				PreparedStatement pstat = conn.prepareStatement(sqlQuery);
				ResultSet result = pstat.executeQuery();
				){
			List<MovieDTO> movies= new ArrayList<>();
			
			while(result.next()) {
				
				int id = result.getInt("id");
				String movieName = result.getString("title");
				String movieGenre = result.getString("genre");
				movies.add(new MovieDTO(id, movieName, movieGenre));
			}
			
			return movies;
		}

	}
	
	public int getMovieListSize() throws SQLException {
		String sqlQuery= "SELECT count(*) from movie_list";
		

		
		try(Connection conn = getConnection();
			
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				ResultSet result = pstmt.executeQuery();
				){
			result.next();
			return result.getInt(1);
		}
		
	}
	
	
	public boolean hasId(int id) throws SQLException {
		
		String query= "SELECT * from movie_list where id=?";
		
		try(Connection conn = getConnection();
			PreparedStatement pstat = conn.prepareStatement(query);

				){
			pstat.setInt(1, id);
			try(ResultSet result =   pstat.executeQuery()){
				return result.next();
			}
			//result.next가 있다는건, 데이터가 존재한다는것이므로 hasID 가 true
			//result.next가 없다는건, 데이터가 없다는 것이므로, hasID도 false
			
		}
	}

	
	public int deleteByID(int id) throws SQLException {
		
		String query = "delete from Movie_list where id=?";
		
		
		try( 	Connection conn = getConnection();
				PreparedStatement pstat = conn.prepareStatement(query);
				
			){
			
			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			return result;
			
		}
	}
	
	public int updateMovie(MovieDTO dto) throws SQLException {
		
		String query = "Update Movie_list set title= ?,genre=? where id =?";
		try( 	Connection conn = getConnection();
				//Statement statement = conn.createStatement();
				PreparedStatement pstat = conn.prepareStatement(query)
			){
			
			
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getGenre());
			pstat.setInt(3,dto.getId());
			
			int result = pstat.executeUpdate();
			return result;
			
		}
		
		
	}

	public int addMovie(MovieDTO dto) throws SQLException {
		
//		try( 	Connection conn = getConnection();
//				Statement statement = conn.createStatement();
//				
//			){
//			
//			String sqlString = String.format("insert into Movie_list values( movie_seq.NEXTVAL, '%s', '%s')",dto.getTitle(),dto.getGenre() );
//			int result = statement.executeUpdate(sqlString);
//			return result;
//			
//		}
//		
		//?는 placeholder - 파라미터 자리 표시자로, 후에 값이 들어갈 부분을 나타냅니다
		String sql = "insert into Movie_list values( movie_seq.NEXTVAL, ?, ?)";
		try(	Connection conn = this.getConnection();
				PreparedStatement pstat = conn.prepareStatement(sql);
				){
			
				
				pstat.setString(1, dto.getTitle());
				pstat.setString(2, dto.getGenre());
				int result = 	pstat.executeUpdate();
				return result;
		}
	}
	

}
