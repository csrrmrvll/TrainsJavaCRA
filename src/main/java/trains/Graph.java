package trains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

class Graph {
	
	private final Map<Town, List<Road>>	map;
	private final Map<Road, Integer>	distances;
	
	public Graph(List<Road> roads) {
		this.map = new HashMap<>();
		this.distances = new HashMap<>();
		for (Road r : roads) {
			final Town from = r.getFrom();
			List<Road> stops = this.getRoads(from);
			if (stops == null) {
				stops = new ArrayList<>();
				this.map.put(from, stops);
			}
			stops.add(r);
			Collections.sort(stops);
			final int distance = r.getDistance();
			this.distances.put(r, distance);
		}
	}
	
	private List<Road> getRoads(Town from) {
		return this.map.get(from);
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
	
	public int getRouteDistance(Route route) throws NoSuchRouteError {
		int distance = 0;
		for (Road r : route.getRoads()) {
			final List<Road> roads = this.getRoads(r.getFrom());
			if (roads.contains(r)) {
				distance += this.getDistance(r);
			} else {
				throw new NoSuchRouteError();
			}
		}
		return distance;
	}
	
	private int getDistance(Road r) {
		return this.distances.containsKey(r) ? this.distances.get(r) : 0;
	}
	
	private final Map<Town, List<Route>>	routes		= new HashMap<>();
	
	private static final int				MAX_STOPS	= 10;
	
	private void depthFirstSearch(Town from, Town to, Stack<Road> path) {
		final List<Road> roads = this.map.get(to);
		for (Road r : roads) {
			final Town t = r.getTo();
			if (path.size() < MAX_STOPS) {
				path.add(r);
				this.depthFirstSearch(from, t, path);
			} else {
				@SuppressWarnings("unchecked")
				final List<Road> olr = (List<Road>) path.clone();
				olr.add(r);
				final Route route = new Route(olr);
				if (this.routes.containsKey(from) == false) {
					this.routes.put(from, new ArrayList<>());
				}
				this.routes.get(from).add(route);
			}
		}
		if (path.isEmpty() == false) {
			@SuppressWarnings("unchecked")
			final Route route = new Route((List<Road>) path.clone());
			this.routes.get(from).add(route);
			path.pop();
		}
	}
	
	private void computeRoutes() {
		for (Entry<Town, List<Road>> e : this.map.entrySet()) {
			final Town from = e.getKey();
			Stack<Road> sr = new Stack<>();
			this.depthFirstSearch(from, from, sr);
		}
	}
	
	private void getRoutes() {
		this.computeRoutes();
	}
	
	public List<Route> getRoutes(Town from, Town to) {
		this.getRoutes();
		final List<Route> routes = this.routes.get(from);
		final List<Route> result = new ArrayList<>();
		for (Route r : routes) {
			if (r.getTo().equals(to)) {
				result.add(r);
			}
		}
		return result;
	}
	
	private int getShortestRoute(List<Route> routes) {
		int distance = Integer.MAX_VALUE;
		for (Route r : routes) {
			final int d = r.getDistance();
			if (d < distance) {
				distance = d;
			}
		}
		return distance;
	}
	
	public int getShortestRoute(Town from, Town to) {
		final List<Route> routes = this.getRoutes(from, to);
		return this.getShortestRoute(routes);
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (Entry<Town, List<Road>> e : this.map.entrySet()) {
			final Town from = e.getKey();
			final List<Road> roads = e.getValue();
			for (Road r : roads) {
				final int distance = this.getDistance(r);
				sb.append(from).append(r.getTo()).append(String.valueOf(distance));
			}
		}
		return sb.toString();
	}
	
	public int getNumberOfTrips(Town from, Town to, RouteCondition sc) {
		this.getRoutes();
		int counter = 0;
		final List<Route> trips = this.routes.get(from);
		for (Route r : trips) {
			if (sc.mustCount(r)) {
				++counter;
			}
		}
		return counter;
	}
	
}
