/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmapping.ws;

import flightlist.travelgood.ws.Travel;
import flightlist.travelgood.ws.TravelOptions;
import javax.jws.WebService;
import lameduck.FlightOption;

/**
 *
 * @author srnjcbsn
 */
@WebService(serviceName = "flightMappingService", portName = "flightMappingPort", endpointInterface = "ws.flightmapping.FlightMappingPortType", targetNamespace = "http://flightMapping.ws", wsdlLocation = "WEB-INF/wsdl/FlightMappingService/flightMapping.wsdl")
public class FlightMappingService {

    public flightlist.travelgood.ws.TravelOptions flightMappingOperation(lameduck.GetFlightsResponse lameDuckFlightList) {
        TravelOptions retval = new TravelOptions();
        
        for (FlightOption flight : lameDuckFlightList.getReturn())
        {
            Travel travel = new Travel();
            
            flightlist.travelgood.ws.Flight tgFligth = new flightlist.travelgood.ws.Flight();
            tgFligth.setCarrier(flight.getFlight().getAirline());
            tgFligth.setFromAirport(flight.getFlight().getSource());
            tgFligth.setToAirport(flight.getFlight().getDestination());
            tgFligth.setTimeTakeOff(flight.getFlight().getDepartureTime());
            tgFligth.setTimeLanding(flight.getFlight().getArrivalTime());
            
            travel.setFlight(tgFligth);
            travel.setBookingNumber(flight.getBookingNumber());
            travel.setPrice(flight.getPrice());
            travel.setReservationService(flight.getAirlineReservationService());
            
            retval.getTravels().add(travel);
        }
        
        return retval;
    }
    
}
