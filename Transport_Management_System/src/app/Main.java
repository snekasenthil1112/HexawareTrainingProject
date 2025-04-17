package app;
import entity.*;
import dao.TransportManagementServiceImpl;

import java.util.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TransportManagementServiceImpl service = new TransportManagementServiceImpl();

        while (true) {
            System.out.println("\n--- Transport Management System ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Update Vehicle");
            System.out.println("3. Delete Vehicle");
            System.out.println("4. Schedule Trip");
            System.out.println("5. Cancel Trip");
            System.out.println("6. Book Trip");
            System.out.println("7. Cancel Booking");
            System.out.println("8. Allocate Driver");
            System.out.println("9. Deallocate Driver");
            System.out.println("10. Get Bookings by Passenger");
            System.out.println("11. Get Bookings by Trip");
            System.out.println("12. Get Available Drivers");
            System.out.println("13. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: // Add Vehicle
                    Vehicle v = new Vehicle();
                    System.out.print("Model: ");
                    v.setModel(sc.nextLine());
                    System.out.print("Capacity: ");
                    v.setCapacity(sc.nextDouble());
                    sc.nextLine();
                    System.out.print("Type: ");
                    v.setType(sc.nextLine());
                    System.out.print("Status: ");
                    v.setStatus(sc.nextLine());
                    System.out.println(service.addVehicle(v) ? "Vehicle Added" : "Failed to Add");
                    break;

                case 2: // Update Vehicle
                    Vehicle uv = new Vehicle();
                    System.out.print("Vehicle ID to update: ");
                    uv.setVehicleID(sc.nextInt());
                    sc.nextLine();
                    System.out.print("New Model: ");
                    uv.setModel(sc.nextLine());
                    System.out.print("New Capacity: ");
                    uv.setCapacity(sc.nextDouble());
                    sc.nextLine();
                    System.out.print("New Type: ");
                    uv.setType(sc.nextLine());
                    System.out.print("New Status: ");
                    uv.setStatus(sc.nextLine());
                    System.out.println(service.updateVehicle(uv) ? "Vehicle Updated" : "Update Failed");
                    break;

                case 3: // Delete Vehicle
                    System.out.print("Vehicle ID to delete: ");
                    int deleteId = sc.nextInt();
                    System.out.println(service.deleteVehicle(deleteId) ? "Vehicle Deleted" : "Delete Failed");
                    break;

                case 4: // Schedule Trip
                    Trip trip = new Trip();
                    System.out.print("Vehicle ID: ");
                    trip.setVehicleID(sc.nextInt());
                    System.out.print("Route ID: ");
                    trip.setRouteID(sc.nextInt());
                    sc.nextLine();
                    trip.setDepartureDate(LocalDateTime.now().plusDays(1));
                    trip.setArrivalDate(LocalDateTime.now().plusDays(1).plusHours(5));
                    trip.setStatus("Scheduled");
                    trip.setTripType("Passenger");
                    System.out.print("Max Passengers: ");
                    trip.setMaxPassengers(sc.nextInt());
                    System.out.println(service.scheduleTrip(trip) ? "Trip Scheduled" : "Failed to Schedule");
                    break;

                case 5: // Cancel Trip
                    System.out.print("Trip ID to cancel: ");
                    int cancelTripId = sc.nextInt();
                    System.out.println(service.cancelTrip(cancelTripId) ? "Trip Cancelled" : "Cancel Failed");
                    break;

                case 6: // Book Trip
                    Bookings b = new Bookings();
                    System.out.print("Trip ID: ");
                    b.setTripID(sc.nextInt());
                    System.out.print("Passenger ID: ");
                    b.setPassengerID(sc.nextInt());
                    b.setBookingDate(LocalDateTime.now());
                    b.setStatus("Confirmed");
                    System.out.println(service.bookTrip(b) ? "Trip Booked" : "Booking Failed");
                    break;

                case 7: // Cancel Booking
                    System.out.print("Booking ID to cancel: ");
                    int cancelBookingId = sc.nextInt();
                    System.out.println(service.cancelBooking(cancelBookingId) ? "Booking Cancelled" : "Cancel Failed");
                    break;

                case 8: // Allocate Driver
                    System.out.print("Trip ID: ");
                    int tripId = sc.nextInt();
                    System.out.print("Driver ID: ");
                    int driverId = sc.nextInt();
                    System.out.println(service.allocateDriver(tripId, driverId) ? "Driver Allocated" : "Allocation Failed");
                    break;

                case 9: // Deallocate Driver
                    System.out.print("Trip ID: ");
                    int tripIdDeallocate = sc.nextInt();
                    System.out.println(service.deallocateDriver(tripIdDeallocate) ? "Driver Deallocated" : "Deallocation Failed");
                    break;

                case 10: // Get Bookings by Passenger
                    System.out.print("Passenger ID: ");
                    int pid = sc.nextInt();
                    List<Bookings> bookings = service.getBookingsByPassenger(pid);
                    bookings.forEach(System.out::println);
                    break;

                case 11: // Get Bookings by Trip
                    System.out.print("Trip ID: ");
                    int tid = sc.nextInt();
                    List<Bookings> bookings2 = service.getBookingsByTrip(tid);
                    bookings2.forEach(System.out::println);
                    break;

                case 12: // Get Available Drivers
                    List<Driver> availableDrivers = service.getAvailableDrivers();
                    availableDrivers.forEach(System.out::println);
                    break;

                case 13:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
