package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class jdbc {
	public static void main(String args[])throws ClassCastException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
}
