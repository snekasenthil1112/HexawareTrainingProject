package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entity.Bookings;
import entity.Driver;
import entity.Vehicle;
import util.DBConnection;

public class TransportManagementServiceImpl implements TransportManagementService {

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Vehicles (Model, Capacity, Type, Status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehicle.getModel());
            stmt.setDouble(2, vehicle.getCapacity());
            stmt.setString(3, vehicle.getType());
            stmt.setString(4, vehicle.getStatus());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public boolean updateVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteVehicle(int vehicleId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scheduleTrip(int vehicleId, int routeId, String departureDate, String arrivalDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelTrip(int tripId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bookTrip(int tripId, int passengerId, String bookingDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelBooking(int bookingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean allocateDriver(int tripId, int driverId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deallocateDriver(int tripId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Bookings> getBookingsByPassenger(int passengerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bookings> getBookingsByTrip(int tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Driver> getAvailableDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

    // TODO: Implement other methods as per interface
}
