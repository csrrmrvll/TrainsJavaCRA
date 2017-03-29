package trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Graph {
	
	private final Map<Stop, List<Stop>> map = new HashMap<>();
	
	Graph(List<String> nodes) {
		for (String s : nodes) {
			final Stop start = new Stop(s.substring(0, 1));
			List<Stop> elem = this.getMap().get(start);
			if (elem == null) {
				this.getMap().put(start, new ArrayList<>());
			}
			final String end = s.substring(1, 2);
			final int distance = Integer.valueOf(s.substring(2, 3));
			final Stop trip = new Stop(end, distance);
			this.getMap().get(start).add(trip);
		}
	}
	
	Map<Stop, List<Stop>> getMap() {
		return this.map;
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
	
	int getRouteDistance(String route) throws NoSuchRouteError {
		int distance = 0;
		for (int i = 0; i < route.length() - 1; ++i) {
			final Stop start = new Stop(route.substring(i, i + 1));
			final Stop end = new Stop(route.substring(i + 1, i + 2));
			if (this.getMap().containsKey(start)) {
				final List<Stop> trips = this.getMap().get(start);
				final int idx = trips.indexOf(end);
				if (idx != -1) {
					final Stop trip = trips.get(idx);
					distance += trip.getDistance();
				} else {
					throw new NoSuchRouteError();
				}
				
			} else {
				throw new NoSuchRouteError();
			}
		}
		return distance;
	}
	
	int getNumberOfTrips(String from, String to, StopCondition sc) {
		final Stop f = new Stop(from);
		final List<Stop> stops = this.getMap().get(f);
		final TripCounter tc = new TripCounter(this, sc);
		tc.getTrips(stops, f, new Stop(to));
		return tc.getValue();
	}
	
	int getShortestRoute(String f, String t) {
		final Stop from = new Stop(f);
		final Stop to = new Stop(t);
		final Heap<Integer, Stop> heap = new Heap<>();
		final Set<Stop> visited = new HashSet<>();
		final Map<Stop, Integer> scores = new HashMap<>();
		final int defaultScore = Integer.MAX_VALUE;
		for (Stop s : this.map.keySet()) {
			heap.put(defaultScore, s);
			scores.put(s, defaultScore);
		}
		heap.put(0, from);
		while (heap.isEmpty() == false) {
			final Entry<Integer, Stop> top = heap.pop();
			final Stop current = top.getValue();
			final int currentScore = top.getKey();
			scores.put(current, currentScore);
			visited.add(current);
			final List<Stop> stops = this.map.get(current);
			for (Stop s : stops) {
				if (visited.contains(s) == false) {
					final int oldScore = scores.get(s);
					heap.remove(oldScore);
					final int distance = s.getDistance();
					final int tempScore = currentScore + distance;
					final int newScore = Math.min(oldScore, tempScore);
					scores.put(s, newScore);
					heap.put(newScore, s);
				}
			}
		}
		return scores.get(to);
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (Entry<Stop, List<Stop>> e : this.getMap().entrySet()) {
			final Stop start = e.getKey();
			final List<Stop> trips = e.getValue();
			for (Stop trip : trips) {
				final String end = trip.getEnd();
				final int distance = trip.getDistance();
				sb.append(start).append(end).append(String.valueOf(distance));
			}
		}
		return sb.toString();
	}
	
}
