package trains;

class EqualToStopsCondition extends StopCondition {
	
	EqualToStopsCondition(int limit) {
		super(limit);
	}
	
	@Override
	boolean mustStop() {
		this.counter.addOne();
		return this.counter.getValue() == this.limit;
	}
}
