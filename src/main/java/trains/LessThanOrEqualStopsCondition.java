package trains;

public class LessThanOrEqualStopsCondition extends StopCondition {
	
	LessThanOrEqualStopsCondition(int limit) {
		super(limit);
	}
	
	@Override
	protected boolean virtualMustStop() {
		return this.counter.getValue() > this.limit;
	}
}