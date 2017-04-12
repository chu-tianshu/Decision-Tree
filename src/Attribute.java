public class Attribute {
	public Attribute(String s, int no) {
		label = s;
		number = no;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String toString() {
		return ("attr" + number + " [shape=\"rectangle\", label=\"" + label + "\"]");
	}
	
	private String label;
	private int number;
}
