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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Persistance.Connect;
import Persistance.Orders;

public class Order_Test {

	public static Connection conn;
	public static Statement stmnt;

	@Mock
	Statement mockStmnt;

	@Mock
	Connection connect;

	@Mock
	Orders orderData = mock(Orders.class);

	@BeforeClass
	public static void connect() throws SQLException {
		conn = DriverManager.getConnection(Connect.URL, Connect.USER, Connect.PASSWORD);
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreat() throws SQLException {

		when(connect.createStatement()).thenReturn(mockStmnt);

		int OID = 112;
		int fk_CID = 1007;
		int fk_PID = 3;
		int Quantity = 3;

		orderData.createOrders(OID, fk_CID, fk_PID, Quantity);

		String newSql = "INSERT INTO Orders (OID, fk_CID, fk_PID) VALUES (" + OID + ", \"" + fk_CID + "\", \"" + fk_PID
				+ "\", \"" + Quantity + "\")";
		verify(mockStmnt).executeUpdate(newSql);

	}
}
