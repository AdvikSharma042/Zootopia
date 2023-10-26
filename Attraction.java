package Admin;

import Visitor.Visitor;

public class Attraction {
    private  String name;
    private int status;
    private int ticket_price;
    private int cust_count;
    private final int id;
    private static int curr_id =1;

    public Attraction(String name, int status, int ticketPrice) {
        this.name = name;
        this.status = status;
        ticket_price = ticketPrice;
        cust_count = 0;
        this.id = curr_id;
        curr_id++;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        if(status==1){
            return "open";
        }
        else{
            return "close";
        }
    }
    public int getTicket_price() {
        return ticket_price;
    }

    public int getCust_count() {
        return cust_count;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTicket_price(int ticket_price) {
        this.ticket_price = ticket_price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void updateCustCnt(){this.cust_count++;}
    public int getTotal(){
        return this.cust_count*this.ticket_price;
    }

    @Override
    public String toString() {
        return ("No of visitors are "+this.getCust_count()+"\nTicket price is "+this.getTicket_price()
        +"\n Total revenue generated is "+this.getTotal());
    }
}
