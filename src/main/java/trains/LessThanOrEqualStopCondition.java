package trains;

public class LessThanOrEqualStopCondition extends StopCondition {
	
	LessThanOrEqualStopCondition(int limit) {
		super(limit);
	}
	
	@Override
	protected boolean virtualMustStop() {
		return this.counter.getValue() > this.limit;
	}
}