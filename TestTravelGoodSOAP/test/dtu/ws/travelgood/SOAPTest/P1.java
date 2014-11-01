package dtu.ws.travelgood.SOAPTest;

import java.util.Arrays;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.AccommodationOptions;

import ws.travelgood.xml.Itinerary;
import ws.travelgood.xml.Stay;
import ws.travelgood.xml.Travel;
import ws.travelgood.xml.TravelOptions;

/**
 * P1 (planning and booking) Plan a trip by first planning a flight (i.e.
 * getting a list of flights and then adding a flight to the itinerary), then by
 * planning a hotel, another flight, a third flight, and finally a hotel. Ask
 * for the itinerary and check that it is correct using JUnit’s assert
 * statements – i.e. assertEquals, assertTrue, . . . – in particular, that the
 * booking status for each item is unconfirmed. Book the itinerary and ask again
 * for the itinerary. Check that each booking status is now confirmed
 *
 * @author Jonas Karlsson (S143341)
 * @return
 */
public class P1 {

    private DatatypeFactory df;
    private XMLGregorianCalendar fromDate, toDate;
    private String personName, Copenhagen, Oslo, Berlin, itinearyID;
    int creditCardNumber;
    Travel[] travels, itinearyTravels;
    Stay[] stays, itinearyStays;

    public P1() throws DatatypeConfigurationException {
        df = DatatypeFactory.newInstance();
        fromDate = df.newXMLGregorianCalendar("2015-09-15");
        toDate = df.newXMLGregorianCalendar("2015-09-18");
        personName = "Poul Thomsen";
        creditCardNumber = 661011002;

        Copenhagen = "Copenhagen";
        Berlin = "Berlin";
        Oslo = "Oslo";

        travels = new Travel[3];
        itinearyTravels = new Travel[3];

        stays = new Stay[2];
        itinearyStays = new Stay[2];


    }

    /**
     *
     */
    @Test
    public void testP1() throws DatatypeConfigurationException {




        itinearyID = createItinerary(personName);

        //Plan first flight
        travels[0] = getFlights(Copenhagen, fromDate, Berlin).getTravel().get(0);
        addFlight(itinearyID, travels[0].getBookingNumber());


        //Plan a hotol
        stays[0] = getHotels(fromDate, toDate, Berlin).getStay().get(0);
        addHotel(itinearyID, stays[0].getBookingNumber());

        //Plan another flight
        travels[1] = getFlights(Oslo, fromDate, Berlin).getTravel().get(0);
        addFlight(itinearyID, travels[1].getBookingNumber());

        //Plan a third flight
        travels[2] = getFlights(Berlin, toDate, Copenhagen).getTravel().get(0);
        addFlight(itinearyID, travels[2].getBookingNumber());


        //Plan a 2nd hotel
        stays[1] = getHotels(fromDate, toDate, Copenhagen).getStay().get(0);
        addHotel(itinearyID, stays[1].getBookingNumber());


        //Assertations

        //Booking unconfirmed;
        assertEquals("unconfirmed", getItinerary(itinearyID).getStatus());

        //Do booking 
        bookItinerary(itinearyID, creditCardNumber, personName);

        //get itinerary
        Itinerary itinerary = getItinerary(itinearyID);
        assertEquals("confirmed", itinerary.getStatus());

        // compare travel information
        itinearyTravels = (Travel[]) itinerary.getFlightIbookings().toArray();
        itinearyStays = (Stay[]) itinerary.getHotelbookings().toArray();

        Arrays.sort(stays);
        Arrays.sort(itinearyStays);
        Arrays.sort(travels);
        Arrays.sort(itinearyTravels);

        assertArrayEquals(stays, itinearyStays);
        assertArrayEquals(travels, itinearyTravels);

    }

    private static boolean addFlight(java.lang.String itenaryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.addFlight(itenaryID, bookingNumber);
    }

    private static boolean bookItinerary(java.lang.String itenaryID, int creditCardNumber, java.lang.String cardOwner) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.bookItinerary(itenaryID, creditCardNumber, cardOwner);
    }

    private static boolean cancelItinerary(java.lang.String itenaryID) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.cancelItinerary(itenaryID);
    }

    private static String createItinerary(java.lang.String personName) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.createItinerary(personName);
    }

    private static TravelOptions getFlights(java.lang.String arrivalDestination, javax.xml.datatype.XMLGregorianCalendar time, java.lang.String departureDestination) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.getFlights(arrivalDestination, time, departureDestination);
    }

    private static AccommodationOptions getHotels(javax.xml.datatype.XMLGregorianCalendar start, javax.xml.datatype.XMLGregorianCalendar end, java.lang.String city) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.getHotels(start, end, city);
    }

    private static Itinerary getItinerary(java.lang.String id) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.getItinerary(id);
    }

    private static boolean addHotel(java.lang.String itineraryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.addHotel(itineraryID, bookingNumber);
    }
}