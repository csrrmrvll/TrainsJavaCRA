package trains;

class EqualToStopsCondition extends StopCondition {
	
	EqualToStopsCondition(int limit) {
		super(limit);
	}
	
	@Override
	protected boolean virtualMustStop() {
		return this.counter.getValue() == this.limit;
	}
}
