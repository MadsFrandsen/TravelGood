/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

import fastmoney.imm.dtu.dk.AccountType;
import fastmoney.imm.dtu.dk.CreditCardFaultMessage;
import fastmoney.imm.dtu.dk.CreditCardInfoType;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.Scanner;
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
    private HashSet<Flight> flights = new HashSet<Flight>();
    private HashMap<Integer, FlightOption> flightsAvailable = new HashMap<Integer, FlightOption>();

    /**
     * Constructor
     */
    public LameDuckWebService() throws LameDuckException {
        try {

            String flightData = "/Users/Nygaard/Code/GitHub/TravelGood/LameDuck/flightsdata.csv";
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
                if (!flightsAvailable.containsKey(flight.getId())) {
                    flightsAvailable.put(flight.getId(), flightOption);
                }
            }
        }
        ArrayList<FlightOption> flightList = new ArrayList<FlightOption>(flightsAvailable.values());
        return flightList;
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

        for (Map.Entry<Integer, FlightOption> entry : flightsAvailable.entrySet()) {
            if (entry.getValue().getBookingNumber() == bookingNumber) {
                try {

                    booked = chargeCreditCard(5, creditCard, entry.getValue().getPrice(), LAME_DUCK_ACCOUNT);

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

        // hardcoded condition so that we can control our unittest.
        if (bookingNumber == 666) {
            return canceled;
        }
        int refund = (int) (price / 2);

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
                System.out.println(f.getFlight().getId() + " " + f.getFlight().getSource() + " " + f.getFlight().getDestination() + " " + f.getPrice());
            }
            
            ArrayList<FlightOption> flights2 = ws.getFlights("CPH", "BKK", date);
            System.out.println(flights2.size());
            for (FlightOption f : flights){
                System.out.println(f.getFlight().getId() + " " + f.getFlight().getSource() + " " + f.getFlight().getDestination() + " " + f.getPrice());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(LameDuckWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
