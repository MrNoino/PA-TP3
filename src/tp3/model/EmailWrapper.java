package tp3.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * A class to manage the email client
 */
public class EmailWrapper {

    private String sender;
    private String pwd;
    private String host;
    private String port;
    private Session session;

    /**
     * Class constructor that uses a default email client
     */
    public EmailWrapper() {
        this.sender = "a2020126392@alunos.estgoh.ipc.pt";
        this.host = "smtp.estgoh.ipc.pt";
        this.port = "465";
        if (!readEmailProperties()) {
            return;
        }
        this.setProperties();
    }

    /**
     * Class constructor that requires the email information
     *
     * @param sender the email that will send a message
     * @param pwd the password used to login to the email
     * @param host the email host address
     * @param port the port to be used by the email client
     */
    public EmailWrapper(String sender, String pwd, String host, String port) {
        this.sender = sender;
        this.pwd = pwd;
        this.host = host;
        this.port = port;

        this.setProperties();
    }

    /**
     * Gets the email properties in the saved file with the credentials
     *
     * @return information was read
     */
    private boolean readEmailProperties() {
        File file = new File("email.cfg.dat");
        if (!file.exists()) {
            this.pwd = null;
            return false;
        }
        try {
            ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(file));
            ArrayList<String> emailConfig = (ArrayList<String>) objectStream.readObject();
            objectStream.close();
            this.sender = emailConfig.get(0);
            this.pwd = emailConfig.get(1);
        } catch (ClassNotFoundException | IOException e) {
            this.pwd = null;
            return false;
        }
        return true;
    }

    /**
     * Sets the email properties
     */
    private void setProperties() {
        Properties properties = System.getProperties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        this.session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, pwd);
            }
        });
    }

    /**
     * Sends an email
     *
     * @param recipient the email to which the email will be sent too
     * @param subject the email subject
     * @param body the email content
     * @return email was sent
     */
    public boolean sendMail(String recipient, String subject, String body) {
        if (this.pwd == null) {
            return false;
        }
        try {
            MimeMessage message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress(this.sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            return true;
        } catch (MessagingException mex) {
            return false;
        }
    }

}
