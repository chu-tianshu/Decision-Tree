public class Connection {
	public Connection(int idx1, int idx2, String et, String l) {
		startIdx = idx1;
		endIdx = idx2;
		endType = et;
		label = l;
	}
	
	public int[] getAttrIndices() {
		return new int[] {startIdx, endIdx};
	}
	
	public String getEndType() {
		return endType;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String toString() {
		return ("attr" + startIdx + " -> " + endType + endIdx + "[label=\"" + label + "\"]");
	}
	
	private int startIdx;
	private int endIdx;
	private String endType;
	private String label;
}
