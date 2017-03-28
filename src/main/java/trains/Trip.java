package trains;

public class Trip {
	
	String	end;
	int		distance;
	
	public Trip(String end) {
		this(end, 0);
	}
	
	public Trip(String end, int distance) {
		this.end = end;
		this.distance = distance;
	}
	
	public final String getEnd() {
		return this.end;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.end == null) ? 0 : this.end.hashCode());
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
		Trip other = (Trip) obj;
		if (this.end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!this.end.equals(other.end)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return this.end + this.distance;
	}
	
}
