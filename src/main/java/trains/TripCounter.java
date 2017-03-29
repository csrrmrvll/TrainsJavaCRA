package trains;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TripCounter extends Counter {
	
	/**
	 * 
	 */
	private final Graph		graph;
	private StopCondition	stopCondition;
	private Set<Stop>		explored;
	
	public TripCounter(Graph graph, StopCondition sc) {
		this.graph = graph;
		this.stopCondition = sc;
		this.explored = new HashSet<>();
	}
	
	public void getTrips(List<Stop> stops, Stop from, Stop to) {
		if (stops == null) {
			return;
		}
		if (this.stopCondition.mustStop()) {
			this.stopCondition.decrease();
			return;
		}
		for (Stop s : stops) {
			if (to.equals(s)) {
				this.addOne();
				this.stopCondition.decrease();
				return;
			}
			if (this.explored.contains(s)) {
				continue;
			}
			this.explored.add(s);
			final List<Stop> newStops = this.graph.map.get(s);
			this.getTrips(newStops, s, to);
		}
	}
	
}