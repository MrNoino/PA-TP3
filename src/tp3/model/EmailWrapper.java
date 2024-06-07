package tp3.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailWrapper {

    private String sender;
    private String pwd;
    private String host;
    private String port;
    private Session session;

    public EmailWrapper(){
        this.sender = "a2020126392@alunos.estgoh.ipc.pt";
        this.host = "smtp.estgoh.ipc.pt";
        this.port = "465";
        if(!readEmailProperties())
            return;
        this.setProperties();
    }

    public EmailWrapper(String sender, String pwd, String host, String port) {
        this.sender = sender;
        this.pwd = pwd;
        this.host = host;
        this.port = port;

        this.setProperties();
    }
    
    private boolean readEmailProperties(){
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

    public boolean sendMail(String recipient, String subject, String body) {
        if(this.pwd == null)
            return false;
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
