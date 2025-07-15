import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exam04 {
	public static void main(String[] args) {

		String urlString = "jdbc:oracle:thin:@localhost:1521:xe";
		String idString = "kedu";
		String pwString = "kedu";

		try {
			Connection connection = DriverManager.getConnection(urlString, idString, pwString);
			Statement statement = connection.createStatement();

			String queryString = "select * from cafe_menu";
			

			//connection이 유지될 동안만 쿼리값을 꺼내 올 수 있는 포인터
			ResultSet result = statement.executeQuery(queryString);

			while (result.next()) {
				int id = result.getInt("id");
				String nameString = result.getString("name");
				int price = result.getInt("price");
				String iceAble = result.getString("iceable");

				System.out.println(id + ":" + nameString + ":" + price + ":" + iceAble);
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
