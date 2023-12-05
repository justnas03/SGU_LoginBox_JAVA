import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Database {
	
	public static final String DB_URL = "jdbc:derby:UserAccountDB";
	
	//Connection and Statement
	public static Connection conn = null;
	public static Statement stm = null;
    
	public Database() throws SQLException {
		TaoConnection();
	}
	
	public static Statement getStatement() {
        return stm;
	}
    public static Connection getConnection() {
        return conn;
    }
    
    public void TaoConnection() throws SQLException {
    	//Create Connection
		conn = DriverManager.getConnection(DB_URL);
		stm = conn.createStatement();
		System.out.println("Connection to UserAccountDB is created.");	
    }
    
	public static Connection createConnection() throws SQLException{
			//Create Connection
			conn = DriverManager.getConnection(DB_URL);
			stm = conn.createStatement();
			System.out.println("Connection to UserAccountDB is created.");	
		return conn;
    }
	
	public static void Insert(int ID, String username, String password, String tablename) throws SQLException{
            stm.executeUpdate("insert into "+tablename+" values ("+ID+",'"+username+"','"+password+"')");
    }
	
    public static void DropTable(String tablename) throws SQLException{
    		stm.executeUpdate("DROP TABLE " + tablename +";");
    }
    
    public static void DeleteData(String tablename, String dieukien) throws SQLException{
        	stm.executeUpdate("DELETE FROM " + tablename + " WHERE " + dieukien +";");
    }
    
    public static ResultSet Select(String selection, String tablename, String dieukien) throws SQLException {
    	ResultSet result = stm.executeQuery("SELECT "+ selection +" FROM "+ tablename + "WHERE " + dieukien);
        return result;
    }
    
    public static void Close(Connection con) throws SQLException {
        con.close();
    }
	
	

	
}
