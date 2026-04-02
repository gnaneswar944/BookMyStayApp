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

        public void increaseRoom(String roomType) {
            availability.put(roomType, availability.getOrDefault(roomType, 0) + 1);
        }

        public int getAvailableCount(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }
    }

    static class CancellationService {
        private Stack<String> releasedRoomIds;
        private Map<String, String> reservationRoomTypeMap;

        public CancellationService() {
            releasedRoomIds = new Stack<>();
            reservationRoomTypeMap = new HashMap<>();
        }

        public void registerBooking(String reservationId, String roomType) {
            reservationRoomTypeMap.put(reservationId, roomType);
        }

        public void cancelBooking(String reservationId, RoomInventory inventory) {
            if (reservationRoomTypeMap.containsKey(reservationId)) {
                String roomType = reservationRoomTypeMap.get(reservationId);
                releasedRoomIds.push(reservationId);
                inventory.increaseRoom(roomType);
                reservationRoomTypeMap.remove(reservationId);
                System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
            } else {
                System.out.println("Cancellation failed. Reservation not found.");
            }
        }

        public void showRollbackHistory() {
            System.out.println("\nRollback History (Most Recent First):");
            Stack<String> temp = new Stack<>();
            while (!releasedRoomIds.isEmpty()) {
                String id = releasedRoomIds.pop();
                System.out.println("Released Reservation ID: " + id);
                temp.push(id);
            }
            while (!temp.isEmpty()) {
                releasedRoomIds.push(temp.pop());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 5);
        inventory.addRoom("Double", 3);
        inventory.addRoom("Suite", 2);

        CancellationService cancellationService = new CancellationService();

        cancellationService.registerBooking("Single-1", "Single");

        cancellationService.cancelBooking("Single-1", inventory);

        cancellationService.showRollbackHistory();

        System.out.println("\nUpdated Single Room Availability: " + inventory.getAvailableCount("Single"));
    }
}