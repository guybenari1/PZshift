package model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class Email {
    private static String SENDER = "PZshiftmail@gmail.com";
    private static String PASSWORD = "EZPZlemonsquize";
    private static Properties PROPERTIES = new Properties();
    private static Session SESSION;
    private String receiver;

    public Email(String receiver) {
        this.receiver = receiver;
        setProperties(PROPERTIES);
        SESSION = Session.getInstance(PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, PASSWORD);
            }
        });
    }

    public void sendCodeByEmail() {
        int randomPassword = generateRandomPassword();
        Message message = codeByEmail(SESSION, receiver, randomPassword);
        try {
            Transport.send(message);
            System.out.println("code message sent");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendTextByEmail(String text) {
        Message message = textByEmail(SESSION, receiver, text);
        try {
            Transport.send(message);
            System.out.println("text message sent");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void setProperties (Properties properties){
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.username", SENDER);
        properties.put("mail.smtp.password", PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
    }

    private int generateRandomPassword(){
        Random random = new Random();
        return (random.nextInt(9000) + 1000);
    }

    private Message codeByEmail(Session session, String receiver, int randomPassword) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(SENDER));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject("password for you");
            message.setText("hi, \nthe password is: " + randomPassword );
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Message textByEmail(Session session, String receiver, String text) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(SENDER));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject("email from PZshift boss");
            message.setText(text);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}