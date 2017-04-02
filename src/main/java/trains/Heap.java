package trains;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

class Heap<K, V> {
	
	private TreeMap<K, List<V>> data;
	
	Heap() {
		this.data = new TreeMap<>();
	}
	
	boolean notEmpty() {
		return this.data.isEmpty() == false;
	}
	
	V put(K key, V value) {
		List<V> values = this.data.get(key);
		if (values == null) {
			values = new ArrayList<>();
		}
		values.add(value);
		final List<V> oldValues = this.data.put(key, values);
		return (oldValues == null || oldValues.isEmpty()) ? null : oldValues.get(oldValues.size() - 1);
	}
	
	V remove(K key) {
		final List<V> values = this.data.remove(key);
		if (values == null || values.isEmpty()) {
			return null;
		}
		final V value = values.remove(0);
		if (values.size() == 1) {
			return value;
		}
		this.data.put(key, values);
		return value;
	}
	
	private Entry<K, List<V>> firstEntry() {
		return this.data.firstEntry();
	}
	
	Entry<K, V> top() {
		final Entry<K, List<V>> firstEntry = this.firstEntry();
		final TreeMap<K, V> map = new TreeMap<>();
		map.put(firstEntry.getKey(), firstEntry.getValue().get(0));
		return map.firstEntry();
	}
	
	Entry<K, V> pop() {
		final Entry<K, List<V>> firstEntry = this.firstEntry();
		final List<V> values = firstEntry.getValue();
		final V value = values.remove(0);
		final K key = firstEntry.getKey();
		if (values.isEmpty()) {
			this.remove(key);
		}
		final TreeMap<K, V> map = new TreeMap<>();
		map.put(key, value);
		return map.firstEntry();
	}
	
	@Override
	public String toString() {
		return this.data.toString();
	}
}
