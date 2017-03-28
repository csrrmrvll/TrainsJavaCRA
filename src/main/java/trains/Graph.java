package trains;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Graph {
	
	final Map<Route, Integer> map = new HashMap<>();
	
	public Graph(List<String> nodes) {
		for (String s : nodes) {
			final String start = s.substring(0, 1);
			final String end = s.substring(1, 2);
			final Route route = new Route(start, end);
			final int distance = Integer.valueOf(s.substring(2, 3));
			this.map.put(route, distance);
		}
	}
	
	public Set<Entry<Route, Integer>> getRoutes() {
		return this.map.entrySet();
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
			final Route trip = new Route(route.substring(i, i + 2));
			if (this.map.containsKey(trip)) {
				distance += this.map.get(trip);
			} else {
				throw new NoSuchRouteError();
			}
		}
		return distance;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (Entry<Route, Integer> e : this.map.entrySet()) {
			final Route route = e.getKey();
			final int distance = e.getValue();
			sb.append(route).append(String.valueOf(distance));
		}
		return sb.toString();
	}
	
}
