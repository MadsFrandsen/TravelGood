/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;

/**
 * @author Pablo Lanaspa Ferrer
 * 
 */
public class FinalReservation {
    
    private int bookingNumber;
    private int totalPrice;
    private CreditCardInfoType creditCardInfo;
    private boolean isCreditCardGuarantee;

    public FinalReservation(int bookingNumber, int totalPrice, CreditCardInfoType creditCardInfo, boolean isCreditCardGuarantee) {
        this.bookingNumber = bookingNumber;
        this.totalPrice = totalPrice;
        this.creditCardInfo = creditCardInfo;
        this.isCreditCardGuarantee = isCreditCardGuarantee;     
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

    public boolean isIsCreditCardGuarantee() {
        return isCreditCardGuarantee;
    }

    public void setIsCreditCardGuarantee(boolean isCreditCardGuarantee) {
        this.isCreditCardGuarantee = isCreditCardGuarantee;
    }
    
    
}
