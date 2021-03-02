package PresentationLayer;

import BusinessLogicLayer.C_EventHandler;
import DataAccessLayer.C_Database;
import java.util.Scanner;

public class UserInput extends C_EventHandler {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Integer Option=0;

        C_Database connect = new C_Database();
        connect.Connect(); // Call method to connect database to the project
        int i = 1;
        while (i == 1) { //While loop Welcome page

            DisplayWelcomePage();
            int Option = input.nextInt(); // Read user input

            switch (Option) {
                case 1:
                    boolean LogCheck = C_EventHandler.login();
                    if (LogCheck == true) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Login Successful!Please wait");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i = 0; // Breaks loop and continue to next section
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Login not found!Press ENTER to continue.");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case 2:
                    boolean RegCheck = C_EventHandler.Register();
                    if (RegCheck = true) {
                        i = 0; // Breaks loop and continue to next section
                    } else {
                        System.out.println("Login not found!Press ENTER to continue.");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case 3:
                    C_EventHandler.ForgotPassword();

                    break;
                case 4: // Close applicaiton
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option chosen!Press ENTER to continue and try again.");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }//end of while loop Welcome page
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Next Menu.Welcome! Answer Upcoming Questions:");


    }

    public static void DisplayWelcomePage() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("------------------------------");
        System.out.println("Welcome to Delicious Catering!");
        System.out.println("-----Choose option below------");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Forgot Password");
        System.out.println("4. Exit");
    }

}
