package trains;

public class LessThanStopCondition extends StopCondition {
	
	public LessThanStopCondition(int limit) {
		super(limit);
	}
	
	@Override
	protected boolean virtualMustStop() {
		return this.counter.getValue() >= this.limit;
	}
	
}
