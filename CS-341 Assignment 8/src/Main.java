import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		Inventory a = new Inventory();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter one of the following commands");
		System.out.println("1. View inventory\n2. Search a textbook by SKU\n3. Add a textbook to the inventory\n4. Remove a textbook from the inventory");
		int command = input.nextInt();
		
		if (command > 4 || command < 1) {
			System.out.println("Error. Invalid command");
			run();
		}
		else if (command == 1) {
			a.display();
			run();
		}
		else if (command == 2) {
			a.info();
			run();
		}
		else if (command == 3) {
			a.add();
			run();
		}
		else if (command == 4) {
			a.remove();
			run();
		}
	}
}
