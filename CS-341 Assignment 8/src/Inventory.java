import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
	private ArrayList<Textbook> list = new ArrayList<>();
	
	public Inventory() {
		try {
			FileInputStream fileIn = new FileInputStream("object.txt");
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(fileIn));
			list = (ArrayList<Textbook>) in.readObject();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void add() {
		boolean inStock = false;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter textbook title:\n");
		String title = input.nextLine();
		
		System.out.println("Enter sku:\n");
		int sku = input.nextInt();
		
		System.out.println("Enter textbook price:\n");
		double price = input.nextDouble();
		
		System.out.println("Enter quantity of textbook to be added:\n");
		int quantity = input.nextInt();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSku() == sku) {
				list.get(i).setQuantity(list.get(i).getQuantity() + quantity);
				inStock = true;
			}
		}
		
		if (!inStock) {
			list.add(new Textbook(sku, title, price, quantity));
		}
		
		try {
			FileOutputStream fileOut = new FileOutputStream("object.txt");
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(fileOut));
			try {
				out.writeObject(list);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
			fileOut.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Added textbook: " + "(" + quantity + ") " + title + " " + "(" + sku + ")" + " to the inventory\n");
	}
	
	public void remove() {
		boolean inStock = false;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter sku of textbook to be removed\n");
		int sku = input.nextInt();
		System.out.println("Enter the amount of this book to be removed");
		int amount = input.nextInt();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSku() == sku) {
				list.get(i).setQuantity(list.get(i).getQuantity() - amount);
				inStock = true;
			}
		}
		
		if (inStock) {
			try {
				FileOutputStream fileOut = new FileOutputStream("object.txt");
				ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(fileOut));
				try {
					out.writeObject(list);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				out.close();
				fileOut.close();
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
		else {
			System.out.println("Book is not in stock\n");
		}
	}
	
	public void info() {
		Scanner input = new Scanner(System.in);
		boolean contains = false;
		
		System.out.println("Enter an sku to see textbook info\n");
		int sku = input.nextInt();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSku() == sku) {
				System.out.println("SKU \t Title \t Price \t Quantity \n" + list.get(i).getSku() + "\t"
						+ list.get(i).getTitle() + "\t" + list.get(i).getPrice() + "\t"
						+ list.get(i).getQuantity() + "\n");
				contains = true;
			}
		}
		
		if (contains == false) {
			System.out.println("Book is not in stock\n");
		}
		
	}
	
	public void display() {
		if (list.isEmpty()) {
			System.out.println("Inventory is currently empty\n");
		} 
		
		else {
			System.out.println("SKU \t Title \t Price \t Quantity");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getSku() + "\t" + list.get(i).getTitle() + "\t" + list.get(i).getPrice() + "\t" + list.get(i).getQuantity());
			}
		}
	}
}
