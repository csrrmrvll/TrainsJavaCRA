package trains;

class EqualToStopsondition extends StopCondition {
	
	EqualToStopsondition(int limit) {
		super(limit);
	}
	
	@Override
	protected boolean virtualMustStop() {
		return this.counter.getValue() == this.limit;
	}
}
