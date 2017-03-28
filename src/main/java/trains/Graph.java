package trains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graph {
	
	final Map<Node, List<Route>> map = new HashMap<>();
	
	public Graph(List<String> nodes) {
		for (String s : nodes) {
			final Node start = new Node(s.substring(0, 1));
			final Node end = new Node(s.substring(1, 2));
			final int distance = Integer.valueOf(s.substring(2, 3));
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
		final StringBuilder sb = new StringBuilder();
		for (Entry<Node, List<Route>> e : this.map.entrySet()) {
			final Node n = e.getKey();
			final List<Route> lr = e.getValue();
			for (Route r : lr) {
				final String route = r.toString();
				sb.append(n).append(route);
			}
		}
		return sb.toString();
	}
	
}
