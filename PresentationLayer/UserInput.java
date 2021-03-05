package PresentationLayer;

import BusinessLogicLayer.C_EventHandler;
import DataAccessLayer.C_Database;

import java.util.Date;
import java.util.Scanner;

public class UserInput extends C_EventHandler {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Integer Option=0;

        C_Database connect = new C_Database();
        connect.Connect(); // Call method to connect database to the project

        int i = 1;
        while (i == 1) { // While loop Welcome page

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
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    Boolean RegCheck = C_EventHandler.Register();
                    if (RegCheck = true) {
                        System.out.println("Register successful! Please wait 2 seconds.");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Register not successful!Please wait 2 seconds and try again");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
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
        } // end of while loop Welcome page

        int j = 1;
        while (j == 1) { // While loop Welcome page
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ClientView();
            int Choice = input.nextInt();
            switch (Choice) {
                case 1:
                    boolean placeOrder = C_EventHandler.PlaceOrder();
                    break;
                case 2:

                    break;
                case 3:
                    
                    C_EventHandler ev = new C_EventHandler();
                    Boolean DeleteCheck = ev.DeleteOrder();
                    if (DeleteCheck == true) {
                        System.out.print("\033[H\033[2J");
                    System.out.flush();
                        System.out.println("Delete successful! Please wait 2 seconds.");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.print("\033[H\033[2J");
                    System.out.flush();
                        System.out.println("Incorrect ID.Delete not successful! Please wait 4 seconds and try again");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 4:

                    break;
                case 5:
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.exit(0);
                    break;

                default:
                    break;
            }
        }

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

    public static void ClientView() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("-----Choose option below------");
        System.out.println("1. Place a Order");
        System.out.println("2. Update a Order");
        System.out.println("3. Delete a Order");
        System.out.println("4. View Orders");
        System.out.println("5. Exit");
    }

}
