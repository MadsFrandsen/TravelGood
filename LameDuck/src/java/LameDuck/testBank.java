/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

import fastmoney.imm.dtu.dk.AccountType;
import fastmoney.imm.dtu.dk.CreditCardFaultMessage;
import fastmoney.imm.dtu.dk.CreditCardInfoType;
import fastmoney.imm.dtu.dk.ExpirationDateType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nygaard
 */
public class testBank {
    
    public static void main(String[] args) {
        
        AccountType LAME_DUCK_ACCOUNT = new AccountType();
        LAME_DUCK_ACCOUNT.setName("LameDuck");
        LAME_DUCK_ACCOUNT.setNumber("50208812");
        
        CreditCardInfoType cc = new CreditCardInfoType();
        cc.setName("Tick Joachim");
        cc.setNumber("50408824");
        ExpirationDateType expirationDate = new ExpirationDateType();
        expirationDate.setMonth(2);
        expirationDate.setYear(11);
        cc.setExpirationDate(expirationDate);
        
        try {
            boolean booked = chargeCreditCard(5, cc, 100, LAME_DUCK_ACCOUNT);
            boolean canceled = refundCreditCard(5, cc, 0, LAME_DUCK_ACCOUNT);
        } catch (CreditCardFaultMessage ex) {
            Logger.getLogger(testBank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static boolean chargeCreditCard(int group, fastmoney.imm.dtu.dk.CreditCardInfoType creditCardInfo, int amount, fastmoney.imm.dtu.dk.AccountType account) throws CreditCardFaultMessage {
        fastmoney.imm.dtu.dk.BankService service = new fastmoney.imm.dtu.dk.BankService();
        fastmoney.imm.dtu.dk.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean refundCreditCard(int group, fastmoney.imm.dtu.dk.CreditCardInfoType creditCardInfo, int amount, fastmoney.imm.dtu.dk.AccountType account) throws CreditCardFaultMessage {
        fastmoney.imm.dtu.dk.BankService service = new fastmoney.imm.dtu.dk.BankService();
        fastmoney.imm.dtu.dk.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }
    
}
