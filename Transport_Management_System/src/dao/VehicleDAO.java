package dao;

import entity.Vehicle;

import java.sql.*;

public class VehicleDAO {

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
}
