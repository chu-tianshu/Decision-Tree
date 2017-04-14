import java.util.List;
import java.util.Map;

public class ChartParser {
	public static int[][] parseChart(List<String[]> chart, Map<String, Integer> choiceToNumber) {
		int numAttr = chart.get(0).length - 1;
		
		int[][] result = new int[chart.size()][numAttr + 1];
		int currNumber = 0;
		
		for (int i = 0; i < chart.size(); i++) {
			String[] instance = chart.get(i);
			result[i][result[0].length - 1] = Integer.parseInt(instance[instance.length - 1]);
			
			for (int j = 0; j < numAttr; j++) {
				String name = instance[j];
				
				if (choiceToNumber.containsKey(name)) {
					result[i][j] = choiceToNumber.get(name);
				} else {
					result[i][j] = currNumber;
					choiceToNumber.put(name, currNumber);
					currNumber++;
				}
			}
		}
		
		return result;
	}
}
