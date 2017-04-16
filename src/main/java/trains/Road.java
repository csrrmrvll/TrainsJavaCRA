package trains;

public class Road implements Comparable<Road> {
	
	private Town	from;
	private Town	to;
	private int		distance;
	
	public Road(Town from, Town to, int distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.distance;
		result = prime * result + ((this.from == null) ? 0 : this.from.hashCode());
		result = prime * result + ((this.to == null) ? 0 : this.to.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Road other = (Road) obj;
		if (this.distance != other.distance) {
			return false;
		}
		if (this.from == null) {
			if (other.from != null) {
				return false;
			}
		} else if (!this.from.equals(other.from)) {
			return false;
		}
		if (this.to == null) {
			if (other.to != null) {
				return false;
			}
		} else if (!this.to.equals(other.to)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return this.from.toString() + this.to.toString() + this.distance;
	}
	
	public Town getFrom() {
		return this.from;
	}
	
	public Town getTo() {
		return this.to;
	}
	
	@Override
	public int compareTo(Road o) {
		if (this.distance < o.getDistance()) {
			return -1;
		}
		if (this.distance > o.getDistance()) {
			return 1;
		}
		return 0;
	}
	
	public int getDistance() {
		return this.distance;
	}
}
