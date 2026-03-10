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

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("Hotel Room Initialization\n");

        System.out.println("Single Room:");
        Single.displayRoomDetails();
        System.out.println("Available: " + singleAvailable);

        System.out.println("\nDouble Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailable);

        System.out.println("\nSuite Room:");
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailable);

    }
}
