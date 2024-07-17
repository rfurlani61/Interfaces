package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalServices {
	private Double pricePerHour;
	private Double pricePerDay;
	private BrazilTaxService taxService;
	
	public RentalServices(Double pricePerHour, Double pricePerDay, BrazilTaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}	

	public void processInvoice(CarRental carRental) {
		Double minutes = (double) Duration.between(carRental.getStart(),carRental.getFinish()).toMinutes();
		double duration = minutes / 60;
		Double basicPayment;
		if (Math.ceil(duration) < 12) {
			basicPayment = Math.ceil(duration) * pricePerHour;
		}
		else {
			duration /= 24;
			basicPayment = Math.ceil(duration) * pricePerDay;
		}
		carRental.setInvoice(new Invoice(basicPayment, taxService.tax(basicPayment)));
	}
}
