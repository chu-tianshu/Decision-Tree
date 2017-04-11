import java.util.List;
import java.util.Map;

public class ChartParser {
	public static int[][] parseChart(List<String[]> chart, Map<String, Integer> map) {
		int numAttr = chart.get(0).length - 1;
		
		int[][] result = new int[chart.size()][numAttr + 1];
		int[] currNumbers = new int[numAttr]; // currNumbers[i] starts from 0 and increments each time a new choice in the ith attribute appears.
		
		for (int i = 0; i < chart.size(); i++) {
			String[] instance = chart.get(i);
			result[i][result[0].length - 1] = Integer.parseInt(instance[instance.length - 1]);
			
			for (int j = 0; j < numAttr; j++) {
				String name = instance[j];
				
				if (map.containsKey(name)) {
					result[i][j] = map.get(name);
				} else {
					result[i][j] = currNumbers[j];
					map.put(name, currNumbers[j]);
					currNumbers[j]++;
				}
			}
		}
		
		return result;
	}
}
