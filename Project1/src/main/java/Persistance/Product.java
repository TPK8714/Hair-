package Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Product {

	static Connection conn;
	public Statement stmnt;

	public void product() throws SQLException {
		conn = DriverManager.getConnection(Connect.URL, Connect.USER, Connect.PASSWORD);
		stmnt = conn.createStatement();

	}

	public void createProduct(int product_ID, String Name, String Size, double Price, int Qty) throws SQLException {
		product();
		this.stmnt = conn.createStatement();
		String sql = "INSERT INTO product (PID, name, size, price, quantity) VALUES (" + product_ID + ", \"" + Name
				+ "\", \"" + Size + "\", \"" + Price + "\", \"" + Qty + "\")";
		stmnt.executeUpdate(sql);
	}

	public void readProduct() throws SQLException {
		product();
		String sql = "SELECT * FROM product";

		try (Statement stmnt = conn.createStatement()) {
			ResultSet res = this.stmnt.executeQuery(sql);
			while (res.next()) {
				System.out.println(res.getInt("PID") + " " + res.getString("name") + " " + res.getString("size") + " "
						+ res.getString("price") + res.getString("quantity"));

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void close() throws SQLException {
		conn.close();
	}

}
