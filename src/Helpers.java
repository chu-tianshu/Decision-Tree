import java.util.HashMap;
import java.util.Map;

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

	public static Map<Integer, String> reverseMap(Map<String, Integer> map) {
		Map<Integer, String> result = new HashMap<>();
		for (String key : map.keySet()) result.put(map.get(key), key);
		return result;
	}
}
