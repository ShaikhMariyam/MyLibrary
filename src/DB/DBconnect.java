package DB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/DBconnect")
public class DBconnect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static Connection getConn() {
		Connection conn=null;
        String dbname = "mylibrary";
		String loadDriver ="com.mysql.jdbc.Driver"; 
		String dbURL ="jdbc:mysql://localhost:3306/"+dbname+"?"; 
		String dbUSERNAME ="root"; 
		String dbPASSWORD =""; 

		try {
			Class.forName(loadDriver); 
			conn = DriverManager.getConnection(dbURL, dbUSERNAME, dbPASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}