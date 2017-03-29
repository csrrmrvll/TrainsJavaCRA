package trains;

import java.util.List;

class TripCounter extends Counter {
	
	/**
	 * 
	 */
	private final Graph		graph;
	private StopCondition	stopCondition;
	
	TripCounter(Graph graph, StopCondition sc) {
		this.graph = graph;
		this.stopCondition = sc;
	}
	
	void getTrips(List<Stop> stops, Stop from, Stop to) {
		if (stops == null) {
			return;
		}
		if (this.stopCondition.mustStop()) {
			this.stopCondition.decrease();
			return;
		}
		for (Stop s : stops) {
			if (to.equals(s)) {
				this.increase();
				this.stopCondition.decrease();
				return;
			}
			final List<Stop> newStops = this.graph.getMap().get(s);
			this.getTrips(newStops, s, to);
		}
	}
	
}