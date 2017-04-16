package trains;

public class RouteCondition {
	
	private Town to;
	
	public RouteCondition(Town to) {
		this.to = to;
	}
	
	public boolean mustCount(Route route) {
		return route.getTo().equals(this.to) && this.virtualMustCount(route);
		
	}
	
	protected boolean virtualMustCount(Route route) {
		return route.getTo().equals(this.to);
	}
	
}