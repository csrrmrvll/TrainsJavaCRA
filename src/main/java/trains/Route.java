package trains;

public class Route {
	
	Node	node;
	int		distance;
	
	public Route(Node node, int distance) {
		this.node = node;
		this.distance = distance;
	}
	
	public final Node getNode() {
		return this.node;
	}
	
	public final int getDistance() {
		return this.distance;
	}
	
}
