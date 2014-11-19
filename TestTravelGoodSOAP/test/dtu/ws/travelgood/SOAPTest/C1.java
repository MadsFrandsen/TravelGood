package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.Travel;

/**
 * C1 (cancel booking) Create an itinerary with three bookings (mixed flights
 * and hotels) and book it. Get the itinerary and make sure that the booking
 * status is confirmed for each entry. Cancel the trip and check that now the
 * booking status is cancelled for all bookings of the itinerary.
 *
 * @author Jonas Karlsson (S143341)
 * @return
 */
public class C1 extends T {

    /**
     *
     */
    public C1() throws DatatypeConfigurationException {
    }

    /**
     *
     */
    @Test
    public void testC1() {
        //Create itinerary
        itinearyID = createItinerary(person[2].name);
        //get a booking number and book a flight 
        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        //get another  booking number and book a flight
        bookingNumber = getFlights(itinearyID, bookable[2].source, bookable[2].departure, bookable[2].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        //add a hotel
        addHotel(itinearyID, getHotels(itinearyID, dates[0], dates[1], destinations[0]).getStays().get(0).getBookingNumber());

        bookItinerary(itinearyID, person[2].number, person[2].name);
        
        travels = (Travel[]) getItinerary(itinearyID).getFlightIbookings().toArray();
        for (int i = 0; i < travels.length; i++) {
            assertEquals("confirmed", travels[i].getStatus());
        }
        assertEquals("confirmed", getItinerary(itinearyID).getHotelbookings().get(0).getStatus());

        cancelItinerary(itinearyID);

        travels = (Travel[]) getItinerary(itinearyID).getFlightIbookings().toArray();

        for (int i = 0; i < travels.length; i++) {
            assertEquals("cancelled", travels[i].getStatus());
        }
        assertEquals("cancelled", getItinerary(itinearyID).getHotelbookings().get(0).getStatus());
        
        

    }
}