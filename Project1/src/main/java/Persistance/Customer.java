package Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {

	static Connection conn;
	public Statement stmnt;

	public void customer() throws SQLException {
		conn = DriverManager.getConnection(Connect.URL, Connect.USER, Connect.PASSWORD);
		stmnt = conn.createStatement();

	}

	public void createCustomer(int CID, String fname, String lname, String address, String pcode, String city)
			throws SQLException {
		customer();
		this.stmnt = conn.createStatement();
		String sql = "INSERT INTO customer (CID, first_name, last_name, adresss, postcode, city) VALUES (\"" + CID
				+ "\", \"" + fname + "\", \"" + lname + "\", \"" + address + "\", \"" + pcode + "\", \"" + city + "\")";
		stmnt.executeUpdate(sql);
	}

	public void readCustomer() throws SQLException {

		customer();
		String sql = "SELECT * FROM customer";
		try (Statement stmnt = conn.createStatement()) {
			ResultSet res = this.stmnt.executeQuery(sql);
			while (res.next()) {
				System.out.println(
						res.getInt("CID") + " " + res.getString("first_name") + " " + res.getString("last_name") + " "
								+ res.getString("adresss") + res.getString("postcode") + res.getString("city"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void close() throws SQLException {
		conn.close();
	}

}
