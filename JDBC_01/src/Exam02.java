import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Exam02 {
	public static void main(String[] args) {
		
		
		//cafe 테이블의 Americano 가격을 2000원으로 변경하는 코드 작성
		
		
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id ="kedu";
		String pw = "kedu";
		
		try {
			Connection conn = DriverManager.getConnection(url, id,pw);
			Statement statement = conn.createStatement();
			
			//String sql = "update cafe_menu set price=2000 where id=1009";
			String sql = "update cafe_menu set price=2600 where name='Americano'";
			int result = statement.executeUpdate(sql);
			
			if(result >0) {
				System.out.println("쿼리 실행 완료");
			}
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
				
		
		
	}
}
