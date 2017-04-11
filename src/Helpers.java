public class Helpers {
	public static int[][] getSubTrainingSet(int[][] trainingSet, int attributeUsed, int choice) {
		int numberOfInstances = 0;
		
		for (int i = 0; i < trainingSet.length; i++)
			if (trainingSet[i][attributeUsed] == choice) numberOfInstances++;
		
		int[][] result = new int[numberOfInstances][trainingSet[0].length];
		
		int resultIndex = 0;
		
		for (int i = 0; i < trainingSet.length; i++)
			if (trainingSet[i][attributeUsed] == choice) result[resultIndex++] = trainingSet[i];
		
		return result;
	}
}
