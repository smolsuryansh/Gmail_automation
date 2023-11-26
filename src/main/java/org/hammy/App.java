
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class App {
    public static void main(String[] args) {

        System.out.println("Preparing to send message");
        String message = "Hello! This message was sent to annoy you."; // Type the message you want to send here
        String subject = "Annoy"; // Type the subject of your message
        String to = "receiver_emailaddress"; // Enter the email address of the receiver
        String from = "sender_emailaddress"; // Enter the email address of the sender

//        sendEmail(message, subject, to, from); // Uncomment if you want to send only text and vice versa
        sendAttach(message, subject, to, from); // Comment if you want to send text with attachment and vice versa
    }

    // Responsible method for sending emails for sending emails
//    private static void sendEmail(String message, String subject, String to, String from) {
//        // Gmail host
//        String host = "smtp.gmail.com";
//
//        // Fetching system properties
//        Properties properties = System.getProperties();
//        System.out.println("PROPERTIES: " + properties);
//
//        // Setting important information to properties object
//        // Hosting set
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//
//        // Getting the session object
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("sender_email", "sender_password"); // Enter sender's Email address and its password
//            }
//        });
//
//        // Composing the message
//        MimeMessage mimeMessage = new MimeMessage(session);
//
//        try{
//            mimeMessage.setFrom(from); // From email
//
//            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to))); // Adding recipient to message
//
//            mimeMessage.setSubject(subject); // Adding subject to message
//
//            mimeMessage.setText(message); // Adding text to message
//
//
//
//
//            // Sending message
//            Transport.send(mimeMessage);
//            System.out.println("Sent successfully!");
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//    }

    // Responsible for sending text with attachments
    private static void sendAttach(String message, String subject, String to, String from) {
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
                return new PasswordAuthentication("sender_emailaddress", "sender_password"); // Enter sender's Email address and its password
            }
        });

        // Composing the message
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(from); // From email

            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to))); // Adding recipient to message

            mimeMessage.setSubject(subject); // Adding subject to message

            // Attachment
            String path = "attachment_path"; // File path

            MimeMultipart mimeMultipart = new MimeMultipart();

            mimeMessage.setContent(mimeMultipart);

            // Text
            MimeBodyPart textMime = new MimeBodyPart();

            // File
            MimeBodyPart fileMime = new MimeBodyPart();

            try {
                textMime.setText(message);

                File file = new File(path);
                fileMime.attachFile(file);

                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Sending message
            Transport.send(mimeMessage);
            System.out.println("Sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

