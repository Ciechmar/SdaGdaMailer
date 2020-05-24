package gui;

public class MailSubjectAndContent {
    private String adress;
    private String subject;
    private String content;

    public MailSubjectAndContent(String adress, String subject, String content) {
        this.adress = adress;
        this.subject = subject;
        this.content = content;


    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
