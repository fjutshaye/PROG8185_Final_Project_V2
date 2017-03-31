package tjiang4966.prog8185.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTools {
	
	private static final String DATABASE_PASSWORD = "shaye3100205113";
	private static final String DBURL = "jdbc:sqlserver://SQL5034.SmarterASP.NET";
	private static final String DBUSERID = "DB_A19B67_fjutshaye_admin";
	
	public static final String SQL_CHART_1_FILTER_1 = 
			"SELECT COUNT(*) AS 'Number of Employee(s)',Title"
			+ " FROM Employee "
			+ "GROUP BY Title";
	public static final String SQL_CHART_1_FILTER_2 = 
			"SELECT COUNT(*) AS 'Number of Employee(s)',DATEPART(yyyy, HireDate) "
			+ "FROM Employee "
			+ "GROUP BY DATEPART(yyyy, HireDate)";
	public static final String SQL_CHART_1_FILTER_3 =
			"SELECT COUNT(*) AS 'Number of Employee(s)',City"
			+ " FROM Employee"
			+ " GROUP BY City";
	public static final String SQL_CHART_2_FILTER_1 =
			"SElECT COUNT(*) AS UnitPriceCount, UnitPrice "
			+ "FROM Track "
			+ "GROUP BY UnitPrice";
	public static final String SQL_CHART_2_FILTER_2 =
			"SElECT TOP 5 COUNT(*) GenreCount, Genre.Name "
			+ "FROM Track LEFT OUTER JOIN Genre "
			+ "ON Track.GenreId = Genre.GenreId "
			+ "GROUP BY Genre.Name "
			+ "ORDER BY GenreCount DESC";
	public static final String SQL_CHART_2_FILTER_3 =
			"SELECT TOP 5 COUNT(*) AS ArtistCount,"
			+ " T2.Name FROM Track AS T1,"
			+ " (	SELECT A1.AlbumId, A1.ArtistId, A2.Name	FROM Album AS A1, Artist AS A2 WHERE A1.ArtistId = A2.ArtistId)"
			+ " AS T2 "
			+ "WHERE T1.AlbumId = T2.AlbumId"
			+ " GROUP BY T2.Name ORDER BY ArtistCount DESC";
	public static Connection connectToSqlServer(){
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(DBURL, DBUSERID, DATABASE_PASSWORD);
			System.out.println("Established Database Connection");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Database Connection Established");
		return connection;
	}
}
