package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.FlightBooking;

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
//        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        //get another  booking number and book a flight
//        bookingNumber = getFlights(itinearyID, bookable[2].source, bookable[2].departure, bookable[2].destination).getTravels().get(0).getBookingNumber();
        bookingNumber = getFlights(itinearyID, bookable[2].source, bookable[2].departure, bookable[2].destination).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        //add a hotel
        addHotel(itinearyID, getHotels(itinearyID, hotelOption[0].fromDate, hotelOption[0].toDate, hotelOption[0].city).getStays().get(0).getBookingNumber());

        bookItinerary(itinearyID, person[2].number, person[2].name);
        
        // In the following, all occurrences of "travel" have been replaced with "itinearyTravels"
        
//        travels = (Travel[]) getItinerary(itinearyID).getFlightIbookings().toArray();
        itinearyTravels = (FlightBooking[]) getItinerary(itinearyID).getFlightBookings().toArray();
        for (int i = 0; i < itinearyTravels.length; i++) {
            assertEquals("confirmed", itinearyTravels[i].getStatus());
        }
        assertEquals("confirmed", getItinerary(itinearyID).getHotelbookings().get(0).getStatus());

        cancelItinerary(itinearyID);

        itinearyTravels = (FlightBooking[]) getItinerary(itinearyID).getFlightBookings().toArray();

        for (int i = 0; i < itinearyTravels.length; i++) {
            assertEquals("cancelled", itinearyTravels[i].getStatus());
        }
        assertEquals("cancelled", getItinerary(itinearyID).getHotelbookings().get(0).getStatus());
        
        

    }
}