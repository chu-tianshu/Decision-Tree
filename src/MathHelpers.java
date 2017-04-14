import java.util.HashMap;
import java.util.Map;

public class MathHelpers {
	public static double calcEntropy(int numY, int numN) {
		if (numY == 0 || numN == 0) return 0.0;
		
		double pY = numY / (numY + numN + 0.0);
		return (-pY * Math.log(pY) / Math.log(2) - (1 - pY) * Math.log(1 - pY) / Math.log(2));
	}
	
	public static double calcEntropy(int[][] trainingSet) {
		int numY = 0;
		int numN = 0;
		
		for (int[] ins : trainingSet) {
			if (ins[ins.length - 1] == 1) numY++;
			else numN++;
		}
		
		return calcEntropy(numY, numN);
	}
	
	public static double calcEntropy(int[][] trainingSet, int attrIdx, int choiceNumber) {
		int numY = 0;
		int numN = 0;
		
		for (int i = 0; i < trainingSet.length; i++) {
			if (trainingSet[i][attrIdx] == choiceNumber) {
				if (trainingSet[i][trainingSet[i].length - 1] == 1) numY++;
				else numN++;
			}
		}
		
		return calcEntropy(numY, numN);
	}
	
	public static double calcInformationGain(int[][] trainingSet, int attrIdx, double entropy) {
		double sumPartialEntropy = 0.0;
		
		Map<Integer, Integer> choiceToCount = new HashMap<>();
		for (int i = 0; i < trainingSet.length; i++) {
			int currChoice = trainingSet[i][attrIdx];
			if (choiceToCount.containsKey(currChoice)) choiceToCount.put(currChoice, choiceToCount.get(currChoice) + 1);
			else choiceToCount.put(currChoice, 1);
		}
		
		for (Integer choice : choiceToCount.keySet()) {
			double portion = (double) choiceToCount.get(choice) / trainingSet.length;
			sumPartialEntropy += portion * MathHelpers.calcEntropy(trainingSet, attrIdx, choice);
		}
		
		System.out.println(entropy - sumPartialEntropy);
		return (entropy - sumPartialEntropy);
	}
}
