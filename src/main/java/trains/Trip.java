package trains;

import java.util.ArrayList;
import java.util.List;

class Trip {
	
	private Stop		from;
	private Stop		to;
	private List<Stop>	stops;
	private int			distance;
	
	Trip(Stop from, Stop to) {
		this.from = from;
		this.to = to;
		this.stops = new ArrayList<>();
		this.distance = 0;
	}
	
	void addStop(Stop s) {
		this.stops.add(s);
		this.distance += s.getDistance();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.distance;
		result = prime * result + ((this.from == null) ? 0 : this.from.hashCode());
		result = prime * result + ((this.stops == null) ? 0 : this.stops.hashCode());
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
		Trip other = (Trip) obj;
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
		if (this.stops == null) {
			if (other.stops != null) {
				return false;
			}
		} else if (!this.stops.equals(other.stops)) {
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
}
