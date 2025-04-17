package dao;

import entity.Passengers;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO {

    public List<Passengers> getAllPassengers() {
        List<Passengers> passengers = new ArrayList<>();
        String sql = "SELECT * FROM Passengers";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Passengers p = new Passengers();
                p.setPassengerID(rs.getInt("PassengerID"));
                p.setFirstName(rs.getString("FirstName"));
                p.setGender(rs.getString("gender"));
                p.setAge(rs.getInt("age"));
                p.setEmail(rs.getString("Email"));
                p.setPhoneNumber(rs.getString("PhoneNumber"));

                passengers.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }
}
