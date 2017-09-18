package systemdesign;

public class CabBookingSystem {
List<Customer>;
List<Car> availableCars;
HashMap<bookingId, Booking>;
}

class Customer extends Person
{   List<Booking> past, 
	Booking current (empty), 
	Location
}

class Driver extends Person
{ Booking (empty), List<Feedback>}

class Car extends CarType:
{Location, Driver}

class Booking extends BookingType
{
	Customer, Driver, Start, Destination, Fare, Type_of_booking}
calculate_distance

Note: this isn't the shortest distance problem, it is the nearest car/point 
problem

Map:
{Graph<Car> free_cars}

update(Booking ) //updates free_cars when booking made,ended or cancelled
}
class RideHelper {
static findRider(customerLocation) //iterate through points. algorithm is another problem. not diving into it right now as you asked for OOD.
}

class Booking {
CustomerID;
DriverId;
StartLocation;
EndLocation;
starttime;
Fare;

}
}

FareCalulator {
	calculate_fare(startloc, end location, starttime);
}


public class CabFinder implements CabStatusListener {
	private List<Cab> cabList = null;
	
	private Map<CabWrapper, Cab> cabMap = new TreeMap<CabWrapper, Cab>();
	
	private int maxCabs;
	Position userPos = null;

public void initialize(CabApp app, int maxCabs) {
		cabList = new ArrayList<Cab>();
		Iterator<Cab> iter =  app.getCabs();
		userPos = app.getUserPosition();
		while(iter.hasNext()) {
			Cab cab = iter.next();
			if (cab.isAvailable()) {//proceed only if it is available 
				double distance = calculateDistance(cab);
				CabWrapper cabWrap = new CabWrapper();
				cabWrap.distance = distance;
				cabWrap.cabId= cab.getID();
				if (distance <= 1000) //if within 1km range add it to the map
					cabMap.put(cabWrap, cab);
			}
		}
		this.maxCabs = maxCabs;
	}
	
	private double calculateDistance(Cab cab) {
		return Math.sqrt(Math.abs(userPos.x
				- cab.getCabPosition().x)
				^ 2 + Math.abs(userPos.y - cab.getCabPosition().y) ^ 2);
	}
	public Cab[] getNearestCabs() {
		Collection<Cab> sortedList = cabMap.values();
		Cab[] cabArrayTotal = (Cab[]) sortedList.toArray();
		Cab[] cabArray = Arrays.copyOf(cabArrayTotal, maxCabs);
		return cabArray;
	}

	public void onCabPositionChanged(Cab cab) {
		
		double distance = calculateDistance(cab);
		CabWrapper cabWrap = new CabWrapper();
		cabWrap.distance = distance;
		cabWrap.cabId= cab.getID();
		
		if (cabMap.containsKey(cab)) {
			cabMap.remove(cabWrap) ;
		}
		if(distance <;1000 && cab.isAvailable())
			cabMap.put(cabWrap, cab);
		}

public void onCabAvailabilityChanged(Cab cab, boolean isAvailable) {
		CabWrapper cabWrap = new CabWrapper();
		double distance = calculateDistance(cab);
		cabWrap.cabId= cab.getID();
		cabWrap.distance = distance;
//		if (!cab.isAvailable() && cabMap.containsKey(cabWrap)) {
			cabMap.remove(cabWrap) ;
//		} else {
			cabMap.put(cabWrap, cab);
		}
class CabWrapper implements Comparator<CabWrapper> {
	int cabId;
	double distance;
	
	@Override
	public boolean equals(Object obj) {
		if (cabId ==  ((Cab) obj).getID()) return true;
		
		return false;
	}

	@Override
	public int compare(CabWrapper cab1, CabWrapper cab2) {
		if (cab1.distance < cab2.distance) {
			return -1;
		}
		if (cab1.distance > cab2.distance) {
			return 1;
		} else if (cab1.cabId == cab2.cabId) {
			return 0;
		} else
			return -1;
	}
}


//https://www.careercup.com/question?id=5717814883123200