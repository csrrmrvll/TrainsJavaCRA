package trains;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Graph {
	
	Map<Node, List<Route>> map;
	
	public Graph(List<String> nodes) {
		for (String s : nodes) {
			final Node start = new Node(s.charAt(0));
			final Node end = new Node(s.charAt(1));
			final int distance = Integer.valueOf(s.charAt(2));
			final Route route = new Route(end, distance);
			if (this.map.containsKey(start)) {
				this.map.get(start).add(route);
			} else {
				this.map.put(start, Arrays.asList(route));
			}
		}
	}
	
	public final Collection<List<Route>> getEdges() {
		return this.map.values();
	}
	
}
