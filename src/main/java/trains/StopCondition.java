package trains;

abstract class StopCondition {
	
	protected int		limit;
	protected Counter	counter;
	
	public StopCondition(int limit) {
		this.limit = limit;
		this.counter = new Counter();
	}
	
	abstract boolean mustStop();
	
	void decrease() {
		this.counter.decrease();
	}
}