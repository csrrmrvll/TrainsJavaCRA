package trains;

public class LessThanOrEqualStopsCondition extends StopCondition {
	
	LessThanOrEqualStopsCondition(int limit) {
		super(limit);
	}
	
	@Override
	boolean mustStop() {
		this.counter.addOne();
		return this.counter.getValue() > this.limit;
	}
}