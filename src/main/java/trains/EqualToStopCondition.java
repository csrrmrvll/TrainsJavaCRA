package trains;

class EqualToStopCondition extends StopCondition {
	
	EqualToStopCondition(int limit) {
		super(limit);
	}
	
	@Override
	protected boolean virtualMustStop() {
		return this.getCounter().getValue() == this.getLimit();
	}
}
