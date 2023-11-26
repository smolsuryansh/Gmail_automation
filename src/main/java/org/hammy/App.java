
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class App {
    public static void main(String[] args) {

        System.out.println("Preparing to send message");
        String message = "Hello! This message was sent to annoy you.";
        String subject = "Annoy";
        String to = "suryanshdubey2525@gmail.com"; // Enter the email address of the receiver
        String from = "cuteboihamster@gmail.com"; // Enter the email address of the sender

        sendEmail(message, subject, to, from);
    }

    // Responsible method for sending emails
    private static void sendEmail(String message, String subject, String to, String from) {
        // Gmail host
        String host = "smtp.gmail.com";

        // Fetching system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES: " + properties);

        // Setting important information to properties object
        // Hosting set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Getting the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cuteboihamster@gmail.com", "ggwr ucrs jkda sgiu "); // Enter sender's Email address and its password
            }
        });

        // Composing the message
        MimeMessage mimeMessage = new MimeMessage(session);

        try{
            mimeMessage.setFrom(from); // From email

            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to))); // Adding recipient to message

            mimeMessage.setSubject(subject); // Adding subject to message

            mimeMessage.setText(message); // Adding text to message


            // Sending message
            Transport.send(mimeMessage);
            System.out.println("Send successfully!");

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

