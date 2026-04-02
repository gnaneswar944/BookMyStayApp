import java.util.*;

public class BookMyStayApp {

    static class Reservation {
        private String reservationId;
        private String guestName;
        private String roomType;

        public Reservation(String reservationId, String guestName, String roomType) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getReservationId() {
            return reservationId;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }
    }

    static class AddOnService {
        private String serviceName;
        private double cost;

        public AddOnService(String serviceName, double cost) {
            this.serviceName = serviceName;
            this.cost = cost;
        }

        public String getServiceName() {
            return serviceName;
        }

        public double getCost() {
            return cost;
        }
    }

    static class AddOnServiceManager {
        private Map<String, List<AddOnService>> reservationServices;

        public AddOnServiceManager() {
            reservationServices = new HashMap<>();
        }

        public void addService(String reservationId, AddOnService service) {
            reservationServices.putIfAbsent(reservationId, new ArrayList<>());
            reservationServices.get(reservationId).add(service);
        }

        public List<AddOnService> getServices(String reservationId) {
            return reservationServices.getOrDefault(reservationId, new ArrayList<>());
        }

        public double getTotalServiceCost(String reservationId) {
            double total = 0;
            for (AddOnService service : getServices(reservationId)) {
                total += service.getCost();
            }
            return total;
        }
    }

    public static void main(String[] args) {
        System.out.println("Add-On Service Selection");

        Reservation r1 = new Reservation("R101", "Abhi", "Single");
        Reservation r2 = new Reservation("R102", "Subha", "Double");
        Reservation r3 = new Reservation("R103", "Vanmathi", "Suite");

        AddOnService wifi = new AddOnService("WiFi", 500);
        AddOnService breakfast = new AddOnService("Breakfast", 800);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1200);

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(r1.getReservationId(), wifi);
        manager.addService(r1.getReservationId(), breakfast);

        manager.addService(r2.getReservationId(), breakfast);

        manager.addService(r3.getReservationId(), wifi);
        manager.addService(r3.getReservationId(), breakfast);
        manager.addService(r3.getReservationId(), airportPickup);

        Reservation[] reservations = {r1, r2, r3};

        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.getGuestName() + ", Room Type: " + r.getRoomType());
            System.out.println("Selected Services:");
            for (AddOnService service : manager.getServices(r.getReservationId())) {
                System.out.println(service.getServiceName() + " - " + service.getCost());
            }
            System.out.println("Total Add-On Cost: " + manager.getTotalServiceCost(r.getReservationId()));
            System.out.println();
        }
    }
}