package dtu.ws.travelgood.SOAPTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.travelgood.xml.AccommodationOptions;
import ws.travelgood.xml.Itinerary;
import ws.travelgood.xml.Stay;
import ws.travelgood.xml.Travel;
import ws.travelgood.xml.TravelOptions;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jonas
 */
public class T {

    class FlightOption {

        @Override
        public String toString() {
            return "FlightOption{" + "airline=" + airline + ", source=" + source + ", destination=" + destination + ", arrival=" + arrival + ", departure=" + departure + '}';
        }
        public String airline, source, destination;
        XMLGregorianCalendar arrival, departure;
    }
    FlightOption[] bookable, unbookable;
    public DatatypeFactory df;
    public String[] personNames;
    public int[] creditCardNumbers;
    public Travel[] travels, itinearyTravels, bookableTravels;
    public Stay[] stays, itinearyStays;
    public String itinearyID;
    public Itinerary itineary;

    public T() throws DatatypeConfigurationException {
        df = DatatypeFactory.newInstance();
        loadFlightOptions();

        personNames = new String[3];
        personNames[0] = "Poul Thomsen";
        personNames[1] = "Søren Frederiksen";
        personNames[2] = "Peter Pan";

        bookableTravels = new Travel[6];

        creditCardNumbers = new int[2];
        creditCardNumbers[0] = 1121222224;
        creditCardNumbers[1] = 1121222224;

    }

    public void loadFlightOptions() {
        bookable = new FlightOption[1];
        unbookable = new FlightOption[1];


        String flightData = "../LameDuck/src/java/LameDuck/flightsdata.csv";
        ArrayList<FlightOption> bookableList = new ArrayList<>();
        ArrayList<FlightOption> unbookableList = new ArrayList<>();

        try {
            File f = new File(flightData);

            Scanner in = new Scanner(f);
            String headers = in.nextLine(); // skip first line as it is only headers
            FlightOption fo = new FlightOption();

            DatatypeFactory df = DatatypeFactory.newInstance();
            while (in.hasNext()) {
                String[] flightInfo = in.nextLine().split(";"); // Excel data is seperated by ;

                fo.airline = flightInfo[0];
                fo.source = flightInfo[1];
                String departureDate = flightInfo[2];
                String departureTime = flightInfo[3];
                fo.destination = flightInfo[4];
                String arrivalDate = flightInfo[5];
                String arrivalTime = flightInfo[6];

                /* Convert string date to Gregorian Calendar */
                String departureDay = departureDate.substring(0, 2);
                String departureMonth = departureDate.substring(2, 4);
                String departureYear = departureDate.substring(4, 8);
                String departureHour = departureTime.substring(0, 2);
                String departureMinute = departureTime.substring(2, 4);
                GregorianCalendar departure = new GregorianCalendar(Integer.parseInt(departureYear), Integer.parseInt(departureMonth), Integer.parseInt(departureDay), Integer.parseInt(departureHour), Integer.parseInt(departureMinute));
                fo.departure = df.newXMLGregorianCalendar(departure);

                String arrivalDay = departureDate.substring(0, 2);
                String arrivalMonth = departureDate.substring(2, 4);
                String arrivalYear = departureDate.substring(4, 8);
                String arrivalHour = arrivalDate.substring(0, 2);
                String arrivalMinute = arrivalDate.substring(2, 4);
                GregorianCalendar arrival = new GregorianCalendar(Integer.parseInt(arrivalYear), Integer.parseInt(arrivalMonth), Integer.parseInt(arrivalDay), Integer.parseInt(arrivalHour), Integer.parseInt(arrivalMinute));
                fo.arrival = df.newXMLGregorianCalendar(arrival);
                //System.out.println(fo);
                if ("Disney".equals(fo.airline)) {
                    unbookableList.add(fo);
                } else {
                    bookableList.add(fo);

                }

            }
            bookable = bookableList.toArray(bookable);
            unbookable = unbookableList.toArray(bookable);
        } catch (Exception e) {
            System.err.println("File not found");
        }
    }

    public boolean addFlight(java.lang.String itineraryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addFlight(itineraryID, bookingNumber);
    }

    public boolean addHotel(java.lang.String itineraryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addHotel(itineraryID, bookingNumber);
    }

    public boolean bookItinerary(java.lang.String itineraryID, int creditCardNumber, java.lang.String cardOwner) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.bookItinerary(itineraryID, creditCardNumber, cardOwner);
    }

    public boolean cancelItinerary(java.lang.String itineraryID) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.cancelItinerary(itineraryID);
    }

    public String createItinerary(java.lang.String personName) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.createItinerary(personName);
    }

    public Itinerary getItinerary(java.lang.String itineraryID) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getItinerary(itineraryID);
    }

    public TravelOptions getFlights(java.lang.String itineraryID, java.lang.String arrivalDestination, javax.xml.datatype.XMLGregorianCalendar time, java.lang.String departureDestination) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getFlights(itineraryID, arrivalDestination, time, departureDestination);
    }

    public AccommodationOptions getHotels(java.lang.String itineraryID, javax.xml.datatype.XMLGregorianCalendar start, javax.xml.datatype.XMLGregorianCalendar end, java.lang.String city) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getHotels(itineraryID, start, end, city);
    }
}
