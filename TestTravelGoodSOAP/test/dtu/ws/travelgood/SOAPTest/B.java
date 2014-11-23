package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.Travel;

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
        //Create itinerary
        itinearyID = createItinerary(person[8].name); // this person has a CC limit on 10.000
        //Get a flight and book it.
//        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
//        bookingNumber = getFlights(itinearyID, unbookable[0].source, unbookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        bookingNumber = getFlights(itinearyID, unbookable[0].source, unbookable[0].departure, bookable[0].destination).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
//        bookingNumber = getFlights(itinearyID, bookable[1].source, bookable[1].departure, bookable[1].destination).getTravels().get(0).getBookingNumber();
        bookingNumber = getFlights(itinearyID, bookable[1].source, bookable[1].departure, bookable[1].destination).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        
        itinearyTravels = (Travel[]) getItinerary(itinearyID).getFlightBookings().toArray();

        for (int i = 0; i < itinearyTravels.length; i++) {
            assertEquals("unconfirmed", itinearyTravels[i].getStatus());

        }
        
        //Asser that booking fail
        assertEquals(false, bookItinerary(itinearyID, person[8].number, person[8].name));
        itineary = getItinerary(itinearyID);
        
        assertEquals(itineary.getFlightBookings().get(0).getStatus(),"cancelled");
        assertEquals(itineary.getFlightBookings().get(1).getStatus(),"unconfirmed");
        assertEquals(itineary.getFlightBookings().get(2).getStatus(),"unconfirmed");
        
        
        

    }
}