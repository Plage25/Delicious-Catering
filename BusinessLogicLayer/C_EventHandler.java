package BusinessLogicLayer;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

import DataAccessLayer.C_Database;

public class C_EventHandler extends C_Database {
    static String userEmail;
    private static Scanner input = new Scanner(System.in);

    public static boolean login() {
        System.out.print("Enter Email: ");
        userEmail = input.nextLine(); // Read user input
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
                            && (onlyDigits(userPhoneNo, 10) == true)) {
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

    public static boolean onlyDigits(String str, int n) // Check if number format input correct
    {
        for (int i = 0; i < n; i++) { // Ensure number must be 10 digits long containing only digits

            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void PlaceOrder() {

        String EventType = "";
        String EventDate = "2021/12/17"; // DATE
        String Time = "";
        String EventAdress = "";
        int AmountOfAdults = 0;
        int AmountOfKids = 0;
        int fk = 1;
        int AdultMeals = 0;
        int ChildMeals = 0;
        int AlcoholDrinks = 0;
        int NonAlcoholDrinks = 0;
        int Desserts = 0;
        String Payed = "";
        double amntOutsanding = 0;
        String decorations = "";
        double TotalPrice = 0;
        double decPrice = 0;

        int j = 1;
        while (j == 1) { // While loop Foodmenu page
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("------------------------------");
            System.out.println("Main Menu");
            System.out.println("-----Choose option below------");
            System.out.println("1. Event Details");
            System.out.println("2. Food Catering");
            System.out.println("3. Decore");
            System.out.println("4. Return / Confirm");

            int Choice = input.nextInt();
            input.nextLine(); // To avoid common scanner problem
            switch (Choice) {
                case 1:
                    System.out.println("-----------------------------------");
                    System.out.println("Please Enter the events infomation below");
                    System.out.println("-----------------------------------");
                    System.out.println("Type of Party:");
                    EventType = input.nextLine();
                    int validDate = 0;
                    while (validDate == 0) {
                        System.out.println("Enter the date of the event: yyyy-mm-dd  eg;2021-12-2");
                        EventDate = input.nextLine(); // Read user input

                        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                        LocalDate quickdate = LocalDate.now();
                        String changeToday = quickdate.toString();
                        String Day = EventDate.substring(8, 10);
                        String Month = EventDate.substring(5, 7);
                        String Year = EventDate.substring(0, 4);
                        String DateCheck = Day + " " + Month + " " + Year;

                        String Day1 = changeToday.substring(8, 10);
                        String Month1 = changeToday.substring(5, 7);
                        String Year1 = changeToday.substring(0, 4);
                        String DateCheck1 = Day1 + " " + Month1 + " " + Year1;

                        String dateBeforeString = DateCheck1;
                        String dateAfterString = DateCheck;
                        float daysBetween = 0;
                        try {
                            Date dateBefore = myFormat.parse(dateBeforeString);
                            Date dateAfter = myFormat.parse(dateAfterString);
                            long difference = dateAfter.getTime() - dateBefore.getTime();
                            daysBetween = (difference / (1000 * 60 * 60 * 24));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (daysBetween > 15) {
                            C_Database cdbd = new C_Database();
                            boolean checkdate = cdbd.checkValidDate(EventDate);
                            if (checkdate == true) {
                                validDate = 1;
                            } else {
                                System.out.println("Error!:This date is already booked.Please pick another date");
                            }
                        } else {
                            System.out.println("Error!:Booking must be made more than 15 days from today");
                        }

                    } // end date check while loop
                    System.out.println("Enter the time of the event: hh:mm  eg;19:25");
                    Time = input.nextLine(); // Read user input
                    System.out.println("Please enter the events Address: ");
                    EventAdress = input.nextLine(); // Read user input
                    System.out.println("Please enter the amount of adults that will be at the event: ");
                    AmountOfAdults = input.nextInt(); // Read user input
                    System.out.println("Please enter the amount of Kids that will be at the event: ");
                    AmountOfKids = input.nextInt(); // Read user input
                    try {
                        System.out.println("Event details saved successfully, please wait...");
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("PLease Enter the food infomation below");
                    System.out.println("Enter number of AdultMeals: ");
                    AdultMeals = input.nextInt(); // Read user input
                    System.out.println("Enter number of ChildMeals: ");
                    ChildMeals = input.nextInt(); // Read user input
                    System.out.println("Enter number of Alcohol drinks: ");
                    AlcoholDrinks = input.nextInt(); // Read user input
                    System.out.println("Enter number of NonAlcohol drinks: ");
                    NonAlcoholDrinks = input.nextInt(); // Read user input
                    System.out.println("Enter number of Desserts: ");
                    Desserts = input.nextInt(); // Read user input
                    try {
                        System.out.println("Food information saved successfully, please wait...");
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:

                    System.out.println(
                            "Delicious-Catering will provide costs at later time(Ts &Cs apply)\nPlease provide an information paragraph regarding your decor requirements below;");
                    decorations = input.nextLine();
                    try {
                        System.out.println("Decorations saved successfully, please wait...");
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    int confirm = 0;
                    while (confirm == 0) {
                        System.out.println("Choose option below");
                        System.out.println("1. Confirm and place order");
                        System.out.println("2. Wait! I want to edit my order.");
                        System.out.println("3. Return to main menu without placing order");
                        Choice = input.nextInt();
                        if (Choice == 1) {
                            C_Database cdbd = new C_Database();
                            cdbd.InputOrder(fk, EventType, EventDate, Time, EventAdress, AmountOfAdults, AmountOfKids,
                                    AdultMeals, ChildMeals, AlcoholDrinks, NonAlcoholDrinks, Desserts, Payed,
                                    amntOutsanding, TotalPrice, decorations, decPrice);
                            
                            j = 0;
                            confirm = 1;
                        } else if (Choice == 2) {
                            j = 1;
                            confirm = 1;
                        } else if (Choice == 3) {
                            j = 0;
                            confirm = 1;
                        } else {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Please choose a valid option!\n");

                        }
                    }
                    break;

                default:

                    break;
            }
        }
    }

    public static void ForgotPassword() {
        // ForgotPassword(Research actual email)//additional marks
        System.out.println("Please enter email address to receive your password");
        String email = input.nextLine();
        C_Database CDBD = new C_Database();
        CDBD.Forgotpassword(email);
    }

    public boolean DeleteOrder() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Please provide eventID / orderID you wish to delete:");
        int eventID = input.nextInt();
        C_Database CDBD = new C_Database();
        boolean deletecheck = CDBD.DeleteOrder(eventID);
        return deletecheck;
    }

    public static boolean UpdateOrder(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Please provide eventID / orderID you wish to update:");
        int eventID = input.nextInt();        

        String EventType = "";
        String EventDate = "2021/12/17"; // DATE
        String Time = "";
        String EventAdress = "";
        int AmountOfAdults = 0;
        int AmountOfKids = 0;
        int fk = 1;
        int AdultMeals = 0;
        int ChildMeals = 0;
        int AlcoholDrinks = 0;
        int NonAlcoholDrinks = 0;
        int Desserts = 0;
        String Payed = "";
        double amntOutsanding = 0;
        String decorations = "";
        double TotalPrice = 0;
        double decPrice = 0;

        int j = 1;
        while (j == 1) { // While loop Welcome page
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("---------------------------------------------------------------------");
            System.out.println("----------------------------Main Menu--------------------------------");
            System.out.println("-----Choose option below and please enter all information again------");
            System.out.println("1. Event Details");
            System.out.println("2. Food Catering");
            System.out.println("3. Decore");
            System.out.println("4. Return / Confirm");

            int Choice = input.nextInt();
            input.nextLine(); // To avoid common scanner problem
            switch (Choice) {
                case 1:
                    System.out.println("-----------------------------------");
                    System.out.println("Please enter the events infomation below");
                    System.out.println("-----------------------------------");
                    System.out.println("Type of Party:");
                    EventType = input.nextLine();
                    int validDate = 0;
                    while (validDate == 0) {
                        System.out.println("Enter the date of the event: yyyy-mm-dd  eg;2021-12-2");
                        EventDate = input.nextLine(); // Read user input

                        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                        LocalDate quickdate = LocalDate.now();
                        String changeToday = quickdate.toString();
                        String Day = EventDate.substring(8, 10);
                        String Month = EventDate.substring(5, 7);
                        String Year = EventDate.substring(0, 4);
                        String DateCheck = Day + " " + Month + " " + Year;

                        String Day1 = changeToday.substring(8, 10);
                        String Month1 = changeToday.substring(5, 7);
                        String Year1 = changeToday.substring(0, 4);
                        String DateCheck1 = Day1 + " " + Month1 + " " + Year1;

                        String dateBeforeString = DateCheck1;
                        String dateAfterString = DateCheck;
                        float daysBetween = 0;
                        try {
                            Date dateBefore = myFormat.parse(dateBeforeString);
                            Date dateAfter = myFormat.parse(dateAfterString);
                            long difference = dateAfter.getTime() - dateBefore.getTime();
                            daysBetween = (difference / (1000 * 60 * 60 * 24));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (daysBetween > 15) {
                            C_Database cdbd = new C_Database();
                            boolean checkdate = cdbd.checkValidDate(EventDate);
                            if (checkdate == true) {
                                validDate = 1;
                            } else {
                                System.out.println("Error! :This date is already booked.Please pick another date");
                            }
                        } else {
                            System.out.println("Error!:Booking must be made more than 15 days from today");
                        }

                    } // end date check while loop
                    System.out.println("Enter the time of the event: hh:mm  eg;19:25");
                    Time = input.nextLine(); // Read user input
                    System.out.println("Please enter the events Address: ");
                    EventAdress = input.nextLine(); // Read user input
                    System.out.println("Please enter the amount of adults that will be at the event: ");
                    AmountOfAdults = input.nextInt(); // Read user input
                    System.out.println("Please enter the amount of Kids that will be at the event: ");
                    AmountOfKids = input.nextInt(); // Read user input
                    try {
                        System.out.println("Event details saved successfully, please wait...");
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Please Enter the food infomation below");
                    System.out.println("Enter number of AdultMeals: ");
                    AdultMeals = input.nextInt(); // Read user input
                    System.out.println("Enter number of ChildMeals: ");
                    ChildMeals = input.nextInt(); // Read user input
                    System.out.println("Enter number of Alcohol drinks: ");
                    AlcoholDrinks = input.nextInt(); // Read user input
                    System.out.println("Enter number of NonAlcohol drinks: ");
                    NonAlcoholDrinks = input.nextInt(); // Read user input
                    System.out.println("Enter number of Desserts: ");
                    Desserts = input.nextInt(); // Read user input
                    try {
                        System.out.println("Food information saved successfully, please wait...");
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:

                    System.out.println(
                            "Delicious-Catering will provide costs at later time(Ts &Cs apply)\nPlease provide an information paragraph regarding your decor requirements below;");
                    decorations = input.nextLine();
                    try {
                        System.out.println("Decorations saved successfully, please wait...");
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    int confirm = 0;
                    while (confirm == 0) {
                        System.out.println("Choose option below");
                        System.out.println("1. Confirm and place order");
                        System.out.println("2. Wait! I want to edit my order.");
                        System.out.println("3. Return to main menu without placing order");
                        Choice = input.nextInt();
                        if (Choice == 1) {
                            C_Database cdbd = new C_Database();
                            cdbd.UpdateOrder(eventID,fk, EventType, EventDate, Time, EventAdress, AmountOfAdults, AmountOfKids,
                                    AdultMeals, ChildMeals, AlcoholDrinks, NonAlcoholDrinks, Desserts, Payed,
                                    amntOutsanding, TotalPrice, decorations, decPrice);
                            j = 0;
                            confirm = 1;
                        } else if (Choice == 2) {
                            j = 1;
                            confirm = 1;
                        } else if (Choice == 3) {
                            j = 0;
                            confirm = 1;
                        } else {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Please choose a valid option!\n");

                        }
                    }
                    break;

                default:

                    break;
            }
        }
        return false;
    }

    public void ViewOrders() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        C_Database CDBD = new C_Database();
        CDBD.ViewOrders(userEmail);
        System.out.println("Press ENTER to continue.");
             input.nextLine();//Avoid scanner error skip
             try {
                 System.in.read();
             } catch (Exception e) {
                 e.printStackTrace();
             }
    }

}