package Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Orders {

	public static Connection conn;
	public Statement stmnt;

	public void orders() throws SQLException {
		conn = DriverManager.getConnection(Connect.URL, Connect.USER, Connect.PASSWORD);
		stmnt = conn.createStatement();
	}

	public void createOrders(int OID, int fk_CID, int fk_PID, int Quantity) throws SQLException {
		orders();
		this.stmnt = conn.createStatement();
		String sql = "INSERT INTO Orders (OID, fk_CID, fk_PID) VALUES (" + OID + ", \"" + fk_CID + "\", \"" + fk_PID
				+ "\", \"" + Quantity + "\")";
		stmnt.executeUpdate(sql);
	}

	public void readOrders() throws SQLException {
		orders();
		this.stmnt = conn.createStatement();
		String sql = "SELECT * FROM orders";
		ResultSet results = this.stmnt.executeQuery(sql);
		while (results.next()) {
			System.out.println(results.getInt("OID") + " " + results.getInt("fk_CID") + " " + results.getInt("fk_PID"));
		}
	}

	public void orderCost(int OID) throws SQLException {
		ResultSet rs = stmnt
				.executeQuery("SELECT (product.price * orders.quantity) AS orders_total FROM product, orders"
						+ "WHERE product.PID = ordrs.PID AND OID=" + OID);
		while (rs.next()) {
			String test = rs.getString("totalCost");
			System.out.println(test);
		}
	}

	public void close() throws SQLException {
		conn.close();
	}
}
