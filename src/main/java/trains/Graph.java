package trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graph {
	
	final Map<String, List<Trip>> map = new HashMap<>();
	
	public Graph(List<String> nodes) {
		for (String s : nodes) {
			final String start = s.substring(0, 1);
			List<Trip> elem = this.map.get(start);
			if (elem == null) {
				this.map.put(start, new ArrayList<>());
			}
			final String end = s.substring(1, 2);
			final int distance = Integer.valueOf(s.substring(2, 3));
			final Trip trip = new Trip(end, distance);
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
			final String start = route.substring(i, i + 1);
			final String end = route.substring(i + 1, i + 2);
			if (this.map.containsKey(start)) {
				final List<Trip> trips = this.map.get(start);
				final int idx = trips.indexOf(new Trip(end));
				if (idx != -1) {
					final Trip trip = trips.get(idx);
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
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (Entry<String, List<Trip>> e : this.map.entrySet()) {
			final String start = e.getKey();
			final List<Trip> trips = e.getValue();
			for (Trip trip : trips) {
				final String end = trip.getEnd();
				final int distance = trip.getDistance();
				sb.append(start).append(end).append(String.valueOf(distance));
			}
		}
		return sb.toString();
	}
	
}
