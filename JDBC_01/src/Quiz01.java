import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Quiz01 {
	public static void main(String[] args) {
		String urlString = "jdbc:oracle:thin:@10.5.5.13:1521:xe";
		String idString= "kedu";
		String pwString = "kedu";
		
		try {
			Connection connection = DriverManager.getConnection(urlString,idString,pwString);
			Statement statement=  connection.createStatement();
			
			String queryString = "select emp_id, emp_name,phone from employee";
			
			ResultSet result =statement.executeQuery(queryString);
			
			while(result.next()) {
				int emp_no = result.getInt("emp_id");
				String emp_name = result.getString("emp_name");
				String phoneString = result.getString("phone");
				
				System.out.println(emp_no +":" + emp_name + ":" + phoneString);
			}
			
			result.close();
			statement.close();
			connection.close();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
