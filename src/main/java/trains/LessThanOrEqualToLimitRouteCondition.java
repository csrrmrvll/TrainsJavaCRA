package trains;

class LessThanOrEqualToLimitRouteCondition extends LimitRouteCondition {
	
	LessThanOrEqualToLimitRouteCondition(Town to, int limit) {
		super(to, limit);
	}
	
	@Override
	protected boolean virtualMustCount(Route route) {
		return route.size() <= this.getLimit();
	}
}