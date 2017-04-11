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
		
		int currChoice = 0;
		
		for (int i = 0; i < trainingSet.length; i++) {
			if (trainingSet[i][attrIdx] == currChoice) {
				int currChoiceCount = 0;
				
				for (int index = 0; index < trainingSet.length; index++)
					if (trainingSet[index][attrIdx] == currChoice) currChoiceCount++;
				
				double portion = currChoiceCount * 1.0 / trainingSet.length;
				
				sumPartialEntropy = portion * MathHelpers.calcEntropy(trainingSet, attrIdx, currChoice);
				currChoice++;
			}
		}
		
		return (entropy - sumPartialEntropy);
	}
}
