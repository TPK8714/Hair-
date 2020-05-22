package Logic;

import java.sql.SQLException;
import java.util.Scanner;

import Persistance.Connect;

public class Main_Run {

	private static Scanner scan1 = new Scanner(System.in);

	static String getAction3() {
		System.out.println("Welcome to Cantu Hair Care");
		System.out.println("Type 1 for customer, Type 2 for products, type 3 for an order");
		return scan1.nextLine();
	}

	public static void main(String[] args) throws SQLException {
		Connect.connect();

		Logic logic = new Logic();

		try {
			String action3 = "";
			action3 = getAction3();

			do {
				switch (action3) {

				case "1":
					logic.CustomerNew();
					break;

				case "2":
					logic.ProductNew();
					break;

				case "3":
					logic.OrdersNew();
				}

				action3 = getAction3();

			} while (!action3.equals("exit"));
			System.out.println("Goodbye");

		} finally {

			scan1.close();
			logic.close();
		}
	}

}
