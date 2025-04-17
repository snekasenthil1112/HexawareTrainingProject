package service;

import java.util.List;
import entity.*;
public interface TransportManagementService {

	    // Bookings
	    void addBooking(Bookings booking);
	    Bookings getBookingById(int bookingID);
	    List<Bookings> getAllBookings();
	    void updateBooking(Bookings booking);
	    void deleteBooking(int bookingID);

	    // Drivers
	    void addDriver(Driver driver);
	    Driver getDriverById(int driverID);
	    List<Driver> getAllDrivers();
	    void updateDriver(Driver driver);
	    void deleteDriver(int driverID);

	    // Passengers
	    void addPassenger(Passengers passenger);
	    Passengers getPassengerById(int passengerID);
	    List<Passengers> getAllPassengers();
	    void updatePassenger(Passengers passenger);
	    void deletePassenger(int passengerID);

	    // Routes
	    void addRoute(Routes route);
	    Routes getRouteById(int routeID);
	    List<Routes> getAllRoutes();
	    void updateRoute(Routes route);
	    void deleteRoute(int routeID);

	    // Trips
	    void addTrip(Trip trip);
	    Trip getTripById(int tripID);
	    List<Trip> getAllTrips();
	    void updateTrip(Trip trip);
	    void deleteTrip(int tripID);

	    // Vehicles
	    void addVehicle(Vehicle vehicle);
	    Vehicle getVehicleById(int vehicleID);
	    List<Vehicle> getAllVehicles();
	    void updateVehicle(Vehicle vehicle);
	    void deleteVehicle(int vehicleID);
	}
