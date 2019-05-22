package controllers.UserController;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Email {
    public void sendEmail(String reciever)throws Exception{
        Properties props = new Properties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        String sender ="Club.Estival.SFHZY@gmail.com";
        String password="clubestival123";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender,password);
            }
        });

        Multipart emailContent =new MimeMultipart();
        MimeBodyPart textbodypart = new MimeBodyPart();
        String htmlCode ="<H1 style=\"color: #e36d00; font-family: Arial; text-align: center; background-color: #eeeeee ; border-radius:10px; width: 20em;\">Club Estival</H1>\n" +
                "\t<H2 style=\"color: #e36d00; font-family: Arial; text-align: center; background-color: #eeeeee ; border-radius:10px; width: 20em; margin-left: 3.5em;\">Confirmation de réservation</H2> ";
        textbodypart.setContent(htmlCode,"text/html");

        MimeBodyPart pdfattachement = new MimeBodyPart();
        pdfattachement.attachFile("./newfacture.pdf");

        emailContent.addBodyPart(textbodypart);
        emailContent.addBodyPart(pdfattachement);


        Message message = prepareMessage(session,sender,reciever);
        message.setContent(emailContent);
        Transport.send(message);
        
    }

    private static Message prepareMessage(Session session,String sender,String reciever) throws Exception{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sender"));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(reciever));
        message.setSubject("Facture de votre réservation au club estival");
        return message;
    }

}
