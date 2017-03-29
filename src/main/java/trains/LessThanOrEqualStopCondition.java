package trains;

class LessThanOrEqualStopCondition extends StopCondition {
	
	LessThanOrEqualStopCondition(int limit) {
		super(limit);
	}
	
	@Override
	protected boolean virtualMustStop() {
		return this.getCounter().getValue() > this.getLimit();
	}
}