package Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Orders {

	public static Connection conn;
	public static Statement stmnt;

	public static void orders() throws SQLException {
		conn = DriverManager.getConnection(Connect.URL, Connect.USER, Connect.PASSWORD);
		stmnt = conn.createStatement();
	}

	public void createOrders(int OID, int fk_CID, int fk_PID, int Quantity) throws SQLException {
		orders();
		stmnt = conn.createStatement();
		String sql = "INSERT INTO Orders (OID, fk_CID, fk_PID, quantity) VALUES (" + OID + ", \"" + fk_CID + "\", \""
				+ fk_PID + "\", \"" + Quantity + "\")";
		stmnt.executeUpdate(sql);
	}

	public void readOrders() throws SQLException {
		orders();
		stmnt = conn.createStatement();
		String sql = "SELECT * FROM orders";
		ResultSet results = Orders.stmnt.executeQuery(sql);
		while (results.next()) {
			System.out.println(results.getInt("OID") + " " + results.getInt("fk_CID") + " " + results.getInt("fk_PID")
					+ " " + results.getInt("quantity"));
			stmnt.executeUpdate(sql);
		}
	}

	public String orderCost(int OID) throws SQLException {
		orders();

		ResultSet rs = stmnt.executeQuery(
				"SELECT (product.price * orders.quantity) AS orderCost FROM product, orders WHERE product.PID = orders.fk_PID AND OID = "
						+ OID);
		while (rs.next()) {
			String test = rs.getString("orderCost");
			System.out.println(test);
		}
		return null;
	}

	public void close() throws SQLException {
		conn.close();
	}
}
