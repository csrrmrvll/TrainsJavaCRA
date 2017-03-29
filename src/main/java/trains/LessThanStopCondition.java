package trains;

class LessThanStopCondition extends StopCondition {
	
	LessThanStopCondition(int limit) {
		super(limit);
	}
	
	@Override
	protected boolean virtualMustStop() {
		return this.getCounter().getValue() >= this.getLimit();
	}
	
}
