package trains;

public class Node {
	
	String town;
	
	public Node(char c) {
		this.town = String.valueOf(c);
	}
	
	public String getTown() {
		return this.town;
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
		Node other = (Node) obj;
		if (this.town == null) {
			if (other.town != null) {
				return false;
			}
		} else if (!this.town.equals(other.town)) {
			return false;
		}
		return true;
	}
	
}
