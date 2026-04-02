import java.util.*;

public class BookMyStayApp {

    // -------- Room Class --------
    static class Room {
        String roomType;
        int beds;
        int size;
        double price;

        Room(String roomType, int beds, int size, double price) {
            this.roomType = roomType;
            this.beds = beds;
            this.size = size;
            this.price = price;
        }
    }

    // -------- Inventory Class --------
    static class RoomInventory {
        Map<String, Integer> availability = new HashMap<>();

        void addRoom(String type, int count) {
            availability.put(type, count);
        }

        Map<String, Integer> getAvailability() {
            return availability; // read-only usage
        }
    }

    // -------- Search Service --------
    static void searchRooms(RoomInventory inventory,
                            Room single,
                            Room dbl,
                            Room suite) {

        Map<String, Integer> avail = inventory.getAvailability();

        System.out.println("Room Search\n");

        if (avail.getOrDefault("Single", 0) > 0) {
            System.out.println("Single Room:");
            System.out.println("Beds: " + single.beds);
            System.out.println("Size: " + single.size + " sqft");
            System.out.println("Price: " + single.price);
            System.out.println("Available: " + avail.get("Single"));
            System.out.println();
        }

        if (avail.getOrDefault("Double", 0) > 0) {
            System.out.println("Double Room:");
            System.out.println("Beds: " + dbl.beds);
            System.out.println("Size: " + dbl.size + " sqft");
            System.out.println("Price: " + dbl.price);
            System.out.println("Available: " + avail.get("Double"));
            System.out.println();
        }

        if (avail.getOrDefault("Suite", 0) > 0) {
            System.out.println("Suite Room:");
            System.out.println("Beds: " + suite.beds);
            System.out.println("Size: " + suite.size + " sqft");
            System.out.println("Price: " + suite.price);
            System.out.println("Available: " + avail.get("Suite"));
        }
    }

    // -------- Main Method --------
    public static void main(String[] args) {

        // Create rooms
        Room single = new Room("Single", 1, 250, 1500);
        Room dbl = new Room("Double", 2, 400, 2500);
        Room suite = new Room("Suite", 3, 750, 5000);

        // Create inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 5);
        inventory.addRoom("Double", 3);
        inventory.addRoom("Suite", 2);

        // Search (READ ONLY)
        searchRooms(inventory, single, dbl, suite);
    }
}