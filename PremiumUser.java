package Visitor;
import Admin.Admin;import Driver.Main_Menu;
import java.util.Scanner;

public class PremiumUser extends Visitor {
    private boolean login_status = false;

    public PremiumUser(String name, String email, String password, int age, int phone_number, double balance) {
        super(name, email, password, age, phone_number, balance);
    }

    @Override
    public void register_visitor() {
        Admin.addVisitor(this);
    }

    @Override
    public Visitor BuyMembership() {
        if(!login_status){ System.out.println("Login required");return this;}
        System.out.println("You want to get basic membership ?Press y for yes n for no");
        String resp = Main_Menu.stringChoice();
        if (resp.equals("n")) {
            System.out.println("Transaction cancelled");
            return this;
        } else if (resp.equals("y")) {
            BasicUser modified_visitor = new BasicUser(this.getName(), this.getEmail(), this.getPassword(), this.getAge()
                    , this.getPhone_number(), this.getBalance());
            Admin.removeVisitor(this);
            Admin.addVisitor(modified_visitor);
            return modified_visitor;
        } else {
            System.out.println("Enter a valid answer y/n that is ");
            return BuyMembership();
        }
    }
    @Override
    public void BuyTicket() {
        System.out.println("That is for basic users only");
    }

    @Override
    public void viewDiscount() {
        System.out.println("That is for basic users only");
    }

    @Override
    public String applyDiscount() {
        if(!login_status){ System.out.println("Login required");return "invalid code";}
        System.out.println("That is for basic users only");
        return null;
    }
    @Override
    public void visitAnimal() {
        if(!login_status){ System.out.println("Login required");return;}
        System.out.println("Enter animal you want to visit");
        String animalName = Main_Menu.stringChoice();
        Admin.animalGuide(animalName);
    }

    @Override
    public void visitAttraction() {
        if(!login_status){ System.out.println("Login required");return;}
        System.out.println("Enter attraction id");
        Admin.viewAttraction();
        if (Admin.attractionFinder(Main_Menu.IntegerChoice()) == null) {
            System.out.println("Wrong attraction id");
        } else if (Admin.attractionFinder(Main_Menu.IntegerChoice()).getStatus() == "close") {
            System.out.println("Attraction closed try again later");
        } else {
            System.out.println("Attraction visited");
            Admin.attractionFinder(Main_Menu.IntegerChoice()).updateCustCnt();
        }
    }

    @Override
    public void giveFeedback() {
        if(!login_status){ System.out.println("Login required");return;}
        Scanner sc1 = new Scanner(System.in);
        Admin.addFeedback(sc1.nextLine());
    }
    public void setBalance(double balance) {
        super.setBalance(balance);
    }
    @Override
    public int getTickets() {
        return 0;
    }
    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public String getEmail() {
        return super.getEmail();
    }
    @Override
    public String getPassword() {
        return super.getPassword();
    }
    @Override
    public int getAge() {
        return super.getAge();
    }
    @Override
    public int getPhone_number() {
        return super.getPhone_number();
    }
    @Override
    public double getBalance() {
        return super.getBalance();
    }
    public static void main(String[] args) {
        PremiumUser premiumUser = new PremiumUser("adv", "advmail", "adv123", 14, 900, 500);
        Admin.addAnimal();
        premiumUser.visitAnimal();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }  //reference equality
        if (obj == null || getClass() != obj.getClass()) return false;
        Visitor v = (Visitor) obj;
        return (v.getName().equals(getName()) && v.getEmail().equals(getEmail()) &&
                v.getPassword().equals(getPassword()) && v.getAge() == getAge() && v.getBalance() == getBalance());
    }
}
