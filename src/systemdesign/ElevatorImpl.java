package systemdesign;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

enum ElevatorDirection {
    UP,
    DOWN,
    HOLD
}
class ElevatorStatus {
    private int currentFloor;
    private ElevatorDirection direction;
    private int finalDestination;
}

interface UserInterface {
    void request(Request request);
}

class ElevatorRequest {
    private long requestTime;
    private int floor;
    private ElevatorDirection direction;
}
interface Elevator {
    void toToFloor(int floor);
    void updateIntermediateStops(List<Integer> floor);
    ElevatorStatus getStatus();
}

interface RulesManager {
    void enqueue(Request request);
    int getNextDestination();
}

public class ElevatorImpl implements Elevator {
  private Integer currentFloor;
  private Queue<Integer> destinationFloors = new LinkedList<>();
  private Stack<Integer> stack = new Stack<>();
  public ElevatorDirection direction() {
    if (destinationFloors.size() > 0){
      if (currentFloor < destinationFloors.peek()){
        return ElevatorDirection.UP;
      } else if (currentFloor > destinationFloors.peek()) {
        return ElevatorDirection.DOWN;
      }
      stack.lastElement();
    }
    return ElevatorDirection.HOLD;
  }


@Override
public void toToFloor(int floor) {
	// TODO Auto-generated method stub
	
}

@Override
public void updateIntermediateStops(List<Integer> floor) {
	// TODO Auto-generated method stub
	
}

@Override
public ElevatorStatus getStatus() {
	// TODO Auto-generated method stub
	return null;
}
}
class ElevatorControlSystem  {

  public static final int MAX_ELEVATORS = 16;
  Integer numberOfElevators = 0;
  Integer numberOfFloors = 0;
  ArrayList<Elevator> elevators;
  Queue<Integer> pickupLocations;
}
