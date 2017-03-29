package trains;

class Stop {
	
	private String	end;
	private int		distance;
	
	Stop(String end) {
		this(end, 0);
	}
	
	Stop(String end, int distance) {
		this.end = end;
		this.distance = distance;
	}
	
	final String getEnd() {
		return this.end;
	}
	
	int getDistance() {
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
		Stop other = (Stop) obj;
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
