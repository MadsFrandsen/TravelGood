package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.AccommodationOptions;
import ws.travelgood.xml.Itenary;
import ws.travelgood.xml.Itinerary;
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

    /**
     *
     */
    public P1() throws DatatypeConfigurationException {
        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar fromDate = df.newXMLGregorianCalendar("2015-09-15");
        XMLGregorianCalendar toDate = df.newXMLGregorianCalendar("2015-09-18");
        String personName="Poul Thomsen";
        String Copenhagen="Copenhagen";
        String Berlin="Berlin";
        String Oslo="Oslo";
        int[] flightBookingNumbers=new int[3];
        int[] hotelBookingNumbers=new int[2];
        String itinearyID;
        
        
        itinearyID=createItinerary(personName);
        
        //Plan first flight
        flightBookingNumbers[0]=getFlights(Copenhagen, fromDate, Berlin).getTravel().get(0).getBookingNumber();
        addFlight(itinearyID, flightBookingNumbers[0]);
        
        
        //Plan a hotol
        hotelBookingNumbers[0]=getHotels(fromDate, toDate, Berlin).getStay().get(0).getBookingNumber();
        addHotelStay(itinearyID, hotelBookingNumbers[0]);
        
        //Plan another flight
        flightBookingNumbers[1]=getFlights(Oslo, fromDate, Berlin).getTravel().get(0).getBookingNumber();
        addFlight(itinearyID, flightBookingNumbers[1]);
        
        
        //Plan a third flight
        flightBookingNumbers[2]=getFlights(Berlin, toDate, Copenhagen).getTravel().get(0).getBookingNumber();
        addFlight(itinearyID, flightBookingNumbers[2]);
        
        
        //Plan a 2nd hotel
        hotelBookingNumbers[1]=getHotels(fromDate, toDate, Copenhagen).getStay().get(0).getBookingNumber();
        addHotelStay(itinearyID, hotelBookingNumbers[1]);
        
        Itenary itenary = getItinerary(itinearyID);
        
       
    }

    
    /**
     *
     */
    @Test
    public void testP1() {
        
    }

    private static boolean addFlight(java.lang.String itenaryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.addFlight(itenaryID, bookingNumber);
    }

    private static boolean addHotelStay(java.lang.String itenaryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getTravelgoodPort();
        return port.addHotel(itenaryID, bookingNumber);
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
}