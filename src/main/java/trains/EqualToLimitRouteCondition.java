package trains;

class EqualToLimitRouteCondition extends LimitRouteCondition {
	
	EqualToLimitRouteCondition(Town to, int limit) {
		super(to, limit);
	}
	
	@Override
	protected boolean virtualMustCount(Route route) {
		return route.size() == this.getLimit();
	}
}
