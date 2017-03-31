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
	
	void getTrips(Stop from, Stop to) {
		if (this.stopCondition.mustStop()) {
			this.stopCondition.decrease();
			return;
		}
		final List<Stop> stops = this.graph.getMap().get(from);
		for (Stop s : stops) {
			if (to.equals(s)) {
				this.increase();
				this.stopCondition.decrease();
				return;
			}
			this.getTrips(s, to);
		}
	}
	
}