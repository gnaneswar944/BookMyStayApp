import java.util.*;
abstract class Room{
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;
    public Room(int numberofBeds,int squareFeet,double pricePerNight){
        this.numberOfBeds = numberofBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }
    public void displayRoomDetails(){
        System.out.println("Beds :"+numberOfBeds);
        System.out.println("size :"+squareFeet+"spft");
        System.out.println("price per night"+pricePerNight);
    }
}
class RoomInventory{
    private Map<String , Integer> roomAvailability;
    public RoomInventory(){
        roomAvailability = new HashMap<>();
        intializeInventory();
    }
    private void intializeInventory(){
        roomAvailability.put("Single",5);
        roomAvailability.put("Double",3);
        roomAvailability.put("Suite",2);
    }
    public int getAvailability(String roomType){
        return roomAvailability.get(roomType);
    }
}
class SingleRoom extends Room{
    public SingleRoom(){
        super(1,250,1500.0);
    }
}
class DoubleRoom extends Room{
    public DoubleRoom(){
        super(2,400,2500.0);
    }
}
 class SuiteRoom extends Room{
    public SuiteRoom(){
        super(3,750,5000.0);
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
    Room Single = new SingleRoom();
    Room doubleRoom = new DoubleRoom();
    Room suite = new SuiteRoom();
    RoomInventory inventory = new RoomInventory();
        System.out.println("Hotel Room Initialization\n");

        System.out.println("Single Room:");
        Single.displayRoomDetails();
        System.out.println("Available rooms:"+inventory.getAvailability("Single"));

        System.out.println("\nDouble Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available rooms: "+inventory.getAvailability("Double"));

        System.out.println("\nSuite Room:");
        suite.displayRoomDetails();

        System.out.println("available rooms: "+inventory.getAvailability("Suite"));

    }
}
