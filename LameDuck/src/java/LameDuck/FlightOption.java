/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Nygaard
 */
public class FlightOption {
    
    private Flight flight;
    private int bookingNumber;
    private static int nextBookingNumber = 0;
    private String airlineReservationService;
    private int price;

    public FlightOption(Flight flight, String airlineReservationService) {
        nextBookingNumber++;
        this.bookingNumber = nextBookingNumber;
        this.flight = flight;
        this.airlineReservationService = airlineReservationService;
        Random rng = new Random();
        this.price = rng.nextInt(5000);
    }

    /**
     * @return the flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @param flight the flight to set
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @return the bookingNumber
     */
    public int getBookingNumber() {
        return bookingNumber;
    }

    /**
     * @return the airlineReservationService
     */
    public String getAirlineReservationService() {
        return airlineReservationService;
    }

    /**
     * @param airlineReservationService the airlineReservationService to set
     */
    public void setAirlineReservationService(String airlineReservationService) {
        this.airlineReservationService = airlineReservationService;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    
    
    
}
