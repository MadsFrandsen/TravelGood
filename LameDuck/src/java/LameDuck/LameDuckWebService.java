/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

import fastmoney.imm.dtu.dk.AccountType;
import fastmoney.imm.dtu.dk.CreditCardFaultMessage;
import fastmoney.imm.dtu.dk.CreditCardInfoType;
import fastmoney.imm.dtu.dk.ExpirationDateType;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.WebFault;

/**
 *
 * @author Nygaard
 */
@WebService(serviceName = "LameDuckWebService")
@WebFault(name = "LameDuckException")
public class LameDuckWebService {
    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")

    private final AccountType LAME_DUCK_ACCOUNT = new AccountType();
    private ArrayList<Flight> flights = new ArrayList<Flight>();
    private ArrayList<FlightOption> flightsAvailable = new ArrayList<FlightOption>();

    /**
     * Constructor
     */
    public LameDuckWebService() throws LameDuckException {
        try {

            String flightData = "/Users/Nygaard/Code/GitHub/TravelGood/LameDuck/src/java/LameDuck/flightsdata.csv";
            LAME_DUCK_ACCOUNT.setName("LameDuck");
            LAME_DUCK_ACCOUNT.setNumber("50208812");
            File f = new File(flightData);
            Scanner in = new Scanner(f);
            String headers = in.nextLine(); // skip first line as it is only headers
            while (in.hasNext()) {
                String[] flightInfo = in.nextLine().split(";"); // Excel data is seperated by ;
                String airline = flightInfo[0];
                String source = flightInfo[1];
                String departureDate = flightInfo[2];
                String departureTime = flightInfo[3];
                String destination = flightInfo[4];
                String arrivalDate = flightInfo[5];
                String arrivalTime = flightInfo[6];
                Flight flight = new Flight(airline, source, departureDate, departureTime, destination, arrivalDate, arrivalTime);
                flights.add(flight);
            }
            /*
             Flight flight = new Flight("SAS", "CPH","24122014","1400","BKK","25122014","0630");
             flights.add(flight);
             */


        } catch (Exception e) {
            throw new LameDuckException(e.getMessage());
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

        if (source == null || source.isEmpty()) {
            throw new LameDuckException("Invalid user input: empty source");
        } else if (destination == null || destination.isEmpty()) {
            throw new LameDuckException("Invalid user input: empty destination");
        } else if (date == null) {
            throw new LameDuckException("Invalid user input: empty date");
        }


        // Search through all the flights
        for (Flight flight : flights) {

            // Extract Year, Month and Day from calendar for comparison
            GregorianCalendar departureDate = flight.getDepartureTime();
            int flightYear = departureDate.get(Calendar.YEAR);
            int flightMonth = departureDate.get(Calendar.MONTH);
            int flightDay = departureDate.get(Calendar.DAY_OF_MONTH);

            // Check if source, destination and date matches
            if (flight.getSource().equals(source) && flight.getDestination().equals(destination) && flightYear == date.get(Calendar.YEAR) && flightMonth == date.get(Calendar.MONTH) && flightDay == date.get(Calendar.DAY_OF_MONTH)) {
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
     * @throws LameDuckException
     */
    @WebMethod(operationName = "bookFlight")
    public boolean bookFlight(@WebParam(name = "bookingNumber") int bookingNumber, @WebParam(name = "creditCard") CreditCardInfoType creditCard) throws LameDuckException {
        // TODO: actually book the flight that is note somewhere that the flight is booked
        boolean booked = false;
        if (flightsAvailable.isEmpty()) {
            throw new LameDuckException("empty flight list");
        }

        for (FlightOption flightOption : flightsAvailable) {
            if (flightOption.getBookingNumber() == bookingNumber) {
                try {

                    booked = chargeCreditCard(5, creditCard, flightOption.getPrice(), LAME_DUCK_ACCOUNT);

                } catch (Exception e) {
                    throw new LameDuckException(e.getMessage());
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
     * @throws LameDuckException
     */
    @WebMethod(operationName = "cancelFlight")
    public boolean cancelFlight(@WebParam(name = "bookingNumber") int bookingNumber, @WebParam(name = "price") int price, @WebParam(name = "creditCard") CreditCardInfoType creditCard) throws LameDuckException {
        boolean canceled = false;
        int refund = (int) (price/2);

        try {
            canceled = refundCreditCard(5, creditCard, refund, LAME_DUCK_ACCOUNT);

        } catch (Exception e) {
            throw new LameDuckException(e.getMessage());
        }


        return canceled;
    }

    /*
     * BankService Methods
     */
    
    private static boolean chargeCreditCard(int group, fastmoney.imm.dtu.dk.CreditCardInfoType creditCardInfo, int amount, fastmoney.imm.dtu.dk.AccountType account) throws CreditCardFaultMessage {
        fastmoney.imm.dtu.dk.BankService service = new fastmoney.imm.dtu.dk.BankService();
        fastmoney.imm.dtu.dk.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean refundCreditCard(int group, fastmoney.imm.dtu.dk.CreditCardInfoType creditCardInfo, int amount, fastmoney.imm.dtu.dk.AccountType account) throws CreditCardFaultMessage {
        fastmoney.imm.dtu.dk.BankService service = new fastmoney.imm.dtu.dk.BankService();
        fastmoney.imm.dtu.dk.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean validateCreditCard(int group, fastmoney.imm.dtu.dk.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        fastmoney.imm.dtu.dk.BankService service = new fastmoney.imm.dtu.dk.BankService();
        fastmoney.imm.dtu.dk.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }

    public static void main(String[] args) {
        try {
            LameDuckWebService ws = new LameDuckWebService();
            GregorianCalendar date = new GregorianCalendar(2014, 11, 24);
            ArrayList<FlightOption> flights = ws.getFlights("CPH", "BKK", date);
            for (FlightOption f : flights) {
                System.out.println(f.getFlight().getSource() + " " + f.getFlight().getDestination() + " " + f.getPrice());

            }
            FlightOption myFlight = flights.get(0);
            int price = myFlight.getPrice();
            int bookingNumber = myFlight.getBookingNumber();
            CreditCardInfoType cc = new CreditCardInfoType();
            cc.setName("Tick Joachim");
            cc.setNumber("50408824");
            ExpirationDateType expirationDate = new ExpirationDateType();
            expirationDate.setMonth(2);
            expirationDate.setYear(11);
            cc.setExpirationDate(expirationDate);

            System.out.println(ws.LAME_DUCK_ACCOUNT);
            boolean booked = chargeCreditCard(5, cc, price, ws.LAME_DUCK_ACCOUNT);
            //System.out.println(booked);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(LameDuckWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
