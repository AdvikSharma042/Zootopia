package Animal;
public class ZooAnimal implements Animal{
    private  String name;
    private  String type;
    private  String sound;
    private String History;
    public ZooAnimal(String name, String type, String sound, String history) {
        this.name = name;
        this.type = type;
        this.sound = sound;
        History = history;
    }
    @Override
    public void makeSound() {
        System.out.println(this.sound);
    }
    @Override
    public void getDetails() {
        System.out.println(this.name);
        System.out.println("------------------------");
        System.out.println(this.History);
        System.out.println("------------------------");
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setSound(String sound) {
        this.sound = sound;
    }
    public void setHistory(String history) {
        History = history;
    }
}
