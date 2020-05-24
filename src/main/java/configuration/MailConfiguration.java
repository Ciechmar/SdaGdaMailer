package configuration;


import javax.mail.Session;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailConfiguration {
    //    Dane login, hasło powinny być w pliku, na raiz erobimy to w kodzie potem przepisz do pliku
//   Klasa do przechowywanai konfiguracji ale mogła by być mapa:
    private Properties properties;
    private String username;
    private String password;

    public MailConfiguration() {
        prepareConfiguration();

    }

    // Te dane są wzięte z dokumentacji gmail:
    private void prepareConfiguration() {
        properties = new Properties();
        try (FileReader reader = new FileReader("mail.properties")) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        username = properties.getProperty("mail.username");
        password = properties.getProperty("mail.password");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//        username = "*********";
//        password = "********";


    }

    //    sesja wysyłana meila:
    public Session createSession() {
        return Session.getDefaultInstance(properties, new GamilAuthenticator(username, password));

    }
}
