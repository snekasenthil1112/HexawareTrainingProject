package entity;

import java.time.LocalDateTime;

public class Trip {
	private int tripID;
	private int vehicleID;
	private int routeID;
	private int driverID;
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;
	private String status;
	private String tripType;
	private int maxPassengers;
	
	public Trip () {}
	public Trip(int tripID, int vehicleID,int routeID, LocalDateTime departureDate, LocalDateTime arrivalDate, String status, String tripType, int maxPassengers) {
		this.setTripID(tripID);
		this.setVehicleID(vehicleID);
		this.setRouteID(routeID);
		private int driverID;
		this.setDepartureDate(departureDate);
		this.setArrivalDate(arrivalDate);
		this.setStatus(status);
		this.setTripType(tripType);
		this.setMaxPassengers(maxPassengers);
		}
	public int getTripID() {
		return tripID;
	}
	public void setTripID(int tripID) {
		this.tripID = tripID;
	}
	public int getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	public LocalDateTime getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}
	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public int getMaxPassengers() {
		return maxPassengers;
	}
	public void setMaxPassengers(int maxPassengers) {
		this.maxPassengers = maxPassengers;
	}
	public int getDriverID() {
		return driverID;
	}
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
}
