package trains;

abstract class StopCondition {
	
	private int		limit;
	private Counter	counter;
	
	StopCondition(int limit) {
		this.limit = limit;
		this.counter = new Counter();
	}
	
	protected int getLimit() {
		return this.limit;
	}
	
	protected Counter getCounter() {
		return this.counter;
	}
	
	void increase() {
		this.counter.increase();
	}
	
	void increase(int n) {
		for (int i = 0; i < n; ++i) {
			this.counter.increase();
		}
	}
	
	boolean mustStop(Stop s) {
		this.increase(s.getDistance());
		return this.virtualMustStop();
	}
	
	boolean mustStop() {
		this.counter.increase();
		return this.virtualMustStop();
	}
	
	protected abstract boolean virtualMustStop();
	
	void decrease() {
		this.counter.decrease();
	}
}