
import java.io.File;
import java.util.GregorianCalendar;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nygaard
 */
public class FileReadTest {
    
    public static void main(String[] args) throws Exception {

        String flightData = "/Users/Nygaard/Code/GitHub/TravelGood/LameDuck/src/java/LameDuck/flightsdata.csv";

        File f = new File(flightData);
        Scanner in = new Scanner(f);
        String headers = in.nextLine(); // skip first line as it is only headers
        while (in.hasNext()) {
            String[] flightInfo = in.nextLine().split(";"); // Excel data is seperated by ;
            
            String airline = flightInfo[0];
            String source = flightInfo[1];
            String departureDate = flightInfo[2];
            String departureTime = flightInfo[3];
            String destination = flightInfo[4];
            String arrivalDate = flightInfo[5];
            String arrivalTime = flightInfo[6];
            
            /* Convert string date to Gregorian Calendar */
        String departureDay = departureDate.substring(0, 2);
        String departureMonth = departureDate.substring(2, 4);
        String departureYear = departureDate.substring(4, 8);
        String departureHour = departureTime.substring(0, 2);
        String departureMinute = departureTime.substring(2, 4);
        System.out.println(departureDay+departureMonth+departureYear);
        GregorianCalendar departureCalendar = new GregorianCalendar(Integer.parseInt(departureYear), Integer.parseInt(departureMonth), Integer.parseInt(departureDay), Integer.parseInt(departureHour), Integer.parseInt(departureMinute));
        
        String arrivalDay = departureDate.substring(0, 2);
        String arrivalMonth = departureDate.substring(2, 4);
        String arrivalYear = departureDate.substring(4, 8);
        String arrivalHour = arrivalDate.substring(0, 2);
        String arrivalMinute = arrivalDate.substring(2, 4);
        GregorianCalendar arrivalCalendar = new GregorianCalendar(Integer.parseInt(arrivalYear), Integer.parseInt(arrivalMonth), Integer.parseInt(arrivalDay), Integer.parseInt(arrivalHour), Integer.parseInt(arrivalMinute));
        
        System.out.println(departureCalendar.getTime().toString());
        
        //this.source = source;
        //this.destination = destination;
        //this.airline = airline;
            

        }

    }
    
}
