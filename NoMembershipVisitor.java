package Visitor;
import Admin.Admin;
import Driver.Main_Menu;

import java.util.Scanner;
public class NoMembershipVisitor extends PremiumUser{

    public NoMembershipVisitor(String name, String email, String password, int age, int phone_number, int balance) {
        super(name, email, password, age, phone_number, balance);
    }
    @Override
    public Visitor BuyMembership() {
        System.out.println("Do you want 1. Premium membership(50rs.)\n2. Basic Membership(30rs.)");
        int resp = Main_Menu.RangedChoice(1, 2);
        if (resp == 2) {
            BasicUser modified_visitor = new BasicUser(this.getName(), this.getEmail(), this.getPassword(), this.getAge()
                    , this.getPhone_number(), this.getBalance());
            Admin.removeVisitor(this);
            Admin.addVisitor(modified_visitor);
            modified_visitor.setBalance(modified_visitor.getBalance() - 30);
            return modified_visitor;
        } else{
            PremiumUser modified_visitor = new PremiumUser(this.getName(), this.getEmail(), this.getPassword(),
                    this.getAge(), this.getPhone_number(), this.getBalance());
            modified_visitor.setBalance(modified_visitor.getBalance() - 50);
            Admin.removeVisitor(this);
            Admin.addVisitor(modified_visitor);
            return modified_visitor;
        }
    }
    @Override
    public void BuyTicket() {
        System.out.println("Have a membership to buy a ticket");
    }
    @Override
    public void visitAttraction() {
        System.out.println("Cannot visit any attraction because you don't have any membership");
    }
    public void visitAnimal(){
        System.out.println("cannot visit animals, you don't have a membership");
    }
}
