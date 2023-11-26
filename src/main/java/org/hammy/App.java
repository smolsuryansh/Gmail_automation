package org.hammy;

        import javax.mail.*;
        import javax.mail.internet.InternetAddress;
        import javax.mail.internet.MimeMessage;
        import java.util.Properties;

public class App {
    public static void main(String[] args) {

        System.out.println("Preparing to send message");
        String message = "Hello! This message was sent to annoy you.";
        String subject = "Annoy";
        String to = "suryanshdubey2525@gmail.com";
        String from = "cuteboihamster@gmail.com";

        sendEmail(message, subject, to, from);
    }

    // Resposible method for sending emails
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
                return new PasswordAuthentication("cuteboihamster@gmail.com", "ggwr ucrs jkda sgiu ");
            }
        });

        // Composing the message
        MimeMessage mimeMessage = new MimeMessage(session);

        try{
            mimeMessage.setFrom(from); // From email

            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to))); // Adding receipient to message

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

