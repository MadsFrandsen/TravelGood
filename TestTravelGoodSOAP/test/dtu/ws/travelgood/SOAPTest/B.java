package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import lameduck.FlightOption;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.FlightBooking;

/**
 * B (booking fails) Plan an itinerary with three bookings (mixed flights and
 * hotels). Get the itinerary and make sure that the booking status is
 * unconfirmed for each entry. Then book the itinerary. During booking, the
 * second booking should fail. Get the itinerary and check that the result of
 * the book Trip operation records a failure and that the returned itinerary has
 * cancelled as the booking status of the first booking and unconfirmed for the
 * status of the second and third booking.
 *
 * @author Jonas Karlsson (S143341)
 * @return
 */
public class B extends T {

    /**
     *
     */
    public B() throws DatatypeConfigurationException {
    }

    /**
     *
     */
    @Test
    public void testB() {
        itinearyID = createItinerary(itinearyID);
//        int bookingNumber = getFlights(itinearyID, destinations[0], dates[0], destinations[1]).getTravels().get(0).getBookingNumber();
        int bookingNumber = getFlights(itinearyID, destinations[0], dates[0], destinations[1]).getReturn().get(0).getBookingNumber(); // .getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
//        bookingNumber = getFlights(itinearyID, unbookableDestinations[0], dates[0], unbookableDestinations[1]).getTravels().get(0).getBookingNumber();
        bookingNumber = getFlights(itinearyID, unbookableDestinations[0], dates[0], unbookableDestinations[1]).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
//        bookingNumber = getFlights(itinearyID, destinations[1], dates[0], destinations[2]).getTravels().get(0).getBookingNumber();
        bookingNumber = getFlights(itinearyID, destinations[1], dates[0], destinations[2]).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        
//        travels = (Travel[]) getItinerary(itinearyID).getFlightIbookings().toArray();
        itineraryTravels = (FlightBooking[]) getItinerary(itinearyID).getFlightBookings().toArray();

        for (int i = 0; i < itineraryTravels.length; i++) {
//            assertEquals("unconfirmed", travels[i].getStatus());
            assertEquals("unconfirmed", itineraryTravels[i].getStatus());
        }
        
        //Asser that booking fail
        assertEquals(false, bookItinerary(itinearyID, creditCardNumbers[0], personNames[0]));
        itineary = getItinerary(itinearyID);
        
        
        assertEquals(itineary.getFlightBookings().get(0).getStatus(),"cancelled");
        assertEquals(itineary.getFlightBookings().get(1).getStatus(),"unconfirmed");
        assertEquals(itineary.getFlightBookings().get(2).getStatus(),"unconfirmed");
//        assertEquals(itineary.getFlightIbookings().get(0).getStatus(),"cancelled");
//        assertEquals(itineary.getFlightIbookings().get(1).getStatus(),"unconfirmed");
//        assertEquals(itineary.getFlightIbookings().get(2).getStatus(),"unconfirmed");
    }
}