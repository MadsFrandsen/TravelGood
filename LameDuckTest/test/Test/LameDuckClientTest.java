/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import lameduck.FlightOption;
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
    
    private CreditCardInfoType cc = new CreditCardInfoType();
    
    
    public LameDuckClientTest() {               
        cc.setName("Tick Joachim");
        cc.setNumber("50408824");
        ExpirationDateType expirationDate = new ExpirationDateType();
        expirationDate.setMonth(2);
        expirationDate.setYear(11);
        cc.setExpirationDate(expirationDate);
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
    public void testGetFlight() throws DatatypeConfigurationException, LameDuckException_Exception {
        GregorianCalendar gregDate = new GregorianCalendar(2014,11,24);
        
        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar date = df.newXMLGregorianCalendar(gregDate);
        System.out.println(date.getDay() + "." + date.getMonth() + "." + date.getYear() + " " + date.getHour() + ":" + date.getMinute());
        
        List<FlightOption> flights = getFlights("CPH", "BKK", date);
        
        System.out.println(flights.size());
        assertEquals(flights.size(), 3);
        
        System.out.println("--------------------");
        
    }

    @Test
    public void testBookFlight() throws DatatypeConfigurationException, LameDuckException_Exception {
        
        GregorianCalendar gregDate = new GregorianCalendar(2014,11,24);
        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar date = df.newXMLGregorianCalendar(gregDate);
        List<FlightOption> flights = getFlights("CPH", "BKK", date);
        FlightOption myFlight = flights.get(0);
        
        int bookingNumber = myFlight.getBookingNumber();
        
        System.out.println("Booking nr.: " + bookingNumber);
        System.out.println("Price: " + myFlight.getPrice());
        System.out.println("Booking Flight ...");
        
        boolean booked = bookFlight(bookingNumber, cc);
        
        assertTrue(booked);
        
        System.out.println("--------------------");
        
    }

    @Test
    public void testCancelFlight() throws DatatypeConfigurationException, LameDuckException_Exception {
        
        GregorianCalendar gregDate = new GregorianCalendar(2014,11,24);
        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar date = df.newXMLGregorianCalendar(gregDate);
        List<FlightOption> flights = getFlights("CPH", "BKK", date);
        FlightOption myFlight = flights.get(0);
        
        int bookingNumber = myFlight.getBookingNumber();
        int price = myFlight.getPrice();
        System.out.println("Booking nr.: " + bookingNumber);
        System.out.println("Price: " + price);
        System.out.println("Booking Flight ...");
        
        
        boolean booked = bookFlight(bookingNumber, cc);
        System.out.println(booked);
        System.out.println("--------------------");
        
        assertTrue(booked);
        
        System.out.println("Canceling Flight ...");
        boolean cancled = cancelFlight(bookingNumber, price, cc);
        
        assertTrue(cancled);
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