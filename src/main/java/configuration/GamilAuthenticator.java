package configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;



public class GamilAuthenticator extends Authenticator {
    private final String username;
    private final String password;

    public GamilAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
