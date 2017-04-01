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
	
	void getTrips(Town from, Town to) {
		if (this.stopCondition.mustStop()) {
			this.stopCondition.decrease();
			return;
		}
		final List<Town> stops = this.graph.getMap().get(from);
		for (Town t : stops) {
			if (to.equals(t)) {
				this.increase();
				this.stopCondition.decrease();
				return;
			}
			this.getTrips(t, to);
		}
	}
	
}