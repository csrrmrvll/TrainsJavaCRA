package trains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	
	final Map<Node, List<Route>> map = new HashMap<>();
	
	public Graph(List<String> nodes) {
		for (String s : nodes) {
			final Node start = new Node(s.charAt(0));
			final Node end = new Node(s.charAt(1));
			final int distance = Integer.valueOf(s.charAt(2));
			final Route route = new Route(end, distance);
			if (this.map.containsKey(start)) {
				final List<Route> routes = this.map.get(start);
				routes.add(route);
				this.map.put(start, routes);
			} else {
				final ArrayList<Route> routes = new ArrayList<>();
				routes.add(route);
				this.map.put(start, routes);
			}
		}
	}
	
	public final Collection<List<Route>> getEdges() {
		return this.map.values();
	}
	
	@Override
	public String toString() {
		return this.map.toString();
	}
	
}
