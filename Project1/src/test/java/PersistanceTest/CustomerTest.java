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
import Persistance.Customer;

public class CustomerTest {

	public static Connection conn;
	public static Statement stmnt;

	@Mock
	Statement mockstatement;

	@Mock
	Connection connect;

	@InjectMocks
	Customer customerData = mock(Customer.class);

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

		int CID = 1010;
		String fname = "Dolly";
		String lname = "Pitz";
		String address = "22 Grape drive";
		String pcode = "sk6 6vc";
		String city = "Stockport";

		customerData.createCustomer(CID, fname, lname, address, pcode, city);

		String expectedSql = "INSERT INTO customer (CID, first_name, last_name, adresss, postcode, city) VALUES (\""
				+ CID + "\", \"" + fname + "\", \"" + lname + "\", \"" + address + "\", \"" + pcode + "\", \"" + city
				+ "\")";
		verify(mockstatement).executeUpdate(expectedSql);

	}

}
