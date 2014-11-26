/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import niceview.NiceViewFault_Exception;
import niceview.Reservation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author planaspa
 */
public class NiceViewJUnitTest {
    
    public NiceViewJUnitTest() {
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
    public void getHotelsTest(){
        XMLGregorianCalendar dateArrival = null;
        XMLGregorianCalendar dateDeparture = null;
        try{
        GregorianCalendar arrival = new GregorianCalendar(2014,12,12);
        GregorianCalendar departure = new GregorianCalendar(2014,12,14) {};
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        dateArrival = datatypeFactory.newXMLGregorianCalendar(arrival);
        dateDeparture = datatypeFactory.newXMLGregorianCalendar(departure);
        List<Reservation> hotels = getHotels("Bangkok", dateArrival, dateDeparture);
        for (Reservation hotel : hotels){
            System.out.println(hotel.getName());
            System.out.println("=============");
            System.out.printf("Booking number = %d%n", hotel.getBookingNumber());
            System.out.printf("Total price = %d%n", hotel.getTotalPrice());
            System.out.printf("Address = %s%n", hotel.getAddress());
            System.out.printf("Hotel Reservation service = %s%n", hotel.getHotelReservationService());
            System.out.printf("CreditCardGuarantee = %b%n", hotel.isCreditCardGuarantee());

        }
        }
        catch (Exception er){
           er.getStackTrace();
           System.out.println("Error: "+er.getMessage());
        }
    }
    
    @Test
    public void CreditCardNotGuaranteeHotelTest (){
        try {
            XMLGregorianCalendar dateArrival = null;
            XMLGregorianCalendar dateDeparture = null;
            GregorianCalendar arrival = new GregorianCalendar(2014,12,12);
            GregorianCalendar departure = new GregorianCalendar(2014,12,14) {};
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            dateArrival = datatypeFactory.newXMLGregorianCalendar(arrival);
            dateDeparture = datatypeFactory.newXMLGregorianCalendar(departure);
            List<Reservation> hotels = getHotels("Bangkok", dateArrival, dateDeparture);
            Reservation fhotel = null;
            for (Reservation hotel : hotels){
                if (!hotel.isCreditCardGuarantee())
                    fhotel = hotel;
            }
            if (fhotel != null){
                boolean output = bookHotel(fhotel.getBookingNumber(),null);
                assertTrue(output);
                System.out.println("Reserved");
                cancelHotel(fhotel.getBookingNumber());
                System.out.println("Cancelled");
            }
            else {
                System.out.println("There is not hotel with such features");
                assertTrue (false);
            }
        } catch (NiceViewFault_Exception ex) {
            Logger.getLogger(NiceViewJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(NiceViewJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void CreditCardGuaranteeHotelTest (){
        try {
            XMLGregorianCalendar dateArrival = null;
            XMLGregorianCalendar dateDeparture = null;
            GregorianCalendar arrival = new GregorianCalendar(2014,12,12);
            GregorianCalendar departure = new GregorianCalendar(2014,12,14) {};
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            dateArrival = datatypeFactory.newXMLGregorianCalendar(arrival);
            dateDeparture = datatypeFactory.newXMLGregorianCalendar(departure);
            List<Reservation> hotels = getHotels("Bangkok", dateArrival, dateDeparture);
            Reservation fhotel = null;
            for (Reservation hotel : hotels){
                if (!hotel.isCreditCardGuarantee())
                    fhotel = hotel;
            }
            
            
            dk.dtu.imm.fastmoney.types.CreditCardInfoType cc = 
                    new dk.dtu.imm.fastmoney.types.CreditCardInfoType();
  
            cc.setName("Thor-Jensen Claus");
            cc.setNumber("50408825");
            ExpirationDateType expirationDate = new ExpirationDateType();
            expirationDate.setMonth(5);
            expirationDate.setYear(9);
            cc.setExpirationDate(expirationDate);
            if (fhotel != null){
            boolean output = bookHotel(fhotel.getBookingNumber(),cc);
            assertTrue(output);
            System.out.println("Reserved");
            cancelHotel(fhotel.getBookingNumber());
            System.out.println("Cancelled");
            }
            else {
                System.out.println("There is not hotel with such features");
                assertTrue (false);
            }
        } catch (NiceViewFault_Exception ex) {
            Logger.getLogger(NiceViewJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(NiceViewJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private static void cancelHotel(int bookingNumber) throws NiceViewFault_Exception {
        niceview.NiceViewService service = new niceview.NiceViewService();
        niceview.NiceViewWebService port = service.getNiceViewWebServicePort();
        port.cancelHotel(bookingNumber);
    }

    private static java.util.List<niceview.Reservation> getHotels(java.lang.String city, javax.xml.datatype.XMLGregorianCalendar arrival, javax.xml.datatype.XMLGregorianCalendar departure) {
        niceview.NiceViewService service = new niceview.NiceViewService();
        niceview.NiceViewWebService port = service.getNiceViewWebServicePort();
        return port.getHotels(city, arrival, departure);
    }

    private static boolean bookHotel(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws NiceViewFault_Exception {
        niceview.NiceViewService service = new niceview.NiceViewService();
        niceview.NiceViewWebService port = service.getNiceViewWebServicePort();
        return port.bookHotel(bookingNumber, creditCardInfo);
    }
    
}