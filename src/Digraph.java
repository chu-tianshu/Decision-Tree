import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Digraph {
	public Digraph() {
		attrs = new ArrayList<>();
		leaves = new ArrayList<>();
		conns = new ArrayList<>();
		
		attrNumber = 1;
		leafNumber = 1;
	}
	
	public Digraph(Node root, Map<Integer, String> numberToChoice, Map<Integer, String> numberToAttr) {
		attrs = new ArrayList<>();
		leaves = new ArrayList<>();
		conns = new ArrayList<>();
		
		attrNumber = 1;
		leafNumber = 1;
		
		nodeToAttr = new HashMap<>();
		
		if (root.isLeaf()) {
			addLeaf(new Leaf(root.getClassificationResult() == 1, leafNumber++));
		} else {
			Attribute attr = new Attribute(numberToAttr.get(root.getAttributeUsed()), attrNumber++);
			addAttr(attr);
			nodeToAttr.put(root, attr);
			visit(root, numberToChoice, numberToAttr);
		}
	}
	
	public void addAttr(Attribute attr) {
		attrs.add(attr);
	}
	
	public void addLeaf(Leaf leaf) {
		leaves.add(leaf);
	}
	
	public void addConn(Connection conn) {
		conns.add(conn);
	}
	
	public List<Attribute> getAttrs() {
		return attrs;
	}
	
	public List<Leaf> getLeaves() {
		return leaves;
	}
	
	public List<Connection> getConns() {
		return conns;
	}
	
	public String toString() {
		String result = new String();
		
		for (Attribute attr : attrs) result += (attr.toString() + "\n");
		result += "\n";
		for (Leaf leaf : leaves) result += (leaf.toString() + "\n");
		result += "\n";
		for (Connection conn : conns) result += (conn.toString() + "\n");
		
		return result;
	}
	
	private void visit(Node node, Map<Integer, String> numberToChoice, Map<Integer, String> numberToAttr) {
		Attribute currAttr = nodeToAttr.get(node);
		
		for (int i = 0; i < node.getChildrenCount(); i++) {
			Node child = node.getChild(i);
			int choiceNumber = node.getChoice(i);
			
			if (child.isLeaf()) {
				addLeaf(new Leaf(child.getClassificationResult() == 1, leafNumber));
				addConn(new Connection(currAttr.getNumber(), leafNumber, "leaf", numberToChoice.get(choiceNumber)));
				leafNumber++;
			} else {
				int nextAttrNumber = child.getAttributeUsed();
				Attribute childAttr = new Attribute(numberToAttr.get(nextAttrNumber), attrNumber);
				nodeToAttr.put(child, childAttr);
				addAttr(childAttr);
				addConn(new Connection(currAttr.getNumber(), attrNumber, "attr", numberToChoice.get(choiceNumber)));
				attrNumber++;
				visit(child, numberToChoice, numberToAttr);
			}
		}
	}
	
	private List<Attribute> attrs;
	private List<Leaf> leaves;
	private List<Connection> conns; 
	
	private int attrNumber;
	private int leafNumber;
	
	private Map<Node, Attribute> nodeToAttr;
}