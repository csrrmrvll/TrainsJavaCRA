package trains;

abstract class StopCondition {
	
	protected int		limit;
	protected Counter	counter;
	
	public StopCondition(int limit) {
		this.limit = limit;
		this.counter = new Counter();
	}
	
	boolean mustStop() {
		this.counter.addOne();
		return this.virtualMustStop();
	}
	
	protected abstract boolean virtualMustStop();
	
	void decrease() {
		this.counter.decrease();
	}
}