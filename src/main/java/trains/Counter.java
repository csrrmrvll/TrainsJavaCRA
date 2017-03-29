package trains;

class Counter {
	
	private int value;
	
	public Counter() {
		this(0);
	}
	
	public Counter(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void addOne() {
		++this.value;
	}
	
	public void decrease() {
		--this.value;
	}
}