package trains;

import java.util.Map.Entry;
import java.util.TreeMap;

class Heap<K, V> {
	
	private TreeMap<K, V> data;
	
	Heap() {
		this.data = new TreeMap<>();
	}
	
	boolean notEmpty() {
		return this.data.isEmpty() == false;
	}
	
	V put(K key, V value) {
		return this.data.put(key, value);
	}
	
	V remove(K key) {
		return this.data.remove(key);
	}
	
	Entry<K, V> top() {
		return this.data.firstEntry();
	}
	
	Entry<K, V> pop() {
		final Entry<K, V> top = this.top();
		this.remove(top.getKey());
		return top;
	}
	
	@Override
	public String toString() {
		return this.data.toString();
	}
}
