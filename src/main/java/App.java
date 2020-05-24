import configuration.MailConfiguration;
import gui.UserInterface;
import mail.GmailMailService;

import java.util.Scanner;

//ToDo: Raporty ile raportów poszło, Watek z wysyłanymi meilami,
public class App {
    private Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    private void start() {
        MailConfiguration mailConfiguration = new MailConfiguration();
        UserInterface userInterface = new UserInterface(new GmailMailService(mailConfiguration));
        userInterface.start(scanner);
    }
}
