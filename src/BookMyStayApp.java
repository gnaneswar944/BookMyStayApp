import java.util.*;

public class BookMyStayApp {

    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }
    }

    static class RoomInventory {
        private Map<String, Integer> availability;

        public RoomInventory() {
            availability = new HashMap<>();
        }

        public void addRoom(String roomType, int count) {
            availability.put(roomType, count);
        }

        public int getAvailableCount(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }

        public void reduceRoom(String roomType) {
            if (availability.getOrDefault(roomType, 0) > 0) {
                availability.put(roomType, availability.get(roomType) - 1);
            }
        }
    }

    static class BookingRequestQueue {
        private Queue<Reservation> requestQueue;

        public BookingRequestQueue() {
            requestQueue = new LinkedList<>();
        }

        public void addRequest(Reservation reservation) {
            requestQueue.offer(reservation);
        }

        public Reservation getNextRequest() {
            return requestQueue.poll();
        }

        public boolean hasPendingRequests() {
            return !requestQueue.isEmpty();
        }
    }

    static class RoomAllocationService {
        private Set<String> allocatedRoomIds;
        private Map<String, Set<String>> assignedRoomsByType;

        public RoomAllocationService() {
            allocatedRoomIds = new HashSet<>();
            assignedRoomsByType = new HashMap<>();
        }

        public void allocateRoom(Reservation reservation, RoomInventory inventory) {
            String roomType = reservation.getRoomType();

            if (inventory.getAvailableCount(roomType) > 0) {
                String roomId = generateRoomId(roomType);
                allocatedRoomIds.add(roomId);

                assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
                assignedRoomsByType.get(roomType).add(roomId);

                inventory.reduceRoom(roomType);

                System.out.println("Booking confirmed for Guest: " +
                        reservation.getGuestName() + ", Room ID: " + roomId);
            } else {
                System.out.println("No room available for Guest: " +
                        reservation.getGuestName() + ", Room Type: " + roomType);
            }
        }

        private String generateRoomId(String roomType) {
            assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
            int nextNumber = assignedRoomsByType.get(roomType).size() + 1;
            return roomType + "-" + nextNumber;
        }
    }

    public static void main(String[] args) {
        System.out.println("Room Allocation Processing");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Suite", 1);

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        RoomAllocationService allocationService = new RoomAllocationService();

        while (bookingQueue.hasPendingRequests()) {
            Reservation reservation = bookingQueue.getNextRequest();
            allocationService.allocateRoom(reservation, inventory);
        }
    }
}