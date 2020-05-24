package mail;

import configuration.MailConfiguration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailMailService implements MailService {
    private final MailConfiguration mailConfiguration;

    public GmailMailService(MailConfiguration mailConfiguration) {
        this.mailConfiguration = mailConfiguration;
    }

    @Override
    public void sendEmail(Email email) {
        Session session = mailConfiguration.createSession();

//        Musimy go uzupełnić wiadomością do wywsyłania poczty.
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setText(email.getContent());
            mimeMessage.setSubject(email.getSubject());
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getMailAddress()));
            Transport.send(mimeMessage);
            System.out.println("Wiadomość wysłana");
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
