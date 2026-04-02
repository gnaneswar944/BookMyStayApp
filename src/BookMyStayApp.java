import java.util.*;

public class BookMyStayApp {

    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    static class RoomInventory {
        private Map<String, Integer> availability;

        public RoomInventory() {
            availability = new HashMap<>();
            availability.put("Single", 2);
            availability.put("Double", 2);
            availability.put("Suite", 1);
        }

        public int getAvailableCount(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }
    }

    static class ReservationValidator {
        public void validate(String guestName, String roomType, RoomInventory inventory)
                throws InvalidBookingException {

            if (guestName == null || guestName.trim().isEmpty()) {
                throw new InvalidBookingException("Guest name cannot be empty.");
            }

            if (!(roomType.equals("Single") || roomType.equals("Double") || roomType.equals("Suite"))) {
                throw new InvalidBookingException("Invalid room type selected.");
            }

            if (inventory.getAvailableCount(roomType) <= 0) {
                throw new InvalidBookingException("No rooms available for selected type.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = scanner.nextLine();

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();

        try {
            validator.validate(guestName, roomType, inventory);
            System.out.println("Booking successful for " + guestName + " (" + roomType + ")");
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}