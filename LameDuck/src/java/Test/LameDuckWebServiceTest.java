/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import LameDuck.FlightOption;
import LameDuck.LameDuckWebService;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Nygaard
 */
public class LameDuckWebServiceTest {
    
    public static void main(String[] args) throws Exception{
    
    LameDuckWebService ws = new LameDuckWebService("flightdata.csv");
    GregorianCalendar cal = new GregorianCalendar(1, 12, 2014);
    ArrayList<FlightOption> flights = ws.getFlights("CPH", "SFO", cal);
    
    }
    
}
