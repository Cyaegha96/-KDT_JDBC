
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Exam01 {
	public static void main(String[] args) {
		
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbId = "kedu";
		String dbPw = "kedu";
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
			Statement stat = conn.createStatement();
			
			
			
			String sqlString = "insert into cafe_menu values(cafe_seq.nextval, 'Americano', 2500,'y')";
			int result = stat.executeUpdate(sqlString);
			if(result > 0) {
				System.out.println("메뉴 입력완료");
			}
			
			conn.close();
			//세션을 끊는 코드. 
			
			
			
			
			//conn.commit(); 현재 버전의 라이브러리에서는 (8) auto Commit이 활성화 되어 있음
			
			//stat.executeUpdate -update , delete, insert 처럼 DB에 변화를 주는 작업
			
			//stat.executeQuery() - select 처럼 DB에 변화가 없는 작업
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
				
				
	}
}
