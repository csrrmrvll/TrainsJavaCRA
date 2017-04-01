package trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

class Graph {
	
	private final TreeMap<Town, List<Town>>	map;
	private final Map<Road, Integer>		distances;
	
	Graph(List<String> nodes) {
		this.map = new TreeMap<>();
		this.distances = new HashMap<>();
		for (String s : nodes) {
			final Town from = new Town(s.substring(0, 1));
			final List<Town> stops = this.getStops(from);
			if (stops == null) {
				this.map.put(from, new ArrayList<>());
			}
			final Town to = new Town(s.substring(1, 2));
			this.getStops(from).add(to);
			final int distance = Integer.valueOf(s.substring(2, 3));
			final Road road = new Road(from, to);
			this.distances.put(road, distance);
		}
	}
	
	List<Town> getStops(Town from) {
		return this.map.get(from);
	}
	
	class NoSuchRouteError extends Exception {
		
		/**
		 * 
		 */
		private static final long	serialVersionUID	= -5969432656469507171L;
		
		static final String			NO_SUCH_ROUTE		= "NO SUCH ROUTE";
		
		NoSuchRouteError(String message) {
			super(message);
		}
		
		NoSuchRouteError() {
			this(NO_SUCH_ROUTE);
		}
	}
	
	int getRouteDistance(Town from, Route route) throws NoSuchRouteError {
		int distance = 0;
		for (Town to : route.getTowns()) {
			final List<Town> trips = this.getStops(from);
			if (trips.contains(to)) {
				distance += this.getDistance(from, to);
				from = to;
			} else {
				throw new NoSuchRouteError();
			}
		}
		return distance;
	}
	
	private int getDistance(Town from, Town to) {
		final Road r = new Road(from, to);
		return this.distances.containsKey(r) ? this.distances.get(r) : 0;
	}
	
	private Integer getDistance(Town to) {
		return this.getDistance(this.map.firstKey(), to);
	}
	
	int getNumberOfTrips(Town from, Town to, StopCondition sc) {
		final TripCounter tc = new TripCounter(this, sc);
		tc.getTrips(from, to);
		return tc.getValue();
	}
	
	int getShortestRoute(Town from, Town to) {
		final Heap<Integer, Town> heap = new Heap<>();
		final Set<Town> visited = new HashSet<>();
		final Map<Town, Integer> scores = new HashMap<>();
		final int defaultScore = Integer.MAX_VALUE;
		for (Town t : this.map.keySet()) {
			heap.put(defaultScore, t);
			scores.put(t, defaultScore);
		}
		heap.put(this.getDistance(from), from);
		while (heap.notEmpty()) {
			final Entry<Integer, Town> top = heap.pop();
			final Town current = top.getValue();
			final int currentScore = top.getKey();
			scores.put(current, currentScore);
			visited.add(current);
			final List<Town> stops = this.getStops(current);
			for (Town town : stops) {
				if (visited.contains(town) == false) {
					final int oldScore = scores.get(town);
					heap.remove(oldScore);
					final int distance = this.getDistance(current, town);
					final int tempScore = currentScore + distance;
					final int newScore = Math.min(oldScore, tempScore);
					scores.put(town, newScore);
					heap.put(newScore, town);
				}
			}
		}
		return scores.get(to);
	}
	
	private Town getFirstStop(Town from) {
		return this.getStops(from).get(0);
	}
	
	int getShortestCyclicRoute(Town from) {
		final Town to = this.getFirstStop(from);
		int startDistance = this.getDistance(from, to);
		return this.getShortestRoute(to, from) + startDistance;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (Entry<Town, List<Town>> e : this.map.entrySet()) {
			final Town from = e.getKey();
			final List<Town> trips = e.getValue();
			for (Town to : trips) {
				final int distance = this.getDistance(from, to);
				sb.append(from).append(to).append(String.valueOf(distance));
			}
		}
		return sb.toString();
	}
	
}
