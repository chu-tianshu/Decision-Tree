public class Leaf {
	public Leaf(boolean res, int no) {
		result = res;
		number = no;
	}
	
	public boolean getResult() {
		return result;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String toString() {
		return ("leaf" + number + " [shape=\"plaintext\", label=\"" + (result ? "Yes" : "No") + "\"]");
	}
	
	private boolean result;
	private int number;
}
