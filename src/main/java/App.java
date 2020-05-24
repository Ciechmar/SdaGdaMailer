import configuration.MailConfiguration;
import gui.UserInterface;
import mail.GmailMailService;

import java.util.Scanner;

//ToDo: Zrobić listę meili i wysyłac kilak na raz, Raporty ile raportów poszło, Watek z wysyłanymi meilami,  w listen() dodać 3 cas napisz meila bez wysylania, 4 wyślij zaległe meile.
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

//        userInterface.showMenu();
//        userInterface.listen(scanner);
    }


}
