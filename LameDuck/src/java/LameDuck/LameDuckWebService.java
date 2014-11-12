/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

import fastmoney.imm.dtu.dk.AccountType;
import fastmoney.imm.dtu.dk.BankService;
import fastmoney.imm.dtu.dk.CreditCardFaultMessage;
import fastmoney.imm.dtu.dk.CreditCardInfoType;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.Scanner;
import javax.xml.ws.WebFault;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Nygaard
 */
@WebService(serviceName = "LameDuckWebService")
@WebFault(name="LameDuckException")
public class LameDuckWebService {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;
    
    private final AccountType LAME_DUCK_ACCOUNT = new AccountType();
    private ArrayList<Flight> flights = new ArrayList<Flight>();
    private ArrayList<FlightOption> flightsAvailable = new ArrayList<FlightOption>();

    /**
     * Constructor
     */
    public LameDuckWebService() throws LameDuckException {
        try {
            
            String flightData = "";
            
            LAME_DUCK_ACCOUNT.setName("LameDuck");
            LAME_DUCK_ACCOUNT.setNumber("50208812");
            
            File f = new File(flightData);
            Scanner in = new Scanner(f);
            while (in.hasNext()) {
                String[] flightInfo = in.next().split(";"); // Excel data is seperated by ;
                String airline = flightInfo[0];
                String source = flightInfo[1];
                int departureDate = Integer.parseInt(flightInfo[2]);
                int departureTime = Integer.parseInt(flightInfo[3]);
                String destination = flightInfo[4];
                int arrivalDate = Integer.parseInt(flightInfo[5]);
                int arrivalTime = Integer.parseInt(flightInfo[6]);
                
                Flight flight = new Flight(airline, source, departureDate, departureTime, destination, arrivalDate, arrivalTime);
                flights.add(flight);

            }
        } catch (Exception e) {
            throw new LameDuckException();
        }

    }

    /**
     *
     * @param source
     * @param destination
     * @param date
     * @return
     * @throws Exception
     */
    @WebMethod(operationName = "getFlights")
    public ArrayList<FlightOption> getFlights(@WebParam(name = "from") String source, @WebParam(name = "to") String destination, @WebParam(name = "date") GregorianCalendar date) throws LameDuckException {
        // Check for valid user input
        if (source == null || source.isEmpty() || destination == null || destination.isEmpty() || date == null) {
            throw new LameDuckException();
        }

        // Convert date to int
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        String tempDate = "" + year + month + day;
        int departureDate = Integer.parseInt(tempDate);

        // Search through all the flights
        for (Flight flight : flights) {
            if (flight.getSource().equals(source) && flight.getDestination().equals(destination) && flight.getDepartureDate() == departureDate) {
                FlightOption flightOption = new FlightOption(flight, "LameDuck");
                flightsAvailable.add(flightOption);
            }
        }
        return flightsAvailable;
    }

    /**
     *
     * @param bookingNumber
     * @param creditCard
     * @return
     * @throws CreditCardFaultMessage
     */
    @WebMethod(operationName = "bookFlight")
    public boolean bookFlight(@WebParam(name = "bookingNumber") int bookingNumber, @WebParam(name = "creditCard") CreditCardInfoType creditCard) throws LameDuckException {
        // TODO: actually book the flight that is note somewhere that the flight is booked
        boolean booked = false;
        for (FlightOption flightOption : flightsAvailable) {
            if (flightOption.getBookingNumber() == bookingNumber) {
                try {
                    booked = chargeCreditCard(0, creditCard, flightOption.getPrice(), LAME_DUCK_ACCOUNT);

                } catch (Exception e) {
                    throw new LameDuckException();
                }

            }
        }

        return booked;
    }

    /**
     *
     * @param bookingNumber
     * @param price
     * @param creditCard
     * @return
     * @throws CreditCardFaultMessage
     */
    @WebMethod(operationName = "cancelFlight")
    public boolean cancelFlight(@WebParam(name = "bookingNumber") int bookingNumber, @WebParam(name = "price") int price, @WebParam(name = "creditCard") CreditCardInfoType creditCard) throws LameDuckException {
        boolean canceled = false;
        int refund = (int) 0.5 * price;

        try {
            canceled = refundCreditCard(0, creditCard, refund, LAME_DUCK_ACCOUNT);
            
        } catch (Exception e) {
            throw new LameDuckException();
        }
        

        return canceled;
    }

    /*
     * BankService Methods
     */

    private boolean chargeCreditCard(int group, fastmoney.imm.dtu.dk.CreditCardInfoType creditCardInfo, int amount, fastmoney.imm.dtu.dk.AccountType account) throws CreditCardFaultMessage {
        fastmoney.imm.dtu.dk.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean refundCreditCard(int group, fastmoney.imm.dtu.dk.CreditCardInfoType creditCardInfo, int amount, fastmoney.imm.dtu.dk.AccountType account) throws CreditCardFaultMessage {
        fastmoney.imm.dtu.dk.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean validateCreditCard(int group, fastmoney.imm.dtu.dk.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        fastmoney.imm.dtu.dk.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
    
}
