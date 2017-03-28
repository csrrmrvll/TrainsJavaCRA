package trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Graph {
	
	final Map<Stop, List<Stop>> map = new HashMap<>();
	
	public Graph(List<String> nodes) {
		for (String s : nodes) {
			final Stop start = new Stop(s.substring(0, 1));
			List<Stop> elem = this.map.get(start);
			if (elem == null) {
				this.map.put(start, new ArrayList<>());
			}
			final String end = s.substring(1, 2);
			final int distance = Integer.valueOf(s.substring(2, 3));
			final Stop trip = new Stop(end, distance);
			this.map.get(start).add(trip);
		}
	}
	
	public class NoSuchRouteError extends Exception {
		
		/**
		 * 
		 */
		private static final long	serialVersionUID	= -5969432656469507171L;
		
		public static final String	NO_SUCH_ROUTE		= "NO SUCH ROUTE";
		
		public NoSuchRouteError(String message) {
			super(message);
		}
		
		public NoSuchRouteError() {
			this(NO_SUCH_ROUTE);
		}
	}
	
	public int getRouteDistance(String route) throws NoSuchRouteError {
		int distance = 0;
		for (int i = 0; i < route.length() - 1; ++i) {
			final Stop start = new Stop(route.substring(i, i + 1));
			final Stop end = new Stop(route.substring(i + 1, i + 2));
			if (this.map.containsKey(start)) {
				final List<Stop> trips = this.map.get(start);
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
	
	private void getNumberOfTrips(List<Stop> stops, Stop to, Set<Stop> explored, int nosts, int nots) {
		for (Stop s : stops) {
			explored.add(s);
			++nots;
			if (++nosts > 3) {
				return;
			}
			if (to.equals(s)) {
				++nots;
				return;
			}
			final List<Stop> newStops = this.map.get(s);
			this.getNumberOfTrips(newStops, to, explored, nosts, nots);
		}
	}
	
	public int getNumberOfTrips(String from, String to) {
		final Set<Stop> explored = new HashSet<>();
		explored.add(new Stop(from));
		final List<Stop> stops = this.map.get(from);
		final Integer nosts = 0;
		this.getNumberOfTrips(stops, new Stop(to), explored, nosts, 0);
		return nosts;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (Entry<Stop, List<Stop>> e : this.map.entrySet()) {
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
