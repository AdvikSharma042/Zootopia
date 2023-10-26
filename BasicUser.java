package Visitor;
import Admin.*;import Driver.Main_Menu;

import java.util.HashMap;

public class BasicUser extends PremiumUser{
    private boolean login_status = false;
    int ticket_count=0;
    private final HashMap<Integer,Double> tickets_bought = new HashMap<>();
    public BasicUser(String name, String email, String password, int age, int phone_number, double balance) {
        super(name, email, password, age, phone_number, balance);
    }
    @Override
    public void viewDiscount(){Admin.showDiscount();}
    public String applyDiscount(){
        if(!login_status){ System.out.println("Login required");return "invalid code";}
        viewDiscount();return Main_Menu.stringChoice();
    }
    @Override
    public Visitor BuyMembership() {
        if(!login_status){ System.out.println("Login required");return this;}
        System.out.println("You want to get premium membership ?Press y for yes n for no");
        String resp=Main_Menu.stringChoice();
        if(resp.equals("n")){
            System.out.println("Transaction cancelled");return this;
        }
        else if(resp.equals("y")){
            PremiumUser modified_visitor = new PremiumUser(this.getName(),this.getEmail(),this.getPassword(),
                    this.getAge(),this.getPhone_number(),this.getBalance());
            modified_visitor.setBalance(modified_visitor.getBalance()-50);
            Admin.removeVisitor(this);
            Admin.addVisitor(modified_visitor);return modified_visitor;
        }
        else{
            System.out.println("Enter a valid response y/n that is");return BuyMembership();
        }
    }
    @Override
    public void BuyTicket() {
        if(!login_status){ System.out.println("Login required");return;}
        Admin.viewAttraction();
        int attraction_id = Main_Menu.IntegerChoice();
        String disc_code=applyDiscount();
        if(disc_code.equals("MINOR10") && this.getAge()>=18){disc_code="invalid code";};
        if(disc_code.equals("SENIOR20") && this.getAge()<60){disc_code="invalid code";};
        if(tickets_bought.containsKey(attraction_id)){
            System.out.println("Already have the ticket");
        }
        Attraction attraction = Admin.attractionFinder(attraction_id);if(attraction==null){
            System.out.println("No such attraction sorry");
        } else{
            double price = Admin.sellTicket(attraction,disc_code,this);
            if(price>this.getBalance()){System.out.println("Not enough balance available");}
            else if(price != -1){attraction.updateCustCnt();tickets_bought.put(attraction_id,price);ticket_count++;}}}
    @Override
    public void visitAttraction(){
        if(!login_status){ System.out.println("Login required");return;}
        System.out.println("Enter attraction id");
        Admin.viewAttraction();
        int id=Main_Menu.IntegerChoice();
        if(tickets_bought.containsKey(id)){
            if(Admin.attractionFinder(id)==null){
                System.out.println("Seems the attraction you are looking for was removed");
                double refundPrice = tickets_bought.get(id);setBalance(refundPrice);
            }
            else if(Admin.attractionFinder(id).getStatus() ==  "close"){ //taken care of in creating an attraction
                System.out.println("Sorry it is currently close try again later");
            }
            else{
                tickets_bought.remove(id);
                System.out.println("Attraction visited");
            }
        }
        else{
            System.out.println("Don't have ticket to this attraction");
        }
    }
    public int getTickets(){return ticket_count;}
}
