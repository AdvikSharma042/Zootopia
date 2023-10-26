package Admin;
import Driver.Main_Menu;
import Animal.ZooAnimal;
import Visitor.Visitor;
import java.util.*;
public class Admin {
    private final static String username = "admin";private final static String password = "admin123";
    private static final ArrayList<String> feedbacks = new ArrayList<>();
    private static final HashMap<String, Integer> discount_codes = new HashMap<>();
    private static final HashSet<Visitor> visitors = new HashSet<Visitor>();
    private static boolean login_status = false;
    private static final ArrayList<Attraction> attractions = new ArrayList<>();
    private static final HashMap<String, ArrayList<ZooAnimal>> ZooAnimals = new HashMap<>();
    public static Attraction attractionFinder(int id) {
        if (attractions.isEmpty()) {
            return null;
        }
        for (Attraction attraction : attractions) {
            if (attraction.getId() == id) {
                return attraction;
            }
        }
        System.out.println("no such attraction exists");
        return null;
    }
    public static void adminLogin() {
        System.out.println("Enter username");
        String usn = Main_Menu.stringChoice();
        System.out.println("Enter password");
        String passwd = Main_Menu.stringChoice();
        if (usn.equals(username) && passwd.equals(password)) {
            System.out.println("Successful log in now you can use admin functionalities");
            login_status = true;
        } else {
            System.out.println("Log in failed, try again");
            adminLogin();
        }
    }
    public static void addAttraction() {
        if(!login_status){ System.out.println("Login required");return;}
        System.out.println("Enter name");
        String name = Main_Menu.stringChoice();
        System.out.println("Enter status 1 for open 0 for close");
        int status = Main_Menu.RangedChoice(0,1);
        System.out.println("Enter price of the ticket");
        int ticket_price = Main_Menu.IntegerChoice();
        attractions.add(new Attraction(name, status, ticket_price));
    }
    public static void removeAttraction() {
        if(!login_status){ System.out.println("Login required");return;}
        System.out.println("Enter id of attraction you want to remove");
        int attr_id = Main_Menu.IntegerChoice();
        Attraction required_attraction = attractionFinder(attr_id);
        if (required_attraction == null) {
            System.out.println("No such attraction exists");
        } else {
            attractions.remove(required_attraction);
        }
    }
    public static void viewAttraction() {
            if (attractions.isEmpty()) {
                System.out.println("No attraction added until now");
            } else {
                for (Attraction attraction : attractions) {
                    System.out.println(attraction);
                }
            }
         }
    public static void modifyAttraction() {
        if(!login_status){ System.out.println("Login required");return;}
        System.out.println("Enter attraction id you wanna modify");
        Attraction requiredAttraction = attractionFinder(Main_Menu.IntegerChoice());
        if (requiredAttraction == null) {
            System.out.println("No such attraction exists");
        } else {
            System.out.println("1. Change name\n2. Change ticket price\n 3. Change status");int choice = Main_Menu.RangedChoice(1,3);
            if (choice==1) {
                System.out.println("Enter new name");
                requiredAttraction.setName(Main_Menu.stringChoice());
            } else if (choice == 2) {
                System.out.println("Enter new ticket price");
                requiredAttraction.setTicket_price(Main_Menu.IntegerChoice());
            } else if (choice == 3) {
                if (requiredAttraction.getStatus().equals("close")) {
                    requiredAttraction.setStatus(1);
                } else {
                    requiredAttraction.setStatus(0);
                }
            }
        }
    }
    public static void ManageAttraction(){
        System.out.println("Do you want to");
        System.out.println("1. Add attraction\n2. Remove Attraction\n3. Modify one\n4. View them");
        int choice = Main_Menu.RangedChoice(1,4);
        if(choice==1){Admin.addAttraction();}
        if(choice==2){Admin.removeAttraction();}
        if(choice==3){Admin.modifyAttraction();}
        else{Admin.viewAttraction();}
    }
    public static void addAnimal() {
        if(!login_status){ System.out.println("Login required");return;}
        if (ZooAnimals.isEmpty()) {
            ZooAnimals.put("Mammal", new ArrayList<>());
            ZooAnimals.put("Reptile", new ArrayList<>());
            ZooAnimals.put("Amphibian", new ArrayList<>());
        }
        System.out.println("Enter name of the animal:");
        String name = Main_Menu.stringChoice();
        System.out.println("Enter type of the animal:");
        String type = Main_Menu.stringChoice();
        System.out.println("Enter sound of the animal:");
        String sound = Main_Menu.stringChoice();
        System.out.println("Enter details of the animal:");
        String history = Main_Menu.stringChoice();
        while (true) {
            if (type.equals("Mammal") || type.equals("Reptile") || type.equals("Amphibian")) {
                ZooAnimals.get(new ZooAnimal(name, type, sound, history).getType()).add(new ZooAnimal(name, type, sound, history));
                break;
            } else {
                System.out.println("Enter type again: Mammal,Reptile or Amphibian");
                type = Main_Menu.stringChoice();
            }
        }
    }
    public static void modifyAnimal(String animalName){
        if(!login_status){ System.out.println("Login required");return;}
        if (ZooAnimals.isEmpty()) {
            System.out.println("No animals added till now");
            return;
        }
        for (String str : ZooAnimals.keySet()) {
            for (ZooAnimal animal : ZooAnimals.get(str)) {
                if (animal.getName().equals(animalName)) {
                        System.out.println("What do you want to modify?");
                        System.out.println("1. Name\n2. Type\n3. Sound\n4. Details");
                        int choice = Main_Menu.RangedChoice(1,4);
                        if (choice == 1) {
                            System.out.println("Enter new name ");
                            animal.setName(Main_Menu.stringChoice());break;
                        }
                        if (choice == 2) {
                            System.out.println("Enter new name ");
                            animal.setType(Main_Menu.stringChoice());break;
                        }
                        if (choice == 3) {
                            System.out.println("Enter new name ");
                            animal.setSound(Main_Menu.stringChoice());break;
                        }
                        if (choice == 4) {
                            System.out.println("Enter new details ");
                            animal.setHistory(Main_Menu.stringChoice());break;
                        }
                    return;
                }
                System.out.println("animal not found");
            }
        }
    }
    public static void removeAnimal(String animalName) {
        if(!login_status){ System.out.println("Login required");return;}
        if (ZooAnimals.isEmpty()) {
            System.out.println("No animals added till now");
            return;
        }
        for (String str : ZooAnimals.keySet()) {
            for (ZooAnimal animal : ZooAnimals.get(str)) {
                if (animal.getName().equals(animalName) && ZooAnimals.get(str).size() > 2) {
                    ZooAnimals.get(str).remove(animal);
                    return;
                }
            }
        }
        System.out.println("This animal currently doesn't exist");
    }
    public static void ManageAnimal(){
        System.out.println("Do you want to");
        System.out.println("1. Add Animal\n2. Remove Animal\n3. Modify one\n4. View them");
        int choice = Main_Menu.RangedChoice(1,4);
        if(choice==1){Admin.addAnimal();}
        if(choice==2){
            System.out.println("Enter name of animal you want to remove");
            String animalName=Main_Menu.stringChoice();
            Admin.removeAnimal(animalName);}
        if(choice==3){
            System.out.println("Enter name of animal you want to modify");
            String animalName=Main_Menu.stringChoice();
            Admin.modifyAnimal(animalName);}
        else {
            if (ZooAnimals.isEmpty()) {
                System.out.println("No animals added till now");
                return;
            }
            for (String str : ZooAnimals.keySet()) {
                for (ZooAnimal animal : ZooAnimals.get(str)) {
                    System.out.println(animal.getName()+" "+str);
                    }
                }
        }
    }
    public static void animalGuide(String animalName) {
        if (ZooAnimals.isEmpty()) {
            System.out.println("No animals added till now");
            return;
        }
        for (String str : ZooAnimals.keySet()) {
            for (ZooAnimal animal : ZooAnimals.get(str)) {
                if (animal.getName().equals(animalName)) {
                    System.out.println("Here is your " + animalName);
                    System.out.println("Would you like to 1. Feed it\n2. Know more");
                    Scanner sc1 = new Scanner(System.in);
                    if (Integer.parseInt(sc1.nextLine()) == 1) {
                        animal.makeSound();
                    } else {
                        animal.getDetails();
                    }
                }
            }
        }
    }
    public static void addVisitor(Visitor tourist) {
        if(!login_status){ System.out.println("Before registering a user, login as admin once");return;}
        visitors.add(tourist);System.out.println("Visitor successfully added");
    }
    public static Visitor log_inVisitor(String mail, String passwd) {
        if(!login_status){ System.out.println("Currently no users registered");return null;}
        if (visitors.isEmpty()) {
            System.out.println("No visitors added");
        } else {
            for (Visitor visitor : visitors) {
                if (visitor.getEmail().equals(mail) && visitor.getPassword().equals(passwd)) {
                    System.out.println("Successfully logged in");
                    return visitor;
                }
            }
            System.out.println("Sorry no such visitor");return null;
        }
        return null;
    }
    public static void removeVisitor(Visitor v) {
        if(!login_status){ System.out.println("Login required");return;}
        if (visitors.isEmpty()) {
            System.out.println("No visitors till now");
        } else {
            for (Visitor visitor : visitors) {
                if (visitor.equals(v)) {
                    visitors.remove(v);
                }
            }
        }
    }
    public static void showDiscount() {
        if (discount_codes.isEmpty()) {
            discount_codes.put("MINOR10", 10);
            discount_codes.put("SENIOR20", 20);
        }
        System.out.println("Minors get 10 percent off");
        System.out.println("Senior citizens gets 20 percent off");
        for (String code : discount_codes.keySet()) {
            System.out.println(code);
        }
    }
    public static void addFeedback(String feedback) {
        feedbacks.add(feedback);
    }
    public static void viewFeedback(){
        for(String feedback: feedbacks){
            System.out.println(feedback);
        }
    }
    public static double sellTicket(Attraction attraction, String code, Visitor tourist) {
        if(!login_status){ System.out.println("Before buying ticket admin must be registered");return -1.0;}
        double final_price = attraction.getTicket_price();
            if (discount_codes.containsKey(code)) {
                final_price = final_price * ((double) (100 - discount_codes.get(code)) / 100);
            }
            if (tourist.getTickets() > 3) {
                final_price = final_price * 0.70;
            }
            if (tourist.getTickets() == 3) {
                final_price = final_price * 0.85;
            }
            return final_price;
    }
    public static void EditDiscountCodes(){
        if(!login_status){ System.out.println("Login required");return;}
        System.out.println("Do you want to 1. Add a Code\n2. Remove a code");
        int choice = Main_Menu.RangedChoice(1,2);
        if(choice==1){
            System.out.println("Enter new code");
            String new_code=Main_Menu.stringChoice();
            System.out.println("Enter discount percentage i.e. 20 for 20%");
            int disc_amnt= Main_Menu.IntegerChoice();discount_codes.put(new_code,disc_amnt);
        }
        if(choice==2){
            System.out.println("Enter code you wish to remove");
            String code_r=Main_Menu.stringChoice();
            try
            {discount_codes.remove(code_r);}
            catch (Exception e){
                System.out.println("Such discount code doesn't exist");
            }
        }
    }
    public static void viewStats(){
        if(!login_status){ System.out.println("Login required");return;}
        if(attractions.isEmpty()){System.out.println("No attractions added");}
        double total_income=0;Attraction max_attraction = attractions.get(0);
        for (Attraction attraction: attractions){
            if(attraction.getTotal()>=max_attraction.getTotal()){
                max_attraction=attraction;
            }
            total_income=total_income+attraction.getTotal();
        }
        System.out.println("No of visitors are "+visitors.size());
        System.out.println("Total revenue generated "+total_income);
        System.out.println("The most revenue generating attraction is "+max_attraction);
    }
}
