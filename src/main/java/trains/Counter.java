package trains;

class Counter {
	
	private int value;
	
	Counter() {
		this(0);
	}
	
	Counter(int value) {
		this.value = value;
	}
	
	int getValue() {
		return this.value;
	}
	
	void increase() {
		++this.value;
	}
	
	void increase(int v) {
		this.value += v;
	}
	
	void decrease() {
		--this.value;
	}
}