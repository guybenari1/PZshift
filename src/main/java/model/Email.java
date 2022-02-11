package model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void sendCodeByEmail(Worker worker) {
        int randomPassword = generateRandomPassword();
        Message message = codeByEmail(SESSION, worker.getEmail(), randomPassword);
        try {
            Transport.send(message);
            worker.setPassword(randomPassword);
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

    public static boolean checkValidEmail(String email){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        return mat.matches();
    }
}