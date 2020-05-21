package Logic;

import java.sql.SQLException;
import java.util.Scanner;

import Persistance.Customer;
import Persistance.Orders;
import Persistance.Product;

public class Logic {

	private static Scanner scan = new Scanner(System.in);

	public void CustomerNew() throws SQLException {

		Customer customer = new Customer();

		try {
			String action = "";
			action = getAction();

			do {
				switch (action) {

				case "New":
					System.out.println("Enter customer ID: ");
					int CID = scan.nextInt();
					scan.nextLine();
					System.out.println("Enter first name: ");
					String fname = scan.nextLine();

					System.out.println("Enter last name:");
					String lname = scan.nextLine();

					System.out.println("Enter address: ");
					String address = scan.nextLine();

					System.out.println("Enter postcode: ");
					String pcode = scan.nextLine();

					System.out.println("Enter city: ");
					String city = scan.nextLine();

					customer.createCustomer(CID, fname, lname, address, pcode, city);
					break;

				case "Read":
					customer.readCustomer();
				}

				action = getAction1();

			} while (!action.equals("exit"));
			System.out.println("Goodbye");

		} finally {

			scan.close();

		}
	}

	private String getAction() {
		System.out.println("Type 'New' to create a customer, Type 'Read' to print customer list");
		return scan.nextLine();
	}

	public void ProductNew() throws SQLException {
		Product product = new Product();

		try {
			String action1 = "";
			action1 = getAction1();

			do {
				switch (action1) {

				case "New":

					System.out.println("Enter product ID: ");
					int PID = scan.nextInt();
					System.out.println("Enter product name: ");
					String Name = scan.nextLine();
					System.out.println("Enter size: ");
					String Size = scan.nextLine();
					System.out.println("Enter price: ");
					double Price = scan.nextDouble();
					System.out.println("Enter quantity: ");
					int Qty = scan.nextInt();
					product.createProduct(PID, Name, Size, Price, Qty);
					break;

				case "Read":
					product.readProduct();
				}

				action1 = getAction1();

			} while (!action1.equals("exit"));
			System.out.println("Goodbye");

		} finally {

			scan.close();
			product.close();

		}
	}

	private static String getAction1() {
		System.out.println("Type 'New' to create a new order, Type 'Read' to print order list");
		return scan.nextLine();
	}

	public void OrdersNew() throws SQLException {
		Orders orders = new Orders();

		try {
			String action2 = "";
			action2 = getAction2();

			do {
				switch (action2) {
				case "Create":
					System.out.println("Enter Order ID: ");
					int OID = scan.nextInt();
					System.out.println("Enter Customer ID: ");
					int fk_CID = scan.nextInt();
					System.out.println("Enter Product ID:");
					int fk_PID = scan.nextInt();
					System.out.println("Enter amount required");
					int Quantity = scan.nextInt();
					orders.createOrders(OID, fk_CID, fk_PID, Quantity);
					break;

				case "Read":
					orders.readOrders();
					break;

				case "Cost":
					System.out.println("Which Order do you want to calculate the value for? (Enter OID)");
					int OID1 = scan.nextInt();
					scan.hasNextLine();

					orders.orderCost(OID1);
				}

				action2 = getAction2();

			} while (!action2.equals("exit"));
			System.out.println("Goodbye");

		} finally {

			scan.close();
			orders.close();
		}
	}

	private static String getAction2() {
		System.out.println(
				"Type 'Create' to insert an order, Type 'Read' to view orders, type 'Cost' to calculate the value of an order");
		return scan.nextLine();
	}

	public void close() {

	}
}
