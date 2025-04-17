package service;

import entity.Vehicle;
import entity.Driver;
import entity.Bookings;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportManagementServiceImpl extends TransportManagementService {

    public boolean addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO Vehicles (Model, Capacity, Type, Status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, vehicle.getModel());
            pstmt.setDouble(2, vehicle.getCapacity());
            pstmt.setString(3, vehicle.getType());
            pstmt.setString(4, vehicle.getStatus());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateVehicle(Vehicle vehicle) {
        String query = "UPDATE Vehicles SET Model = ?, Capacity = ?, Type = ?, Status = ? WHERE VehicleID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, vehicle.getModel());
            pstmt.setDouble(2, vehicle.getCapacity());
            pstmt.setString(3, vehicle.getType());
            pstmt.setString(4, vehicle.getStatus());
            pstmt.setInt(5, vehicle.getVehicleID());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteVehicle(int vehicleId) {
        String query = "DELETE FROM Vehicles WHERE VehicleID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, vehicleId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean scheduleTrip(int vehicleId, int routeId, String departureDate, String arrivalDate) {
        String query = "INSERT INTO Trips (VehicleID, RouteID, DepartureDate, ArrivalDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, vehicleId);
            pstmt.setInt(2, routeId);
            pstmt.setString(3, departureDate);
            pstmt.setString(4, arrivalDate);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cancelTrip(int tripId) {
        String query = "DELETE FROM Trips WHERE TripID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, tripId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean bookTrip(int tripId, int passengerId, String bookingDate) {
        String query = "INSERT INTO Bookings (TripID, PassengerID, BookingDate) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, tripId);
            pstmt.setInt(2, passengerId);
            pstmt.setString(3, bookingDate);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cancelBooking(int bookingId) {
        String query = "DELETE FROM Bookings WHERE BookingID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean allocateDriver(int tripId, int driverId) {
        String query = "UPDATE Trips SET DriverID = ? WHERE TripID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, driverId);
            pstmt.setInt(2, tripId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deallocateDriver(int tripId) {
        String query = "UPDATE Trips SET DriverID = NULL WHERE TripID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, tripId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Bookings> getBookingsByPassenger(int passengerID) {
        List<Bookings> bookingsList = new ArrayList<>();
        String query = "SELECT * FROM Bookings WHERE PassengerID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, passengerID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Bookings booking = new Bookings();
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setTripID(rs.getInt("TripID"));
                booking.setPassengerID(rs.getInt("PassengerID"));
                booking.setBookingDate(rs.getDate("BookingDate").toString()); // Safely convert Date to String
                bookingsList.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingsList;
    }

    @Override
    public List<Bookings> getBookingsByTrip(int tripId) {
        List<Bookings> bookingsList = new ArrayList<>();
        String query = "SELECT * FROM Bookings WHERE TripID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, tripId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Bookings booking = new Bookings();
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setTripID(rs.getInt("TripID"));
                booking.setPassengerID(rs.getInt("PassengerID"));
                booking.setBookingDate(rs.getDate("BookingDate").toString());
                bookingsList.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingsList;
    }

    @Override
    public List<Driver> getAvailableDriver() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM Drivers WHERE Status = 'Available'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverId(rs.getInt("DriverID"));
                driver.setName(rs.getString("Name"));
                driver.setLicenseNumber(rs.getString("LicenseNumber"));
                driver.setStatus(rs.getString("Status"));
                drivers.add(driver);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

}
