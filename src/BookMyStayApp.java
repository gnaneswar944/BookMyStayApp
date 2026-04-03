import java.io.*;
import java.util.*;

public class BookMyStayApp {

    static class RoomInventory {
        private Map<String, Integer> availability;

        public RoomInventory() {
            availability = new HashMap<>();
        }

        public void addRoom(String roomType, int count) {
            availability.put(roomType, count);
        }

        public void setRoomCount(String roomType, int count) {
            availability.put(roomType, count);
        }

        public int getAvailableCount(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }

        public Map<String, Integer> getAvailability() {
            return availability;
        }
    }

    static class FilePersistenceService {
        public void saveInventory(RoomInventory inventory, String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Map.Entry<String, Integer> entry : inventory.getAvailability().entrySet()) {
                    writer.write(entry.getKey() + "=" + entry.getValue());
                    writer.newLine();
                }
                System.out.println("Inventory saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving inventory.");
            }
        }

        public void loadInventory(RoomInventory inventory, String filePath) {
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("No valid inventory data found. Starting fresh.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean hasData = false;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=");
                    if (parts.length == 2) {
                        String roomType = parts[0];
                        int count = Integer.parseInt(parts[1]);
                        inventory.setRoomCount(roomType, count);
                        hasData = true;
                    }
                }

                if (!hasData) {
                    System.out.println("No valid inventory data found. Starting fresh.");
                }
            } catch (Exception e) {
                System.out.println("No valid inventory data found. Starting fresh.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("System Recovery");

        String filePath = "inventory.txt";

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        persistenceService.loadInventory(inventory, filePath);

        if (inventory.getAvailability().isEmpty()) {
            inventory.addRoom("Single", 5);
            inventory.addRoom("Double", 3);
            inventory.addRoom("Suite", 2);
        }

        System.out.println("\nCurrent Inventory:");
        System.out.println("Single: " + inventory.getAvailableCount("Single"));
        System.out.println("Double: " + inventory.getAvailableCount("Double"));
        System.out.println("Suite: " + inventory.getAvailableCount("Suite"));

        persistenceService.saveInventory(inventory, filePath);
    }
}