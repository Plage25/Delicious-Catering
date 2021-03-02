package BusinessLogicLayer;

import java.util.Scanner;

import DataAccessLayer.C_Database;

public class C_EventHandler extends C_Database {

    private static Scanner input = new Scanner(System.in);

    public static boolean login() {
        System.out.print("Enter Email: ");
        String userEmail = input.nextLine(); // Read user input
        System.out.print("Enter Password: ");
        String userPassword = input.nextLine(); // Read user input

        C_Database C_DBD = new C_Database();
        boolean LoginStatus = C_DBD.LoginCheck(userEmail, userPassword);
        return LoginStatus;
    }

    public static boolean Register() {
        System.out.println("Enter Name");
        String userName = input.nextLine(); // Read user input
        System.out.println("Enter Surname");
        String userSurname = input.nextLine(); // Read user input
        System.out.println("Enter Email");
        String userEmail = input.nextLine(); // Read user input
        System.out.println("Enter Phone Number");
        String userPhoneNo = input.nextLine(); // Read user input
        System.out.println("Enter Password");
        String userPassword = input.nextLine(); // Read user input
        System.out.println("Enter Your Password again");
        String userPasswordMatch = input.nextLine(); // Read user input

        return true;
    }

    public static void ForgotPassword() {
        // ForgotPassword(Research actual email)//additional marks
    }

    /////////////////////////////////////////////////////////////////////////////////////
    public class Client // Client login
    {

    }

    public class Menu // food menu
    {

    }

    interface Bookings // Booking information
    {
        public void Details();

        public void NumberOfAdults();

        public void NumberOfChildrene();

        public void AdultMeals();

        public void ChildreneMeals();

        public void Drinks();

        public void Desserts();

        public void payments();

        public void Decor();
    }

    public void Details() {

    }

    public void NumberOfAdults() {

    }

    public void NumberOfChildrene() {

    }

    public void AdultMeals() {

    }

    public void ChildreneMeals() {

    }

    public void Drinks() {

    }

    public void Desserts() {

    }

    public void payments() {

    }

    public void Decor() {

    }

    public void UpdateMeals() {

    }

    public void ViewBookings() {

    }

    public void Notification() {

    }
}