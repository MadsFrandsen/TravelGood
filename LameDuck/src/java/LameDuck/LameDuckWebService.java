/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.ChargeCreditCard;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.lang.Exception;
import java.util.Scanner;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Nygaard
 */
@WebService(serviceName = "LameDuckWebService")
public class LameDuckWebService {
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk/BankService.wsdl")
    private BankService bankService;
    private final AccountType LAME_DUCK_ACCOUNT = new AccountType();
    private ArrayList<Flight> flights;
    private ArrayList<FlightOption> flightsAvailable;

    /**
     * Constructor
     */
    public LameDuckWebService(String flightData) {
        try {
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
            e.printStackTrace();
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
    public ArrayList<FlightOption> getFlights(@WebParam(name = "from") String source, @WebParam(name = "to") String destination, @WebParam(name = "date") GregorianCalendar date) throws Exception {
        // Check for valid user input
        if (source == null || source.isEmpty() || destination == null || destination.isEmpty() || date == null) {
            throw new Exception();
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
    public boolean bookFlight(@WebParam(name = "bookingNumber") int bookingNumber, @WebParam(name = "creditCard") CreditCardInfoType creditCard) throws CreditCardFaultMessage {
        // TODO: actually book the flight that is note somewhere that the flight is booked
        boolean booked = false;
        for (FlightOption flightOption : flightsAvailable) {
            if (flightOption.getBookingNumber() == bookingNumber) {
                try {
                    booked = chargeCreditCard(0, creditCard, flightOption.getPrice(), LAME_DUCK_ACCOUNT);

                } catch (Exception e) {
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
    public boolean cancelFlight(int bookingNumber, int price, CreditCardInfoType creditCard) throws CreditCardFaultMessage {
        boolean canceled = false;
        int refund = (int) 0.5 * price;

        canceled = refundCreditCard(0, creditCard, refund, LAME_DUCK_ACCOUNT);

        return canceled;
    }

    /*
     * BankService Methods
     */
    private boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = bankService.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }

    private boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = bankService.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = bankService.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }
    
}
