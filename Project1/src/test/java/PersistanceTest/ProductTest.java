package PersistanceTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Persistance.Connect;
import Persistance.Product;

public class ProductTest {

	public static Connection conn;
	public static Statement stmnt;

	@Mock
	Statement mockstatement;

	@Mock
	Connection connect;

	@InjectMocks
	Product productData = mock(Product.class);

	@BeforeClass
	public static void connect() throws SQLException {
		conn = DriverManager.getConnection(Connect.URL, Connect.USER, Connect.PASSWORD);
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreate() throws SQLException {

		when(connect.createStatement()).thenReturn(mockstatement);

		int PID = 1009;
		String Name = "Kitz";
		String Size = "22 Orange drive";
		double Price = 7.99;
		int Qty = 500;

		productData.createProduct(PID, Name, Size, Price, Qty);

		String expectedSql = "INSERT INTO product (PID, name, size, price, quantity) VALUES (" + PID + ", \"" + Name
				+ "\", \"" + Size + "\", \"" + Price + "\", \"" + Qty + "\")";
		verify(mockstatement).executeQuery(expectedSql);

	}

}
