package PresentationLayer;

import java.util.Scanner;

import BusinessLogicLayer.C_EventHandler;
import DataAccessLayer.C_Database;

//Email = RahealDeliciousCatering@gmail.com
//Password = DeliciousCatering
public class OwnerInput extends C_EventHandler {
    Scanner input = new Scanner(System.in);
    C_Database cdbd = new C_Database();
    C_EventHandler ev = new C_EventHandler();

    public void Activate() {
        int j = 1;
        while (j == 1) { // While loop Main Menu page
            DisplayOwnerMenu();
            int Choice = input.nextInt();

            switch (Choice) {
            case 1:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                ev.DisplayPastOrders();
                break;

            case 2:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                ev.DisplayUpcomingOrders();
                break;

            case 3:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                ev.UpdateInvoice();
                break;

            case 4:
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

    public void DisplayOwnerMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("=================");
        System.out.println("     Welcome     ");
        System.out.println("=================");
        System.out.println("1. View past orders");
        System.out.println("2. View upcoming orders");
        System.out.println("3. Invoice Orders");
        System.out.println("4. Delete Order");
        System.out.println("5. Exit");
    }

}
