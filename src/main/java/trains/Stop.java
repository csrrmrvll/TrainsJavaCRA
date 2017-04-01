package trains;

class Stop {
	
	private Town	town;
	private int		distance;
	
	Stop(Town to, int distance) {
		this.town = to;
		this.distance = distance;
	}
	
	int getDistance() {
		return this.distance;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.town == null) ? 0 : this.town.hashCode());
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
		Stop other = (Stop) obj;
		if (this.town == null) {
			if (other.town != null) {
				return false;
			}
		} else if (!this.town.equals(other.town)) {
			return false;
		}
		return true;
	}
	
	public Town getTown() {
		return this.town;
	}
	
	@Override
	public String toString() {
		return this.town.toString() + this.distance;
	}
}
