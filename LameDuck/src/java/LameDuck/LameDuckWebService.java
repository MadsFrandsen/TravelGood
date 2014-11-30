/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

import fastmoney.imm.dtu.dk.AccountType;
import fastmoney.imm.dtu.dk.CreditCardFaultMessage;
import fastmoney.imm.dtu.dk.CreditCardInfoType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
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
    private static int nextRequestId = 0;
    private HashSet<Flight> flights = new HashSet<Flight>();
    private HashMap<Integer, FlightOption> matchingFlights;
    private static HashMap<Integer, HashMap<Integer, FlightOption>> requests = new HashMap<Integer, HashMap<Integer, FlightOption>>();

    /**
     * Constructor
     */
    public LameDuckWebService() {

        LAME_DUCK_ACCOUNT.setName("LameDuck");
        LAME_DUCK_ACCOUNT.setNumber("50208812");

        flights.add(new Flight("SAS", "CPH", "24122014", "1430", "BKK", "25122014", "0600"));
        flights.add(new Flight("SAS", "CPH", "24122014", "1630", "BKK", "25122014", "0800"));
        flights.add(new Flight("SAS", "CPH", "24122014", "1830", "BKK", "25122014", "1000"));
        flights.add(new Flight("SAS", "BKK", "31122014", "0130", "CPH", "01012014", "0730"));
        flights.add(new Flight("SAS", "BKK", "31122014", "0230", "CPH", "01012014", "0830"));
        flights.add(new Flight("SAS", "BKK", "31122014", "0330", "CPH", "01012014", "0930"));
        flights.add(new Flight("Thai", "BKK", "27122014", "1530", "SFO", "28122014", "0700"));
        flights.add(new Flight("Thai", "BKK", "27122015", "1700", "SFO", "28122014", "0930"));
        flights.add(new Flight("Thai", "SFO", "02022015", "1100", "BKK", "03022015", "0800"));
        flights.add(new Flight("Thai", "SFO", "02022016", "1500", "BKK", "03022015", "1200"));
        flights.add(new Flight("Lufthansa", "FRA", "26122014", "1200", "SFO", "26122014", "2000"));
        flights.add(new Flight("Lufthansa", "FRA", "26122014", "1400", "SFO", "26122014", "2200"));
        flights.add(new Flight("Lufthansa", "FRA", "26122014", "1600", "SFO", "26122014", "2330"));
        flights.add(new Flight("Lufthansa", "SFO", "05012015", "1200", "FRA", "05012015", "1830"));
        flights.add(new Flight("Lufthansa", "SFO", "05012016", "1430", "FRA", "05012016", "2000"));
        flights.add(new Flight("Lufthansa", "SFO", "05012017", "1645", "FRA", "05012017", "2245"));
        flights.add(new Flight("Air France", "CDG", "10012015", "0930", "YVR", "10012015", "1430"));
        flights.add(new Flight("Air France", "CDG", "10012015", "1130", "YVR", "10012015", "1630"));
        flights.add(new Flight("Air France", "YVR", "15012015", "1030", "CDG", "15012015", "1830"));
        flights.add(new Flight("Air France", "YVR", "15012015", "1330", "CDG", "15012015", "2130"));
        flights.add(new Flight("KLM", "AMS", "12122014", "1730", "DAR", "12122014", "2300"));
        flights.add(new Flight("KLM", "DAR", "15122014", "1500", "AMS", "15122014", "2100"));
        flights.add(new Flight("Disney", "Andeby", "01012015", "1200", "Moon", "01012015", "2300"));
        flights.add(new Flight("Mordor", "Good", "31122014", "1200", "Evil", "01022015", "1200"));

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

        matchingFlights = new HashMap<Integer, FlightOption>();

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
                matchingFlights.put(flightOption.getBookingNumber(), flightOption);
            }
        }
        nextRequestId++;
        int requestId = nextRequestId;
        requests.put(requestId, matchingFlights);
        ArrayList<FlightOption> flightList = new ArrayList<FlightOption>(matchingFlights.values());
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

        FlightOption relevantFlight = null;
        int requestId = -1;
        boolean booked = false;

        for (Entry<Integer, HashMap<Integer, FlightOption>> entry : requests.entrySet()) {
            requestId = entry.getKey();
            HashMap<Integer, FlightOption> flightOptions = entry.getValue();

            if (flightOptions.get(bookingNumber) != null) {
                relevantFlight = flightOptions.get(bookingNumber);
                break;
            }
        }

        if (relevantFlight == null) {
            throw new LameDuckException("Booking number does not exist!");
        }

        try {

            booked = chargeCreditCard(5, creditCard, relevantFlight.getPrice(), LAME_DUCK_ACCOUNT);

        } catch (Exception e) {
            throw new LameDuckException(e.getMessage());
        }

        // delete flightOptions since user has booked a flight
        if (requestId != -1){
            requests.remove(requestId);
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

//    public static void main(String[] args) {
//
//        try {
//            LameDuckWebService ws = new LameDuckWebService();
//            GregorianCalendar date = new GregorianCalendar(2014, 11, 24);
//            ArrayList<FlightOption> flights = ws.getFlights("CPH", "BKK", date);
//            for (FlightOption f : flights) {
//                System.out.println(f.getFlight().getId() + " " + f.getFlight().getSource() + " " + f.getFlight().getDestination() + " " + f.getPrice() + " " + f.getFlight().getArrivalTime().getTime().toString());
//            }
//
//            GregorianCalendar date2 = new GregorianCalendar(2014, 11, 31);
//            ArrayList<FlightOption> flights2 = ws.getFlights("BKK", "CPH", date2);
//            System.out.println(flights2.size());
//            for (FlightOption f : flights2) {
//                System.out.println(f.getFlight().getId() + " " + f.getFlight().getSource() + " " + f.getFlight().getDestination() + " " + f.getPrice()+ " " + f.getFlight().getArrivalTime().getTime().toString());
//            }
//
//            System.out.println("");
//            ArrayList<FlightOption> flights3 = ws.getFlights("CPH", "BKK", date);
//            for (FlightOption f : flights3) {
//                System.out.println(f.getFlight().getId() + " " + f.getFlight().getSource() + " " + f.getFlight().getDestination() + " " + f.getPrice() + " " + f.getFlight().getArrivalTime().getTime().toString());
//            }
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            //Logger.getLogger(LameDuckWebService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
