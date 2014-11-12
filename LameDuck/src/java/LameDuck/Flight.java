/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

/**
 *
 * @author Nygaard
 */
public class Flight {
    
    private int id;
    private static int nextId = 0;
    private int departureDate;
    private int departureTime;
    private int arrivalDate;
    private int arrivalTime;
    private String source;
    private String destination;
    private String airline;

    public Flight(){
        
    }
    
    public Flight(String airline, String source, int departureDate, int departureTime, String destination, int arrivalDate, int arrivalTime ) {
        nextId++;
        this.id = nextId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.source = source;
        this.destination = destination;
        this.airline = airline;
    }

    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the departureDate
     */
    public int getDepartureDate() {
        return departureDate;
    }

    /**
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(int departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * @return the departureTime
     */
    public int getDepartureTime() {
        return departureTime;
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * @return the arrivalDate
     */
    public int getArrivalDate() {
        return arrivalDate;
    }

    /**
     * @param arrivalDate the arrivalDate to set
     */
    public void setArrivalDate(int arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * @return the arrivalTime
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     * @param airline the airline to set
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }
    
    
    
}
