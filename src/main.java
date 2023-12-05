import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class main {
	
	public static void main(String[] args) {
		
		//LoginBox demo = new LoginBox();
		final String DB_URL = "jdbc:derby:UserAccountDB";
		
		try {
			//Create Connection
			Connection conn = DriverManager.getConnection(DB_URL);
			System.out.println("Connection to UserAccountDB is created.");
			
			Statement stm = conn.createStatement();
			
			String createTable = "CREATE TABLE Account( "
			         + "id INT NOT NULL, "
			         + "username VARCHAR(255), "
			         + "password VARCHAR(255), "
			         + "PRIMARY KEY (id))";
			//stm.execute(createTable);

			System.out.println("Table Created");
			//inserting value
//				stm.executeUpdate("insert into Account values (1,'user1','user1')");
//				stm.executeUpdate("insert into Account values (2,'user2','user2')");
//				stm.executeUpdate("insert into Account values (3,'user3','user3')");
//				stm.executeUpdate("insert into Account values (4,'user4','user4')");
//			System.out.println("Inserted Value");
//			
			//getValue
			String getTable = "SELECT * FROM Account";
			ResultSet result = stm.executeQuery(getTable);
			
			//display
//			while(result.next()) {
//				System.out.println("username: "+ result.getString("username"));
//				System.out.println("password: "+ result.getString("password"));
//			}
//			
//			
			//Close Connection
			stm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			//System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}
}
