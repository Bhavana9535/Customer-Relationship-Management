package in.co.crm.Utility;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import in.com.crm.Exception.ApplicationException;
public class EmailUtility {

	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	static ResourceBundle rb = ResourceBundle.getBundle("system");
    private static Properties props = new Properties();
   static {
	   props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		props.put("mail.smtp.timeout", "5000");
		props.put("mail.smtp.writetimeout", "5000");
		props.put("mail.smtp.connectiontimeout", "5000");
   }
   public static void sendMail(EmailMessage emailMessageDTO) throws ApplicationException{
      final String sender_Email = "mukesh.ahir151997@gmail.com";
      final String sender_email_pass = "akvvrnkatfbsrnga";
     System.out.println("mail11");
       try {
           // Connection to Mail Server
           Session session = Session.getDefaultInstance(props,
                   new javax.mail.Authenticator() {
                       protected PasswordAuthentication getPasswordAuthentication() {
                           return new PasswordAuthentication(sender_Email,sender_email_pass);
                       }
                   });
           System.out.println("mail22222");
           session.setDebug(true);
           // Create a message
           Message msg = new MimeMessage(session);
           InternetAddress addressFrom = new InternetAddress(sender_Email);
           msg.setFrom(addressFrom);
           // Set TO addresses
           System.out.println("mail222220000");
           String[] emailIds = new String[0];
           if (emailMessageDTO.getTo() != null) {
               emailIds = emailMessageDTO.getTo().split(",");
           }
           System.out.println("mail111122222");
           // Set CC addresses
           String[] emailIdsCc = new String[0];
           if (emailMessageDTO.getCc() != null) {
               emailIdsCc = emailMessageDTO.getCc().split(",");
           }
           System.out.println("0000mail22222");
           // Set BCC addresses
           String[] emailIdsBcc = new String[0];
           if (emailMessageDTO.getBcc() != null) {
               emailIdsBcc = emailMessageDTO.getBcc().split(",");
           }
           System.out.println("4444mail22222");
           InternetAddress[] addressTo = new InternetAddress[emailIds.length];
           for (int i = 0; i < emailIds.length; i++) {
               addressTo[i] = new InternetAddress(emailIds[i]);
           }
           System.out.println("55555mail22222");
           InternetAddress[] addressCc = new InternetAddress[emailIdsCc.length];
           for (int i = 0; i < emailIdsCc.length; i++) {
               addressCc[i] = new InternetAddress(emailIdsCc[i]);
           }
           System.out.println("666666mail22222");
           InternetAddress[] addressBcc = new InternetAddress[emailIdsBcc.length];
           for (int i = 0; i < emailIdsBcc.length; i++) {
               addressBcc[i] = new InternetAddress(emailIdsBcc[i]);
           }
           if (addressTo.length > 0) {
               msg.setRecipients(Message.RecipientType.TO, addressTo);
           }
           if (addressCc.length > 0) {
               msg.setRecipients(Message.RecipientType.CC, addressCc);
           }
           if (addressBcc.length > 0) {
               msg.setRecipients(Message.RecipientType.BCC, addressBcc);
           }
           System.out.println("7777mail22222");
           // Setting the Subject and Content Type
           msg.setSubject(emailMessageDTO.getSubject());
           // Set message MIME type
           switch (emailMessageDTO.getMessageType()) {
           case EmailMessage.HTML_MSG:
               msg.setContent(emailMessageDTO.getMessage(), "text/html");
               break;
           case EmailMessage.TEXT_MSG:
               msg.setContent(emailMessageDTO.getMessage(), "text/plain");
               break;
           }
           System.out.println("mail333");
           // Send the mail
           Transport.send(msg);
       } catch (Exception ex) {
           ex.printStackTrace();
       } 
   }
}
