package dtu.ws.travelgood.SOAPTest;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * B (booking fails) Plan an itinerary with three bookings (mixed flights amd
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
public class B {

    /**
     *
     */
    public B() {
    }

    /**
     *
     */
    @Test
    public void testB() {
    }
}