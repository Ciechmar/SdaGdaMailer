package gui;

import mail.Email;
import mail.MailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static final String SPACE = "---------------------------------------";
    private MailService mailService;
    private List<MailSubjectAndContent> mailList;
    private MailSubjectAndContent msc;

    //Wstrzykiwanie zależności:
    public UserInterface(MailService mailService) {
        this.mailService = mailService;
    }

    public void showMenu() {
        System.out.println("Witaj w aplikacji .::Ciechmar Mailer::.");
        System.out.println(SPACE);
        System.out.println("Wybierz co chesz zrobić:");
        System.out.println("1. Wyślij maila");
        System.out.println("2. Wyjście");
        System.out.println("3. Napisz maila bez wysłania");
        System.out.println("4. Pokaż listę meili do wysłąnia");
        System.out.println("5. Wyslij zaległe maile");
        System.out.println(SPACE);

    }


    public void listen(Scanner scanner) {

        int optionMenu = scanner.nextInt();
        scanner.nextLine();

        switch (optionMenu) {
            case 1: {
                sendEmail(scanner);
                break;
            }
            case 2: {
                System.out.println("Wychodzę z programu.");
//               Sysytem?:
                System.exit(0);
                break;
            }
            case 3: {
                MailSubjectAndContent msc = zbierzDaneDoMiela(scanner);
                mailList.add(msc);
                break;
            }
            case 4: {
                System.out.println("NA lisćie znajduje się: " + mailList.size() + " maili");
                for (MailSubjectAndContent mailSubjectAndContent : mailList) {
                    System.out.println("Mail na adres: " + mailSubjectAndContent.getAdress() + ", o temacie: " + mailSubjectAndContent.getSubject());
                }
                break;
            }

            case 5 : {
                System.out.println("Wysyłam zaległe maile");
                sendEmail(mailList);
                break;
            }
            default:
                System.out.println("Wybierz opcje : 1 - 5");
        }

    }

    private MailSubjectAndContent zbierzDaneDoMiela(Scanner scanner) {

        System.out.println("Podaj email:");
        String mail = scanner.nextLine();
        System.out.println("Podaj temat meila:");
        String subject = scanner.nextLine();
        System.out.println("Podaj treść meila:");
        String content = scanner.nextLine();
        return new MailSubjectAndContent(mail, subject, content);
    }


    private void sendEmail(Scanner scanner) {
        MailSubjectAndContent mailSubjectAndContent = zbierzDaneDoMiela(scanner);
        Email email = new Email(mailSubjectAndContent.getAdress(), mailSubjectAndContent.getSubject(), mailSubjectAndContent.getContent());
        Thread thread = new Thread(() -> mailService.sendEmail(email));
        thread.start();
    }

    private void sendEmail(List <MailSubjectAndContent> maillist) {
        for (MailSubjectAndContent mailFromList : maillist) {
            Email email = new Email(mailFromList.getAdress(), mailFromList.getSubject(), mailFromList.getContent());
            Thread thread = new Thread(() -> mailService.sendEmail(email));
            thread.start();
        }
    }

    public void start(Scanner scanner) {
        mailList = new ArrayList<>();


        while (true) {
            showMenu();
            listen(scanner);
        }
    }
}
