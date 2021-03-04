package BusinessLogicLayer;

import java.util.Scanner;
import java.util.regex.Pattern;

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
        int IncorrectDetails = 1;

        while (IncorrectDetails == 1) {

            System.out.println("======Register Menu======");
            System.out.println("Enter Name");
            String userName = input.nextLine(); // Read user input
            System.out.println("Enter Surname");
            String userSurname = input.nextLine(); // Read user input

            if (!(userName.isEmpty()) && !(userSurname.isEmpty())) {// Upcoming are use of nested if's
                System.out.println("Enter Email");
                String userEmail = input.nextLine(); // Read user input

                if (isValidEmail(userEmail)) {
                    System.out.println("Enter Phone Number");
                    String userPhoneNo = input.nextLine(); // Read user input
                    if ((userPhoneNo.length() == 10) && (userPhoneNo.startsWith("0"))
                            && (onlyDigits(userPhoneNo) == true)) {
                        System.out.println("Enter Password");
                        String userPassword = input.nextLine(); // Read user input
                        System.out.println("Enter Your Password again");
                        String userPasswordMatch = input.nextLine(); // Read user input
                        if ((userPassword.equals(userPasswordMatch)) && !(userPassword.isEmpty())
                                && !(userPasswordMatch.isEmpty())) {
                            IncorrectDetails = 0;
                            C_Database C_DBD = new C_Database();
                            C_DBD.Register(userName, userSurname, userEmail, userPassword, userPhoneNo);
                        } else {

                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Passwords do not match\n");
                        }
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Number format not correct!Try again\n");
                    }

                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Email address format not correct!Try again\n");
                    ;
                }
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Username or surname format not correct!Try again");
            }

        }
        return true;
    }

    public static boolean isValidEmail(String email) { // Check if email is correct format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean onlyDigits(String str) // Check if number format input correct
    {
        for (int i = 0; i < 10; i++) { // Ensure number must be 10 digits long containing only digits

            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean PlaceOrder() {

        int j = 1;
        while (j == 1) { // While loop Welcome page
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("------------------------------");
            System.out.println("Welcome to Delicious Catering!");
            System.out.println("-----Choose option below------");
            System.out.println("1. Event Details");
            System.out.println("2. Food Catering");
            System.out.println("3. Drinks Catering");
            System.out.println("4. Decore");
            System.out.println("5. Return");

            int Choice = input.nextInt();
            switch (Choice) {
                case 1:
                    System.out.println("PLease Enter the events infomation below");
                    System.out.println("Type of Party: ");
                    String EventType = input.next(); // Read user input
                    System.out.println("Enter the date of the event: ");
                    String EventDate = input.next(); // Read user input
                    System.out.println("Please enter the events Address: ");
                    String EventAdress = input.next(); // Read user input
                    System.out.println("Please enter a contact number for the event: ");
                    String ContactNumber = input.next(); // Read user input
                    break;
                case 2:
                    System.out.println("Please enter the amount of adults that will be at the event: ");
                    String AmountOfAdults = input.next(); // Read user input
                    System.out.println("Please enter the amount of Kids that will be at the event: ");
                    String AmountOfKids = input.next(); // Read user input
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    j = 0;
                    break;

                default:
                    break;
            }
        }
        return false;
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