/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wshop.mail;

/**
 *
 * @author MohamedAmine
 */
public class Mail {
    
    
   
    private String mailAddressSender ;
    private String pwd ;
    private String mailAddressRecipient ; 
    private String mailObject ;
    private String mailSubject ;

    public String getMailAddressSender() {
        return mailAddressSender;
    }

    public void setMailAddressSender(String mailAddressSender) {
        this.mailAddressSender = mailAddressSender;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMailAddressRecipient() {
        return mailAddressRecipient;
    }

    public void setMailAddressRecipient(String mailAddressRecipient) {
        this.mailAddressRecipient = mailAddressRecipient;
    }

    public String getMailObject() {
        return mailObject;
    }

    public void setMailObject(String mailObject) {
        this.mailObject = mailObject;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }
   
  
    

    
    
}
