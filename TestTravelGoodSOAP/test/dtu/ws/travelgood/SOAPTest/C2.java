package dtu.ws.travelgood.SOAPTest;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * C2 (cancelling fails) Create an itinerary with three bookings and book it.
 * Make sure that the booking status is confirmed for each entry. During
 * cancelling of the trip, the cancellation of the second booking should fail.
 * Check that the cancelling resulted in an error condition (e.g. value of
 * status variable, exception, HTTP status code). Get the itinerary and check
 * that the returned itinerary has cancelled as the first and third booking and
 * confirmed for the second booking.
 *
 * @author Jonas Karlsson (S143341)
 * @return
 */
public class C2 {

    /**
     *
     */
    public C2() {
    }

    /**
     *
     */
    @Test
    public void testC2() {
    }
}