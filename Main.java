// Online Java Railway system

import java.util.ArrayList;
import java.util.Scanner;

class Train {
    String name;
    String time;
    int passengerStrength;
    int trainNumber;

    public Train(String name, String time, int passengerStrength, int trainNumber) {
        this.name = name;
        this.time = time;
        this.passengerStrength = passengerStrength;
        this.trainNumber = trainNumber;
    }
}

class ReservationSystem {
    private ArrayList<Train> availableTrains = new ArrayList<>();
    private ArrayList<String> bookedSeats = new ArrayList<>();

    public ReservationSystem() {
        availableTrains.add(new Train("Mumbai - Delhi", "13:05", 50, 1010));
        availableTrains.add(new Train("Delhi - Jaipur", "7:00", 50, 2013));
        availableTrains.add(new Train("Prayagraj - Delhi", "10:00", 50, 3045));
    }

    public void displayAvailableTrains() {
        System.out.println("Available Trains:");
        System.out.println("Train Name\tTime\tPassenger Strength\tTrain Number");
        for (Train train : availableTrains) {
            System.out.println(train.name + "\t" + train.time + "\t" + train.passengerStrength + "\t" + train.trainNumber);
        }
    }

    public void checkSeatAvailability(int trainNumber) {
        for (Train train : availableTrains) {
            if (train.trainNumber == trainNumber) {
                int availableSeats = train.passengerStrength - bookedSeats.size();
                System.out.println("Available seats on Train " + train.trainNumber + ": " + availableSeats);
                return;
            }
        }
        System.out.println("Train not found.");
    }

    public void bookTicket(int trainNumber, String passengerName) {
        for (Train train : availableTrains) {
            if (train.trainNumber == trainNumber) {
                if (bookedSeats.size() < train.passengerStrength) {
                    bookedSeats.add(passengerName);
                    System.out.println("Ticket booked successfully for " + passengerName);
                } else {
                    System.out.println("Sorry, the train is fully booked.");
                }
                return;
            }
        }
        System.out.println("Train not found.");
    }

    public void cancelTicket(String passengerName) {
        if (bookedSeats.remove(passengerName)) {
            System.out.println("Ticket canceled successfully for " + passengerName);
        } else {
            System.out.println("Passenger not found or no booking exists for this passenger.");
        }
    }

    public void displayBookedTickets() {
        System.out.println("Booked Tickets:");
        for (String passenger : bookedSeats) {
            System.out.println(passenger);
        }
    }
}
class Main {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nRailway Reservation System Menu:");
            System.out.println("1. Display Available Trains");
            System.out.println("2. Check Seat Availability");
            System.out.println("3. Book a Ticket");
            System.out.println("4. Cancel a Ticket");
            System.out.println("5. Display Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    reservationSystem.displayAvailableTrains();
                    break;
                case 2:
                    System.out.print("Enter Train Number: ");
                    int trainNumber = scanner.nextInt();
                    reservationSystem.checkSeatAvailability(trainNumber);
                    break;
                case 3:
                    System.out.print("Enter Train Number: ");
                    trainNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Enter Passenger Name: ");
                    String passengerName = scanner.nextLine();
                    reservationSystem.bookTicket(trainNumber, passengerName);
                    break;
                case 4:
                    System.out.print("Enter Passenger Name to Cancel: ");
                    passengerName = scanner.nextLine();
                    reservationSystem.cancelTicket(passengerName);
                    break;
                case 5:
                    reservationSystem.displayBookedTickets();
                    break;
                case 6:
                    System.out.println("Exiting Railway Reservation System. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
