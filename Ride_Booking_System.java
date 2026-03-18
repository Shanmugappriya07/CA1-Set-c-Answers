import java.util.Scanner;

interface RideControl {
    void startRide();
    void endRide();
}
abstract class Ride {
    String rideId;
    double distance;
    static int rideCount = 0;

    Ride(String rideId, double distance) {
        this.rideId = rideId;
        this.distance = distance;
        rideCount++;
    }

    abstract double calculateFare();

    void displayRideDetails() {
        System.out.println("Ride ID: " + rideId);
        System.out.println("Distance: " + distance + " km");
    }
}

class BikeRide extends Ride implements RideControl {

    BikeRide(String rideId, double distance) {
        super(rideId, distance);
    }

    double calculateFare() {
        return distance * 8;
    }

    public void startRide() {
        System.out.println("Bike Ride Started");
    }

    public void endRide() {
        System.out.println("Bike Ride Ended");
    }
}

class CarRide extends Ride implements RideControl {

    CarRide(String rideId, double distance) {
        super(rideId, distance);
    }

    double calculateFare() {
        return distance * 15;
    }

    public void startRide() {
        System.out.println("Car Ride Started");
    }

    public void endRide() {
        System.out.println("Car Ride Ended");
    }
}

class RideManager {
    Ride[] rides = new Ride[10];
    int count = 0;

    void addRide(Ride r) {
        rides[count++] = r;
    }

    void displayAllRides() {
        for (int i = 0; i < count; i++) {
            rides[i].displayRideDetails();
            System.out.println("Fare: " + rides[i].calculateFare());
            System.out.println("-------------------");
        }
    }

    double getTotalFare() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += rides[i].calculateFare();
        }
        return total;
    }
}

public class Ride_Booking_System {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RideManager manager = new RideManager();

        System.out.print("Enter number of rides: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.println("\nChoose Ride Type (1-Bike, 2-Car): ");
            int choice = sc.nextInt();

            System.out.print("Enter Ride ID: ");
            String id = sc.next();

            System.out.print("Enter Distance (km): ");
            double dist = sc.nextDouble();

            Ride r = null;

            if (choice == 1) {
                r = new BikeRide(id, dist);
            } else if (choice == 2) {
                r = new CarRide(id, dist);
            } else {
                System.out.println("Invalid choice!");
                i--;
                continue;
            }
            ((RideControl) r).startRide();
            manager.addRide(r);
            ((RideControl) r).endRide();
        }
        System.out.println("\n===== Ride Details =====");
        manager.displayAllRides();

        System.out.println("Total Fare: " + manager.getTotalFare());
        System.out.println("Total Rides: " + Ride.rideCount);

        sc.close();
    }
}