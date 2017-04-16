package trains;

class LessThanLimitRouteCondition extends LimitRouteCondition {
	
	LessThanLimitRouteCondition(Town to, int limit) {
		super(to, limit);
	}
	
	@Override
	protected boolean virtualMustCount(Route route) {
		return route.getDistance() < this.getLimit();
	}
	
}
