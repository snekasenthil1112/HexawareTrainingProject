package dao;

import entity.Bookings;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public List<Bookings> getBookingsByPassenger(int passengerId) {
        List<Bookings> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Bookings WHERE PassengerID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, passengerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bookings b = new Bookings();
                b.setBookingId(rs.getInt("BookingID"));
                b.setTripId(rs.getInt("TripID"));
                b.setPassengerId(rs.getInt("PassengerID"));
                b.setBookingDate(rs.getTimestamp("BookingDate"));
                b.setStatus(rs.getString("Status"));
                bookings.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
