
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // mai chutiya hu - vedant

        System.out.println("Preparing to send message");
        System.out.println("Type in your message: ");
        String message = sc.nextLine(); // Type the message you want to send here
        System.out.println("What should your subject be?: ");
        String subject = sc.nextLine(); // Type the subject of your message
        System.out.println("Type in the email address of the receiver: ");
        String to = sc.nextLine(); // Enter the email address of the receiver
        System.out.println("Type in the email address of the sender: ");
        String from = sc.nextLine(); // Enter the email address of the sender

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
//                Scanner sc = new Scanner(System.in);
//                System.out.println("Type in sender's password here: ");
//                String pw = sc.nextLine();
//                return new PasswordAuthentication(from, pw); // Enter sender's Email address and its password
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
                Scanner sc = new Scanner(System.in);
                System.out.println("Type in sender's password here: ");
                String pw = sc.nextLine();
                return new PasswordAuthentication(from, pw); // Enter sender's Email address and its password
            }
        });

        // Composing the message
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(from); // From email

            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to))); // Adding recipient to message

            mimeMessage.setSubject(subject); // Adding subject to message

            // Attachment
            Scanner sc = new Scanner(System.in);
            System.out.println("The path of your file: ");
            String path = sc.nextLine(); // File path

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

