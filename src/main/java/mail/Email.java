package mail;

public class Email {
    private final String mailAddress;
    private final String subject;
    private final String content;
    private boolean isSend;

    public Email(String mailAddress, String subject, String content) {
        this.mailAddress = mailAddress;
        this.subject = subject;
        this.content = content;
        isSend = false;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }
}
