package trains;

public class Route {
	
	String	start;
	String	end;
	
	public Route(String route) {
		this(route.substring(0, 1), route.substring(1, 2));
	}
	
	public Route(String start, String end) {
		this.start = start;
		this.end = end;
	}
	
	public final String getStart() {
		return this.start;
	}
	
	public final String getEnd() {
		return this.end;
	}
	
	@Override
	public String toString() {
		return this.start.toString() + this.end.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.end == null) ? 0 : this.end.hashCode());
		result = prime * result + ((this.start == null) ? 0 : this.start.hashCode());
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
		Route other = (Route) obj;
		if (this.end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!this.end.equals(other.end)) {
			return false;
		}
		if (this.start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!this.start.equals(other.start)) {
			return false;
		}
		return true;
	}
	
}
