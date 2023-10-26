package Visitor;
public abstract class Visitor {
    private boolean login_status=false;
    private final String name;
    private final String email;
    private final String password;
    private final int age;
    private final int phone_number;
    private double balance;

    public Visitor(String name, String email, String password, int age, int phone_number, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phone_number = phone_number;
        this.balance = balance;
    }
    public abstract void register_visitor();
    public abstract Visitor BuyMembership();
    public abstract void BuyTicket();
    public abstract void viewDiscount();
    public abstract String applyDiscount();
    public abstract void visitAnimal();
    public abstract void visitAttraction();
    public abstract void giveFeedback();
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getPhone_number() {
        return phone_number;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public abstract int getTickets();
    public void setLoginStatus(boolean login_status) {
        this.login_status = login_status;
    }
}
