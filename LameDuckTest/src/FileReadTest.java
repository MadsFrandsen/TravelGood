
import java.io.File;
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
            System.out.println(flightInfo[2]);
            String airline = flightInfo[0];
            String source = flightInfo[1];
            int departureDate = Integer.parseInt(flightInfo[2]);
            int departureTime = Integer.parseInt(flightInfo[3]);
            String destination = flightInfo[4];
            int arrivalDate = Integer.parseInt(flightInfo[5]);
            int arrivalTime = Integer.parseInt(flightInfo[6]);
            

        }

    }
    
}
