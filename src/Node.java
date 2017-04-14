import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
	public Node(int[][] trainingSet, Set<Integer> usedAttrs) {
		classificationResult = -1;
		isLeaf = false;
		attributeUsed = -1;
		children = null;
		choices = null;
		
		double entropy = MathHelpers.calcEntropy(trainingSet);
		
		if (entropy == 0) {
			isLeaf = true;
			classificationResult = trainingSet[0][trainingSet[0].length - 1];
		} else {
			if (usedAttrs.size() == trainingSet[0].length) {
				isLeaf = true;
				
				int numY = 0;
				int numN = 0;
				
				for (int i = 0; i < trainingSet.length; i++) {
					if (trainingSet[i][trainingSet[0].length - 1] == 1) numY++;
					else numN++;
				}
				
				if (numY >= numN) classificationResult = 1;
				else classificationResult = 0;
			} else {
				children = new ArrayList<>();
				choices = new ArrayList<>();
				
				double maxInfoGain = Double.MIN_VALUE;
				int maxInfoGainAttrIdx = -1;
				
				double[] informationGains = new double[trainingSet[0].length - 1];
				
				for (int i = 0; i < informationGains.length; i++) {
					if (usedAttrs.contains(i)) {
						informationGains[i] = -1;
						continue;
					}
					
					informationGains[i] = MathHelpers.calcInformationGain(trainingSet, i, entropy);
					
					if (informationGains[i] > maxInfoGain) {
						maxInfoGain = informationGains[i];
						maxInfoGainAttrIdx = i;
					}
				}
				
				attributeUsed = maxInfoGainAttrIdx;
				
				Set<Integer> usedAttrsForNextLayer = new HashSet<>();
				usedAttrsForNextLayer.addAll(usedAttrs);
				usedAttrsForNextLayer.add(attributeUsed);
				
				Set<Integer> choicesForUsedAttribute = new HashSet<>();
				for (int[] ins : trainingSet) choicesForUsedAttribute.add(ins[attributeUsed]);
				
				for (Integer choice : choicesForUsedAttribute) {
					children.add(new Node(Helpers.getSubTrainingSet(trainingSet, attributeUsed, choice), usedAttrsForNextLayer));
					choices.add(choice);
				}
			}
		}
	}
	
	public boolean isLeaf() {
		return isLeaf;
	}
	
	public int getClassificationResult() {
		return classificationResult;
	}
	
	public int getAttributeUsed() {
		return attributeUsed;
	}
	
	public int getChildrenCount() {
		return children.size();
	}
	
	public Node getChild(int i) {
		return children.get(i);
	}
	
	public int getChoice(int i) {
		return choices.get(i);
	}
	
	private boolean isLeaf;
	private int classificationResult;
	private int attributeUsed; // the attribute used to classify for the current layer
	private List<Node> children;
	private List<Integer> choices;
}
