package systemdesign;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationManager {
	List<Reservation> reservations;
	List<Table> tables;
	Set<RestaurantCustomer> customers;
	private static ReservationManager instance;
	
	private ReservationManager(){
		customers = new HashSet<>();
		init();
	}
	
	public ReservationManager getInstance(){
		if(instance == null){
			instance = new ReservationManager();
		}
		return instance;
	}
	
	private void init(){
		reservations = new ArrayList<>();
		tables = new ArrayList<>();
		// todo: add different types of tables
	}
	
	public void CreateNewProfile(int customerId, String fullName, String contactInfo){
		// create profile for new customer
	}
	
	public boolean makeReservation(int customerId, LocalDate reservationTime, String otherRequirements){
		Table table = null;
		for(Table t : tables){
			if(t.isAvailable(reservationTime)){
				table = t;
				break;
			}
		}
		if(table == null) { // no table is available. It could be based on the capacity. Try generating the suggestion
			// of table based on ability to join or adjency. Every Table can maintain an adjancy list of tables. Iterate over each 
			// unreserved table and its adjency list.
			
			
			
		}
		if(table != null){
			Reservation rsv = new Reservation(customerId, table.tableId, reservationTime, otherRequirements);
			table.addReservedTime(reservationTime);
			return true;
		}
		return false;	
	}

	
	private class Table{
		int tableId;
		int numberOfSeats;
		TableType type;
		Set<LocalDate> reservedTimes;
		public Table(int id, int number, TableType type){
			this.tableId = id;
			this.numberOfSeats = number;
			this.type = type;
			this.reservedTimes = new HashSet<>();
		}
		
		public boolean isAvailable(LocalDate reservationTime){
			// check if this table is available begin from reserve time, the default duration is 1 hour.
			// 
			return true;
		}
		public void addReservedTime(LocalDate reservedTime) {
			reservedTimes.add(reservedTime);
		}
		
	}
	
	enum TableType{
		Booth, HighTable
	}
}

class Reservation{
	int reservationId;
	int customerId;
	int tableId;
	LocalDate creationTime;
	LocalDate reservationTime;
	String otherRequirements;
	
	public Reservation(int customerId, int tableId, LocalDate reservationTime, String otherRequirements){
		this.reservationId = reservationId;
		this.tableId = tableId;
		this.customerId = customerId;
		this.reservationTime = reservationTime;
		this.otherRequirements = otherRequirements;
	}
	
	public void udpateTable() {
		// to change the table for customer
	}
	
	public void updateTime(){
		// update the reservation time
	}
	
	public void updateRequirements(){
		// update the requirements
	}
}

class RestaurantCustomer{
	private int customerId;
	private String fullName;
	private String telephoneNumber;
	public RestaurantCustomer(int id, String name, String telePhoneNumber){
		this.customerId = id;
		this.fullName = name;
		this.telephoneNumber = telePhoneNumber;
	}
	
	public int getId(){
		return this.customerId;
	}
	
	public String getName(){
		return this.fullName;
	}
	
	public String getTeleNumber(){
		return this.telephoneNumber;
	}
	
	// update methods
}