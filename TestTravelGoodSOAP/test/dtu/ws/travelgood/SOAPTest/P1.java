package dtu.ws.travelgood.SOAPTest;

import java.util.Arrays;
import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.Itinerary;
import ws.travelgood.xml.Stay;
import ws.travelgood.xml.Travel;


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
public class P1 extends T {

    
    
    public P1() throws DatatypeConfigurationException {
      

        travels = new Travel[3];
        itinearyTravels = new Travel[3];

        stays = new Stay[2];
        itinearyStays = new Stay[2];


    }

    /**
     *
     */
    @Test
    public void testP1()  {

        itinearyID = createItinerary(personNames[0]);

        
        //Plan first flight
        travels[0] = getFlights(itinearyID, destinations[0], dates[0], destinations[1]).getTravels().get(0);
        addFlight(itinearyID, travels[0].getBookingNumber());


        //Plan a hotol
        stays[0] = getHotels(itinearyID, dates[0], dates[2], destinations[2]).getStays().get(0);
        addHotel(itinearyID, stays[0].getBookingNumber());

        //Plan another flight
        travels[1] = getFlights(itinearyID, destinations[2], dates[0], destinations[1]).getTravels().get(0);
        addFlight(itinearyID, travels[1].getBookingNumber());

        //Plan a third flight
        travels[2] = getFlights(itinearyID, destinations[0], dates[4], destinations[0]).getTravels().get(0);
        addFlight(itinearyID, travels[2].getBookingNumber());


        //Plan a 2nd hotel
        stays[1] = getHotels(itinearyID, dates[0], dates[4], destinations[1]).getStays().get(0);
        addHotel(itinearyID, stays[1].getBookingNumber());


        //Assertations

        //Booking unconfirmed;
        assertEquals("unconfirmed", getItinerary(itinearyID).getStatus());

        //Do booking 
        bookItinerary(itinearyID, creditCardNumbers[1], personNames[1]);

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
        
        assertEquals(itinearyTravels.length,travels.length);
        
        for (int i = 0; i < itinearyTravels.length; i++) {
            assertEquals(itinearyTravels[i].getBookingNumber(),travels[i].getBookingNumber());
            assertEquals(itinearyTravels[i].getFlight(),travels[i].getFlight());
            assertEquals(itinearyTravels[i].getReservationService(),travels[i].getReservationService());
            assertEquals(itinearyTravels[i].getStatus(),"confirmed");
            
        }
        
        assertEquals(itinearyStays.length,stays.length);
        
        for (int i = 0; i < itinearyStays.length; i++) {
            assertEquals(itinearyStays[i].getAddress(),stays[i].getAddress());
            assertEquals(itinearyStays[i].getBookingNumber(),stays[i].getBookingNumber());
            assertEquals(itinearyStays[i].getName(),stays[i].getName());
            assertEquals(itinearyStays[i].getReservationServiceName(),stays[i].getReservationServiceName());
            assertEquals(itinearyStays[i].isCreditCardRequired(),stays[i].isCreditCardRequired());
            assertEquals(itinearyStays[i],"confirmed");
            
        }

        
    }

    
}