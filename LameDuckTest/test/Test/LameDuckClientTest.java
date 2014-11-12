/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import lameduck.LameDuckException_Exception;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nygaard
 */
public class LameDuckClientTest {
    
    public LameDuckClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testGetFlight(){
        
        
    }
    
    @Test
    public void testBookFlight(){
        
    }
    
    @Test
    public void testCancelFlight(){
        
    }

    private static boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard) throws LameDuckException_Exception {
        lameduck.LameDuckWebService_Service service = new lameduck.LameDuckWebService_Service();
        lameduck.LameDuckWebService port = service.getLameDuckWebServicePort();
        return port.bookFlight(bookingNumber, creditCard);
    }

    private static boolean cancelFlight(int bookingNumber, int price, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard) throws LameDuckException_Exception {
        lameduck.LameDuckWebService_Service service = new lameduck.LameDuckWebService_Service();
        lameduck.LameDuckWebService port = service.getLameDuckWebServicePort();
        return port.cancelFlight(bookingNumber, price, creditCard);
    }

    private static java.util.List<lameduck.FlightOption> getFlights(java.lang.String from, java.lang.String to, javax.xml.datatype.XMLGregorianCalendar date) throws LameDuckException_Exception {
        lameduck.LameDuckWebService_Service service = new lameduck.LameDuckWebService_Service();
        lameduck.LameDuckWebService port = service.getLameDuckWebServicePort();
        return port.getFlights(from, to, date);
    }
}