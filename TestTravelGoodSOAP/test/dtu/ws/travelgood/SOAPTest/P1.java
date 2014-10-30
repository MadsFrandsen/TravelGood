package dtu.ws.travelgood.SOAPTest;

import dtu.ws.travelgood.client.FlightInformationType;
import dtu.ws.travelgood.client.FlightList;
import dtu.ws.travelgood.client.HotelList;
import dtu.ws.travelgood.client.ItineraryType;
import org.junit.Test;
//For date
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import static org.junit.Assert.*;

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
    public P1() {
       
    }

    
    /**
     *
     */
    @Test
    public void testP1() throws DatatypeConfigurationException {
        //Input data
        String name="Poul Thomsen";
        String destination="Berlin";
        String departure="Copenhagen";
        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar date = df.newXMLGregorianCalendar("2015-09-15");
        
        
        
        //Output data
        String itenary;
        FlightList flightList;
        
        
        itenary=createItinerary(name);
        flightList=getFlights(destination, date, departure);
        FlightInformationType flightInformation = flightList.getFlight();
        int flightBookingNumber=flightInformation.getBookingNumber();
        
        addFlight(itenary, flightBookingNumber);
    }

    private static boolean addFlight(java.lang.String id, int bookingNumber) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addFlight(id, bookingNumber);
    }

    private static boolean addHotelStay(java.lang.String id, int bookingNumber) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addHotelStay(id, bookingNumber);
    }

    private static boolean cancelItinerary(java.lang.String id) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.cancelItinerary(id);
    }

    private static String createItinerary(java.lang.String name) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.createItinerary(name);
    }

    private static FlightList getFlights(java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar time, java.lang.String departure) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getFlights(destination, time, departure);
    }

    private static HotelList getHotels(javax.xml.datatype.XMLGregorianCalendar start, javax.xml.datatype.XMLGregorianCalendar end, java.lang.String city) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getHotels(start, end, city);
    }

    private static ItineraryType getItinerary(java.lang.String id) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getItinerary(id);
    }
}