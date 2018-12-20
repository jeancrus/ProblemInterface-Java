package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException{
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		Vehicle vehicle = new Vehicle(sc.nextLine());
		System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Return (dd/MM/yyyy hh:mm): ");
		Date finish = sdf.parse(sc.nextLine());
		CarRental cr = new CarRental(start, finish, vehicle);
		System.out.print("Enter price per hour: ");
		double priceHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double priceDay = sc.nextDouble();
		RentalService rs = new RentalService(priceDay, priceHour, new BrazilTaxService());
		
		rs.processInvoice(cr);
		
		System.out.println("INVOICE:");
		
		System.out.print("Basic payment: " + String.format("%.2f%n", cr.getInvoice().getBasicPayment()));
		System.out.print("Tax: " + String.format("%.2f%n", cr.getInvoice().getTax()));
		System.out.print("Total payment: " + String.format("%.2f%n", cr.getInvoice().getTotalPayment()));
		
		
		sc.close();

	}

}
