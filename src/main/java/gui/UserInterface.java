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
    private int meilCounter;

    //Wstrzykiwanie zależności:
    public UserInterface(MailService mailService) {
        this.mailService = mailService;
    }

    public void showMenu() {
        System.out.println("Witaj w aplikacji .::Ciechmar Mailer::.");
        System.out.println(SPACE);
        System.out.println("Wybierz co chesz zrobić:");
        System.out.println("1. Wyślij maila");
        System.out.println("2. Napisz maila bez wysłania");
        System.out.println("3. Pokaż listę meili do wysłąnia");
        System.out.println("4. Wyslij zaległe maile");
        System.out.println("5. Wyjście");
        System.out.println("6. Raport");
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
                MailSubjectAndContent msc = zbierzDaneDoMiela(scanner);
                mailList.add(msc);
                break;
            }
            case 3: {
                System.out.println("Na lisćie znajduje się: " + mailList.size() + " maili");
                for (MailSubjectAndContent mailSubjectAndContent : mailList) {
                    System.out.println("Mail na adres: " + mailSubjectAndContent.getAdress() + ", o temacie: " + mailSubjectAndContent.getSubject());
                }
                break;
            }

            case 4: {
                if (mailList.size() != 0) {
                    System.out.println("Wysyłam zaległe maile");
                    sendEmail(mailList);
                } else {
                    System.out.println("Nie ma zaległych maili");
                }

                break;
            }

            case 5: {
                System.out.println("Wychodzę z programu.");
//               Sysytem?:
                System.exit(0);
                break;
            }

            case 6: {
                System.out.println("Wysłąno dzisiaj : " + meilCounter + " e-mail'i");
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
        meilCounter++;
    }

    private void sendEmail(List<MailSubjectAndContent> maillist) {
        for (MailSubjectAndContent mailFromList : maillist) {
            Email email = new Email(mailFromList.getAdress(), mailFromList.getSubject(), mailFromList.getContent());
            Thread thread = new Thread(() -> mailService.sendEmail(email));
            thread.start();
            meilCounter++;
        }
        maillist.clear();
    }

    public void start(Scanner scanner) {
        meilCounter = 0;
        mailList = new ArrayList<>();
        while (true) {
            showMenu();
            listen(scanner);
        }
    }
}
