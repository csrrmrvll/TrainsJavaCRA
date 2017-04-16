package trains;

abstract class LimitRouteCondition extends RouteCondition {
	
	private int limit;
	
	LimitRouteCondition(Town to, int limit) {
		super(to);
		this.limit = limit;
	}
	
	protected int getLimit() {
		return this.limit;
	}
}