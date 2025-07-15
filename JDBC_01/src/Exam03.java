import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Exam03 {
	public static void main(String[] args) {
		
		//Cafe Mocha를 삭제하는 코드 작성
		
		String urlString = "jdbc:oracle:thin:@10.5.5.13:1521:xe";
		String idString= "kedu";
		String pwString = "kedu";
		
		try {
			Connection connection = DriverManager.getConnection(urlString,idString,pwString);
			Statement statement=  connection.createStatement();
			
			String query=  "Delete from cafe_menu where name='Cafe Mocha'";
			int result = statement.executeUpdate(query);
			
			if(result > 0) {
				System.out.println("쿼리 입력 성공");
			}else if(result == 0) {
				System.out.println("삭제한 쿼리의 데이터가 0줄입니다.");
			}
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
