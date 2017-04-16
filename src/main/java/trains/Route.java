package trains;

import java.util.ArrayList;
import java.util.List;

public class Route {
	
	private final List<Road> roads;
	
	public Route() {
		this.roads = new ArrayList<>();
	}
	
	public Route(List<Road> roads) {
		this.roads = roads;
	}
	
	public Route(Route r) {
		this.roads = r.getRoads();
	}
	
	public List<Road> getRoads() {
		return this.roads;
	}
	
	public int size() {
		return this.roads.size();
	}
	
	public int getDistance() {
		int distance = 0;
		for (Road r : this.roads) {
			distance += r.getDistance();
		}
		return distance;
	}
	
	public boolean add(Road r) {
		return this.roads.add(r);
		
	}
	
	@Override
	public String toString() {
		return this.roads.toString();
	}
	
	public void pop() {
		if (this.nonEmpty()) {
			this.roads.remove(this.roads.size() - 1);
		}
		
	}
	
	public boolean nonEmpty() {
		return this.roads.isEmpty() == false;
	}
	
	public Town getFrom() {
		return this.roads.get(0).getFrom();
	}
	
	public Town getTo() {
		return this.roads.get(this.size() - 1).getTo();
	}
}
