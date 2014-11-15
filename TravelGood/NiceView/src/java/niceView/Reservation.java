/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView;

import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author planaspa
 */
@XmlRootElement
@XmlType(propOrder = {"hotelInfo", "bookingNumber", "totalPrice"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation {
   // @XmlElement
    private Hotel hotelInfo;
    //@XmlElement
    private int bookingNumber;
    //@XmlElement
    private int totalPrice;
    @XmlTransient
    private dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo;
    @XmlTransient
    private dk.dtu.imm.fastmoney.types.AccountType account;

    public Reservation(){
        
    }
    
    public Reservation(Hotel hotelInfo, int bookingNumber, int totalPrice) {
        this.hotelInfo = hotelInfo;
        this.bookingNumber = bookingNumber;
        this.totalPrice = totalPrice;
        this.creditCardInfo = null;
        this.account = null;
    }

    public Hotel getHotelInfo() {
        return hotelInfo;
    }

    public String getName() {
        return hotelInfo.getName();
    }

    public String getAddress() {
        return hotelInfo.getAddress();
    }

    public boolean isCreditCardGuarantee() {
        return hotelInfo.isCreditCardGuarantee();
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
 
    public void setHotelInfo(Hotel hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
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
    
    public AccountType getAccount() {
        return account;
    }

    public void setAccount(AccountType account) {
        this.account = account;
    }  
    
}
