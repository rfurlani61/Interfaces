package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicles;
import model.services.BrazilTaxService;
import model.services.RentalServices;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		System.out.println("Entre com os dados do aluguel: ");
		System.out.print("Modelo do carro: ");
		String modelo = sc.nextLine();
		System.out.println("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.println("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Entre com o preço por hora: ");
		Double precoPorHora = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		Double precoPorDia = sc.nextDouble();
		CarRental cr = new CarRental(start, finish, new Vehicles(modelo));
		RentalServices rs = new RentalServices(precoPorHora, precoPorDia, new BrazilTaxService());
		rs.processInvoice(cr);
		System.out.println("FATURA:");
		System.out.println("Pagamento básico: " + cr.getInvoice().getBasicPayment());
		System.out.println("Imposto: " + cr.getInvoice().getTax());
		System.out.println("Pagamento Total: " + cr.getInvoice().totalPayment()); 
	}
}
