/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;

/**
 *
 * @author planaspa
 */
public class FinalReservation {
    
    private int bookingNumber;
    private int totalPrice;
    private CreditCardInfoType creditCardInfo;

    public FinalReservation(int bookingNumber, int totalPrice, CreditCardInfoType creditCardInfo) {
        this.bookingNumber = bookingNumber;
        this.totalPrice = totalPrice;
        this.creditCardInfo = creditCardInfo;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CreditCardInfoType getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(CreditCardInfoType creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }
    
    
}
