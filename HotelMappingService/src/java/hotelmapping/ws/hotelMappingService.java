/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmapping.ws;

import hotellist.travelgood.ws.AccommodationOptions;
import hotellist.travelgood.ws.Stay;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import niceview.GetHotelsResponse;
import niceview.Reservation;

/**
 *
 * @author srnjcbsn
 */
@WebService(serviceName = "hotelMappingService", portName = "hotelMappingPort", endpointInterface = "org.netbeans.j2ee.wsdl.hotelmappingservice.java.hotelmapping.HotelMappingPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/HotelMappingService/java/hotelMapping", wsdlLocation = "WEB-INF/wsdl/hotelMappingService/hotelMapping.wsdl")
public class hotelMappingService {

    public hotellist.travelgood.ws.AccommodationOptions mapHotelList(GetHotelsResponse niceViewHotelList, XMLGregorianCalendar checkIn, XMLGregorianCalendar checkOut) 
    {
        AccommodationOptions accomodations = new AccommodationOptions();
        
        for (Reservation reservation : niceViewHotelList.getReturn())
        {
            Stay stay = new Stay();
            
            stay.setCheckIn(checkIn);
            stay.setCheckOut(checkOut);

            stay.setAddress(reservation.getAddress());
            stay.setBookingNumber(reservation.getBookingNumber());
            stay.setCreditCardRequired(reservation.isCreditCardGuarantee());
            stay.setPrice(reservation.getTotalPrice());
            stay.setName(reservation.getName());
            stay.setReservationServiceName(reservation.getHotelReservationService());
            
            accomodations.getStays().add(stay);
        }
        
        return accomodations;
    }
    
}
