import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DecisionTree {
	private static List<String[]> chart;
	private static int[][] trainingSet;
	private static Map<String, Integer> choiceToNumber;
	private static Map<String, Integer> attrToNumber;
	private static Node root;
	
	public static void main(String[] args) {
		chart = new ArrayList<>();
		chart.add(new String[] {"NVIDIA", "Intel", "Chrome", "0"});
		chart.add(new String[] {"Radeon", "Intel", "Linux", "1"});
		chart.add(new String[] {"DC", "AMD", "Windows", "1"});
		chart.add(new String[] {"NVIDIA", "Intel", "DOS", "0"});
		chart.add(new String[] {"Radeon", "Intel", "Windows", "1"});
		chart.add(new String[] {"Radeon", "Intel", "DOS", "0"});
		chart.add(new String[] {"Radeon", "AMD", "Windows", "0"});
		chart.add(new String[] {"Radeon", "AMD", "Linux", "1"});
		chart.add(new String[] {"DC", "AMD", "Chrome", "0"});
		chart.add(new String[] {"NVIDIA", "Intel", "Linux", "1"});
		chart.add(new String[] {"NVIDIA", "AMD", "Chrome", "1"});
		chart.add(new String[] {"NVIDIA", "AMD", "Windows", "0"});
		
		attrToNumber = new HashMap<>();
		attrToNumber.put("GPU", 0);
		attrToNumber.put("CPU", 1);
		attrToNumber.put("OS", 2);
		
		choiceToNumber = new HashMap<>();
		trainingSet = ChartParser.parseChart(chart, choiceToNumber);
		
		root = new Node(trainingSet, new HashSet<>());
		
		System.out.println(root);
	}
}
