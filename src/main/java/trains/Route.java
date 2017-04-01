package trains;

import java.util.List;

public class Route {
	
	final List<Town> stops;
	
	public Route(List<Town> stops) {
		this.stops = stops;
	}
	
	public List<Town> getTowns() {
		return this.stops;
	}
	
}
