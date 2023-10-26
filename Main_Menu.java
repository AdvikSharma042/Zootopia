package Driver;
import java.util.Scanner;
import Admin.*;
import Visitor.*;
public class Main_Menu {
    public static int IntegerChoice(){
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter a integer corresponding to choices");
        try {
            return Integer.parseInt(sc1.nextLine());
        }
        catch (Exception e){
            System.out.println("Enter an integer");
            return IntegerChoice();
        }
    }
    public static int RangedChoice(int lower_bound,int upper_bound){
        int choice = IntegerChoice();
        if(choice>=lower_bound && choice<=upper_bound){
            return choice;
        }
        else{
            System.out.println("Please enter a valid input");
            return RangedChoice(lower_bound,upper_bound);
        }
    }
    public static String stringChoice(){
        Scanner sc1 = new Scanner(System.in);String inp=sc1.nextLine();
        if(inp.isEmpty()){
            System.out.println("Don't enter a empty string ");
            return stringChoice();
        }
        else{
            return inp;
        }
    }
    public static void AdminMenu(){
        System.out.println("""
                1. Login as Admin
                2. Manage or Schedule Attractions
                3. Manage Animals
                4. Edit Discount Codes
                5. View Visitor Stats
                6. View Feedback
                7. Exit""");
        int choice = RangedChoice(1,8);
        if(choice==1){
            Admin.adminLogin();
        }
        else if(choice==2){
            Admin.ManageAttraction();
        }
        else if(choice==3){
            Admin.ManageAnimal();
        }
        else if(choice==4){
            Admin.EditDiscountCodes();
        }
        else if(choice==5){
            Admin.viewStats();
        }
        else if(choice == 6){
            Admin.viewFeedback();
        }
        if(choice != 7){
            AdminMenu();
        }
        MainMenu();
    }
    public static void VisitorChoices(Visitor curr_user){
        System.out.println("""
                1. Buy Membership
                2. Buy Tickets
                3. Visit Animals
                4. Visit Attractions
                5. Leave Feedback
                6. Log Out
                """);
        int choice = Main_Menu.RangedChoice(1,6);
            if (choice == 1) {
                curr_user = curr_user.BuyMembership();
            } else if (choice == 2) {
                System.out.println("How many tickets you want to buy");
                int num = Main_Menu.IntegerChoice();
                for (int i = 0; i < num; i = i + 1) {
                    curr_user.BuyTicket();
                }
            } else if (choice == 3) {
                curr_user.visitAnimal();
            } else if (choice == 4) {
                curr_user.visitAttraction();
            } else if (choice == 5) {
                curr_user.giveFeedback();
            }
            if (choice != 6) {
                VisitorChoices(curr_user);
            }
        MainMenu();
    }
    public static void VisitorMenu(){
        System.out.println("Do you want to 1. Register a user\n2. Log in");
        int choice = Main_Menu.RangedChoice(1,2);
        if(choice==1){
            System.out.println("Enter name: ");String name = Main_Menu.stringChoice();
            System.out.println("Enter mail: ");String mail = Main_Menu.stringChoice();
            System.out.println("Enter password: ");String password = Main_Menu.stringChoice();
            System.out.println("Enter age: ");int age = Main_Menu.IntegerChoice();
            System.out.println("Enter phone number: ");int phone_num = Main_Menu.IntegerChoice();
            System.out.println("Enter balance: ");int balance = Main_Menu.IntegerChoice();
            Visitor curr_user=new NoMembershipVisitor(name,mail,password,age,phone_num,balance);
            curr_user.register_visitor();
        }
        else{
            System.out.println("Enter mail");
            String mail = Main_Menu.stringChoice();
            System.out.println("Enter Password");
            String password = Main_Menu.stringChoice();
            Visitor curr_user=Admin.log_inVisitor(mail,password);
            if(curr_user==null){
                System.out.println("Wrong details");
                VisitorMenu();
            }
            else {
                curr_user.setLoginStatus(true);
                VisitorChoices(curr_user);
            }
        }
    }
    public static void MainMenu(){
        System.out.println("1. Enter as admin\n2. Enter as a Visitor\n3. Exit");
        int choice = Main_Menu.RangedChoice(1,3);
        if(choice==1){
            AdminMenu();
        }
        if (choice==2){
            VisitorMenu();
        }
        if (choice==3){
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        MainMenu();
    }
}
